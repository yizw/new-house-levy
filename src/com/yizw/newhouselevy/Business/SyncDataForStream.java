package com.yizw.newhouselevy.Business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.j256.ormlite.misc.TransactionManager;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.DAO.DatabaseManager;
import com.yizw.newhouselevy.Entity.CmdCode;

public class SyncDataForStream {	
	/**从服务端获取到的待同步数据总数量*/
	public static String serverReturnSqlcount;
	/**从服务端获取到的该次同步时间*/
	public static String serverReturnSyndate;//
	/**已经执行的sql语句数量*/
	public static int executeSqlNum = 0;
	
    private String syncZip = null;//用于接收服务端的未解压的同步数据，本地存放的路径
    private String syncXml = null;//用于接收服务端的同步数据，本地存放的路径
    private Context context;
    public DatabaseHelper databaseHelper;    
    
    public SyncDataForStream(Context context,DatabaseHelper databaseHelper){
    	this.context = context;
    	this.databaseHelper = databaseHelper;
    	
    	//如果未指定保存文件夹，则保存到应用程序对应的手机内存中
    	String saveFolder = GlobalVar.getSaveFolder();
    	if(StringHelper.isNullOrEmpty(saveFolder)){
    		syncZip = DatabaseManager.getDbDir(context)+"/SyncZip";
    		syncXml = DatabaseManager.getDbDir(context)+"/Sync.xml";
    	}else{
    		syncZip = saveFolder + "SyncZip";
    		syncXml = saveFolder + "Sync.xml";
    	}
    }
    
    /** 发送数据同步状态*/
	private final int Sync_SendStatus = 1;
	/** 数据同步执行结束*/
	private final int Sync_End = 2;
	
	private EventHandler mHandler = new EventHandler();
    
    /** 同步数据*/
    public void SyncData()
    {
    	//开启数据同步线程
		new Thread(new Runnable() {
			public void run() {			
				startSyncData();
			}
		}).start();
    }
    
    private long timeSync = 0;//用来记录同步使用时间
    /** 同步使用时间记录*/
    private void syncTime(String txt){
    	if(timeSync==0){
    		timeSync = System.currentTimeMillis();
    		Log.i(GlobalVar.TAG, "同步时间开始记录");
    	}else{
    		long temp = System.currentTimeMillis();
    		long used = temp - timeSync;
    		Log.i(GlobalVar.TAG, txt+",已使用:"+used);
    	}   		
    }
    
    /** 开始进行数据同步*/
    private void startSyncData(){
    	executeSqlNum = 0;
		String dbPath = DatabaseManager.getDbPath(context);
		syncTime("");
		try {
			File sourceFile = new File(dbPath);
			if (!sourceFile.exists())
				throw new MyException("缺少本地数据库","本地数据库不存在");			
			
			sendSyncStatus("正在向服务端请求数据");
			syncTime("向服务端请求数据");
			UploadMsg syncDataMsg = getSyncDataMsg();
			MessageUploader messageUploader = new MessageUploader(context, new MsgConfig());
			HttpURLConnection httpConn = messageUploader.uploadMessageForConn(true, syncDataMsg);
			
			ReadStreamWriteToDB(httpConn);			
			
			sendSyncStatus("最后的数据整理中...");
			syncTime("最后的数据整理中");
			SyncDataClear.clearData(databaseHelper);			
			
			//更新同步时间
			SysConfigBus.setSyncData(context, serverReturnSyndate);
			syncTime("同步完成");
			sendEnd(true,"");
			
		} catch (MyException e) {
			ExceptionHelper.Operate(e, false, context);
			sendEnd(false, e.getShowMsg());
		}
    }
   
	
	/** 构建数据同步的请求消息
	 * @throws MyException */
	private UploadMsg getSyncDataMsg() throws MyException{
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = new UploadCmd(CmdCode.SYN_DATA);
		cmd.addParameter("usn", new MsgConfig().getUSN(context));
		cmd.addParameter("lastsynDate",SysConfigBus.getSyncData(context));
		cmd.addParameter("hasuser",LoginBus.hasuser(databaseHelper));
		msg.addCmd(cmd);
		return msg;
	}
	
	/** 从连接中获取响应流，进行数据库同步*/
	private void ReadStreamWriteToDB(HttpURLConnection httpConn) throws MyException{
		try {  
			sendSyncStatus("服务端正在处理，请耐心等待...");
			syncTime("等待服务端处理");
			InputStream input = httpConn.getInputStream();
			long fileSize = httpConn.getContentLength();
			decompress(input,fileSize);
			httpConn.disconnect();			
			
			parseXml();
		} catch (IOException e) {
			throw new MyException("接收数据过程中出错",e.getMessage(),e.getStackTrace() );
		}
	}
	
