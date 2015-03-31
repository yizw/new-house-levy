package com.yizw.newhouselevy.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.HouJTQYConfirmAsyncTask;
import com.yizw.newhouselevy.AsyncTask.HouseConfirmJTQYSaveAsyncTask;
import com.yizw.newhouselevy.Business.CONFIRM_JTQY_PRO_MON_Bus;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HouseConfirm;


/** 集体企业--房屋调查确认表（集体企业货币确认表和集体企业产权确认表一致） */
public class HouJTQYConfirmTable extends OrmLiteBaseActivity<DatabaseHelper> {

	public class KEY {
		/*确认表id*/
		public static final String in_id = "in_id";
		/** 补偿计算单类型 */
		public static final String in_type = "in_type";
		public static final String cid = "cid";
		public static final String hid = "hid";
		
		public static final String pid = "pid";
	}

	public HouJTQYConfirmTable activity;
	public HouseConfirm entity;
	public String status="1";

	/** 是否是新增确认信息 */
	boolean isAdd = true;
	public String id,cid,in_id,in_type,hid,pid;  
	private Button btn_title_right;

	private EditText edit_houselocation_x4, edit_houseowner_x1, edit_licenseno,
			edit_authority, edit_operatperiod, edit_busaddress,
		//	edit_enterprisename_x1, 
			edit_enterprisenature,
			edit_representative_x186, edit_tellink_x3, edit_paysecurity,
			edit_taxproof, edit_apprprocedure, edit_structure_x7_y,
			edit_area_x7_y, edit_structure_x8_y, edit_area_x8_y,
			edit_structure_x81_y, edit_area_x81_y, edit_structure_x84_y,
			edit_area_x84_y, edit_structure_x200_y, edit_area_x200_y,
			edit_structure_x201_y, edit_area_x201_y,
			edit_area_x11_y, edit_area_x7_n,edit_area_x8_n,
			edit_area_x81_n, edit_area_x84_n,
			edit_area_x200_n, edit_area_x201_n, 
			edit_area_x12_n,
			edit_x600,
			edit_prono, edit_pronature, edit_prouse, edit_proarea,
			edit_approveno, edit_approvedepart, edit_approvearea,
			edit_specsituation, edit_closesituation, edit_remark,
		//	edit_maininquirer_x218,
			
			edit_isoperat, edit_islegaluse, edit_ismortgage, edit_isclose;

	private TextView text_structure_x7_n,text_structure_x8_n,text_structure_x81_n,text_enterprisename_x1,
    text_structure_x84_n,text_structure_x200_n,text_structure_x201_n,text_maininquirer_x218;

