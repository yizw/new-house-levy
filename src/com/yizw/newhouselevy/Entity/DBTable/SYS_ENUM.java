package com.yizw.newhouselevy.Entity.DBTable;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

/**系统枚举信息表*/
public class SYS_ENUM {
	@DatabaseField(id=true)
	private String ID;//ID
	@DatabaseField
	private String ENUMTYPECODE;//枚举类型代码
	@DatabaseField
	private String ENUMITEMNAME;//枚举名称
	@DatabaseField
	private String VALUE;//枚举值
	@DatabaseField
	private int SEQ;//排序顺序
	@DatabaseField
	private int ISDEFAULT;//是否默认值
	@DatabaseField
	private String CREATOR;//创建人
	@DatabaseField
	private Date CREDATE;//创建时间
	@DatabaseField
	private String MODIFIER;//修改人
	@DatabaseField
	private Date MODIFYDATE;//修改时间
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getENUMTYPECODE() {
		return ENUMTYPECODE;
	}
	public void setENUMTYPECODE(String eNUMTYPECODE) {
		ENUMTYPECODE = eNUMTYPECODE;
	}
	public String getENUMITEMNAME() {
		return ENUMITEMNAME;
	}
	public void setENUMITEMNAME(String eNUMITEMNAME) {
		ENUMITEMNAME = eNUMITEMNAME;
	}
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}
	
	
	public int getSEQ() {
		return SEQ;
	}
	public void setSEQ(int sEQ) {
		SEQ = sEQ;
	}
	public int getISDEFAULT() {
		return ISDEFAULT;
	}
	public void setISDEFAULT(int iSDEFAULT) {
		ISDEFAULT = iSDEFAULT;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public Date getCREDATE() {
		return CREDATE;
	}
	public void setCREDATE(Date cREDATE) {
		CREDATE = cREDATE;
	}
	public String getMODIFIER() {
		return MODIFIER;
	}
	public void setMODIFIER(String mODIFIER) {
		MODIFIER = mODIFIER;
	}
	public Date getMODIFYDATE() {
		return MODIFYDATE;
	}
	public void setMODIFYDATE(Date mODIFYDATE) {
		MODIFYDATE = mODIFYDATE;
	}
}
