package com.yizw.newhouselevy.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.R;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.FileHelper;
import com.cogent.core.util.LogHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.SystemInfo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class DatabaseManager {
	private final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "db_houselevy.sqlite";
	
	private Context context;
	public DatabaseManager(Context context) {
		this.context = context;	
	}

	/**获取数据库文件的路径*/
	public static String getDbPath(Context context){
		return getDbDir(context)+"/"+DB_NAME;
	}
	
	private static String DB_Dir = null;
	/**获取数据库文件的目录*/
	public static String getDbDir(Context context){
		if(DB_Dir == null){
			String packageName = SystemInfo.getPackageInfo(context).packageName;
			DB_Dir = "/data"+ Environment.getDataDirectory().getAbsolutePath() + "/"+ packageName+"/databases"; 
		}
		return DB_Dir;
	}
	
	/** 数据库是否存在*/
	public boolean existDatabase(){
		String dbfile = getDbPath(context);
		if(new File(dbfile).length()>0)
			return true;
		else
			return false;
	}
	
	/** 删除数据库*/
	public void deleteDatabase(){
		Log.i(GlobalVar.TAG, "删除数据库");
		LogHelper.writeToLogFlie("删除数据库");
		String dbfile = getDbPath(context);
		FileHelper.deleteFile(dbfile);
	}
	
	/** 初始化数据库*/
	public String initDatabse(DatabaseHelper helper){
		return initDatabse(helper,false);
	}

	/**
	 * 初始化数据库
	 * @param isforce 是否强行初始化(true:不管数据库是否已存在都重新进行初始化)
	 * @return
	 */
	public String initDatabse(DatabaseHelper helper,boolean isforce){
        try {
        	if(!isforce){//如果是强行初始化则不检测是否存在
	        	if(existDatabase())
	        		return "";
        	}
        	
    		Log.i(GlobalVar.TAG, "数据库初始化   数据库版本："+DatabaseHelper.DATABASE_VERSION);
    		copyDatabase();
			DatabaseHelper.init=true;
			helper.getWritableDatabase().setVersion(DatabaseHelper.DATABASE_VERSION);
			DatabaseHelper.init=false;				
        	return "";
		} catch (MyException e) {
			ExceptionHelper.Operate(e, false, context);
			return "数据库初始化失败";
		}
	}
	
	/** 将资源文件中的数据库文件复制到当前程序数据库目录下 */
	private void copyDatabase() throws MyException {
		try {
			
			File file = new File(getDbDir(context));
			if(!file.isDirectory())
				file.mkdir();
			String dbfile = getDbPath(context);
						
			FileOutputStream fos = new FileOutputStream(dbfile);
			byte[] buffer = new byte[BUFFER_SIZE];
			
			readDB(fos, buffer,R.raw.db_houselevy);
//				readDB(fos, buffer,R.raw.db_houselevy0);
//				readDB(fos, buffer,R.raw.db_houselevy1);
//				readDB(fos, buffer,R.raw.db_houselevy2);
//				readDB(fos, buffer,R.raw.db_houselevy3);
//				readDB(fos, buffer,R.raw.db_houselevy4);
//				readDB(fos, buffer,R.raw.db_houselevy5);
//				readDB(fos, buffer,R.raw.db_houselevy6);
			
			fos.close();
		} catch (FileNotFoundException e) {
			MyException myE = new MyException("初始化数据库出错",e.getMessage(),e.getStackTrace());
			throw myE;
		} catch (IOException e) {
			MyException myE = new MyException("初始化数据库出错",e.getMessage(),e.getStackTrace());
			throw myE;
		}
	}
	
	private void readDB(FileOutputStream fos, byte[] buffer,int db_id) throws IOException {
		int count;
		InputStream is;
		is = this.context.getResources().openRawResource(db_id);
		while ((count = is.read(buffer)) > 0) {
			fos.write(buffer, 0, count);
		}
		is.close();
	}
}
