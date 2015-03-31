package com.yizw.newhouselevy.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;

    public class MoreA extends Activity {

	private Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		PublicBus.titleBarControl(this, "更多", true, null, false, null);
		initControl();
	}

	private void initControl() {
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);

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
		//	Intent intent = new Intent();
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_title_left:
				activity.finish();
				break;
			case R.id.btn_title_right:
				Onclick_btn_title_right();
				break;

			// 更多--时限控制
			case R.id.time_control:
		    activity.startActivity(new Intent(activity,TimeControlA.class));
				break;

			// 更多--资金余额
			case R.id.fund_balance:
		    activity.startActivity(new Intent(activity,FundBalanceListA.class));
				break;

			// 更多--问题反馈
			case R.id.problem_feedback:
		    activity.startActivity(new Intent(activity,ProProblemFeedbackListA.class));
				break;

			default:
				break;
			}
		}

		void Onclick_btn_title_right() {

		}
	}
}

	
	