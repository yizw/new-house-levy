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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseTabActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.AsyncTask.HouseRoomInfoDeleteAsyncTask;
import com.yizw.newhouselevy.AsyncTask.JTGRProAsyncTask;
import com.yizw.newhouselevy.AsyncTask.JTGRProSaveAsyncTask;
import com.yizw.newhouselevy.Business.HOU_JTGR_PROEXCHANGE_Bus;
import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.EnumCode;
import com.yizw.newhouselevy.Entity.HouseConfirm;
import com.yizw.newhouselevy.Entity.HouseList;

/**分户--集体个人--产权--补偿计算单
*author:yizw
*/
public class JTGRProExchangeA extends OrmLiteBaseTabActivity<DatabaseHelper>{

	public class KEY{
		/** 补偿计算单id*/
		public static final String in_id = "in_id";
		/** 补偿计算单类型*/
		public static final String in_type = "in_type";
		/*分户id*/
		public static final String in_hid = "in_hid";
		
		/*算单内房间id*/
		public static final String hid = "hid";
		
		public static final String in_pid = "in_pid";
		
		public static final String c_status="c_status";
		
		public static final String index = "index";
		
	}
	
	private final int requestCode_house1= 1;
	private final int requestCode_house2= 2;
	private final int requestCode_house3= 3;
	private final int requestCode_house4= 4;
	private final int requestCode_house5= 5;
	private final int requestCode_house6= 6;
	private final int requestCode_house7= 7;
	private final int requestCode_house8= 8;
	
	private final int requestCode_house11= 11;
	private final int requestCode_house12= 12;
	private final int requestCode_house13= 13;
	private final int requestCode_house14= 14;
	private final int requestCode_house15= 15;
	private final int requestCode_house16= 16;
	private final int requestCode_house21= 21;
	private final int requestCode_house22= 22;
	private final int requestCode_house23= 23;
	private final int requestCode_house24= 24;
	private final int requestCode_house25= 25;
	private final int requestCode_house26= 26;
	
	
	
	private final int requestCode_housedelete31= 31;
	private final int requestCode_housedelete32= 32;
	private final int requestCode_housedelete33= 33;
	private final int requestCode_housedelete34= 34;
	private final int requestCode_housedelete35= 35;
	private final int requestCode_housedelete36= 36;
	private final int requestCode_housedelete37= 37;
	private final int requestCode_housedelete38= 38;
	
	private final int requestCode_housedelete39= 39;
	private final int requestCode_housedelete40= 40;
	private final int requestCode_housedelete41= 41;
	private final int requestCode_housedelete42= 42;
	private final int requestCode_housedelete43= 43;
	private final int requestCode_housedelete44= 44;
	private final int requestCode_housedelete45= 45;
	private final int requestCode_housedelete46= 46;
	private final int requestCode_housedelete47= 47;
	private final int requestCode_housedelete48= 48;
	private final int requestCode_housedelete49= 49;
	private final int requestCode_housedelete50= 50;
	
	private final int requestcode_att1=100;
	private final int requestcode_att2=101;
	private final int requestcode_att3=102;
	
	public JTGRProExchangeA activity;
	
	public HouseList entity;
	public HouseConfirm c;
	
	
    /** 是否是新增确认信息*/
    boolean isAdd = true;
    public String id,in_id,hid,pid,index,c_status,in_hid; 
    private Button btn_title_right;
    
    //tab3  四、搬迁费
    private Spinner sp_bqflagvalue;
    
    LinearLayout bqflagvalue,bqflagvalue_0,bqflagvalue_1;
	
    private EditText 
    //tab1
    edit_confirmarea_x12,
    
    edit_cardno_x2,edit_linktel_x3,
    //tab2
    edit_prohouseaera_x64,
    
    edit_value_x19_1,
    edit_value_x19_2,
    edit_value_x19_3,
    edit_value_x19_4,
    //3月12日添加    
    edit_value_x19_5,
    edit_value_x19_6,
    edit_value_x19_7,
    edit_value_x19_8,
    edit_value_x19_9,
    edit_value_x19_10,  
    
        
    edit_value_x42_1,
    edit_value_x42_2,
    edit_value_x42_3,
    edit_value_x42_4,
    //3月12日添加  
    edit_value_x42_5,
    edit_value_x42_6,
    edit_value_x42_7,
    edit_value_x42_8,
    edit_value_x42_9,
    edit_value_x42_10,
    
    edit_prohouseaera_x65, edit_value_x66,edit_prohouseaera_x68,
    edit_value_x69_1,edit_percent_x70,edit_prohouseaera_x72,edit_percent_x74,
    edit_prohouseaera_x77,edit_prohouseaera_x79,edit_prohouseaera_x82, edit_prohouseaera_x85,
    edit_prohouseaera_x221,edit_prohouseaera_x222,
    
    
    //tab3  
    edit_legalarea_x13_2,edit_prohouseaera_x63_2,edit_residuearea_x1363,
    
    edit_value_x88_2,
    
    edit_value_x88_1,edit_num_x89,edit_num_x91,edit_num_x93,
    edit_cost_x95_1,edit_month_x96,edit_month_x98,
    edit_month_x100,edit_month_x102,
    edit_month_x104,edit_cost_x170, edit_area_x164,edit_rise_x111_1,edit_area_x165,
    edit_area_x166,edit_area_x167,edit_area_x208,
    edit_area_x209,edit_money_x119,edit_money_x120,
      
    edit_value_x128,edit_value_x129,edit_value_x130,edit_value_x131,edit_money_x136,
    edit_num_x139,edit_money_x137,edit_num_x140,edit_money_x138,edit_num_x141,
    edit_value_x146,edit_price_x148,edit_housenum_x149, edit_money_x168,edit_money_x169,
    
    edit_x500,edit_x501,edit_x502,edit_x503,edit_x504,
    
    edit_area_x800,edit_area_x801,edit_area_x802,edit_area_x803,
    
    //3月20日新增
    edit_a501,edit_a502,edit_a503,edit_a504,edit_a505,edit_a506,
    
    edit_month_a98,edit_month_a100,edit_month_a102,
    edit_month_a104,edit_month_a106,edit_month_a108,
    
        
    //tab4
    edit_area_x152_1, edit_value_x153, edit_area_x155_1, edit_value_x156,
    edit_area_x158_1, edit_value_x159,edit_area_x212_1, edit_value_x215,
    edit_area_x213_1, edit_value_x216,edit_area_x214_1, edit_value_x217,
    edit_value_x270, edit_value_x271,
    edit_value_x272,edit_value_x273,
    edit_value_x274, edit_value_x275;
    
    private TextView 
    //tab1
    
    text_contractno_x185,text_levyname_x1,text_maininquier_x218,text_levyaddress_x4,
    text_housetotalarea_x5,text_purpose_x6,text_structure_x7_1,text_area_x9,text_structure_x8_1,text_area_x10,
    text_structure_x81_1,text_area_x184,text_structure_x84_1,text_area_x188,text_structure_x200_1,text_area_x202,
    text_structure_x201_1,text_area_x203,    
    text_registerarea_x11,text_legalarea_x13,    
    text_structure_x7_2,text_value_x14_1,text_structure_x8_2,text_value_x15_1,text_structure_x81_2,
    text_value_x16_1,text_structure_x84_2,text_value_x86_1,text_structure_x200_2,text_value_x204_1,
    text_structure_x201_2,text_value_x205_1,
	//tab2
    text_value_x69,
    text_prohouseaera_x63,
    text_value_x67,text_value_x71,
    text_prohouseaera_x75,text_value_x76,text_structure_x7_3,text_ass_value_x14_3,text_value_x78,text_structure_x8_3,
    text_ass_value_x15_3,text_value_x80,text_structure_x81_3,text_ass_value_x16_3,text_value_x83,text_structure_x84_3,
    text_ass_value_x86_3,text_value_x87,text_structure_x200_3,text_ass_value_x204_3,text_value_x206,
    text_structure_x201_3,text_ass_value_x205_3,text_value_x207,    
    
    text_house_x17_1,text_existhouse_x18_1, text_house_x20,text_house_x21,text_house_x22,text_house_x23,text_area_x24,
    text_house_x17_2,text_existhouse_x18_2,text_house_x25,text_house_x26,text_house_x27,text_house_x28,text_area_x29,
    text_house_x17_3,text_existhouse_x18_3,text_house_x30,text_house_x31,text_house_x32,text_house_x33, text_area_x34,
    text_house_x17_4,text_existhouse_x18_4,text_house_x35,text_house_x36,text_house_x37,text_house_x38,text_area_x39,
    
    //3月12日新增  现房
    text_house_x17_5,text_existhouse_x18_5, text_house_x900,text_house_x902,text_house_x903,text_house_x904,text_area_x905,
    text_house_x17_6,text_existhouse_x18_6, text_house_x906,text_house_x907,text_house_x908,text_house_x909,text_area_x910,
    text_house_x17_7,text_existhouse_x18_7, text_house_x911,text_house_x912,text_house_x913,text_house_x914,text_area_x915, 
    text_house_x17_8,text_existhouse_x18_8, text_house_x916,text_house_x917,text_house_x918,text_house_x919,text_area_x920,
    text_house_x17_9,text_existhouse_x18_9, text_house_x921,text_house_x922,text_house_x923,text_house_x924,text_area_x925,
    text_house_x17_10,text_existhouse_x18_10, text_house_x926,text_house_x927,text_house_x928,text_house_x929,text_area_x930,
    
    
    text_house_x40_1,text_existhouse_x41_1, text_house_x43,text_house_x44,text_house_x45,text_house_x46,text_area_x47_1,
    text_house_x40_2, text_existhouse_x41_2,text_house_x48,text_house_x49,text_house_x50,text_house_x51,text_area_x52_1,
    text_house_x40_3,text_existhouse_x41_3,text_house_x53,text_house_x54,text_house_x55,text_house_x56,text_area_x57_1,
    text_house_x40_4, text_existhouse_x41_4,text_house_x58,text_house_x59,text_house_x60,text_house_x61,text_area_x62_1,
    //3月12日新增  期房 
    text_house_x40_5,text_existhouse_x41_5, text_house_x931,text_house_x932,text_house_x933,text_house_x934,text_area_x935,
    text_house_x40_6,text_existhouse_x41_6, text_house_x936,text_house_x937,text_house_x938,text_house_x939,text_area_x940,
    text_house_x40_7,text_existhouse_x41_7, text_house_x941,text_house_x942,text_house_x943,text_house_x944,text_area_x945,
    text_house_x40_8,text_existhouse_x41_8, text_house_x946,text_house_x947,text_house_x948,text_house_x949,text_area_x950,
    text_house_x40_9,text_existhouse_x41_9, text_house_x951,text_house_x952,text_house_x953,text_house_x954,text_area_x955,
    text_house_x40_10,text_existhouse_x41_10, text_house_x956,text_house_x957,text_house_x958,text_house_x959,text_area_x960,
    
    
    //tab3    
    text_cost_x90,text_cost_x92,
    text_cost_x94,text_cost_x97,
    text_cost_x99,text_cost_x101,
    text_cost_x103,text_cost_x105,text_legalarea_x13_3,
    text_cost_x106,text_cost_x107,text_cost_x108,text_cost_x109,text_structure_x7_4,
    text_value_x14_4,text_cost_x112,text_structure_x8_4,text_value_x15_4,text_cost_x113,
    text_structure_x81_4,text_value_x16_4,text_cost_x115,text_structure_x84_4,
    text_value_x86_4,text_cost_x118,text_structure_x200_4,text_value_x204_4,text_cost_x210,
    text_structure_x201_4,text_value_x205_4,text_cost_x211,
    
    text_value_x88_3,
    text_cost_x95_2,text_cost_x95_3,text_cost_x95_4,text_cost_x95_5,
    
    
    text_value_x132,text_value_x133,text_value_x134,
    text_value_x135,text_value_x142,text_value_x143,text_value_x144,
    text_value_x147,text_money_x150,text_money_x151,
    
    text_rise_x111_6,text_rise_x111_5,text_rise_x111_4,text_rise_x111_3,text_rise_x111_2,
     
    text_area_x46,
    
    
    //3月20日新增
    text_cost_x95_6,text_cost_x95_7,text_cost_x95_8,text_cost_x95_9,
    text_cost_x95_10,text_cost_x95_11,
    
    text_cost_a99_000,text_cost_a101_000,
    text_cost_a103_000,text_cost_a105_000,text_cost_a107_000,text_cost_a109_000,
    
    
    //tab4
    text_structure_x7_5,text_allowance_x154, text_structure_x8_5,
    text_allowance_x157, text_structure_x81_5,
    text_allowance_x160, text_structure_x84_5, 
    text_allowance_x223,text_structure_x200_5, 
    text_allowance_x219,text_structure_x201_5,
    text_allowance_x220,text_totallowance_x161,text_structure_x7_6,
    text_allowance_x276,text_structure_x8_6,
    text_allowance_x277,text_structure_x81_6, 
    text_allowance_x278, text_structure_x84_6, 
    text_allowance_x279, text_structure_x200_6,
    text_allowance_x280, text_structure_x201_6,
    text_allowance_x281,text_totallowance_x282,text_totallowance_x162, 
    text_totallowance_x163,text_totallowance_x162163,text_totallowance_x76_2,
    
    text_area_x152_2,text_area_x155_2,text_area_x158_2,
    text_area_x212_2,text_area_x213_2,text_area_x214_2;
    
    
    private Button btn_housechoice_1,btn_housechoice_2,btn_housechoice_3,btn_housechoice_4,
                   btn_housechoice_5,btn_housechoice_6,btn_housechoice_7,btn_housechoice_8,
                   
                   //3月12日新增 选房
                   btn_housechoice_05,btn_housechoice_06,btn_housechoice_07,btn_housechoice_08,
                   btn_housechoice_09,btn_housechoice_010,
                   
                   btn_housechoice_9,btn_housechoice_10,btn_housechoice_11,btn_housechoice_12,
                   btn_housechoice_13,btn_housechoice_14,
                   
                   
                   btn_housedelete_1,btn_housedelete_2,btn_housedelete_3,btn_housedelete_4,
                   btn_housedelete_5,btn_housedelete_6,btn_housedelete_7,btn_housedelete_8,
    
                   //3月12日新增 删房
                   btn_housedelete_05,btn_housedelete_06,btn_housedelete_07,btn_housedelete_08,
                   btn_housedelete_09,btn_housedelete_010,
                   
                   btn_housedelete_9,btn_housedelete_10,btn_housedelete_11,btn_housedelete_12,
                   btn_housedelete_13,btn_housedelete_14;
    
    
    AlertDialog statusDialog = null;
	
	 @SuppressWarnings("deprecation")
	 public void onResume(){
		 super.onResume();
		 MobclickAgent.onResume(this);
	 }
	 
