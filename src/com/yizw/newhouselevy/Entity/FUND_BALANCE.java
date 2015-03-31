package com.yizw.newhouselevy.Entity;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;


/*资金余额*/
public class FUND_BALANCE {

	@DatabaseField
	private String id;//ID	 
	
	
	
	@DatabaseField
	private String allmoney;//当前项目预算金额
	
	@DatabaseField
	private String inmoney;//当前项目拨入总金额
	
	@DatabaseField
	private String remain;//当前项目余额
	
	
	
	@DatabaseField
	private String creator;//创建者
	@DatabaseField
	private Date createddate;//创建日期	
	@DatabaseField
	private String creatorid;//创建者id
	@DatabaseField
	private String modifier;//修改人
	@DatabaseField
	private String modifierid;//修改人id
	@DatabaseField
	private Date modifydate;//修改时间 
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 
	public String getRemain() {
		return (String) (this.remain==null?"0.00":remain);
	}
	public void setRemain(String remain) {
		this.remain = remain;
	}
	 
	public String getAllmoney() {
		return (String) (this.allmoney==null?"0.00":allmoney);
	}
	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}
	
	
	public String getInmoney() {
		return (String) (this.inmoney==null?"0.00":inmoney);
	}
	public void setInmoney(String inmoney) {
		this.inmoney = inmoney;
	}
	
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
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
	
	
}
