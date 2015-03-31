package com.yizw.newhouselevy.AsyncTask;

import java.util.HashMap;

import android.os.AsyncTask;
import android.widget.Toast;

import com.cogent.core.component.DataTableList;
import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.RebackInfo;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.Entity.CONFIRM_PERSON;
import com.yizw.newhouselevy.Entity.CmdCode;
import com.yizw.newhouselevy.UI.HouGYGRConfirmTable;

public class FamilyRegGYGRListAsyncTask extends AsyncTask<Void, String, HashMap<String, DataTableList>> {

	private HouGYGRConfirmTable activity;
 
	
//	public HouseholdListAsyncTask(HouseholdListA activity,String houseowner,String cardno){
//		this.activity = activity;
//		this.houseowner = houseowner;
//		this.cardno = cardno;
//	}
	
	
	public FamilyRegGYGRListAsyncTask(HouGYGRConfirmTable activity){
		this.activity = activity; 
	}
	
	
	
	@Override
	protected void onPreExecute() {
	 
	}
	
	
	@Override
	protected HashMap<String, DataTableList> doInBackground(Void...params) {
		
		try {
			MessageUploader messageUploader = new MessageUploader(activity,new MsgConfig());						
			UploadMsg msg = new UploadMsg();
			UploadCmd cmd = new UploadCmd(CmdCode.SEARCH_PRO_FAMILY_REGLIST);
			cmd.addParameter("confirmid", activity.id);
			 
	 
			msg.addCmd(cmd);			
			
			RebackInfo rebackInfo = messageUploader.uploadMessage(true,msg);
			rebackInfo.throwException();
			return rebackInfo.getDataSetByDATA();		
		} catch (MyException e) {
			ExceptionHelper.Operate(e, false, activity);
			publishProgress(e.getShowMsg());
			return null;
		}
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		Toast.makeText(activity, values[0], Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onPostExecute(HashMap<String, DataTableList> result) {	
		if(result == null){
			//activity.txt_list_footer.setText(GlobalVar.footerInfo[2]);
		}else{
			//得到分页信息
		//	DataTableList pageList = result.get("page");
//			activity.pageDomain.itemTotal = Integer.valueOf(pageList.get(0).get("total").toString());
//			activity.pageDomain.pageItems = Integer.valueOf(pageList.get(0).get("pagesize").toString());
//			
			//得到数据项信息
			DataTableList itemList = result.get("item");
			if(itemList!=null){
				//将获取到的数据添加到页面中
				for(int i=0;i<itemList.size();i++){
					CONFIRM_PERSON person=new CONFIRM_PERSON();
					  try {
						MsgHelper.setValuesToEntity(person, itemList.get(i));
						activity.adapter.addItem(person);
					} catch (MyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				//通知更新
				activity.adapter.notifyDataSetChanged();
			}else{
			  //	activity.txt_list_footer.setText(GlobalVar.footerInfo[1]);
			}
		}
	//	activity.pageDomain.isloading = false;
	}
	
}
