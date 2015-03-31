package com.yizw.newhouselevy.Business;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.CmdCode;
import com.yizw.newhouselevy.Entity.PDA_Login;
import com.cogent.core.component.DataTableList;
import com.cogent.core.msg.*;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.LogHelper;
import com.cogent.core.util.MD5;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.cogent.core.util.SystemInfo;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class LoginBus {

  
	/** 登录信息缓存对象*/
	private static PDA_Login LOGIN = null;
	public static PDA_Login getLogin(Context context){
		if(LOGIN == null){
			LogHelper.writeToLogFlie("登录信息丢失，从数据库中重新获取");
			DatabaseHelper helper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
			LOGIN = getLocalLogin(helper);
		}
		return LOGIN;
	}
	public static PDA_Login getLogin(DatabaseHelper helper){
		if(LOGIN == null){
			LogHelper.writeToLogFlie("登录信息丢失，从数据库中重新获取");
			LOGIN = getLocalLogin(helper);
		}
		return LOGIN;
	}

	
	private static String USN = "";
	public static String getUSN(Context context) throws MyException {
		//如果USN为空，则进行在线登录并获取该值，在线登录失败则抛出异常
		if(StringHelper.isNullOrEmpty(USN)){
			String str = loginOnline(getLogin(context).getUSERNAME(),getLogin(context).getPASSWORD(),context);
			if(str.equals(""))
				return USN;
			else{
				MyException myE = new MyException("登录服务端失败，"+str, str);
				throw myE;
			}
		}else{
			return USN;
		}
	}
	
	public static void clearUSN(){
		USN = "";
	}

	/** 登录，成功返回"",否则返回失败原因*/
    public static String login(String username, String password,DatabaseHelper helper,Context context){   	
    	
    	String returnStr = "";
    	
    	PDA_Login login = getLocalLogin(helper);
    	if(login!=null){
    		if(!username.equals(login.getUSERNAME()))
    			return "只能使用“"+login.getUSERNAME()+"”登录";
    	}
    	
    	//将密码进行MD5加密
    	MD5 md5 = new MD5();
    	password = md5.getMD5ofStr(password);
    	
    	returnStr = loginLocal(username,password,helper,context);
    	//如果本地登录失败，则进行在线登录
    	if(!returnStr.equals("")){
    		returnStr = loginOnline(username,password,context);
    		if(returnStr.equals(""))
    			updateToDB(helper,getLogin(context));
    	}
    		
		return returnStr;
    }
    
    /** 本地数据库中是否存在用户的登录信息*/
    public static int hasuser(DatabaseHelper helper) throws MyException{
    	try {
			Dao<PDA_Login, String> dao = helper.getDao(PDA_Login.class);
			int num = dao.queryForAll().size();
			if (num > 0)
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			throw new MyException("获取本地用户信息失败", e.getMessage(), e.getStackTrace());			
		}
    }
    
    /** 获取本地登录用户信息，不存在返回null*/
    public static PDA_Login getLocalLogin(DatabaseHelper helper){
    	try {
			Dao<PDA_Login, String> dao = helper.getDao(PDA_Login.class);
			List<PDA_Login> list = dao.queryForAll();
			if(list.size()>0)
				return list.get(0);
			else
				return null;
		} catch (SQLException e) {
			return null;			
		}
    }
    
    /** 更新登录信息到数据库</br>注：该处只有存在登录信息才更新，因为新安装时是不存在登录信息的，这个在同步数据时会用到*/
    private static void updateToDB(DatabaseHelper helper,PDA_Login login){
    	try {
			Dao<PDA_Login, String> dao = helper.getDao(PDA_Login.class);
			if(getLocalLogin(helper)!=null)
				dao.update(login);
		} catch (SQLException e) {
			MyException myE = new MyException("更新登录信息失败", e.getMessage(),e.getStackTrace());
			ExceptionHelper.Operate(myE, false, null);
		}
    }
    
    /** 本地数据库登录*/
    private static String loginLocal(String username, String password,DatabaseHelper helper,Context context){
    	String returnStr = "";
    	try {
			Dao<PDA_Login, String> dao = helper.getDao(PDA_Login.class);
			QueryBuilder<PDA_Login, String> queryBuilder = dao.queryBuilder();
			queryBuilder.where().eq("USERNAME", username).and().eq("PASSWORD", password);
			PreparedQuery<PDA_Login> preparedQuery = queryBuilder.prepare();
			List<PDA_Login> list = dao.query(preparedQuery);
			if(list.size()>0){
				LOGIN = list.get(0);//将登录信息缓存
				returnStr = "";
			}
			else
				returnStr = "用户名或密码错误";
		} catch (SQLException e) {
			returnStr = "本地登录异常";
			MyException myE = new MyException("本地登录异常", e.getMessage(), e.getStackTrace());
			ExceptionHelper.Operate(myE, false, context);
		}
		return returnStr;
    }
    
    /** 在线登录*/
    private static String loginOnline(String username, String password,Context context){
		String returnStr = "";
		try {
			UploadMsg msg = new UploadMsg();
			UploadCmd cmd = new UploadCmd(CmdCode.LOGIN);
			cmd.addParameter("username", username);
			cmd.addParameter("password", password);
			cmd.addParameter("machinecode", SystemInfo.getDeviceId(context));
			cmd.addParameter("version", SystemInfo.getVersionCode(context));
			msg.addCmd(cmd);

			MessageUploader messageUploader = new MessageUploader(context,new MsgConfig());
			RebackInfo rebackInfo = messageUploader.uploadMessage(false, msg);
			rebackInfo.throwException();	
			
			DataTableList dataTable = rebackInfo.getDataTableByDATA();
			
			PDA_Login login = new PDA_Login();
			MsgHelper.setValuesToEntity(login, dataTable);
			LOGIN = login;
			USN = login.getUSN();
		} catch (MyException e) {
			ExceptionHelper.Operate(e, false, context);
			returnStr = e.getShowMsg();
			if (returnStr == null)
				returnStr = "通信出错";
		}

		return returnStr;
	}
    
    public static void clearlogin(DatabaseHelper helper,String id) throws MyException{
    	try {
			Dao<PDA_Login, String> dao = helper.getDao(PDA_Login.class);
			dao.deleteById(id);
		} catch (SQLException e) {
			throw new MyException("删除用户信息失败", e.getMessage(), e.getStackTrace());			
		}
    }
}