	@SuppressWarnings("deprecation")
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hou_jtgr_proexchange);
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

	/* 初始化选项卡 */
	@SuppressWarnings("deprecation")
	private void InitTabHost() {
	  TabHost tabHost = this.getTabHost();
	  LayoutInflater inflater = LayoutInflater.from(this);
			 
	  inflater.inflate(R.layout.hou_jtgr_proexchange_tab1, tabHost.getTabContentView());
	  tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getTabItemView(inflater,0,"一")).setContent(R.id.hou_jtgr_proexchange_tab1));
	  tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_item_bg);
		 
	  inflater.inflate(R.layout.hou_jtgr_proexchange_tab2, tabHost.getTabContentView());
	  tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getTabItemView(inflater,0,"二")).setContent(R.id.hou_jtgr_proexchange_tab2));
	  tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_item_bg);
		 
	  inflater.inflate(R.layout.hou_jtgr_proexchange_tab3, tabHost.getTabContentView());
	  tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getTabItemView(inflater,0,"三")).setContent(R.id.hou_jtgr_proexchange_tab3));
	  tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab_item_bg);
		     
	  inflater.inflate(R.layout.hou_jtgr_proexchange_tab4, tabHost.getTabContentView());
	  tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(getTabItemView(inflater,0,"四")).setContent(R.id.hou_jtgr_proexchange_tab4));
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
		
		try {
			//tab3  四、搬迁费
		sp_bqflagvalue = initSp(R.id.sp_bqflagvalue, EnumCode.GYGR_PRO_INFO.bqflagvalue, true);
		sp_bqflagvalue.setOnItemSelectedListener(new OnItemSelected());
		
		
		// tab2 选房按钮
		btn_housechoice_1= initBtn(R.id.btn_housechoice_1);
		btn_housechoice_2= initBtn(R.id.btn_housechoice_2);
		btn_housechoice_3= initBtn(R.id.btn_housechoice_3);
		btn_housechoice_4= initBtn(R.id.btn_housechoice_4);
		btn_housechoice_5= initBtn(R.id.btn_housechoice_5);
		btn_housechoice_6= initBtn(R.id.btn_housechoice_6);
		btn_housechoice_7= initBtn(R.id.btn_housechoice_7);
		btn_housechoice_8= initBtn(R.id.btn_housechoice_8);
		
		//3月12日新增 选房
		btn_housechoice_05= initBtn(R.id.btn_housechoice_05);
		btn_housechoice_06= initBtn(R.id.btn_housechoice_06);
		btn_housechoice_07= initBtn(R.id.btn_housechoice_07);
		btn_housechoice_08= initBtn(R.id.btn_housechoice_08);
		btn_housechoice_09= initBtn(R.id.btn_housechoice_09);
		btn_housechoice_010= initBtn(R.id.btn_housechoice_010);
		
		btn_housechoice_9= initBtn(R.id.btn_housechoice_9);
		btn_housechoice_10= initBtn(R.id.btn_housechoice_10);
		btn_housechoice_11= initBtn(R.id.btn_housechoice_11);
		btn_housechoice_12= initBtn(R.id.btn_housechoice_12);
		btn_housechoice_13= initBtn(R.id.btn_housechoice_13);
		btn_housechoice_14= initBtn(R.id.btn_housechoice_14);
		
		
		// tab2 删房按钮
		btn_housedelete_1= initBtn(R.id.btn_housedelete_1);
		btn_housedelete_2= initBtn(R.id.btn_housedelete_2);
		btn_housedelete_3= initBtn(R.id.btn_housedelete_3);
		btn_housedelete_4= initBtn(R.id.btn_housedelete_4);
		btn_housedelete_5= initBtn(R.id.btn_housedelete_5);
		btn_housedelete_6= initBtn(R.id.btn_housedelete_6);
		btn_housedelete_7= initBtn(R.id.btn_housedelete_7);
		btn_housedelete_8= initBtn(R.id.btn_housedelete_8);
		
		//3月12日新增 删房
	    btn_housedelete_05= initBtn(R.id.btn_housedelete_05);
	    btn_housedelete_06= initBtn(R.id.btn_housedelete_06);
	    btn_housedelete_07= initBtn(R.id.btn_housedelete_07);
	    btn_housedelete_08= initBtn(R.id.btn_housedelete_08);
	    btn_housedelete_09= initBtn(R.id.btn_housedelete_09);
	    btn_housedelete_010= initBtn(R.id.btn_housedelete_010);
	    
	    btn_housedelete_9= initBtn(R.id.btn_housedelete_9);
	    btn_housedelete_10= initBtn(R.id.btn_housedelete_10);
	    btn_housedelete_11= initBtn(R.id.btn_housedelete_11);
	    btn_housedelete_12= initBtn(R.id.btn_housedelete_12);
	    btn_housedelete_13= initBtn(R.id.btn_housedelete_13);
	    btn_housedelete_14= initBtn(R.id.btn_housedelete_14);
		
		
		//算单附件  按钮
		initBtn(R.id.btn_att_3);
		initBtn(R.id.btn_att_1);
		initBtn(R.id.btn_att_2);
		
		//tab3 spinner搬迁费类型
		bqflagvalue = (LinearLayout) findViewById(R.id.bqflagvalue);
		bqflagvalue_0 = (LinearLayout) findViewById(R.id.bqflagvalue_0);
		bqflagvalue_1 = (LinearLayout) findViewById(R.id.bqflagvalue_1);
		

		// tab1
		edit_confirmarea_x12 = (EditText) findViewById(R.id.edit_confirmarea_x12);
		
		edit_cardno_x2 = (EditText) findViewById(R.id.edit_cardno_x2);
		edit_linktel_x3 = (EditText) findViewById(R.id.edit_linktel_x3);

//		text_cardno_x2 = (TextView) findViewById(R.id.text_cardno_x2);
//		text_linktel_x3 = (TextView) findViewById(R.id.text_linktel_x3);

		text_contractno_x185 = (TextView) findViewById(R.id.text_contractno_x185);
		text_levyname_x1 = (TextView) findViewById(R.id.text_levyname_x1);
		text_maininquier_x218 = (TextView) findViewById(R.id.text_maininquier_x218);

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
		text_registerarea_x11 = (TextView) findViewById(R.id.text_registerarea_x11);
		text_legalarea_x13 = (TextView) findViewById(R.id.text_legalarea_x13);
		text_structure_x7_2 = (TextView) findViewById(R.id.text_structure_x7_2);
		text_value_x14_1 = (TextView) findViewById(R.id.text_value_x14_1);
		text_structure_x8_2 = (TextView) findViewById(R.id.text_structure_x8_2);
		text_value_x15_1 = (TextView) findViewById(R.id.text_value_x15_1);
		text_structure_x81_2 = (TextView) findViewById(R.id.text_structure_x81_2);
		text_value_x16_1 = (TextView) findViewById(R.id.text_value_x16_1);
		text_structure_x84_2 = (TextView) findViewById(R.id.text_structure_x84_2);
		text_value_x86_1 = (TextView) findViewById(R.id.text_value_x86_1);
		text_structure_x200_2 = (TextView) findViewById(R.id.text_structure_x200_2);
		text_value_x204_1 = (TextView) findViewById(R.id.text_value_x204_1);
		text_structure_x201_2 = (TextView) findViewById(R.id.text_structure_x201_2);
		text_value_x205_1 = (TextView) findViewById(R.id.text_value_x205_1);

		// tab2
		edit_prohouseaera_x64 = (EditText) findViewById(R.id.edit_prohouseaera_x64);
		
		edit_value_x19_1 = (EditText) findViewById(R.id.edit_value_x19_1);
		edit_value_x19_2 = (EditText) findViewById(R.id.edit_value_x19_2);
		edit_value_x19_3 = (EditText) findViewById(R.id.edit_value_x19_3);
		edit_value_x19_4 = (EditText) findViewById(R.id.edit_value_x19_4);
		edit_value_x42_1 = (EditText) findViewById(R.id.edit_value_x42_1);
		edit_value_x42_2 = (EditText) findViewById(R.id.edit_value_x42_2);
		edit_value_x42_3 = (EditText) findViewById(R.id.edit_value_x42_3);
		edit_value_x42_4 = (EditText) findViewById(R.id.edit_value_x42_4);
			
	    
	    edit_value_x19_5 = (EditText) findViewById(R.id.edit_value_x19_5);
		edit_value_x19_6 = (EditText) findViewById(R.id.edit_value_x19_6);
		edit_value_x19_7 = (EditText) findViewById(R.id.edit_value_x19_7);
		edit_value_x19_8 = (EditText) findViewById(R.id.edit_value_x19_8);
		edit_value_x19_9 = (EditText) findViewById(R.id.edit_value_x19_9);
		edit_value_x19_10 = (EditText) findViewById(R.id.edit_value_x19_10);
	    
		edit_value_x42_5 = (EditText) findViewById(R.id.edit_value_x42_5);
		edit_value_x42_6 = (EditText) findViewById(R.id.edit_value_x42_6);
		edit_value_x42_7 = (EditText) findViewById(R.id.edit_value_x42_7);
		edit_value_x42_8 = (EditText) findViewById(R.id.edit_value_x42_8);
		edit_value_x42_9 = (EditText) findViewById(R.id.edit_value_x42_9);
		edit_value_x42_10 = (EditText) findViewById(R.id.edit_value_x42_10);
		
		
		edit_prohouseaera_x65 = (EditText) findViewById(R.id.edit_prohouseaera_x65);
		edit_value_x66 = (EditText) findViewById(R.id.edit_value_x66);
		edit_prohouseaera_x68 = (EditText) findViewById(R.id.edit_prohouseaera_x68);
		edit_value_x69_1 = (EditText) findViewById(R.id.edit_value_x69_1);
		edit_percent_x70 = (EditText) findViewById(R.id.edit_percent_x70);
		edit_prohouseaera_x72 = (EditText) findViewById(R.id.edit_prohouseaera_x72);
		edit_percent_x74 = (EditText) findViewById(R.id.edit_percent_x74);
		edit_prohouseaera_x77 = (EditText) findViewById(R.id.edit_prohouseaera_x77);
		edit_prohouseaera_x79 = (EditText) findViewById(R.id.edit_prohouseaera_x79);
		edit_prohouseaera_x82 = (EditText) findViewById(R.id.edit_prohouseaera_x82);
		edit_prohouseaera_x85 = (EditText) findViewById(R.id.edit_prohouseaera_x85);
		edit_prohouseaera_x221 = (EditText) findViewById(R.id.edit_prohouseaera_x221);
		edit_prohouseaera_x222 = (EditText) findViewById(R.id.edit_prohouseaera_x222);
		
		text_house_x17_1 = (TextView) findViewById(R.id.text_house_x17_1);
		text_existhouse_x18_1 = (TextView) findViewById(R.id.text_existhouse_x18_1);

		text_house_x20 = (TextView) findViewById(R.id.text_house_x20);
		text_house_x21 = (TextView) findViewById(R.id.text_house_x21);
		text_house_x22 = (TextView) findViewById(R.id.text_house_x22);
		text_house_x23 = (TextView) findViewById(R.id.text_house_x23);
		text_area_x24 = (TextView) findViewById(R.id.text_area_x24);
		text_house_x17_2 = (TextView) findViewById(R.id.text_house_x17_2);
		text_existhouse_x18_2 = (TextView) findViewById(R.id.text_existhouse_x18_2);

		text_house_x25 = (TextView) findViewById(R.id.text_house_x25);
		text_house_x26 = (TextView) findViewById(R.id.text_house_x26);
		text_house_x27 = (TextView) findViewById(R.id.text_house_x27);
		text_house_x28 = (TextView) findViewById(R.id.text_house_x28);
		text_area_x29 = (TextView) findViewById(R.id.text_area_x29);
		text_house_x17_3 = (TextView) findViewById(R.id.text_house_x17_3);
		text_existhouse_x18_3 = (TextView) findViewById(R.id.text_existhouse_x18_3);

		text_house_x30 = (TextView) findViewById(R.id.text_house_x30);
		text_house_x31 = (TextView) findViewById(R.id.text_house_x31);
		text_house_x32 = (TextView) findViewById(R.id.text_house_x32);
		text_house_x33 = (TextView) findViewById(R.id.text_house_x33);
		text_area_x34 = (TextView) findViewById(R.id.text_area_x34);
		text_house_x17_4 = (TextView) findViewById(R.id.text_house_x17_4);
		text_existhouse_x18_4 = (TextView) findViewById(R.id.text_existhouse_x18_4);

		text_house_x35 = (TextView) findViewById(R.id.text_house_x35);
		text_house_x36 = (TextView) findViewById(R.id.text_house_x36);
		text_house_x37 = (TextView) findViewById(R.id.text_house_x37);
		text_house_x38 = (TextView) findViewById(R.id.text_house_x38);
		text_area_x39 = (TextView) findViewById(R.id.text_area_x39);
		text_house_x40_1 = (TextView) findViewById(R.id.text_house_x40_1);
		text_existhouse_x41_1 = (TextView) findViewById(R.id.text_existhouse_x41_1);

		text_house_x43 = (TextView) findViewById(R.id.text_house_x43);
		text_house_x44 = (TextView) findViewById(R.id.text_house_x44);
		text_house_x45 = (TextView) findViewById(R.id.text_house_x45);
		text_house_x46 = (TextView) findViewById(R.id.text_house_x46);
		text_area_x47_1 = (TextView) findViewById(R.id.text_area_x47_1);
		text_house_x40_2 = (TextView) findViewById(R.id.text_house_x40_2);
		text_existhouse_x41_2 = (TextView) findViewById(R.id.text_existhouse_x41_2);

		text_house_x48 = (TextView) findViewById(R.id.text_house_x48);
		text_house_x49 = (TextView) findViewById(R.id.text_house_x49);
		text_house_x50 = (TextView) findViewById(R.id.text_house_x50);
		text_house_x51 = (TextView) findViewById(R.id.text_house_x51);
		text_area_x52_1 = (TextView) findViewById(R.id.text_area_x52_1);
		text_house_x40_3 = (TextView) findViewById(R.id.text_house_x40_3);
		text_existhouse_x41_3 = (TextView) findViewById(R.id.text_existhouse_x41_3);

		text_house_x53 = (TextView) findViewById(R.id.text_house_x53);
		text_house_x54 = (TextView) findViewById(R.id.text_house_x54);
		text_house_x55 = (TextView) findViewById(R.id.text_house_x55);
		text_house_x56 = (TextView) findViewById(R.id.text_house_x56);
		text_area_x57_1 = (TextView) findViewById(R.id.text_area_x57_1);
		text_house_x40_4 = (TextView) findViewById(R.id.text_house_x40_4);
		text_existhouse_x41_4 = (TextView) findViewById(R.id.text_existhouse_x41_4);

		text_house_x58 = (TextView) findViewById(R.id.text_house_x58);
		text_house_x59 = (TextView) findViewById(R.id.text_house_x59);
		text_house_x60 = (TextView) findViewById(R.id.text_house_x60);
		text_house_x61 = (TextView) findViewById(R.id.text_house_x61);
		text_area_x62_1 = (TextView) findViewById(R.id.text_area_x62_1);

		text_value_x69 = (TextView) findViewById(R.id.text_value_x69);

		text_prohouseaera_x63 = (TextView) findViewById(R.id.text_prohouseaera_x63);
	//	text_prohouseaera_x64 = (TextView) findViewById(R.id.text_prohouseaera_x64);
		text_value_x67 = (TextView) findViewById(R.id.text_value_x67);
		text_value_x71 = (TextView) findViewById(R.id.text_value_x71);
		text_prohouseaera_x75 = (TextView) findViewById(R.id.text_prohouseaera_x75);
		text_value_x76 = (TextView) findViewById(R.id.text_value_x76);
		text_structure_x7_3 = (TextView) findViewById(R.id.text_structure_x7_3);
		text_ass_value_x14_3 = (TextView) findViewById(R.id.text_ass_value_x14_3);
		text_value_x78 = (TextView) findViewById(R.id.text_value_x78);
		text_structure_x8_3 = (TextView) findViewById(R.id.text_structure_x8_3);
		text_ass_value_x15_3 = (TextView) findViewById(R.id.text_ass_value_x15_3);
		text_value_x80 = (TextView) findViewById(R.id.text_value_x80);
		text_structure_x81_3 = (TextView) findViewById(R.id.text_structure_x81_3);
		text_ass_value_x16_3 = (TextView) findViewById(R.id.text_ass_value_x16_3);
		text_value_x83 = (TextView) findViewById(R.id.text_value_x83);
		text_structure_x84_3 = (TextView) findViewById(R.id.text_structure_x84_3);
		text_ass_value_x86_3 = (TextView) findViewById(R.id.text_ass_value_x86_3);
		text_value_x87 = (TextView) findViewById(R.id.text_value_x87);
		text_structure_x200_3 = (TextView) findViewById(R.id.text_structure_x200_3);
		text_ass_value_x204_3 = (TextView) findViewById(R.id.text_ass_value_x204_3);
		text_value_x206 = (TextView) findViewById(R.id.text_value_x206);
		text_structure_x201_3 = (TextView) findViewById(R.id.text_structure_x201_3);
		text_ass_value_x205_3 = (TextView) findViewById(R.id.text_ass_value_x205_3);
		text_value_x207 = (TextView) findViewById(R.id.text_value_x207);
		
		    
		
		    
		    text_house_x17_5 = (TextView) findViewById(R.id.text_house_x17_5);
		    text_existhouse_x18_5 = (TextView) findViewById(R.id.text_existhouse_x18_5);
		    text_house_x900 = (TextView) findViewById(R.id.text_house_x900);
		    text_house_x902 = (TextView) findViewById(R.id.text_house_x902);
		    text_house_x903 = (TextView) findViewById(R.id.text_house_x903);
		    text_house_x904 = (TextView) findViewById(R.id.text_house_x904);
		    text_area_x905 = (TextView) findViewById(R.id.text_area_x905);   
		    
		    text_house_x17_6 = (TextView) findViewById(R.id.text_house_x17_6);
		    text_existhouse_x18_6 = (TextView) findViewById(R.id.text_existhouse_x18_6);
		    text_house_x906 = (TextView) findViewById(R.id.text_house_x906);
		    text_house_x907 = (TextView) findViewById(R.id.text_house_x907);
		    text_house_x908 = (TextView) findViewById(R.id.text_house_x908);
		    text_house_x909 = (TextView) findViewById(R.id.text_house_x909);
		    text_area_x910 = (TextView) findViewById(R.id.text_area_x910);  
		    
		    text_house_x17_7 = (TextView) findViewById(R.id.text_house_x17_7);
		    text_existhouse_x18_7 = (TextView) findViewById(R.id.text_existhouse_x18_7);
		    text_house_x911 = (TextView) findViewById(R.id.text_house_x911);
		    text_house_x912 = (TextView) findViewById(R.id.text_house_x912);
		    text_house_x913 = (TextView) findViewById(R.id.text_house_x913);
		    text_house_x914 = (TextView) findViewById(R.id.text_house_x914);
		    text_area_x915 = (TextView) findViewById(R.id.text_area_x915);  
		    
		    text_house_x17_8 = (TextView) findViewById(R.id.text_house_x17_8);
		    text_existhouse_x18_8 = (TextView) findViewById(R.id.text_existhouse_x18_8);
		    text_house_x916 = (TextView) findViewById(R.id.text_house_x916);
		    text_house_x917 = (TextView) findViewById(R.id.text_house_x917);
		    text_house_x918 = (TextView) findViewById(R.id.text_house_x918);
		    text_house_x919 = (TextView) findViewById(R.id.text_house_x919);
		    text_area_x920 = (TextView) findViewById(R.id.text_area_x920);  
		    
		    text_house_x17_9 = (TextView) findViewById(R.id.text_house_x17_9);
		    text_existhouse_x18_9 = (TextView) findViewById(R.id.text_existhouse_x18_9);
		    text_house_x921 = (TextView) findViewById(R.id.text_house_x921);
		    text_house_x922 = (TextView) findViewById(R.id.text_house_x922);
		    text_house_x923 = (TextView) findViewById(R.id.text_house_x923);
		    text_house_x924 = (TextView) findViewById(R.id.text_house_x924);
		    text_area_x925 = (TextView) findViewById(R.id.text_area_x925); 
		    
		    text_house_x17_10 = (TextView) findViewById(R.id.text_house_x17_10);
		    text_existhouse_x18_10 = (TextView) findViewById(R.id.text_existhouse_x18_10);
		    text_house_x926 = (TextView) findViewById(R.id.text_house_x926);
		    text_house_x927 = (TextView) findViewById(R.id.text_house_x927);
		    text_house_x928 = (TextView) findViewById(R.id.text_house_x928);
		    text_house_x929 = (TextView) findViewById(R.id.text_house_x929);
		    text_area_x930 = (TextView) findViewById(R.id.text_area_x930); 
		
		  
		    text_house_x40_5 = (TextView) findViewById(R.id.text_house_x40_5);
			text_existhouse_x41_5 = (TextView) findViewById(R.id.text_existhouse_x41_5);
		    text_house_x931 = (TextView) findViewById(R.id.text_house_x931);
			text_house_x932 = (TextView) findViewById(R.id.text_house_x932);
			text_house_x933 = (TextView) findViewById(R.id.text_house_x933);
			text_house_x934 = (TextView) findViewById(R.id.text_house_x934);
			text_area_x935 = (TextView) findViewById(R.id.text_area_x935);
			
			text_house_x40_6 = (TextView) findViewById(R.id.text_house_x40_6);
			text_existhouse_x41_6 = (TextView) findViewById(R.id.text_existhouse_x41_6);
		    text_house_x936 = (TextView) findViewById(R.id.text_house_x936);
			text_house_x937 = (TextView) findViewById(R.id.text_house_x937);
			text_house_x938 = (TextView) findViewById(R.id.text_house_x938);
			text_house_x939 = (TextView) findViewById(R.id.text_house_x939);
			text_area_x940 = (TextView) findViewById(R.id.text_area_x940);
			
			text_house_x40_7 = (TextView) findViewById(R.id.text_house_x40_7);
			text_existhouse_x41_7 = (TextView) findViewById(R.id.text_existhouse_x41_7);
		    text_house_x941 = (TextView) findViewById(R.id.text_house_x941);
			text_house_x942 = (TextView) findViewById(R.id.text_house_x942);
			text_house_x943 = (TextView) findViewById(R.id.text_house_x943);
			text_house_x944 = (TextView) findViewById(R.id.text_house_x944);
			text_area_x945 = (TextView) findViewById(R.id.text_area_x945);
			
		    
		    text_house_x40_8 = (TextView) findViewById(R.id.text_house_x40_8);
			text_existhouse_x41_8 = (TextView) findViewById(R.id.text_existhouse_x41_8);
		    text_house_x946 = (TextView) findViewById(R.id.text_house_x946);
			text_house_x947 = (TextView) findViewById(R.id.text_house_x947);
			text_house_x948 = (TextView) findViewById(R.id.text_house_x948);
			text_house_x949 = (TextView) findViewById(R.id.text_house_x949);
			text_area_x950 = (TextView) findViewById(R.id.text_area_x950);
			
			text_house_x40_9 = (TextView) findViewById(R.id.text_house_x40_9);
			text_existhouse_x41_9 = (TextView) findViewById(R.id.text_existhouse_x41_9);
		    text_house_x951 = (TextView) findViewById(R.id.text_house_x951);
			text_house_x952 = (TextView) findViewById(R.id.text_house_x952);
			text_house_x953 = (TextView) findViewById(R.id.text_house_x953);
			text_house_x954 = (TextView) findViewById(R.id.text_house_x954);
			text_area_x955 = (TextView) findViewById(R.id.text_area_x955);
			
			
			text_house_x40_10 = (TextView) findViewById(R.id.text_house_x40_10);
			text_existhouse_x41_10 = (TextView) findViewById(R.id.text_existhouse_x41_10);
		    text_house_x956 = (TextView) findViewById(R.id.text_house_x956);
			text_house_x957 = (TextView) findViewById(R.id.text_house_x957);
			text_house_x958 = (TextView) findViewById(R.id.text_house_x958);
			text_house_x959 = (TextView) findViewById(R.id.text_house_x959);
			text_area_x960 = (TextView) findViewById(R.id.text_area_x960);
		    
		   		    
		    

		// tab3
		edit_legalarea_x13_2 = (EditText) findViewById(R.id.edit_legalarea_x13_2);
		edit_prohouseaera_x63_2 = (EditText) findViewById(R.id.edit_prohouseaera_x63_2);
		edit_residuearea_x1363 = (EditText) findViewById(R.id.edit_residuearea_x1363);
		
		
		edit_value_x88_1 = (EditText) findViewById(R.id.edit_value_x88_1);
		edit_num_x89 = (EditText) findViewById(R.id.edit_num_x89);
		edit_num_x91 = (EditText) findViewById(R.id.edit_num_x91);
		edit_num_x93 = (EditText) findViewById(R.id.edit_num_x93);
		edit_cost_x95_1 = (EditText) findViewById(R.id.edit_cost_x95_1);
		edit_month_x96 = (EditText) findViewById(R.id.edit_month_x96);
		edit_month_x98 = (EditText) findViewById(R.id.edit_month_x98);
		edit_month_x100 = (EditText) findViewById(R.id.edit_month_x100);
		edit_month_x102 = (EditText) findViewById(R.id.edit_month_x102);
		edit_month_x104 = (EditText) findViewById(R.id.edit_month_x104);
		edit_cost_x170 = (EditText) findViewById(R.id.edit_cost_x170);
		edit_area_x164 = (EditText) findViewById(R.id.edit_area_x164);
		edit_rise_x111_1 = (EditText) findViewById(R.id.edit_rise_x111_1);
		edit_area_x165 = (EditText) findViewById(R.id.edit_area_x165);
		edit_area_x166 = (EditText) findViewById(R.id.edit_area_x166);
		edit_area_x167 = (EditText) findViewById(R.id.edit_area_x167);
		edit_area_x208 = (EditText) findViewById(R.id.edit_area_x208);
		edit_area_x209 = (EditText) findViewById(R.id.edit_area_x209);
		edit_money_x119 = (EditText) findViewById(R.id.edit_money_x119);
		edit_money_x120 = (EditText) findViewById(R.id.edit_money_x120);
		edit_value_x128 = (EditText) findViewById(R.id.edit_value_x128);
		edit_value_x129 = (EditText) findViewById(R.id.edit_value_x129);
		edit_value_x130 = (EditText) findViewById(R.id.edit_value_x130);
		edit_value_x131 = (EditText) findViewById(R.id.edit_value_x131);
		edit_money_x136 = (EditText) findViewById(R.id.edit_money_x136);
		edit_num_x139 = (EditText) findViewById(R.id.edit_num_x139);
		edit_money_x137 = (EditText) findViewById(R.id.edit_money_x137);
		edit_num_x140 = (EditText) findViewById(R.id.edit_num_x140);
		edit_money_x138 = (EditText) findViewById(R.id.edit_money_x138);
		edit_num_x141 = (EditText) findViewById(R.id.edit_num_x141);
		edit_value_x146 = (EditText) findViewById(R.id.edit_value_x146);
		edit_price_x148 = (EditText) findViewById(R.id.edit_price_x148);
		edit_housenum_x149 = (EditText) findViewById(R.id.edit_housenum_x149);
		edit_money_x168 = (EditText) findViewById(R.id.edit_money_x168);
		edit_money_x169 = (EditText) findViewById(R.id.edit_money_x169);
		
		edit_value_x88_2 = (EditText) findViewById(R.id.edit_value_x88_2);
		
		edit_x500 = (EditText) findViewById(R.id.edit_x500);
		edit_x501 = (EditText) findViewById(R.id.edit_x501);
		edit_x502 = (EditText) findViewById(R.id.edit_x502);
		edit_x503 = (EditText) findViewById(R.id.edit_x503);
		edit_x504 = (EditText) findViewById(R.id.edit_x504);
		
		
		edit_a501 = (EditText) findViewById(R.id.edit_a501);
		edit_a502 = (EditText) findViewById(R.id.edit_a502);
		edit_a503 = (EditText) findViewById(R.id.edit_a503);
		edit_a504 = (EditText) findViewById(R.id.edit_a504);
		edit_a505 = (EditText) findViewById(R.id.edit_a505);
		edit_a506 = (EditText) findViewById(R.id.edit_a506);
		
		edit_month_a98 = (EditText) findViewById(R.id.edit_month_a98);
		edit_month_a100 = (EditText) findViewById(R.id.edit_month_a100);
		edit_month_a102 = (EditText) findViewById(R.id.edit_month_a102);
		edit_month_a104 = (EditText) findViewById(R.id.edit_month_a104);
		edit_month_a106 = (EditText) findViewById(R.id.edit_month_a106);
		edit_month_a108 = (EditText) findViewById(R.id.edit_month_a108);
		
		
		edit_area_x800 = (EditText) findViewById(R.id.edit_area_x800);
		edit_area_x801 = (EditText) findViewById(R.id.edit_area_x801);
		edit_area_x802 = (EditText) findViewById(R.id.edit_area_x802);
		edit_area_x803 = (EditText) findViewById(R.id.edit_area_x803);
		
		text_cost_x95_6 = (TextView) findViewById(R.id.text_cost_x95_6);
		text_cost_x95_7 = (TextView) findViewById(R.id.text_cost_x95_7);
		text_cost_x95_8 = (TextView) findViewById(R.id.text_cost_x95_8);
		text_cost_x95_9 = (TextView) findViewById(R.id.text_cost_x95_9);
		text_cost_x95_10 = (TextView) findViewById(R.id.text_cost_x95_10);
		text_cost_x95_11 = (TextView) findViewById(R.id.text_cost_x95_11);
		
		
		text_cost_a99_000 = (TextView) findViewById(R.id.text_cost_a99_000);
		text_cost_a101_000 = (TextView) findViewById(R.id.text_cost_a101_000);
		text_cost_a103_000 = (TextView) findViewById(R.id.text_cost_a103_000);
		text_cost_a105_000 = (TextView) findViewById(R.id.text_cost_a105_000);
		text_cost_a107_000 = (TextView) findViewById(R.id.text_cost_a107_000);
		text_cost_a109_000 = (TextView) findViewById(R.id.text_cost_a109_000);
		

		text_area_x46 = (TextView) findViewById(R.id.text_area_x46);

		text_value_x88_3 = (TextView) findViewById(R.id.text_value_x88_3);
		text_cost_x95_2 = (TextView) findViewById(R.id.text_cost_x95_2);
		text_cost_x95_3 = (TextView) findViewById(R.id.text_cost_x95_3);
		text_cost_x95_4 = (TextView) findViewById(R.id.text_cost_x95_4);
		text_cost_x95_5 = (TextView) findViewById(R.id.text_cost_x95_5);

		text_rise_x111_2 = (TextView) findViewById(R.id.text_rise_x111_2);
		text_rise_x111_3 = (TextView) findViewById(R.id.text_rise_x111_3);
		text_rise_x111_4 = (TextView) findViewById(R.id.text_rise_x111_4);
		text_rise_x111_5 = (TextView) findViewById(R.id.text_rise_x111_5);
		text_rise_x111_6 = (TextView) findViewById(R.id.text_rise_x111_6);

		text_cost_x90 = (TextView) findViewById(R.id.text_cost_x90);
		text_cost_x92 = (TextView) findViewById(R.id.text_cost_x92);
		text_cost_x94 = (TextView) findViewById(R.id.text_cost_x94);
		text_cost_x97 = (TextView) findViewById(R.id.text_cost_x97);
		text_cost_x99 = (TextView) findViewById(R.id.text_cost_x99);
		text_cost_x101 = (TextView) findViewById(R.id.text_cost_x101);
		text_cost_x103 = (TextView) findViewById(R.id.text_cost_x103);
		text_cost_x105 = (TextView) findViewById(R.id.text_cost_x105);
		text_legalarea_x13_3 = (TextView) findViewById(R.id.text_legalarea_x13_3);
		text_cost_x106 = (TextView) findViewById(R.id.text_cost_x106);
		text_cost_x107 = (TextView) findViewById(R.id.text_cost_x107);
		text_cost_x108 = (TextView) findViewById(R.id.text_cost_x108);
		text_cost_x109 = (TextView) findViewById(R.id.text_cost_x109);
		text_structure_x7_4 = (TextView) findViewById(R.id.text_structure_x7_4);
		text_value_x14_4 = (TextView) findViewById(R.id.text_value_x14_4);
		text_cost_x112 = (TextView) findViewById(R.id.text_cost_x112);
		text_structure_x8_4 = (TextView) findViewById(R.id.text_structure_x8_4);
		text_value_x15_4 = (TextView) findViewById(R.id.text_value_x15_4);
		text_cost_x113 = (TextView) findViewById(R.id.text_cost_x113);
		text_structure_x81_4 = (TextView) findViewById(R.id.text_structure_x81_4);
		text_value_x16_4 = (TextView) findViewById(R.id.text_value_x16_4);
		text_cost_x115 = (TextView) findViewById(R.id.text_cost_x115);
		text_structure_x84_4 = (TextView) findViewById(R.id.text_structure_x84_4);
		text_value_x86_4 = (TextView) findViewById(R.id.text_value_x86_4);
		text_cost_x118 = (TextView) findViewById(R.id.text_cost_x118);
		text_structure_x200_4 = (TextView) findViewById(R.id.text_structure_x200_4);
		text_value_x204_4 = (TextView) findViewById(R.id.text_value_x204_4);
		text_cost_x210 = (TextView) findViewById(R.id.text_cost_x210);
		text_structure_x201_4 = (TextView) findViewById(R.id.text_structure_x201_4);
		text_value_x205_4 = (TextView) findViewById(R.id.text_value_x205_4);
		text_cost_x211 = (TextView) findViewById(R.id.text_cost_x211);
					
		
		
		text_value_x132 = (TextView) findViewById(R.id.text_value_x132);
		text_value_x133 = (TextView) findViewById(R.id.text_value_x133);
		text_value_x134 = (TextView) findViewById(R.id.text_value_x134);
		text_value_x135 = (TextView) findViewById(R.id.text_value_x135);
		text_value_x142 = (TextView) findViewById(R.id.text_value_x142);
		text_value_x143 = (TextView) findViewById(R.id.text_value_x143);
		text_value_x144 = (TextView) findViewById(R.id.text_value_x144);
		text_value_x147 = (TextView) findViewById(R.id.text_value_x147);
		text_money_x150 = (TextView) findViewById(R.id.text_money_x150);
		text_money_x151 = (TextView) findViewById(R.id.text_money_x151);

		// tab4
		edit_area_x152_1 = (EditText) findViewById(R.id.edit_area_x152_1);
		edit_value_x153 = (EditText) findViewById(R.id.edit_value_x153);
		edit_area_x155_1 = (EditText) findViewById(R.id.edit_area_x155_1);
		edit_value_x156 = (EditText) findViewById(R.id.edit_value_x156);
		edit_area_x158_1 = (EditText) findViewById(R.id.edit_area_x158_1);
		edit_value_x159 = (EditText) findViewById(R.id.edit_value_x159);
		edit_area_x212_1 = (EditText) findViewById(R.id.edit_area_x212_1);
		edit_value_x215 = (EditText) findViewById(R.id.edit_value_x215);
		edit_area_x213_1 = (EditText) findViewById(R.id.edit_area_x213_1);
		edit_value_x216 = (EditText) findViewById(R.id.edit_value_x216);
		edit_area_x214_1 = (EditText) findViewById(R.id.edit_area_x214_1);
		edit_value_x217 = (EditText) findViewById(R.id.edit_value_x217);
		edit_value_x270 = (EditText) findViewById(R.id.edit_value_x270);
		edit_value_x271 = (EditText) findViewById(R.id.edit_value_x271);
		edit_value_x272 = (EditText) findViewById(R.id.edit_value_x272);
		edit_value_x273 = (EditText) findViewById(R.id.edit_value_x273);
		edit_value_x274 = (EditText) findViewById(R.id.edit_value_x274);
		edit_value_x275 = (EditText) findViewById(R.id.edit_value_x275);

		text_area_x152_2 = (TextView) findViewById(R.id.text_area_x152_2);
		text_area_x155_2 = (TextView) findViewById(R.id.text_area_x155_2);
		text_area_x158_2 = (TextView) findViewById(R.id.text_area_x158_2);
		text_area_x212_2 = (TextView) findViewById(R.id.text_area_x212_2);
		text_area_x213_2 = (TextView) findViewById(R.id.text_area_x213_2);
		text_area_x214_2 = (TextView) findViewById(R.id.text_area_x214_2);

		text_structure_x7_5 = (TextView) findViewById(R.id.text_structure_x7_5);
		text_allowance_x154 = (TextView) findViewById(R.id.text_allowance_x154);
		text_structure_x8_5 = (TextView) findViewById(R.id.text_structure_x8_5);
		text_allowance_x157 = (TextView) findViewById(R.id.text_allowance_x157);
		text_structure_x81_5 = (TextView) findViewById(R.id.text_structure_x81_5);
		text_allowance_x160 = (TextView) findViewById(R.id.text_allowance_x160);
		text_structure_x84_5 = (TextView) findViewById(R.id.text_structure_x84_5);
		text_allowance_x223 = (TextView) findViewById(R.id.text_allowance_x223);
		text_structure_x200_5 = (TextView) findViewById(R.id.text_structure_x200_5);
		text_allowance_x219 = (TextView) findViewById(R.id.text_allowance_x219);
		text_structure_x201_5 = (TextView) findViewById(R.id.text_structure_x201_5);
		text_allowance_x220 = (TextView) findViewById(R.id.text_allowance_x220);
		text_totallowance_x161 = (TextView) findViewById(R.id.text_totallowance_x161);
		text_structure_x7_6 = (TextView) findViewById(R.id.text_structure_x7_6);
		text_allowance_x276 = (TextView) findViewById(R.id.text_allowance_x276);
		text_structure_x8_6 = (TextView) findViewById(R.id.text_structure_x8_6);
		text_allowance_x277 = (TextView) findViewById(R.id.text_allowance_x277);
		text_structure_x81_6 = (TextView) findViewById(R.id.text_structure_x81_6);
		text_allowance_x278 = (TextView) findViewById(R.id.text_allowance_x278);
		text_structure_x84_6 = (TextView) findViewById(R.id.text_structure_x84_6);
		text_allowance_x279 = (TextView) findViewById(R.id.text_allowance_x279);
		text_structure_x200_6 = (TextView) findViewById(R.id.text_structure_x200_6);
		text_allowance_x280 = (TextView) findViewById(R.id.text_allowance_x280);
		text_structure_x201_6 = (TextView) findViewById(R.id.text_structure_x201_6);
		text_allowance_x281 = (TextView) findViewById(R.id.text_allowance_x281);
		text_totallowance_x282 = (TextView) findViewById(R.id.text_totallowance_x282);
		text_totallowance_x162 = (TextView) findViewById(R.id.text_totallowance_x162);
		text_totallowance_x163 = (TextView) findViewById(R.id.text_totallowance_x163);
		text_totallowance_x162163 = (TextView) findViewById(R.id.text_totallowance_x162163);
		text_totallowance_x76_2 = (TextView) findViewById(R.id.text_totallowance_x76_2);

		} catch (MyException e) {
			ExceptionHelper.Operate(e, true, this);
		}
	
	}
		 
	private Button initBtn(int id) {
		Button btn = (Button) findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	private Spinner initSp(int id,String enmucode,boolean allowEmptyRow) throws MyException{
		Spinner sp = (Spinner)findViewById(id);
		PublicBus.spinnerEnumBinding(sp,enmucode,allowEmptyRow,getHelper(),this);
		return sp;
	}

	/** 处理窗体传过来的数据 */
	private void operateIntent() {
		Intent intent = this.getIntent();
		in_id = intent.getStringExtra(KEY.in_id);
		in_hid = intent.getStringExtra(KEY.in_hid);
		hid = intent.getStringExtra(KEY.hid);
		pid = intent.getStringExtra(KEY.in_pid);
		c_status = intent.getStringExtra(KEY.c_status);
		if (StringHelper.isNullOrEmpty(in_id)) {
			isAdd = true;
			entity = new HouseList();
			c = new HouseConfirm();

			setEntityToUI(entity, c);

		} else {
			isAdd = false;
			entity = new HouseList();
			c = new HouseConfirm();

			JTGRProAsyncTask task = new JTGRProAsyncTask(activity);
			task.execute(in_id);
			setEntityToUI(entity, c);

			// 屏蔽保存 不能修改
		//	Button btn = (Button) findViewById(R.id.btn_title_right);
		//	btn.setVisibility(View.INVISIBLE);
		}
	}
		 
	/** 将国有个人 产权调换补偿计算单的信息展现到UI */
	public void setEntityToUI(HouseList entity,HouseConfirm c){
		
		DecimalFormat DouToStr = new DecimalFormat("0.00");
		
		// tab1
		entity.setHouseConfirm(c);
		edit_confirmarea_x12.setText(DouToStr.format(entity.getX12()));

		edit_cardno_x2.setText(entity.getX2());
		edit_linktel_x3.setText(entity.getX3());
		
		text_contractno_x185.setText(entity.getX185());
		text_levyname_x1.setText(c.getX1());
		text_maininquier_x218.setText(c.getX218());
		text_levyaddress_x4.setText(c.getX4());
		text_housetotalarea_x5.setText(DouToStr.format(entity.getX5_100()));
		text_purpose_x6.setText(c.getX6());
		text_structure_x7_1.setText(c.getX7());
		text_area_x9.setText(c.getX9()+"");
		text_structure_x8_1.setText(c.getX8());
		text_area_x10.setText(c.getX10()+"");
		text_structure_x81_1.setText(c.getX81());
		text_area_x184.setText(c.getX184()+"");
		text_structure_x84_1.setText(c.getX84());
		text_area_x188.setText(c.getX188()+"");
		text_structure_x200_1.setText(c.getX200());
		text_area_x202.setText(c.getX202()+"");
		text_structure_x201_1.setText(c.getX201());
		text_area_x203.setText(c.getX203()+"");
		text_registerarea_x11.setText(c.getX11()+"");
		text_legalarea_x13.setText(DouToStr.format(entity.getX13_100()));
		text_structure_x7_2.setText(c.getX7());
		text_value_x14_1.setText(c.getX14()+"");
		text_structure_x8_2.setText(c.getX8());
		text_value_x15_1.setText(c.getX15()+"");
		text_structure_x81_2.setText(c.getX81());
		text_value_x16_1.setText(c.getX16()+"");
		text_structure_x84_2.setText(c.getX84());
		text_value_x86_1.setText(c.getX86()+"");
		text_structure_x200_2.setText(c.getX200());
		text_value_x204_1.setText(c.getX204()+"");
		text_structure_x201_2.setText(c.getX201());
		text_value_x205_1.setText(c.getX205()+"");

		// tab2
		edit_prohouseaera_x64.setText(DouToStr.format(entity.getX64input()));
		
		
		edit_value_x19_1.setText(DouToStr.format(entity.getX19_1()));
		edit_value_x19_2.setText(DouToStr.format(entity.getX19_2()));
		edit_value_x19_3.setText(DouToStr.format(entity.getX19_3()));
		edit_value_x19_4.setText(DouToStr.format(entity.getX19_4()));
		edit_value_x42_1.setText(DouToStr.format(entity.getX42_1()));
		edit_value_x42_2.setText(DouToStr.format(entity.getX42_2()));
		edit_value_x42_3.setText(DouToStr.format(entity.getX42_3()));
		edit_value_x42_4.setText(DouToStr.format(entity.getX42_4()));
		
		
		
		edit_value_x19_5.setText(DouToStr.format(entity.getX19_5()));
		edit_value_x19_6.setText(DouToStr.format(entity.getX19_6()));
		edit_value_x19_7.setText(DouToStr.format(entity.getX19_7()));
		edit_value_x19_8.setText(DouToStr.format(entity.getX19_8()));
		edit_value_x19_9.setText(DouToStr.format(entity.getX19_9()));
		edit_value_x19_10.setText(DouToStr.format(entity.getX19_10()));
		
		edit_value_x42_5.setText(DouToStr.format(entity.getX42_5()));
		edit_value_x42_6.setText(DouToStr.format(entity.getX42_6()));
		edit_value_x42_7.setText(DouToStr.format(entity.getX42_7()));
		edit_value_x42_8.setText(DouToStr.format(entity.getX42_8()));
		edit_value_x42_9.setText(DouToStr.format(entity.getX42_9()));
		edit_value_x42_10.setText(DouToStr.format(entity.getX42_10()));
		
				
				
		text_house_x17_1.setText(entity.getX17_1());
		text_existhouse_x18_1.setText(entity.getX18_1());
		
		text_house_x20.setText(entity.getX20());
		text_house_x21.setText(entity.getX21());
		text_house_x22.setText(entity.getX22());
		text_house_x23.setText(entity.getX23());
		text_area_x24.setText(DouToStr.format(entity.getX24()));
		text_house_x17_2.setText(entity.getX17_2());
		text_existhouse_x18_2.setText(entity.getX18_2());
		
		text_house_x25.setText(entity.getX25());
		text_house_x26.setText(entity.getX26());
		text_house_x27.setText(entity.getX27());
		text_house_x28.setText(entity.getX28());
		text_area_x29.setText(DouToStr.format(entity.getX29()));
		text_house_x17_3.setText(entity.getX17_3());
		text_existhouse_x18_3.setText(entity.getX18_3());
		
		text_house_x30.setText(entity.getX30());
		text_house_x31.setText(entity.getX31());
		text_house_x32.setText(entity.getX32());
		text_house_x33.setText(entity.getX33());
		text_area_x34.setText(DouToStr.format(entity.getX34()));
		text_house_x17_4.setText(entity.getX17_4());
		text_existhouse_x18_4.setText(entity.getX18_4());
		
		text_house_x35.setText(entity.getX35());
		text_house_x36.setText(entity.getX36());
		text_house_x37.setText(entity.getX37());
		text_house_x38.setText(entity.getX38());
		text_area_x39.setText(DouToStr.format(entity.getX39()));
		text_house_x40_1.setText(entity.getX40_1());
		text_existhouse_x41_1.setText(entity.getX41_1());
		
		text_house_x43.setText(entity.getX43());
		text_house_x44.setText(entity.getX44());
		text_house_x45.setText(entity.getX45());
		text_house_x46.setText(entity.getX46());
		text_area_x47_1.setText(DouToStr.format(entity.getX47()));
		text_house_x40_2.setText(entity.getX40_2());
		text_existhouse_x41_2.setText(entity.getX41_2());
		
		text_house_x48.setText(entity.getX48());
		text_house_x49.setText(entity.getX49());
		text_house_x50.setText(entity.getX50());
		text_house_x51.setText(entity.getX51());
		text_area_x52_1.setText(DouToStr.format(entity.getX52()));
		text_house_x40_3.setText(entity.getX40_3());
		text_existhouse_x41_3.setText(entity.getX41_3());
		
		text_house_x53.setText(entity.getX53());
		text_house_x54.setText(entity.getX54());
		text_house_x55.setText(entity.getX55());
		text_house_x56.setText(entity.getX56());
		text_area_x57_1.setText(DouToStr.format(entity.getX57()));
		text_house_x40_4.setText(entity.getX40_4());
		text_existhouse_x41_4.setText(entity.getX41_4());
		
		text_house_x58.setText(entity.getX58());
		text_house_x59.setText(entity.getX59());
		text_house_x60.setText(entity.getX60());
		text_house_x61.setText(entity.getX61());
		text_area_x62_1.setText(DouToStr.format(entity.getX62()));
		

		edit_prohouseaera_x65.setText(DouToStr.format(entity.getX65()));
		edit_value_x66.setText(DouToStr.format(entity.getX66()));
		edit_prohouseaera_x68.setText(DouToStr.format(entity.getX68()));
		edit_value_x69_1.setText(DouToStr.format(entity.getX69()));
		edit_percent_x70.setText(entity.getX70() + "");
		edit_prohouseaera_x72.setText(DouToStr.format(entity.getX72()));

		edit_percent_x74.setText(entity.getX74() + "");
		edit_prohouseaera_x77.setText(DouToStr.format(entity.getX77()));
		edit_prohouseaera_x79.setText(DouToStr.format(entity.getX79()));
		edit_prohouseaera_x82.setText(DouToStr.format(entity.getX82()));
		edit_prohouseaera_x85.setText(DouToStr.format(entity.getX85()));
		edit_prohouseaera_x221.setText(DouToStr.format(entity.getX221()));
		edit_prohouseaera_x222.setText(DouToStr.format(entity.getX222()));

		text_value_x69.setText(DouToStr.format(entity.getX69()));
		
		text_prohouseaera_x63.setText(DouToStr.format(entity.getX63()));
	//	text_prohouseaera_x64.setText(entity.getX64_100()+"");
		text_value_x67.setText(DouToStr.format(entity.getX67()));
		text_value_x71.setText(DouToStr.format(entity.getX71()));
		text_prohouseaera_x75.setText(DouToStr.format(entity.getX75()));
		text_value_x76.setText(DouToStr.format(entity.getX76()));
		text_structure_x7_3.setText(c.getX7());
		text_ass_value_x14_3.setText(c.getX14()+"");
		text_value_x78.setText(DouToStr.format(entity.getX78()));
		text_structure_x8_3.setText(c.getX8());
		text_ass_value_x15_3.setText(c.getX15()+"");
		text_value_x80.setText(DouToStr.format(entity.getX80()));
		text_structure_x81_3.setText(c.getX81());
		text_ass_value_x16_3.setText(c.getX16()+"");
		text_value_x83.setText(DouToStr.format(entity.getX83()));
		text_structure_x84_3.setText(c.getX84());
		text_ass_value_x86_3.setText(c.getX86()+"");
		text_value_x87.setText(DouToStr.format(entity.getX87()));
		text_structure_x200_3.setText(c.getX200());
		text_ass_value_x204_3.setText(c.getX204()+"");
		text_value_x206.setText(DouToStr.format(entity.getX206()));
		text_structure_x201_3.setText(c.getX201());
		text_ass_value_x205_3.setText(c.getX205()+"");
		text_value_x207.setText(DouToStr.format(entity.getX207()));
		

		text_house_x17_5.setText(entity.getX17_5());
		text_existhouse_x18_5.setText(entity.getX18_5());
		text_house_x900.setText(entity.getX900());
		text_house_x902.setText(entity.getX902());
		text_house_x903.setText(entity.getX903());
		text_house_x904.setText(entity.getX904());
		text_area_x905.setText(DouToStr.format(entity.getX905()));
		
		
		text_house_x17_6.setText(entity.getX17_6());
		text_existhouse_x18_6.setText(entity.getX18_6());
		text_house_x906.setText(entity.getX906());
		text_house_x907.setText(entity.getX907());
		text_house_x908.setText(entity.getX908());
		text_house_x909.setText(entity.getX909());
		text_area_x910.setText(DouToStr.format(entity.getX910()));
		
		
		text_house_x17_7.setText(entity.getX17_7());
		text_existhouse_x18_7.setText(entity.getX18_7());
		text_house_x911.setText(entity.getX911());
		text_house_x912.setText(entity.getX912());
		text_house_x913.setText(entity.getX913());
		text_house_x914.setText(entity.getX914());
		text_area_x915.setText(DouToStr.format(entity.getX915()));
		
		
		text_house_x17_8.setText(entity.getX17_8());
		text_existhouse_x18_8.setText(entity.getX18_8());
		text_house_x916.setText(entity.getX916());
		text_house_x917.setText(entity.getX917());
		text_house_x918.setText(entity.getX918());
		text_house_x919.setText(entity.getX919());
		text_area_x920.setText(DouToStr.format(entity.getX920()));
		
		
		text_house_x17_9.setText(entity.getX17_9());
		text_existhouse_x18_9.setText(entity.getX18_9());
		text_house_x921.setText(entity.getX921());
		text_house_x922.setText(entity.getX922());
		text_house_x923.setText(entity.getX923());
		text_house_x924.setText(entity.getX924());
		text_area_x925.setText(DouToStr.format(entity.getX925()));
		
		
		text_house_x17_10.setText(entity.getX17_10());
		text_existhouse_x18_10.setText(entity.getX18_10());
		text_house_x926.setText(entity.getX926());
		text_house_x927.setText(entity.getX927());
		text_house_x928.setText(entity.getX928());
		text_house_x929.setText(entity.getX929());
		text_area_x930.setText(DouToStr.format(entity.getX930()));
		
		text_house_x40_5.setText(entity.getX40_5());
	    text_existhouse_x41_5.setText(entity.getX41_5());
	    text_house_x931.setText(entity.getX931());
		text_house_x932.setText(entity.getX932());
		text_house_x933.setText(entity.getX933());
		text_house_x934.setText(entity.getX934());
		text_area_x935.setText(DouToStr.format(entity.getX935()));
			
		text_house_x40_6.setText(entity.getX40_6());
		text_existhouse_x41_6.setText(entity.getX41_6());
		text_house_x936.setText(entity.getX936());
		text_house_x937.setText(entity.getX937());
		text_house_x938.setText(entity.getX938());
		text_house_x939.setText(entity.getX939());
		text_area_x940.setText(DouToStr.format(entity.getX940()));
				
				
		text_house_x40_7.setText(entity.getX40_7());
		text_existhouse_x41_7.setText(entity.getX41_7());
		text_house_x941.setText(entity.getX941());
		text_house_x942.setText(entity.getX942());
		text_house_x943.setText(entity.getX943());
		text_house_x944.setText(entity.getX944());
		text_area_x945.setText(DouToStr.format(entity.getX945()));
			
			    
	    text_house_x40_8.setText(entity.getX40_8());
		text_existhouse_x41_8.setText(entity.getX41_8());
		text_house_x946.setText(entity.getX946());
		text_house_x947.setText(entity.getX947());
		text_house_x948.setText(entity.getX948());
		text_house_x949.setText(entity.getX949());
		text_area_x950.setText(DouToStr.format(entity.getX950()));
		
		text_house_x40_9.setText(entity.getX40_9());
		text_existhouse_x41_9.setText(entity.getX41_9());
		text_house_x951.setText(entity.getX951());
		text_house_x952.setText(entity.getX952());
		text_house_x953.setText(entity.getX953());
		text_house_x954.setText(entity.getX954());
		text_area_x955.setText(DouToStr.format(entity.getX955()));
			
		text_house_x40_10.setText(entity.getX40_10());
		text_existhouse_x41_10.setText(entity.getX41_10());
		text_house_x956.setText(entity.getX956());
		text_house_x957.setText(entity.getX957());
		text_house_x958.setText(entity.getX958());
		text_house_x959.setText(entity.getX959());
		text_area_x960.setText(DouToStr.format(entity.getX960()));
		

		// tab3
		

		//搬迁费类型
		PublicBus.spinnerSetValue(sp_bqflagvalue, entity.getBqflagvalue());
		
	//	 edit_legalarea_x13_2,edit_prohouseaera_x63_2,edit_residuearea_x1363,
		
		edit_legalarea_x13_2.setText(DouToStr.format(entity.getA1000()));
		edit_prohouseaera_x63_2.setText(DouToStr.format(entity.getA1001()));
		edit_residuearea_x1363.setText(DouToStr.format(entity.getA1002()));
		

		edit_value_x88_1.setText(DouToStr.format(entity.getX88()));
		edit_num_x89.setText(entity.getX89()+"");
		
		edit_num_x91.setText(entity.getX91()+"");
		edit_num_x93.setText(entity.getX93()+"");		
		edit_cost_x95_1.setText(DouToStr.format(entity.getX95()));
		edit_month_x96.setText(entity.getX96()+"");
		
		edit_month_x98.setText(entity.getX98()+"");
		edit_month_x100.setText(entity.getX100()+"");
		edit_month_x102.setText(entity.getX102()+"");
		edit_month_x104.setText(entity.getX104()+"");
		edit_cost_x170.setText(DouToStr.format(entity.getX170()));
		edit_area_x164.setText(DouToStr.format(entity.getX164()));
		edit_rise_x111_1.setText(entity.getX111()+"");
		edit_area_x165.setText(DouToStr.format(entity.getX165()));
		
		edit_area_x166.setText(DouToStr.format(entity.getX166()));
		edit_area_x167.setText(DouToStr.format(entity.getX167()));
		edit_area_x208.setText(DouToStr.format(entity.getX208()));
		edit_area_x209.setText(DouToStr.format(entity.getX209()));
		edit_money_x119.setText(DouToStr.format(entity.getX119()));		
		edit_money_x120.setText(DouToStr.format(entity.getX120()));
		
		edit_value_x128.setText(DouToStr.format(entity.getX128()));
		edit_value_x129.setText(DouToStr.format(entity.getX129()));
		edit_value_x130.setText(DouToStr.format(entity.getX130()));
		edit_value_x131.setText(DouToStr.format(entity.getX131()));
		edit_money_x136.setText(DouToStr.format(entity.getX136()));
		edit_num_x139.setText(entity.getX139()+"");
		edit_money_x137.setText(DouToStr.format(entity.getX137()));
		edit_num_x140.setText(entity.getX140()+"");
		edit_money_x138.setText(DouToStr.format(entity.getX138()));
		edit_num_x141.setText(entity.getX141()+"");
		edit_value_x146.setText(DouToStr.format(entity.getX146()));
		edit_price_x148.setText(DouToStr.format(entity.getX148()));
		edit_housenum_x149.setText(entity.getX149()+"");
		edit_money_x168.setText(DouToStr.format(entity.getX168()));
		edit_money_x169.setText(DouToStr.format(entity.getX169()));
		
		edit_x500.setText(DouToStr.format(entity.getX500()));
		edit_x501.setText(DouToStr.format(entity.getX501()));
		edit_x502.setText(DouToStr.format(entity.getX502()));
		edit_x503.setText(DouToStr.format(entity.getX503()));
		edit_x504.setText(DouToStr.format(entity.getX504()));
		
		
		edit_a501.setText(entity.getA501() + "");
		edit_a502.setText(entity.getA502() + "");
		edit_a503.setText(entity.getA503() + "");
		edit_a504.setText(entity.getA504() + "");
		edit_a505.setText(entity.getA505() + "");
		edit_a506.setText(entity.getA506() + "");
		
		
		edit_month_a98.setText(entity.getA98() + "");
		edit_month_a100.setText(entity.getA100() + "");
		edit_month_a102.setText(entity.getA102() + "");
		edit_month_a104.setText(entity.getA104() + "");
		edit_month_a106.setText(entity.getA106() + "");
		edit_month_a108.setText(entity.getA108() + "");
		
		
		
		
		edit_value_x88_2.setText(DouToStr.format(entity.getX88()));
						
		edit_area_x800.setText(DouToStr.format(entity.getX800()));
		edit_area_x801.setText(DouToStr.format(entity.getX801()));
		edit_area_x802.setText(DouToStr.format(entity.getX802()));
		edit_area_x803.setText(DouToStr.format(entity.getX803()));
		
		
		text_area_x46.setText(c.getX46()+"");
		
	//	text_value_x88_2.setText(entity.getX88()+"");
		text_value_x88_3.setText(DouToStr.format(entity.getX88()));
		text_cost_x95_2.setText(DouToStr.format(entity.getX95()));
		text_cost_x95_3.setText(DouToStr.format(entity.getX95()));
		text_cost_x95_4.setText(DouToStr.format(entity.getX95()));
		text_cost_x95_5.setText(DouToStr.format(entity.getX95()));
		
		
		text_cost_x95_6.setText(entity.getX95() + "");
		text_cost_x95_7.setText(entity.getX95() + "");
		text_cost_x95_8.setText(entity.getX95() + "");
		text_cost_x95_9.setText(entity.getX95() + "");
		text_cost_x95_10.setText(entity.getX95() + "");
		text_cost_x95_11.setText(entity.getX95() + "");
		
		
		text_cost_a99_000.setText(DouToStr.format(entity.getA99_000()));
		text_cost_a101_000.setText(DouToStr.format(entity.getA101_000()));
		text_cost_a103_000.setText(DouToStr.format(entity.getA103_000()));
		text_cost_a105_000.setText(DouToStr.format(entity.getA105_000()));
		text_cost_a107_000.setText(DouToStr.format(entity.getA107_000()));
		text_cost_a109_000.setText(DouToStr.format(entity.getA109_000()));
		
		text_rise_x111_2.setText(entity.getX111()+"");
		text_rise_x111_3.setText(entity.getX111()+"");
		text_rise_x111_4.setText(entity.getX111()+"");
		text_rise_x111_5.setText(entity.getX111()+"");
		text_rise_x111_6.setText(entity.getX111()+"");
		
		
		text_cost_x90.setText(DouToStr.format(entity.getX90_100()));
		text_cost_x92.setText(DouToStr.format(entity.getX92_100()));
		text_cost_x94.setText(DouToStr.format(entity.getX94_100()));
		
		text_cost_x97.setText(DouToStr.format(entity.getX97_100()));
		text_cost_x99.setText(DouToStr.format(entity.getX99_100()));
		text_cost_x101.setText(DouToStr.format(entity.getX101_100()));
		text_cost_x103.setText(DouToStr.format(entity.getX103_100()));
		text_cost_x105.setText(DouToStr.format(entity.getX105_100()));
		
		text_legalarea_x13_3.setText(DouToStr.format(entity.getX13_100()));
		text_cost_x106.setText(DouToStr.format(entity.getX106_100()));
		text_cost_x107.setText(DouToStr.format(entity.getX107()));
		text_cost_x108.setText(DouToStr.format(entity.getX108()));
		text_cost_x109.setText(DouToStr.format(entity.getX109()));
		text_structure_x7_4.setText(c.getX7()+"");
		text_value_x14_4.setText(c.getX14()+"");
		text_cost_x112.setText(DouToStr.format(entity.getX112()));
		text_structure_x8_4.setText(c.getX8());	
		text_value_x15_4.setText(c.getX15()+"");	
		text_cost_x113.setText(DouToStr.format(entity.getX113()));
		text_structure_x81_4.setText(c.getX81()+"");
		text_value_x16_4.setText(c.getX16()+"");
		text_cost_x115.setText(DouToStr.format(entity.getX115()));
		text_structure_x84_4.setText(c.getX84());	
		text_value_x86_4.setText(c.getX86()+"");
		text_cost_x118.setText(DouToStr.format(entity.getX118()));
		text_structure_x200_4.setText(c.getX200());	
		text_value_x204_4.setText(c.getX204()+"");	
		text_cost_x210.setText(DouToStr.format(entity.getX210()));
		text_structure_x201_4.setText(c.getX201());
		text_value_x205_4.setText(c.getX205()+"");
		text_cost_x211.setText(DouToStr.format(entity.getX211()));
		
		text_value_x132.setText(DouToStr.format(entity.getX132_100()));
		text_value_x133.setText(DouToStr.format(entity.getX133_100()));
		text_value_x134.setText(DouToStr.format(entity.getX134_100()));
		text_value_x135.setText(DouToStr.format(entity.getX135_100()));
		text_value_x142.setText(DouToStr.format(entity.getX142_100()));
		text_value_x143.setText(DouToStr.format(entity.getX143_100()));
		text_value_x144.setText(DouToStr.format(entity.getX144_100()));
		text_value_x147.setText(DouToStr.format(entity.getX147_100()));
		text_money_x150.setText(DouToStr.format(entity.getX150()));
		text_money_x151.setText(DouToStr.format(entity.getX151_100()));
		
		// tab4
		edit_area_x152_1.setText(DouToStr.format(entity.getX152()));
		edit_value_x153.setText(DouToStr.format(entity.getX153()));
		edit_area_x155_1.setText(DouToStr.format(entity.getX155()));
		edit_value_x156.setText(DouToStr.format(entity.getX156()));
		edit_area_x158_1.setText(DouToStr.format(entity.getX158()));
		edit_value_x159.setText(DouToStr.format(entity.getX159()));
		edit_area_x212_1.setText(DouToStr.format(entity.getX212()));
		edit_value_x215.setText(DouToStr.format(entity.getX215()));
		edit_area_x213_1.setText(DouToStr.format(entity.getX213()));
		edit_value_x216.setText(DouToStr.format(entity.getX216()));
		edit_area_x214_1.setText(DouToStr.format(entity.getX214()));
		edit_value_x217.setText(DouToStr.format(entity.getX217()));
		
		edit_value_x270.setText(DouToStr.format(entity.getX270()));
		edit_value_x271.setText(DouToStr.format(entity.getX271()));
		edit_value_x272.setText(DouToStr.format(entity.getX272()));
		edit_value_x273.setText(DouToStr.format(entity.getX273()));
		edit_value_x274.setText(DouToStr.format(entity.getX274()));
		edit_value_x275.setText(DouToStr.format(entity.getX275()));
				
		text_area_x152_2.setText(DouToStr.format(entity.getX152()));
		text_area_x155_2.setText(DouToStr.format(entity.getX155()));
		text_area_x158_2.setText(DouToStr.format(entity.getX158()));
		text_area_x212_2.setText(DouToStr.format(entity.getX212()));
		text_area_x213_2.setText(DouToStr.format(entity.getX213()));
		text_area_x214_2.setText(DouToStr.format(entity.getX214()));
		
		text_structure_x7_5.setText(c.getX7()+"");
		text_allowance_x154.setText(DouToStr.format(entity.getX154()));
		text_structure_x8_5.setText(c.getX8()+"");
		text_allowance_x157.setText(DouToStr.format(entity.getX157()));
		text_structure_x81_5.setText(c.getX81()+"");
		text_allowance_x160.setText(DouToStr.format(entity.getX160()));
		text_structure_x84_5.setText(c.getX84()+"");

		text_allowance_x223.setText(DouToStr.format(entity.getX223()));
		text_structure_x200_5.setText(c.getX200()+"");
		text_allowance_x219.setText(DouToStr.format(entity.getX219()));
		text_structure_x201_5.setText(c.getX201()+"");
		text_allowance_x220.setText(DouToStr.format(entity.getX220()));
		text_totallowance_x161.setText(DouToStr.format(entity.getX161()));
		
		text_structure_x7_6.setText(c.getX7()+"");
		text_allowance_x276.setText(DouToStr.format(entity.getX276()));
		text_structure_x8_6.setText(c.getX8()+"");
		text_allowance_x277.setText(DouToStr.format(entity.getX277()));
		text_structure_x81_6.setText(c.getX81()+"");
		text_allowance_x278.setText(DouToStr.format(entity.getX278()));
		text_structure_x84_6.setText(c.getX84()+"");
		text_allowance_x279.setText(DouToStr.format(entity.getX279()));
		text_structure_x200_6.setText(c.getX200()+"");
		text_allowance_x280.setText(DouToStr.format(entity.getX280()));
		text_structure_x201_6.setText(c.getX201()+"");
		text_allowance_x281.setText(DouToStr.format(entity.getX281()));
		text_totallowance_x282.setText(DouToStr.format(entity.getX282()));
		
	//	text_totallowance_x162.setText(entity.getX162_100()+"");
//		text_totallowance_x163.setText(entity.getX163_100()+"");
//		text_totallowance_x162163.setText(entity.getX162_100X163_100()+"");
//		text_totallowance_x76_2.setText(entity.getX76()+"");
		
		text_totallowance_x162.setText(DouToStr.format(entity.getX162_100()));
		text_totallowance_x163.setText(DouToStr.format(entity.getX163_100()));
		
	
		text_totallowance_x162163.setText(DouToStr.format(entity.getX162_100X163_100()));
		text_totallowance_x76_2.setText(DouToStr.format(entity.getX76()));
		
		//如果算单状态值为2（已提交），屏蔽保存按钮，使其不能修改
		if (entity.getStatus()!=null && entity.getStatus() == 2) {
		Button btn = (Button)findViewById(R.id.btn_title_right);
		btn.setVisibility(View.INVISIBLE);
		}
		
		
		if(!StringHelper.isNullOrEmpty(entity.getX17_1())){
			   btn_housechoice_1.setEnabled(false);
			   btn_housedelete_1.setEnabled(true); 
		  }else{
			   btn_housechoice_1.setEnabled(true);
			   btn_housedelete_1.setEnabled(false);  
		  }
		
		 if(!StringHelper.isNullOrEmpty(entity.getX17_2())){
			    btn_housechoice_2.setEnabled(false);
				btn_housedelete_2.setEnabled(true); 
			  }else{
				btn_housechoice_2.setEnabled(true);
			    btn_housedelete_2.setEnabled(false); 
		   }
		 
		 if(!StringHelper.isNullOrEmpty(entity.getX17_3())){
			    btn_housechoice_3.setEnabled(false);
				btn_housedelete_3.setEnabled(true); 
			  }else{
				btn_housechoice_3.setEnabled(true);
				btn_housedelete_3.setEnabled(false);  
		   }
		 
		 if(!StringHelper.isNullOrEmpty(entity.getX17_4())){
			    btn_housechoice_4.setEnabled(false);
				btn_housedelete_4.setEnabled(true); 
			  }else{
				btn_housechoice_4.setEnabled(true);
			    btn_housedelete_4.setEnabled(false); 
		   }
		 
		
		 
	     if(!StringHelper.isNullOrEmpty(entity.getX17_5())){
			    btn_housechoice_05.setEnabled(false);
				btn_housedelete_05.setEnabled(true); 
			  }else{
				btn_housechoice_05.setEnabled(true);
			    btn_housedelete_05.setEnabled(false); 
		   }
	     
	     if(!StringHelper.isNullOrEmpty(entity.getX17_6())){
			    btn_housechoice_06.setEnabled(false);
				btn_housedelete_06.setEnabled(true); 
			  }else{
				btn_housechoice_06.setEnabled(true);
			    btn_housedelete_06.setEnabled(false); 
		   }
	     
	     if(!StringHelper.isNullOrEmpty(entity.getX17_7())){
			    btn_housechoice_07.setEnabled(false);
				btn_housedelete_07.setEnabled(true); 
			  }else{
				btn_housechoice_07.setEnabled(true);
			    btn_housedelete_07.setEnabled(false); 
		   }
	     if(!StringHelper.isNullOrEmpty(entity.getX17_8())){
			    btn_housechoice_08.setEnabled(false);
				btn_housedelete_08.setEnabled(true); 
			  }else{
				btn_housechoice_08.setEnabled(true);
			    btn_housedelete_08.setEnabled(false); 
		   }
	     
	     if(!StringHelper.isNullOrEmpty(entity.getX17_9())){
			    btn_housechoice_09.setEnabled(false);
				btn_housedelete_09.setEnabled(true); 
			  }else{
				btn_housechoice_09.setEnabled(true);
			    btn_housedelete_09.setEnabled(false); 
		   }
	     
	     if(!StringHelper.isNullOrEmpty(entity.getX17_10())){
			    btn_housechoice_010.setEnabled(false);
				btn_housedelete_010.setEnabled(true); 
			  }else{
				btn_housechoice_010.setEnabled(true);
			    btn_housedelete_010.setEnabled(false); 
		   }
	     
		 
		 
		 if(!StringHelper.isNullOrEmpty(entity.getX40_1())){
			    btn_housechoice_5.setEnabled(false);
				btn_housedelete_5.setEnabled(true); 
			  }else{
			    btn_housechoice_5.setEnabled(true);
			    btn_housedelete_5.setEnabled(false); 
		   }
		 
		 
		 if(!StringHelper.isNullOrEmpty(entity.getX40_2())){
			    btn_housechoice_6.setEnabled(false);
			 	btn_housedelete_6.setEnabled(true); 
			  }else{
			    btn_housechoice_6.setEnabled(true);
			    btn_housedelete_6.setEnabled(false); 
		   }
		 
		 if(!StringHelper.isNullOrEmpty(entity.getX40_3())){
			    btn_housechoice_7.setEnabled(false);
				btn_housedelete_7.setEnabled(true); 
			  }else{
				btn_housechoice_7.setEnabled(true);
			    btn_housedelete_7.setEnabled(false);
		   }
		 
		 if(!StringHelper.isNullOrEmpty(entity.getX40_4())){
			    btn_housechoice_8.setEnabled(false);
				btn_housedelete_8.setEnabled(true); 
			  }else{
				btn_housechoice_8.setEnabled(true);
				btn_housedelete_8.setEnabled(false); 
		   }
				
		 
		 if(!StringHelper.isNullOrEmpty(entity.getX40_5())){
			    btn_housechoice_9.setEnabled(false);
				btn_housedelete_9.setEnabled(true); 
			  }else{
				btn_housechoice_9.setEnabled(true);
				btn_housedelete_9.setEnabled(false); 
		   }
  
		 if(!StringHelper.isNullOrEmpty(entity.getX40_6())){
			    btn_housechoice_10.setEnabled(false);
				btn_housedelete_10.setEnabled(true); 
			  }else{
				btn_housechoice_10.setEnabled(true);
				btn_housedelete_10.setEnabled(false); 
		   }
		 if(!StringHelper.isNullOrEmpty(entity.getX40_7())){
			    btn_housechoice_11.setEnabled(false);
				btn_housedelete_11.setEnabled(true); 
			  }else{
				btn_housechoice_11.setEnabled(true);
				btn_housedelete_11.setEnabled(false); 
		   }
		 if(!StringHelper.isNullOrEmpty(entity.getX40_8())){
			    btn_housechoice_12.setEnabled(false);
				btn_housedelete_12.setEnabled(true); 
			  }else{
				btn_housechoice_12.setEnabled(true);
				btn_housedelete_12.setEnabled(false); 
		   }
		 if(!StringHelper.isNullOrEmpty(entity.getX40_9())){
			    btn_housechoice_13.setEnabled(false);
				btn_housedelete_13.setEnabled(true); 
			  }else{
				btn_housechoice_13.setEnabled(true);
				btn_housedelete_13.setEnabled(false); 
		   }
		 if(!StringHelper.isNullOrEmpty(entity.getX40_10())){
			    btn_housechoice_14.setEnabled(false);
				btn_housedelete_14.setEnabled(true); 
			  }else{
				btn_housechoice_14.setEnabled(true);
				btn_housedelete_14.setEnabled(false); 
		   }
	     
		//若算单已提交，选房、删房按钮都不操作
		if("2".equals(String.valueOf(entity.getStatus()))){
				
				btn_housechoice_1.setEnabled(false);
				btn_housedelete_1.setEnabled(false);

				btn_housechoice_2.setEnabled(false);
				btn_housedelete_2.setEnabled(false);

				btn_housechoice_3.setEnabled(false);
				btn_housedelete_3.setEnabled(false);

				btn_housechoice_4.setEnabled(false);
				btn_housedelete_4.setEnabled(false);

				btn_housechoice_05.setEnabled(false);
				btn_housedelete_05.setEnabled(false); 

				btn_housechoice_06.setEnabled(false);
				btn_housedelete_06.setEnabled(false); 

				btn_housechoice_07.setEnabled(false);
				btn_housedelete_07.setEnabled(false); 

				btn_housechoice_08.setEnabled(false);
				btn_housedelete_08.setEnabled(false); 

				btn_housechoice_09.setEnabled(false);
				btn_housedelete_09.setEnabled(false); 

				btn_housechoice_010.setEnabled(false);
				btn_housedelete_010.setEnabled(false); 

				btn_housechoice_5.setEnabled(false);
				btn_housedelete_5.setEnabled(false);

				btn_housechoice_6.setEnabled(false);
				btn_housedelete_6.setEnabled(false);

				btn_housechoice_7.setEnabled(false);
				btn_housedelete_7.setEnabled(false);

				btn_housechoice_8.setEnabled(false);
				btn_housedelete_8.setEnabled(false);

				btn_housechoice_9.setEnabled(false);
				btn_housedelete_9.setEnabled(false);

				btn_housechoice_10.setEnabled(false);
				btn_housedelete_10.setEnabled(false);

				btn_housechoice_11.setEnabled(false);
				btn_housedelete_11.setEnabled(false);

				btn_housechoice_12.setEnabled(false);
				btn_housedelete_12.setEnabled(false);

				btn_housechoice_13.setEnabled(false);
				btn_housedelete_13.setEnabled(false);

				btn_housechoice_14.setEnabled(false);
				btn_housedelete_14.setEnabled(false);
				
			}
		 
	 }
	   
	
	public void setEntityToHouse1(HouseList entity,HouseConfirm c) {	
		entity.setHouseConfirm(c);
		text_house_x17_1.setText(entity.getX17_1());
		text_existhouse_x18_1.setText(entity.getX18_1());
		edit_value_x19_1.setText(entity.getX19_1() + "");
		text_house_x20.setText(entity.getX20());
		text_house_x21.setText(entity.getX21());
		text_house_x22.setText(entity.getX22());
		text_house_x23.setText(entity.getX23());
		text_area_x24.setText(entity.getX24() + "");
		if(!StringHelper.isNullOrEmpty(entity.getId())){
			 id=entity.getId();
			 loadingHouse1Data();
		}
	}
	
	public void setEntityToHouse2(HouseList entity,HouseConfirm c) {	
		entity.setHouseConfirm(c);
		text_house_x17_2.setText(entity.getX17_2());
		text_existhouse_x18_2.setText(entity.getX18_2());
		edit_value_x19_2.setText(entity.getX19_2() + "");
		text_house_x25.setText(entity.getX25());
		text_house_x26.setText(entity.getX26());
		text_house_x27.setText(entity.getX27());
		text_house_x28.setText(entity.getX28());
		text_area_x29.setText(entity.getX29() + "");
		if(!StringHelper.isNullOrEmpty(entity.getId())){
			 id=entity.getId();
			 loadingHouse2Data();
		}
	}
	
	public void setEntityToHouse3(HouseList entity,HouseConfirm c) {	
		entity.setHouseConfirm(c);
		text_house_x17_3.setText(entity.getX17_3());
		text_existhouse_x18_3.setText(entity.getX18_3());
		edit_value_x19_3.setText(entity.getX19_3() + "");
		text_house_x30.setText(entity.getX30());
		text_house_x31.setText(entity.getX31());
		text_house_x32.setText(entity.getX32());
		text_house_x33.setText(entity.getX33());
		text_area_x34.setText(entity.getX34() + "");
		if(!StringHelper.isNullOrEmpty(entity.getId())){
			 id=entity.getId();
			 loadingHouse3Data();
		}
	}
	
	public void setEntityToHouse4(HouseList entity,HouseConfirm c) {	
		entity.setHouseConfirm(c);
		text_house_x17_4.setText(entity.getX17_4());
		text_existhouse_x18_4.setText(entity.getX18_4());
		edit_value_x19_4.setText(entity.getX19_4() + "");
		text_house_x35.setText(entity.getX35());
		text_house_x36.setText(entity.getX36());
		text_house_x37.setText(entity.getX37());
		text_house_x38.setText(entity.getX38());
		text_area_x39.setText(entity.getX39() + "");
		if(!StringHelper.isNullOrEmpty(entity.getId())){
			 id=entity.getId();
			 loadingHouse4Data();
		}
	}
	
	public void setEntityToHouse5(HouseList entity,HouseConfirm c) {	
		entity.setHouseConfirm(c);
		text_house_x40_1.setText(entity.getX40_1());
		text_existhouse_x41_1.setText(entity.getX41_1());
		edit_value_x42_1.setText(entity.getX42_1() + "");
		text_house_x43.setText(entity.getX43());
		text_house_x44.setText(entity.getX44());
		text_house_x45.setText(entity.getX45());
		text_house_x46.setText(entity.getX46());
		edit_area_x800.setText(entity.getX800() + "");
		if(!StringHelper.isNullOrEmpty(entity.getId())){
			 id=entity.getId();
			 loadingHouse5Data();
		}
	}
	
	public void setEntityToHouse6(HouseList entity,HouseConfirm c) {	
		entity.setHouseConfirm(c);
		text_house_x40_2.setText(entity.getX40_2());
		text_existhouse_x41_2.setText(entity.getX41_2());
		edit_value_x42_2.setText(entity.getX42_2() + "");
		text_house_x48.setText(entity.getX48());
		text_house_x49.setText(entity.getX49());
		text_house_x50.setText(entity.getX50());
		text_house_x51.setText(entity.getX51());
		edit_area_x801.setText(entity.getX801() + "");
		if(!StringHelper.isNullOrEmpty(entity.getId())){
			 id=entity.getId();
			 loadingHouse6Data();
		}
	}
	
	public void setEntityToHouse7(HouseList entity,HouseConfirm c) {	
		entity.setHouseConfirm(c);
		text_house_x40_3.setText(entity.getX40_3());
		text_existhouse_x41_3.setText(entity.getX41_3());
		edit_value_x42_3.setText(entity.getX42_3() + "");
		text_house_x53.setText(entity.getX53());
		text_house_x54.setText(entity.getX54());
		text_house_x55.setText(entity.getX55());
		text_house_x56.setText(entity.getX56());
		edit_area_x802.setText(entity.getX802() + "");
		if(!StringHelper.isNullOrEmpty(entity.getId())){
			 id=entity.getId();
			 loadingHouse7Data();
		}
	}
	
	public void setEntityToHouse8(HouseList entity,HouseConfirm c) {	
		entity.setHouseConfirm(c);
		text_house_x40_4.setText(entity.getX40_4());
		text_existhouse_x41_4.setText(entity.getX41_4());
		edit_value_x42_4.setText(entity.getX42_4() + "");
		text_house_x58.setText(entity.getX58());
		text_house_x59.setText(entity.getX59());
		text_house_x60.setText(entity.getX60());
		text_house_x61.setText(entity.getX61());
		edit_area_x803.setText(entity.getX803() + "");
		if(!StringHelper.isNullOrEmpty(entity.getId())){
			 id=entity.getId();
			 loadingHouse8Data();
		}
	}
	
		 
	private HouseList getEntity() {
		if (isAdd) {
			entity.setCreator(LoginBus.getLogin(this).getREALNAME());
			// entity.setCREDATE(new Date());
		}
		//tab1		
		
		entity.setX12(StringToDouble(edit_confirmarea_x12.getText().toString()));
		
		entity.setX2(edit_cardno_x2.getText().toString());
		entity.setX3(edit_linktel_x3.getText().toString());

		// tab2
		entity.setX64input(StringToDouble(edit_prohouseaera_x64.getText().toString()));    
		
		entity.setX19_1(StringToDouble(edit_value_x19_1.getText().toString()));
		entity.setX19_2(StringToDouble(edit_value_x19_2.getText().toString()));
	    entity.setX19_3(StringToDouble(edit_value_x19_3.getText().toString()));
		entity.setX19_4(StringToDouble(edit_value_x19_4.getText().toString()));
		entity.setX42_1(StringToDouble(edit_value_x42_1.getText().toString()));
		entity.setX42_2(StringToDouble(edit_value_x42_2.getText().toString()));
		entity.setX42_3(StringToDouble(edit_value_x42_3.getText().toString()));
		entity.setX42_4(StringToDouble(edit_value_x42_4.getText().toString()));
		
	    
	    entity.setX19_5(StringToDouble(edit_value_x19_5.getText().toString()));
		entity.setX19_6(StringToDouble(edit_value_x19_6.getText().toString()));
		entity.setX19_7(StringToDouble(edit_value_x19_7.getText().toString()));
		entity.setX19_8(StringToDouble(edit_value_x19_8.getText().toString()));
		entity.setX19_9(StringToDouble(edit_value_x19_9.getText().toString()));
		entity.setX19_10(StringToDouble(edit_value_x19_10.getText().toString()));
	    
		
	    entity.setX42_5(StringToDouble(edit_value_x42_5.getText().toString()));
		entity.setX42_6(StringToDouble(edit_value_x42_6.getText().toString()));
		entity.setX42_7(StringToDouble(edit_value_x42_7.getText().toString()));
		entity.setX42_8(StringToDouble(edit_value_x42_8.getText().toString()));
		entity.setX42_9(StringToDouble(edit_value_x42_9.getText().toString()));
		entity.setX42_10(StringToDouble(edit_value_x42_10.getText().toString()));
	    
		
		
		entity.setX65(StringToDouble(edit_prohouseaera_x65.getText().toString()));
		entity.setX66(StringToDouble(edit_value_x66.getText().toString()));
		entity.setX68(StringToDouble(edit_prohouseaera_x68.getText().toString()));
		entity.setX69(StringToDouble(edit_value_x69_1.getText().toString()));
		entity.setX70(StringToDouble(edit_percent_x70.getText().toString()));
		entity.setX72(StringToDouble(edit_prohouseaera_x72.getText().toString()));
		entity.setX74(StringToDouble(edit_percent_x74.getText().toString()));
		entity.setX77(StringToDouble(edit_prohouseaera_x77.getText().toString()));
		entity.setX79(StringToDouble(edit_prohouseaera_x79.getText().toString()));
		entity.setX82(StringToDouble(edit_prohouseaera_x82.getText().toString()));
		entity.setX85(StringToDouble(edit_prohouseaera_x85.getText().toString()));
		entity.setX221(StringToDouble(edit_prohouseaera_x221.getText().toString()));
		entity.setX222(StringToDouble(edit_prohouseaera_x222.getText().toString()));

		// tab3
		//搬迁费类型
		entity.setBqflagvalue(PublicBus.spinnerGetValue(sp_bqflagvalue));
					
		entity.setA1000(StringToDouble(edit_legalarea_x13_2.getText().toString()));
		entity.setA1001(StringToDouble(edit_prohouseaera_x63_2.getText().toString()));
		entity.setA1002(StringToDouble(edit_residuearea_x1363.getText().toString()));
		
		entity.setX88(StringToDouble(edit_value_x88_1.getText().toString()));
		entity.setX88(StringToDouble(edit_value_x88_2.getText().toString()));
		
		entity.setX89(StringToInteger(edit_num_x89.getText().toString()));
		entity.setX91(StringToInteger(edit_num_x91.getText().toString()));
		entity.setX93(StringToInteger(edit_num_x93.getText().toString()));		
		entity.setX95(StringToDouble(edit_cost_x95_1.getText().toString()));
		entity.setX96(StringToInteger(edit_month_x96.getText().toString()));
		entity.setX98(StringToInteger(edit_month_x98.getText().toString()));		
		entity.setX100(StringToInteger(edit_month_x100.getText().toString()));
		entity.setX102(StringToInteger(edit_month_x102.getText().toString()));
		entity.setX104(StringToInteger(edit_month_x104.getText().toString()));
		entity.setX170(StringToDouble(edit_cost_x170.getText().toString()));
		entity.setX164(StringToDouble(edit_area_x164.getText().toString()));
		entity.setX111(StringToDouble(edit_rise_x111_1.getText().toString()));
		entity.setX165(StringToDouble(edit_area_x165.getText().toString()));
		entity.setX166(StringToDouble(edit_area_x166.getText().toString()));		
		entity.setX167(StringToDouble(edit_area_x167.getText().toString()));
		entity.setX208(StringToDouble(edit_area_x208.getText().toString()));
		entity.setX209(StringToDouble(edit_area_x209.getText().toString()));
		entity.setX119(StringToDouble(edit_money_x119.getText().toString()));
		entity.setX120(StringToDouble(edit_money_x120.getText().toString()));		
		entity.setX128(StringToDouble(edit_value_x128.getText().toString()));
		entity.setX129(StringToDouble(edit_value_x129.getText().toString()));
		entity.setX130(StringToDouble(edit_value_x130.getText().toString()));
		entity.setX131(StringToDouble(edit_value_x131.getText().toString()));
		entity.setX136(StringToDouble(edit_money_x136.getText().toString()));
		entity.setX139(StringToInteger(edit_num_x139.getText().toString()));
		entity.setX137(StringToDouble(edit_money_x137.getText().toString()));
		entity.setX140(StringToInteger(edit_num_x140.getText().toString()));
		entity.setX138(StringToDouble(edit_money_x138.getText().toString()));
		entity.setX141(StringToInteger(edit_num_x141.getText().toString()));
		entity.setX146(StringToDouble(edit_value_x146.getText().toString()));
		entity.setX148(StringToDouble(edit_price_x148.getText().toString()));
		entity.setX149(StringToInteger(edit_housenum_x149.getText().toString()));
		entity.setX168(StringToDouble(edit_money_x168.getText().toString()));
		entity.setX169(StringToDouble(edit_money_x169.getText().toString()));
		
		entity.setX500(StringToDouble(edit_x500.getText().toString()));
		entity.setX501(StringToDouble(edit_x501.getText().toString()));
		entity.setX502(StringToDouble(edit_x502.getText().toString()));
		entity.setX503(StringToDouble(edit_x503.getText().toString()));
		entity.setX504(StringToDouble(edit_x504.getText().toString()));
		
		//3月20日新增
		entity.setA501(StringToDouble(edit_a501.getText().toString()));
		entity.setA502(StringToDouble(edit_a502.getText().toString()));
		entity.setA503(StringToDouble(edit_a503.getText().toString()));
		entity.setA504(StringToDouble(edit_a504.getText().toString()));
		entity.setA505(StringToDouble(edit_a505.getText().toString()));
		entity.setA506(StringToDouble(edit_a506.getText().toString()));
		
		
		entity.setA98(StringToInteger(edit_month_a98.getText().toString()));
		entity.setA100(StringToInteger(edit_month_a100.getText().toString()));
		entity.setA102(StringToInteger(edit_month_a102.getText().toString()));
		entity.setA104(StringToInteger(edit_month_a104.getText().toString()));
		entity.setA106(StringToInteger(edit_month_a106.getText().toString()));
		entity.setA108(StringToInteger(edit_month_a108.getText().toString()));
		
		
		entity.setX800(StringToDouble(edit_area_x800.getText().toString()));
		entity.setX801(StringToDouble(edit_area_x801.getText().toString()));
		entity.setX802(StringToDouble(edit_area_x802.getText().toString()));
		entity.setX803(StringToDouble(edit_area_x803.getText().toString()));
		

		// tab4
		entity.setX152(StringToDouble(edit_area_x152_1.getText().toString()));
		entity.setX153(StringToDouble(edit_value_x153.getText().toString()));
		entity.setX155(StringToDouble(edit_area_x155_1.getText().toString()));
		entity.setX156(StringToDouble(edit_value_x156.getText().toString()));
		entity.setX158(StringToDouble(edit_area_x158_1.getText().toString()));
		entity.setX159(StringToDouble(edit_value_x159.getText().toString()));
		entity.setX212(StringToDouble(edit_area_x212_1.getText().toString()));
		entity.setX215(StringToDouble(edit_value_x215.getText().toString()));
		entity.setX213(StringToDouble(edit_area_x213_1.getText().toString()));
		entity.setX216(StringToDouble(edit_value_x216.getText().toString()));
		entity.setX214(StringToDouble(edit_area_x214_1.getText().toString()));
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
	
	
	
	
	 class OnItemSelected implements OnItemSelectedListener {
		 public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
					switch (parent.getId()) {
					case R.id.sp_bqflagvalue:
						OnItemSelected_sp_bqflagvalue();
						break;
						
					default:
						break;
					}
		         }
		 
			 void OnItemSelected_sp_bqflagvalue(){
				 
				 String value = PublicBus.spinnerGetValue(sp_bqflagvalue);
				 if(value == null){//未选择征收房屋与安置房的大小时，只显示spinner
					    bqflagvalue.setVisibility(View.VISIBLE);
					    bqflagvalue_0.setVisibility(View.VISIBLE);
					    bqflagvalue_1.setVisibility(View.VISIBLE);
					}else if(value != null && value.equals("0")){//被征收房屋小于安置房
						bqflagvalue.setVisibility(View.GONE);
						sp_bqflagvalue.setEnabled(false);
						bqflagvalue_0.setVisibility(View.VISIBLE);
					    bqflagvalue_1.setVisibility(View.GONE);
					}else if(value.equals("1")){//被征收房屋大于安置房
						bqflagvalue.setVisibility(View.GONE);
						sp_bqflagvalue.setEnabled(false);
						bqflagvalue_0.setVisibility(View.GONE);
						bqflagvalue_1.setVisibility(View.VISIBLE);
					}			
			 }
			 
			 //if (StringHelper.isNullOrEmpty(in_id)) {
			 
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
			 
		 }
         
	 // @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);

			switch (requestCode) {
			//3月12日之前  现房 选择房间  4套 
			case requestCode_house1:
				requestCode_house(resultCode,data,"1");
				setEntityToHouse1(entity,c);
				break;
			case requestCode_house2:
				requestCode_house(resultCode,data,"2");
				setEntityToHouse2(entity,c);
				break;
			case requestCode_house3:
				requestCode_house(resultCode,data,"3");
				setEntityToHouse3(entity,c);
				break;
			case requestCode_house4:
				requestCode_house(resultCode,data,"4");
				setEntityToHouse4(entity,c);
				break;
			//3月12日之前  期房 选择房间  4套 	
			case requestCode_house5:
				requestCode_house(resultCode,data,"5");
				setEntityToHouse5(entity,c);
				break;
			case requestCode_house6:
				requestCode_house(resultCode,data,"6");
				setEntityToHouse6(entity,c);
				break;
			case requestCode_house7:
				requestCode_house(resultCode,data,"7");
				setEntityToHouse7(entity,c);
				break;
			case requestCode_house8:
				requestCode_house(resultCode,data,"8");
				setEntityToHouse8(entity,c);
				break;
				
		    //3月12日之后增加  现房 选择房间  6套	
			case requestCode_house11:
				requestCode_house(resultCode,data,"11");
				break;
			case requestCode_house12:
				requestCode_house(resultCode,data,"12");
				break;
			case requestCode_house13:
				requestCode_house(resultCode,data,"13");
				break;
			case requestCode_house14:
				requestCode_house(resultCode,data,"14");
				break;
			case requestCode_house15:
				requestCode_house(resultCode,data,"15");
				break;
			case requestCode_house16:
				requestCode_house(resultCode,data,"16");
				break;
				
		    //3月12日之后新增  期房 选择房间 6套	
			case requestCode_house21:
				requestCode_house(resultCode,data,"21");
				break;
			case requestCode_house22:
				requestCode_house(resultCode,data,"22");
				break;	
				
			case requestCode_house23:
				requestCode_house(resultCode,data,"23");
				break;
			case requestCode_house24:
				requestCode_house(resultCode,data,"24");
				break;
			case requestCode_house25:
				requestCode_house(resultCode,data,"25");
				break;
			case requestCode_house26:
				requestCode_house(resultCode,data,"26");
				break;		
				
				
			case requestCode_housedelete31:
				requestCode_housedelete(resultCode,data,"1");
				break;	
			case requestCode_housedelete32:
				requestCode_housedelete(resultCode,data,"2");
				break;	
			case requestCode_housedelete33:
				requestCode_housedelete(resultCode,data,"3");
				break;	
			case requestCode_housedelete34:
				requestCode_housedelete(resultCode,data,"4");
				break;	
				
			case requestCode_housedelete35:
				requestCode_housedelete(resultCode,data,"5");
				break;	
			case requestCode_housedelete36:
				requestCode_housedelete(resultCode,data,"6");
				break;	
			case requestCode_housedelete37:
				requestCode_housedelete(resultCode,data,"7");
				break;	
			case requestCode_housedelete38:
				requestCode_housedelete(resultCode,data,"8");
				break;	
				
				
			case requestCode_housedelete39:
				requestCode_housedelete(resultCode,data,"11");
				break;	
			case requestCode_housedelete40:
				requestCode_housedelete(resultCode,data,"12");
				break;	
			case requestCode_housedelete41:
				requestCode_housedelete(resultCode,data,"13");
				break;	
			case requestCode_housedelete42:
				requestCode_housedelete(resultCode,data,"14");
				break;	
			case requestCode_housedelete43:
				requestCode_housedelete(resultCode,data,"15");
				break;	
			case requestCode_housedelete44:
				requestCode_housedelete(resultCode,data,"16");
				break;	
				
			case requestCode_housedelete45:
				requestCode_housedelete(resultCode,data,"21");
				break;	
			case requestCode_housedelete46:
				requestCode_housedelete(resultCode,data,"22");
				break;	
				
			case requestCode_housedelete47:
				requestCode_housedelete(resultCode,data,"23");
				break;	
			case requestCode_housedelete48:
				requestCode_housedelete(resultCode,data,"24");
				break;	
			case requestCode_housedelete49:
				requestCode_housedelete(resultCode,data,"25");
				break;	
			case requestCode_housedelete50:
				requestCode_housedelete(resultCode,data,"26");
				break;	
				
				
			case requestcode_att1:
				requestCode_Att(resultCode,data,text_cost_x108);
				break;
			case requestcode_att2:
				requestCode_Att(resultCode,data,text_cost_x109);
				break;
			case requestcode_att3:
				requestCode_Att(resultCode,data,text_cost_x107);
				break;
			default:
				// RequestCode_photo(requestCode,resultCode, data);
				break;
			}
		}

		private void requestCode_Att(int resultCode, Intent data,TextView textView) {
			if(resultCode!=RESULT_OK)
				return;	 
			textView.setText(data.getStringExtra(HListAttList.KEY.RE_ALLMONEY));
		}

		private void requestCode_house(int resultCode,Intent data, String string){
			if(resultCode!=RESULT_OK)
				return;	
			operateIntent();
		}
		
		private void requestCode_housedelete(int resultCode,Intent data, String string){
			if(resultCode!=RESULT_OK)
				return;	
			operateIntent();
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
				 //3月12日之前  现房 选择房间  4套 
			case R.id.btn_housechoice_1:
				Onclick_btn_housechoice(1);
				break;	
			case R.id.btn_housechoice_2:
				Onclick_btn_housechoice(2);
				break;
			case R.id.btn_housechoice_3:
				Onclick_btn_housechoice(3);
				break;
			case R.id.btn_housechoice_4:
				Onclick_btn_housechoice(4);
				break;
			//3月12日之前   期房   选择房间  4套 	
			case R.id.btn_housechoice_5:
				Onclick_btn_housechoice(5);
				break;
			case R.id.btn_housechoice_6:
				Onclick_btn_housechoice(6);
				break;
			case R.id.btn_housechoice_7:
				Onclick_btn_housechoice(7);
				break;
			case R.id.btn_housechoice_8:
				Onclick_btn_housechoice(8);
				break;
				
		    //3月12日之后   现房   选择房间  6套 	
			case R.id.btn_housechoice_05:
				Onclick_btn_housechoice(11);
				break;	
			case R.id.btn_housechoice_06:
				Onclick_btn_housechoice(12);
				break;
			case R.id.btn_housechoice_07:
				Onclick_btn_housechoice(13);
				break;
			case R.id.btn_housechoice_08:
				Onclick_btn_housechoice(14);
				break;
			case R.id.btn_housechoice_09:
				Onclick_btn_housechoice(15);
				break;
			case R.id.btn_housechoice_010:
				Onclick_btn_housechoice(16);
				break;
				
			//3月12日之后   期房   选择房间  6套 		
			case R.id.btn_housechoice_9:
				Onclick_btn_housechoice(21);
				break;
			case R.id.btn_housechoice_10:
				Onclick_btn_housechoice(22);
				break;
			case R.id.btn_housechoice_11:
				Onclick_btn_housechoice(23);
				break;
			case R.id.btn_housechoice_12:
				Onclick_btn_housechoice(24);
				break;
			case R.id.btn_housechoice_13:
				Onclick_btn_housechoice(25);
				break;
			case R.id.btn_housechoice_14:
				Onclick_btn_housechoice(26);
				break;
				
			//3月12日之前  现房 删除房间	
			case R.id.btn_housedelete_1:
				Onclick_btn_housedelete(entity.getHid1());
				break;	
			case R.id.btn_housedelete_2:
				Onclick_btn_housedelete(entity.getHid2());
				break;
			case R.id.btn_housedelete_3:
				Onclick_btn_housedelete(entity.getHid3());
				break;
			case R.id.btn_housedelete_4:
				Onclick_btn_housedelete(entity.getHid4());
				break;
			//3月12日之前  期房 删除房间	
			case R.id.btn_housedelete_5:
				Onclick_btn_housedelete(entity.getHid5());
				break;
			case R.id.btn_housedelete_6:
				Onclick_btn_housedelete(entity.getHid6());
				break;
			case R.id.btn_housedelete_7:
				Onclick_btn_housedelete(entity.getHid7());
				break;
			case R.id.btn_housedelete_8:
				Onclick_btn_housedelete(entity.getHid8());
				break;
				
		    //3月12日之后  现房  删除房间  6套	
			case R.id.btn_housedelete_05:
				Onclick_btn_housedelete(entity.getHid11());
				break;	
			case R.id.btn_housedelete_06:
				Onclick_btn_housedelete(entity.getHid12());
				break;
			case R.id.btn_housedelete_07:
				Onclick_btn_housedelete(entity.getHid13());
				break;
			case R.id.btn_housedelete_08:
				Onclick_btn_housedelete(entity.getHid14());
				break;
			case R.id.btn_housedelete_09:
				Onclick_btn_housedelete(entity.getHid15());
				break;
			case R.id.btn_housedelete_010:
				Onclick_btn_housedelete(entity.getHid16());
				break;
				
			//3月12日之后  期房  删除房间  6套	
			case R.id.btn_housedelete_9:
				Onclick_btn_housedelete(entity.getHid21());
				break;
			case R.id.btn_housedelete_10:
				Onclick_btn_housedelete(entity.getHid22());
				break;
			case R.id.btn_housedelete_11:
				Onclick_btn_housedelete(entity.getHid23());
				break;
			case R.id.btn_housedelete_12:
				Onclick_btn_housedelete(entity.getHid24());
				break;
			case R.id.btn_housedelete_13:
				Onclick_btn_housedelete(entity.getHid25());
				break;
			case R.id.btn_housedelete_14:
				Onclick_btn_housedelete(entity.getHid26());
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
		
		
		void Onclick_btn_housechoice(int index) {
				Intent intent = new Intent(activity, HouseNameListA.class);
		    	intent.putExtra(HouseNameListA.KEY.in_id, entity.getId());
			    intent.putExtra(HouseNameListA.KEY.in_index,""+index);  
		 	    activity.startActivityForResult(intent,index);
	    	
          }
		
		
		
		
		void Onclick_btn_housedelete(final String hid1) {	 
			
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("是否删除房屋");
		//	builder.setMessage(message);
			builder.setIcon(android.R.drawable.ic_dialog_info); 
			
			builder.setSingleChoiceItems(new String[] {"删除房屋"}, 0, new DialogInterface.OnClickListener() {  
                
				@Override  
				public void onClick(DialogInterface dialog, int which) {  
					}  
				}) ;

			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					try { 
						HouseRoomInfoDeleteAsyncTask task = new HouseRoomInfoDeleteAsyncTask(activity,hid1);
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
		
		
		
		 void OnItemSelected_sp_bqflagvalue(){
			 
			 String value = PublicBus.spinnerGetValue(sp_bqflagvalue);
			 if(value == null){//未选择征收房屋与安置房的大小时，只显示spinner
				    bqflagvalue.setVisibility(View.VISIBLE);
				    bqflagvalue_0.setVisibility(View.VISIBLE);
				    bqflagvalue_1.setVisibility(View.VISIBLE);
				}else if(value != null && value.equals("0")){//被征收房屋小于安置房
					bqflagvalue.setVisibility(View.GONE);
					sp_bqflagvalue.setEnabled(false);
					bqflagvalue_0.setVisibility(View.VISIBLE);
				    bqflagvalue_1.setVisibility(View.GONE);
				}else if(value.equals("1")){//被征收房屋大于安置房
					bqflagvalue.setVisibility(View.GONE);
					sp_bqflagvalue.setEnabled(false);
					bqflagvalue_0.setVisibility(View.GONE);
					bqflagvalue_1.setVisibility(View.VISIBLE);
				}			
		 }
		

		void Onclick_btn_title_right(){	
			getEntity();
			String result = HOU_JTGR_PROEXCHANGE_Bus.check(entity);
			if (!result.equals("")) {
				DialogHelper.showConfirm(activity, result);
				return;
			} 
			showTempSaveDialog(null);
	       }
		
		void showTempSaveDialog(String message) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("集体个人产权算单提交或暂存");
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
							JTGRProSaveAsyncTask save = new JTGRProSaveAsyncTask(activity);
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
							JTGRProSaveAsyncTask save = new JTGRProSaveAsyncTask(activity);
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
				JTGRProExchangeA.this.finish();
				return;
			}
			// 新增国有个人产权调换补偿计算单信息时，按下返回，如果必填项姓名或证件号不为空则进行关闭确认
			if (isAdd) {
				String levyname = text_levyname_x1.getText().toString().trim();
				String cardno = edit_cardno_x2.getText().toString().trim();

				if ((!levyname.equals("")) || (!cardno.equals(""))) {
					new AlertDialog.Builder(JTGRProExchangeA.this)
							.setMessage("信息未保存，确认要关闭？")
							.setPositiveButton("确定", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									JTGRProExchangeA.this.finish();
								}
							}).setNegativeButton("取消", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).create().show();
				} else {
					JTGRProExchangeA.this.finish();
				}
			} else {
				JTGRProExchangeA.this.finish();
			}
		}

	}

}