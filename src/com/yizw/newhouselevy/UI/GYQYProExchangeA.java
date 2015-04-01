package com.yizw.newhouselevy.UI;

import java.text.DecimalFormat;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.TabHost;
import android.widget.TextView;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseTabActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.GYQYProAsyncTask;
import com.yizw.newhouselevy.AsyncTask.GYQYProSaveAsyncTask;
import com.yizw.newhouselevy.Business.HOU_GYQY_PROEXCHANGE_Bus;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HouseConfirm;
import com.yizw.newhouselevy.Entity.HouseList;

/**分户--国有企业--产权--补偿计算单*/
public class GYQYProExchangeA extends OrmLiteBaseTabActivity<DatabaseHelper>{

	public class KEY{
		/** 补偿计算单id*/
		public static final String in_id = "in_id";
		/** 补偿计算单类型*/
		public static final String in_type = "in_type";
		
		public static final String in_hid="in_hid";
		public static final String in_pid="in_pid";
		public static final String c_status="c_status";
		
	}
	
	//3月18日新需求，国有企业产权、国有企业货币、集体企业产权、集体企业货币，X107、X108、X109调整为手动录入
	private final int requestcode_att1=10;
	private final int requestcode_att2=11;
	private final int requestcode_att3=12;
	
	public GYQYProExchangeA activity;
	
	public HouseList entity;
	public HouseConfirm c;
	
	public HouseList gyqyproexchange;
	
	  /** 是否是新增确认信息*/
    boolean isAdd = true;
    public String id,in_id,hid,pid,c_status;  
    private Button btn_title_right;
	
    private EditText 
    //tab1
    edit_cardno_x2,edit_linktel_x3,
    edit_area_x9, edit_area_x10, edit_area_x184,edit_area_x188,edit_area_x202,
    edit_area_x203,edit_address_x14, edit_area_x15,
    //tab2
    edit_value_x16, edit_value_x17,edit_value_x20,edit_transition_x21, 
    edit_value_x22,   
    
    //tab3
     edit_value_x24,edit_cost_x187, edit_money_x39,
    edit_money_x168,edit_money_x169,
    
  //3月18日新需求，国有企业产权、国有企业货币、集体企业产权、集体企业货币，X107、X108、X109调整为手动录入
    edit_cost_x107,edit_cost_x108,edit_cost_x109,
    
    edit_e111,
    
    //tab4 
    edit_area_x152,edit_value_x153,edit_area_x155,edit_value_x156,edit_area_x158,
    edit_value_x159,edit_area_x212, edit_value_x215,edit_area_x213,edit_value_x216,
    edit_area_x214,edit_value_x217,
    
    edit_value_x270,edit_value_x271,
    edit_value_x272,edit_value_x273,
    edit_value_x274,edit_value_x275;
    
    private TextView
    //tab1	
    text_contractno_x185,text_maininquier_x218,text_levyname_x1,text_legalperson_x186, 
   // text_cardno_x2,text_linktel_x3,
    text_levyaddress_x4,text_housetotalarea_x5,text_purpose_x6, text_structure_x7_1,
    text_structure_x8_1,text_structure_x81_1,text_structure_x84_1, text_structure_x200_1,
    text_structure_x201_1,text_permitarea_x11,text_unregisterarea_x12, text_legalarea_x13,
    //tab2
    text_structure_x7_2, text_structure_x8_2,text_structure_x81_2, text_structure_x84_2, text_structure_x200_2,
    text_structure_x201_2, text_structure_x7_3,text_structure_x8_3, text_structure_x81_3, text_structure_x84_3,
    text_structure_x200_3, text_structure_x201_3,text_value_x18,text_value_x19,
    //tab3
  //  text_area_x13_010,
    text_value_x25,
    //tab4  	
    text_structure_x7_4, text_value_x59,text_structure_x8_4,	
    text_value_x60,text_structure_x81_4,text_allowance_x61,
    text_structure_x84_4,text_allowance_x68,text_structure_x200_4,
    text_allowance_x214, text_structure_x201_4,text_allowance_x217, 
    text_totallowance_x62,
    
    text_structure_x7_5,text_value_x276,text_structure_x8_5,text_value_x277,
    text_structure_x81_5,text_value_x278,
    text_structure_x84_5,text_value_x279,text_structure_x200_5,
    text_value_x280,text_structure_x201_5,text_value_x281,text_cost_x282,
    
    
    text_area_x152,text_area_x155,text_area_x158,text_area_x212,text_area_x213,
    text_area_x214,
    
    
    text_totallowance_x63,text_totallowance_x52,text_totallowance_x64;    

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
		setContentView(R.layout.hou_gyqy_proexchange);
		InitTabHost();
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
		 
