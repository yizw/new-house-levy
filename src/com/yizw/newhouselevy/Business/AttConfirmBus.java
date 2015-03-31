package com.yizw.newhouselevy.Business;

import java.sql.SQLException;

import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.AttConfirm;
import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;

public class AttConfirmBus {
	
	DatabaseHelper helper;
	public AttConfirmBus(DatabaseHelper databaseHelper){
		this.helper = databaseHelper;
	}
	
	public void create(AttConfirm entity) throws MyException, SQLException{
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_CONFIRM_ATT1_INFO", entity);
		msg.addCmd(cmd);
		
		PDA_MessageBus msgBus = new PDA_MessageBus(helper);
		msgBus.create(msg);
	}
	
	public static String check(AttConfirm entity){
		StringBuilder strBuilder =new StringBuilder();
		if(StringHelper.isNullOrEmpty(entity.getName()))
			PublicBus.addStr(strBuilder, "-名称 不能为空");
		return strBuilder.toString();
	    }
    }