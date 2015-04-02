package com.yizw.newhouselevy.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.cogent.core.util.DateHelper;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.DataBaseInfoSearchAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.DATA_BASE;

/*资料库信息页面*/
public class DataBaseInfoA extends OrmLiteBaseActivity<DatabaseHelper>{
	public class KEY{	
		public static final String in_id = "in_id";
	}
	
	public DataBaseInfoA activity;
	
	private WebView webView;
	
	
	public DATA_BASE entity;
	
	private TextView createddate,creator,title;
	
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
		setContentView(R.layout.database_info);
		PublicBus.titleBarControl(this, "信息详情", true, null, true, null);
		initControl();
		operateIntent();
	}
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
		title = (TextView) findViewById(R.id.title);
		
		webView = (WebView) findViewById(R.id.content);
		 
		createddate = (TextView) findViewById(R.id.createddate);
		creator = (TextView) findViewById(R.id.creator);
	}
	
	
	
	
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
		in_id = id;
		if(StringHelper.isNullOrEmpty(id)){
			isAdd = true;
			entity = new DATA_BASE();			
		}else{
			isAdd = false;
			entity = new DATA_BASE(); 
			DataBaseInfoSearchAsyncTask task = new DataBaseInfoSearchAsyncTask(activity);
			task.execute(id);		
			
			//屏蔽保存 不能修改
			Button btn = (Button)findViewById(R.id.btn_title_right);
			btn.setVisibility(View.INVISIBLE);
		}
		
	}
	
	Button initBtn(int id) {
		Button btn = (Button) findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	public void setEntityToUI(DATA_BASE entity){
		title.setText(entity.getTitle());

	 	webView.loadDataWithBaseURL(null, "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><body>"+entity.getContent()+"</body></html>", "text/html", "utf-8", null);
		
	 	creator.setText(entity.getCreator());
		createddate.setText(DateHelper.dateToString(entity.getCreateddate(), DateHelper.formatTwo)); 
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
