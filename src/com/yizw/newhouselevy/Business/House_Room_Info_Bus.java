package com.yizw.newhouselevy.Business;

import java.sql.SQLException;

import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.House_Room_Info;

public class House_Room_Info_Bus {
	
	DatabaseHelper helper;
	public House_Room_Info_Bus(DatabaseHelper databaseHelper){
		this.helper = databaseHelper;
	}
	
	public void create(House_Room_Info entity) throws MyException, SQLException{
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_HOUSE_ROOM_INFO", entity);
		msg.addCmd(cmd);
		
		PDA_MessageBus msgBus = new PDA_MessageBus(helper);
		msgBus.create(msg);
	}
	
	public static String check(House_Room_Info entity){
		StringBuilder strBuilder =new StringBuilder();
//		if(StringHelper.isNullOrEmpty(entity.getTitle()))
//			PublicBus.addStr(strBuilder, "-标题 不能为空");
		return strBuilder.toString();
        
	}
	
 }