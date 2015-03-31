package com.yizw.newhouselevy.AsyncTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.RebackInfo;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.DAO.DatabaseManager;
import com.yizw.newhouselevy.UI.LoginA;
import com.yizw.newhouselevy.UI.MainIndexA;

/*修改用户信息（密码）*/
public class MainUserInfoSaveAsyncTask extends AsyncTask<String, String, RebackInfo> {

	private MainIndexA activity;
	AlertDialog statusDialog;
	
	public MainUserInfoSaveAsyncTask(MainIndexA activity){
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		statusDialog = PublicBus.getStatusDialog(activity, null);
	   //注释掉，避免View not attached to window manager
	//	statusDialog.show();
	}
	@Override
	protected RebackInfo doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			MessageUploader messageUploader = new MessageUploader(activity,new MsgConfig());						
			UploadMsg msg = new UploadMsg();
			UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_USER_INFO", activity.entity);
			msg.addCmd(cmd);		 
			RebackInfo rebackInfo = messageUploader.uploadMessage(true,msg); 
			return rebackInfo;
			
		} catch (MyException e) {
			ExceptionHelper.Operate(e, false, activity);
			publishProgress(e.getShowMsg());
		}
		return null;
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		Toast.makeText(activity, values[0], Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onPostExecute(RebackInfo rebackInfo) {
		//关掉状态提示
		if(statusDialog != null)
			statusDialog.dismiss(); 
		try {
			rebackInfo.throwException(); 
			
			Toast.makeText(activity, "保存成功", Toast.LENGTH_LONG).show();
			activity.setResult(Activity.RESULT_OK, null); 
			deleteDate();
			Intent intent = new Intent(activity, LoginA.class);
			activity.startActivity(intent);
			activity.finish();
			
		} catch (MyException e) {
			ExceptionHelper.Operate(e, true, activity);
		}
	}
    
	//修改密码后，初始化之前的用户数据
	public void deleteDate(){
		DatabaseManager dbm = new DatabaseManager(activity);
		String restr = dbm.initDatabse(new DatabaseHelper(activity),true);
		if(restr.equals("")){
			Toast.makeText(activity, "初始化成功", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(activity, "初始化失败", Toast.LENGTH_LONG).show();
		}
	}
}
