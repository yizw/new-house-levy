package com.yizw.newhouselevy.AsyncTask;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.yizw.newhouselevy.UI.HouseholdInfoA;

public class HouseholdInfoSaveAsyncTask extends AsyncTask<String, String, RebackInfo> {

	private HouseholdInfoA activity;
	AlertDialog statusDialog;
	
	public HouseholdInfoSaveAsyncTask(HouseholdInfoA activity){
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		statusDialog = PublicBus.getStatusDialog(activity, null);
		statusDialog.show();
	}
	@Override
	protected RebackInfo doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			MessageUploader messageUploader = new MessageUploader(activity,new MsgConfig());						
			UploadMsg msg = new UploadMsg();
			UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_PRO_HOUSEHOLD", activity.household);
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
		if(statusDialog!=null)
			statusDialog.dismiss(); 
		try {
			rebackInfo.throwException(); 
			
			Toast.makeText(activity, "保存成功", Toast.LENGTH_LONG).show();
			activity.setResult(Activity.RESULT_OK, null); 
			activity.finish();
		} catch (MyException e) {
			ExceptionHelper.Operate(e, true, activity);
		}
	}

}
