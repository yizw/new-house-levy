package com.yizw.newhouselevy.UI;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.yizw.newhouselevy.Business.PDA_MessageBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PDA_Message;

/** 消息处理模版*/
public class MsgMainA extends OrmLiteBaseActivity<DatabaseHelper>{
	public Activity activity;
	public Object entity;
	boolean isAdd = true;
	
	final int requestCode_one = 1;
	final int requestCode_two = 2;
	
	TextView txt_;
	Button btn_;
	EditText edit_;
	Spinner sp_;
	
	
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.msg_main);
		
        PublicBus.titleBarControl(this, "消息处理", true, null, false, null);
        initControl();
    }
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		initBtn(R.id.btn_first);
	}
	
	Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	Spinner initSp(int id,String enmucode,boolean allowEmptyRow) throws MyException{
		Spinner sp = (Spinner)findViewById(id);
//		PublicBus.spinnerEnumBinding(sp,enmucode,allowEmptyRow,getHelper(),this);
		return sp;
	}
	
	public void setEntityToUI(Object entity){
		
	}
	
	public Object getEntity(){
		return entity;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		case requestCode_one:
			requestCode_one(resultCode, data);
			break;
		case requestCode_two:
			requestCode_two(resultCode, data);
			break;

		default:
			break;
		}
	}
	
	private void requestCode_one(int resultCode, Intent data){
		if(resultCode!=RESULT_OK)
			return;
	}
	
	private void requestCode_two(int resultCode, Intent data){
		if(resultCode!=RESULT_OK)
			return;
	}
	
	class OnClick implements View.OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.btn_title_left:
				activity.finish();
				break;
			case R.id.btn_title_right:
				Onclick_btn_title_right();
				break;				
			case R.id.btn_first:
				Onclick_btn_first();
				break;
				
			default:
				break;
			}
		}
		
		private void Onclick_btn_first() {
			try {
				PDA_MessageBus msgbus = new PDA_MessageBus(getHelper());
				PDA_Message msg = msgbus.getUploadMsg();
				if(msg==null){
					Toast.makeText(activity, "没有待上传的消息", Toast.LENGTH_SHORT).show();
				}else{
					Intent intent = new Intent(activity, MsgFirstInfoA.class);
					intent.putExtra(MsgFirstInfoA.KEY.in_id, msg.getId());
					activity.startActivity(intent);
				}					
			} catch (SQLException e) {
				MyException myE = new MyException("运行出错了", e.getMessage(),e.getStackTrace());
				ExceptionHelper.Operate(myE, true, activity);
			}
		}

		void Onclick_btn_title_right() {
			
		}
	}
}
