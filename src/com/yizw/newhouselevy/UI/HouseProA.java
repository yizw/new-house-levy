package com.yizw.newhouselevy.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;

public class HouseProA extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public class KEY{	
		public static final String in_id = "in_id";
		public static final String listid = "listid";
		public static final String index = "index";
	}	

	public String in_id,listid,index ;
	
	public HouseProA activity;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.housepro);
		PublicBus.titleBarControl(this, "房源管理", true, null, false, "");   
		initControl();
		operateIntent();
	}
    
    
	//屏蔽掉实体返回键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			return false;
		}
		return false; 
	}
    
	
	private void initControl(){
	//	Intent intent = this.getIntent();
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);

		// 房开商和征收项目  
		initBtn(R.id.btn_house);
		initBtn(R.id.btn_pro);
		
	}
	
	
	
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		in_id = intent.getStringExtra(KEY.in_id);
		listid=intent.getStringExtra(KEY.listid);
		index=intent.getStringExtra(KEY.index);
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
				
			case R.id.btn_house:
				activity.startActivity(new Intent(activity, HouseNameListA.class));
				break;
			case R.id.btn_pro:	
				activity.startActivity(new Intent(activity, HouseProListA.class));
				break;
			default:
				break;
			}
		}
		
		void Onclick_btn_title_right(){
			
	         }
	
    }

}

