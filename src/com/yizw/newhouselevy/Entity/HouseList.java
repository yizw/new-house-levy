package com.yizw.newhouselevy.Entity;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;


import com.j256.ormlite.field.DatabaseField;

/**
 * The persistent class for the H_LIST database table.
 * 补偿计算单内容
 */
@SuppressWarnings("serial")
public class HouseList implements Serializable {
	
 // private static final long serialVersionUID = 1L;
	
	/*
	 * 000  国有个人产权
	 * 001  国有个人货币
	 * 010  国有企业产权
	 * 011  国有企业货币
	 * 100  集体个人产权
	 * 101  集体个人货币
	 * 110  集体企业产权
	 * 111  集体企业货币
	 */
	
	
  public HouseList(String id,String hid1, String hid2, String hid3, String hid4,
			String hid5, String hid6, String hid7, String hid8,
			String hid11,String hid12,String hid13,String hid14 ,String hid15,String hid16,
			String hid21,String hid22,String hid23,String hid24,String hid25,String hid26) {
		this.id = id;
		this.hid1 = hid1;
		this.hid2 = hid2;
		this.hid3 = hid3;
		this.hid4 = hid4;
		this.hid5 = hid5;
		this.hid6 = hid6;
		this.hid7 = hid7;
		this.hid8 = hid8;
		
		this.hid11 = hid11;
		this.hid12 = hid12;
		this.hid13 = hid13;
		this.hid14 = hid14;
		this.hid15 = hid15;
		this.hid16 = hid16;
		
		this.hid21 = hid21;
		this.hid22 = hid22;
		this.hid23 = hid23;
		this.hid24 = hid24;
		this.hid25 = hid25;
		this.hid26 = hid26;
		
		
	}

	
	@DatabaseField
	private String id;//ID	
	@DatabaseField
	private String creator;//创建人
	@DatabaseField
	private String creatorid;//创建人id
	@DatabaseField
	private String modifier;//修改人
	@DatabaseField
	private String modifierid;//修改人id
	@DatabaseField
	private Date createddate;//创建时间	
	@DatabaseField
	private Date modifydate;//修改时间	
	
	
	@DatabaseField
	private String hid;
	@DatabaseField
	private String pid;
	@DatabaseField
	private Integer contracttype;//合同类型
	@DatabaseField
	private String s168;
	@DatabaseField
	private String s169;
	@DatabaseField
	private Integer x100;
	@DatabaseField
	private Integer x102;
	@DatabaseField
	private Integer x104;
	@DatabaseField
	private Double x111;
	@DatabaseField
	private Double x119;
	@DatabaseField
	private Double x120;
	@DatabaseField
	private Double x148;
	@DatabaseField
	private Integer x149;
	@DatabaseField
	private Double x152;
	@DatabaseField
	private Double x153;
	@DatabaseField
	private Double x155;
	@DatabaseField
	private Double x156;
	@DatabaseField
	private Double x158;
	@DatabaseField
	private Double x159;
	@DatabaseField
	private Double x164;
	@DatabaseField
	private Double x165;
	@DatabaseField
	private Double x166;
	@DatabaseField
	private Double x167;
	@DatabaseField
	private Double x168;
	@DatabaseField
	private Double x169;
	@DatabaseField
	private String x17;
	@DatabaseField
	private Double x170;
	@DatabaseField
	private Long mainnum;//主编号
	@DatabaseField
	private Long subnum;//子编号
	@DatabaseField
	private String x2;
	@DatabaseField
	private String x20;
	@DatabaseField
	private Double x208;
	@DatabaseField
	private Double x209;
	@DatabaseField	
	private String x21;
	@DatabaseField	
	private Double x212;
	@DatabaseField	
	private Double x213;
	@DatabaseField
	private Double x214;
	@DatabaseField
	private Double x215;
	@DatabaseField
	private Double x216;
	@DatabaseField
	private Double x217;
	@DatabaseField
	private String x22;
	@DatabaseField
	private Double x221;
	@DatabaseField
	private Double x222;
	@DatabaseField
	private String x23;
	@DatabaseField
	private Double x24;
	@DatabaseField
	private String x25;
	@DatabaseField
	private String x26;
	@DatabaseField
	private String x27;
	@DatabaseField
	private String x28;
	@DatabaseField
	private Double x29;
	@DatabaseField
	private String x3;
	@DatabaseField
	private String x30;
	@DatabaseField
	private String x31;
	@DatabaseField
	private String x32;
	@DatabaseField
	private String x33;
	@DatabaseField
	private Double x34;
	@DatabaseField
	private String x35;
	@DatabaseField
	private String x36;
	@DatabaseField
	private String x37;
	@DatabaseField	
	private String x38;
	@DatabaseField	
	private Double x39;
	@DatabaseField
	private String x43;
	@DatabaseField
	private String x44;
	@DatabaseField
	private String x45;
	@DatabaseField
	private String x46;
	@DatabaseField
	private Double x47;
	@DatabaseField
	private String x48;
	@DatabaseField
	private String x49;
	@DatabaseField	
	private String x50;
	@DatabaseField
	private String x51;
	@DatabaseField
	private Double x52;
	@DatabaseField
	private String x53;
	@DatabaseField
	private String x54;
	@DatabaseField
	private String x55;
	@DatabaseField
	private String x56;
	@DatabaseField
	private Double x57;
	@DatabaseField
	private String x58;
	@DatabaseField
	private String x59;
	@DatabaseField
	private String x60;
	@DatabaseField
	private String x61;
	@DatabaseField
	private Double x62;
	@DatabaseField
	private Double x65;
	@DatabaseField
	private Double x66;
	@DatabaseField
	private Double x68;
	@DatabaseField
	private Double x69;
	@DatabaseField
	private Double x70;
	@DatabaseField
	private Double x72;
	@DatabaseField
	private Double x74;
	@DatabaseField
	private Double x77;
	@DatabaseField
	private Double x79;
	@DatabaseField
	private Double x82;
	@DatabaseField
	private Double x85;
	@DatabaseField
	private Double x88;
	@DatabaseField
	private Integer x89;
	@DatabaseField
	private Integer x91;
	@DatabaseField
	private Integer x93;
	@DatabaseField
	private Double x95;
	@DatabaseField
	private Integer x96;
	@DatabaseField
	private Integer x98;
	@DatabaseField
	private String hid1; //房源ID1
	@DatabaseField
	private String hid2;
	@DatabaseField
	private String hid3;
	@DatabaseField
	private String hid4;
	@DatabaseField
	private String hid5;
	@DatabaseField
	private String hid6;
	@DatabaseField
	private String hid7;
	@DatabaseField
	private String hid8;//房源ID8
	
	@DatabaseField
	private String hid11;
	@DatabaseField
	private String hid12;
	@DatabaseField
	private String hid13;
	@DatabaseField
	private String hid14;
	@DatabaseField
	private String hid15;
	@DatabaseField
	private String hid16;
	
	@DatabaseField
	private String hid21;
	@DatabaseField
	private String hid22;
	@DatabaseField
	private String hid23;
	@DatabaseField
	private String hid24;
	@DatabaseField
	private String hid25;
	@DatabaseField
	private String hid26;
	
	@DatabaseField
	private Long t; //房源时间
	
	/*@Column(columnDefinition="number")
	private Long t1;//时间毫秒数
	@Column(columnDefinition="number")
	private Long t2;
	@Column(columnDefinition="number")
	private Long t3;
	@Column(columnDefinition="number")
	private Long t4;
	@Column(columnDefinition="number")
	private Long t5;
	@Column(columnDefinition="number")
	private Long t6;
	@Column(columnDefinition="number")
	private Long t7;
	@Column(columnDefinition="number")
	private Long t8;*/
	
	@DatabaseField
	private String x17_1;
	@DatabaseField
	private String x17_2;
	@DatabaseField
	private String x17_3;
	@DatabaseField
	private String x17_4;
	@DatabaseField
	private String x17_5;
	@DatabaseField
	private String x17_6;
	@DatabaseField
	private String x17_7;
	@DatabaseField
	private String x17_8;
	@DatabaseField
	private String x17_9;
	@DatabaseField
	private String x17_10;
	
	@DatabaseField
	private String x18_1;
	@DatabaseField
	private String x18_2;
	@DatabaseField
	private String x18_3;
	@DatabaseField
	private String x18_4;
	@DatabaseField
	private String x18_5;
	@DatabaseField
	private String x18_6;
	@DatabaseField
	private String x18_7;
	@DatabaseField
	private String x18_8;
	@DatabaseField
	private String x18_9;
	@DatabaseField
	private String x18_10;
	
	
	@DatabaseField
	private Double x19_1;
	@DatabaseField
	private Double x19_2;
	@DatabaseField
	private Double x19_3;
	@DatabaseField
	private Double x19_4;
	@DatabaseField
	private Double x19_5;
	@DatabaseField
	private Double x19_6;
	@DatabaseField
	private Double x19_7;
	@DatabaseField
	private Double x19_8;
	@DatabaseField
	private Double x19_9;
	@DatabaseField
	private Double x19_10;
	
	
	@DatabaseField
	private String x40_1;
	@DatabaseField
	private String x40_2;
	@DatabaseField
	private String x40_3;
	@DatabaseField
	private String x40_4;
	@DatabaseField
	private String x40_5;
	@DatabaseField
	private String x40_6;
	@DatabaseField
	private String x40_7;
	@DatabaseField
	private String x40_8;
	@DatabaseField
	private String x40_9;
	@DatabaseField
	private String x40_10;
	
	@DatabaseField
	private String x41_1;
	@DatabaseField
	private String x41_2;
	@DatabaseField
	private String x41_3;
	@DatabaseField
	private String x41_4;
	@DatabaseField
	private String x41_5;
	@DatabaseField
	private String x41_6;
	@DatabaseField
	private String x41_7;
	@DatabaseField
	private String x41_8;
	@DatabaseField
	private String x41_9;
	@DatabaseField
	private String x41_10;
	@DatabaseField
	private Double x42_1;
	@DatabaseField
	private Double x42_2;
	@DatabaseField
	private Double x42_3;
	@DatabaseField
	private Double x42_4;
	@DatabaseField
	private Double x42_5;
	@DatabaseField
	private Double x42_6;
	@DatabaseField
	private Double x42_7;
	@DatabaseField
	private Double x42_8;
	@DatabaseField
	private Double x42_9;
	@DatabaseField
	private Double x42_10;
	
	
		
	@DatabaseField
	private String x900;
	@DatabaseField
	private String x902;
	@DatabaseField
	private String x903;
	@DatabaseField
	private String x904;
	@DatabaseField
	private Double x905;
	
	
	@DatabaseField
	private String x906;
	@DatabaseField
	private String x907;
	@DatabaseField
	private String x908;
	@DatabaseField
	private String x909;
	@DatabaseField
	private Double x910;
	
	
	@DatabaseField
	private String x911;
	@DatabaseField
	private String x912;
	@DatabaseField
	private String x913;
	@DatabaseField
	private String x914;
	@DatabaseField
	private Double x915;
	
	
	
