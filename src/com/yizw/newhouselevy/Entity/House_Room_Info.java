package com.yizw.newhouselevy.Entity;

import java.util.Date;
import com.j256.ormlite.field.DatabaseField;


/*房间信息表*/
public class House_Room_Info {
	@DatabaseField
	private String id;//ID	
	
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
	private Double area;//面积	
	
	@DatabaseField
	private String bno;//楼栋
	@DatabaseField
	private String cartno;//身份证号
	@DatabaseField
	private String dealperson;//被征收人
	@DatabaseField
	private String dealprojectname;//安置项目
	@DatabaseField
	private String deliverydate;//预计交付时间
	@DatabaseField
	private String floor;//楼层
	@DatabaseField
	private String hid;//分户id
	@DatabaseField
	private String houseimage;//房间内部造型图片
	@DatabaseField
	private String housetype;//户型（三室两厅两卫）
	@DatabaseField
	private String inarea;//套内面积
	@DatabaseField
	private String remark;//备注
	@DatabaseField
	private String room;//房间
	@DatabaseField
	private String saledate;//预交房时间
	@DatabaseField
	private String saleenddate;//预售许可证到期时间
	@DatabaseField
	private String selectdate;//选房时间
	@DatabaseField
	private String signdate;//签订时间
	@DatabaseField
	private String status;//房间状态
	@DatabaseField
	private String swapdate;//交换时间
	@DatabaseField
	private String unit;//单元
	@DatabaseField
	private String usage;//用途
	@DatabaseField
	private String name;//房开商名
	@DatabaseField
	private String url;//附件链接
	
	
//	@DatabaseField
//	private Date ;//时间
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	
	
	public Double getArea() {
		return this.area==null?0.00:area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getCartno() {
		return cartno;
	}
	public void setCartno(String cartno) {
		this.cartno = cartno;
	}
	public String getDealperson() {
		return dealperson;
	}
	public void setDealperson(String dealperson) {
		this.dealperson = dealperson;
	}
	public String getDealprojectname() {
		return dealprojectname;
	}
	public void setDealprojectname(String dealprojectname) {
		this.dealprojectname = dealprojectname;
	}
	public String getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	public String getHouseimage() {
		return houseimage;
	}
	public void setHouseimage(String houseimage) {
		this.houseimage = houseimage;
	}
	public String getHousetype() {
		return housetype;
	}
	public void setHousetype(String housetype) {
		this.housetype = housetype;
	}
	public String getInarea() {
		return inarea;
	}
	public void setInarea(String inarea) {
		this.inarea = inarea;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	public String getSaleenddate() {
		return saleenddate;
	}
	public void setSaleenddate(String saleenddate) {
		this.saleenddate = saleenddate;
	}
	public String getSelectdate() {
		return selectdate;
	}
	public void setSelectdate(String selectdate) {
		this.selectdate = selectdate;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSwapdate() {
		return swapdate;
	}
	public void setSwapdate(String swapdate) {
		this.swapdate = swapdate;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
