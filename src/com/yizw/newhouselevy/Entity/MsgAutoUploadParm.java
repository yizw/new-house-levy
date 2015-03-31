package com.yizw.newhouselevy.Entity;

import java.util.ArrayList;
import java.util.List;

/** 消息自动上传用到的参数封装*/
public class MsgAutoUploadParm {
	
	private int waitSendMessageCount;//待上传消息数量
	private List<Integer> ids = new ArrayList<Integer>();//待上传消息id
	private boolean uploadStatus;//上传状态 true为开启自动上传 false为关闭自动上传
	private int uploadCycle = 30000;//自动上传周期 单位毫秒，默认值设为30秒
	
	public int getWaitSendMessageCount() {
		return waitSendMessageCount;
	}
	public void setWaitSendMessageCount(int waitSendMessageCount) {
		this.waitSendMessageCount = waitSendMessageCount;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	/** true为开启自动上传  false为关闭自动上传*/
	public boolean isUploadStatus() {
		return uploadStatus;
	}
	/** true为开启自动上传  false为关闭自动上传*/
	public void setUploadStatus(boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public int getUploadCycle() {
		return uploadCycle;
	}
	public void setUploadCycle(int uploadCycle) {
		this.uploadCycle = uploadCycle;
	}		
}
