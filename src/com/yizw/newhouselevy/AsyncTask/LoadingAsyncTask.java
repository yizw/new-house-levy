package com.yizw.newhouselevy.AsyncTask;

import android.content.Intent;
import android.os.AsyncTask;

import com.cogent.core.util.StringHelper;
import com.yizw.newhouselevy.Business.SysConfigBus;
import com.yizw.newhouselevy.Business.Sys_EnumBus;
import com.yizw.newhouselevy.DAO.DatabaseManager;
import com.yizw.newhouselevy.UI.LoadingA;
import com.yizw.newhouselevy.UI.LoginA;

public class LoadingAsyncTask extends AsyncTask<String, String, String>{
	/**加载页面显示的最小时间*/
	private int Show_time_min = 1500;
	private LoadingA activity;
	
	public LoadingAsyncTask(LoadingA activity){
		this.activity = activity;
	}
	
	@Override
	protected String doInBackground(String... params) {		
        long startTime = System.currentTimeMillis();
        
        publishProgress("正在初始化...");
        //初始化数据库
        DatabaseManager dbManager = new DatabaseManager(activity);
        String str;
		try {
			str = dbManager.initDatabse(activity.getHelper());
			boolean autoSync = SysConfigBus.getAutoSync(activity);
			if(!autoSync)//如果没有设置自动同步则进行枚举缓存处理
				Sys_EnumBus.putAllToCache(activity.getHelper());
		} catch (Exception e1) {
			str = "初始化出错,请关闭重新启动";
			Show_time_min = 5000;
			e1.printStackTrace();
		}
        if(!StringHelper.isNullOrEmpty(str))
        	publishProgress(str);
        
        long loadingTime = System.currentTimeMillis() - startTime; 
        if (loadingTime < Show_time_min) { 
            try { 
                Thread.sleep(Show_time_min - loadingTime); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 

		return "";
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		activity.txt_status.setText(values[0]);
	}
	
	@Override
	protected void onPostExecute(String result) {		
		activity.startActivity(new Intent(activity, LoginA.class));
		activity.finish();
	}
}
