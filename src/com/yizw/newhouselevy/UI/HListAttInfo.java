package com.yizw.newhouselevy.UI;

import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newhouselevy.R;
import com.yizw.newhouselevy.AsyncTask.HListAttInfoSearchAsyncTask;
import com.yizw.newhouselevy.AsyncTask.HListAttInfoSaveAsyncTask;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.Business.AttConfirmItemBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.AttConfirm;
import com.yizw.newhouselevy.Entity.AttConfirmItem;
import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;

/*算单 附件一  补充单价信息*/
public class HListAttInfo  extends OrmLiteBaseActivity<DatabaseHelper> {
	
	public class KEY{
		/** 附件id*/
		public static final String in_id = "in_id";
		/** 算单id*/
		public static final String listid = "listid";
		/** 附件att_id*/
		public static final String att_id = "att_id";
		/** 算单状态*/
		public static final String h_status ="h_status";
	}
	
	
	public HListAttInfo activity;
	
	public AttConfirmItem entity;
	
	public AttConfirm  att;
	
	public String in_id,listid,att_id,h_status;
	
	DatabaseHelper helper;
	boolean isAdd = true;
	
	private EditText edit_price;

	private TextView text_name,text_type,text_unit,text_num,text_total;
	
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
		setContentView(R.layout.hou_hlist_att_info);
		PublicBus.titleBarControl(this, "详细信息", true, null, true, null);
		initControl();
		operateIntent();
	}
	
	private void initControl() {
		Intent intent = this.getIntent();
		h_status = intent.getStringExtra(KEY.h_status);
		
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);

		edit_price = (EditText) findViewById(R.id.edit_price);
		
		text_name = (TextView) findViewById(R.id.text_name);
		text_type = (TextView) findViewById(R.id.text_type);
		text_unit = (TextView) findViewById(R.id.text_unit);
		text_num = (TextView) findViewById(R.id.text_num);
		text_total = (TextView) findViewById(R.id.text_total);
	}
		
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
		
		if(StringHelper.isNullOrEmpty(id)){
			isAdd = true;
			entity = new AttConfirmItem();	
			att = new AttConfirm();
			
		}else{
			isAdd = false;
			entity = new AttConfirmItem();
			
			if(!StringHelper.isNullOrEmpty(h_status)&&"2".equals(h_status)){
				View btn = findViewById(R.id.btn_title_right);
				btn.setVisibility(View.GONE);
			}
			
			HListAttInfoSearchAsyncTask task = new HListAttInfoSearchAsyncTask(activity);
			task.execute(id);
			
			
		}
	}
	public void setEntityToUI(AttConfirmItem entity,AttConfirm att) {
		text_name.setText(entity.getName());
		text_type.setText(entity.getType());
		text_unit.setText(entity.getUnit());
		text_num.setText(entity.getNum()+"");
		edit_price.setText(entity.getPrice()+"");
		text_total.setText(entity.getTotal()+"");
		
	}
	
	public AttConfirmItem getEntity(){
		if(isAdd){
			entity.setCreator(LoginBus.getLogin(this).getREALNAME());
			entity.setCreateddate(new Date());
		}else{
			entity.setModifier(LoginBus.getLogin(this).getREALNAME());
			entity.setModifydate(new Date());
		}
		
		entity.setModifier(LoginBus.getLogin(this).getREALNAME());
		entity.setModifydate(new Date());
		
		entity.setPrice(StringToDouble(edit_price.getText().toString()));
		
		entity.setListid(listid);
		
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
				String result = AttConfirmItemBus.check(entity);
				if(!result.equals("")){
					DialogHelper.showConfirm(activity, result);
					return;
				}
				
				HListAttInfoSaveAsyncTask task = new HListAttInfoSaveAsyncTask(activity);
				task.execute("");   
			} catch (Exception e) {
				MyException myE = new MyException("保存出错", e.getMessage(),e.getStackTrace());
				ExceptionHelper.Operate(myE, true, activity);
			}
		}
	}
	
}
