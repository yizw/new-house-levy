package com.yizw.newhouselevy.UI;

import java.text.DecimalFormat;

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
import com.yizw.newhouselevy.AsyncTask.JTGRMonAsyncTask;
import com.yizw.newhouselevy.AsyncTask.JTGRMonSaveAsyncTask;
import com.yizw.newhouselevy.Business.HOU_JTGR_MONETARY_Bus;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HouseConfirm;
import com.yizw.newhouselevy.Entity.HouseList;

/**分户--集体个人--货币--补偿计算单*/
public class JTGRMonetaryCoptA extends OrmLiteBaseTabActivity<DatabaseHelper>{

	public class KEY{
		/** 补偿计算单id*/
		public static final String in_id = "in_id";
		/** 补偿计算单类型*/
		public static final String in_type = "in_type";
		public static final String cid = "cid";
		
		public static final String in_hid="in_hid";
		public static final String in_pid="in_pid";
		public static final String c_status="c_status";
		
	}
	
	private final int requestcode_att1=10;
	private final int requestcode_att2=11;
	private final int requestcode_att3=12;
	
	public JTGRMonetaryCoptA activity;
	
	public HouseList entity;
	public HouseConfirm c;
//	public HouseAttDTO d;
	
	public HouseList jtgrmonetary;
	
	  /** 是否是新增确认信息*/
    boolean isAdd = true;
    public String id,in_id,hid,pid,c_status;   
    private Button btn_title_right;
	
    private EditText 
    //tab1
    edit_insidearea_x12,
    
    edit_cardno_x2,edit_linktel_x3,
    
    //tab2
    edit_area_x164,edit_area_x165,edit_area_x166,edit_area_x167,edit_area_x208,
    edit_area_x209,
    edit_cost_x88,edit_num_x89,edit_cost_x95,edit_cost_x171,
    //tab3
    edit_percent_x111,edit_value_xd1,edit_cost_x119,edit_cost_x120,    
    edit_value_xd3,edit_cost_x148,edit_num_x149,
    edit_cost_x168,edit_cost_x169,
    //tab4
    edit_area_x152,edit_area_x155,edit_area_x158,edit_area_x212,edit_area_x213,edit_area_x214,
    edit_value_x153,edit_value_x156,edit_value_x159,edit_value_x215,edit_value_x216,edit_value_x217,
          
    edit_value_x270, edit_value_x271, edit_value_x272,
    edit_value_x273,edit_value_x274, edit_value_x275;
	
    private TextView text_contractno_x185,text_maininquier_x218,text_levyname_x1,
    text_levyaddress_x4,text_housetotalarea_x5,text_purpose_x6,text_structure_x7_1,text_area_x9,text_structure_x8_1,  	
    text_area_x10,text_structure_x81_1,text_area_x184,text_structure_x84_1,text_area_x188,text_structure_x200_1,	
    text_area_x202,text_structure_x201_1,text_area_x203,text_legalarea_x11, 
    text_unregisterarea_x13,text_structure_x7_2,text_value_x14_1,text_structure_x8_2,text_value_x15_1,
    text_structure_x81_2,text_value_x16_1,text_structure_x84_2,text_value_x38_1,text_structure_x200_2,
    text_value_x204_1,text_structure_x201_2,text_value_x205_1,
    
   // text_cardno_x2,text_linktel_x3,
    
    //tab2
    text_structure_x7_3,text_value_x14_2,text_structure_x8_3,text_value_x15_2,
    text_structure_x81_3,text_value_x16_2,text_structure_x84_3,text_value_x86_2,
    text_structure_x200_3,text_value_x204_2,text_structure_x201_3,text_value_x205_2,
    
    text_value_x78_1,text_value_x80_1,text_value_x83_1,text_value_x87_1,text_value_x206_1,text_value_x207_1,
        
    text_area_x13_1,text_cost_x25,text_area_x13_2,text_cost_x30,text_cost_x107,
    text_cost_x108,text_cost_x109,
    //tab3
    text_structure_x7_4,text_value_x14_3,
    text_structure_x8_4,text_value_x15_3,
    text_structure_x81_4,text_value_x16_3,
    text_structure_x84_4,text_value_x86_3,
    text_structure_x200_4,text_value_x204_3,
    text_structure_x201_4,text_value_x205_3,
    
    text_area_x13_4,text_cost_x284 ,text_area_x46, text_cost_x48,text_cost_x150,text_cost_x52,
    text_area_x164,text_area_x165,text_area_x166,text_area_x167,text_area_x208,text_area_x209,
    text_percent_x111_2,text_percent_x111_3,text_percent_x111_4,text_percent_x111_5,text_percent_x111_6,
    text_value_x112,text_value_x113,text_value_x115,text_value_x118,text_value_x210,text_value_x211,
   
       
    //tab4
    text_structure_x7_5,text_structure_x8_5,text_structure_x81_5,
    text_structure_x84_5,text_structure_x200_5,text_structure_x201_5,
    
