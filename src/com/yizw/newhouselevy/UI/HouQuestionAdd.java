package com.yizw.newhouselevy.UI;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.yizw.newhouselevy.AsyncTask.HouQuestionSaveAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.Question;

/*分户--问题反馈- 问题  新增页面*/
public class HouQuestionAdd extends OrmLiteBaseActivity<DatabaseHelper>{
	public class KEY{	
		public static final String in_id = "in_id";
		/** 当前进行回复的问题反馈id*/
		public static final String in_parentid = "in_parentid";
		/** 项目id*/
		public static final String pid = "pid";
		/** 分户id*/
		public static final String household_id = "household_id";
		
	}
	
	public HouQuestionAdd activity;
	
	public Question entity;
	
	boolean isAdd = true;	
	
	EditText edit_content;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.hou_question_reply_add);
        PublicBus.titleBarControl(this, "新增问题反馈", true, null, true, "");
        initControl();
        operateIntent();
    }
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
	
		edit_content = (EditText) findViewById(R.id.edit_content);
	}
	
	private void operateIntent(){
		Intent intent = this.getIntent();	
		String pid = intent.getStringExtra(KEY.pid);
		String household_id = intent.getStringExtra(KEY.household_id);
		entity = new Question();
		
	//	entity.setReplies(List<Reply> replies);
		
		entity.setPid(pid);
		entity.setHousehold_id(household_id);
	}
	
	Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	Spinner initSp(int id,String enmucode,boolean allowEmptyRow) throws MyException{
		Spinner sp = (Spinner)findViewById(id);
		PublicBus.spinnerEnumBinding(sp,enmucode,allowEmptyRow,getHelper(),this);
		return sp;
	}

	public Object getEntity(){	
		entity.setContent(edit_content.getText().toString());		
		return entity;
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
			getEntity();
			if(StringHelper.isNullOrEmpty(entity.getContent())){
				DialogHelper.showConfirm(activity, "内容不能为空");
				return;
			}
			HouQuestionSaveAsyncTask task = new HouQuestionSaveAsyncTask(activity);
			task.execute();
		}
	}
}
