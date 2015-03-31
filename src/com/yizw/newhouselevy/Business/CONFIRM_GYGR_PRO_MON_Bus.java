package com.yizw.newhouselevy.Business;

import java.sql.SQLException;

import android.content.Context;
import android.util.Log;

import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.MsgHelper;
import com.cogent.core.msg.RebackInfo;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.CONFIRM_PERSON;
import com.yizw.newhouselevy.Entity.HouseConfirm;

/**国有个人-产权调换-货币补偿-调查确认表*/
public class CONFIRM_GYGR_PRO_MON_Bus {
     DatabaseHelper helper;
     
	 public CONFIRM_GYGR_PRO_MON_Bus(DatabaseHelper databaseHelper){
				this.helper = databaseHelper;
	   }

	public void create(HouseConfirm entity) throws MyException, SQLException {
		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_PRO_HOUSE_CONFIRM",entity);
		msg.addCmd(cmd);

		PDA_MessageBus msgBus = new PDA_MessageBus(helper);
		msgBus.create(msg);
	}

	/** 在线保存户籍人员信息,保存成功后返回产生的id */
	public static String savePerson(Context context, CONFIRM_PERSON person)throws MyException {

		UploadMsg msg = new UploadMsg();
		UploadCmd cmd = MsgHelper.buildUploadCmd("SAVE_FAMILYREG", person);
		msg.addCmd(cmd);

		MessageUploader messageUploader = new MessageUploader(context,new MsgConfig());
		RebackInfo rebackInfo = messageUploader.uploadMessage(true, msg);
		Log.i(GlobalVar.TAG,msg.toString());
		rebackInfo.throwException();
		return rebackInfo.getParameter("id").getValue().toString();
	}

	public static String check(HouseConfirm entity) {
		StringBuilder strBuilder = new StringBuilder();
		if(StringHelper.isNullOrEmpty(entity.getX4()))
			PublicBus.addStr(strBuilder, "-房屋座落不能为空");
		if(StringHelper.isNullOrEmpty(entity.getX1()))
			PublicBus.addStr(strBuilder, "-房屋所有权人不能为空");
		if(StringHelper.isNullOrEmpty(entity.getX218()))
			PublicBus.addStr(strBuilder, "-主调查人不能为空");
		return strBuilder.toString();
	}

	public static String checkPerson(CONFIRM_PERSON person) {
		StringBuilder strBuilder = new StringBuilder();
		System.out.println(person.toString());
		if(StringHelper.isNullOrEmpty(person.getName()))
			PublicBus.addStr(strBuilder, "-姓名不能为空");
		if(StringHelper.isNullOrEmpty(person.getCardno()))
			PublicBus.addStr(strBuilder, "-身份证号码不能为空");
		return strBuilder.toString();
	}

}
