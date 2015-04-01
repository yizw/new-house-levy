package com.yizw.newhouselevy.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.PowerManagerWakeLock;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseTabActivity;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.AsyncTask.MainIndexUpdateAsyncTask;
import com.yizw.newhouselevy.AsyncTask.MainUserInfoSaveAsyncTask;
import com.yizw.newhouselevy.AsyncTask.MainUserInfoSearchAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.Business.SysConfigBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PDA_Login;

public class MainIndexA extends OrmLiteBaseTabActivity<DatabaseHelper>{
	public class KEY{	
		public static final String in_id = "in_id";
	}
	
	public String in_id;
	private MainIndexA activity; 
	public PDA_Login entity;
	
    private TextView name;
    private EditText edit_realname,edit_linktel,edit_address,edit_password,edit_password2;
	
	public boolean autoUpdate = false;
	public boolean autoSync = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_index);
		InitTabHost();
		initControl();
		operateIntent();
		updateSync();
	}
	
	/** 初始化选项卡*/
	@SuppressWarnings("deprecation")
	private void InitTabHost() {
		/*TabHost tabHost = this.getTabHost();
		LayoutInflater inflater = LayoutInflater.from(this);
		tabHost.setBackgroundResource(R.color.transparent);*/
		
		final TabHost tabHost = this.getTabHost();
		LayoutInflater inflater = LayoutInflater.from(this);
		tabHost.setBackgroundResource(R.color.transparent);
		
		//首页
		inflater.inflate(R.layout.main_index1, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getTabItemView("", R.drawable.index1,true)).setContent(R.id.index_tab1));
	//	tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_item_bg);
		tabHost.getTabWidget().getChildAt(0).setSelected(true);	
		
		//个人
		inflater.inflate(R.layout.main_index3, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getTabItemView("", R.drawable.index_2,false)).setContent(R.id.index_tab3));
		
		//设置
		inflater.inflate(R.layout.main1, tabHost.getTabContentView());
		Intent intent = new Intent(this,SysConfigA.class);
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getTabItemView("", R.drawable.index_3,false)).setContent(intent));
		
		//关于
		inflater.inflate(R.layout.main_index2, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(getTabItemView("", R.drawable.index_4,false)).setContent(R.id.index_tab2));
	
	
	    tabHost.setOnTabChangedListener(new OnTabChangeListener() {
		public void onTabChanged(String tabId) {
		int[] hots = new int[] { R.drawable.index1,R.drawable.index2, R.drawable.index3,R.drawable.index4 };
		int[] hots2 = new int[] { R.drawable.index_1, R.drawable.index_2,R.drawable.index_3, R.drawable.index_4 };
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
		  View vvv = tabHost.getTabWidget().getChildAt(i);
		  if (tabHost.getCurrentTab() == i) {
		  TextView textView = (TextView) vvv.findViewById(R.id.txt);
		  textView.setTextColor(vvv.getResources().getColor(R.color.bluetwo));
		  ImageView imgView = (ImageView) vvv.findViewById(R.id.img);
		  imgView.setBackgroundResource(hots[i]);
		  } else {
		  TextView textView = (TextView) vvv.findViewById(R.id.txt);
		  textView.setTextColor(vvv.getResources().getColor(R.color.heihtgray));
		  ImageView imgView = (ImageView) vvv.findViewById(R.id.img);
		  imgView.setBackgroundResource(hots2[i]);
			}
		  }
	    }
	  });
    }
	
	
	private View getTabItemView(String txt, int img, boolean isselect) {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.ty_tab_item, null);
		
		TextView textView = (TextView) view.findViewById(R.id.txt);
		textView.setText(txt);
		if (isselect) {
			textView.setTextColor((this.getResources().getColor(R.color.bluetwo)));
		} else {
			textView.setTextColor((this.getResources().getColor(R.color.heihtgray)));
		}
		textView.setTextSize(18);
		textView.setVisibility(View.GONE);
		
		ImageView imgView = (ImageView) view.findViewById(R.id.img);
		imgView.setBackgroundResource(img);
		return view;
	}
	
	
	private void initControl(){
		activity = this;
		initLayout(R.id.btn_1);
		initLayout(R.id.btn_2);
		initLayout(R.id.btn_3);
		initLayout(R.id.btn_4);
		initLayout(R.id.btn_5);
		initLayout(R.id.btn_6);
		initLayout(R.id.btn_7);	
		initLayout(R.id.btn_8);
		
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
//		initLayout(R.id.btn_update);
//		initLayout(R.id.btn_sync);
		
		name =(TextView) findViewById(R.id.name);
		
		edit_realname = (EditText) findViewById(R.id.edit_realname);
		edit_linktel = (EditText) findViewById(R.id.edit_linktel);
		edit_address = (EditText) findViewById(R.id.edit_address);
		edit_password = (EditText) findViewById(R.id.edit_password);
		edit_password2 = (EditText) findViewById(R.id.edit_password2);
		
	}
	
	private void initLayout(int id) {
		LinearLayout x1= (LinearLayout)findViewById(id);
		x1.setOnClickListener(new OnClick());
	}
	
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
	
		if(StringHelper.isNullOrEmpty(id)){
			entity = new PDA_Login();
			MainUserInfoSearchAsyncTask task = new MainUserInfoSearchAsyncTask(activity);
			task.execute(id);
		    setEntityToUI(entity);
		}
		
	}
	
	
    /** 自动升级和同步控制*/
	private void updateSync() {
		autoUpdate = SysConfigBus.getAutoUpdate(this);
        autoSync = SysConfigBus.getAutoSync(this);
        
        if(autoUpdate){//如果开启了自动升级，则进行升级检测，是否进行数据同步只在升级完成了进行，避免两者冲突
        	MainIndexUpdateAsyncTask update = new MainIndexUpdateAsyncTask(activity);
        	update.execute("");
        	
        	if(autoSync){
        		syncData();
        	}
        
        }else{//没启动自动升级，则进行是否自动同步检测
        	if(autoSync){
        		syncData();
        	}
        	
        }
	}

	/**  启动数据同步*/
	public void syncData(){
		Intent intent = new Intent();
		intent.setClass(MainIndexA.this, SyncDataA.class);
		startActivityForResult(intent, 0);
	}
	
	
   public void setEntityToUI(PDA_Login entity){
	 name.setText(entity.getUSERNAME());
     edit_realname.setText(entity.getREALNAME());
     edit_linktel.setText(entity.getLINKTEL());
     edit_address.setText(entity.getADDRESS());
    }
	
   
   public PDA_Login getEntity(){
	   
	   entity.setREALNAME(edit_realname.getText().toString());
	   entity.setLINKTEL(edit_linktel.getText().toString());
	   entity.setADDRESS(edit_address.getText().toString());
	   entity.setPASSWORD(edit_password.getText().toString());
	
	   	   
	   return entity;
   }
   
	
	@Override
	protected void onDestroy() {
		super.onDestroy();	

		GlobalVar.clearCache();
//		bus.stopAutoUpload(activity);
		
		PowerManagerWakeLock.release();
	}
	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK)
			DialogHelper.showCloseConfirm(this, "退出系统", "您确认要退出系统？");
		return super.dispatchKeyEvent(event);
	}
	
	class OnClick implements View.OnClickListener {

		public void onClick(View v) {
		//	Intent intent = new Intent();
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_title_left:
			   Onclick_btn_title_left();
			   break;
			 
			case R.id.btn_title_right:
			   Onclick_btn_title_right();
			   break;

			// 项目总览
			case R.id.btn_1:
				activity.startActivity(new Intent(activity,MainProjectListA.class));
				break;
			// 房源
			case R.id.btn_2:
			//	activity.startActivity(new Intent(activity,HouseNameListA.class));
				activity.startActivity(new Intent(activity,HouseProA.class));
				break;
			// 总推进
			case R.id.btn_3:
				activity.startActivity(new Intent(activity,TotalPushTableListA.class));
				break;
			// 资料库
			case R.id.btn_4:
				activity.startActivity(new Intent(activity, DataBaseListA.class));
				break;
			// 分户推进
			case R.id.btn_5:
				activity.startActivity(new Intent(activity,HouseholdProjectListA.class));
				break;
			// 资金余额
			case R.id.btn_6:
				activity.startActivity(new Intent(activity,FundBalanceListA.class));
				break;
			// 问题反馈
			case R.id.btn_7:
				activity.startActivity(new Intent(activity,ProProblemFeedbackListA.class));
				break;
			// 时限控制
			case R.id.btn_8:
				activity.startActivity(new Intent(activity,TimeControlA.class));
				break;	
				
				
			// 检测--版本更新
			case R.id.btn_update:
				autoSync = false;// 手工引发的版本检测，确认为最新版本后不进行自动同步操作
				MainIndexUpdateAsyncTask update = new MainIndexUpdateAsyncTask(activity);
				update.execute("");
				break;
			// 检测--同步数据
			case R.id.btn_sync:
				syncData();
				break;

			default:
				break;
			}
		}
		
		void Onclick_btn_title_left(){
			
		}
		
		
		void Onclick_btn_title_right(){
		
			try {
				getEntity();
				String result = checkPassWord(edit_password.getText().toString(),edit_password2.getText().toString());
				if(!result.equals("")){
					DialogHelper.showConfirm(activity, result);
					return;
				}
				new AlertDialog.Builder(activity)
				.setTitle("是否确认")
				.setMessage("修改密码请慎重，确认无误后保存")
				.setPositiveButton("确定", new OnClickListener() {				
					
				 public void onClick(DialogInterface dialog, int which) {
				   MainUserInfoSaveAsyncTask task = new MainUserInfoSaveAsyncTask(activity);
				   task.execute(""); 
 				 //    deleteDate();
                 //    activity.finish();
					}
				})
				.setNegativeButton("取消", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.create().show();
				
			}catch (Exception e) {
				MyException myE = new MyException("保存出错", e.getMessage(),e.getStackTrace());
				ExceptionHelper.Operate(myE, true, activity);
				 activity.finish();
			}
			
		}

		private String checkPassWord(String password, String password2) {
			StringBuilder strBuilder =new StringBuilder(); 
		
			if(StringHelper.isNullOrEmpty(password)){
				 
			}else{
				if(!password.equals(password2)){
			    PublicBus.addStr(strBuilder, "-新密码和确认密码不一致");		
				}else{
					if(password.length()<6||password.length()>18){
						 PublicBus.addStr(strBuilder, "-密码长度不能小于6或大于18");		
					}
				}
				
				 
			}
			
			return strBuilder.toString();
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
			autoSync = false;//手工引发的版本检测，确认为最新版本后不进行自动同步操作
			MainIndexUpdateAsyncTask update = new MainIndexUpdateAsyncTask(activity);
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