	@DatabaseField
	private String x916;
	@DatabaseField
	private String x917;
	@DatabaseField
	private String x918;
	@DatabaseField
	private String x919;
	@DatabaseField
	private Double x920;
	
	@DatabaseField
	private String x921;
	@DatabaseField
	private String x922;
	@DatabaseField
	private String x923;
	@DatabaseField
	private String x924;
	@DatabaseField
	private Double x925;
	
	
	@DatabaseField
	private String x926;
	@DatabaseField
	private String x927;
	@DatabaseField
	private String x928;
	@DatabaseField
	private String x929;
	@DatabaseField
	private Double x930;
	
	@DatabaseField
	private String x931;
	@DatabaseField
	private String x932;
	@DatabaseField
	private String x933;
	@DatabaseField
	private String x934;
	@DatabaseField
	private Double x935;
	
	@DatabaseField
	private String x936;
	@DatabaseField
	private String x937;
	@DatabaseField
	private String x938;
	@DatabaseField
	private String x939;
	@DatabaseField
	private Double x940;
	
	@DatabaseField
	private String x941;
	@DatabaseField
	private String x942;
	@DatabaseField
	private String x943;
	@DatabaseField
	private String x944;
	@DatabaseField
	private Double x945;
	
	@DatabaseField
	private String x946;
	@DatabaseField
	private String x947;
	@DatabaseField
	private String x948;
	@DatabaseField
	private String x949;
	@DatabaseField
	private Double x950;
	
	@DatabaseField
	private String x951;
	@DatabaseField
	private String x952;
	@DatabaseField
	private String x953;
	@DatabaseField
	private String x954;
	@DatabaseField
	private Double x955;
	
	@DatabaseField
	private String x956;
	@DatabaseField
	private String x957;
	@DatabaseField
	private String x958;
	@DatabaseField
	private String x959;
	@DatabaseField
	private Double x960;
	
	
	@DatabaseField
	private Double x300;
	@DatabaseField
	private Integer status;//状态  1=暂时保存, 2=确认提交
	@DatabaseField
	private Double x270;
	@DatabaseField
	private Double x271;
	@DatabaseField
	private Double x272;
	@DatabaseField
	private Double x273;
	@DatabaseField
	private Double x274;
	@DatabaseField
	private Double x275;
	@DatabaseField
	private Double x171;
	
	private HouseConfirm houseConfirm;
	
	@DatabaseField
	private Double x9;
	@DatabaseField
	private Double x10;
	@DatabaseField
	private Double x184;
	@DatabaseField
	private Double x188;
	@DatabaseField
	private Double x202;
	@DatabaseField
	private Double x203;
	@DatabaseField
	private Double x15;
	@DatabaseField
	private Double x16;
	@DatabaseField
	private Double xd1;
	@DatabaseField
	private Double xd2;
	@DatabaseField
	private Double xd3;
	@DatabaseField
	private Double xd4;
	@DatabaseField
	private Double xd5;
	@DatabaseField
	private String xs1;
	@DatabaseField
	private String xs2;
	@DatabaseField
	private String xs3;
	@DatabaseField
	private String xs4;
	@DatabaseField
	private String xs5;
	@DatabaseField
	private Double x187;
	@DatabaseField
	private Double x14;
	@DatabaseField
	private Double x86;
	@DatabaseField
	private Double x204;
	@DatabaseField
	private Double x205;
	@DatabaseField
	private Double x12;
	@DatabaseField
	private Double x126;
	@DatabaseField
	private Double x128;
	@DatabaseField
	private Double x129;
	@DatabaseField
	private Double x130;
	@DatabaseField
	private Double x131;
	@DatabaseField
	private Double x136;
	@DatabaseField
	private Integer x139;
	@DatabaseField
	private Double x137;
	@DatabaseField
	private Integer x140;
	@DatabaseField
	private Double x138;
	@DatabaseField
	private Integer x141;
	@DatabaseField
	private Double x145;
	@DatabaseField
	private Double x146;
	@DatabaseField
	private Double x212_011;
	
	
	
	private Double x107;
	private Double x108;
	private Double x109;
	
	
	
	//国有个人产权调换算单内新增的实体X600-X607 产权调换房屋公摊系数，X500-X504 临时安置费房屋面积
	
	private Double x600;
	
	private Double x601;
	
	private Double x602;
	
	private Double x603;
	
	private Double x604;
	
	private Double x605;
	
	private Double x606;
	
	private Double x607;
	
	
    private Double x608;
	
	private Double x609;
    
	private Double x610;
	
	private Double x611;
	
	private Double x612;
	
	private Double x613;
	
	private Double x614;
	
	private Double x615;
	
	private Double x616;
	
	private Double x617;
    
	private Double x618;
	
	private Double x619;
	
	
	
	//国有个人产权调换算单“三、产权调换房屋大于或小于被征收房屋时的处理，超安面积x64input  输入值
	private Double x64input;
	
	//国有个人产权调换算单 选择值做条件判断展示什么内容
	private String bqflagvalue;
	
	private Double x500;
	
	private Double x501;
	
	private Double x502;
	
	private Double x503;
	
	private Double x504;
	
	
	
   
	
	private Double a501;
	
	private Double a502;
	
	private Double a503;
	
	private Double a504;
	
	private Double a505;
	
	private Double a506;
	
	
	private Integer a98;
	
	private Integer a100;
	
	private Integer a102;
	
	private Integer a104;
	
	private Integer a106;
	
	private Integer a108;
	
	
	
	private Double a1000;
	
	private Double a1001;
	
	private Double a1002;
	
	
	private Double x800;
	private Double x801;
	private Double x802;
	private Double x803;
	
	

	//1月23日新补充
	private Integer household_Type;;//分户类型
	
  
	//3月18日新补充
	private Double x1000;
	
	private Double e111;
	
	
	/**
	 * X13_100=X11+X12
	 */
	
