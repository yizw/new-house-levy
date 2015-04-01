package com.yizw.newhouselevy.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.HouseConfirmRegInfoSaveAsyncTask;
import com.yizw.newhouselevy.AsyncTask.HouseConfirmRegInfoSearchAsyncTask;
import com.yizw.newhouselevy.Business.HouseConfirmRegBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HouseConfirmReg;


/*房屋调查确认表 -- 房屋产权手续登记信息页面
*author:yizw
*/
public class HouseConfirmRegInfoA extends OrmLiteBaseActivity<DatabaseHelper>{

	public class KEY{	
		//确认表id
		public static final String confirmid = "confirmid";
		public static final String in_id = "in_id";
		
	}
	
	
	public HouseConfirmRegInfoA activity;
	public HouseConfirmReg entity;
	DatabaseHelper helper;
	
	boolean isAdd = true;
	
	public String id,in_id,confirmid,p_id,h_id; 
	
	private EditText  edit_prono, edit_pronature,edit_prouse,edit_proarea,
    edit_approveno,edit_approvedepart,
    edit_approvearea,edit_specsituation,edit_closesituation,
    edit_ismortgage,edit_isclose;
	
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
		setContentView(R.layout.hou_confirmreg_info);
		PublicBus.titleBarControl(this, "详细信息", true, null, true, null);
		initControl();
		operateIntent();
	}
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);

		edit_prono = (EditText) findViewById(R.id.edit_prono);
		edit_pronature = (EditText) findViewById(R.id.edit_pronature);
		edit_prouse = (EditText) findViewById(R.id.edit_prouse);
		edit_proarea = (EditText) findViewById(R.id.edit_proarea);
		edit_approveno = (EditText) findViewById(R.id.edit_approveno);
		edit_approvedepart = (EditText) findViewById(R.id.edit_approvedepart);
		edit_approvearea = (EditText) findViewById(R.id.edit_approvearea);
		edit_specsituation = (EditText) findViewById(R.id.edit_specsituation);
		edit_closesituation = (EditText) findViewById(R.id.edit_closesituation);
		edit_ismortgage = (EditText) findViewById(R.id.edit_ismortgage);
		edit_isclose = (EditText) findViewById(R.id.edit_isclose);
	}
		
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		id = intent.getStringExtra(KEY.in_id); 
		if(StringHelper.isNullOrEmpty(id)){
			isAdd = true;
			confirmid =  intent.getStringExtra(KEY.confirmid);;
			entity = new HouseConfirmReg();	
			entity.setConfirmid(confirmid);
		}else{
			isAdd = false;
			entity = new HouseConfirmReg();
			entity.setId(id);
			HouseConfirmRegInfoSearchAsyncTask task = new HouseConfirmRegInfoSearchAsyncTask(activity);
			task.execute(id);
		}
	}
	public void setEntityToUI(HouseConfirmReg entity) {
		
		edit_prono.setText(entity.getC4());
		edit_pronature.setText(entity.getC32());
		edit_prouse.setText(entity.getX6());
		edit_proarea.setText(entity.getC21());
		edit_approveno.setText(entity.getC3());
		edit_approvedepart.setText(entity.getC15());
		edit_approvearea.setText(entity.getC34());
		edit_specsituation.setText(entity.getC23());
		edit_closesituation.setText(entity.getC25());
		
		edit_ismortgage.setText(entity.getC22());
		edit_isclose.setText(entity.getC24());
		
//		if(entity.getStatus()!=null&&entity.getStatus()==2){
//			btn_title_right.setVisibility(View.GONE); 
//		}
	
	}
	
	public HouseConfirmReg getEntity(){
		
		entity.setC4(edit_prono.getText().toString());
		entity.setC32(edit_pronature.getText().toString());
		entity.setX6(edit_prouse.getText().toString());
		entity.setC21(edit_proarea.getText().toString());
		entity.setC3(edit_approveno.getText().toString());
		entity.setC15(edit_approvedepart.getText().toString());
		entity.setC34(edit_approvearea.getText().toString());
		entity.setC23(edit_specsituation.getText().toString());
		entity.setC25(edit_closesituation.getText().toString());
		
		entity.setC22(edit_ismortgage.getText().toString());
		entity.setC24(edit_isclose.getText().toString()); 
		
		return entity;
	}

	
	class OnClick implements OnClickListener {

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
		
		void Onclick_btn_title_right(){
			try {
				getEntity();
				String result = HouseConfirmRegBus.check(entity);
				if(!result.equals("")){
					DialogHelper.showConfirm(activity, result);
					return;
				}
				
				HouseConfirmRegInfoSaveAsyncTask task = new HouseConfirmRegInfoSaveAsyncTask(activity);
				task.execute(id);   
			} catch (Exception e) {
				MyException myE = new MyException("保存出错", e.getMessage(),e.getStackTrace());
				ExceptionHelper.Operate(myE, true, activity);
			}
		}
	}
	
}