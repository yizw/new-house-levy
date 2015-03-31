package com.yizw.newhouselevy.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.FamilyRegSaveAsyncTask;
import com.yizw.newhouselevy.Business.CONFIRM_JTGR_PRO_MON_Bus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.CONFIRM_PERSON;

/*集体个人房屋调查确认表选择户籍人口*/
public class JTGRConfirmPerson extends OrmLiteBaseActivity<DatabaseHelper>{
	//页面间数据传输用到的key
	public class KEY{
		/** 确认表户籍人员索引*/
		public static final String JTGRconfirmPersonIndex = "JTGRconfirmPersonIndex";
		
		public static final String c_status = "c_status";
	}
	
	public String c_status;
	
	public boolean isAdd = true;
	
	public CONFIRM_PERSON entity=new CONFIRM_PERSON();
	public JTGRConfirmPerson activity;
   
	private EditText edit_remark,edit_workunit,edit_cardno,edit_realation,edit_name;
   
    
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gyconfirm_person);
		PublicBus.titleBarControl(this, "户籍人员信息", true, null, true,null);
		initControl();
		operateIntent();
	}
	
	/** 初始化控件*/
	private void initControl(){
		
		Intent intent = this.getIntent();
		c_status = intent.getStringExtra(KEY.c_status);
		
		activity = this;

		initBtn(R.id.btn_title_right);
		initBtn(R.id.btn_title_left);

		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_realation = (EditText) findViewById(R.id.edit_realation);
		edit_cardno = (EditText) findViewById(R.id.edit_cardno);
		edit_workunit = (EditText) findViewById(R.id.edit_workunit);
		edit_remark = (EditText) findViewById(R.id.edit_remark);
	}
	
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	/*
	 * 取得intent对像
	 */
	private void operateIntent(){
		Intent intent=this.getIntent(); 
		
		entity = (CONFIRM_PERSON)intent.getSerializableExtra(KEY.JTGRconfirmPersonIndex);

        if(entity!=null&&!StringHelper.isNullOrEmpty(entity.getId())){
        	setEntityToUI(entity);
 		   isAdd = false;
        }else{
        	setEntityToUI(entity);
        	isAdd = true;
        }
//		JTGRconfirmPersonKey = intent.getIntExtra(KEY.JTGRconfirmPersonIndex,-1);
//		if(JTGRconfirmPersonKey!= -1&&HouJTGRConfirmTable.confirmPersons.size()>0){
//			entity = HouJTGRConfirmTable.confirmPersons.get(JTGRconfirmPersonKey);
//			setEntityToUI(entity);
//			isAdd = false;
//		}else{
//			isAdd = true;
//		}
			
	}
	
	private void setEntityToUI(CONFIRM_PERSON confirmPerson){
		edit_name.setText(confirmPerson.getName());
		edit_realation.setText(confirmPerson.getRelationship());
		edit_cardno.setText(confirmPerson.getCardno());
		edit_workunit.setText(confirmPerson.getWorkunit());
		edit_remark.setText(confirmPerson.getRemark());
		
		//如果确认表状态值为2（已提交），屏蔽保存按钮，使其不能修改
		if (c_status !=null && "2".equals(c_status)) {
		Button btn = (Button)findViewById(R.id.btn_title_right);
		btn.setVisibility(View.INVISIBLE);
		}
	} 
	
	
	private CONFIRM_PERSON getEntity(){
		entity.setName(edit_name.getText().toString());
		entity.setRelationship(edit_realation.getText().toString());
		entity.setCardno(edit_cardno.getText().toString());
		entity.setWorkunit(edit_workunit.getText().toString());
		entity.setRemark(edit_remark.getText().toString());
		return entity;
	}

	class OnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
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
		
		@SuppressLint("ShowToast")
		void Onclick_btn_title_right() {
			getEntity();
			String result = CONFIRM_JTGR_PRO_MON_Bus.checkPerson(entity);
			if(!result.equals("")){
				DialogHelper.showConfirm(activity,result);
				return;
			} 
			
			FamilyRegSaveAsyncTask task = new FamilyRegSaveAsyncTask(activity);
			task.execute("");
			 
		}
		
	}

	
}
