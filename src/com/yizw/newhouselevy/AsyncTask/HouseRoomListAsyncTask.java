package com.yizw.newhouselevy.AsyncTask;

import java.util.HashMap;

import android.os.AsyncTask;
import android.widget.Toast;

import com.cogent.core.component.DataTableList;
import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.RebackInfo;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.Entity.CmdCode;
import com.yizw.newhouselevy.UI.HouseRoomListA;

/*房源-房间 列表查询*/
public class HouseRoomListAsyncTask extends AsyncTask<Void, String, HashMap<String, DataTableList>> {

	private HouseRoomListA activity;
	private String bno;
	private String unit;
	private String floor;
	private String room;
	private String listid;
	
	public HouseRoomListAsyncTask(HouseRoomListA activity,String bno,String unit,String floor,String room,String listid){
	this.activity = activity;
	this.bno = bno;
	this.unit = unit;
	this.floor = floor;
	this.room = room;
	this.listid=listid;
}
	
/*	public HouseRoomListAsyncTask(HouseRoomListA activity){
		this.activity = activity;
	}
	*/
	@Override
	protected void onPreExecute() {
		activity.txt_list_footer.setText(GlobalVar.footerInfo[0]);
		activity.pageDomain.isloading = true;
	}
	
	@Override
	protected HashMap<String, DataTableList> doInBackground(Void...params) {
		
		try {
			MessageUploader messageUploader = new MessageUploader(activity,new MsgConfig());					
			
			UploadMsg msg = new UploadMsg();
			UploadCmd cmd = new UploadCmd(CmdCode.SEARCH_H_HOUSE_ITEM_LIST);
			
			cmd.addParameter("bno",bno);
			cmd.addParameter("unit",unit);
			cmd.addParameter("floor",floor);
			cmd.addParameter("room",room);
			cmd.addParameter("listid",listid);
			cmd.addParameter("hid", activity.hid); 
			cmd.addParameter("pid", activity.pid); 
		    cmd.addParameter("page", activity.pageDomain.getNowPage()+1);
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
			activity.txt_list_footer.setText(GlobalVar.footerInfo[2]);
		}else{
			//得到分页信息
			DataTableList pageList = result.get("page");
			activity.pageDomain.itemTotal = Integer.valueOf(pageList.get(0).get("total").toString());
			activity.pageDomain.pageItems = Integer.valueOf(pageList.get(0).get("pagesize").toString());
			
			//得到数据项信息
			DataTableList itemList = result.get("item");
			if(itemList!=null){
				//将获取到的数据添加到页面中
				for(int i=0;i<itemList.size();i++){
					activity.adapter.addItem(itemList.get(i));
				}
				//通知更新
				activity.adapter.notifyDataSetChanged();
			}else{
				activity.txt_list_footer.setText(GlobalVar.footerInfo[1]);
			}
		}
		activity.pageDomain.isloading = false;
	}
	
}