    text_cost_x161,    
    text_value_x154,text_value_x157,text_value_x160,text_value_x223,text_value_x219,text_value_x220,
    text_area_x152,text_area_x155,text_area_x158,text_area_x212,text_area_x213,text_area_x214,
    
    text_structure_x7_6,text_value_x276, text_structure_x8_6, text_value_x277,text_structure_x81_6, 
    text_value_x278, text_structure_x84_6,text_value_x279,text_structure_x200_6,text_value_x280,
    text_structure_x201_6,text_value_x281,text_cost_x282,
    
    text_cost_x63,text_cost_x53,text_cost_x64;
	
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
		setContentView(R.layout.hou_jtgr_monetary);
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
			 
		inflater.inflate(R.layout.hou_jtgr_monetary_tab1, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getTabItemView(inflater,0,"一")).setContent(R.id.hou_jtgr_monetary_tab1));
		tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_item_bg);
		 
		inflater.inflate(R.layout.hou_jtgr_monetary_tab2, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getTabItemView(inflater,0,"二")).setContent(R.id.hou_jtgr_monetary_tab2));
		tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_item_bg);
		 
		inflater.inflate(R.layout.hou_jtgr_monetary_tab3, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getTabItemView(inflater,0,"三")).setContent(R.id.hou_jtgr_monetary_tab3));
	    tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab_item_bg);
		     
		inflater.inflate(R.layout.hou_jtgr_monetary_tab4, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(getTabItemView(inflater,0,"四")).setContent(R.id.hou_jtgr_monetary_tab4));
		tabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.tab_item_bg); 
	 }


	@SuppressLint("InflateParams")
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

		edit_insidearea_x12 = (EditText) findViewById(R.id.edit_insidearea_x12);
		
		edit_cardno_x2 = (EditText) findViewById(R.id.edit_cardno_x2);
		edit_linktel_x3 = (EditText) findViewById(R.id.edit_linktel_x3);
		

