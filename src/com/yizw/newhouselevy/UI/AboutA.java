package com.yizw.newhouselevy.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cogent.core.util.SystemInfo;
import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;

/*关于软件*/
public class AboutA extends Activity {
	private Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		PublicBus.titleBarControl(this, "关于", true, null, false, null);
		
		initControl();
	}
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
		TextView txtvVersionCode =(TextView)findViewById(R.id.txt_VersionCode);
		txtvVersionCode.setText(" V"+SystemInfo.getVersionName(this));				
	}
	
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
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
				
			default:
				break;
			}
		}
		
		void Onclick_btn_title_right() {

		}		
	}
}
