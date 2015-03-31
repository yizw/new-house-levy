package com.yizw.newhouselevy.Business;

import java.sql.SQLException;

import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.AttConfirmItem;
import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;

public class AttConfirmItemBus {
	
	DatabaseHelper helper;
	public AttConfirmItemBus(DatabaseHelper databaseHelper){
		this.helper = databaseHelper;
	}
	
	public void create(AttConfirmItem entity) throws MyException, SQLException{
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_HLIST_ATT1_INFO", entity);
		msg.addCmd(cmd);
		
		PDA_MessageBus msgBus = new PDA_MessageBus(helper);
		msgBus.create(msg);
	}
	
	public static String check(AttConfirmItem entity){
		StringBuilder strBuilder =new StringBuilder();
		if(StringHelper.isNullOrEmpty(entity.getPrice()+""))
			PublicBus.addStr(strBuilder, "-单价 不能为空");
		return strBuilder.toString();
	    }
    }