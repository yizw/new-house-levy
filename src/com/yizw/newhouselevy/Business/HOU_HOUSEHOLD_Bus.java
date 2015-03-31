package com.yizw.newhouselevy.Business;
import java.sql.SQLException;

import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HOU_HOUSEHOLD;

public class HOU_HOUSEHOLD_Bus {
	DatabaseHelper helper;
	
	public HOU_HOUSEHOLD_Bus(DatabaseHelper databaseHelper){
		this.helper = databaseHelper;
	}
	
	public void create(HOU_HOUSEHOLD entity) throws MyException, SQLException{
	UploadMsg msg = new UploadMsg();
	UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_PRO_HOUSEHOLD", entity);
	msg.addCmd(cmd);
	PDA_MessageBus msgBus = new PDA_MessageBus(helper);
		msgBus.create(msg);
	}
	
	public static String check(HOU_HOUSEHOLD entity){
		StringBuilder strBuilder =new StringBuilder();
	
		if(StringHelper.isNullOrEmpty(entity.getHouseowner()))
			PublicBus.addStr(strBuilder, "-分户姓名不能为空");		
		if(StringHelper.isNullOrEmpty(entity.getHousehold_type()))
			PublicBus.addStr(strBuilder, "-分户类型不能为空");
		return strBuilder.toString();
	}
}
