package com.yizw.newhouselevy.Business;

import java.sql.SQLException;

import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.TOTAL_PUSH_INFO;

public class TOTAL_PUSH_INFO_Bus {
	
	DatabaseHelper helper;
	public TOTAL_PUSH_INFO_Bus(DatabaseHelper databaseHelper){
		this.helper = databaseHelper;
	}
	
	public void create(TOTAL_PUSH_INFO entity) throws MyException, SQLException{
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_PROGRESS_MAIN", entity);
		msg.addCmd(cmd);
		
		PDA_MessageBus msgBus = new PDA_MessageBus(helper);
		msgBus.create(msg);
	}
	
	public static String check(TOTAL_PUSH_INFO entity){
		StringBuilder strBuilder =new StringBuilder();
		if(StringHelper.isNullOrEmpty(entity.getTitle()))
			PublicBus.addStr(strBuilder, "-标题 不能为空");
		if(entity.getFinishdate()==null)
			PublicBus.addStr(strBuilder, "-完成时间不能为空");
		return strBuilder.toString();
	    }
    }