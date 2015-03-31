package com.yizw.newhouselevy.AsyncTask;

import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.Entity.CmdCode;
import com.yizw.newhouselevy.UI.HListAttInfo;
import com.cogent.core.component.DataTableList;
import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.RebackInfo;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.widget.Toast;

/*算单 附件一   item  信息查询*/
public class HListAttInfoSearchAsyncTask extends AsyncTask<String, String, DataTableList> {

	private HListAttInfo activity;
	AlertDialog statusDialog;
	
	public HListAttInfoSearchAsyncTask(HListAttInfo activity){
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		statusDialog = PublicBus.getStatusDialog(activity, null);
		statusDialog.show();
	}
	@Override
	protected DataTableList doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			UploadMsg msg = new UploadMsg();
			UploadCmd cmd = new UploadCmd(CmdCode.SEARCH_HLIST_ATT_INFO);
			cmd.addParameter("id", params[0]);
			msg.addCmd(cmd);

			MessageUploader messageUploader = new MessageUploader(activity,new MsgConfig());
			RebackInfo rebackInfo = messageUploader.uploadMessage(true, msg);
			rebackInfo.throwException();		
			DataTableList table = rebackInfo.getDataTableByDATA();
			if(table == null)
				throw new MyException("未获取到信息","返回的是空");
			else
				return table;
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
	protected void onPostExecute(DataTableList result) {
		//关掉状态提示
		if(statusDialog!=null)
			statusDialog.dismiss();
		
		try {
			MsgHelper.setValuesToEntity(activity.entity, result);
			activity.setEntityToUI(activity.entity,activity.att);
		} catch (MyException e) {
			ExceptionHelper.Operate(e, true, activity);
		}
	}

}


