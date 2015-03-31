package com.yizw.newhouselevy.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.PowerManagerWakeLock;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.AsyncTask.UpdateAsyncTask;
import com.yizw.newhouselevy.Business.MainBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.Business.SysConfigBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;

public class MainA extends OrmLiteBaseActivity<DatabaseHelper> {
	private MainA activity;
	
	public boolean autoUpdate = false;
	public boolean autoSync = false;
	
	private GridView gridView;
	private MainBus bus;
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		PowerManagerWakeLock.acquire(this);
		PublicBus.titleBarControl(this, "功能导航", true, "", false, "•••");
		initControl();
		updateSync();
//		bus.startAutoUpload(activity);
	}

	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		bus = new MainBus(activity);		
		
		gridView = (GridView) findViewById(R.id.gridView);
		gridView.setOnItemClickListener(new OnItemClick());

		bus.loadGridData(gridView);
	}
	
	Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	/** 自动升级和同步控制*/
	private void updateSync() {
		autoUpdate = SysConfigBus.getAutoUpdate(this);
        autoSync = SysConfigBus.getAutoSync(this);
        
        if(autoUpdate){//如果开启了自动升级，则进行升级检测，是否进行数据同步只在升级完成了进行，避免两者冲突
        	UpdateAsyncTask update = new UpdateAsyncTask(activity);
        	update.execute("");
        }else{//没启动自动升级，则进行是否自动同步检测
        	if(autoSync)
        		syncData();
        }
	}

	class OnItemClick implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			bus.startIntent(position);
		}

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK)
			DialogHelper.showCloseConfirm(this, "退出系统", "您确认要退出系统？");
		return super.dispatchKeyEvent(event);
	}
	
	/** 启动数据同步*/
	public void syncData(){
		Intent intent = new Intent();
		intent.setClass(MainA.this, SyncDataA.class);
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			bus.loadGridData(gridView);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();	

		GlobalVar.clearCache();
//		bus.stopAutoUpload(activity);
		
		PowerManagerWakeLock.release();
	}
	
	class OnClick implements View.OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.btn_title_left:
				DialogHelper.showCloseConfirm(activity, "退出系统", "您确认要退出系统？");
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

	
	private final int menu_update = 1;
	private final int menu_sync = 2;
	private final int menu_msg = 3;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, menu_update, 1,"版本更新").setIcon(R.drawable.ic_menu_upload);
		menu.add(0, menu_sync, 2,"同步数据").setIcon(R.drawable.ic_menu_refresh);
//		menu.add(0, menu_msg, 3,"消息处理").setIcon(R.drawable.ic_menu_start_conversation);
		
		return super.onCreateOptionsMenu(menu);
	}	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		super.onOptionsItemSelected(item);
		
		switch (item.getItemId()) {
		case menu_update://版本更新
			autoSync = false;//手工引发的版本检测，确认为新最新版本后不进行自动同步操作
			UpdateAsyncTask update = new UpdateAsyncTask(activity);
        	update.execute("");    
			break;
		case menu_sync://同步数据
			syncData();
			break;
		case menu_msg:
			startActivity(new Intent(this, MsgMainA.class));
			break;
		}

		return false;
	} 
}
