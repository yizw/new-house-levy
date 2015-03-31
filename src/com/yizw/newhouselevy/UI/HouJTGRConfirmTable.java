package com.yizw.newhouselevy.UI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.yizw.newhouselevy.Adapter.JTGRConfirmPersonAdapter;
import com.yizw.newhouselevy.AsyncTask.FamilyRegListAsyncTask;
import com.yizw.newhouselevy.AsyncTask.HouJTGRConfirmAsyncTask;
import com.yizw.newhouselevy.AsyncTask.HouseConfirmJTGRSaveAsyncTask;
import com.yizw.newhouselevy.Business.CONFIRM_JTGR_PRO_MON_Bus;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.CONFIRM_PERSON;
import com.yizw.newhouselevy.Entity.HouseConfirm;

/**集体个人--房屋调查确认表（集体个人货币确认表和集体个人产权确认表一致）*/
public class HouJTGRConfirmTable extends OrmLiteBaseTabActivity<DatabaseHelper>{

	public class KEY{
		/*确认表id*/
		public static final String in_id = "in_id";
		/** 类型*/
		public static final String in_type = "in_type";
		public static final String cid = "cid";
		public static final String hid = "hid";
		public static final String pid = "pid";
		
		public static final String c_status = "c_status";
	}
	
	public String id,cid,in_type,in_id,hid,pid,c_status;   
	
	public  List<CONFIRM_PERSON> confirmPersons = new ArrayList<CONFIRM_PERSON>();	
	private final int requestCode_person = 1;
	
	public HouJTGRConfirmTable activity;
	public HouseConfirm entity;
  
	  /** 是否是新增确认信息*/
    boolean isAdd = true;
     
    private Button btn_title_right;
	
    private TextView text_levyarea_x5,
    
    //tab3
    text_afm_houseowner_x1,
    text_purpose_x6,
    text_afm_structure_x7,text_afm_structure_x8,text_afm_structure_x81,
    text_afm_structure_x84,text_afm_structure_x200,text_afm_structure_x201,    	

    text_ass_structure_x7,text_ass_structure_x8,
    text_ass_structure_x81,text_ass_structure_x84,
    text_ass_structure_x200,text_ass_structure_x201,
    
    //tab4
    text_maininquirer_x218;
    
    private EditText
    //tab1
    edit_houselocation_x4,edit_houseowner_x1,edit_purpose_x6,edit_structure_x7,edit_area_x9,
    edit_structure_x8,edit_area_x10,edit_structure_x81,edit_area_x184,edit_structure_x84, 
    edit_area_x188,edit_structure_x200,edit_area_x202,edit_structure_x201,edit_area_x203,
    edit_floornumber,edit_examineremark,edit_procedure,edit_propertynumber,edit_propertyuse, 
    edit_propertystructure,edit_propertyarea_x11,edit_propertynum,edit_propertyremark,
   
   // edit_name1,edit_realation1,edit_cardno1,edit_workunit1,edit_remark1, 
   // edit_name2,edit_realation2,edit_cardno2,edit_workunit2,edit_remark2,
    //tab2
    
    //tab3
   // edit_afm_houseowner_x1,
    
    
  //  edit_afm_purpose_x6,
    edit_afm_area_x9,edit_afm_area_x10,edit_afm_area_x184,
    edit_afm_area_x188,edit_afm_area_x202,   
    edit_afm_area_x203,edit_affirmnumber,edit_affirmremark,    	

    edit_ass_value_x14,edit_ass_value_x15,
    edit_ass_value_x16,edit_ass_value_x86,  
    edit_ass_value_x204,edit_ass_value_x205, 
//  edit_fdhouse_x42,edit_existhouse_x19,
    edit_assessremark,     	
    //tab4
    edit_changearea_x46,edit_changeremark,edit_remark;
    //edit_maininquirer_x218
    
    private ListView jtlist_confirm_main_tab2;
	public JTGRConfirmPersonAdapter adapter;
	
    AlertDialog statusDialog = null;
	
	 @SuppressWarnings("deprecation")
	public void onResume(){
		 super.onResume();
		 MobclickAgent.onResume(this);
	 }
	 
