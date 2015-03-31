package com.yizw.newhouselevy.Entity;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class DATA_BASE {

	@DatabaseField
	private String id;//ID	
	@DatabaseField
	private String content;//内容	
	@DatabaseField
	private String resource_type;//资源类别
	@DatabaseField
	private String seq;//排序
	@DatabaseField
	private String status;//状态
	@DatabaseField
	private String title;//标题
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
