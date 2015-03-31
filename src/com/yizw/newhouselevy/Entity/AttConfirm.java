package com.yizw.newhouselevy.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;


/**
 * 分户附件内容
 * @author yizw
 */
//@Table(name="pro_att_confirm")
@SuppressWarnings("serial")
public class AttConfirm implements Serializable {
	
//	private static final long serialVersionUID = 1L;
	
	@DatabaseField
	private String id;//ID	 
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
	
	
	
	
	
	private String name;// 附件内容总的名称
	
	private String type; // 附件内容中的类别

	private String temptype;// 模板类别

	private String unit;// 单位

	private Double num;// 数量

//	@Column(columnDefinition = "number(12,2)")
//	private Double price;// 单价

	private String hid;// 分户ID
	
	private String pid;// 项目ID
	
	private String remark;
	
	private Long seqdate;
	
	private List<AttConfirmItem> attConfirmItems = new ArrayList<AttConfirmItem>();

	public AttConfirm() {
		super();
	}


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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getTemptype() {
		return temptype;
	}


	public void setTemptype(String temptype) {
		this.temptype = temptype;
	}


	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Double getNum() {
		return num==null?0.00:num;
	}
	
	public void setNum(Double num) {
		this.num = num;
	}
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getSeqdate() {
		return seqdate;
	}
	public void setSeqdate(Long seqdate) {
		this.seqdate = seqdate;
	}
	public List<AttConfirmItem> getAttConfirmItems() {
		return attConfirmItems;
	}
	public void setAttConfirmItems(List<AttConfirmItem> attConfirmItems) {
		this.attConfirmItems = attConfirmItems;
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
