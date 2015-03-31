package com.yizw.newhouselevy;

import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.Sys_EnumBus;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.FileHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.cogent.core.util.SystemInfo;

/** 全局变量*/
public class GlobalVar {
	public class SP_KEY{
		/**事件*/
		public static final String incident = "incident";
	}
	
	/** 日志输出的tag标志*/	 
	public static final String TAG="PDA";
	
	/** 枚举的默认值*/
	public static final String enum_defalut= "";	
	
	/** 0:用来缓存按钮上原来的文字  1：操作失败后显示的文字*/
	public static final String[] txt_btn = new String[]{"","重新获取"};
	
	/** 0:正在加载  1：加载完成  2：加载失败*/
	public static String[] footerInfo = new String[]{"正在获取中，请耐心等待一下吧~","已经加载完成了~","获取失败了，请点击重试~"};

	private static String saveFolder = null;
	/** 获取用来保存日志等信息的默认文件夹路径<br/>注：文件夹路径最后已加上“/”，如：/sdcard/cogent/*/
	public static String getSaveFolder(){
		if(!StringHelper.isNullOrEmpty(saveFolder))
			return saveFolder;
		
		String sdPath = SystemInfo.getSDPath();
		if (StringHelper.isNullOrEmpty(sdPath)){
			return null;
		}else{
			String folder = sdPath + "/" + "cogent";
			try {
				FileHelper.createDir(folder);
			} catch (MyException e) {
				ExceptionHelper.Operate(e, false, null);
				return null;
			}
			return folder + "/";
		}
	}
	
	/** 清除缓存*/
	public static void clearCache(){
		//静态对象保存的缓存，不会随着应用的关闭而销毁，下次启动时可能还是上一次的
		LoginBus.clearUSN();
		Sys_EnumBus.ClearCache();
		saveFolder = null;
	}
}
