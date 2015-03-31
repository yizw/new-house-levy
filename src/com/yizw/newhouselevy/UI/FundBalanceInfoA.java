package com.yizw.newhouselevy.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.FundBalanceInfoSearchAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.FUND_BALANCE;


/*资金余额信息展示页面*/
public class FundBalanceInfoA extends OrmLiteBaseActivity<DatabaseHelper>{

	public class KEY{	
		public static final String in_id = "in_id";
		public static final String pid = "pid";
	}
	
	public FundBalanceInfoA activity;
	
	public FUND_BALANCE entity;
	
	private TextView budget_fund,all_fund,fund_balance;
	
	public String in_id,pid;
	
	
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
		setContentView(R.layout.fund_balance_info);
		PublicBus.titleBarControl(this, "资金余额", true, null, false, "");
		initControl();
		operateIntent();
	}
	
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
		budget_fund = (TextView) findViewById(R.id.budget_fund);
		all_fund = (TextView) findViewById(R.id.all_fund);
		fund_balance = (TextView) findViewById(R.id.fund_balance);
	}
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
		in_id = id;
		if(!StringHelper.isNullOrEmpty(id)){
			
			entity = new FUND_BALANCE(); 
			FundBalanceInfoSearchAsyncTask task = new FundBalanceInfoSearchAsyncTask(activity);
			task.execute(id);		
			
		}
		
	}
	
	
	Button initBtn(int id) {
		Button btn = (Button) findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	public void setEntityToUI(FUND_BALANCE entity){
		budget_fund.setText(entity.getAllmoney());
		all_fund.setText(entity.getInmoney());		
		fund_balance.setText(entity.getRemain());
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
