package com.yizw.newhouselevy.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;


/*问题反馈-问题*/
@SuppressWarnings("serial")
public class Question implements Serializable {

//	private static final long serialVersionUID = 1L;

	private String pid;
	
	private String pname;
	
	private String content;
	
	private String personName;
	
	private String personId;
	
	private Integer status;
	
	private String household_id;
	
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
	
	
	
    private List<Reply> replies = new ArrayList<Reply>();

	private HOU_HOUSEHOLD household;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public HOU_HOUSEHOLD getHousehold() {
		return household;
	}

	public void setHousehold(HOU_HOUSEHOLD household) {
		this.household = household;
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

	public String getHousehold_id() {
		return household_id;
	}

	public void setHousehold_id(String household_id) {
		this.household_id = household_id;
	}
	
	
	
}
