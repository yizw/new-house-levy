package com.yizw.newhouselevy.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.LoginAsyncTask;
import com.yizw.newhouselevy.Business.SysConfigBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;


public class LoginA extends OrmLiteBaseActivity<DatabaseHelper> {	
	private Activity activity;
	
	EditText edit_user;
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login); 
		initControl();
	}
	
	private void initControl(){
		activity = this;
		
		edit_user = (EditText) findViewById(R.id.edit_user);
		edit_user.setText(SysConfigBus.getUserName(this));
		initBtn(R.id.btn_login);
	}
	
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	class OnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.btn_login:
				Onclick_btn_login();
				break;
			}
			
		}
		
		void Onclick_btn_login(){
			EditText edit_pwd = (EditText) findViewById(R.id.edit_pwd);
			String user = edit_user.getText().toString();
			String pwd = edit_pwd.getText().toString();
			
			if(StringHelper.isNullOrEmpty(user)||StringHelper.isNullOrEmpty(pwd)){
				Toast.makeText(LoginA.this, "账号或密码不能为空", Toast.LENGTH_LONG).show();
				return;
			}

			LoginAsyncTask loginAsyncTask = new LoginAsyncTask(activity, user, pwd, getHelper());
			loginAsyncTask.execute("");
		}		
	}
	
	private static final int menu_setting = 1;
	private static final int menu_about = 2;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, menu_setting, 1, "设置").setIcon(R.drawable.ic_menu_preferences);
		menu.add(0, menu_about, 2, "关于").setIcon(R.drawable.ic_menu_info_details);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		Intent intent = new Intent();
		switch(item.getItemId()){
		case menu_setting:
			intent.setClass(this, SysConfigA.class);
			startActivity(intent);
			break;
		case menu_about:
			intent.setClass(this, AboutA.class);
			startActivity(intent);
			break;
		}
		
		return false;
	}

}