package com.yizw.newhouselevy.Entity;


/**房间列表  查询 用到的查询条件*/
public class HouseRoomListMainParm {

	private String bno;//楼栋号
	private String unit;//单元号
	private String floor;//楼层号
	private String room;//房间号
	
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
		
}
