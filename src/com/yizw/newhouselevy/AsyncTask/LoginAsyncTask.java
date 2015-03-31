package com.yizw.newhouselevy.AsyncTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cogent.core.util.DialogHelper;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.SysConfigBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.UI.MainIndexA;

public class LoginAsyncTask extends AsyncTask<String, String, String>{
	private Activity activity;
	private String username;
	private String password;
	private DatabaseHelper helper;
	
	AlertDialog statusDialog =null;//状态提示
	
	public LoginAsyncTask(Activity activity,String username,String password,DatabaseHelper helper){
		this.activity = activity;
		this.username = username;
		this.password = password;
		this.helper = helper;
	}
	
	//该方法运行在UI线程当中,主要用于进行异步操作之前的UI准备工作
	@Override
	protected void onPreExecute() {
		statusDialog = DialogHelper.getStatusDialog(activity,null);
	}
	
	//该方法并不运行在UI线程当中，所以在该方法当中，不能对UI当中的控件进行设置和修改
	//主要用于进行异步操作。
	@Override
	protected String doInBackground(String... params) {
//		return "";
		return LoginBus.login(username, password, helper, activity);
	}
	
	//在doInBackground方法当中，每次调用publishProgress()方法之后，都会触发该方法
	//用于在异步任务执行的过程当中，对用户进行提示，例如控制进度条等
	@Override
	protected void onProgressUpdate(String... values) {
	}
	
	//在doInBackground方法执行结束之后再运行，并且运行在UI线程当中。
	//主要用于将异步任务执行的结果展示给客户
	@Override
	protected void onPostExecute(String result) {
		//关掉状态提示
		if(statusDialog!=null)
			statusDialog.dismiss();
		
		if(result.equals("")){
			SysConfigBus.setUserName(activity, username);
			activity.startActivity(new Intent(activity, MainIndexA.class));
			activity.finish();
		}else{
			Toast.makeText(activity, "登录失败,"+result, Toast.LENGTH_LONG).show();
			
//          测试期间，不用输入用户名、密码，直接点击登录进入软件主界面，测试结束后，取消上一行代码注释并删除以下两行代码即可
//			activity.startActivity(new Intent(activity, MainA.class));
//			activity.finish();
			
		}
	}
}
