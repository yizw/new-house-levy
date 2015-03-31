package com.yizw.newhouselevy.Business;

import com.example.newhouselevy.R;
import com.cogent.core.util.SPUtil;

import android.content.Context;

public class SysConfigBus {
	
	private static SPUtil sputil = null;
	/**获取对 默认配置文件操控 的工具类对象*/
	public static SPUtil getSpUtil(Context context){
		if(sputil==null)
			sputil = new SPUtil(context);
		return sputil;
	}
	
	/** 通信服务地址*/
	public static String getNetAddress(Context context) {
		return getSpUtil(context).getValue("NetAddress",context.getString(R.string.NetAddress));
	}	
	public static void setNetAddress(Context context, String value) {
		getSpUtil(context).setValue("NetAddress", value);
	}
	
	
	/** 时限控制服务地址*/
	public static String getReportAddress(Context context) {
		return getSpUtil(context).getValue("ReportAddress",context.getString(R.string.ReportAddress));
	}	
	public static void setReportAddress(Context context, String value) {
		getSpUtil(context).setValue("ReportAddress", value);
	}
	
	
	
	/** 自动升级*/
	public static boolean getAutoUpdate(Context context) {
		return getSpUtil(context).getValue("AutoUpdate",Boolean.valueOf(context.getString(R.string.AutoUpdate)));				
	}	
	public static void setAutoUpdate(Context context, boolean value) {
		getSpUtil(context).setValue("AutoUpdate", value);
	}
	
	/** 自动上传*/
	public static boolean getAutoUpload(Context context) {
		return getSpUtil(context).getValue("AutoUpload",Boolean.valueOf(context.getString(R.string.AutoUpload)));
	}	
	public static void setAutoUpload(Context context, boolean value) {
		getSpUtil(context).setValue("AutoUpload", value);
	}
	
	/** 自动上传周期(秒)*/
	public static int getUploadCycle(Context context) {
		String temp = getSpUtil(context).getValue("UploadCycle",context.getString(R.string.UploadCycle));
		return Integer.valueOf(temp);
	}	
	public static void setUploadCycle(Context context, int value) {
		getSpUtil(context).setValue("UploadCycle", value+"");
	}
	
	/** 自动同步*/
	public static boolean getAutoSync(Context context) {
		return getSpUtil(context).getValue("AutoSync",Boolean.valueOf(context.getString(R.string.AutoSync)));
	}
	public static void setAutoSync(Context context, boolean value) {
		getSpUtil(context).setValue("AutoSync", value);
	}

	
	/** 最后登录成功的用户名*/
	public static String getUserName(Context context){
		return getSpUtil(context).getValue("UserName","");
	}
	public static void setUserName(Context context, String value) {
		getSpUtil(context).setValue("UserName", value);
	}
	
	/** 同步时间*/
	public static String getSyncData(Context context){
		return getSpUtil(context).getValue("SyncData",context.getString(R.string.SyncData));
	}
	public static void setSyncData(Context context, String value) {
		getSpUtil(context).setValue("SyncData", value);
	}
	
	/** 读卡器蓝牙地址*/
	public static String getReaderAddress(Context context){
		return getSpUtil(context).getValue("ReaderAddress","");
	}
	public static void setReaderAddress(Context context, String value) {
		getSpUtil(context).setValue("ReaderAddress", value);
	}
	
//	/** GPS是否开启*/
//	public static boolean getGpsOpen(Context context) {
//		return getSpUtil(context).getValue("GpsOpen",Boolean.valueOf(context.getString(R.string.GpsOpen)));
//	}	
//	
//	/** GPS上传周期(秒)*/
//	public static int getGpsCycle(Context context) {
//		String temp = getSpUtil(context).getValue("GpsCycle",context.getString(R.string.GpsCycle));
//		return Integer.valueOf(temp);
//	}	
	
	/** 照片最大高度(像素)*/
	public static int getPhotoMaxHeight(Context context) {
		String temp = getSpUtil(context).getValue("PhotoMaxHeight",context.getString(R.string.PhotoMaxHeight));
		return Integer.valueOf(temp);
	}	
	/** 照片最大宽度(像素)*/
	public static int getPhotoMaxWidth(Context context) {
		String temp = getSpUtil(context).getValue("PhotoMaxWidth",context.getString(R.string.PhotoMaxWidth));
		return Integer.valueOf(temp);
	}	
}
