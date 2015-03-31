package com.yizw.newhouselevy.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;

 public class MainProjectIndexA extends OrmLiteBaseActivity<DatabaseHelper>{
	 
	 public class KEY{	
			public static final String in_id = "in_id";
			public static final String pid = "pid";
		}
	 
	public String pid,in_id;

	public MainProjectIndexA activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_projectindex);
		PublicBus.titleBarControl(this, "项目详情", true, null, false, null);
		initControl();
	}

	private void initControl() {
		Intent intent = this.getIntent();
		pid = intent.getStringExtra(KEY.pid); 
		in_id = intent.getStringExtra(KEY.in_id); 
		
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
		initLayout(R.id.total_pushtable);
		initLayout(R.id.time_control);
		initLayout(R.id.fund_balance);
		initLayout(R.id.problem_feedback);
	 }
	

	private void initLayout(int id) {
		LinearLayout more = (LinearLayout) findViewById(id);
	//	more.getBackground().setAlpha(179);
		more.setOnClickListener(new OnClick());
	  }

	private Button initBtn(int id) {
		Button btn = (Button) findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
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
				
			// 项目详情--总推进表
			case R.id.total_pushtable:
				Onclick_btn_pushtable();
				break;

			// 项目详情--时限控制
			case R.id.time_control:
				Onclick_btn_timecontrol();
		   //   activity.startActivity(new Intent(activity,TimeControlA.class));
				break;

			// 项目详情--资金余额
			case R.id.fund_balance:
				Onclick_btn_fundbalance();
				break;

			// 项目详情--问题反馈
			case R.id.problem_feedback:
				Onclick_btn_feedback();
				break;

			default:
				break;
			}
		}

		void Onclick_btn_title_right() {

		}
		
		
		
		void Onclick_btn_pushtable() {
			Intent intent = new Intent(activity, TotalPushTableDetailA.class);
			intent.putExtra(TotalPushTableDetailA.KEY.in_id, in_id);
			intent.putExtra(TotalPushTableDetailA.KEY.hide, "1");
			activity.startActivity(intent);
		}

		void Onclick_btn_timecontrol() {
			
			Intent intent = new Intent(activity, TimeControlA.class);
		//	intent.putExtra(TimeControlA.KEY.in_id, in_id);
			activity.startActivity(intent);

		}

		void Onclick_btn_fundbalance() {
			Intent intent = new Intent(activity, FundBalanceInfoA.class);
			intent.putExtra(FundBalanceInfoA.KEY.in_id, in_id);
			activity.startActivity(intent);
		}
		
		void Onclick_btn_feedback() {
			Intent intent = new Intent(activity, ProQuestionListA.class);
			intent.putExtra(ProQuestionListA.KEY.in_id, in_id);
			intent.putExtra(ProQuestionListA.KEY.hide, "1");
			activity.startActivity(intent);
		}
		
	}
}

	
	