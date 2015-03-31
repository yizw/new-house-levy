package com.yizw.newhouselevy.UI;

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

/*分户流程控制信息页面*/
public class TimeControlHouInfoA extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public class KEY{	
		public static final String in_id = "in_id";
		/*项目类型*/
		public static final String householdid = "householdid";	
		
	}	
	
	public TimeControlHouInfoA activity;
	
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
		setContentView(R.layout.time_control_houinfo);
		PublicBus.titleBarControl(this, "信息详情", true, null, false, null);
		initControl();
		operateIntent();
	}
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
		
	//	content = (TextView) findViewById(R.id.content);
		webView = (WebView) findViewById(R.id.content);
		
		
		webView.getSettings().setJavaScriptEnabled(true);  
	        //加载需要显示的网页      webview.loadUrl("http://www.51cto.com/");  
	        //设置Web视图  
		Intent intent = this.getIntent();
 		String pid = intent.getStringExtra(KEY.in_id);
 		String householdid = intent.getStringExtra(KEY.householdid);
 		String usn="";
		try {
			usn = new MsgConfig().getUSN(activity);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//base/household/household_time_list.do?pid=e0f25287-6c72-4040-ad29-d254f2183d6d&householdid=f694e9f9-ef99-47ab-8fff-3df767a20199
		String url="/base/pda/household_time_list.do?usn="+usn+"&pid="+pid+"&householdid="+householdid;
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
//		Intent intent = this.getIntent();
//		String id = intent.getStringExtra(KEY.in_id);
//	//	setEntityToUI(entity);
//		in_id = id;
//		if(StringHelper.isNullOrEmpty(id)){
//			isAdd = true;
//			entity = new Time();			
//		}else{
//			isAdd = false;
//			entity = new Time(); 
//			
//			TimeInfoSearchAsyncTask task = new TimeInfoSearchAsyncTask(activity);
//			task.execute(id);		
//			
//			//屏蔽保存 不能修改
//			Button btn = (Button)findViewById(R.id.btn_title_right);
//			btn.setVisibility(View.INVISIBLE);
//		}
		
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
