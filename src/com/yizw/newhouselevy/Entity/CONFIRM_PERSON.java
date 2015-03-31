package com.yizw.newhouselevy.Entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;

@SuppressWarnings("serial")
public class CONFIRM_PERSON  implements Serializable  { 
	
	private String id;
	 
	private String name;
	@DatabaseField
	private String relationship;
	@DatabaseField
	private String cardno;
	@DatabaseField
	private String workunit;
	@DatabaseField
	private String remark;
	
	private String confirmid;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getWorkunit() {
		return workunit;
	}
	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getConfirmid() {
		return confirmid;
	}
	public void setConfirmid(String confirmid) {
		this.confirmid = confirmid;
	}
	
}