//		text_cardno_x2 = (TextView) findViewById(R.id.text_cardno_x2);
//		text_linktel_x3 = (TextView) findViewById(R.id.text_linktel_x3);

		text_contractno_x185 = (TextView) findViewById(R.id.text_contractno_x185);
		text_maininquier_x218 = (TextView) findViewById(R.id.text_maininquier_x218);
		text_levyname_x1 = (TextView) findViewById(R.id.text_levyname_x1);
		text_levyaddress_x4 = (TextView) findViewById(R.id.text_levyaddress_x4);
		text_housetotalarea_x5 = (TextView) findViewById(R.id.text_housetotalarea_x5);
		text_purpose_x6 = (TextView) findViewById(R.id.text_purpose_x6);
		text_structure_x7_1 = (TextView) findViewById(R.id.text_structure_x7_1);
		text_area_x9 = (TextView) findViewById(R.id.text_area_x9);
		text_structure_x8_1 = (TextView) findViewById(R.id.text_structure_x8_1);
		text_area_x10 = (TextView) findViewById(R.id.text_area_x10);
		text_structure_x81_1 = (TextView) findViewById(R.id.text_structure_x81_1);
		text_area_x184 = (TextView) findViewById(R.id.text_area_x184);
		text_structure_x84_1 = (TextView) findViewById(R.id.text_structure_x84_1);
		text_area_x188 = (TextView) findViewById(R.id.text_area_x188);
		text_structure_x200_1 = (TextView) findViewById(R.id.text_structure_x200_1);
		text_area_x202 = (TextView) findViewById(R.id.text_area_x202);
		text_structure_x201_1 = (TextView) findViewById(R.id.text_structure_x201_1);
		text_area_x203 = (TextView) findViewById(R.id.text_area_x203);
		text_legalarea_x11 = (TextView) findViewById(R.id.text_legalarea_x11);
		text_unregisterarea_x13 = (TextView) findViewById(R.id.text_unregisterarea_x13);
		text_structure_x7_2 = (TextView) findViewById(R.id.text_structure_x7_2);
		text_value_x14_1 = (TextView) findViewById(R.id.text_value_x14_1);
		text_structure_x8_2 = (TextView) findViewById(R.id.text_structure_x8_2);
		text_value_x15_1 = (TextView) findViewById(R.id.text_value_x15_1);
		text_structure_x81_2 = (TextView) findViewById(R.id.text_structure_x81_2);
		text_value_x16_1 = (TextView) findViewById(R.id.text_value_x16_1);
		text_structure_x84_2 = (TextView) findViewById(R.id.text_structure_x84_2);
		text_value_x38_1 = (TextView) findViewById(R.id.text_value_x38_1);
		text_structure_x200_2 = (TextView) findViewById(R.id.text_structure_x200_2);
		text_value_x204_1 = (TextView) findViewById(R.id.text_value_x204_1);
		text_structure_x201_2 = (TextView) findViewById(R.id.text_structure_x201_2);
		text_value_x205_1 = (TextView) findViewById(R.id.text_value_x205_1);

		// tab2
		edit_area_x164 = (EditText) findViewById(R.id.edit_area_x164);
		edit_area_x165 = (EditText) findViewById(R.id.edit_area_x165);
		edit_area_x166 = (EditText) findViewById(R.id.edit_area_x166);
		edit_area_x167 = (EditText) findViewById(R.id.edit_area_x167);
		edit_area_x208 = (EditText) findViewById(R.id.edit_area_x208);
		edit_area_x209 = (EditText) findViewById(R.id.edit_area_x209);
		edit_cost_x88 = (EditText) findViewById(R.id.edit_cost_x88);
		edit_num_x89 = (EditText) findViewById(R.id.edit_num_x89);
		edit_cost_x95 = (EditText) findViewById(R.id.edit_cost_x95);
		edit_cost_x171 = (EditText) findViewById(R.id.edit_cost_x171);

		text_structure_x7_3 = (TextView) findViewById(R.id.text_structure_x7_3);
		text_value_x14_2 = (TextView) findViewById(R.id.text_value_x14_2);
		text_value_x78_1 = (TextView) findViewById(R.id.text_value_x78_1);
		text_structure_x8_3 = (TextView) findViewById(R.id.text_structure_x8_3);
		text_value_x15_2 = (TextView) findViewById(R.id.text_value_x15_2);
		text_value_x80_1 = (TextView) findViewById(R.id.text_value_x80_1);
		text_structure_x81_3 = (TextView) findViewById(R.id.text_structure_x81_3);
		text_value_x16_2 = (TextView) findViewById(R.id.text_value_x16_2);
		text_value_x83_1 = (TextView) findViewById(R.id.text_value_x83_1);
		text_structure_x84_3 = (TextView) findViewById(R.id.text_structure_x84_3);
		text_value_x86_2 = (TextView) findViewById(R.id.text_value_x86_2);
		text_value_x87_1 = (TextView) findViewById(R.id.text_value_x87_1);
		text_structure_x200_3 = (TextView) findViewById(R.id.text_structure_x200_3);
		text_value_x204_2 = (TextView) findViewById(R.id.text_value_x204_2);
		text_value_x206_1 = (TextView) findViewById(R.id.text_value_x206_1);
		text_structure_x201_3 = (TextView) findViewById(R.id.text_structure_x201_3);
		text_value_x205_2 = (TextView) findViewById(R.id.text_value_x205_2);
		text_value_x207_1 = (TextView) findViewById(R.id.text_value_x207_1);

		text_area_x13_1 = (TextView) findViewById(R.id.text_area_x13_1);
		text_cost_x25 = (TextView) findViewById(R.id.text_cost_x25);
		text_area_x13_2 = (TextView) findViewById(R.id.text_area_x13_2);
		text_cost_x30 = (TextView) findViewById(R.id.text_cost_x30);
		text_cost_x107 = (TextView) findViewById(R.id.text_cost_x107);
		text_cost_x108 = (TextView) findViewById(R.id.text_cost_x108);
		text_cost_x109 = (TextView) findViewById(R.id.text_cost_x109);

		// tab3
		edit_cost_x168 = (EditText) findViewById(R.id.edit_cost_x168);
		edit_cost_x169 = (EditText) findViewById(R.id.edit_cost_x169);
		edit_percent_x111 = (EditText) findViewById(R.id.edit_percent_x111);
		edit_cost_x119 = (EditText) findViewById(R.id.edit_cost_x119);
		edit_cost_x120 = (EditText) findViewById(R.id.edit_cost_x120);
		edit_cost_x148 = (EditText) findViewById(R.id.edit_cost_x148);
		edit_num_x149 = (EditText) findViewById(R.id.edit_num_x149);
		edit_value_xd1 = (EditText) findViewById(R.id.edit_value_xd1);
		edit_value_xd3 = (EditText) findViewById(R.id.edit_value_xd3);

		text_area_x13_4 = (TextView) findViewById(R.id.text_area_x13_4);
		text_cost_x284 = (TextView) findViewById(R.id.text_cost_x284);
		text_area_x46 = (TextView) findViewById(R.id.text_area_x46);
		text_cost_x48 = (TextView) findViewById(R.id.text_cost_x48);
		text_cost_x150 = (TextView) findViewById(R.id.text_cost_x150);
		text_cost_x52 = (TextView) findViewById(R.id.text_cost_x52);
		text_area_x164 = (TextView) findViewById(R.id.text_area_x164);
		text_area_x165 = (TextView) findViewById(R.id.text_area_x165);
		text_area_x166 = (TextView) findViewById(R.id.text_area_x166);
		text_area_x167 = (TextView) findViewById(R.id.text_area_x167);
		text_area_x208 = (TextView) findViewById(R.id.text_area_x208);
		text_area_x209 = (TextView) findViewById(R.id.text_area_x209);
		text_percent_x111_2 = (TextView) findViewById(R.id.text_percent_x111_2);
		text_percent_x111_3 = (TextView) findViewById(R.id.text_percent_x111_3);
		text_percent_x111_4 = (TextView) findViewById(R.id.text_percent_x111_4);
		text_percent_x111_5 = (TextView) findViewById(R.id.text_percent_x111_5);
		text_percent_x111_6 = (TextView) findViewById(R.id.text_percent_x111_6);
		text_value_x112 = (TextView) findViewById(R.id.text_value_x112);
		text_value_x113 = (TextView) findViewById(R.id.text_value_x113);
		text_value_x115 = (TextView) findViewById(R.id.text_value_x115);
		text_value_x118 = (TextView) findViewById(R.id.text_value_x118);
		text_value_x210 = (TextView) findViewById(R.id.text_value_x210);
		text_value_x211 = (TextView) findViewById(R.id.text_value_x211);

		text_structure_x7_4 = (TextView) findViewById(R.id.text_structure_x7_4);
		text_value_x14_3 = (TextView) findViewById(R.id.text_value_x14_3);
		text_structure_x8_4 = (TextView) findViewById(R.id.text_structure_x8_4);
		text_value_x15_3 = (TextView) findViewById(R.id.text_value_x15_3);
		text_structure_x81_4 = (TextView) findViewById(R.id.text_structure_x81_4);
		text_value_x16_3 = (TextView) findViewById(R.id.text_value_x16_3);
		text_structure_x84_4 = (TextView) findViewById(R.id.text_structure_x84_4);
		text_value_x86_3 = (TextView) findViewById(R.id.text_value_x86_3);
		text_structure_x200_4 = (TextView) findViewById(R.id.text_structure_x200_4);
		text_value_x204_3 = (TextView) findViewById(R.id.text_value_x204_3);
		text_value_x210 = (TextView) findViewById(R.id.text_value_x210);
		text_structure_x201_4 = (TextView) findViewById(R.id.text_structure_x201_4);
		text_value_x205_3 = (TextView) findViewById(R.id.text_value_x205_3);

		// tab4
		edit_area_x152 = (EditText) findViewById(R.id.edit_area_x152);
		edit_area_x155 = (EditText) findViewById(R.id.edit_area_x155);
		edit_area_x158 = (EditText) findViewById(R.id.edit_area_x158);
		edit_area_x212 = (EditText) findViewById(R.id.edit_area_x212);
		edit_area_x213 = (EditText) findViewById(R.id.edit_area_x213);
		edit_area_x214 = (EditText) findViewById(R.id.edit_area_x214);
		edit_value_x153 = (EditText) findViewById(R.id.edit_value_x153);
		edit_value_x156 = (EditText) findViewById(R.id.edit_value_x156);
		edit_value_x159 = (EditText) findViewById(R.id.edit_value_x159);
		edit_value_x215 = (EditText) findViewById(R.id.edit_value_x215);
		edit_value_x216 = (EditText) findViewById(R.id.edit_value_x216);
		edit_value_x217 = (EditText) findViewById(R.id.edit_value_x217);

		edit_value_x270 = (EditText) findViewById(R.id.edit_value_x270);
		edit_value_x271 = (EditText) findViewById(R.id.edit_value_x271);
		edit_value_x272 = (EditText) findViewById(R.id.edit_value_x272);
		edit_value_x273 = (EditText) findViewById(R.id.edit_value_x273);
		edit_value_x274 = (EditText) findViewById(R.id.edit_value_x274);
		edit_value_x275 = (EditText) findViewById(R.id.edit_value_x275);

		text_cost_x161 = (TextView) findViewById(R.id.text_cost_x161);
		text_value_x154 = (TextView) findViewById(R.id.text_value_x154);
		text_value_x157 = (TextView) findViewById(R.id.text_value_x157);
		text_value_x160 = (TextView) findViewById(R.id.text_value_x160);
		text_value_x223 = (TextView) findViewById(R.id.text_value_x223);
		text_value_x219 = (TextView) findViewById(R.id.text_value_x219);
		text_value_x220 = (TextView) findViewById(R.id.text_value_x220);

		text_area_x152 = (TextView) findViewById(R.id.text_area_x152);
		text_area_x155 = (TextView) findViewById(R.id.text_area_x155);
		text_area_x158 = (TextView) findViewById(R.id.text_area_x158);
		text_area_x212 = (TextView) findViewById(R.id.text_area_x212);
		text_area_x213 = (TextView) findViewById(R.id.text_area_x213);
		text_area_x214 = (TextView) findViewById(R.id.text_area_x214);

		text_structure_x7_5 = (TextView) findViewById(R.id.text_structure_x7_5);
		text_structure_x8_5 = (TextView) findViewById(R.id.text_structure_x8_5);
		text_structure_x81_5 = (TextView) findViewById(R.id.text_structure_x81_5);
		text_structure_x84_5 = (TextView) findViewById(R.id.text_structure_x84_5);
		text_structure_x200_5 = (TextView) findViewById(R.id.text_structure_x200_5);
		text_structure_x201_5 = (TextView) findViewById(R.id.text_structure_x201_5);

		text_structure_x7_6 = (TextView) findViewById(R.id.text_structure_x7_6);
		text_value_x276 = (TextView) findViewById(R.id.text_value_x276);
		text_structure_x8_6 = (TextView) findViewById(R.id.text_structure_x8_6);
		text_value_x277 = (TextView) findViewById(R.id.text_value_x277);
		text_structure_x81_6 = (TextView) findViewById(R.id.text_structure_x81_6);
		text_value_x278 = (TextView) findViewById(R.id.text_value_x278);
		text_structure_x84_6 = (TextView) findViewById(R.id.text_structure_x84_6);
		text_value_x279 = (TextView) findViewById(R.id.text_value_x279);
		text_structure_x200_6 = (TextView) findViewById(R.id.text_structure_x200_6);
		text_value_x280 = (TextView) findViewById(R.id.text_value_x280);
		text_structure_x201_6 = (TextView) findViewById(R.id.text_structure_x201_6);
		text_value_x281 = (TextView) findViewById(R.id.text_value_x281);
		text_cost_x282 = (TextView) findViewById(R.id.text_cost_x282);

		text_cost_x63 = (TextView) findViewById(R.id.text_cost_x63);
		text_cost_x53 = (TextView) findViewById(R.id.text_cost_x53);
		text_cost_x64 = (TextView) findViewById(R.id.text_cost_x64);

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
			
