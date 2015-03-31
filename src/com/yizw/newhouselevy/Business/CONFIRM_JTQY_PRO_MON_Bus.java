package com.yizw.newhouselevy.Business;

import java.sql.SQLException;

import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HouseConfirm;


	/**集体企业-产权调换-货币补偿-调查确认表*/
	public class CONFIRM_JTQY_PRO_MON_Bus {

	  DatabaseHelper helper;
					
	public CONFIRM_JTQY_PRO_MON_Bus(DatabaseHelper databaseHelper){
	   this.helper = databaseHelper;
		}
					
	public void create(HouseConfirm entity) throws MyException, SQLException{
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_CONFIRM_JTGR_PRO_MON", entity);
		msg.addCmd(cmd);
						
		PDA_MessageBus msgBus = new PDA_MessageBus(helper);
		msgBus.create(msg);
		}

	public static String check(HouseConfirm entity){
		StringBuilder strBuilder =new StringBuilder();
						
		if(StringHelper.isNullOrEmpty(entity.getX4()))
			PublicBus.addStr(strBuilder,"-房屋座落不能为空");			
		if(StringHelper.isNullOrEmpty(entity.getX1()))
			PublicBus.addStr(strBuilder,"-房屋所有权人不能为空");			
		if(StringHelper.isNullOrEmpty(entity.getX218()))
			PublicBus.addStr(strBuilder,"-主调查人不能为空");		
		return strBuilder.toString();
					}		
					
				}
