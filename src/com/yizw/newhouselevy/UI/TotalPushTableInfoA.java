package com.yizw.newhouselevy.UI;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.TotalPushTableInfoAsyncTask;
import com.yizw.newhouselevy.AsyncTask.TotalPushTableInfoSaveAsyncTask;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.Business.TOTAL_PUSH_INFO_Bus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.EnumCode;
import com.yizw.newhouselevy.Entity.TOTAL_PUSH_INFO;

/*
 * 总推进表每一个item内容信息填写页面
*/
public class TotalPushTableInfoA extends OrmLiteBaseActivity<DatabaseHelper> {
	public class KEY{	
		public static final String in_id = "in_id";
		public static final String pid = "pid";
		
		//从项目总览入口进入，只做查看，将保存按钮隐藏
		public static final String hide = "hide";
	}
	
	public TotalPushTableInfoA activity;
	public TOTAL_PUSH_INFO entity;
	DatabaseHelper helper;
	boolean isAdd = true;
	
	public String hide;
	
	private EditText edit_title,edit_content;
	private Button btn_finishdate;
	private Spinner sp_status;
	
	@Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }	
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.total_push_table_info);
		PublicBus.titleBarControl(this, "详细信息", true, null, true, null);
		initControl();
		operateIntent();
	}
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
		try {
			edit_title = (EditText) findViewById(R.id.edit_title);
			edit_content = (EditText) findViewById(R.id.edit_content);
			btn_finishdate = initBtn(R.id.btn_finishdate);
			sp_status = initSp(R.id.sp_status, EnumCode.TOTAL_PUSH_INFO.status, false);
		} catch (MyException e) {
			ExceptionHelper.Operate(e, true, this);
		}
	}
		
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	private Spinner initSp(int id,String enmucode,boolean allowEmptyRow) throws MyException{
		Spinner sp = (Spinner)findViewById(id);
		PublicBus.spinnerEnumBinding(sp,enmucode,allowEmptyRow,getHelper(),this);
		return sp;
	}
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
		
		String hide = intent.getStringExtra(KEY.hide);
		
		if(StringHelper.isNullOrEmpty(id)){
			isAdd = true;
			entity = new TOTAL_PUSH_INFO();					
		}else{
			isAdd = false;
			entity = new TOTAL_PUSH_INFO();
			
			if(!StringHelper.isNullOrEmpty(hide) && "1".equals(hide)){
				Button btn = (Button)findViewById(R.id.btn_title_right);
				btn.setVisibility(View.GONE);
			}
			
			TotalPushTableInfoAsyncTask task = new TotalPushTableInfoAsyncTask(activity);
			task.execute(id);
		}
	}
	public void setEntityToUI(TOTAL_PUSH_INFO entity) throws MyException{
		edit_title.setText(entity.getTitle());
		edit_content.setText(entity.getContent());
		PublicBus.btnSetDate(activity, btn_finishdate, entity.getFinishdate());
		PublicBus.spinnerSetValue(sp_status, entity.getStatus());
		
	}
	
	public TOTAL_PUSH_INFO getEntity(){
		if(isAdd){
			entity.setCreator(LoginBus.getLogin(this).getREALNAME());
			entity.setCreateddate(new Date());
		}else{
			entity.setModifier(LoginBus.getLogin(this).getREALNAME());
			entity.setModifydate(new Date());
		}
		
	//	entity.setSTATUS("0");
		entity.setModifier(LoginBus.getLogin(this).getREALNAME());
		entity.setModifydate(new Date());
		
		entity.setTitle(edit_title.getText().toString());
		entity.setContent(edit_content.getText().toString());
		entity.setFinishdate(PublicBus.btnGetDate(activity, btn_finishdate));
		entity.setStatus(PublicBus.spinnerGetValue(sp_status));
		return entity;
	}

	
	class OnClick implements OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.btn_title_left:
				activity.finish();
				break;
			case R.id.btn_title_right:
				Onclick_btn_title_right();
				break;
			case R.id.btn_finishdate:
				PublicBus.setDate(activity, btn_finishdate);
				break;
			default:
				break;
			}
		}
		
		void Onclick_btn_title_right(){
			try {
				getEntity();
				String result = TOTAL_PUSH_INFO_Bus.check(entity);
				if(!result.equals("")){
					DialogHelper.showConfirm(activity, result);
					return;
				}
				
				TotalPushTableInfoSaveAsyncTask task = new TotalPushTableInfoSaveAsyncTask(activity);
				task.execute("");   
			} catch (Exception e) {
				MyException myE = new MyException("保存出错", e.getMessage(),e.getStackTrace());
				ExceptionHelper.Operate(myE, true, activity);
			}
		}
	}
	
}
