package com.yizw.newhouselevy.Business;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.cogent.core.msg.IUploadConfig;
import com.cogent.core.util.Base64;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;

public class SyncDataXmlHandler extends DefaultHandler {
	/** 节点名称*/
	String elementName;
	StringBuilder elementValue;
	private Context context;
	private SQLiteDatabase db;
	
	private SyncDataForStream syncDataForStream;
	public SyncDataXmlHandler(Context context,SyncDataForStream syncDataForStream) throws SQLException{
		this.context = context;
		this.syncDataForStream = syncDataForStream;
		db = syncDataForStream.databaseHelper.getWritableDatabase();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		elementName = localName;
		elementValue = new StringBuilder();//在开始解析一个节点时清空节点的值
	}
	

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if(elementValue==null)
			return;
		
		String value = elementValue.toString();
		byte[] myTemp = Base64.decode(value,Base64.DEFAULT);		
		try {//该异常不会出现，不进一步处理
			value = new String(myTemp, IUploadConfig.ENCODE_UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(elementName.equals("sqlcount")){
			SyncDataForStream.serverReturnSqlcount = value;
		}else if(elementName.equals("syndate")){
			SyncDataForStream.serverReturnSyndate = value;
		}else if(elementName.equals("sql")){		
			try {
				db.execSQL(value);
				SyncDataForStream.executeSqlNum ++;
				String status = "正在同步数据："+SyncDataForStream.executeSqlNum+"/"+SyncDataForStream.serverReturnSqlcount;
				syncDataForStream.sendSyncStatus(status);
				
				SyncDataClear.needClearCheck(value);
			} catch (Exception e) {
				MyException myE = new MyException("执行第"+(SyncDataForStream.executeSqlNum+1)+"条SQL时出错",value,e.getStackTrace());
				ExceptionHelper.Operate(myE, false, context);
				throw new SAXException("sql",myE);
			}
		}
		//节点解析结束，清空
		elementName = "";
		elementValue = null;
	}


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {	
		if (elementValue != null) {
			String str = new String(ch, start, length);
			if (length < 10) {
				str = str.replace("\n", "");
			}
			elementValue.append(str);
		}
	}

}
