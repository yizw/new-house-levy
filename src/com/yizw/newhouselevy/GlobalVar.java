package com.yizw.newhouselevy;

import com.yizw.newhouselevy.Business.LoginBus;
import com.yizw.newhouselevy.Business.Sys_EnumBus;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.FileHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;
import com.cogent.core.util.SystemInfo;

/** ȫ�ֱ�*/
public class GlobalVar {
	public class SP_KEY{
		/**�¼�*/
		public static final String incident = "incident";
	}
	
	/** ��־����tag��־*/	 
	public static final String TAG="PDA";
	
	/** ö�ٵ�Ĭ��ֵ*/
	public static final String enum_defalut= "";	
	
	/** 0:��4���水ť��ԭ4������  1������ʧ�ܺ���ʾ������*/
	public static final String[] txt_btn = new String[]{"","���»�ȡ"};
	
	/** 0:���ڼ���  1���������  2������ʧ��*/
	public static String[] footerInfo = new String[]{"���ڻ�ȡ�У������ĵȴ�һ�°�~","�Ѿ����������~","��ȡʧ���ˣ���������~"};

	private static String saveFolder = null;
	/** ��ȡ��4������־����Ϣ��Ĭ���ļ���·��<br/>ע���ļ���·������Ѽ��ϡ�/�����磺/sdcard/cogent/*/
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
	
	/** ����*/
	public static void clearCache(){
		//��̬���󱣴�Ļ��棬��������Ӧ�õĹرն���٣��´���ʱ���ܻ�����һ�ε�
		LoginBus.clearUSN();
		Sys_EnumBus.ClearCache();
		saveFolder = null;
	}
}