//			JTGRMonAsyncTask task = new JTGRMonAsyncTask(activity);
//			task.execute(in_id);
			setEntityToUI(entity,c);
		} else {
			isAdd = false;
			entity = new HouseList();
			c = new HouseConfirm();
			
			JTGRMonAsyncTask task = new JTGRMonAsyncTask(activity);
			task.execute(in_id);
			setEntityToUI(entity,c);
			
			//屏蔽保存 不能修改
		//	Button btn = (Button)findViewById(R.id.btn_title_right);
		//	btn.setVisibility(View.INVISIBLE);

		}
	}

	/** 将集体个人货币补偿计算单的信息展现到UI */
	public void setEntityToUI(HouseList entity, HouseConfirm c) {
		
		DecimalFormat DouToStr = new DecimalFormat("0.00");
		// tab1

		entity.setHouseConfirm(c);
		// jtgrmonetary.setHouseAttdto(d);
		edit_insidearea_x12.setText(DouToStr.format(entity.getX12()));
		
		edit_cardno_x2.setText(entity.getX2());
	    edit_linktel_x3.setText(entity.getX3());

//		text_cardno_x2.setText(entity.getX2());
//		text_linktel_x3.setText(entity.getX3());

		text_contractno_x185.setText(entity.getX185());
		text_maininquier_x218.setText(c.getX218());
		text_levyname_x1.setText(c.getX1());
		text_levyaddress_x4.setText(c.getX4());
		text_housetotalarea_x5.setText(DouToStr.format(entity.getX5_100()));
		text_purpose_x6.setText(c.getX6());
		text_structure_x7_1.setText(c.getX7());
		text_area_x9.setText(c.getX9() + "");
		text_structure_x8_1.setText(c.getX8());
		text_area_x10.setText(c.getX10() + "");
		text_structure_x81_1.setText(c.getX81());
		text_area_x184.setText(c.getX184() + "");
		text_structure_x84_1.setText(c.getX84());
		text_area_x188.setText(c.getX188() + "");
		text_structure_x200_1.setText(c.getX200());
		text_area_x202.setText(c.getX202() + "");
		text_structure_x201_1.setText(c.getX201());
		text_area_x203.setText(c.getX203() + "");
		text_legalarea_x11.setText(c.getX11() + "");
		text_unregisterarea_x13.setText(DouToStr.format(entity.getX13_101()));
		text_structure_x7_2.setText(c.getX7());
		text_value_x14_1.setText(c.getX14() + "");
		text_structure_x8_2.setText(c.getX8());
		text_value_x15_1.setText(c.getX15() + "");
		text_structure_x81_2.setText(c.getX81());
		text_value_x16_1.setText(c.getX16() + "");
		text_structure_x84_2.setText(c.getX84());
		text_value_x38_1.setText(c.getX86() + "");
		text_structure_x200_2.setText(c.getX200());
		text_value_x204_1.setText(c.getX204() + "");
		text_structure_x201_2.setText(c.getX201());
		text_value_x205_1.setText(c.getX205() + "");

		// tab2

		edit_area_x164.setText(DouToStr.format(entity.getX164()));
		edit_area_x165.setText(DouToStr.format(entity.getX165()));
		edit_area_x166.setText(DouToStr.format(entity.getX166()));
		edit_area_x167.setText(DouToStr.format(entity.getX167()));
		edit_area_x208.setText(DouToStr.format(entity.getX208()));
		edit_area_x209.setText(DouToStr.format(entity.getX209()));
		edit_cost_x88.setText(DouToStr.format(entity.getX88()));
		edit_num_x89.setText(entity.getX89() + "");
		edit_cost_x95.setText(DouToStr.format(entity.getX95()));
		edit_cost_x171.setText(DouToStr.format(entity.getX171()));

		text_structure_x7_3.setText(c.getX7());
		text_value_x14_2.setText(c.getX14() + "");
		text_value_x78_1.setText(DouToStr.format(entity.getX78_1()));
		text_structure_x8_3.setText(c.getX8());
		text_value_x15_2.setText(c.getX15() + "");
		text_value_x80_1.setText(DouToStr.format(entity.getX80_1()));
		text_structure_x81_3.setText(c.getX81());
		text_value_x16_2.setText(c.getX16() + "");
		text_value_x83_1.setText(DouToStr.format(entity.getX83_1()));
		text_structure_x84_3.setText(c.getX84());
		text_value_x86_2.setText(c.getX86() + "");
		text_value_x87_1.setText(DouToStr.format(entity.getX87_1()));
		text_structure_x200_3.setText(c.getX200());
		text_value_x204_2.setText(c.getX204() + "");
		text_value_x206_1.setText(DouToStr.format(entity.getX206_1()));
		text_structure_x201_3.setText(c.getX201());
		text_value_x205_2.setText(c.getX205() + "");

		text_value_x207_1.setText(DouToStr.format(entity.getX207_1()));
		text_area_x13_1.setText(DouToStr.format(entity.getX13_101()));
		text_cost_x25.setText(DouToStr.format(entity.getX25_101()));
		text_area_x13_2.setText(DouToStr.format(entity.getX13_101()));
		text_cost_x30.setText(DouToStr.format(entity.getX30_101()));
		text_cost_x107.setText(DouToStr.format(entity.getX107()));
		text_cost_x108.setText(DouToStr.format(entity.getX108()));
		text_cost_x109.setText(DouToStr.format(entity.getX109()));

		// tab3

		edit_percent_x111.setText(entity.getX111() + "");
		edit_cost_x168.setText(DouToStr.format(entity.getX168()));
		edit_cost_x169.setText(DouToStr.format(entity.getX169()));
		edit_value_xd1.setText(DouToStr.format(entity.getXd1()));
		edit_cost_x119.setText(DouToStr.format(entity.getX119()));
		edit_cost_x120.setText(DouToStr.format(entity.getX120()));
		edit_value_xd3.setText(DouToStr.format(entity.getXd3()));
		edit_cost_x148.setText(DouToStr.format(entity.getX148()));
		edit_num_x149.setText(entity.getX149() + "");
		edit_cost_x168.setText(DouToStr.format(entity.getX168()));
		edit_cost_x169.setText(DouToStr.format(entity.getX169()));

		text_structure_x7_4.setText(c.getX7());
		text_value_x14_3.setText(c.getX14() + "");
		text_structure_x8_4.setText(c.getX8());
		text_value_x15_3.setText(c.getX15() + "");
		text_structure_x81_4.setText(c.getX81());
		text_value_x16_3.setText(c.getX16() + "");
		text_structure_x84_4.setText(c.getX84());
		text_value_x86_3.setText(c.getX86() + "");
		text_structure_x200_4.setText(c.getX200());
		text_value_x204_3.setText(c.getX204() + "");
		text_structure_x201_4.setText(c.getX201());
		text_value_x205_3.setText(c.getX205() + "");

		text_area_x13_4.setText(DouToStr.format(entity.getX13_101()));
		text_cost_x284.setText(DouToStr.format(entity.getX284_101()));
		text_area_x46.setText(c.getX46()+ "");
		text_cost_x48.setText(DouToStr.format(entity.getX48_101()));
		text_cost_x150.setText(DouToStr.format(entity.getX150()));
		text_cost_x52.setText(DouToStr.format(entity.getX52_101()));

		text_area_x164.setText(DouToStr.format(entity.getX164()));
		text_area_x165.setText(DouToStr.format(entity.getX165()));
		text_area_x166.setText(DouToStr.format(entity.getX166()));
		text_area_x167.setText(DouToStr.format(entity.getX167()));
		text_area_x208.setText(DouToStr.format(entity.getX208()));
		text_area_x209.setText(DouToStr.format(entity.getX209()));

		text_percent_x111_2.setText(entity.getX111() + "");
		text_percent_x111_3.setText(entity.getX111() + "");
		text_percent_x111_4.setText(entity.getX111() + "");
		text_percent_x111_5.setText(entity.getX111() + "");
		text_percent_x111_6.setText(entity.getX111() + "");

		text_value_x112.setText(DouToStr.format(entity.getX112()));
		text_value_x113.setText(DouToStr.format(entity.getX113()));
		text_value_x115.setText(DouToStr.format(entity.getX115()));
		text_value_x118.setText(DouToStr.format(entity.getX118()));
		text_value_x210.setText(DouToStr.format(entity.getX210()));
		text_value_x211.setText(DouToStr.format(entity.getX211()));

		// tab4

		edit_area_x152.setText(DouToStr.format(entity.getX152()));
		edit_area_x155.setText(DouToStr.format(entity.getX155()));
		edit_area_x158.setText(DouToStr.format(entity.getX158()));
		edit_area_x212.setText(DouToStr.format(entity.getX212()));
		edit_area_x213.setText(DouToStr.format(entity.getX213()));
		edit_area_x214.setText(DouToStr.format(entity.getX214()));
		edit_value_x153.setText(DouToStr.format(entity.getX153()));
		edit_value_x156.setText(DouToStr.format(entity.getX156()));
		edit_value_x159.setText(DouToStr.format(entity.getX159()));
		edit_value_x215.setText(DouToStr.format(entity.getX215()));
		edit_value_x216.setText(DouToStr.format(entity.getX216()));
		edit_value_x217.setText(DouToStr.format(entity.getX217()));
		edit_value_x270.setText(DouToStr.format(entity.getX270()));
		edit_value_x271.setText(DouToStr.format(entity.getX271()));
		edit_value_x272.setText(DouToStr.format(entity.getX272()));
		edit_value_x273.setText(DouToStr.format(entity.getX273()));
		edit_value_x274.setText(DouToStr.format(entity.getX274()));
		edit_value_x275.setText(DouToStr.format(entity.getX275()));

		text_cost_x161.setText(DouToStr.format(entity.getX161()));
		text_value_x154.setText(DouToStr.format(entity.getX154()));
		text_value_x157.setText(DouToStr.format(entity.getX157()));
		text_value_x160.setText(DouToStr.format(entity.getX160()));
		text_value_x223.setText(DouToStr.format(entity.getX223()));

		text_value_x219.setText(DouToStr.format(entity.getX219()));
		text_value_x220.setText(DouToStr.format(entity.getX220()));
		text_area_x152.setText(DouToStr.format(entity.getX152()));
		text_area_x155.setText(DouToStr.format(entity.getX155()));
		text_area_x158.setText(DouToStr.format(entity.getX158()));

		text_area_x212.setText(DouToStr.format(entity.getX212()));
		text_area_x213.setText(DouToStr.format(entity.getX213()));
		text_area_x214.setText(DouToStr.format(entity.getX214()));
		text_value_x276.setText(DouToStr.format(entity.getX276()));
		text_value_x277.setText(DouToStr.format(entity.getX277()));

		text_value_x278.setText(DouToStr.format(entity.getX278()));
		text_value_x279.setText(DouToStr.format(entity.getX279()));
		text_value_x280.setText(DouToStr.format(entity.getX280()));

		text_structure_x7_5.setText(c.getX7());
		text_structure_x8_5.setText(c.getX8());
		text_structure_x81_5.setText(c.getX81());
		text_structure_x84_5.setText(c.getX84());
		text_structure_x200_5.setText(c.getX200());
		text_structure_x201_5.setText(c.getX201());
		text_structure_x7_6.setText(c.getX7());
		text_structure_x8_6.setText(c.getX8());
		text_structure_x81_6.setText(c.getX81());
		text_structure_x84_6.setText(c.getX84());
		text_structure_x200_6.setText(c.getX200());
		text_structure_x201_6.setText(c.getX201());

		text_value_x281.setText(DouToStr.format(entity.getX281()));
		text_cost_x282.setText(DouToStr.format(entity.getX282()));

		text_cost_x63.setText(DouToStr.format(entity.getX63_101()));
		text_cost_x53.setText(DouToStr.format(entity.getX53_101()));
		text_cost_x64.setText(DouToStr.format(entity.getX64_101()));
		
		//如果算单状态值为2（已提交），屏蔽保存按钮，使其不能修改
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
		//tab1		
		
		entity.setX12(StringToDouble(edit_insidearea_x12.getText().toString()));

		entity.setX2(edit_cardno_x2.getText().toString());
		entity.setX3(edit_linktel_x3.getText().toString());

	    //tab2
	    
		entity.setX164(StringToDouble(edit_area_x164.getText().toString()));
	    entity.setX165(StringToDouble(edit_area_x165.getText().toString()));
	    entity.setX166(StringToDouble(edit_area_x166.getText().toString()));
	    entity.setX167(StringToDouble(edit_area_x167.getText().toString()));
	    entity.setX208(StringToDouble(edit_area_x208.getText().toString()));
	    entity.setX209(StringToDouble(edit_area_x209.getText().toString()));
	    entity.setX88(StringToDouble(edit_cost_x88.getText().toString()));
	    entity.setX89(StringToInteger(edit_num_x89.getText().toString()));
	    entity.setX95(StringToDouble(edit_cost_x95.getText().toString()));
	    entity.setX171(StringToDouble(edit_cost_x171.getText().toString()));
	    
		// tab3
		 		    
		entity.setX111(StringToDouble(edit_percent_x111.getText().toString()));
		entity.setXd1(StringToDouble(edit_value_xd1.getText().toString()));
		entity.setX119(StringToDouble(edit_cost_x119.getText().toString()));
		entity.setX120(StringToDouble(edit_cost_x120.getText().toString()));
		entity.setXd3(StringToDouble(edit_value_xd3.getText().toString()));
		entity.setX148(StringToDouble(edit_cost_x148.getText().toString()));
		entity.setX149(StringToInteger(edit_num_x149.getText().toString()));
		entity.setX168(StringToDouble(edit_cost_x168.getText().toString()));
		entity.setX169(StringToDouble(edit_cost_x169.getText().toString()));
		
		// tab4
		
		entity.setX152(StringToDouble(edit_area_x152.getText().toString()));
		entity.setX155(StringToDouble(edit_area_x155.getText().toString()));
		entity.setX158(StringToDouble(edit_area_x158.getText().toString()));
		entity.setX212(StringToDouble(edit_area_x212.getText().toString()));
		entity.setX214(StringToDouble(edit_area_x213.getText().toString()));
		entity.setX214(StringToDouble(edit_area_x214.getText().toString()));
		entity.setX153(StringToDouble(edit_value_x153.getText().toString()));
		entity.setX156(StringToDouble(edit_value_x156.getText().toString()));
		entity.setX159(StringToDouble(edit_value_x159.getText().toString()));
		entity.setX215(StringToDouble(edit_value_x215.getText().toString()));
		entity.setX216(StringToDouble(edit_value_x216.getText().toString()));
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
			return Double.parseDouble("0.00");
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
	
	
	
	 
	 // @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case requestcode_att3:
			requestCode_Att(resultCode,data,text_cost_x107);
			break;
		case requestcode_att1:
			requestCode_Att(resultCode,data,text_cost_x108);
			break;
		case requestcode_att2:
			requestCode_Att(resultCode,data,text_cost_x109);
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
			activity.startActivityForResult(intent,requestcode_att3);
		}
		void Onclick_btn_att_1() {
			Intent intent = new Intent(activity, HListAttList.class);
			intent.putExtra(HListAttList.KEY.listid, entity.getId());
			intent.putExtra(HListAttList.KEY.hid, entity.getHid());
			intent.putExtra(HListAttList.KEY.pid, entity.getPid());;
			intent.putExtra(HListAttList.KEY.temptype, "0");
			intent.putExtra(HListAttList.KEY.h_status, String.valueOf(entity.getStatus()));
			activity.startActivityForResult(intent,requestcode_att1);
		}
		void Onclick_btn_att_2() {
			Intent intent = new Intent(activity, HListAttList.class);
			intent.putExtra(HListAttList.KEY.listid, entity.getId());
			intent.putExtra(HListAttList.KEY.hid, entity.getHid());
			intent.putExtra(HListAttList.KEY.pid, entity.getPid());
			intent.putExtra(HListAttList.KEY.temptype, "1");
			intent.putExtra(HListAttList.KEY.h_status, String.valueOf(entity.getStatus()));
			activity.startActivityForResult(intent,requestcode_att2);
		}