    /** 接收数据流并解压缩,保存到XML文件中*/   
    private void decompress(InputStream input,long fileSize) throws MyException {   
    	long readCount = 0;
    	byte[] buffer = new byte[1024]; 
    	int n;
    	syncTime("接收数据");
    	try{
        	FileOutputStream outZip = new FileOutputStream(new File(syncZip)); 
        	while ((n = input.read(buffer)) >= 0) {   
        		outZip.write(buffer, 0, n);   
	            readCount = readCount + n;
	            int percent = (int) (readCount * 100 / fileSize);
	            sendSyncStatus("正在接收数据:"+percent+"%");
	        }
        	outZip.close();
        }catch(Throwable t) {
        	throw new MyException("接收数据过程中出错", "数据接收出错",t.getStackTrace());
        }
        
    	try{
    		sendSyncStatus("正在解压数据...");
    		syncTime("解压数据");
    		FileOutputStream outXml = new FileOutputStream(new File(syncXml));
    		FileInputStream zipStream = new FileInputStream(syncZip); 
	        GZIPInputStream gunzip = new GZIPInputStream(zipStream);	          
	           
	        while ((n = gunzip.read(buffer)) >= 0) {   
	        	outXml.write(buffer, 0, n);   
	        }   
	        zipStream.close();
	        outXml.close();
        }catch(Throwable t) {
        	throw new MyException("数据解压过程中出错", "数据解压出错",t.getStackTrace());
        }
    }

    /** 解析XML文件进行数据处理
     * @throws MyException  */
    private void parseXml() throws MyException{
    	syncTime("解析文件执行SQL");
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			final XMLReader reader = factory.newSAXParser().getXMLReader();
			reader.setContentHandler(new SyncDataXmlHandler(context,this));
			final InputStream inputStream = new FileInputStream(syncXml);
			TransactionManager mgr = new TransactionManager(databaseHelper.getConnectionSource());
			mgr.callInTransaction(new Callable<Void>() {
				public Void call() throws Exception{
					reader.parse(new InputSource(inputStream));										
					return null;
				}
			});

		} catch (SAXException saxE) {
			if(saxE.getMessage()!=null&&saxE.getMessage().equals("sql"))
				throw (MyException)saxE.getException();
			else
				throw new MyException("解析XML时出错",saxE.getMessage(),saxE.getStackTrace());
		} catch (ParserConfigurationException e) {
			throw new MyException("解析XML时出错",e.getMessage(),e.getStackTrace());
		} catch (FileNotFoundException e) {
			throw new MyException("解析XML时出错",e.getMessage(),e.getStackTrace());
		} catch (SQLException e) {
			throw new MyException("数据库操作出错",e.getMessage(),e.getStackTrace());
		}
    }

    

    //下面的都是事件的处理代码，虽然多，但很简单，结构都一样
    
    /** 发送数据同步状态*/
	public void sendSyncStatus(String status){
		Message msg = mHandler.obtainMessage(Sync_SendStatus);
		msg.obj = status;
		mHandler.sendMessage(msg);
	}
	
    /**
     * 数据同步结束通知事件
     * @param success true:同步成功 false：同步失败
     * @param info 描述信息
     */
	public void sendEnd(Boolean success,String info){
		Message msg = mHandler.obtainMessage(Sync_End);
		msg.arg1 = success == true?1:0;
		if(StringHelper.isNullOrEmpty(info))
			msg.obj = "";
		else
			msg.obj = info;
		mHandler.sendMessage(msg);
	}
	
	/** 自定义的 Handler*/
	@SuppressLint("HandlerLeak")
	private class EventHandler extends Handler {
		
		// 处理接收到的消息
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case Sync_SendStatus:
				if (mSyncStatusChangeListener != null)
					mSyncStatusChangeListener.syncStatusChange(msg.obj.toString());
				break;
			case Sync_End:
				if(mSyncEndListener != null){
					boolean success = msg.arg1 == 1 ? true:false;
					mSyncEndListener.syncEnd(success, msg.obj.toString());
				}
			default:
				break;
			}
		}
	}
	
	// 定义数据同步状态变化通知事件
	private SyncStatusChangeListener mSyncStatusChangeListener;
	public interface SyncStatusChangeListener {
		/**
		 * 数据同步状态变化通知事件
		 * @param 当前同步状态描述
		 */
		void syncStatusChange(String status);
	}
	public void setSyncStatusChangeListener(SyncStatusChangeListener listener) {
		mSyncStatusChangeListener = listener;
	}
	
	// 定义数据同步结束通知事件
	private SyncEndListener mSyncEndListener;
	public interface SyncEndListener {
		/**
		 * 数据同步结束通知事件
		 * @param success true:同步成功 false：同步失败
		 * @param info 描述信息
		 */
		void syncEnd(Boolean success,String info);
	}
	public void setSyncEndListener(SyncEndListener listener) {
		mSyncEndListener = listener;
	}
}
