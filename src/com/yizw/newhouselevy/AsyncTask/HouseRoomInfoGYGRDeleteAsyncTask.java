package com.yizw.newhouselevy.AsyncTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.RebackInfo;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.Entity.CmdCode;
import com.yizw.newhouselevy.UI.GYGRProExchangeA;

/*国有个人产权-删除已经选择的房源信息*/
public class HouseRoomInfoGYGRDeleteAsyncTask extends AsyncTask<String, String, RebackInfo> {

	private GYGRProExchangeA activity;
	AlertDialog statusDialog;
	private String hid;
	
	public HouseRoomInfoGYGRDeleteAsyncTask(GYGRProExchangeA activity,String hid){
		this.activity = activity;
		this.hid=hid;
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
			UploadCmd cmd= new UploadCmd(CmdCode.DELETE_HOUSE_ROOM_INFO);
		//	UploadCmd cmd = MsgHelper.buildUploadCmd(CmdCode.SAVE_HOUSE_ROOM_INFO, activity.entity);
			cmd.addParameter("listid",activity.in_id);
			cmd.addParameter("hid",hid);
			cmd.addParameter("index",activity.index);
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
			
			Toast.makeText(activity, "删除房源成功", Toast.LENGTH_LONG).show();
			activity.setResult(Activity.RESULT_OK, null);
			activity.finish();
		} catch (MyException e) {
			ExceptionHelper.Operate(e, true, activity);
		}
	}

}