	AlertDialog statusDialog = null;

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
		setContentView(R.layout.hou_jtqy_pro_mon_confirmtable);
		PublicBus.titleBarControl(this, "征收非住宅房屋调查确认表（集体）", true, null, true, null);
		initControl();
		operateIntent();
	}

	// 屏蔽掉实体返回键
	@Override
	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		if (KeyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			return false;
		}
		return false;
	}

	/* 初始化控件 */
	private void initControl() {
		activity = this;

		btn_title_right = (Button) findViewById(R.id.btn_title_right);
		btn_title_right.setOnClickListener(new OnClick());
		findViewById(R.id.btn_title_left).setOnClickListener(new OnClick());
		
		//算单附件  按钮
		initBtn(R.id.btn_att_1);
		initBtn(R.id.btn_att_2);
		initBtn(R.id.btn_att_3);

		edit_houselocation_x4 = (EditText) findViewById(R.id.edit_houselocation_x4);
		edit_houseowner_x1 = (EditText) findViewById(R.id.edit_houseowner_x1);
		edit_licenseno = (EditText) findViewById(R.id.edit_licenseno);
		edit_authority = (EditText) findViewById(R.id.edit_authority);
		edit_operatperiod = (EditText) findViewById(R.id.edit_operatperiod);
		edit_busaddress = (EditText) findViewById(R.id.edit_busaddress);
		//edit_enterprisename_x1 = (EditText) findViewById(R.id.edit_enterprisename_x1);
		
		text_enterprisename_x1 = (TextView) findViewById(R.id.text_enterprisename_x1);
		
		edit_enterprisenature = (EditText) findViewById(R.id.edit_enterprisenature);
		edit_representative_x186 = (EditText) findViewById(R.id.edit_representative_x186);
		edit_tellink_x3 = (EditText) findViewById(R.id.edit_tellink_x3);
		edit_paysecurity = (EditText) findViewById(R.id.edit_paysecurity);
		edit_taxproof = (EditText) findViewById(R.id.edit_taxproof);
		edit_apprprocedure = (EditText) findViewById(R.id.edit_apprprocedure);
		edit_structure_x7_y = (EditText) findViewById(R.id.edit_structure_x7_y);
		edit_area_x7_y = (EditText) findViewById(R.id.edit_area_x7_y);
		edit_structure_x8_y = (EditText) findViewById(R.id.edit_structure_x8_y);
		edit_area_x8_y = (EditText) findViewById(R.id.edit_area_x8_y);
		edit_structure_x81_y = (EditText) findViewById(R.id.edit_structure_x81_y);
		edit_area_x81_y = (EditText) findViewById(R.id.edit_area_x81_y);
		edit_structure_x84_y = (EditText) findViewById(R.id.edit_structure_x84_y);
		edit_area_x84_y = (EditText) findViewById(R.id.edit_area_x84_y);

		edit_structure_x200_y = (EditText) findViewById(R.id.edit_structure_x200_y);
		edit_area_x200_y = (EditText) findViewById(R.id.edit_area_x200_y);
		edit_structure_x201_y = (EditText) findViewById(R.id.edit_structure_x201_y);
		edit_area_x201_y = (EditText) findViewById(R.id.edit_area_x201_y);
				
		edit_area_x11_y = (EditText) findViewById(R.id.edit_area_x11_y);
		text_structure_x7_n = (TextView) findViewById(R.id.text_structure_x7_n);
		edit_area_x7_n = (EditText) findViewById(R.id.edit_area_x7_n);
		text_structure_x8_n = (TextView) findViewById(R.id.text_structure_x8_n);
		edit_area_x8_n = (EditText) findViewById(R.id.edit_area_x8_n);
		text_structure_x81_n = (TextView) findViewById(R.id.text_structure_x81_n);
		edit_area_x81_n = (EditText) findViewById(R.id.edit_area_x81_n);
		text_structure_x84_n = (TextView) findViewById(R.id.text_structure_x84_n);
		edit_area_x84_n = (EditText) findViewById(R.id.edit_area_x84_n);
		text_structure_x200_n = (TextView) findViewById(R.id.text_structure_x200_n);
		edit_area_x200_n = (EditText) findViewById(R.id.edit_area_x200_n);
		text_structure_x201_n = (TextView) findViewById(R.id.text_structure_x201_n);
		edit_area_x201_n = (EditText) findViewById(R.id.edit_area_x201_n);
		edit_area_x12_n = (EditText) findViewById(R.id.edit_area_x12_n);
		
		edit_x600 = (EditText) findViewById(R.id.edit_x600);
		
		edit_prono = (EditText) findViewById(R.id.edit_prono);
		edit_pronature = (EditText) findViewById(R.id.edit_pronature);
		edit_prouse = (EditText) findViewById(R.id.edit_prouse);
		edit_proarea = (EditText) findViewById(R.id.edit_proarea);
		edit_approveno = (EditText) findViewById(R.id.edit_approveno);
		edit_approvedepart = (EditText) findViewById(R.id.edit_approvedepart);
		edit_approvearea = (EditText) findViewById(R.id.edit_approvearea);
		edit_specsituation = (EditText) findViewById(R.id.edit_specsituation);
		edit_closesituation = (EditText) findViewById(R.id.edit_closesituation);
		edit_remark = (EditText) findViewById(R.id.edit_remark);
	//	edit_maininquirer_x218 = (EditText) findViewById(R.id.edit_maininquirer_x218);
		
		text_maininquirer_x218 = (TextView) findViewById(R.id.text_maininquirer_x218);

		edit_isoperat = (EditText) findViewById(R.id.edit_isoperat);
		edit_islegaluse = (EditText) findViewById(R.id.edit_islegaluse);
		edit_ismortgage = (EditText) findViewById(R.id.edit_ismortgage);
		edit_isclose = (EditText) findViewById(R.id.edit_isclose);

	}

	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	/** 处理窗体传过来的数据 */
	private void operateIntent() {
		Intent intent = this.getIntent();
		String id = intent.getStringExtra(KEY.in_id);
		
        String c_id = intent.getStringExtra(KEY.cid);
		
		hid = intent.getStringExtra(KEY.hid);
	    pid = intent.getStringExtra(KEY.pid);
		
		in_id=id;
		
		cid = c_id;
		
		in_id=id;
		if (StringHelper.isNullOrEmpty(in_id)) {
			isAdd = true;
			entity = new HouseConfirm();
			entity.setHid(hid);
			entity.setStatus(1);
			text_maininquirer_x218.setText(LoginBus.getLogin(this).getREALNAME());
			setEntityToUI(entity);
		} else {
			isAdd = false; 
			entity = new HouseConfirm();
			entity.setX218(LoginBus.getLogin(this).getREALNAME());
			HouJTQYConfirmAsyncTask task = new HouJTQYConfirmAsyncTask(activity);
			task.execute(id);
			setEntityToUI(entity);
		}
	}

	Spinner initSp(int id, String enmucode, boolean allowEmptyRow) throws MyException {
		Spinner sp = (Spinner) findViewById(id);
		PublicBus.spinnerEnumBinding(sp, enmucode, allowEmptyRow, getHelper(),this);
		return sp;
	}

	/** 将集体企业（产权，货币）房屋调查确认表的信息展现到UI */
	public void setEntityToUI(HouseConfirm entity) {
		edit_houselocation_x4.setText(entity.getX4());
		edit_houseowner_x1.setText(entity.getX1());
		edit_licenseno.setText(entity.getC14());
		edit_authority.setText(entity.getC27());
		edit_operatperiod.setText(entity.getC17());
		edit_busaddress.setText(entity.getC16());
	//	edit_enterprisename_x1.setText(entity.getX1());
		text_enterprisename_x1.setText(entity.getX1());
		
		edit_enterprisenature.setText(entity.getC28());
		edit_representative_x186.setText(entity.getX186());
		edit_tellink_x3.setText(entity.getX3());
		edit_paysecurity.setText(entity.getC33());
		edit_taxproof.setText(entity.getC29());
		edit_apprprocedure.setText(entity.getC20());
		
		edit_structure_x7_y.setText(entity.getX7());
		edit_area_x7_y.setText(entity.getA1()+"");
		edit_structure_x8_y.setText(entity.getX8());
		edit_area_x8_y.setText(entity.getA2()+"");
		edit_structure_x81_y.setText(entity.getX81());
		edit_area_x81_y.setText(entity.getA3()+"");
		edit_structure_x84_y.setText(entity.getX84());
		edit_area_x84_y.setText(entity.getA4()+"");
		edit_structure_x200_y.setText(entity.getX200());
		edit_area_x200_y.setText(entity.getA5()+"");
		edit_structure_x201_y.setText(entity.getX201());
		edit_area_x201_y.setText(entity.getA6()+"");
		edit_area_x11_y.setText(entity.getX11()+"");
		
	    text_structure_x7_n.setText(entity.getX7());
		edit_area_x7_n.setText(entity.getN1()+"");
		text_structure_x8_n.setText(entity.getX8());
		edit_area_x8_n.setText(entity.getN2()+"");
		text_structure_x81_n.setText(entity.getX81());
		edit_area_x81_n.setText(entity.getN3()+"");
		text_structure_x84_n.setText(entity.getX84());
		edit_area_x84_n.setText(entity.getN4()+"");
		text_structure_x200_n.setText(entity.getX200());
		edit_area_x200_n.setText(entity.getN5()+"");
		text_structure_x201_n.setText(entity.getX201());
		edit_area_x201_n.setText(entity.getN6()+"");
		edit_area_x12_n.setText(entity.getX12()+"");
		
		edit_x600.setText(entity.getX600()+"");
		
		edit_prono.setText(entity.getC4());
		edit_pronature.setText(entity.getC32());
		edit_prouse.setText(entity.getX6());
		edit_proarea.setText(entity.getC21());
		edit_approveno.setText(entity.getC3());
		edit_approvedepart.setText(entity.getC15());
		edit_approvearea.setText(entity.getC34());
		edit_specsituation.setText(entity.getC23());
		edit_closesituation.setText(entity.getC25());
		edit_remark.setText(entity.getC26());
	//	edit_maininquirer_x218.setText(entity.getX218());
		
		text_maininquirer_x218.setText(LoginBus.getLogin(this).getREALNAME());
		
		edit_isoperat.setText(entity.getC33());
		edit_islegaluse.setText(entity.getC30());
		edit_ismortgage.setText(entity.getC22());
		edit_isclose.setText(entity.getC24());
		
		if(entity.getStatus()!=null&&entity.getStatus()==2){
			btn_title_right.setVisibility(View.GONE); 
		}
	}

	private HouseConfirm getEntity() {
		if (isAdd) {
			entity.setCreator(LoginBus.getLogin(this).getREALNAME());
			// entity.setCREDATE(new Date());
		}

//		 entity.setUSERNAME(LoginBus.getLogin(this).getUSERNAME());
//		 entity.setSTATUS("0");
		 entity.setModifier(LoginBus.getLogin(this).getREALNAME());
//		 entity.setModifydate(new Date());

		entity.setX4(edit_houselocation_x4.getText().toString());
		entity.setX1(edit_houseowner_x1.getText().toString());
		entity.setC14(edit_licenseno.getText().toString());
		entity.setC27(edit_authority.getText().toString());
		entity.setC17(edit_operatperiod.getText().toString());
		entity.setC16(edit_busaddress.getText().toString());
	//	entity.setX1(edit_enterprisename_x1.getText().toString());
		entity.setC28(edit_enterprisenature.getText().toString());
		entity.setX186(edit_representative_x186.getText().toString());
		entity.setX3(edit_tellink_x3.getText().toString());
		entity.setC29(edit_paysecurity.getText().toString());
		entity.setC20(edit_taxproof.getText().toString());
		entity.setC31(edit_apprprocedure.getText().toString());
		
		entity.setX7(edit_structure_x7_y.getText().toString());
		entity.setA1(StringToDouble(edit_area_x7_y.getText().toString()));
		entity.setX8(edit_structure_x8_y.getText().toString());
		entity.setA2(StringToDouble(edit_area_x8_y.getText().toString()));
		entity.setX81(edit_structure_x81_y.getText().toString());
		entity.setA3(StringToDouble(edit_area_x81_y.getText().toString()));
		entity.setX84(edit_structure_x84_y.getText().toString());
		entity.setA4(StringToDouble(edit_area_x84_y.getText().toString()));
		entity.setX200(edit_structure_x200_y.getText().toString());
		entity.setA5(StringToDouble(edit_area_x200_y.getText().toString()));
		entity.setX201(edit_structure_x201_y.getText().toString());
		entity.setA6(StringToDouble(edit_area_x201_y.getText().toString()));
		entity.setX11(StringToDouble(edit_area_x11_y.getText().toString()));
		
		entity.setN1(StringToDouble(edit_area_x7_n.getText().toString()));
		entity.setN2(StringToDouble(edit_area_x8_n.getText().toString()));
		entity.setN3(StringToDouble(edit_area_x81_n.getText().toString()));
		entity.setN4(StringToDouble(edit_area_x84_n.getText().toString()));
		entity.setN5(StringToDouble(edit_area_x200_n.getText().toString()));
		entity.setN6(StringToDouble(edit_area_x201_n.getText().toString()));
		
		entity.setX600(StringToDouble(edit_x600.getText().toString()));
		
		entity.setX12(StringToDouble(edit_area_x12_n.getText().toString()));
		entity.setC4(edit_prono.getText().toString());
		entity.setC32(edit_pronature.getText().toString());
		entity.setX6(edit_prouse.getText().toString());
		entity.setC21(edit_proarea.getText().toString());
		entity.setC3(edit_approveno.getText().toString());
		entity.setC15(edit_approvedepart.getText().toString());
		entity.setC34(edit_approvearea.getText().toString());
		entity.setC23(edit_specsituation.getText().toString());
		entity.setC25(edit_closesituation.getText().toString());
		entity.setC26(edit_remark.getText().toString());
	//	entity.setX218(edit_maininquirer_x218.getText().toString());
		entity.setX218(LoginBus.getLogin(this).getREALNAME());
		
		entity.setC33(edit_isoperat.getText().toString());
		entity.setC30(edit_islegaluse.getText().toString());
		entity.setC22(edit_ismortgage.getText().toString());
		entity.setC24(edit_isclose.getText().toString());

		return entity;
	}
	
	
	private Double StringToDouble(String text){
		if(StringHelper.isNullOrEmpty(text)){
			return Double.parseDouble("0.00");
		}else{
			return Double.parseDouble(text);
		}
	}
	
	
	

	// @Override
	protected void OnActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {

		default:
			// RequestCode_photo(requestCode,resultCode, data);
			break;
		}
	}

	class OnClick implements View.OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_title_left:
				Onclick_btn_title_left();
				break;
			case R.id.btn_title_right:
				Onclick_btn_title_right();
				break;
			case R.id.btn_att_1:
				Onclick_btn_att_1();
				break;	
			case R.id.btn_att_2:
				Onclick_btn_att_2();
				break;
			case R.id.btn_att_3:
				Onclick_btn_att_3();
				break;
			default:
				break;
			}
		}
		
		void Onclick_btn_att_1() {
			if(newactivity()){
			Intent intent = new Intent(activity, HouConfirmAttListA.class);
			intent.putExtra(HouConfirmAttListA.KEY.hid, entity.getHid());
			intent.putExtra(HouConfirmAttListA.KEY.pid, entity.getPid());
			intent.putExtra(HouConfirmAttListA.KEY.temptype, "0");
			activity.startActivityForResult(intent,1);
		}
		}
		void Onclick_btn_att_2() {
			if(newactivity()){
			Intent intent = new Intent(activity, HouConfirmAttListA.class);
			intent.putExtra(HouConfirmAttListA.KEY.hid, entity.getHid());
			intent.putExtra(HouConfirmAttListA.KEY.pid, entity.getPid());
			intent.putExtra(HouConfirmAttListA.KEY.temptype, "1");
			activity.startActivityForResult(intent,1);
			}
		}
		void Onclick_btn_att_3() {
			if(newactivity()){
			Intent intent = new Intent(activity, HouConfirmAttListA.class);
			intent.putExtra(HouConfirmAttListA.KEY.hid, entity.getHid());
			intent.putExtra(HouConfirmAttListA.KEY.pid, entity.getPid());
			intent.putExtra(HouConfirmAttListA.KEY.temptype, "2");
			activity.startActivityForResult(intent,1);
			}
		}

		
		boolean newactivity(){
			if(StringHelper.isNullOrEmpty(entity.getId())){
				Toast.makeText(activity, "确认表ID为空，请先保存确认表！", Toast.LENGTH_LONG).show();
				return false;
			}
			return true;
		}
		
		
		
		
