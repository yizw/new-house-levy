package com.yizw.newhouselevy.Entity;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;


/**
 * 项目资金--项目创建是添加该数据
 */

//@Table(name="pro_project_fund")
@SuppressWarnings("serial")
public class ProjectFund implements Serializable {

//	private static final long serialVersionUID = 1L;
	

	private PRO_PROJECT project;
	
//	@Column(columnDefinition="number(12,2)")
//	private Double remain;//余额
	
/*	@Column(columnDefinition="nvarchar2(120)")
	private String minister;//项目部长
*/	
	private Double allmoney;
	
	
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
	

	public PRO_PROJECT getProject() {
		return project;
	}

	public void setProject(PRO_PROJECT project) {
		this.project = project;
	}

	/*public Double getRemain() {
		return remain==null?0.00:remain;
	}

	public void setRemain(Double remain) {
		this.remain = remain;
	}
*/
	/*public String getMinister() {
		return minister;
	}

	public void setMinister(String minister) {
		this.minister = minister;
	}*/

	public Double getAllmoney() {
		return allmoney==null?0.00:allmoney;
	}

	public void setAllmoney(Double allmoney) {
		this.allmoney = allmoney;
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
	
	
}
