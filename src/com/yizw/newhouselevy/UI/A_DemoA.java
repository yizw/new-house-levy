package com.yizw.newhouselevy.UI;

import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.cogent.core.util.MyException;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/** UI示例代码*/
public class A_DemoA extends OrmLiteBaseActivity<DatabaseHelper>{
	public class KEY{	
		/** 表示传入用到的key*/
		public static final String in_name = "in_name";
		/** 表示返回用到的key*/
		public static final String re_name = "re_name";
		
	}
	
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
        setContentView(R.layout.main);
		
        PublicBus.titleBarControl(this, getTitle().toString(), true, null, true, null);
        initControl();
        operateIntent();
    }
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
	}
	
	private void operateIntent(){
//		Intent intent = this.getIntent();
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
				
			default:
				break;
			}
		}
		
		void Onclick_btn_title_right() {
			
		}
	}
}