//		void Onclick_btn_title_right() {
//			try {
//				getEntity();
//				String result = CONFIRM_JTQY_PRO_MON_Bus.check(entity);
//				if (!result.equals("")) {
//					DialogHelper.showConfirm(activity, result);
//					return;
//				}
//				CONFIRM_JTQY_PRO_MON_Bus bus = new CONFIRM_JTQY_PRO_MON_Bus(
//						getHelper());
//				bus.create(entity);
//
//				Toast.makeText(activity, "保存成功", Toast.LENGTH_LONG).show();
//				activity.setResult(Activity.RESULT_OK, null);
//				activity.finish();
//			} catch (MyException e) {
//				ExceptionHelper.Operate(e, true, activity);
//			} catch (Exception e) {
//				MyException myE = new MyException("保存出错", e.getMessage(),
//						e.getStackTrace());
//				ExceptionHelper.Operate(myE, true, activity);
//			}
//		}
		

		void Onclick_btn_title_right(){	
			getEntity();
			String result = CONFIRM_JTQY_PRO_MON_Bus.check(entity);
			if (!result.equals("")) {
				DialogHelper.showConfirm(activity, result);
				return;
			} 
			showTempSaveDialog(null);
	       }
		
		void showTempSaveDialog(String message) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("确认表提交或暂存");
		//	builder.setMessage(message);
			builder.setIcon(android.R.drawable.ic_dialog_info); 
			builder.setSingleChoiceItems(new String[] {"暂时保存","确认提交"}, 0, new DialogInterface.OnClickListener() {  
				                       
		    @Override  
			public void onClick(DialogInterface dialog, int which) {  
			entity.setStatus(which+1);
		    }  
			}) ;
			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//		    if (entity.getStatus() != null) {