//		void Onclick_btn_title_right() {
//			try {
//				getEntity();
//				String result = HOU_JTGR_MONETARY_Bus.check(entity);
//				if (!result.equals("")) {
//					DialogHelper.showConfirm(activity, result);
//					return;
//				}
//				HOU_JTGR_MONETARY_Bus bus = new HOU_JTGR_MONETARY_Bus(getHelper());
//				bus.create(entity);
//
//				Toast.makeText(activity, "保存成功", Toast.LENGTH_LONG).show();
//				activity.setResult(Activity.RESULT_OK, null);
//				activity.finish();
//			} catch (MyException e) {
//				ExceptionHelper.Operate(e, true, activity);
//			} catch (Exception e) {
//				MyException myE = new MyException("保存出错", e.getMessage(),e.getStackTrace());
//				ExceptionHelper.Operate(myE, true, activity);
//			}
//		}
		
		
		void Onclick_btn_title_right(){	
			getEntity();
			String result = HOU_JTGR_MONETARY_Bus.check(entity);
			if (!result.equals("")) {
				DialogHelper.showConfirm(activity, result);
				return;
			} 
			showTempSaveDialog(null);
	       }
		
		void showTempSaveDialog(String message) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("集体个人货币算单提交或暂存");
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
							JTGRMonSaveAsyncTask save = new JTGRMonSaveAsyncTask(activity);
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
							JTGRMonSaveAsyncTask save = new JTGRMonSaveAsyncTask(activity);
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
				JTGRMonetaryCoptA.this.finish();
				return;
			}
			// 新增集体个人 货币补偿补偿计算单信息时，按下返回，如果必填项姓名或证件号不为空则进行关闭确认
			if (isAdd) {
				String levyname = text_levyname_x1.getText().toString().trim();
				String cardno = edit_cardno_x2.getText().toString().trim();

				if ((!levyname.equals("")) || (!cardno.equals(""))) {
					new AlertDialog.Builder(JTGRMonetaryCoptA.this)
							.setMessage("信息未保存，确认要关闭？")
							.setPositiveButton("确定", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									JTGRMonetaryCoptA.this.finish();
								}
							}).setNegativeButton("取消", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).create().show();
				} else {
					JTGRMonetaryCoptA.this.finish();
				}
			} else {
				JTGRMonetaryCoptA.this.finish();
			}
		}

	}

}
