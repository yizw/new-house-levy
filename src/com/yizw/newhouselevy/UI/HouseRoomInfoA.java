package com.yizw.newhouselevy.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.HouseRoomInfoSaveAsyncTask;
import com.yizw.newhouselevy.AsyncTask.HouseRoomInfoSearchAsyncTask;
import com.yizw.newhouselevy.Business.House_Room_Info_Bus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.House_Room_Info;

/*房源--房间信息*/
   public class HouseRoomInfoA extends OrmLiteBaseActivity<DatabaseHelper> {

	public class KEY {
		public static final String in_id = "in_id";
		/*算单id*/
		public static final String listid = "listid";
		public static final String index = "index";
		
		/** 返回选中的房间id*/
		public static final String re_id = "id";
	}

	public HouseRoomInfoA activity;
	public House_Room_Info entity;
	DatabaseHelper helper;
	boolean isAdd = false;
	
	public String in_id,listid,index;

	private TextView remark,area,housetype,room,floor,unit,bno;

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
		setContentView(R.layout.house_room_info);
		PublicBus.titleBarControl(this, "房间信息", true, null, true, "");
		initControl();
		operateIntent();
	}
		
	private void initControl() {
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
		bno = (TextView) findViewById(R.id.bno);
		unit = (TextView) findViewById(R.id.unit);
		floor = (TextView) findViewById(R.id.floor);
		room = (TextView) findViewById(R.id.room);
		housetype = (TextView) findViewById(R.id.housetype);
		area = (TextView) findViewById(R.id.area);
		remark = (TextView) findViewById(R.id.remark);

	}
		
	private void operateIntent() {
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
		listid = intent.getStringExtra(KEY.listid);
	    index =intent.getStringExtra(KEY.index);
	    
		in_id = id;
		if(StringHelper.isNullOrEmpty(id)){
			isAdd = true;
			entity = new House_Room_Info();			
		}else{
			isAdd = false;
			entity = new House_Room_Info(); 
			HouseRoomInfoSearchAsyncTask task = new HouseRoomInfoSearchAsyncTask(activity);
			task.execute(id);		
		}
		
	}

	Button initBtn(int id) {
		Button btn = (Button) findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}

	public void setEntityToUI(House_Room_Info entity) {
		bno.setText(entity.getBno());
		unit.setText(entity.getUnit());
		floor.setText(entity.getFloor());
		room.setText(entity.getRoom());
		housetype.setText(entity.getHousetype());
		area.setText(entity.getArea()+"");
		remark.setText(entity.getRemark());
		
		//如果查看房间信息，没有算单listid或index值，屏蔽保存按钮，使其不能修改
		if(listid=="" || index==null){
		Button btn = (Button)findViewById(R.id.btn_title_right);
		btn.setVisibility(View.INVISIBLE);
		}
		
		
	}

	class OnClick implements View.OnClickListener {

		public void onClick(View v) {
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
			try {
				//getEntity();
				String result = House_Room_Info_Bus.check(entity);
				if (!result.equals("")) {
					DialogHelper.showConfirm(activity, result);
					return;
				}
				HouseRoomInfoSaveAsyncTask task = new HouseRoomInfoSaveAsyncTask(activity);
				task.execute("");
				
			} catch (Exception e) {
				MyException myE = new MyException("保存出错", e.getMessage(),
						e.getStackTrace());
				ExceptionHelper.Operate(myE, true, activity);
			}
		}
	}
}
