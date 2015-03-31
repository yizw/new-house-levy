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
import com.yizw.newhouselevy.Entity.HouseholdListMainParm;
import com.yizw.newhouselevy.UI.HouseholdListA;

public class QueryHouseholdBus {
public Context context;
	
private HouseholdListA activity;

	public QueryHouseholdBus(Context context){
		this.context = context;
	}
	
	public HashMap<String, DataTableList> getEventListPage(HouseholdListMainParm entity) throws MyException{
		MessageUploader messageUploader = new MessageUploader(context,new MsgConfig());
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd(CmdCode.SEARCH_PRO_HOUSEHOLD_LIST,entity);
				
	    cmd.addParameter("pid", activity.pid);
		msg.addCmd(cmd);
		RebackInfo rebackInfo = messageUploader.uploadMessage(true,msg);
		rebackInfo.throwException();
		return rebackInfo.getDataSetByDATA();
	}
	
	public String check(HouseholdListMainParm entity){
		StringBuilder strBuilder =new StringBuilder();		
		if(StringHelper.isNullOrEmpty(entity.getHouseholdwner()))
			PublicBus.addStr(strBuilder,"-查询时分户姓名不能为空哦");		
		return strBuilder.toString();
	}
	

}
