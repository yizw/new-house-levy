package com.yizw.newhouselevy.Entity;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/** 分户信息表*/
@DatabaseTable
public class HOU_HOUSEHOLD {
	@DatabaseField
	private String id;//ID	
	@DatabaseField
	private String pid;//项目ID	
	@DatabaseField
	private String c_id;//分户房屋调查确认表id
	@DatabaseField
	private String c_status;//分户房屋调查确认表状态值c_status
	@DatabaseField
	private String hliststatus;//分户算单id的值（为1，不能再加算单，为2，可以添加算单）
	
	
//	@DatabaseField
//	private int type;//分户类型	
	@DatabaseField
	private String houseowner;//分户姓名	
	@DatabaseField
	private String cartno;//身份证号	
	@DatabaseField
	private String household_type;//分户类型	
	@DatabaseField
	private String linktel;//联系方式	
	@DatabaseField
	private String address;//住址	
	@DatabaseField
	private String remark;//备注
	@DatabaseField
	private String project_type;//分户所属的项目类型
	@DatabaseField
	private String contracttype;//分户所拥有的补偿计算单类型（0，产权；1，货币）
	
	@DatabaseField
	private String status;//分户状态 0正常，99删除，999占用编号
	
	@DatabaseField
	private String creator;//创建者
	@DatabaseField
	private Date creatdate;//创建日期	
	@DatabaseField
	private String modifier;//修改人
	@DatabaseField
	private Date modifydate;//修改时间 
	@DatabaseField
	private String username;//PDA用户
	
	private String cstatus;//确认表的状态
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}	
	public String getHouseowner() {
		return houseowner;
	}
	public void setHouseowner(String houseowner) {
		this.houseowner = houseowner;
	}
	public String getHousehold_type() {
		return household_type;
	}
	public void setHousehold_type(String household_type) {
		this.household_type = household_type;
	}	
	public String getCartno() {
		return cartno;
	}
	public void setCartno(String cartno) {
		this.cartno = cartno;
	}
	public String getLinktel() {
		return linktel;
	}
	public void setLinktel(String linktel) {
		this.linktel = linktel;
	}	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatdate() {
		return creatdate;
	}
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProject_type() {
		return project_type;
	}
	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}
	public String getContracttype() {
		return contracttype;
	}
	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}
	public String getCstatus() {
		return cstatus;
	}
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_status() {
		return c_status;
	}
	public void setC_status(String c_status) {
		this.c_status = c_status;
	}
	public String getHliststatus() {
		return hliststatus;
	}
	public void setHliststatus(String hliststatus) {
		this.hliststatus = hliststatus;
	}	
	
	
	
}
