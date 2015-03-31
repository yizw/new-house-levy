package com.yizw.newhouselevy.Entity;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;


/**
 * 项目资金
 */

// @Table(name="pro_fund")
@SuppressWarnings("serial")
public class Fund implements Serializable {
	
//	private static final long serialVersionUID = 1L;

	private String name;
	
	private Double money;

	private Integer type;//收支类型
	
	private String num;//支票号（存折号）
	
	private String operdate;//操作时间
	
//	@Column(columnDefinition="number(15,2)")
//	private Double remain;//余额
	
	private String remark;

	private String pfid;//项目资金情况ID
	
	private Long seqdate;
	
	
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
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOperdate() {
		return operdate;
	}

	public void setOperdate(String operdate) {
		this.operdate = operdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPfid() {
		return pfid;
	}

	public void setPfid(String pfid) {
		this.pfid = pfid;
	}

	/*public Double getRemain() {
		return remain;
	}

	public void setRemain(Double remain) {
		this.remain = remain;
	}*/

	public Long getSeqdate() {
		return seqdate;
	}

	public void setSeqdate(Long seqdate) {
		this.seqdate = seqdate;
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
