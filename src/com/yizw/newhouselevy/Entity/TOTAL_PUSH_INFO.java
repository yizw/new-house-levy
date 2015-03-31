package com.yizw.newhouselevy.Entity;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/** 总推进表详细信息表*/
@DatabaseTable
public class TOTAL_PUSH_INFO {	
	@DatabaseField
	private String id;//ID	
	@DatabaseField
	private String pid;//项目ID
		
	@DatabaseField
	private String creator;//创建者
	@DatabaseField
	private Date createddate;//创建日期	
	@DatabaseField
	private String creatorid;//创建人id	
	@DatabaseField
	private String modifier;//修改人
	@DatabaseField
	private String modifierid;//修改人id
	@DatabaseField
	private Date modifydate;//修改时间 
	
	@DatabaseField
	private String title;//标题
	@DatabaseField
	private Date finishdate;//完成时间
	@DatabaseField
	private String content;//内容
	@DatabaseField
	private String status;//状态
	
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getFinishdate() {
		return finishdate;
	}
	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status == null?"0":status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
