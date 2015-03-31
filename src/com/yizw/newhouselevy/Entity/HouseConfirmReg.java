package com.yizw.newhouselevy.Entity;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

/**
 * 确认表产权证登记
 * @author yizw
 *
 */
//@Table(name="pro_house_confirm_reg")
@SuppressWarnings("serial")
public class HouseConfirmReg implements Serializable {
	
//	private static final long serialVersionUID = 1L;

	private String confirmid;
	
	private String c4;
	
	private String c32;
	
	private String c21;
	
	private String x6;
	
	private String c3;
	
	private String c15;
	
	private String c34;
	
	private String c22;
	
	private String c23;
	
	private String c24;
	
	private String c25;
	
	
	@DatabaseField
	private String id;
	@DatabaseField
	private Date createddate;//创建时间	
	@DatabaseField
	private String creator;//创建人
	@DatabaseField
	private String creatorid;//创建人
	@DatabaseField
	private String modifier;//修改人
	@DatabaseField
	private String modifierid;//修改人id
	@DatabaseField
	private Date modifydate;//修改时间	
	

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC32() {
		return c32;
	}

	public void setC32(String c32) {
		this.c32 = c32;
	}

	public String getC21() {
		return c21;
	}

	public void setC21(String c21) {
		this.c21 = c21;
	}

	public String getX6() {
		return x6;
	}

	public void setX6(String x6) {
		this.x6 = x6;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC15() {
		return c15;
	}

	public void setC15(String c15) {
		this.c15 = c15;
	}

	public String getC34() {
		return c34;
	}

	public void setC34(String c34) {
		this.c34 = c34;
	}

	public String getC22() {
		return c22;
	}

	public void setC22(String c22) {
		this.c22 = c22;
	}

	public String getC23() {
		return c23;
	}

	public void setC23(String c23) {
		this.c23 = c23;
	}

	public String getC24() {
		return c24;
	}

	public void setC24(String c24) {
		this.c24 = c24;
	}

	public String getC25() {
		return c25;
	}

	public void setC25(String c25) {
		this.c25 = c25;
	}

	public String getConfirmid() {
		return confirmid;
	}

	public void setConfirmid(String confirmid) {
		this.confirmid = confirmid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
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

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
	
	
}
