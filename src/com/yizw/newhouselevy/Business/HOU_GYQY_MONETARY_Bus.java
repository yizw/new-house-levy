package com.yizw.newhouselevy.Business;

import java.sql.SQLException;

import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HouseList;


   /**国有企业-货币补偿补偿计算单*/
   public class HOU_GYQY_MONETARY_Bus {

		DatabaseHelper helper;
		
		public HOU_GYQY_MONETARY_Bus(DatabaseHelper databaseHelper){
			this.helper = databaseHelper;
		}
		
		public void create(HouseList entity) throws MyException, SQLException{
			UploadMsg msg = new UploadMsg();
			UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_HOU_GYQY_MONETARY_COPTA", entity);
			msg.addCmd(cmd);
			
			PDA_MessageBus msgBus = new PDA_MessageBus(helper);
			msgBus.create(msg);
		}

		public static String check(HouseList entity){
			StringBuilder strBuilder =new StringBuilder();
					
//			if(StringHelper.isNullOrEmpty(entity.getEdit_cardno_x2()))
//				PublicBus.addStr(strBuilder,"-被征收人证件号不能为空");		
			return strBuilder.toString();
		}
		
		
	}

	
	
	