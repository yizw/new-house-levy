package com.yizw.newhouselevy.AsyncTask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.RebackInfo;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.DownloadAsyncTask;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.SystemInfo;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.Entity.CmdCode;
import com.yizw.newhouselevy.UI.MainIndexA;

public class MainIndexUpdateAsyncTask extends AsyncTask<String, String, RebackInfo> {
	private MainIndexA activity;
	
	AlertDialog statusDialog =null;
	
	public MainIndexUpdateAsyncTask(MainIndexA activity){
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		statusDialog = DialogHelper.getStatusDialog(activity,"正在检测版本信息...");
	}
	

	@Override
	protected RebackInfo doInBackground(String... params) {
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = new UploadCmd(CmdCode.CHECK);
		cmd.addParameter("version", SystemInfo.getVersionCode(activity));
		msg.addCmd(cmd);

		try {
			MessageUploader messageUploader = new MessageUploader(activity,new MsgConfig());
			RebackInfo rebackInfo = messageUploader.uploadMessage(false, msg);			
			return rebackInfo;
		} catch (MyException e) {
			ExceptionHelper.Operate(e, false, activity);
			return null;
		}
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
	}
	
	@Override
	protected void onPostExecute(RebackInfo result) {
		//关掉状态提示
		if(statusDialog!=null)
			statusDialog.dismiss();
		
		if (result == null) {
			Toast.makeText(activity, "升级检测失败", Toast.LENGTH_LONG).show();
			return;
		}
		
		int flag = result.getFlag();
		if(flag == 1){
			Toast.makeText(activity, "已是最新版本", Toast.LENGTH_LONG).show();
//			if(activity.autoSync)
//				activity.syncData();
		}else if(flag == 2){
			final String updateAddress = result.getFailInfo();
			new AlertDialog.Builder(activity)
			.setMessage("检测到新版本，请进行升级")
			.setPositiveButton("确定",new OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String apk = GlobalVar.getSaveFolder() + "shequtong.apk";		
					DownloadAsyncTask task = new DownloadAsyncTask(activity,updateAddress, apk, true);
					task.execute("");
				}
			})
			.create().show();
		}	
	}
}
