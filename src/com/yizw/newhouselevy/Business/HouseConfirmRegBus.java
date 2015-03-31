package com.yizw.newhouselevy.Business;

import java.sql.SQLException;

import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HouseConfirmReg;

public class HouseConfirmRegBus {
	
	DatabaseHelper helper;
	public HouseConfirmRegBus(DatabaseHelper databaseHelper){
		this.helper = databaseHelper;
	}
	
	public void create(HouseConfirmReg entity) throws MyException, SQLException{
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_HOUSE_CONFIRM_REG", entity);
		msg.addCmd(cmd);
		
		PDA_MessageBus msgBus = new PDA_MessageBus(helper);
		msgBus.create(msg);
	}
	
	public static String check(HouseConfirmReg entity){
		StringBuilder strBuilder =new StringBuilder();
		if(StringHelper.isNullOrEmpty(entity.getC4()))
			PublicBus.addStr(strBuilder, "-产权证号 不能为空");
		return strBuilder.toString();
	    }
    }