	 @SuppressWarnings("deprecation")
	public void onPause(){
		 super.onPause();
		 MobclickAgent.onPause(this);
	 }
	  
	    @Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.hou_jtgr_pro_mon_confirmtable);
			InitTabHost();
			initControl(); 
			operateIntent();
			loadingListPerson();
		}
	 
	
		 //屏蔽掉实体返回键
		 @Override
		 public boolean onKeyDown(int KeyCode,KeyEvent event){
			 if(KeyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()== 0 ){
				 return false;
			 }
			 return false;
		 }
		 
	/** 初始化选项卡*/
	@SuppressWarnings("deprecation")
	private void InitTabHost(){
	   TabHost tabHost = this.getTabHost();
	   LayoutInflater inflater = LayoutInflater.from(this);
			 
	   inflater.inflate(R.layout.hou_jtgr_pro_mon_confirmtable_tab1, tabHost.getTabContentView());
	   tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getTabItemView(inflater,0,"一")).setContent(R.id.hou_jtgr_pro_mon_confirmtable_tab1));
       tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_item_bg);
		 
       inflater.inflate(R.layout.hou_jtgr_pro_mon_confirmtable_tab2, tabHost.getTabContentView());
       tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getTabItemView(inflater,0,"二")).setContent(R.id.hou_jtgr_pro_mon_confirmtable_tab2));
	   tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_item_bg);
		 
       inflater.inflate(R.layout.hou_jtgr_pro_mon_confirmtable_tab3, tabHost.getTabContentView());
       tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getTabItemView(inflater,0,"三")).setContent(R.id.hou_jtgr_pro_mon_confirmtable_tab3));
	   tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab_item_bg);
		     
       inflater.inflate(R.layout.hou_jtgr_pro_mon_confirmtable_tab4, tabHost.getTabContentView());
       tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(getTabItemView(inflater,0,"四")).setContent(R.id.hou_jtgr_pro_mon_confirmtable_tab4));
       tabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.tab_item_bg); 		          
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
	
	
	/* 初始化控件 */
	private void initControl() {
        activity = this;
		
		initBtn(R.id.btn_tab2_new);
		btn_title_right = (Button) findViewById(R.id.btn_title_right);
		btn_title_right.setOnClickListener(new OnClick());
		findViewById(R.id.btn_title_left).setOnClickListener(new OnClick());
		
		//算单附件  按钮
		initBtn(R.id.btn_att_1);
		initBtn(R.id.btn_att_2);
		initBtn(R.id.btn_att_3);

		// tab1
		text_levyarea_x5 = (TextView) findViewById(R.id.text_levyarea_x5);
		
		edit_houselocation_x4 = (EditText) findViewById(R.id.edit_houselocation_x4);
		edit_houseowner_x1 = (EditText) findViewById(R.id.edit_houseowner_x1);
		edit_purpose_x6 = (EditText) findViewById(R.id.edit_purpose_x6);
		edit_structure_x7 = (EditText) findViewById(R.id.edit_structure_x7);
		edit_area_x9 = (EditText) findViewById(R.id.edit_area_x9);
		edit_structure_x8 = (EditText) findViewById(R.id.edit_structure_x8);
		edit_area_x10 = (EditText) findViewById(R.id.edit_area_x10);
		edit_structure_x81 = (EditText) findViewById(R.id.edit_structure_x81);
		edit_area_x184 = (EditText) findViewById(R.id.edit_area_x184);
		edit_structure_x84 = (EditText) findViewById(R.id.edit_structure_x84);
		edit_area_x188 = (EditText) findViewById(R.id.edit_area_x188);
		edit_structure_x200 = (EditText) findViewById(R.id.edit_structure_x200);
		edit_area_x202 = (EditText) findViewById(R.id.edit_area_x202);
		edit_structure_x201 = (EditText) findViewById(R.id.edit_structure_x201);
		edit_area_x203 = (EditText) findViewById(R.id.edit_area_x203);
		edit_floornumber = (EditText) findViewById(R.id.edit_floornumber);
		edit_examineremark = (EditText) findViewById(R.id.edit_examineremark);
		edit_procedure = (EditText) findViewById(R.id.edit_procedure);
		edit_propertynumber = (EditText) findViewById(R.id.edit_propertynumber);
		edit_propertyuse = (EditText) findViewById(R.id.edit_propertyuse);
		edit_propertystructure = (EditText) findViewById(R.id.edit_propertystructure);
		edit_propertyarea_x11 = (EditText) findViewById(R.id.edit_propertyarea_x11);
		edit_propertynum = (EditText) findViewById(R.id.edit_propertynum);
		edit_propertyremark = (EditText) findViewById(R.id.edit_propertyremark);
//		edit_name1 = (EditText) findViewById(R.id.edit_name1);
//		edit_realation1 = (EditText) findViewById(R.id.edit_realation1);
//		edit_cardno1 = (EditText) findViewById(R.id.edit_cardno1);
//		edit_workunit1 = (EditText) findViewById(R.id.edit_workunit1);
//		edit_remark1 = (EditText) findViewById(R.id.edit_remark1);
//		edit_name2 = (EditText) findViewById(R.id.edit_name2);
//		edit_realation2 = (EditText) findViewById(R.id.edit_realation2);
//		edit_cardno2 = (EditText) findViewById(R.id.edit_cardno2);
//		edit_workunit2 = (EditText) findViewById(R.id.edit_workunit2);
//		edit_remark2 = (EditText) findViewById(R.id.edit_remark2);
		// tab2
		
		//tab3
		
		text_purpose_x6 = (TextView) findViewById(R.id.text_purpose_x6);
		
		//edit_afm_houseowner_x1 = (EditText) findViewById(R.id.edit_afm_houseowner_x1);
		text_afm_houseowner_x1 = (TextView) findViewById(R.id.text_afm_houseowner_x1);
		
		
	//	edit_afm_purpose_x6 = (EditText) findViewById(R.id.edit_afm_purpose_x6);
		text_afm_structure_x7 = (TextView) findViewById(R.id.text_afm_structure_x7);
		edit_afm_area_x9 = (EditText) findViewById(R.id.edit_afm_area_x9);
		text_afm_structure_x8 = (TextView) findViewById(R.id.text_afm_structure_x8);
		edit_afm_area_x10 = (EditText) findViewById(R.id.edit_afm_area_x10);
		text_afm_structure_x81 = (TextView) findViewById(R.id.text_afm_structure_x81);
		edit_afm_area_x184 = (EditText) findViewById(R.id.edit_afm_area_x184);
		text_afm_structure_x84 = (TextView) findViewById(R.id.text_afm_structure_x84);
		edit_afm_area_x188 = (EditText) findViewById(R.id.edit_afm_area_x188);
		text_afm_structure_x200 = (TextView) findViewById(R.id.text_afm_structure_x200);
		edit_afm_area_x202 = (EditText) findViewById(R.id.edit_afm_area_x202);
		text_afm_structure_x201 = (TextView) findViewById(R.id.text_afm_structure_x201);
		edit_afm_area_x203 = (EditText) findViewById(R.id.edit_afm_area_x203);
		edit_affirmnumber = (EditText) findViewById(R.id.edit_affirmnumber);
		edit_affirmremark = (EditText) findViewById(R.id.edit_affirmremark);
		
		text_ass_structure_x7 = (TextView) findViewById(R.id.text_ass_structure_x7);
		edit_ass_value_x14 = (EditText) findViewById(R.id.edit_ass_value_x14);
		text_ass_structure_x8 = (TextView) findViewById(R.id.text_ass_structure_x8);
		edit_ass_value_x15 = (EditText) findViewById(R.id.edit_ass_value_x15);
		text_ass_structure_x81 = (TextView) findViewById(R.id.text_ass_structure_x81);
		edit_ass_value_x16 = (EditText) findViewById(R.id.edit_ass_value_x16);
		text_ass_structure_x84 = (TextView) findViewById(R.id.text_ass_structure_x84);
		edit_ass_value_x86 = (EditText) findViewById(R.id.edit_ass_value_x86);
		text_ass_structure_x200 = (TextView) findViewById(R.id.text_ass_structure_x200);
		edit_ass_value_x204 = (EditText) findViewById(R.id.edit_ass_value_x204);
		text_ass_structure_x201 = (TextView) findViewById(R.id.text_ass_structure_x201);
		edit_ass_value_x205 = (EditText) findViewById(R.id.edit_ass_value_x205);
//		edit_fdhouse_x42 = (EditText) findViewById(R.id.edit_fdhouse_x42);
//		edit_existhouse_x19 = (EditText) findViewById(R.id.edit_existhouse_x19);
		edit_assessremark = (EditText) findViewById(R.id.edit_assessremark);
		// tab4
		edit_changearea_x46 = (EditText) findViewById(R.id.edit_changearea_x46);
		edit_changeremark = (EditText) findViewById(R.id.edit_changeremark);
		edit_remark = (EditText) findViewById(R.id.edit_remark);
		
	//	edit_maininquirer_x218 = (EditText) findViewById(R.id.edit_maininquirer_x218);
	    text_maininquirer_x218 = (TextView) findViewById(R.id.text_maininquirer_x218);
		
		jtlist_confirm_main_tab2 = (ListView) this.findViewById(R.id.jtlist_confirm_main_tab2);
	 
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
	    
	    c_status = intent.getStringExtra(KEY.c_status);
		
		in_id=id;
		
		cid = c_id;
		
		if (StringHelper.isNullOrEmpty(in_id)) {
			isAdd = true;
			entity = new HouseConfirm();
			entity.setHid(hid);
			entity.setStatus(1);
			entity.setX218(LoginBus.getLogin(this).getREALNAME());
			setEntityToUI(entity);
		} else {
			isAdd = false;
			entity = new HouseConfirm();
			entity.setX218(LoginBus.getLogin(this).getREALNAME());
			HouJTGRConfirmAsyncTask task = new HouJTGRConfirmAsyncTask(activity);
			task.execute(id);
			setEntityToUI(entity);
		}
	}

	Spinner initSp(int id, String enmucode, boolean allowEmptyRow) throws MyException {
		Spinner sp = (Spinner) findViewById(id);
		PublicBus.spinnerEnumBinding(sp, enmucode, allowEmptyRow, getHelper(),this);
		return sp;
	}

	

	private HouseConfirm getEntity() {
		if (isAdd) {
			entity.setCreator(LoginBus.getLogin(this).getREALNAME());
			// entity.setCREDATE(new Date());
		}
		
       
		// entity.setUSERNAME(LoginBus.getLogin(this).getUsername());
		// entity.setSTATUS("0");
		// entity.setMODIFIER(LoginBus.getLogin(this).getREALNAME());
		// entity.setMODIFYDATE(new Date());

		// tab1
		entity.setX4(edit_houselocation_x4.getText().toString());
		
		entity.setX1(edit_houseowner_x1.getText().toString());
		
		entity.setX6(edit_purpose_x6.getText().toString());
		entity.setX7(edit_structure_x7.getText().toString());
		entity.setX9(StringToDouble(edit_area_x9.getText().toString()));
		entity.setX8(edit_structure_x8.getText().toString());
		entity.setX10(StringToDouble(edit_area_x10.getText().toString()));
		entity.setX81(edit_structure_x81.getText().toString());
		entity.setX184(StringToDouble(edit_area_x184.getText().toString()));
		entity.setX84(edit_structure_x84.getText().toString());
		entity.setX188(StringToDouble(edit_area_x188.getText().toString()));
		entity.setX200(edit_structure_x200.getText().toString());
		entity.setX202(StringToDouble(edit_area_x202.getText().toString()));
		entity.setX201(edit_structure_x201.getText().toString());
		entity.setX203(StringToDouble(edit_area_x203.getText().toString()));
		entity.setC1(edit_floornumber.getText().toString());
		entity.setC2(edit_examineremark.getText().toString());
		entity.setC3(edit_procedure.getText().toString());
		entity.setC4(edit_propertynumber.getText().toString());
		entity.setC5(edit_propertyuse.getText().toString());
		entity.setC6(edit_propertystructure.getText().toString());
		entity.setX11(StringToDouble(edit_propertyarea_x11.getText().toString()));
		entity.setC8(edit_propertynum.getText().toString());
		entity.setC9(edit_propertyremark.getText().toString());
		//X5=X9+X10+X184+X188+X202+X203+X11
	//	entity.setX5(StringToDouble(edit_levyarea_x5.getText().toString()));
		
//		entity.setEdit_name1(edit_name1.getText().toString());
//		entity.setEdit_realation1(edit_realation1.getText().toString());
//		entity.setEdit_cardno1(edit_cardno1.getText().toString());
//		entity.setEdit_workunit1(edit_workunit1.getText().toString());
//		entity.setEdit_remark1(edit_remark1.getText().toString());
//		entity.setEdit_name2(edit_name2.getText().toString());
//		entity.setEdit_realation2(edit_realation2.getText().toString());
//		entity.setEdit_cardno2(edit_cardno2.getText().toString());
//		entity.setEdit_workunit2(edit_workunit2.getText().toString());
//		entity.setEdit_remark2(edit_remark2.getText().toString());
		// tab2
		
		//tab3
	//	entity.setX1(text_afm_houseowner_x1.getText().toString());
		
	//	entity.setX6(edit_afm_purpose_x6.getText().toString());
		entity.setA1(StringToDouble(edit_afm_area_x9.getText().toString()));
		entity.setA2(StringToDouble(edit_afm_area_x10.getText().toString()));
		entity.setA3(StringToDouble(edit_afm_area_x184.getText().toString()));
		entity.setA4(StringToDouble(edit_afm_area_x188.getText().toString()));
		entity.setA5(StringToDouble(edit_afm_area_x202.getText().toString()));
		entity.setA6(StringToDouble(edit_afm_area_x203.getText().toString()));
		entity.setC10(edit_affirmnumber.getText().toString());
		entity.setC11(edit_affirmremark.getText().toString());
		
		entity.setX14(StringToDouble(edit_ass_value_x14.getText().toString()));
		entity.setX15(StringToDouble(edit_ass_value_x15.getText().toString()));
		entity.setX16(StringToDouble(edit_ass_value_x16.getText().toString()));
		entity.setX86(StringToDouble(edit_ass_value_x86.getText().toString()));
		entity.setX204(StringToDouble(edit_ass_value_x204.getText().toString()));
		entity.setX205(StringToDouble(edit_ass_value_x205.getText().toString()));
//		entity.setEdit_fdhouse_x42(edit_fdhouse_x42.getText().toString());
//		entity.setEdit_existhouse_x19(edit_existhouse_x19.getText().toString());
		entity.setC12(edit_assessremark.getText().toString());
		// tab4
		entity.setX46(StringToDouble(edit_changearea_x46.getText().toString()));
		entity.setC35(edit_changeremark.getText().toString());
		entity.setC26(edit_remark.getText().toString());
		//entity.setX218(edit_maininquirer_x218.getText().toString());
		
		entity.setX218(LoginBus.getLogin(this).getREALNAME());
		
		return entity;

	}

	
	private Double StringToDouble(String text){
		if(StringHelper.isNullOrEmpty(text)){
			return Double.parseDouble("0.00");
		}else{
			return Double.parseDouble(text);
		}
	}
	
	
	

	public void loadingListPerson(){
		adapter=new JTGRConfirmPersonAdapter(this,confirmPersons);	 
		jtlist_confirm_main_tab2.setAdapter(adapter);	
		jtlist_confirm_main_tab2.setOnItemClickListener(new OnItemClick_list()); 
	 	
	}
	
	public void loadingPersonData(){
	//	 Intent intent = this.getIntent(); 
		 
		 FamilyRegListAsyncTask task = new FamilyRegListAsyncTask(activity);
		 task.execute();
	}
	class OnItemClick_list implements OnItemClickListener{			
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			//注：因为增加了一个加载项，所以最后一项的索引刚好等于已加载项的数量
//			int loadedNum = adapter.getCount();//得到已经加载项的数量
//			if(loadedNum > position){				
				//点击的是数据项
				//String householdid = ((TextView) view.findViewById(R.id.txt_id)).getText().toString();
				//打开分户信息页面
				Intent intent = new Intent();
				intent.setClass(activity, JTGRConfirmPerson.class);
				intent.putExtra(JTGRConfirmPerson.KEY.c_status, c_status);
				
				Bundle mBundle = new Bundle();  
				mBundle.putSerializable(JTGRConfirmPerson.KEY.JTGRconfirmPersonIndex, (Serializable) view.getTag()); 
            	intent.putExtras(mBundle); 
			//	startActivity(intent);
				activity.startActivityForResult(intent,1);
//			}else{
//				if(GlobalVar.footerInfo[2].equals(txt_list_footer.getText().toString()))
//					loadListPageData();
//			}
		}
	}
	
	@Override
	protected void onDestroy() {
		confirmPersons.clear();		
		super.onDestroy();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		case requestCode_person:
			result_person(resultCode, data);
			break;
		default:
		//	RequestCode_photo(requestCode,resultCode, data);
			break;
		}				
	}
	
	private void result_person(int resultCode, Intent data){
		if(resultCode!=RESULT_OK)
			return;		
		loadingListPerson();
	}
	
	
	
	/** 将集体个人 产权调换房屋调查确认表的信息展现到UI */
	public void setEntityToUI(HouseConfirm entity) {
		// tab1
		edit_houselocation_x4.setText(entity.getX4());
		edit_houseowner_x1.setText(entity.getX1());
		edit_purpose_x6.setText(entity.getX6());
		edit_structure_x7.setText(entity.getX7());
		edit_area_x9.setText(entity.getX9()+"");
		edit_structure_x8.setText(entity.getX8());
		edit_area_x10.setText(entity.getX10()+"");
		edit_structure_x81.setText(entity.getX81());
		edit_area_x184.setText(entity.getX184()+"");
		edit_structure_x84.setText(entity.getX84());
		edit_area_x188.setText(entity.getX188()+"");
		edit_structure_x200.setText(entity.getX200());
		edit_area_x202.setText(entity.getX202()+"");
		edit_structure_x201.setText(entity.getX201());
		edit_area_x203.setText(entity.getX203()+"");
		edit_floornumber.setText(entity.getC1());
		edit_examineremark.setText(entity.getC2());
		edit_procedure.setText(entity.getC3());
		edit_propertynumber.setText(entity.getC4());
		edit_propertyuse.setText(entity.getC5());
		edit_propertystructure.setText(entity.getC6());
		edit_propertyarea_x11.setText(entity.getX11()+"");
		edit_propertynum.setText(entity.getC8());
		edit_propertyremark.setText(entity.getC9());
		text_levyarea_x5.setText(entity.getX5_10()+"");
		// tab2
		
		//tab3
		text_afm_houseowner_x1.setText(entity.getX1());
		
		text_purpose_x6.setText(entity.getX6());
		
		text_afm_structure_x7.setText(entity.getX7());
		edit_afm_area_x9.setText(entity.getA1()+"");
		text_afm_structure_x8.setText(entity.getX8());
		edit_afm_area_x10.setText(entity.getA2()+"");
		text_afm_structure_x81.setText(entity.getX81());
		edit_afm_area_x184.setText(entity.getA3()+"");
		text_afm_structure_x84.setText(entity.getX84());
		edit_afm_area_x188.setText(entity.getA4()+"");
		text_afm_structure_x200.setText(entity.getX200());
		edit_afm_area_x202.setText(entity.getA5()+"");
		text_afm_structure_x201.setText(entity.getX201());
		edit_afm_area_x203.setText(entity.getA6()+"");
		edit_affirmnumber.setText(entity.getC10());
		edit_affirmremark.setText(entity.getC11());
		
		text_ass_structure_x7.setText(entity.getX7());
		edit_ass_value_x14.setText(entity.getX14()+"");
		text_ass_structure_x8.setText(entity.getX8());
		edit_ass_value_x15.setText(entity.getX15()+"");
		text_ass_structure_x81.setText(entity.getX81());
		edit_ass_value_x16.setText(entity.getX16()+"");
		text_ass_structure_x84.setText(entity.getX84());
		edit_ass_value_x86.setText(entity.getX86()+"");
		text_ass_structure_x200.setText(entity.getX200());
		edit_ass_value_x204.setText(entity.getX204()+"");
		text_ass_structure_x201.setText(entity.getX201());
		edit_ass_value_x205.setText(entity.getX205()+"");
//		edit_fdhouse_x42.setText(entity.getEdit_fdhouse_x42());
//		edit_existhouse_x19.setText(entity.getEdit_existhouse_x19());
		edit_assessremark.setText(entity.getC12());
		// tab4
		edit_changearea_x46.setText(entity.getX46()+"");
		edit_changeremark.setText(entity.getC35());
		edit_remark.setText(entity.getC26());
	//	edit_maininquirer_x218.setText(entity.getX218());
		
		text_maininquirer_x218.setText(LoginBus.getLogin(this).getREALNAME());
		
		if(!StringHelper.isNullOrEmpty(entity.getId())){
			 id=entity.getId();
			 loadingPersonData();
		}
		 
		if(entity.getStatus()!=null&&entity.getStatus()==2){
			btn_title_right.setVisibility(View.INVISIBLE);
			findViewById(R.id.btn_tab2_new).setVisibility(View.GONE); 
		}
	}
	
	

	class OnClick implements View.OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_tab2_new:
				Onclick_btn_tab2_new();
				break;			
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
			intent.putExtra(HouConfirmAttListA.KEY.c_status, String.valueOf(entity.getStatus()));
			intent.putExtra(HouConfirmAttListA.KEY.hid, entity.getHid());
			intent.putExtra(HouConfirmAttListA.KEY.pid, entity.getPid());
			intent.putExtra(HouConfirmAttListA.KEY.temptype, "0");
			activity.startActivityForResult(intent,1);
			}
		}
		void Onclick_btn_att_2() {
			if(newactivity()){
			Intent intent = new Intent(activity, HouConfirmAttListA.class);
			intent.putExtra(HouConfirmAttListA.KEY.c_status, String.valueOf(entity.getStatus()));
			intent.putExtra(HouConfirmAttListA.KEY.hid, entity.getHid());
			intent.putExtra(HouConfirmAttListA.KEY.pid, entity.getPid());
			intent.putExtra(HouConfirmAttListA.KEY.temptype, "1");
			activity.startActivityForResult(intent,1);
		}
	}
		void Onclick_btn_att_3() {
			if(newactivity()){
			Intent intent = new Intent(activity, HouConfirmAttListA.class);
			intent.putExtra(HouConfirmAttListA.KEY.c_status, String.valueOf(entity.getStatus()));
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
		
		
		
		void Onclick_btn_tab2_new(){
			Intent intent=new Intent();
			intent.setClass(activity, JTGRConfirmPerson.class);
			Bundle mBundle = new Bundle();  
			CONFIRM_PERSON person=	new CONFIRM_PERSON();
			person.setConfirmid(id);
			mBundle.putSerializable(JTGRConfirmPerson.KEY.JTGRconfirmPersonIndex, (Serializable)person); 
        	intent.putExtras(mBundle); 
			startActivityForResult(intent, requestCode_person);
		}
		

		void Onclick_btn_title_right(){	
			getEntity();
			String result = CONFIRM_JTGR_PRO_MON_Bus.check(entity);
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
				
//			   @Override  
//			  public void onClick(DialogInterface dialog, int which) {  
//			    	if(entity.getStatus()!=null){
//			    	entity.setStatus(which+1);
//			    	}else{
//			    		entity.setStatus(1);
//			    	}
//			    
//			    }  
//			    }) ;

			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					try { 
						HouseConfirmJTGRSaveAsyncTask task = new HouseConfirmJTGRSaveAsyncTask(activity);
						task.execute("");
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
				HouJTGRConfirmTable.this.finish();
				return;
			}
			// 新增集体个人房屋调查确认表信息时，按下返回，如果必填项姓名或证件号不为空则进行关闭确认
			if (isAdd) {
				String houselocation = edit_houselocation_x4.getText()
						.toString().trim();
				String houseowner = edit_houseowner_x1.getText().toString()
						.trim();
				if ((!houselocation.equals("")) || (!houseowner.equals(""))) {
					new AlertDialog.Builder(HouJTGRConfirmTable.this)
							.setMessage("信息未保存，确认要关闭？")
							.setPositiveButton("确定", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									HouJTGRConfirmTable.this.finish();
								}
							}).setNegativeButton("取消", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).create().show();
				} else {
					HouJTGRConfirmTable.this.finish();
				}
			} else {
				HouJTGRConfirmTable.this.finish();
			}
		}

	}
}