     /* 初始化选项卡*/
	 @SuppressWarnings("deprecation")
	 private void InitTabHost(){
	   TabHost tabHost = this.getTabHost();
	   LayoutInflater inflater = LayoutInflater.from(this);
			 
	   inflater.inflate(R.layout.hou_gyqy_proexchange_tab1, tabHost.getTabContentView());
	   tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getTabItemView(inflater,0,"一")).setContent(R.id.hou_gyqy_proexchange_tab1));
	   tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_item_bg);
		 
	   inflater.inflate(R.layout.hou_gyqy_proexchange_tab2, tabHost.getTabContentView());
	   tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getTabItemView(inflater,0,"二")).setContent(R.id.hou_gyqy_proexchange_tab2));
	   tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_item_bg);
		 
	   inflater.inflate(R.layout.hou_gyqy_proexchange_tab3, tabHost.getTabContentView());
	   tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getTabItemView(inflater,0,"三")).setContent(R.id.hou_gyqy_proexchange_tab3));
	   tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab_item_bg);
		     
	   inflater.inflate(R.layout.hou_gyqy_proexchange_tab4, tabHost.getTabContentView());
	   tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(getTabItemView(inflater,0,"四")).setContent(R.id.hou_gyqy_proexchange_tab4));
	   tabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.tab_item_bg); 
	  }


	private View getTabItemView(LayoutInflater inflater, int img, String txt) {
		View view = inflater.inflate(R.layout.tab_item_view, null);
		ImageView imageview = (ImageView) view.findViewById(R.id.img);
		imageview.setVisibility(View.GONE);

		TextView textView = (TextView) view.findViewById(R.id.txt);
		textView.setText(txt);
		textView.setTextSize(18);

		return view;
	}

	/* 初始化控件 */
	private void initControl() {
		activity = this;

		btn_title_right = (Button) findViewById(R.id.btn_title_right);
		btn_title_right.setOnClickListener(new OnClick());
		findViewById(R.id.btn_title_left).setOnClickListener(new OnClick());
		
		//算单附件  按钮
		initBtn(R.id.btn_att_3);
		initBtn(R.id.btn_att_1);
		initBtn(R.id.btn_att_2);

		// tab1
		 
	    edit_cardno_x2 = (EditText) findViewById(R.id.edit_cardno_x2);
		edit_linktel_x3 = (EditText) findViewById(R.id.edit_linktel_x3);
		
		edit_area_x9 = (EditText) findViewById(R.id.edit_area_x9);
		edit_area_x10 = (EditText) findViewById(R.id.edit_area_x10);
		edit_area_x184 = (EditText) findViewById(R.id.edit_area_x184);
		edit_area_x188 = (EditText) findViewById(R.id.edit_area_x188);
		edit_area_x202 = (EditText) findViewById(R.id.edit_area_x202);
		edit_area_x203 = (EditText) findViewById(R.id.edit_area_x203);
		edit_address_x14 = (EditText) findViewById(R.id.edit_address_x14);
		edit_area_x15 = (EditText) findViewById(R.id.edit_area_x15);
		// tab2
		edit_value_x16 = (EditText) findViewById(R.id.edit_value_x16);
		edit_value_x17 = (EditText) findViewById(R.id.edit_value_x17);
		edit_value_x20 = (EditText) findViewById(R.id.edit_value_x20);
		edit_transition_x21 = (EditText) findViewById(R.id.edit_transition_x21);
		edit_value_x22 = (EditText) findViewById(R.id.edit_value_x22);
		// tab3
		edit_value_x24 = (EditText) findViewById(R.id.edit_value_x24);
		edit_cost_x187 = (EditText) findViewById(R.id.edit_cost_x187);
		edit_money_x39 = (EditText) findViewById(R.id.edit_money_x39);
		edit_money_x168 = (EditText) findViewById(R.id.edit_money_x168);
		edit_money_x169 = (EditText) findViewById(R.id.edit_money_x169);
		
		//3月18日新增
		edit_cost_x107 = (EditText) findViewById(R.id.edit_cost_x107);
		edit_cost_x108 = (EditText) findViewById(R.id.edit_cost_x108);
		edit_cost_x109 = (EditText) findViewById(R.id.edit_cost_x109);
		
		edit_e111 = (EditText) findViewById(R.id.edit_e111);
		
		// tab4
		
		
	    edit_area_x152 = (EditText) findViewById(R.id.edit_area_x152);
		edit_value_x153 = (EditText) findViewById(R.id.edit_value_x153);
		edit_area_x155 = (EditText) findViewById(R.id.edit_area_x155);
		edit_value_x156 = (EditText) findViewById(R.id.edit_value_x156);
		edit_area_x158 = (EditText) findViewById(R.id.edit_area_x158);
		edit_value_x159 = (EditText) findViewById(R.id.edit_value_x159);
		edit_area_x212 = (EditText) findViewById(R.id.edit_area_x212);
		edit_value_x215 = (EditText) findViewById(R.id.edit_value_x215);
		edit_area_x213 = (EditText) findViewById(R.id.edit_area_x213);
		edit_value_x216 = (EditText) findViewById(R.id.edit_value_x216);
		edit_area_x214 = (EditText) findViewById(R.id.edit_area_x214);
		edit_value_x217 = (EditText) findViewById(R.id.edit_value_x217);

		edit_value_x270 = (EditText) findViewById(R.id.edit_value_x270);
		edit_value_x271 = (EditText) findViewById(R.id.edit_value_x271);
		edit_value_x272 = (EditText) findViewById(R.id.edit_value_x272);
		edit_value_x273 = (EditText) findViewById(R.id.edit_value_x273);
		edit_value_x274 = (EditText) findViewById(R.id.edit_value_x274);
		edit_value_x275 = (EditText) findViewById(R.id.edit_value_x275);

		// tab1

		text_contractno_x185 = (TextView) findViewById(R.id.text_contractno_x185);
		text_maininquier_x218 = (TextView) findViewById(R.id.text_maininquier_x218);
		text_levyname_x1 = (TextView) findViewById(R.id.text_levyname_x1);
		text_legalperson_x186 = (TextView) findViewById(R.id.text_legalperson_x186);
	//	text_cardno_x2 = (TextView) findViewById(R.id.text_cardno_x2);
		text_levyaddress_x4 = (TextView) findViewById(R.id.text_levyaddress_x4);
	//	text_linktel_x3 = (TextView) findViewById(R.id.text_linktel_x3);
		text_housetotalarea_x5 = (TextView) findViewById(R.id.text_housetotalarea_x5);
		text_purpose_x6 = (TextView) findViewById(R.id.text_purpose_x6);
		text_structure_x7_1 = (TextView) findViewById(R.id.text_structure_x7_1);
		text_structure_x8_1 = (TextView) findViewById(R.id.text_structure_x8_1);
		text_structure_x81_1 = (TextView) findViewById(R.id.text_structure_x81_1);
		text_structure_x84_1 = (TextView) findViewById(R.id.text_structure_x84_1);
		text_structure_x200_1 = (TextView) findViewById(R.id.text_structure_x200_1);
		text_structure_x201_1 = (TextView) findViewById(R.id.text_structure_x201_1);
		text_permitarea_x11 = (TextView) findViewById(R.id.text_permitarea_x11);
		text_unregisterarea_x12 = (TextView) findViewById(R.id.text_unregisterarea_x12);
		text_legalarea_x13 = (TextView) findViewById(R.id.text_legalarea_x13);

		// tab2
		text_structure_x7_2 = (TextView) findViewById(R.id.text_structure_x7_2);
		text_structure_x8_2 = (TextView) findViewById(R.id.text_structure_x8_2);
		text_structure_x81_2 = (TextView) findViewById(R.id.text_structure_x81_2);
		text_structure_x84_2 = (TextView) findViewById(R.id.text_structure_x84_2);
		text_structure_x200_2 = (TextView) findViewById(R.id.text_structure_x200_2);
		text_structure_x201_2 = (TextView) findViewById(R.id.text_structure_x201_2);
		text_structure_x7_3 = (TextView) findViewById(R.id.text_structure_x7_3);
		text_structure_x8_3 = (TextView) findViewById(R.id.text_structure_x8_3);
		text_structure_x81_3 = (TextView) findViewById(R.id.text_structure_x81_3);
		text_structure_x84_3 = (TextView) findViewById(R.id.text_structure_x84_3);
		text_structure_x200_3 = (TextView) findViewById(R.id.text_structure_x200_3);
		text_structure_x201_3 = (TextView) findViewById(R.id.text_structure_x201_3);
		text_value_x18 = (TextView) findViewById(R.id.text_value_x18);
		text_value_x19 = (TextView) findViewById(R.id.text_value_x19);

		// tab3
	//	text_area_x13_010 = (TextView) findViewById(R.id.text_area_x13_010);
		text_value_x25 = (TextView) findViewById(R.id.text_value_x25);
		/*text_cost_x107 = (TextView) findViewById(R.id.text_cost_x107);
		text_cost_x108 = (TextView) findViewById(R.id.text_cost_x108);
		text_cost_x109 = (TextView) findViewById(R.id.text_cost_x109);*/

		// tab4
		text_structure_x7_4 = (TextView) findViewById(R.id.text_structure_x7_4);
		text_value_x59 = (TextView) findViewById(R.id.text_value_x59);
		text_structure_x8_4 = (TextView) findViewById(R.id.text_structure_x8_4);
		text_value_x60 = (TextView) findViewById(R.id.text_value_x60);
		text_structure_x81_4 = (TextView) findViewById(R.id.text_structure_x81_4);
		text_allowance_x61 = (TextView) findViewById(R.id.text_allowance_x61);
		text_structure_x84_4 = (TextView) findViewById(R.id.text_structure_x84_4);
		text_allowance_x68 = (TextView) findViewById(R.id.text_allowance_x68);
		text_structure_x200_4 = (TextView) findViewById(R.id.text_structure_x200_4);
		text_allowance_x214 = (TextView) findViewById(R.id.text_allowance_x214);
		text_structure_x201_4 = (TextView) findViewById(R.id.text_structure_x201_4);
		text_allowance_x217 = (TextView) findViewById(R.id.text_allowance_x217);
		text_totallowance_x62 = (TextView) findViewById(R.id.text_totallowance_x62);

		text_structure_x7_5 = (TextView) findViewById(R.id.text_structure_x7_5);
		text_value_x276 = (TextView) findViewById(R.id.text_value_x276);
		text_structure_x8_5 = (TextView) findViewById(R.id.text_structure_x8_5);
		text_value_x277 = (TextView) findViewById(R.id.text_value_x277);
		text_structure_x81_5 = (TextView) findViewById(R.id.text_structure_x81_5);
		text_value_x278 = (TextView) findViewById(R.id.text_value_x278);
		text_structure_x84_5 = (TextView) findViewById(R.id.text_structure_x84_5);
		text_value_x279 = (TextView) findViewById(R.id.text_value_x279);
		text_structure_x200_5 = (TextView) findViewById(R.id.text_structure_x200_5);
		text_value_x280 = (TextView) findViewById(R.id.text_value_x280);
		text_structure_x201_5 = (TextView) findViewById(R.id.text_structure_x201_5);
		text_value_x281 = (TextView) findViewById(R.id.text_value_x281);
		text_cost_x282 = (TextView) findViewById(R.id.text_cost_x282);
		
		text_area_x152 = (TextView) findViewById(R.id.text_area_x152);
		text_area_x155 = (TextView) findViewById(R.id.text_area_x155);
		text_area_x158 = (TextView) findViewById(R.id.text_area_x158);
		text_area_x212 = (TextView) findViewById(R.id.text_area_x212);
		text_area_x213 = (TextView) findViewById(R.id.text_area_x213);
		text_area_x214 = (TextView) findViewById(R.id.text_area_x214);

		text_totallowance_x63 = (TextView) findViewById(R.id.text_totallowance_x63);
		text_totallowance_x52 = (TextView) findViewById(R.id.text_totallowance_x52);
		text_totallowance_x64 = (TextView) findViewById(R.id.text_totallowance_x64);

	}

	private Button initBtn(int id) {
		Button btn = (Button) findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	
	/** 处理窗体传过来的数据 */
	private void operateIntent() {
		Intent intent = this.getIntent();
		in_id = intent.getStringExtra(KEY.in_id);
		hid = intent.getStringExtra(KEY.in_hid);	
		pid = intent.getStringExtra(KEY.in_pid);
		c_status = intent.getStringExtra(KEY.c_status);
		if (StringHelper.isNullOrEmpty(in_id)) {
			isAdd = true;
			entity = new HouseList();
	        c = new HouseConfirm();
			
//			GYQYProAsyncTask task = new GYQYProAsyncTask(activity);
//			task.execute(in_id);
			setEntityToUI(entity,c);
		} else {
			isAdd = false;
			entity = new HouseList();
			c = new HouseConfirm();
			
			GYQYProAsyncTask task = new GYQYProAsyncTask(activity);
			task.execute(in_id);
			setEntityToUI(entity,c);
			
			//屏蔽保存 不能修改
//			Button btn = (Button)findViewById(R.id.btn_title_right);
//			btn.setVisibility(View.INVISIBLE);
					
		}
	}

	/** 将国有企业 产权调换补偿计算单的信息展现到UI */
	public void setEntityToUI(HouseList entity,HouseConfirm c){
		
		DecimalFormat DouToStr = new DecimalFormat("0.00");
		// tab1
		entity.setHouseConfirm(c);
		
		edit_cardno_x2.setText(entity.getX2());
		edit_linktel_x3.setText(entity.getX3());

		edit_area_x9.setText(DouToStr.format(entity.getX9()));
		edit_area_x10.setText(DouToStr.format(entity.getX10()));
		edit_area_x184.setText(DouToStr.format(entity.getX184()));
		edit_area_x188.setText(DouToStr.format(entity.getX188()));
		edit_area_x202.setText(DouToStr.format(entity.getX202()));
		edit_area_x203.setText(DouToStr.format(entity.getX203()));
		edit_address_x14.setText(entity.getXs1());
		edit_area_x15.setText(DouToStr.format(entity.getX15()));
		// tab2
		edit_value_x16.setText(DouToStr.format(entity.getX16()));
		edit_value_x17.setText(DouToStr.format(entity.getXd4()));
		edit_value_x20.setText(DouToStr.format(entity.getX88()));
		edit_transition_x21.setText(entity.getX89()+"");
		edit_value_x22.setText(DouToStr.format(entity.getX95()));
		// tab3
		edit_value_x24.setText(DouToStr.format(entity.getXd2()));
		edit_cost_x187.setText(DouToStr.format(entity.getX187()));
		edit_money_x39.setText(DouToStr.format(entity.getXd3()));
		edit_money_x168.setText(DouToStr.format(entity.getX168()));
		edit_money_x169.setText(DouToStr.format(entity.getX169()));
		
		//3月18日新增
		edit_cost_x107.setText(DouToStr.format(entity.getX107()));
		edit_cost_x108.setText(DouToStr.format(entity.getX108()));
		edit_cost_x109.setText(DouToStr.format(entity.getX109()));
		
		edit_e111.setText(DouToStr.format(entity.getE111()));
		
		// tab4
	
		
	    edit_area_x152.setText(DouToStr.format(entity.getX152()));
		edit_value_x153.setText(DouToStr.format(entity.getX153()));
		edit_area_x155.setText(DouToStr.format(entity.getX155()));
		edit_value_x156.setText(DouToStr.format(entity.getX156()));
		edit_area_x158.setText(DouToStr.format(entity.getX158()));
		edit_value_x159.setText(DouToStr.format(entity.getX159()));
		edit_area_x212.setText(DouToStr.format(entity.getX212()));
		edit_value_x215.setText(DouToStr.format(entity.getX215()));
		edit_area_x213.setText(DouToStr.format(entity.getX213()));
		edit_value_x216.setText(DouToStr.format(entity.getX216()));
		edit_area_x214.setText(DouToStr.format(entity.getX214()));
		edit_value_x217.setText(DouToStr.format(entity.getX217()));

		edit_value_x270.setText(DouToStr.format(entity.getX270()));
		edit_value_x271.setText(DouToStr.format(entity.getX271()));
		edit_value_x272.setText(DouToStr.format(entity.getX272()));
		edit_value_x273.setText(DouToStr.format(entity.getX273()));
		edit_value_x274.setText(DouToStr.format(entity.getX274()));
		edit_value_x275.setText(DouToStr.format(entity.getX275()));

		// tab1
		text_contractno_x185.setText(entity.getX185());
		text_maininquier_x218.setText(c.getX218());
		text_levyname_x1.setText(c.getX1());
		text_legalperson_x186.setText(c.getX186());
	//	text_cardno_x2.setText(entity.getX2());
		text_levyaddress_x4.setText(c.getX4());
	//	text_linktel_x3.setText(entity.getX3());
		text_housetotalarea_x5.setText(DouToStr.format(entity.getX5_010()));
		text_purpose_x6.setText(c.getX6());
		text_structure_x7_1.setText(c.getX7());
		text_structure_x8_1.setText(c.getX8());
		text_structure_x81_1.setText(c.getX81());
		text_structure_x84_1.setText(c.getX84());
		text_structure_x200_1.setText(c.getX200());
		text_structure_x201_1.setText(c.getX201());
		text_permitarea_x11.setText(c.getX11()+"");
		text_unregisterarea_x12.setText(c.getX12()+"");
		text_legalarea_x13.setText(DouToStr.format(entity.getX13_010()));
		// tab2
		text_structure_x7_2.setText(c.getX7());
		text_structure_x8_2.setText(c.getX8());
		text_structure_x81_2.setText(c.getX81());
		text_structure_x84_2.setText(c.getX84());
		text_structure_x200_2.setText(c.getX200());
		text_structure_x201_2.setText(c.getX201());
		text_structure_x7_3.setText(c.getX7());
		text_structure_x8_3.setText(c.getX8());
		text_structure_x81_3.setText(c.getX81());
		text_structure_x84_3.setText(c.getX84());
		text_structure_x200_3.setText(c.getX200());
		text_structure_x201_3.setText(c.getX201());
		text_value_x18.setText(DouToStr.format(entity.getX18_010()));
		text_value_x19.setText(DouToStr.format(entity.getX19_010()));

		// tab3
		//text_area_x13_010.setText(DouToStr.format(entity.getX13_010()));
		text_value_x25.setText(DouToStr.format(entity.getX25_010()));
		/*text_cost_x107.setText(DouToStr.format(entity.getX107()));
		text_cost_x108.setText(DouToStr.format(entity.getX108()));
		text_cost_x109.setText(DouToStr.format(entity.getX109()));*/
		// tab4
		text_structure_x7_4.setText(c.getX7());
		text_value_x59.setText(DouToStr.format(entity.getX154()));
		text_structure_x8_4.setText(c.getX8());
		text_value_x60.setText(DouToStr.format(entity.getX157()));
		text_structure_x81_4.setText(c.getX81());
		text_allowance_x61.setText(DouToStr.format(entity.getX160()));
		text_structure_x84_4.setText(c.getX84());
		text_allowance_x68.setText(DouToStr.format(entity.getX223()));
		text_structure_x200_4.setText(c.getX200());
		text_allowance_x214.setText(DouToStr.format(entity.getX219()));
		text_structure_x201_4.setText(c.getX201());
		text_allowance_x217.setText(DouToStr.format(entity.getX220()));
		text_totallowance_x62.setText(DouToStr.format(entity.getX161()));

		text_structure_x7_5.setText(c.getX7());
		text_value_x276.setText(DouToStr.format(entity.getX276()));
		text_structure_x8_5.setText(c.getX8());
		text_value_x277.setText(DouToStr.format(entity.getX277()));
		text_structure_x81_5.setText(c.getX81());
		text_value_x278.setText(DouToStr.format(entity.getX278()));
		text_structure_x84_5.setText(c.getX84());
		text_value_x279.setText(DouToStr.format(entity.getX279()));
		text_structure_x200_5.setText(c.getX200());
		text_value_x280.setText(DouToStr.format(entity.getX280()));
		text_structure_x201_5.setText(c.getX201());
		text_value_x281.setText(DouToStr.format(entity.getX281()));
		text_cost_x282.setText(DouToStr.format(entity.getX282()));
		
		
		text_area_x152.setText(DouToStr.format(entity.getX152()));
		text_area_x155.setText(DouToStr.format(entity.getX155()));
		text_area_x158.setText(DouToStr.format(entity.getX158()));
		text_area_x212.setText(DouToStr.format(entity.getX212()));
		text_area_x213.setText(DouToStr.format(entity.getX213()));
		text_area_x214.setText(DouToStr.format(entity.getX214()));
		
       //注意，X63_010需要加上x107,x109,x109
		text_totallowance_x63.setText(DouToStr.format(entity.getX63_010()));
		text_totallowance_x52.setText(DouToStr.format(entity.getX52_010()));
		text_totallowance_x64.setText(DouToStr.format(entity.getX64_010()));
		
		//如果算单状态值为2（已提交），屏蔽保存按钮，使其 不能修改
		if (entity.getStatus()!=null && entity.getStatus() ==2) {
		Button btn = (Button)findViewById(R.id.btn_title_right);
		btn.setVisibility(View.INVISIBLE);
		}
		
	}

	private HouseList getEntity() {
		if (isAdd) {
			entity.setCreator(LoginBus.getLogin(this).getREALNAME());
			// entity.setCREDATE(new Date());
		}
		// tab1
		entity.setX2(edit_cardno_x2.getText().toString());
		entity.setX3(edit_linktel_x3.getText().toString());

		entity.setX9(StringToDouble(edit_area_x9.getText().toString()));
		entity.setX10(StringToDouble(edit_area_x10.getText().toString()));
		entity.setX184(StringToDouble(edit_area_x184.getText().toString()));
		entity.setX188(StringToDouble(edit_area_x188.getText().toString()));
		entity.setX202(StringToDouble(edit_area_x202.getText().toString()));
		entity.setX203(StringToDouble(edit_area_x203.getText().toString()));
		entity.setXs1(edit_address_x14.getText().toString());
		entity.setX15(StringToDouble(edit_area_x15.getText().toString()));
		// tab2
		entity.setX16(StringToDouble(edit_value_x16.getText().toString()));
		entity.setXd4(StringToDouble(edit_value_x17.getText().toString()));
		entity.setX88(StringToDouble(edit_value_x20.getText().toString()));
		entity.setX89(StringToInteger(edit_transition_x21.getText().toString()));
		entity.setX95(StringToDouble(edit_value_x22.getText().toString()));
		// tab3
		entity.setXd2(StringToDouble(edit_value_x24.getText().toString()));
		entity.setX187(StringToDouble(edit_cost_x187.getText().toString()));
		entity.setXd3(StringToDouble(edit_money_x39.getText().toString()));
		entity.setX168(StringToDouble(edit_money_x168.getText().toString()));
		entity.setX169(StringToDouble(edit_money_x169.getText().toString()));
		
		//3月18日新增
		entity.setX107(StringToDouble(edit_cost_x107.getText().toString()));
		entity.setX108(StringToDouble(edit_cost_x108.getText().toString()));
		entity.setX109(StringToDouble(edit_cost_x109.getText().toString()));
		
		entity.setE111(StringToDouble(edit_e111.getText().toString()));
		
		// tab4
		
		
		entity.setX152(StringToDouble(edit_area_x152.getText().toString()));
		entity.setX153(StringToDouble(edit_value_x153.getText().toString()));
		entity.setX155(StringToDouble(edit_area_x155.getText().toString()));
		entity.setX156(StringToDouble(edit_value_x156.getText().toString()));
		entity.setX158(StringToDouble(edit_area_x158.getText().toString()));
		entity.setX159(StringToDouble(edit_value_x159.getText().toString()));
		entity.setX212(StringToDouble(edit_area_x212.getText().toString()));
		entity.setX215(StringToDouble(edit_value_x215.getText().toString()));
		entity.setX213(StringToDouble(edit_area_x213.getText().toString()));
		entity.setX216(StringToDouble(edit_value_x216.getText().toString()));
		entity.setX214(StringToDouble(edit_area_x214.getText().toString()));
		entity.setX217(StringToDouble(edit_value_x217.getText().toString()));

		entity.setX270(StringToDouble(edit_value_x270.getText().toString()));
		entity.setX271(StringToDouble(edit_value_x271.getText().toString()));
		entity.setX272(StringToDouble(edit_value_x272.getText().toString()));
		entity.setX273(StringToDouble(edit_value_x273.getText().toString()));
		entity.setX274(StringToDouble(edit_value_x274.getText().toString()));
		entity.setX275(StringToDouble(edit_value_x275.getText().toString()));
		return entity;
	}

	
	private Double StringToDouble(String text) {
		if (StringHelper.isNullOrEmpty(text)) {
			return StringToDouble("0.00");
		} else {
			return Double.parseDouble(text);
		}
	}

	private Integer StringToInteger(String text) {
		if (StringHelper.isNullOrEmpty(text)) {
			return Integer.parseInt("0");
		} else {
			return Integer.parseInt(text);
		}
	}
	
	
	//3月18日新需求，国有企业产权、国有企业货币、集体企业产权、集体企业货币，X107、X108、X109调整为手动录入
	 // @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case requestcode_att3:
			requestCode_Att(resultCode,data,edit_cost_x107);
			break;
		case requestcode_att1:
			requestCode_Att(resultCode,data,edit_cost_x108);
			break;
		case requestcode_att2:
			requestCode_Att(resultCode,data,edit_cost_x109);
			break;
		default:
			// RequestCode_photo(requestCode,resultCode, data);
			break;
		}
	}

	 
	private void requestCode_Att(int resultCode, Intent data,
			TextView textView) {
		if(resultCode!=RESULT_OK)
			return;	 
		textView.setText(data.getStringExtra(HListAttList.KEY.RE_ALLMONEY));
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
			case R.id.btn_att_3:
				Onclick_btn_att_3();
				break;	
			case R.id.btn_att_1:
				Onclick_btn_att_1();
				break;	
			case R.id.btn_att_2:
				Onclick_btn_att_2();
				break;		
			default:
				break;
			}
		}
		
		void Onclick_btn_att_3() {
			Intent intent = new Intent(activity, HListAttList.class);
			intent.putExtra(HListAttList.KEY.listid, entity.getId());
			intent.putExtra(HListAttList.KEY.hid, entity.getHid());
			intent.putExtra(HListAttList.KEY.pid, entity.getPid());
			intent.putExtra(HListAttList.KEY.temptype, "2");
			intent.putExtra(HListAttList.KEY.h_status, String.valueOf(entity.getStatus()));
		//	activity.startActivityForResult(intent,requestcode_att3);
			activity.startActivityForResult(intent,1);
		}
		void Onclick_btn_att_1() {
			Intent intent = new Intent(activity, HListAttList.class);
			intent.putExtra(HListAttList.KEY.listid, entity.getId());
			intent.putExtra(HListAttList.KEY.hid, entity.getHid());
			intent.putExtra(HListAttList.KEY.pid, entity.getPid());;
			intent.putExtra(HListAttList.KEY.temptype, "0");
			intent.putExtra(HListAttList.KEY.h_status, String.valueOf(entity.getStatus()));
		//	activity.startActivityForResult(intent,requestcode_att1);
			activity.startActivityForResult(intent,1);
		}
		void Onclick_btn_att_2() {
			Intent intent = new Intent(activity, HListAttList.class);
			intent.putExtra(HListAttList.KEY.listid, entity.getId());
			intent.putExtra(HListAttList.KEY.hid, entity.getHid());
			intent.putExtra(HListAttList.KEY.pid, entity.getPid());
			intent.putExtra(HListAttList.KEY.temptype, "1");
			intent.putExtra(HListAttList.KEY.h_status, String.valueOf(entity.getStatus()));
		//	activity.startActivityForResult(intent,requestcode_att2);
			activity.startActivityForResult(intent,1);
		}

		void Onclick_btn_title_right(){	
			getEntity();
			String result = HOU_GYQY_PROEXCHANGE_Bus.check(entity);
			if (!result.equals("")) {
				DialogHelper.showConfirm(activity, result);
				return;
			} 
			showTempSaveDialog(null);
	       }
		
		void showTempSaveDialog(String message) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("国有企业产权算单提交或暂存");
		//	builder.setMessage(message);
			builder.setIcon(android.R.drawable.ic_dialog_info); 
			
			
			 if("1".equals(c_status)){
			 builder.setSingleChoiceItems(new String[] {"暂时保存"}, 0, new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {  
					    //算单状态值为1（暂存）或2（提交）
						 entity.setStatus(which+1);
						}  
					}) ;

				builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						try { 
							GYQYProSaveAsyncTask save = new GYQYProSaveAsyncTask(activity);
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
				
				
			}else{
				builder.setSingleChoiceItems(new String[] {"暂时保存","确认提交"}, 0, new DialogInterface.OnClickListener() {  
                    
					@Override  
					public void onClick(DialogInterface dialog, int which) {  
					    //算单状态值为1（暂存）或2（提交）
						 entity.setStatus(which+1);
						}  
					}) ;

				builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						try { 
							GYQYProSaveAsyncTask save = new GYQYProSaveAsyncTask(activity);
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
			
		}
   

		void Onclick_btn_title_left() {
			if (isAdd == false) {
				GYQYProExchangeA.this.finish();
				return;
			}
			// 新增国有企业 产权调换补偿计算单信息时，按下返回，如果必填项姓名或证件号不为空则进行关闭确认
			if (isAdd) {
				String levyname = text_levyname_x1.getText().toString().trim();
				String cardno = edit_cardno_x2.getText().toString().trim();

				if ((!levyname.equals("")) || (!cardno.equals(""))) {
					new AlertDialog.Builder(GYQYProExchangeA.this)
							.setMessage("信息未保存，确认要关闭？")
							.setPositiveButton("确定", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									GYQYProExchangeA.this.finish();
								}
							}).setNegativeButton("取消", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).create().show();
				} else {
					GYQYProExchangeA.this.finish();
				}
			} else {
				GYQYProExchangeA.this.finish();
			}
		}

	}
}