	public Double getX13_100(){
		return new BigDecimal(getX12() + houseConfirm.getX11())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * X11_63=X11-X63
	 */
	
	public Double getX11_63(){
		return new BigDecimal(houseConfirm.getX11() - getX63() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	//X5_100=X9+X10+X184+X188+X202+X203+X11
	
//	public Double getX5_100(){
//		return new BigDecimal(getX9() + getX10() + getX184() + getX188() + getX202() + getX203() + houseConfirm.getX11())
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
	
	public Double getX5_100(){
//		getX9() + getX10() + getX184() + getX188() + getX202() + getX203() + + houseConfirm.getX11()
		return new BigDecimal(houseConfirm.getX5_10())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	//X64_100=X63-X13
	
	public Double getX64_100(){
		return new BigDecimal(getX63() - getX13_100() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//X90_100
	
//	public Double getX90_100(){
//		return new BigDecimal(getX13_100() * getX88() * getX89()  )
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
//	
//	
//	public Double getX92_100(){
//		return new BigDecimal(getX63() * getX88() * getX91()  )
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
//	
//	public Double getX94_100(){
//		return new BigDecimal( (getX13_100()-getX63() ) * getX88() * getX93())
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
	
	//2月5日修改
	
	public Double getX90_100(){
		return new BigDecimal(getA1000() * getX88() * getX89()  )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX92_100(){
		return new BigDecimal(getA1001() * getX88() * getX91()  )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX94_100(){
		return new BigDecimal(  getA1002() * getX88() * getX93())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	
	
	
	
	public Double getX106_100(){
		return new BigDecimal( getX13_100()* getX170())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX127_100(){
		return new BigDecimal( getX13_100()* getX126())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX132_100(){
		return new BigDecimal( getX47()* getX128())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX133_100(){
		return new BigDecimal( getX52()* getX129())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX134_100(){
		return new BigDecimal( getX57()* getX130())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX135_100(){
		return new BigDecimal( getX62()* getX131())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX142_100(){
		return new BigDecimal( getX136()* getX139())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX143_100(){
		return new BigDecimal( getX137()* getX140())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX144_100(){
		return new BigDecimal( getX138()* getX141())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX147_100(){ 
		return new BigDecimal( houseConfirm.getX46() * getX146())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX151_100(){
		/**
		 * X112
			X113
			X115
			X118
			X210
			X211
			X119
			X120
			X127_100---删除 getX127_100()+
			X132_100
			X133_100
			X134_100
			X135_100
			X142_100
			X143_100
			X144_100
			X147_100
			X150
			X168
			X169
		 */
		return new BigDecimal( getX112()+ getX113() + getX115() + getX118() + getX210() + getX211() + getX119() + getX120() +
				getX132_100() + getX133_100() + getX134_100() + getX135_100() + getX142_100() + getX143_100() + getX144_100() + getX147_100()+
				getX150() + getX168() + getX169())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX162_100(){
		//X162_100=X90_100+X92_100+X94_100+X97+X99+X101+X103+X105+X106+X107+X108+X109+X161+X169
		
		//1月9日之前的公式
//		return new BigDecimal(getX90_100()+getX92_100()+ getX94_100() + getX97()+getX99()+getX101() +
//				getX107() + getX108() + getX109() +				
//				getX103() + getX105() + getX106() + getX161() + getX169())
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		//1月9日之后的公式
		return new BigDecimal(getX90_100()+getX92_100()+getX94_100()+getX97_000()+getX99_000()+getX101_000() +
				getX103_000() + getX105_000() + getX106_100() + getX161() + getX169() + getX107() + getX108() + getX109()
				+getX78() + getX80() + getX83() + getX87() + getX206() + getX207()  + 
				getA99_000() + getA101_000() + getA103_000() + getA105_000() + getA107_000() + getA109_000()
				)
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
		
	
	public Double getX163_100(){
		//X163=X112+X113+X115+X118+X210+X211+X119+X120+X127_100+X132_100+X133_100+X134_100+X135_100+X
		//142_100+X143_100+X144_100+X147_100+X150+X168+x282
		return new BigDecimal( getX112() + getX113() + getX115() + getX118() + getX210() + getX211() + getX119() + getX120() +
				getX132_100()+ getX133_100() + getX134_100() + getX135_100() + getX142_100() + getX143_100() + getX144_100() +getX147_100() + getX150() +getX168() + getX282())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX162_100X163_100(){		
		return new BigDecimal( getX162_100() + getX163_100())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	/**
	 * X5_010=X9+X10+X184+X188+X202+X203+X11
	 */
	
	public Double getX5_010(){
		Double d = new BigDecimal(getX9()+getX10()+getX184()+getX188()+getX202() + getX203() + houseConfirm.getX11())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d==null?0.00:d;
	}
	
	
	/**
	 * 国有企业货币
	 * @return
	 */
/*	public Double getX5_011(){
		Double d = new BigDecimal(houseConfirm.getX12() + houseConfirm.getX11() + houseConfirm.getX600())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d==null?0.00:d;
	}*/
	
	/**
	 * 集体企业货币
	 * @return
	 */
/*	public Double getX5_111(){
		Double d = new BigDecimal(houseConfirm.getX12() + houseConfirm.getX11() + houseConfirm.getX600())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d==null?0.00:d;
	}
	*/
	
	
	
	/**
	 * 	
	 * @return
	 */
	
	public Double getX13_010(){
		//X13_010=X5_010-X12
		Double d = new BigDecimal(getX5_010() - houseConfirm.getX12())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d==null?0.00:d;
	}
	
	/**
	 * 集体企业货币	
	 * @return
	 */
/*	public Double getX13_111(){
		Double d = new BigDecimal(getX5_111() - houseConfirm.getX12())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d==null?0.00:d;
	}*/
	
	
	
	/**
	 * 国有企业货币	
	 * @return
	 */
	
	/*public Double getX13_011(){
		Double d = new BigDecimal(getX5_011() - houseConfirm.getX12())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d==null?0.00:d;
	}*/
	
	//X18_010=X16-X17
	
	public Double getX18_010(){
		return new BigDecimal(getX16() - getXd4())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
//	X19_010=X17-X16
	
	public Double getX19_010(){
		return new BigDecimal(getXd4() - getX16())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
//	public Double getX25_010(){
//		//getX25_010 = xd1 * xd2 ;
//		return new BigDecimal(getX13_010() * getXd2())
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}

	public Double getX25_010(){
		//getX25_010 = xd1 * xd2 ;
		return new BigDecimal(getE111() * getXd2())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	/**
	 * X107+X108+X109---- 以0表示
	 * X63_010=X18_010+X88+X95+X25_010+X107+X108+X109+X187+X161+X169
	 */
	
	public Double getX63_010(){
		return new BigDecimal(getX18_010() + getX88() + getX95() + getX25_010() 
				+ getX107() +getX108()+
				getX109() + getX187() + getX161() + getX169() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * X52_010=Xd3+X282+X168
	 */
	
	public Double getX52_010(){
		return new BigDecimal(getXd3() + getX282() + getX168())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * X64=X63_010+X52_010-X19_010
	 * @return
	 */
	
	public Double getX64_010(){
		return new BigDecimal(getX63_010() + getX52_010() - getX19_010())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}
	//private static final NumberFormat FORMAT = new DecimalFormat("0.00");
	
	/*return new BigDecimal(getX65() * getX66())
	.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();*/
	
	
	public Double getX212_001(){
		//X78+X80+X83+X87+X206+X207
		return new BigDecimal(getX78_1()+getX80_1()+getX83_1()+getX87_1()+getX206_1() + getX207_1() ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX25_001(){
		//X13		㎡×	X88	元/㎡×	X89
		// x13 改为 x11
		return new BigDecimal(houseConfirm.getX11() * getX88() * getX89() ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
//	public Double getX25_111(){
//		return new BigDecimal(getX13_010() * getX88() * getX89() ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
//	
//	public Double getX30_111(){
//		return new BigDecimal(getX13_010() * getX95()  ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
	
	
/*	public Double getX25_111(){
		return new BigDecimal(getX13_111() * getX88() * getX89() ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}*/
	
	//3月18日修改
	public Double getX25_111(){
		return new BigDecimal(getX1000() * getX88() * getX89() ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX30_111(){
		return new BigDecimal(getX13_010() * getX95()  ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX30_001(){
		//X13		㎡×	X95
		return new BigDecimal(houseConfirm.getX13() * getX95() ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 没有加 107,108,109
	 * @return
	 */
	
	public Double getX63_001(){
		//=X212_001+X25_001+X30_001+X107+X108+X109+X161+X169
		return new BigDecimal(getX212_001() + getX25_001() + getX30_001() + 
	   getX107() + getX108()+ getX109() + getX161() + getX169() )
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//X52_001=X112+X113+X115+X118+X210+X211+X119+X120+X150+X168+X282
	
	
	public Double getX52_001(){
		return new BigDecimal(getX112() + getX113() + getX115() + getX118() + getX210() 
				+ getX211() + getX119() + getX120() + getX150() + getX168() + getX282())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//X64_001=X63_001+X52_001
	
	public Double getX64_001(){
		return new BigDecimal(getX63_001() + getX52_001())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
//	public Double getX63(){
//		//X63=X24+X29+X34+X39+X47+X52+X57+62
//		return new BigDecimal(getX24()+getX29()+getX34()+getX39()
//				+getX47()+getX52()+getX57()+getX62()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
	
	
	//3月12日修改
	public Double getX63(){
		//X63=X24+X29+X34+X39+X47+X52+X57+62
		return CommonForDouble2(getX24()+getX29()+getX34()+getX39()
				+getX47()+getX52()+getX57()+getX62()
				+getX905() + getX910()+getX915() + getX920() + getX925() + getX930() + getX935() 
				+ getX940() + getX945() + getX950() + getX955() + getX960());
	      }
	
	public Double getX64(){
		//x64 = x63 - x11
		return new BigDecimal(getX63()- houseConfirm.getX11())
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	/**
	 * x67 = x65 * x66  
	 * @return
	 */
	
	public Double getX67(){
		return new BigDecimal(getX65() * getX66())
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX71(){
		return new BigDecimal(getX68()*getX69()*getX70() / 100)
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX75(){
		return new BigDecimal(getX72()*getX69()*getX74() / 100)
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX76(){
		return new BigDecimal(getX67()+getX71()+getX75())
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX78(){
		//x78=x77 * x14
		return new BigDecimal(getX77() * houseConfirm.getX14())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX80(){
		//x80=x79 * x15
		return new BigDecimal(getX79() * houseConfirm.getX15())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX83(){
		//x83=x82 * x16
		return new BigDecimal(getX82() * houseConfirm.getX16())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX87(){
		//x87=x85 * x86
		return new BigDecimal(getX85() * houseConfirm.getX86())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX206(){
		//206 = 221 * 204
		return new BigDecimal(getX221() * houseConfirm.getX204())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX207(){
		//207 = 222 * 205
		return new BigDecimal(getX222() * houseConfirm.getX205())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
//	public Double getX90(){
//		//x90 = x11 * x88 * x89
//		return new BigDecimal(houseConfirm.getX11() * getX88() * getX89())
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
	
	//2月5日修改
	
	public Double getX90(){
	//x90 = x11 * x88 * x89
	return new BigDecimal(getA1000() * getX88() * getX89())
	.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
}
	
	
	
	public Double getX106(){
		//x106 = x170 * x11
		return new BigDecimal(houseConfirm.getX11() * getX170())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX112(){
		//X164	     ㎡×	X14	元/㎡	×上浮	X111%	=	X112
		return new BigDecimal(getX164() * houseConfirm.getX14() * getX111() / 100)
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX113(){
		//X165	     ㎡×	X15	元/㎡	×上浮	X111%	=	X113
		return new BigDecimal(getX165() * houseConfirm.getX15() * getX111() / 100)
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX115(){
		//X166	     ㎡×	X16	元/㎡	×上浮	X111%	=	X115
		return new BigDecimal(getX166() * houseConfirm.getX16() * getX111() / 100)
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX118(){
		//X167	     ㎡×	X86	元/㎡	×上浮	X111%	=	X118
		return new BigDecimal(getX167() * houseConfirm.getX86() * getX111() / 100)
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX210(){
		//X208	     ㎡×	X204	元/㎡	×上浮	X111%	=	X210
		return new BigDecimal(getX208() * houseConfirm.getX204() * getX111() / 100)
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX211(){
		//X209	     ㎡×	X205	元/㎡	×上浮	X111%	=	X211
		return new BigDecimal(getX209() * houseConfirm.getX205() * getX111() / 100)
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX24X29X34X39(){
		return new BigDecimal(getX24()+getX29()+getX34() + getX39())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX13_100X63(){
		return new BigDecimal(getX13_100() - getX63())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	/**
	 * ?????
	 * @return
	 */
	
	public Double getX151(){
		
		return new BigDecimal(getX112() + getX113() +getX115() + 
				getX118() + getX210() + getX211() + getX119() + 
				getX120() + getX150() + getX168() + getX169()) 
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX154(){
		//X152	㎡×	X153	元/㎡=	X154		
		return new BigDecimal(getX152() * getX153() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX157(){
		//X155	㎡×	X156	元/㎡=	X157		
		return new BigDecimal(getX155() * getX156() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX160(){
		//X158	㎡×	X159	元/㎡=	X160		
		return new BigDecimal(getX158() * getX159() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX223(){
		//X212	㎡×	X215	元/㎡=	X223		
		return new BigDecimal(getX212() * getX215() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX219(){
		//X213	㎡×	X216	元/㎡=	X219		
		return new BigDecimal(getX213() * getX216() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX220(){
		//X214	㎡×	X217	元/㎡=	X220		
		return new BigDecimal(getX214() * getX217() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX161(){
		////X161=X154+X157+X160+X223+X219+X220
		return new BigDecimal(getX154() + getX157() + getX160() + 
				getX223() + getX219() + getX220())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX276(){
		return  new BigDecimal(getX152() * getX270())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	public Double getX277(){
		return  new BigDecimal(getX155() * getX271())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX278(){
		return  new BigDecimal(getX158() * getX272())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	public Double getX279(){
		return  new BigDecimal(getX212() * getX273())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX280(){
		return  new BigDecimal(getX213() * getX274())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX281(){
		return  new BigDecimal(getX214() * getX275())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX282(){
		return  new BigDecimal(getX276() + getX277() + getX278() + getX279() + getX280() + getX281())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
//	public Double getX92(){
//		//X63			㎡×	X88	元/㎡×	X91	次=	X92
//		return new BigDecimal(getX63() * getX88() * getX91() )
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
//	
//	public Double getX94(){
//		//(X11-X63	)		㎡×	X88	元/㎡×	X93	次=	X94
//		return new BigDecimal((houseConfirm.getX11()- getX63()) * getX88() * getX93() )
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
	
	
	
	public Double getX92(){
		//X63			㎡×	X88	元/㎡×	X91	次=	X92
		return new BigDecimal(getA1001() * getX88() * getX91() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX94(){
		//(X11-X63	)		㎡×	X88	元/㎡×	X93	次=	X94
		return new BigDecimal( getA1002() * getX88() * getX93() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	/**
	 * 注意这里少了 + getX107() + getX108() + getX109() 这些内容补偿计算单中的附件总额
	 * @return
	 */
	
	public Double getX162(){
		//X162=X90+X92+X94+X97+X99+X101+X103+X105+X106+X107+X108+X109+X161+X169
		//+ getX107() + getX108() + getX109() 
		
		/*2月4日注释*/
//		return new BigDecimal(getX90() + getX92() +getX94() + getX97() + getX99() + getX101()+
//				getX103() + getX105() + getX106() + getX107() +getX108()+
//				getX109() + getX161() + getX169())
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		
		return new BigDecimal(getX90() + getX92() +getX94() + getX97_000() + getX99_000() + getX101_000()+
				getX103_000() + getX105_000() + getX106()  + getX161() +
				getX169() + getX107() + getX108() + getX109()+
				getA99_000() + getA101_000() + getA103_000() + getA105_000() + getA107_000() + getA109_000())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
		
	
	
	public Double getX163(){
		//X163=X112+X113+X115+X118+X210+X211+X119+X120+X150+X168
		return new BigDecimal(getX112() + getX113() + getX115() + getX118() + getX210() 
				+ getX211() + getX119() + getX120() + getX150() + getX168() + getX282())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX162X163(){
		return this.getX162() + this.getX163();
	}
	
	
	public Double getX97_000(){
		return new BigDecimal(getX500() * getX95() * getX96()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public Double getX99_000(){
		return new BigDecimal(getX501() * getX95() * getX98()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX101_000(){
		return new BigDecimal(getX502() * getX95() * getX100()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX103_000(){
		return new BigDecimal(getX503() * getX95() * getX102()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX105_000(){
		return new BigDecimal(getX504() * getX95() * getX104()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	
	public Double getX97(){
		return new BigDecimal(getX24X29X34X39() * getX95() * getX96())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX99(){
		return new BigDecimal(getX47() * getX95() * getX98())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX101(){
		return new BigDecimal(getX52() * getX95()  * getX100())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX103(){
		return new BigDecimal(getX57() * getX95() * getX102())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	public Double getX105(){
		return new BigDecimal(getX62() * getX95()  * getX104())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	
	public Double getX97_100(){
		return new BigDecimal(getX500() * getX95() * getX96()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public Double getX99_100(){
		return new BigDecimal(getX501() * getX95() * getX98()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX101_100(){
		return new BigDecimal(getX502() * getX95() * getX100()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX103_100(){
		return new BigDecimal(getX503() * getX95() * getX102()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX105_100(){
		return new BigDecimal(getX504() * getX95() * getX104()
				).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Double getX150(){
		return new BigDecimal(getX148() * getX149())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX11X63(){
		return new BigDecimal(houseConfirm.getX11()-getX63())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
//	public Double getX25_011(){
//		return new BigDecimal(getX13_011() * getX88() * getX89() )
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
	
	//3月18日修改
	public Double getX25_011(){
		return new BigDecimal(getX1000() * getX88() * getX89() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX30_011(){
		return new BigDecimal(getX13_010() * getX95() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX17_011(){
		return new BigDecimal(getX77() * getX14() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX18_011(){
		return new BigDecimal(getX79() * getX15() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX19_011(){
		return new BigDecimal(getX82() * getX16() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX65_011(){
		return new BigDecimal(getX85() * getX86() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX208_011(){
		return new BigDecimal(getX221() * getX204() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX209_011(){
		return new BigDecimal(getX222() * getX205() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	//这里改为输入
	/*//X212_011=X17_011+X18_011+X19_011+X65_011+X208_011+X209_011
	
	public Double getX212_011(){
		return new BigDecimal(getX17_011() + getX18_011() + getX19_011() +getX65_011() + getX208_011() + getX209_011() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}*/
	
	
	
	public Double getX33_011(){
		//X77 ㎡× 14 元/㎡× X111
		return new BigDecimal(getX77() * getX14() * getX111() / 100 )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX34_011(){
		return new BigDecimal(getX79() * getX15() * getX111() /100 )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX35_011(){
		return new BigDecimal(getX82() * getX16() * getX111() /100 )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX36_011(){
		return new BigDecimal(getX85() * getX86() * getX111() /100 )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX210_011(){
		return new BigDecimal(getX221() * getX204() * getX111() /100 )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX211_011(){
		return new BigDecimal(getX222() * getX205() * getX111() /100 )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 不包含 107,108,109
	 * @return
	 */
	//X63_011=X212_011+X25_001+X30_001+X107+X108+X109+X171+X161+X187+X169
	
	public Double getX63_011(){
		return new BigDecimal(getX212_011() + getX25_011() + getX30_011() + 
				getX107() + getX108()+
				getX109() +	getX171() + getX161() + getX187() + getX169())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//X52_011=X33_011+X34_011+X35_011+X36_011+X210_011+X211_011+X119+X282+X168
	
	
	public Double getX52_011(){
		return new BigDecimal(getX33_011() + getX34_011() + getX35_011() + getX36_011() 
				+ getX210_011() + getX211_011() + getX119() + getX282() + getX168())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX64_011(){
		//=X63_011+X52_011
		return new BigDecimal(getX63_011() + getX52_011())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//--------------------101----------------
//	/X13_101=X5_100-X12
	
//	public Double getX13_101(){
//		return new BigDecimal(getX5_100() - getX12())
//		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
//	
	
	//3月12日修改
	public Double getX13_101(){
		return CommonForDouble2(houseConfirm.getX11() + getX12());
	}
	
	public Double getX78_1(){
		//X78_1 = X164	㎡×	X14
		return new BigDecimal(getX164() * houseConfirm.getX14())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX80_1(){
		//X165	㎡×	X15	元/㎡=	X80_1
		return new BigDecimal(getX165() * houseConfirm.getX15())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX83_1(){
		//X166	㎡×	X16	元/㎡=	X83_1	
		return new BigDecimal(getX166() * houseConfirm.getX16())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX87_1(){
		//X167	㎡×	X86	元/㎡=	X87_1	
		return new BigDecimal(getX167() * houseConfirm.getX86())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX206_1(){
		//X208	㎡×	X204	元/㎡=	X206_1	
		return new BigDecimal(getX208() * houseConfirm.getX204())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX207_1(){
		//X209	㎡×	X205	元/㎡=	X207_1	
		return new BigDecimal(getX209() * houseConfirm.getX205())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX25_101(){
		return new BigDecimal(getX13_101() * getX88() * getX89())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX30_101(){
		return new BigDecimal(getX13_101() * getX95() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX284_101(){
		return new BigDecimal( getX13_101() * getXd1() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX45_101(){
		return new BigDecimal( getX13_101() * getXd2() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX48_101(){
		return new BigDecimal( houseConfirm.getX46() * getXd3() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//
	
	public Double getX52_101(){
		/**
		 * X112
			X113
			X115
			X118
			X210
			X211
			X284_101
			X119
			X120
			X45_101-----删除
			X48_101
			X150
			X168
			X169
		 */
		return new BigDecimal(getX112() + getX113() + getX115() + getX118() + getX210() + getX211() + getX284_101() + getX119() + getX120() +
				 getX48_101() + getX150() + getX168() + getX169() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 107,108,109 都是单独计算 后相加
	 * @return
	 */
	
	public Double getX63_101(){
		//X63_101=X78_1+X80_1+X83_1+X87_1+X206_1+X207_1+X25_101+X30_101+X107+X108+X109+X171+X161+X169
		
		return new BigDecimal( getX78_1() + getX80_1() + getX83_1() + getX87_1() + getX206_1() 
				+ getX107() +getX108()+	getX109()
				+ getX207_1() + getX25_101() + getX30_101() + getX171() + getX161() + getX169())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getX53_101(){
		//getX45_101() + 删除
		//X52_101=X112+X113+X115+X118+X210+X211+X284_101+X119+X120+X45_101+X48_101+X150+X168+X282
		return new BigDecimal( getX112() + getX113() + getX115() + getX118() + getX210() + getX211() + getX284_101() 
				+ getX119() + getX120() +  getX48_101() + getX150() + getX168() + getX282())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public Double getX64_101(){
		//X64_101=X63_101+X53_101
		return new BigDecimal(getX63_101() + getX53_101())
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//--------------------end-------------------
	
	
	//-----------------------111--------------
	
	public Double getX52_111(){
		/*
		 * X33_011
			X34_011
			X35_011
			X36_011
			X210_011
			X211_011
			X119
			X150  删除 getX150() +
			X168
			X169
		 */
		return new BigDecimal(getX33_011() + getX34_011() + getX35_011() + getX36_011() + getX210_011() + getX211_011() + getX119() + 
				getX168() + getX169() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * X63_111=X212_011+X25_111+X30_111+X107+X108+X109+X171+X187+X161+X169
	 */
	
	public Double getX63_111(){
		return new BigDecimal(getX212_011() + getX25_111() + getX30_111() + getX171() + getX187() 
				+ getX107() +getX108()+	getX109()
				+ getX161() + getX169() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * X64_111=X63_111+X52_011
	 */
	
	public Double getX64_111(){
		return new BigDecimal(getX63_111() + getX52_011() )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	/**
	 * 保留两个位小数点并进行四舍五入
	 * @param doubleValue
	 * @return
	 */
	public double CommonForDouble2(double doubleValue){
		return (double)(Math.round(doubleValue*100))/100;
	}
	
	
	
	/**
	 * a501			㎡×	X95	元/㎡×	a98	个月=	a99_000
	 * @return
	 */
	public Double getA99_000(){
		return CommonForDouble2(getA501() * getX95() * getA98());
	}
	
	public Double getA101_000(){
		
		return CommonForDouble2(getA502() * getX95() * getA100());
	}
	
	public Double getA103_000(){
		return CommonForDouble2(getA503() * getX95() * getA102());
	}
	
	public Double getA105_000(){
		return CommonForDouble2(getA504() * getX95() * getA104());
	}
	
	public Double getA107_000(){
		return CommonForDouble2(getA505() * getX95() * getA106());
	}
	
	public Double getA109_000(){
		return CommonForDouble2(getA506() * getX95() * getA108());
	}
	
	
	
	
	
	
	
	
	//-------------------------end--------------
	public HouseList() {
	}

	public HouseConfirm getHouseConfirm() {
		return houseConfirm;
	}

	public void setHouseConfirm(HouseConfirm houseConfirm) {
		this.houseConfirm = houseConfirm;
	}

	public String getHid() {
		return this.hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Double getX111() {
		return this.x111==null?0:this.x111;
	}

	public void setX111(Double x111) {
		this.x111 = x111;
	}

	public Double getX119() {
		return this.x119==null?0:this.x119;
	}

	public void setX119(Double x119) {
		this.x119 = x119;
	}

	public Double getX120() {
		return this.x120==null?0:this.x120;
	}

	public void setX120(Double x120) {
		this.x120 = x120;
	}

	public Double getX148() {
		return this.x148==null?0:this.x148;
	}

	public void setX148(Double x148) {
		this.x148 = x148;
	}

	public Double getX152() {
		return this.x152==null?0:this.x152;
	}

	public void setX152(Double x152) {
		this.x152 = x152;
	}

	public Double getX153() {
		return this.x153==null?0:this.x153;
	}

	public void setX153(Double x153) {
		this.x153 = x153;
	}

	public Double getX155() {
		return this.x155==null?0:this.x155;
	}

	public void setX155(Double x155) {
		this.x155 = x155;
	}

	public Double getX156() {
		return this.x156==null?0:this.x156;
	}

	public void setX156(Double x156) {
		this.x156 = x156;
	}

	public Double getX158() {
		return this.x158==null?0:this.x158;
	}

	public void setX158(Double x158) {
		this.x158 = x158;
	}

	public Double getX159() {
		return this.x159==null?0:this.x159;
	}

	public void setX159(Double x159) {
		this.x159 = x159;
	}

	public Double getX164() {
		return this.x164==null?0:this.x164;
	}

	public void setX164(Double x164) {
		this.x164 = x164;
	}

	public Double getX165() {
		return this.x165==null?0:this.x165;
	}

	public void setX165(Double x165) {
		this.x165 = x165;
	}

	public Double getX166() {
		return this.x166==null?0:this.x166;
	}

	public void setX166(Double x166) {
		this.x166 = x166;
	}

	public Double getX167() {
		return this.x167==null?0:this.x167;
	}

	public void setX167(Double x167) {
		this.x167 = x167;
	}


	public Double getX168() {
		return x168==null?0:this.x168;
	}

	public void setX168(Double x168) {
		this.x168 = x168;
	}

	public Double getX169() {
		return x169==null?0:this.x169;
	}

	public void setX169(Double x169) {
		this.x169 = x169;
	}

	public String getX17() {
		return this.x17;
	}

	public void setX17(String x17) {
		this.x17 = x17;
	}

	public Double getX170() {
		return this.x170==null?0:this.x170;
	}

	public void setX170(Double x170) {
		this.x170 = x170;
	}

	public String getX185() {
		if(getMainnum()==null && getSubnum()==null){
			return "";
		}
		return getMainnum()+"-"+getSubnum();
	}
	
	public Long getMainnum() {
		return mainnum;
	}

	public void setMainnum(Long mainnum) {
		this.mainnum = mainnum;
	}

	public Long getSubnum() {
		return subnum;
	}

	public void setSubnum(Long subnum) {
		this.subnum = subnum;
	}



	public String getX2() {
		return this.x2;
	}

	public void setX2(String x2) {
		this.x2 = x2;
	}

	public String getX20() {
		return this.x20;
	}

	public void setX20(String x20) {
		this.x20 = x20;
	}

	public Double getX208() {
		return this.x208==null?0:this.x208;
	}

	public void setX208(Double x208) {
		this.x208 = x208;
	}

	public Double getX209() {
		return this.x209==null?0:this.x209;
	}

	public void setX209(Double x209) {
		this.x209 = x209;
	}

	public String getX21() {
		return this.x21;
	}

	public void setX21(String x21) {
		this.x21 = x21;
	}

	public Double getX212() {
		return this.x212==null?0:this.x212;
	}

	public void setX212(Double x212) {
		this.x212 = x212;
	}

	public Double getX213() {
		return this.x213==null?0:this.x213;
	}

	public void setX213(Double x213) {
		this.x213 = x213;
	}

	public Double getX214() {
		return this.x214==null?0:this.x214;
	}

	public void setX214(Double x214) {
		this.x214 = x214;
	}

	public Double getX215() {
		return this.x215==null?0:this.x215;
	}

	public void setX215(Double x215) {
		this.x215 = x215;
	}

	public Double getX216() {
		return this.x216==null?0:this.x216;
	}

	public void setX216(Double x216) {
		this.x216 = x216;
	}

	public Double getX217() {
		return this.x217==null?0:this.x217;
	}

	public void setX217(Double x217) {
		this.x217 = x217;
	}

	public String getX22() {
		return this.x22;
	}

	public void setX22(String x22) {
		this.x22 = x22;
	}

	public Double getX221() {
		return this.x221==null?0:this.x221;
	}

	public void setX221(Double x221) {
		this.x221 = x221;
	}

	public Double getX222() {
		return this.x222==null?0:this.x222;
	}

	public void setX222(Double x222) {
		this.x222 = x222;
	}

	public String getX23() {
		return this.x23;
	}

	public void setX23(String x23) {
		this.x23 = x23;
	}

	public Double getX24() {
		return this.x24==null?0:this.x24;
	}

	public void setX24(Double x24) {
		this.x24 = x24;
	}

	public String getX25() {
		return this.x25;
	}

	public void setX25(String x25) {
		this.x25 = x25;
	}

	public String getX26() {
		return this.x26;
	}

	public void setX26(String x26) {
		this.x26 = x26;
	}

	public String getX27() {
		return this.x27;
	}

	public void setX27(String x27) {
		this.x27 = x27;
	}

	public String getX28() {
		return this.x28;
	}

	public void setX28(String x28) {
		this.x28 = x28;
	}

	public Double getX29() {
		return this.x29==null?0:this.x29;
	}

	public void setX29(Double x29) {
		this.x29 = x29;
	}

	public String getX3() {
		return this.x3;
	}

	public void setX3(String x3) {
		this.x3 = x3;
	}

	public String getX30() {
		return this.x30;
	}

	public void setX30(String x30) {
		this.x30 = x30;
	}

	public String getX31() {
		return this.x31;
	}

	public void setX31(String x31) {
		this.x31 = x31;
	}

	public String getX32() {
		return this.x32;
	}

	public void setX32(String x32) {
		this.x32 = x32;
	}

	public String getX33() {
		return this.x33;
	}

	public void setX33(String x33) {
		this.x33 = x33;
	}

	public Double getX34() {
		return this.x34==null?0:this.x34;
	}

	public void setX34(Double x34) {
		this.x34 = x34;
	}

	public String getX35() {
		return this.x35;
	}

	public void setX35(String x35) {
		this.x35 = x35;
	}

	public String getX36() {
		return this.x36;
	}

	public void setX36(String x36) {
		this.x36 = x36;
	}

	public String getX37() {
		return this.x37;
	}

	public void setX37(String x37) {
		this.x37 = x37;
	}

	public String getX38() {
		return this.x38;
	}

	public void setX38(String x38) {
		this.x38 = x38;
	}

	public Double getX39() {
		return this.x39==null?0:this.x39;
	}

	public void setX39(Double x39) {
		this.x39 = x39;
	}

	public String getX43() {
		return this.x43;
	}

	public void setX43(String x43) {
		this.x43 = x43;
	}

	public String getX44() {
		return this.x44;
	}

	public void setX44(String x44) {
		this.x44 = x44;
	}

	public String getX45() {
		return this.x45;
	}

	public void setX45(String x45) {
		this.x45 = x45;
	}

	public String getX46() {
		return this.x46;
	}

	public void setX46(String x46) {
		this.x46 = x46;
	}

	public Double getX47() {
		return this.x47==null?0:this.x47;
	}

	public void setX47(Double x47) {
		this.x47 = x47;
	}

	public String getX48() {
		return this.x48;
	}

	public void setX48(String x48) {
		this.x48 = x48;
	}

	public String getX49() {
		return this.x49;
	}

	public void setX49(String x49) {
		this.x49 = x49;
	}

	public String getX50() {
		return this.x50;
	}

	public void setX50(String x50) {
		this.x50 = x50;
	}

	public String getX51() {
		return this.x51;
	}

	public void setX51(String x51) {
		this.x51 = x51;
	}

	public Double getX52() {
		return this.x52==null?0:this.x52;
	}

	public void setX52(Double x52) {
		this.x52 = x52;
	}

	public String getX53() {
		return this.x53;
	}

	public void setX53(String x53) {
		this.x53 = x53;
	}

	public String getX54() {
		return this.x54;
	}

	public void setX54(String x54) {
		this.x54 = x54;
	}

	public String getX55() {
		return this.x55;
	}

	public void setX55(String x55) {
		this.x55 = x55;
	}

	public String getX56() {
		return this.x56;
	}

	public void setX56(String x56) {
		this.x56 = x56;
	}

	public Double getX57() {
		return this.x57==null?0:this.x57;
	}

	public void setX57(Double x57) {
		this.x57 = x57;
	}

	public String getX58() {
		return this.x58;
	}

	public void setX58(String x58) {
		this.x58 = x58;
	}

	public String getX59() {
		return this.x59;
	}

	public void setX59(String x59) {
		this.x59 = x59;
	}

	public String getX60() {
		return this.x60;
	}

	public void setX60(String x60) {
		this.x60 = x60;
	}

	public String getX61() {
		return this.x61;
	}

	public void setX61(String x61) {
		this.x61 = x61;
	}

	public Double getX62() {
		return this.x62==null?0:this.x62;
	}

	public void setX62(Double x62) {
		this.x62 = x62;
	}

	public Double getX65() {
		return this.x65==null?0:this.x65;
	}

	public void setX65(Double x65) {
		this.x65 = x65;
	}

	public Double getX66() {
		return this.x66==null?0:this.x66;
	}

	public void setX66(Double x66) {
		this.x66 = x66;
	}

	public Double getX68() {
		return this.x68==null?0:this.x68;
	}

	public void setX68(Double x68) {
		this.x68 = x68;
	}

	public Double getX69() {
		return this.x69==null?0:this.x69;
	}

	public void setX69(Double x69) {
		this.x69 = x69;
	}

	public Double getX70() {
		return this.x70==null?0:this.x70;
	}

	public void setX70(Double x70) {
		this.x70 = x70;
	}

	public Double getX72() {
		return this.x72==null?0:this.x72;
	}

	public void setX72(Double x72) {
		this.x72 = x72;
	}

	public Double getX74() {
		return this.x74==null?0:this.x74;
	}

	public void setX74(Double x74) {
		this.x74 = x74;
	}

	public Double getX77() {
		return this.x77==null?0:this.x77;
	}

	public void setX77(Double x77) {
		this.x77 = x77;
	}

	public Double getX79() {
		return this.x79==null?0:this.x79;
	}

	public void setX79(Double x79) {
		this.x79 = x79;
	}

	public Double getX82() {
		return this.x82==null?0:this.x82;
	}

	public void setX82(Double x82) {
		this.x82 = x82;
	}

	public Double getX85() {
		return this.x85==null?0:this.x85;
	}

	public void setX85(Double x85) {
		this.x85 = x85;
	}

	public Double getX88() {
		return this.x88==null?0:this.x88;
	}

	public void setX88(Double x88) {
		this.x88 = x88;
	}

	public Double getX95() {
		return this.x95==null?0:this.x95;
	}

	public void setX95(Double x95) {
		this.x95 = x95;
	}

	public Integer getX100() {
		return x100==null?0:this.x100;
	}

	public void setX100(Integer x100) {
		this.x100 = x100;
	}

	public Integer getX102() {
		return x102==null?0:this.x102;
	}

	public void setX102(Integer x102) {
		this.x102 = x102;
	}

	public Integer getX104() {
		return x104==null?0:this.x104;
	}

	public void setX104(Integer x104) {
		this.x104 = x104;
	}

	public Integer getX149() {
		return x149==null?0:this.x149;
	}

	public void setX149(Integer x149) {
		this.x149 = x149;
	}

	public Integer getX89() {
		return x89==null?0:this.x89;
	}

	public void setX89(Integer x89) {
		this.x89 = x89;
	}

	public Integer getX91() {
		return x91==null?0:this.x91;
	}

	public void setX91(Integer x91) {
		this.x91 = x91;
	}

	public Integer getX93() {
		return x93==null?0:this.x93;
	}

	public void setX93(Integer x93) {
		this.x93 = x93;
	}

	public Integer getX96() {
		return x96==null?0:this.x96;
	}

	public void setX96(Integer x96) {
		this.x96 = x96;
	}

	public Integer getX98() {
		return x98==null?0:this.x98;
	}

	public void setX98(Integer x98) {
		this.x98 = x98;
	}

	public String getHid1() {
		return hid1;
	}

	public void setHid1(String hid1) {
		this.hid1 = hid1;
	}

	public String getHid2() {
		return hid2;
	}

	public void setHid2(String hid2) {
		this.hid2 = hid2;
	}

	public String getHid3() {
		return hid3;
	}

	public void setHid3(String hid3) {
		this.hid3 = hid3;
	}

	public String getHid4() {
		return hid4;
	}

	public void setHid4(String hid4) {
		this.hid4 = hid4;
	}

	public String getHid5() {
		return hid5;
	}

	public void setHid5(String hid5) {
		this.hid5 = hid5;
	}

	public String getHid6() {
		return hid6;
	}

	public void setHid6(String hid6) {
		this.hid6 = hid6;
	}

	public String getHid7() {
		return hid7;
	}

	public void setHid7(String hid7) {
		this.hid7 = hid7;
	}

	public String getHid8() {
		return hid8;
	}

	public void setHid8(String hid8) {
		this.hid8 = hid8;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getContracttype() {
		return contracttype;
	}

	public void setContracttype(Integer contracttype) {
		this.contracttype = contracttype;
	}

	public String getX17_1() {
		return x17_1;
	}

	public void setX17_1(String x17_1) {
		this.x17_1 = x17_1;
	}

	public String getX17_2() {
		return x17_2;
	}

	public void setX17_2(String x17_2) {
		this.x17_2 = x17_2;
	}

	public String getX17_3() {
		return x17_3;
	}

	public void setX17_3(String x17_3) {
		this.x17_3 = x17_3;
	}

	public String getX17_4() {
		return x17_4;
	}

	public void setX17_4(String x17_4) {
		this.x17_4 = x17_4;
	}

	public String getX18_1() {
		return x18_1;
	}

	public void setX18_1(String x18_1) {
		this.x18_1 = x18_1;
	}

	public String getX18_2() {
		return x18_2;
	}

	public void setX18_2(String x18_2) {
		this.x18_2 = x18_2;
	}

	public String getX18_3() {
		return x18_3;
	}

	public void setX18_3(String x18_3) {
		this.x18_3 = x18_3;
	}

	public String getX18_4() {
		return x18_4;
	}

	public void setX18_4(String x18_4) {
		this.x18_4 = x18_4;
	}

	public Double getX19_1() {
		return x19_1==null?0:this.x19_1;
	}

	public void setX19_1(Double x19_1) {
		this.x19_1 = x19_1;
	}

	public Double getX19_2() {
		return x19_2==null?0:this.x19_2;
	}

	public void setX19_2(Double x19_2) {
		this.x19_2 = x19_2;
	}

	public Double getX19_3() {
		return x19_3==null?0:this.x19_3;
	}

	public void setX19_3(Double x19_3) {
		this.x19_3 = x19_3;
	}

	public Double getX19_4() {
		return x19_4==null?0:this.x19_4;
	}

	public void setX19_4(Double x19_4) {
		this.x19_4 = x19_4;
	}

	public String getX40_1() {
		return x40_1;
	}

	public void setX40_1(String x40_1) {
		this.x40_1 = x40_1;
	}

	public String getX40_2() {
		return x40_2;
	}

	public void setX40_2(String x40_2) {
		this.x40_2 = x40_2;
	}

	public String getX40_3() {
		return x40_3;
	}

	public void setX40_3(String x40_3) {
		this.x40_3 = x40_3;
	}

	public String getX40_4() {
		return x40_4;
	}

	public void setX40_4(String x40_4) {
		this.x40_4 = x40_4;
	}

	public String getX41_1() {
		return x41_1;
	}

	public void setX41_1(String x41_1) {
		this.x41_1 = x41_1;
	}

	public String getX41_2() {
		return x41_2;
	}

	public void setX41_2(String x41_2) {
		this.x41_2 = x41_2;
	}

	public String getX41_3() {
		return x41_3;
	}

	public void setX41_3(String x41_3) {
		this.x41_3 = x41_3;
	}

	public String getX41_4() {
		return x41_4;
	}

	public void setX41_4(String x41_4) {
		this.x41_4 = x41_4;
	}

	public Double getX42_1() {
		return x42_1==null?0:this.x42_1;
	}

	public void setX42_1(Double x42_1) {
		this.x42_1 = x42_1;
	}

	public Double getX42_2() {
		return x42_2==null?0:this.x42_2;
	}

	public void setX42_2(Double x42_2) {
		this.x42_2 = x42_2;
	}
	
	

	public Double getX270() {
		return x270==null?0:this.x270;
	}

	public void setX270(Double x270) {
		this.x270 = x270;
	}

	public Double getX271() {
		return x271==null?0:this.x271;
	}

	public void setX271(Double x271) {
		this.x271 = x271;
	}

	public Double getX272() {
		return x272==null?0:this.x272;
	}

	public void setX272(Double x272) {
		this.x272 = x272;
	}

	public Double getX273() {
		return x273==null?0:this.x273;
	}

	public void setX273(Double x273) {
		this.x273 = x273;
	}

	public Double getX274() {
		return x274==null?0:this.x274;
	}

	public void setX274(Double x274) {
		this.x274 = x274;
	}

	public Double getX275() {
		return x275==null?0:this.x275;
	}

	public void setX275(Double x275) {
		this.x275 = x275;
	}

	public Double getX42_3() {
		return x42_3==null?0:this.x42_3;
	}

	public void setX42_3(Double x42_3) {
		this.x42_3 = x42_3;
	}

	public Double getX42_4() {
		return x42_4==null?0:this.x42_4;
	}

	public void setX42_4(Double x42_4) {
		this.x42_4 = x42_4;
	}

	public Double getX300() {
		return x300==null?0:this.x300;
	}

	public void setX300(Double x300) {
		this.x300 = x300;
	}

	public Double getX171() {
		return x171==null?0.00:x171;
	}

	public void setX171(Double x171) {
		this.x171 = x171;
	}

	public Double getX9() {
		return x9==null?0.00:x9;
	}

	public void setX9(Double x9) {
		this.x9 = x9;
	}

	public Double getX10() {
		return x10==null?0.00:x10;
	}

	public void setX10(Double x10) {
		this.x10 = x10;
	}

	public Double getX184() {
		return x184==null?0.00:x184;
	}

	public void setX184(Double x184) {
		this.x184 = x184;
	}

	public Double getX188() {
		return x188==null?0.00:x188;
	}

	public void setX188(Double x188) {
		this.x188 = x188;
	}

	public Double getX202() {
		return x202==null?0.00:x202;
	}

	public void setX202(Double x202) {
		this.x202 = x202;
	}

	public Double getX203() {
		return x203==null?0.00:x203;
	}

	public void setX203(Double x203) {
		this.x203 = x203;
	}

	public Double getXd1() {
		return xd1==null?0.00:xd1;
	}

	public void setXd1(Double xd1) {
		this.xd1 = xd1;
	}

	public Double getXd2() {
		return xd2==null?0.00:xd2;
	}

	public void setXd2(Double xd2) {
		this.xd2 = xd2;
	}

	public Double getXd3() {
		return xd3==null?0.00:xd3;
	}

	public void setXd3(Double xd3) {
		this.xd3 = xd3;
	}

	public Double getXd4() {
		return xd4==null?0.00:xd4;
	}

	public void setXd4(Double xd4) {
		this.xd4 = xd4;
	}


	public Double getX15() {
		return x15==null?0.00:x15;
	}


	public void setX15(Double x15) {
		this.x15 = x15;
	}


	public Double getX16() {
		return x16==null?0.00:x16;
	}


	public void setX16(Double x16) {
		this.x16 = x16;
	}


	public Double getXd5() {
		return xd5==null?0.00:xd5;
	}


	public void setXd5(Double xd5) {
		this.xd5 = xd5;
	}


	public String getXs1() {
		return xs1;
	}


	public void setXs1(String xs1) {
		this.xs1 = xs1;
	}


	public String getXs2() {
		return xs2;
	}


	public void setXs2(String xs2) {
		this.xs2 = xs2;
	}


	public String getXs3() {
		return xs3;
	}


	public void setXs3(String xs3) {
		this.xs3 = xs3;
	}


	public String getXs4() {
		return xs4;
	}


	public void setXs4(String xs4) {
		this.xs4 = xs4;
	}


	public String getXs5() {
		return xs5;
	}


	public void setXs5(String xs5) {
		this.xs5 = xs5;
	}


	public Double getX187() {
		return x187==null?0.00:x187;
	}


	public void setX187(Double x187) {
		this.x187 = x187;
	}


	public Double getX14() {
		return x14==null?0.00:x14;
	}


	public void setX14(Double x14) {
		this.x14 = x14;
	}


	public Double getX86() {
		return x86==null?0.00:x86;
	}


	public void setX86(Double x86) {
		this.x86 = x86;
	}


	public Double getX204() {
		return x204==null?0.00:x204;
	}


	public void setX204(Double x204) {
		this.x204 = x204;
	}


	public Double getX205() {
		return x205==null?0.00:x205;
	}


	public void setX205(Double x205) {
		this.x205 = x205;
	}


	public Double getX12() {
		return x12==null?0.00:x12;
	}


	public void setX12(Double x12) {
		this.x12 = x12;
	}


	public Double getX126() {
		return x126==null?0.00:x126;
	}


	public void setX126(Double x126) {
		this.x126 = x126;
	}


	public Double getX128() {
		return x128==null?0.00:x128;
	}


	public void setX128(Double x128) {
		this.x128 = x128;
	}


	public Double getX129() {
		return x129==null?0.00:x129;
	}


	public void setX129(Double x129) {
		this.x129 = x129;
	}


	public Double getX130() {
		return x130==null?0.00:x130;
	}


	public void setX130(Double x130) {
		this.x130 = x130;
	}


	public Double getX131() {
		return x131==null?0.00:x131;
	}


	public void setX131(Double x131) {
		this.x131 = x131;
	}


	public Double getX136() {
		return x136==null?0.00:x136;
	}


	public void setX136(Double x136) {
		this.x136 = x136;
	}


	public Integer getX139() {
		return x139==null?0:x139;
	}


	public void setX139(Integer x139) {
		this.x139 = x139;
	}


	public Double getX137() {
		return x137==null?0.00:x137;
	}


	public void setX137(Double x137) {
		this.x137 = x137;
	}


	public Integer getX140() {
		return x140==null?0:x140;
	}


	public void setX140(Integer x140) {
		this.x140 = x140;
	}


	public Double getX138() {
		return x138==null?0.00:x138;
	}


	public void setX138(Double x138) {
		this.x138 = x138;
	}


	public Integer getX141() {
		return x141==null?0:x141;
	}


	public void setX141(Integer x141) {
		this.x141 = x141;
	}


	public Double getX145() {
		return x145==null?0.00:x145;
	}


	public void setX145(Double x145) {
		this.x145 = x145;
	}


	public Double getX146() {
		return x146==null?0.00:x146;
	}


	public void setX146(Double x146) {
		this.x146 = x146;
	}

	public Double getX212_011() {
		return x212_011==null?0.00:x212_011;
	}

	public void setX212_011(Double x212_011) {
		this.x212_011 = x212_011;
	}

	public String getS168() {
		return s168;
	}

	public void setS168(String s168) {
		this.s168 = s168;
	}

	public String getS169() {
		return s169;
	}

	public void setS169(String s169) {
		this.s169 = s169;
	}

	public Long getT() {
		return t;
	}

	public void setT(Long t) {
		this.t = t;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifierid() {
		return modifierid;
	}

	public void setModifierid(String modifierid) {
		this.modifierid = modifierid;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}


	public Double getX107() {
		return this.x107==null?0:this.x107;
	}

	public void setX107(Double x107) {
		this.x107 = x107;
	}

	public Double getX108() {
		return this.x108==null?0:this.x108;
	}

	public void setX108(Double x108) {
		this.x108 = x108;
	}

	public Double getX109() {
		return this.x109==null?0:this.x109;
	}

	public void setX109(Double x109) {
		this.x109 = x109;
	}

	public Double getX600() {
		return this.x600==null?0:this.x600;
	}

	public void setX600(Double x600) {
		this.x600 = x600;
	}

	public Double getX601() {
		return this.x601==null?0:this.x601;
	}

	public void setX601(Double x601) {
		this.x601 = x601;
	}

	public Double getX602() {
		return this.x602==null?0:this.x602;
	}

	public void setX602(Double x602) {
		this.x602 = x602;
	}

	public Double getX603() {
		return this.x603==null?0:this.x603;
	}

	public void setX603(Double x603) {
		this.x603 = x603;
	}

	public Double getX604() {
		return this.x604==null?0:this.x604;
	}

	public void setX604(Double x604) {
		this.x604 = x604;
	}

	public Double getX605() {
		return this.x605==null?0:this.x605;
	}

	public void setX605(Double x605) {
		this.x605 = x605;
	}

	public Double getX606() {
		return this.x606==null?0:this.x606;
	}

	public void setX606(Double x606) {
		this.x606 = x606;
	}

	public Double getX607() {
		return x607==null?0.0 :x607;
	}

	public void setX607(Double x607) {
		this.x607 = x607;
	}

	public Double getX64input() {
		return x64input==null?0.0:x64input;
	}

	public void setX64input(Double x64input) {
		this.x64input = x64input;
	}

	public String getBqflagvalue() {
		return bqflagvalue;
	}

	public void setBqflagvalue(String bqflagvalue) {
		this.bqflagvalue = bqflagvalue;
	}

	public Double getX500() {
		return this.x500==null?0:this.x500;
	}

	public void setX500(Double x500) {
		this.x500 = x500;
	}

	public Double getX501() {
		return this.x501==null?0:this.x501;
	}

	public void setX501(Double x501) {
		this.x501 = x501;
	}

	public Double getX502() {
		return this.x502==null?0:this.x502;
	}

	public void setX502(Double x502) {
		this.x502 = x502;
	}

	public Double getX503() {
		return this.x503==null?0:this.x503;
	}

	public void setX503(Double x503) {
		this.x503 = x503;
	}

	public Double getX504() {
		return this.x504==null?0:this.x504;
	}

	public void setX504(Double x504) {
		this.x504 = x504;
	}

	

	public Integer getHousehold_Type() {
		return household_Type;
	}

	public void setHousehold_Type(Integer household_Type) {
		this.household_Type = household_Type;
	}

	public Double getA1000() {
		return a1000==null?0.00:a1000;
	}

	public void setA1000(Double a1000) {
		this.a1000 = a1000;
	}

	public Double getA1001() {
		return a1001==null?0.00:a1001;
	}

	public void setA1001(Double a1001) {
		this.a1001 = a1001;
	}

	public Double getA1002() {
		return a1002==null?0.00:a1002;
	}

	public void setA1002(Double a1002) {
		this.a1002 = a1002;
	}

	
	
	public Double getX800() {
		return x800==null?0.00:x800;
	}

	public void setX800(Double x800) {
		this.x800 = x800;
	}

	public Double getX801() {
		return x801==null?0.00:x801;
	}

	public void setX801(Double x801) {
		this.x801 = x801;
	}

	public Double getX802() {
		return x802==null?0.00:x802;
	}

	public void setX802(Double x802) {
		this.x802 = x802;
	}

	public Double getX803() {
		return x803==null?0.00:x803;
	}

	public void setX803(Double x803) {
		this.x803 = x803;
	}

	
	
	
	
	public Double getX19_5() {
		return x19_5==null?0.0:x19_5;
	}

	public void setX19_5(Double x19_5) {
		this.x19_5 = x19_5;
	}

	public Double getX19_6() {
		return x19_6==null?0.0:x19_6;
	}

	public void setX19_6(Double x19_6) {
		this.x19_6 = x19_6;
	}

	public Double getX19_7() {
		return x19_7==null?0.0:x19_7;
	}

	public void setX19_7(Double x19_7) {
		this.x19_7 = x19_7;
	}

	public Double getX19_8() {
		return x19_8==null?0.0:x19_8;
	}

	public void setX19_8(Double x19_8) {
		this.x19_8 = x19_8;
	}

	public Double getX19_9() {
		return x19_9==null?0.0:x19_9;
	}

	public void setX19_9(Double x19_9) {
		this.x19_9 = x19_9;
	}

	public Double getX19_10() {
		return x19_10==null?0.0:x19_10;
	}

	public void setX19_10(Double x19_10) {
		this.x19_10 = x19_10;
	}

	public Double getX42_5() {
		return x42_5==null?0.0:x42_5;
	}

	public void setX42_5(Double x42_5) {
		this.x42_5 = x42_5;
	}

	public Double getX42_6() {
		return x42_6==null?0.0:x42_6;
	}

	public void setX42_6(Double x42_6) {
		this.x42_6 = x42_6;
	}

	public Double getX42_7() {
		return x42_7==null?0.0:x42_7;
	}

	public void setX42_7(Double x42_7) {
		this.x42_7 = x42_7;
	}

	public Double getX42_8() {
		return x42_8==null?0.0:x42_8;
	}

	public void setX42_8(Double x42_8) {
		this.x42_8 = x42_8;
	}

	public Double getX42_9() {
		return x42_9==null?0.0:x42_9;
	}

	public void setX42_9(Double x42_9) {
		this.x42_9 = x42_9;
	}

	public Double getX42_10() {
		return x42_10==null?0.0:x42_10;
	}

	public void setX42_10(Double x42_10) {
		this.x42_10 = x42_10;
	}

	public String getX17_5() {
		return x17_5;
	}

	public void setX17_5(String x17_5) {
		this.x17_5 = x17_5;
	}

	public String getX17_6() {
		return x17_6;
	}

	public void setX17_6(String x17_6) {
		this.x17_6 = x17_6;
	}

	public String getX17_7() {
		return x17_7;
	}

	public void setX17_7(String x17_7) {
		this.x17_7 = x17_7;
	}

	public String getX17_8() {
		return x17_8;
	}

	public void setX17_8(String x17_8) {
		this.x17_8 = x17_8;
	}

	public String getX17_9() {
		return x17_9;
	}

	public void setX17_9(String x17_9) {
		this.x17_9 = x17_9;
	}

	public String getX17_10() {
		return x17_10;
	}

	public void setX17_10(String x17_10) {
		this.x17_10 = x17_10;
	}

	public String getX18_5() {
		return x18_5;
	}

	public void setX18_5(String x18_5) {
		this.x18_5 = x18_5;
	}

	public String getX18_6() {
		return x18_6;
	}

	public void setX18_6(String x18_6) {
		this.x18_6 = x18_6;
	}

	public String getX18_7() {
		return x18_7;
	}

	public void setX18_7(String x18_7) {
		this.x18_7 = x18_7;
	}

	public String getX18_8() {
		return x18_8;
	}

	public void setX18_8(String x18_8) {
		this.x18_8 = x18_8;
	}

	public String getX18_9() {
		return x18_9;
	}

	public void setX18_9(String x18_9) {
		this.x18_9 = x18_9;
	}

	public String getX18_10() {
		return x18_10;
	}

	public void setX18_10(String x18_10) {
		this.x18_10 = x18_10;
	}

	public String getX40_5() {
		return x40_5;
	}

	public void setX40_5(String x40_5) {
		this.x40_5 = x40_5;
	}

	public String getX40_6() {
		return x40_6;
	}

	public void setX40_6(String x40_6) {
		this.x40_6 = x40_6;
	}

	public String getX40_7() {
		return x40_7;
	}

	public void setX40_7(String x40_7) {
		this.x40_7 = x40_7;
	}

	public String getX40_8() {
		return x40_8;
	}

	public void setX40_8(String x40_8) {
		this.x40_8 = x40_8;
	}

	public String getX40_9() {
		return x40_9;
	}

	public void setX40_9(String x40_9) {
		this.x40_9 = x40_9;
	}

	public String getX40_10() {
		return x40_10;
	}

	public void setX40_10(String x40_10) {
		this.x40_10 = x40_10;
	}

	public String getX41_5() {
		return x41_5;
	}

	public void setX41_5(String x41_5) {
		this.x41_5 = x41_5;
	}

	public String getX41_6() {
		return x41_6;
	}

	public void setX41_6(String x41_6) {
		this.x41_6 = x41_6;
	}

	public String getX41_7() {
		return x41_7;
	}

	public void setX41_7(String x41_7) {
		this.x41_7 = x41_7;
	}

	public String getX41_8() {
		return x41_8;
	}

	public void setX41_8(String x41_8) {
		this.x41_8 = x41_8;
	}

	public String getX41_9() {
		return x41_9;
	}

	public void setX41_9(String x41_9) {
		this.x41_9 = x41_9;
	}

	public String getX41_10() {
		return x41_10;
	}

	public void setX41_10(String x41_10) {
		this.x41_10 = x41_10;
	}

	public String getX900() {
		return x900;
	}

	public void setX900(String x900) {
		this.x900 = x900;
	}

	public String getX902() {
		return x902;
	}

	public void setX902(String x902) {
		this.x902 = x902;
	}

	public String getX903() {
		return x903;
	}

	public void setX903(String x903) {
		this.x903 = x903;
	}

	public String getX904() {
		return x904;
	}

	public void setX904(String x904) {
		this.x904 = x904;
	}

	public Double getX905() {
		return x905==null?0.0 :x905;
	}

	public void setX905(Double x905) {
		this.x905 = x905;
	}

	public String getX906() {
		return x906;
	}

	public void setX906(String x906) {
		this.x906 = x906;
	}

	public String getX907() {
		return x907;
	}

	public void setX907(String x907) {
		this.x907 = x907;
	}

	public String getX908() {
		return x908;
	}

	public void setX908(String x908) {
		this.x908 = x908;
	}

	public String getX909() {
		return x909;
	}

	public void setX909(String x909) {
		this.x909 = x909;
	}

	public Double getX910() {
		return x910==null?0.0 :x910;
	}

	public void setX910(Double x910) {
		this.x910 = x910;
	}

	public String getX911() {
		return x911;
	}

	public void setX911(String x911) {
		this.x911 = x911;
	}

	public String getX912() {
		return x912;
	}

	public void setX912(String x912) {
		this.x912 = x912;
	}

	public String getX913() {
		return x913;
	}

	public void setX913(String x913) {
		this.x913 = x913;
	}

	public String getX914() {
		return x914;
	}

	public void setX914(String x914) {
		this.x914 = x914;
	}

	public Double getX915() {
		return x915==null?0.0 :x915;
	}

	public void setX915(Double x915) {
		this.x915 = x915;
	}

	public String getX916() {
		return x916;
	}

	public void setX916(String x916) {
		this.x916 = x916;
	}

	public String getX917() {
		return x917;
	}

	public void setX917(String x917) {
		this.x917 = x917;
	}

	public String getX918() {
		return x918;
	}

	public void setX918(String x918) {
		this.x918 = x918;
	}

	public String getX919() {
		return x919;
	}

	public void setX919(String x919) {
		this.x919 = x919;
	}

	public Double getX920() {
		return x920==null?0.0 :x920;
	}

	public void setX920(Double x920) {
		this.x920 = x920;
	}

	public String getX921() {
		return x921;
	}

	public void setX921(String x921) {
		this.x921 = x921;
	}

	public String getX922() {
		return x922;
	}

	public void setX922(String x922) {
		this.x922 = x922;
	}

	public String getX923() {
		return x923;
	}

	public void setX923(String x923) {
		this.x923 = x923;
	}

	public String getX924() {
		return x924;
	}

	public void setX924(String x924) {
		this.x924 = x924;
	}

	public Double getX925() {
		return x925==null?0.0 :x925;
	}

	public void setX925(Double x925) {
		this.x925 = x925;
	}

	public String getX926() {
		return x926;
	}

	public void setX926(String x926) {
		this.x926 = x926;
	}

	public String getX927() {
		return x927;
	}

	public void setX927(String x927) {
		this.x927 = x927;
	}

	public String getX928() {
		return x928;
	}

	public void setX928(String x928) {
		this.x928 = x928;
	}

	public String getX929() {
		return x929;
	}

	public void setX929(String x929) {
		this.x929 = x929;
	}

	public Double getX930() {
		return x930==null?0.0 :x930;
	}

	public void setX930(Double x930) {
		this.x930 = x930;
	}

	public String getX931() {
		return x931;
	}

	public void setX931(String x931) {
		this.x931 = x931;
	}

	public String getX932() {
		return x932;
	}

	public void setX932(String x932) {
		this.x932 = x932;
	}

	public String getX933() {
		return x933;
	}

	public void setX933(String x933) {
		this.x933 = x933;
	}

	public String getX934() {
		return x934;
	}

	public void setX934(String x934) {
		this.x934 = x934;
	}

	public Double getX935() {
		return x935==null?0.0 :x935;
	}

	public void setX935(Double x935) {
		this.x935 = x935;
	}

	public String getX936() {
		return x936;
	}

	public void setX936(String x936) {
		this.x936 = x936;
	}

	public String getX937() {
		return x937;
	}

	public void setX937(String x937) {
		this.x937 = x937;
	}

	public String getX938() {
		return x938;
	}

	public void setX938(String x938) {
		this.x938 = x938;
	}

	public String getX939() {
		return x939;
	}

	public void setX939(String x939) {
		this.x939 = x939;
	}

	public Double getX940() {
		return x940==null?0.0 :x940;
	}

	public void setX940(Double x940) {
		this.x940 = x940;
	}

	public String getX941() {
		return x941;
	}

	public void setX941(String x941) {
		this.x941 = x941;
	}

	public String getX942() {
		return x942;
	}

	public void setX942(String x942) {
		this.x942 = x942;
	}

	public String getX943() {
		return x943;
	}

	public void setX943(String x943) {
		this.x943 = x943;
	}

	public String getX944() {
		return x944;
	}

	public void setX944(String x944) {
		this.x944 = x944;
	}

	public Double getX945() {
		return x945==null?0.0 :x945;
	}

	public void setX945(Double x945) {
		this.x945 = x945;
	}

	public String getX946() {
		return x946;
	}

	public void setX946(String x946) {
		this.x946 = x946;
	}

	public String getX947() {
		return x947;
	}

	public void setX947(String x947) {
		this.x947 = x947;
	}

	public String getX948() {
		return x948;
	}

	public void setX948(String x948) {
		this.x948 = x948;
	}

	public String getX949() {
		return x949;
	}

	public void setX949(String x949) {
		this.x949 = x949;
	}

	public Double getX950() {
		return x950==null?0.0 :x950;
	}

	public void setX950(Double x950) {
		this.x950 = x950;
	}

	public String getX951() {
		return x951;
	}

	public void setX951(String x951) {
		this.x951 = x951;
	}

	public String getX952() {
		return x952;
	}

	public void setX952(String x952) {
		this.x952 = x952;
	}

	public String getX953() {
		return x953;
	}

	public void setX953(String x953) {
		this.x953 = x953;
	}

	public String getX954() {
		return x954;
	}

	public void setX954(String x954) {
		this.x954 = x954;
	}

	public Double getX955() {
		return x955==null?0.0 :x955;
	}

	public void setX955(Double x955) {
		this.x955 = x955;
	}

	public String getX956() {
		return x956;
	}

	public void setX956(String x956) {
		this.x956 = x956;
	}

	public String getX957() {
		return x957;
	}

	public void setX957(String x957) {
		this.x957 = x957;
	}

	public String getX958() {
		return x958;
	}

	public void setX958(String x958) {
		this.x958 = x958;
	}

	public String getX959() {
		return x959;
	}

	public void setX959(String x959) {
		this.x959 = x959;
	}

	public Double getX960() {
		return x960==null?0.0 :x960;
	}

	public void setX960(Double x960) {
		this.x960 = x960;
	}

	public String getHid11() {
		return hid11;
	}

	public void setHid11(String hid11) {
		this.hid11 = hid11;
	}

	public String getHid12() {
		return hid12;
	}

	public void setHid12(String hid12) {
		this.hid12 = hid12;
	}

	public String getHid13() {
		return hid13;
	}

	public void setHid13(String hid13) {
		this.hid13 = hid13;
	}

	public String getHid14() {
		return hid14;
	}

	public void setHid14(String hid14) {
		this.hid14 = hid14;
	}

	public String getHid15() {
		return hid15;
	}

	public void setHid15(String hid15) {
		this.hid15 = hid15;
	}

	public String getHid16() {
		return hid16;
	}

	public void setHid16(String hid16) {
		this.hid16 = hid16;
	}

	public String getHid21() {
		return hid21;
	}

	public void setHid21(String hid21) {
		this.hid21 = hid21;
	}

	public String getHid22() {
		return hid22;
	}

	public void setHid22(String hid22) {
		this.hid22 = hid22;
	}

	public String getHid23() {
		return hid23;
	}

	public void setHid23(String hid23) {
		this.hid23 = hid23;
	}

	public String getHid24() {
		return hid24;
	}

	public void setHid24(String hid24) {
		this.hid24 = hid24;
	}

	public String getHid25() {
		return hid25;
	}

	public void setHid25(String hid25) {
		this.hid25 = hid25;
	}

	public String getHid26() {
		return hid26;
	}

	public void setHid26(String hid26) {
		this.hid26 = hid26;
	}

	public Double getX608() {
		return x608==null?0.0 :x608;
	}

	public void setX608(Double x608) {
		this.x608 = x608;
	}

	public Double getX609() {
		return x609==null?0.0 :x609;
	}

	public void setX609(Double x609) {
		this.x609 = x609;
	}

	public Double getX610() {
		return x610==null?0.0 :x610;
	}

	public void setX610(Double x610) {
		this.x610 = x610;
	}

	public Double getX611() {
		return x611==null?0.0 :x611;
	}

	public void setX611(Double x611) {
		this.x611 = x611;
	}

	public Double getX612() {
		return x612==null?0.0 :x612;
	}

	public void setX612(Double x612) {
		this.x612 = x612;
	}

	public Double getX613() {
		return x613==null?0.0 :x613;
	}

	public void setX613(Double x613) {
		this.x613 = x613;
	}

	public Double getX614() {
		return x614==null?0.0 :x614;
	}

	public void setX614(Double x614) {
		this.x614 = x614;
	}

	public Double getX615() {
		return x615==null?0.0 :x615;
	}

	public void setX615(Double x615) {
		this.x615 = x615;
	}

	public Double getX616() {
		return x616==null?0.0 :x616;
	}

	public void setX616(Double x616) {
		this.x616 = x616;
	}

	public Double getX617() {
		return x617==null?0.0 :x617;
	}

	public void setX617(Double x617) {
		this.x617 = x617;
	}

	public Double getX618() {
		return x618==null?0.0 :x618;
	}

	public void setX618(Double x618) {
		this.x618 = x618;
	}

	public Double getX619() {
		return x619==null?0.0 :x619;
	}

	public void setX619(Double x619) {
		this.x619 = x619;
	}
	
	
	public Double getX1000() {
		return x1000==null?0.0 :x1000;
	}

	public void setX1000(Double x1000) {
		this.x1000 = x1000;
	}
	
	public Double getA501() {
		return a501==null?0.00:a501;
	}

	public void setA501(Double a501) {
		this.a501 = a501;
	}

	public Double getA502() {
		return a502==null?0.00:a502;
	}

	public void setA502(Double a502) {
		this.a502 = a502;
	}

	public Double getA503() {
		return a503==null?0.00:a503;
	}

	public void setA503(Double a503) {
		this.a503 = a503;
	}

	public Double getA504() {
		return a504==null?0.00:a504;
	}

	public void setA504(Double a504) {
		this.a504 = a504;
	}

	public Double getA505() {
		return a505==null?0.00:a505;
	}

	public void setA505(Double a505) {
		this.a505 = a505;
	}

	public Double getA506() {
		return a506==null?0.00:a506;
	}

	public void setA506(Double a506) {
		this.a506 = a506;
	}

	public Integer getA98() {
		return a98==null?0:a98;
	}

	public void setA98(Integer a98) {
		this.a98 = a98;
	}

	public Integer getA100() {
		return a100==null?0:a100;
	}

	public void setA100(Integer a100) {
		this.a100 = a100;
	}

	public Integer getA102() {
		return a102==null?0:a102;
	}

	public void setA102(Integer a102) {
		this.a102 = a102;
	}

	public Integer getA104() {
		return a104==null?0:a104;
	}

	public void setA104(Integer a104) {
		this.a104 = a104;
	}

	public Integer getA106() {
		return a106==null?0:a106;
	}

	public void setA106(Integer a106) {
		this.a106 = a106;
	}

	public Integer getA108() {
		return a108==null?0:a108;
	}

	public void setA108(Integer a108) {
		this.a108 = a108;
	}
	
	public Double getE111() {
		return e111==null?0.0 :e111;
	}

	public void setE111(Double e111) {
		this.e111 = e111;
	}
	
}