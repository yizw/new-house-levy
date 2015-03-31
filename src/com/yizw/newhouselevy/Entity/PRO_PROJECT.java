package com.yizw.newhouselevy.Entity;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/** 项目信息表*/
@DatabaseTable
public class PRO_PROJECT {
	@DatabaseField
	private String id;//项目ID	
	@DatabaseField
	private String description;//描叙
	@DatabaseField
	private Date endtime;//结束时间
	@DatabaseField
	private Date seqdate;//时间
	@DatabaseField
	private Date starttime;//开始时间
	@DatabaseField
	private String housenumber;//摸底户数
	@DatabaseField
	private String pname;//项目标题
	@DatabaseField
	private String pnum;//
	@DatabaseField
	private String project_type;//项目类别
	@DatabaseField
	private String signnumber;//已签户数
	@DatabaseField
	private String status;//状态
	@DatabaseField
	private String centerleader;//中心分管领导
	@DatabaseField
	private String specleader;//开发区分管领导
	@DatabaseField
	private String projectmanage;//项目管理人
	@DatabaseField
	private String projectdirector;//项目办公室主任
	
	@DatabaseField
	private String creator;//创建者
	@DatabaseField
	private Date createddate;//创建日期	
	@DatabaseField
	private Date creatorid;//创建人id	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Date getSeqdate() {
		return seqdate;
	}
	public void setSeqdate(Date seqdate) {
		this.seqdate = seqdate;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getHousenumber() {
		return housenumber;
	}
	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getProject_type() {
		return project_type;
	}
	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}
	public String getSignnumber() {
		return signnumber;
	}
	public void setSignnumber(String signnumber) {
		this.signnumber = signnumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCenterleader() {
		return centerleader;
	}
	public void setCenterleader(String centerleader) {
		this.centerleader = centerleader;
	}
	public String getSpecleader() {
		return specleader;
	}
	public void setSpecleader(String specleader) {
		this.specleader = specleader;
	}
	public String getProjectmanage() {
		return projectmanage;
	}
	public void setProjectmanage(String projectmanage) {
		this.projectmanage = projectmanage;
	}
	public String getProjectdirector() {
		return projectdirector;
	}
	public void setProjectdirector(String projectdirector) {
		this.projectdirector = projectdirector;
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
	public Date getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(Date creatorid) {
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