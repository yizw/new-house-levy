package com.yizw.newhouselevy.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.cogent.core.util.MyException;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.Business.SysConfigBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.Time;

/*时限控制信息页面
* author:yizw
*/
public class TimeControlInfoA extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public class KEY{	
		public static final String in_id = "in_id";
		/*项目类型*/
		public static final String project_type = "project_type";	
		
	}	
	
	public TimeControlInfoA activity;
	
	private WebView webView;
	
	
	public Time entity;
	
	public String in_id;
	
	boolean isAdd = false;
	
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
		setContentView(R.layout.time_control_info);
		PublicBus.titleBarControl(this, "信息详情", true, null, false, null);
		initControl();
		operateIntent();
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
		
	//	content = (TextView) findViewById(R.id.content);
		webView = (WebView) findViewById(R.id.content);
		
		
		webView.getSettings().setJavaScriptEnabled(true);  
	        //加载需要显示的网页      webview.loadUrl("http://www.google.com/");  
	        //设置Web视图  
		Intent intent = this.getIntent();
 		String pid = intent.getStringExtra(KEY.in_id);
 		String project_type = intent.getStringExtra(KEY.project_type);
 		String usn="";
		try {
			usn = new MsgConfig().getUSN(activity);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url="/base/pda/project_main_flow_list.do?usn="+usn+"&pid="+pid+"&landFlowType="+project_type;
		webView.loadUrl(SysConfigBus.getReportAddress(activity)+url); 
	 	
		webView.setWebViewClient(new TimeWebViewClient ()); 
	}
	
	private class TimeWebViewClient extends WebViewClient {  
        @Override 
        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
            view.loadUrl(url);  
            return true;  
        }  
    }  
	
	private void operateIntent(){
		
	}
	
	Button initBtn(int id) {
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

			default:
				break;
			}
		}
		
		
		void Onclick_btn_title_right() {

		}
		
		
	}
}