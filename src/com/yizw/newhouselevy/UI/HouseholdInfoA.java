package com.yizw.newhouselevy.UI;

import java.util.Date;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseTabActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.HouseholdInfoSaveAsyncTask;
import com.yizw.newhouselevy.AsyncTask.HouseholdInfoSearchAsyncTask;
import com.yizw.newhouselevy.Business.HOU_HOUSEHOLD_Bus;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.EnumCode;
import com.yizw.newhouselevy.Entity.HOU_HOUSEHOLD;

public class HouseholdInfoA extends OrmLiteBaseTabActivity<DatabaseHelper>{

	public class KEY{	
		/** 分户类型*/
		public static final String type = "in_type";
		public static final String in_id = "in_id";
		/*项目id*/
		public static final String pid = "pid";	
		
		
		/*分户房屋调查确认表id*/
		public static final String c_id = "c_id";
		/*分户房屋调查确认表状态值*/
		public static final String c_status = "c_status";
		
		public static final String hliststatus = "hliststatus";
		
		public static final String h_type = "h_type";	
		public static final String p_type = "p_type";	
	}	
	
	
	public String in_type,in_id,pid,c_id,c_status,hliststatus,p_type,h_type;
	
	public HouseholdInfoA activity;
	public HOU_HOUSEHOLD household;
	
	/** 是否是新增分户*/
	boolean isAdd = true;	
	/** 是否只读*/
	boolean isReader = false;
	
	private EditText edit_houseowner;
	//,edit_cartno,edit_linktel,edit_address,edit_remark
	private Spinner sp_householdtype;
	
	AlertDialog statusDialog = null;
	
