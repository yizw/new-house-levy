package com.yizw.newhouselevy.Business;

import java.util.HashMap;

import android.content.Context;

import com.cogent.core.component.DataTableList;
import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.RebackInfo;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.Entity.CmdCode;
import com.yizw.newhouselevy.Entity.HouseRoomListMainParm;
import com.yizw.newhouselevy.UI.HouseRoomListA;

 public class QueryHouseRoomBus {

	public Context context;
	
    private HouseRoomListA activity;

	public QueryHouseRoomBus(Context context){
		this.context = context;
	}
	
	public HashMap<String, DataTableList> getEventListPage(HouseRoomListMainParm entity) throws MyException{
		MessageUploader messageUploader = new MessageUploader(context,new MsgConfig());
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd(CmdCode.SEARCH_H_HOUSE_ITEM_LIST,entity);
		
		cmd.addParameter("page", activity.pageDomain.getNowPage()+1);
    //  cmd.addParameter("pid", activity.pid);
		msg.addCmd(cmd);
		RebackInfo rebackInfo = messageUploader.uploadMessage(true,msg);
		rebackInfo.throwException();
		return rebackInfo.getDataSetByDATA();
	}
	
	public String check(HouseRoomListMainParm entity){
		StringBuilder strBuilder =new StringBuilder();		
		if(StringHelper.isNullOrEmpty(entity.getRoom()))
			PublicBus.addStr(strBuilder,"-查询时房间号不能为空哦");	
		
		return strBuilder.toString();
	}
	

}
