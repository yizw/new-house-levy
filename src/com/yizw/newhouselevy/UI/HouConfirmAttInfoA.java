package com.yizw.newhouselevy.UI;

import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.HouConfirmAttInfoSaveAsyncTask;
import com.yizw.newhouselevy.AsyncTask.HouConfirmAttInfoSearchAsyncTask;
import com.yizw.newhouselevy.Business.AttConfirmBus;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.AttConfirm;

/*分户房屋调查确认表-附件1  信息明细页面*/
public class HouConfirmAttInfoA  extends OrmLiteBaseActivity<DatabaseHelper> {
	
	public class KEY{	
		public static final String in_id = "in_id";
		
		public static final String cid = "cid";
		
		/*项目  ID*/
		public static final String pid = "pid";
		/*分户id*/
		public static final String hid = "hid";
		/*附件类型  temptype*/
		public static final String temptype = "temptype";
		/*确认表状态 */
		public static final String c_status = "c_status";
		
        /*附件list  ID*/
		public static final String attconfirmid = "attconfirmid";
		
	}
	
	
	public HouConfirmAttInfoA activity;
	
	public String hid,pid,in_id,temptype,c_status;
	
	public AttConfirm entity;
	
	DatabaseHelper helper;
	boolean isAdd = true;
	
	private EditText edit_name,edit_type,edit_unit,edit_num,edit_remark;

	
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
		setContentView(R.layout.hou_confirm_att_info);
		PublicBus.titleBarControl(this, "详细信息", true, null, true, null);
		initControl();
		operateIntent();
	}
	
	private void initControl() {
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);

		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_type = (EditText) findViewById(R.id.edit_type);
		edit_unit = (EditText) findViewById(R.id.edit_unit);
		edit_num = (EditText) findViewById(R.id.edit_num);
		edit_remark = (EditText) findViewById(R.id.edit_remark);
		
	}
		
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
		
		 hid = intent.getStringExtra(KEY.hid);
		 pid = intent.getStringExtra(KEY.pid);
		 temptype = intent.getStringExtra(KEY.temptype);
	    String c_status = intent.getStringExtra(KEY.c_status);
		
	//	entity = new AttConfirm();
		
		
		if(StringHelper.isNullOrEmpty(id)){
			isAdd = true;
			entity = new AttConfirm();	 
						
		}else{
			isAdd = false;
			entity = new AttConfirm();
			
			if(!StringHelper.isNullOrEmpty(c_status) &&"2".equals(c_status)){
				View btn = findViewById(R.id.btn_title_right);
				btn.setVisibility(View.GONE);
			}
			
			HouConfirmAttInfoSearchAsyncTask task = new HouConfirmAttInfoSearchAsyncTask(activity);
			task.execute(id);
		}
	}
	public void setEntityToUI(AttConfirm entity) {
		edit_name.setText(entity.getName());
		edit_type.setText(entity.getType());
		edit_unit.setText(entity.getUnit());
		edit_num.setText(entity.getNum()+"");
		edit_remark.setText(entity.getRemark());
	
	}
	
	public AttConfirm getEntity(){
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
		
		entity.setName(edit_name.getText().toString());
		entity.setType(edit_type.getText().toString());
		entity.setUnit(edit_unit.getText().toString());
		entity.setNum(StringToDouble(edit_num.getText().toString()));
		entity.setRemark(edit_remark.getText().toString());
		
		entity.setHid(hid);
		entity.setPid(pid);
		entity.setTemptype(temptype);
		
		return entity;
	}

	private Double StringToDouble(String text){
		if(StringHelper.isNullOrEmpty(text)){
			return Double.parseDouble("0.00");
		}else{
			return Double.parseDouble(text);
		}
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
			default:
				break;
			}
		}
		
		void Onclick_btn_title_right(){
			try {
				getEntity();
				String result = AttConfirmBus.check(entity);
				if(!result.equals("")){
					DialogHelper.showConfirm(activity, result);
					return;
				}
				 
				HouConfirmAttInfoSaveAsyncTask task = new HouConfirmAttInfoSaveAsyncTask(activity);
				task.execute("");   
			} catch (Exception e) {
				MyException myE = new MyException("保存出错", e.getMessage(),e.getStackTrace());
				ExceptionHelper.Operate(myE, true, activity);
			}
		}
	}
	
}
