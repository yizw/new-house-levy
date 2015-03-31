package com.yizw.newhouselevy.UI;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.yizw.newhouselevy.Business.HOU_HOUSEHOLD_Bus;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.EnumCode;
import com.yizw.newhouselevy.Entity.HOU_HOUSEHOLD;

/**分户--新增分户*/
public class HouseholdAdd extends OrmLiteBaseActivity<DatabaseHelper>{

	public class KEY{	
		public static final String in_id = "in_id";
	}
	
	public HouseholdAdd activity;
	public static HOU_HOUSEHOLD entity;
	boolean isAdd = true;
	
	private EditText edit_houseowner,edit_address,edit_remark;
//	edit_cartno,edit_linktel,
	
	private Spinner sp_householdtype;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.household_info);
		PublicBus.titleBarControl(this, "分户信息", true, null, true, null);
		initControl();
		operateIntent();
	}
			
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
				
		try {
			edit_houseowner = (EditText) findViewById(R.id.edit_houseowner);
		//	edit_cartno = (EditText) findViewById(R.id.edit_cardno);
		//	edit_linktel = (EditText) findViewById(R.id.edit_linktel);
//			edit_address = (EditText) findViewById(R.id.edit_address);
//			edit_remark = (EditText) findViewById(R.id.edit_remark);			
			sp_householdtype = initSp(R.id.sp_householdtype, EnumCode.HOU_HOUSEHOLD.householdtype, true);
			
		} catch (MyException e) {
			ExceptionHelper.Operate(e, true, this);
		}
	}
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
		
		if(StringHelper.isNullOrEmpty(id)){
			isAdd = true;
			entity = new HOU_HOUSEHOLD();			
		}else{
			isAdd = false;
			setEntityToUI(entity);

		}
	}
	
	Button initBtn(int id) {
		Button btn = (Button) findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	Spinner initSp(int id,String enmucode,boolean allowEmptyRow) throws MyException{
		Spinner sp = (Spinner)findViewById(id);
		PublicBus.spinnerEnumBinding(sp,enmucode,allowEmptyRow,getHelper(),this);
		return sp;
	}
	
	
	public void setEntityToUI(HOU_HOUSEHOLD entity){
		edit_houseowner.setText(entity.getHouseowner());
	//	edit_cartno.setText(entity.getCartno());
	//	edit_linktel.setText(entity.getLinktel());		
//		edit_address.setText(entity.getAddress());		
//		edit_remark.setText(entity.getRemark());		
		PublicBus.spinnerSetValue(sp_householdtype, entity.getHousehold_type());		
	}
	
	public HOU_HOUSEHOLD getEntity(){
		if(isAdd){
			entity.setCreator(LoginBus.getLogin(this).getREALNAME());
			entity.setCreatdate(new Date());
		}
		
		entity.setUsername(LoginBus.getLogin(this).getUSERNAME());
	//	entity.setSTATUS("0");
		entity.setModifier(LoginBus.getLogin(this).getREALNAME());
		entity.setModifydate(new Date());
		
		entity.setHouseowner(edit_houseowner.getText().toString());
	//	entity.setCartno(edit_cartno.getText().toString());
	//	entity.setLinktel(edit_linktel.getText().toString());			
		entity.setAddress(edit_address.getText().toString());
		entity.setRemark(edit_remark.getText().toString());	
		entity.setHousehold_type(PublicBus.spinnerGetValue(sp_householdtype));
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
		
		void Onclick_btn_title_right(){
			try {
				getEntity();
				String result = HOU_HOUSEHOLD_Bus.check(entity);
				if(!result.equals("")){
					DialogHelper.showConfirm(activity, result);
					return;
				}
				HOU_HOUSEHOLD_Bus bus = new HOU_HOUSEHOLD_Bus(getHelper());
				bus.create(entity);
				Toast.makeText(activity, "保存成功", Toast.LENGTH_LONG).show();
				activity.setResult(Activity.RESULT_OK, null);
				activity.finish();
			}catch (MyException e) {
				ExceptionHelper.Operate(e, true, activity);
			}catch (Exception e) {
				MyException myE = new MyException("保存出错", e.getMessage(),e.getStackTrace());
				ExceptionHelper.Operate(myE, true, activity);
			}
		}
	}
	
	
}