//		    entity.setStatus(which + 1);
//			} else {
//			entity.setStatus(1);
//			}
//			}
//			});
			

			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					try { 
					HouseConfirmJTQYSaveAsyncTask save = new HouseConfirmJTQYSaveAsyncTask(activity);
						save.execute("");
					} catch (Exception e) {
						MyException myE = new MyException("保存出错", e.getMessage(),e.getStackTrace());
						ExceptionHelper.Operate(myE, true, activity);
					}
				}
			});		
			
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			
		 
			Dialog dialog = builder.create();
			dialog.show();
		} 
		

		void Onclick_btn_title_left() {
			if (isAdd == false) {
				HouJTQYConfirmTable.this.finish();
				return;
			}
			// 新增集体企业（产权，货币）房屋调查确认表信息时，按下返回，如果必填项姓名或证件号不为空则进行关闭确认
			if (isAdd) {
				String houselocation = edit_houselocation_x4.getText()
						.toString().trim();
				String houseowner = edit_houseowner_x1.getText().toString()
						.trim();

				if ((!houselocation.equals("")) || (!houseowner.equals(""))) {
					new AlertDialog.Builder(HouJTQYConfirmTable.this)
							.setMessage("信息未保存，确认要关闭？")
							.setPositiveButton("确定", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									HouJTQYConfirmTable.this.finish();
								}
							}).setNegativeButton("取消", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).create().show();
				} else {
					HouJTQYConfirmTable.this.finish();
				}
			} else {
				HouJTQYConfirmTable.this.finish();
			}
		}

	}

}
