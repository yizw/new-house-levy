package com.yizw.newhouselevy.Entity;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/** 用户记录信息表*/
@DatabaseTable(tableName="PDA_LOGIN")
public class PDA_Login {
	
	private String USN;//登录成功后的usn，PDA上额外附加
	
	@DatabaseField(id=true)
	private String USERNAME;//用户名	           
	
	@DatabaseField
	private String REALNAME;//真实姓名
	@DatabaseField
	private String LINKTEL;//联系电话
	@DatabaseField
	private String PASSWORD;//用户密码
	
	@DatabaseField
	private String ADDRESS;//地址
	
	@DatabaseField
	private String EMAIL;//电子邮箱
	@DatabaseField
	private String MOBILE;//手机
	@DatabaseField
	private String HEADSHIP;//职务
	
	@DatabaseField
	private String DISTRICTCODE;//行政区划编码	
	@DatabaseField
	private String DISTRICTLEVEL;//行政区划级别
	@DatabaseField
	private Date SYNDATE;//上次登录时间
	@DatabaseField
	private String VERSION;//当前使用PDA版本
	@DatabaseField
	private String TIMES;//累积登录次数
	@DatabaseField
	private String SYSID;//系统ID
	@DatabaseField
	private String ORGID;//默认组织机构ID
	@DatabaseField
	private String PERMISSION;//PDA用户权限
	@DatabaseField
	private String ISLEADER;//是否是领导
	@DatabaseField
	private String TITLENAME;//用户职务
	@DatabaseField
	private String USERTYPE;//用户类型
	@DatabaseField
	private String ORGANNAME;//用户机构名称
	
	
	private int mainprojectnum = 0;//该用户参与的征收项目数量	
	
	
	public String getUSN() {
		return USN;
	}
	public void setUSN(String uSN) {
		USN = uSN;
	}
	
 
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getREALNAME() {
		return REALNAME;
	}
	public void setREALNAME(String rEALNAME) {
		REALNAME = rEALNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}
	public String getHEADSHIP() {
		return HEADSHIP;
	}
	public void setHEADSHIP(String hEADSHIP) {
		HEADSHIP = hEADSHIP;
	}
	public String getLINKTEL() {
		return LINKTEL;
	}
	public void setLINKTEL(String lINKTEL) {
		LINKTEL = lINKTEL;
	}
	public String getDISTRICTCODE() {
		return DISTRICTCODE;
	}
	public void setDISTRICTCODE(String dISTRICTCODE) {
		DISTRICTCODE = dISTRICTCODE;
	}
	public String getDISTRICTLEVEL() {
		return DISTRICTLEVEL;
	}
	public void setDISTRICTLEVEL(String dISTRICTLEVEL) {
		DISTRICTLEVEL = dISTRICTLEVEL;
	}
	public Date getSYNDATE() {
		return SYNDATE;
	}
	public void setSYNDATE(Date sYNDATE) {
		SYNDATE = sYNDATE;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public String getTIMES() {
		return TIMES;
	}
	public void setTIMES(String tIMES) {
		TIMES = tIMES;
	}
	public String getSYSID() {
		return SYSID;
	}
	public void setSYSID(String sYSID) {
		SYSID = sYSID;
	}
	public String getORGID() {
		return ORGID;
	}
	public void setORGID(String oRGID) {
		ORGID = oRGID;
	}
	public String getPERMISSION() {
		return PERMISSION;
	}
	public void setPERMISSION(String pERMISSION) {
		PERMISSION = pERMISSION;
	}
	public String getISLEADER() {
		return ISLEADER;
	}
	public void setISLEADER(String iSLEADER) {
		ISLEADER = iSLEADER;
	}
	public String getTITLENAME() {
		return TITLENAME;
	}
	public void setTITLENAME(String tITLENAME) {
		TITLENAME = tITLENAME;
	}
	public String getUSERTYPE() {
		return USERTYPE;
	}
	public void setUSERTYPE(String uSERTYPE) {
		USERTYPE = uSERTYPE;
	}
	public String getORGANNAME() {
		return ORGANNAME;
	}
	public void setORGANNAME(String oRGANNAME) {
		ORGANNAME = oRGANNAME;
	}

	public int getMainprojectnum() {
		return mainprojectnum;
	}
	public void setMainprojectnum(int mainprojectnum) {
		this.mainprojectnum = mainprojectnum;
	}
	
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	
	
}