	@SuppressWarnings("deprecation")
	public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    
    @SuppressWarnings("deprecation")
	public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.household_info);
		InitTabHost();
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
    
    
	/** 初始化选项卡*/
	@SuppressWarnings("deprecation")
	private void InitTabHost() {
		TabHost tabHost = this.getTabHost();
		LayoutInflater inflater = LayoutInflater.from(this);
		
		inflater.inflate(R.layout.household_info_tab1, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getTabItemView(inflater,0,"分户信息")).setContent(R.id.household_info_tab1));
		tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_item_bg);
		
		inflater.inflate(R.layout.household_info_tab2, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getTabItemView(inflater,0,"分户表单")).setContent(R.id.household_info_tab2));
		tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_item_bg);
	}
	
	@SuppressLint("InflateParams")
	private View getTabItemView(LayoutInflater inflater,int img,String txt){
		View view = inflater.inflate(R.layout.tab_item_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.img);
		imageView.setVisibility(View.GONE);
		
		TextView textView = (TextView) view.findViewById(R.id.txt);
		textView.setText(txt);
		textView.setTextSize(18);
		
		return view;
	}
	
	private void initControl(){
		Intent intent = this.getIntent();
		pid = intent.getStringExtra(KEY.pid); 
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
				
		try {
			edit_houseowner = (EditText) findViewById(R.id.edit_houseowner);
//			edit_cartno = (EditText) findViewById(R.id.edit_cartno);
//			edit_linktel = (EditText) findViewById(R.id.edit_linktel);
//			edit_address = (EditText) findViewById(R.id.edit_address);
//			edit_remark = (EditText) findViewById(R.id.edit_remark);
			sp_householdtype = initSp(R.id.sp_householdtype, EnumCode.HOU_HOUSEHOLD.householdtype, true);
			
			//tab2 快捷
			initBtn(R.id.btn_houseconfirm);
			initBtn(R.id.btn_coptsheet);
			initBtn(R.id.btn_housepushtable);	
			initBtn(R.id.btn_housefeedback);
			initBtn(R.id.btn_timecontrol);
		
		} catch (MyException e) {
			ExceptionHelper.Operate(e, true, this);
		}
	}
	
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	private Spinner initSp(int id,String enmucode,boolean allowEmptyRow) throws MyException{
		Spinner sp = (Spinner)findViewById(id);
		PublicBus.spinnerEnumBinding(sp,enmucode,allowEmptyRow,getHelper(),this);
		return sp;
	}
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
		in_id = id;
		if(StringHelper.isNullOrEmpty(id)){
			isAdd = true;
			household = new HOU_HOUSEHOLD();			
		}else{
			isAdd = false;
			
			edit_houseowner.setEnabled(false);
			sp_householdtype.setEnabled(false);
			
			household = new HOU_HOUSEHOLD(); 
			HouseholdInfoSearchAsyncTask task = new HouseholdInfoSearchAsyncTask(activity);
			task.execute(id);		
			
			//屏蔽保存 不能修改
			Button btn = (Button)findViewById(R.id.btn_title_right);
			btn.setVisibility(View.INVISIBLE);
		}
	}

	public void setEntityToUI(HOU_HOUSEHOLD entity){
		edit_houseowner.setText(entity.getHouseowner());
//		edit_cartno.setText(entity.getCartno());
//		edit_linktel.setText(entity.getLinktel());		
//		edit_address.setText(entity.getAddress());		
//		edit_remark.setText(entity.getRemark());		
		PublicBus.spinnerSetValue(sp_householdtype, entity.getHousehold_type());		
		
		if("2".equals(entity.getCstatus())){
			View btn = findViewById(R.id.coptsheet);
			btn.setVisibility(View.GONE);
		}
	}
	
	public HOU_HOUSEHOLD getEntity(){
		if(isAdd){
			household.setCreator(LoginBus.getLogin(this).getREALNAME());
			household.setCreatdate(new Date());
			household.setStatus("0");
		}
		
		household.setUsername(LoginBus.getLogin(this).getUSERNAME());
	//	entity.setSTATUS("0");
	
		household.setPid(pid);
		household.setModifier(LoginBus.getLogin(this).getREALNAME());
		household.setModifydate(new Date());
		
//		if(){
//			btn_select.setEnabled(false);
//		}
		
		household.setHouseowner(edit_houseowner.getText().toString());
//		household.setCartno(edit_cartno.getText().toString());
//		household.setLinktel(edit_linktel.getText().toString());			
//		household.setAddress(edit_address.getText().toString());
//		household.setRemark(edit_remark.getText().toString());	
		household.setHousehold_type(PublicBus.spinnerGetValue(sp_householdtype));
		return household;
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
			case R.id.btn_houseconfirm:				
				Onclick_btn_houseconfirm();					
				break;
			case R.id.btn_coptsheet:				
				Onclick_btn_coptsheet();					
				break;
			case R.id.btn_housepushtable:				
				Onclick_btn_housepushtable();					
				break;
			case R.id.btn_housefeedback:				
				Onclick_btn_housefeedback();					
				break;
			case R.id.btn_timecontrol:				
				Onclick_btn_timecontrol();					
				break;	
			default:
				break;
			}
		}
		
		void Onclick_btn_title_right(){
			try {
				
				getEntity();
				String result = HOU_HOUSEHOLD_Bus.check(household);
				if(!result.equals("")){
					DialogHelper.showConfirm(activity, result);
					return;
				}
				
			//	DialogHelper.showCloseConfirm(activity, "是否确认",);
				new AlertDialog.Builder(activity)
				.setTitle("是否确认")
				.setMessage("确定后无法修改，请确认无误后保存")
				.setPositiveButton("确定", new OnClickListener() {				
					
					public void onClick(DialogInterface dialog, int which) {
						HouseholdInfoSaveAsyncTask task = new HouseholdInfoSaveAsyncTask(activity);
						task.execute("");
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
			}
		}
	}
	
	void Onclick_btn_houseconfirm(){
		if(newactivity()){
			if("0".equals(household.getHousehold_type()) 
			&& "0".equals(household.getProject_type())){
				Intent intent = new Intent(activity, HouGYGRConfirmTable.class);
				intent.putExtra(HouGYGRConfirmTable.KEY.in_id, household.getId());
				intent.putExtra(HouGYGRConfirmTable.KEY.cid, household.getC_id());
				intent.putExtra(HouGYGRConfirmTable.KEY.pid, household.getPid());
				intent.putExtra(HouGYGRConfirmTable.KEY.hid, household.getId());
				intent.putExtra(HouGYGRConfirmTable.KEY.c_status, household.getC_status());
				activity.startActivityForResult(intent,1);
			}			
			else if("1".equals(household.getHousehold_type()) 
			&& "0".equals(household.getProject_type())){
				Intent intent = new Intent(activity, HouGYQYConfirmTable.class);
				intent.putExtra(HouGYQYConfirmTable.KEY.in_id, household.getId());
				intent.putExtra(HouGYQYConfirmTable.KEY.cid, household.getC_id());
				intent.putExtra(HouGYQYConfirmTable.KEY.pid, household.getPid());
				intent.putExtra(HouGYQYConfirmTable.KEY.hid, household.getId());
				activity.startActivityForResult(intent,1);
			}
			else if("0".equals(household.getHousehold_type()) 
			&& "1".equals(household.getProject_type())){
				Intent intent = new Intent(activity, HouJTGRConfirmTable.class);
				intent.putExtra(HouJTGRConfirmTable.KEY.in_id, household.getId());
				intent.putExtra(HouJTGRConfirmTable.KEY.cid, household.getC_id());
				intent.putExtra(HouJTGRConfirmTable.KEY.pid, household.getPid());
				intent.putExtra(HouJTGRConfirmTable.KEY.hid, household.getId());
				intent.putExtra(HouJTGRConfirmTable.KEY.c_status, household.getC_status());
				activity.startActivityForResult(intent,1);
			}
			else if("1".equals(household.getHousehold_type()) 
			&& "1".equals(household.getProject_type())){
				Intent intent = new Intent(activity, HouJTQYConfirmTable.class);
				intent.putExtra(HouJTQYConfirmTable.KEY.in_id, household.getId());
				intent.putExtra(HouJTQYConfirmTable.KEY.cid, household.getC_id());
				intent.putExtra(HouJTQYConfirmTable.KEY.pid, household.getPid());
				intent.putExtra(HouJTQYConfirmTable.KEY.hid, household.getId());
				activity.startActivityForResult(intent,1);
			}
		}
	}
	
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK){
			View btn =  findViewById(R.id.coptsheet);
			btn.setVisibility(View.VISIBLE);
			operateIntent();
		}
	}
	
	void Onclick_btn_coptsheet(){
		if(newactivity()){
				Intent intent = new Intent(activity, HouseholdCoptSheetA.class);
				intent.putExtra(HouseholdCoptSheetA.KEY.in_id, household.getId());
				intent.putExtra(HouseholdCoptSheetA.KEY.h_type, household.getHousehold_type());
				intent.putExtra(HouseholdCoptSheetA.KEY.p_type, household.getProject_type());
				
				intent.putExtra(HouseholdCoptSheetA.KEY.pid, household.getPid());
				intent.putExtra(HouseholdCoptSheetA.KEY.hid, household.getId());
				
				intent.putExtra(HouseholdCoptSheetA.KEY.c_id, household.getC_id());
				intent.putExtra(HouseholdCoptSheetA.KEY.c_status, household.getC_status());
				
				intent.putExtra(HouseholdCoptSheetA.KEY.hliststatus, household.getHliststatus());
				
			//	intent.putExtra(HouseholdCoptSheetA.KEY.hliststatus, hliststatus);
			   
				activity.startActivityForResult(intent,1);		
		    }
	   }
	
	
	
	void Onclick_btn_housepushtable(){
		if(newactivity()){			
			Intent intent = new Intent(activity, HouseholdPushTableA.class);
			intent.putExtra(HouseholdPushTableA.KEY.in_id, household.getId());
			activity.startActivity(intent);
		}
	}
	
	
	void Onclick_btn_housefeedback(){
		if(newactivity()){			
			Intent intent = new Intent(activity, HouQuestionListA.class);
			intent.putExtra(HouQuestionListA.KEY.in_id, household.getId());
			intent.putExtra(HouQuestionListA.KEY.pid, household.getPid());
			intent.putExtra(HouQuestionListA.KEY.household_id, household.getId());
			activity.startActivity(intent);
		}
	}
	
	
	void Onclick_btn_timecontrol(){
		if(newactivity()){			
			Intent intent = new Intent(activity, TimeControlHouInfoA.class);
			intent.putExtra(TimeControlHouInfoA.KEY.in_id, household.getPid());
			intent.putExtra(TimeControlHouInfoA.KEY.householdid, household.getId());
			activity.startActivity(intent);
		}
	}
	
		
	boolean newactivity(){
//		if(isReader){
//			Toast.makeText(activity, "在线查询时不能使用该功能", Toast.LENGTH_LONG).show();
//			return false;
//		}		
		if(isAdd){
			Toast.makeText(activity, "请先保存分户信息", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

}
