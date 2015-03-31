package com.yizw.newhouselevy.Entity;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class HOUSEHOLD_PUSH_TABLE {

	@DatabaseField
	private String id;//ID	
	@DatabaseField
	private String pid;//项目ID	
	@DatabaseField
	private String householdid;//分户ID		
	@DatabaseField
	private Date  finishdate;//结束日期	
	@DatabaseField
	private String seq;//位置
	@DatabaseField
	private String content;//内容	
	@DatabaseField
	private String status;//状态
	@DatabaseField
	private String title;//标题
	
	
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
	public String getHouseholdid() {
		return householdid;
	}
	public void setHouseholdid(String householdid) {
		this.householdid = householdid;
	}
	public Date getFinishdate() {
		return finishdate;
	}
	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
}
