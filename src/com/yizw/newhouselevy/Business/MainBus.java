package com.yizw.newhouselevy.Business;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.cogent.core.util.DialogHelper;
import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Entity.PDA_Login;
import com.yizw.newhouselevy.UI.AboutA;
import com.yizw.newhouselevy.UI.DataBaseListA;
import com.yizw.newhouselevy.UI.HouseNameListA;
import com.yizw.newhouselevy.UI.HouseholdProjectListA;
import com.yizw.newhouselevy.UI.MainIndexA;
import com.yizw.newhouselevy.UI.MainProjectListA;
import com.yizw.newhouselevy.UI.MsgAutoUploadS;
import com.yizw.newhouselevy.UI.TotalPushTableListA;

public class MainBus {
	/** 用来对模块进行标识*/
	class KEY{
		public static final int AllProject = 21;		
		public static final int TotalPushTable = 22;
		public static final int HouseholdPush = 23;

		/** 资料库*/
		public static final int DataBase = 24;
		/** 房源*/
		public static final int HouseChoice = 25;
		
		/** 关于*/
		public static final int about = 11;
		/** 退出*/
		public static final int exit = 12;
		
		
		
		/** 更多*/
		public static final int more = 13;
		/** 设置*/
		public static final int setting = 14;
		
		public static final int test = 15;
		
		
	}
	
	/** 用来缓存 生成界面的数据项*/
	private ArrayList<HashMap<String, Object>> listData;
	private Activity activity;
	
	public MainBus(Activity activity){
		this.activity = activity;
	}
	
	public void loadGridData(GridView gridView){
		
	//	boolean closePerm = false;
		
		listData = new ArrayList<HashMap<String, Object>>();
		
		PDA_Login login = LoginBus.getLogin(activity);
		String permission = null;
		if(login==null){
			permission = "";
		}else{
			permission = login.getPERMISSION();
			if(permission==null)
				permission = "";
		}		
		
		addMap(listData,R.drawable.main_plan,"项目总览",KEY.AllProject);  
		addMap(listData,R.drawable.main_totalpushtable,"总推进表",KEY.TotalPushTable); 
		addMap(listData,R.drawable.main_household,"分户推进",KEY.HouseholdPush);
		
		addMap(listData,R.drawable.main_household,"资料库",KEY.DataBase);
		addMap(listData,R.drawable.main_household,"房源",KEY.HouseChoice);
		addMap(listData,R.drawable.main_household,"设置",KEY.setting);
		addMap(listData,R.drawable.main_household,"更多",KEY.more);
		
		addMap(listData,R.drawable.main_about,"关于",KEY.about);
		
		addMap(listData,R.drawable.main_about,"ceshi",KEY.test);
		
		SimpleAdapter adpter = new SimpleAdapter(activity,listData,R.layout.main_item,
				new String[] { "img", "txt" },new int[] { R.id.img, R.id.txt });		
		gridView.setAdapter(adpter);	
	}
		
	private void addMap(ArrayList<HashMap<String, Object>> lstImageItem,int ItemImage,String ItemText,int key){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("img", ItemImage);
		map.put("txt", ItemText);
		map.put("key", key);
		lstImageItem.add(map);
	}

	public void startIntent(int position){
		Intent intent = new Intent();
		int key = Integer.parseInt(listData.get(position).get("key").toString());
		switch (key) {	
			
		case KEY.AllProject:		
			intent = new Intent(activity,MainProjectListA.class);
			activity.startActivity(intent);
			break;
			
		case KEY.TotalPushTable:
			intent = new Intent(activity,TotalPushTableListA.class);
			activity.startActivity(intent);
			break;
			
		case KEY.HouseholdPush:
			intent = new Intent(activity,HouseholdProjectListA.class);
			activity.startActivity(intent);
			break;
			
		case KEY.DataBase:
			intent = new Intent(activity,DataBaseListA.class);
			activity.startActivity(intent);
			break;	
			
		case KEY.HouseChoice:
			intent = new Intent(activity,HouseNameListA.class);
			activity.startActivity(intent);
			break;
		
		case KEY.about:
			intent = new Intent(activity,AboutA.class);
			activity.startActivity(intent);
			break;	
			
		case KEY.test:
			intent = new Intent(activity,MainIndexA.class);
			activity.startActivity(intent);
			break;	
			
			
			
		case KEY.exit:
			DialogHelper.showCloseConfirm(activity,"退出系统", "您确认要退出系统？");
			break;
		}
	}

	/** 启动消息自动上传服务*/
	public void startAutoUpload(Activity activity){
		/** 是否自动上传*/
		boolean uploadStatus = SysConfigBus.getAutoUpload(activity);
		/** 自动上传周期*/
		int uploadCycle = SysConfigBus.getUploadCycle(activity)*1000;
		
//		GpsBus.open = SysConfigBus.getGpsOpen(activity);
//		GpsBus.cycle = SysConfigBus.getGpsCycle(activity)*1000;
		
		PDA_MessageBus.msgAutoUploadParm.setUploadStatus(uploadStatus);
		PDA_MessageBus.msgAutoUploadParm.setUploadCycle(uploadCycle);
		
    	Intent intent = new Intent(activity,MsgAutoUploadS.class);
    	activity.startService(intent);
	}
	
	/** 终止消息自动上传服务*/
	public void stopAutoUpload(Activity activity){
		//停止上传
		PDA_MessageBus.msgAutoUploadParm.setUploadStatus(false);
	//	GpsBus.open = false;
		
		//关闭自动上传服务
    	Intent intent = new Intent(activity, MsgAutoUploadS.class);
    	activity.stopService(intent);
    	
		// 取消显示在通知列表中的指定通知（参数为通知标识符）
		NotificationManager nm = (NotificationManager) activity.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.cancel(MsgAutoUploadS.id_msg);
	}
}
