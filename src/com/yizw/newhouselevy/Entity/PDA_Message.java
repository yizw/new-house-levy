package com.yizw.newhouselevy.Entity;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="PDA_Message")
public class PDA_Message {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField(canBeNull=false)
	private String uuid;
	
	@DatabaseField
	private String content;
	
	@DatabaseField(canBeNull=false)
	private int isupload;
	
	@DatabaseField
	private Date credate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsupload() {
		return isupload;
	}
	public void setIsupload(int isupload) {
		this.isupload = isupload;
	}
	public Date getCredate() {
		return credate;
	}
	public void setCredate(Date credate) {
		this.credate = credate;
	}
}
