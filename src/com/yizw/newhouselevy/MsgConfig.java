package com.yizw.newhouselevy;
import android.content.Context;

import com.yizw.houseLevy.Business.LoginBus;
import com.yizw.houseLevy.Business.SysConfigBus;
import com.cogent.core.msg.IUploadConfig;
import com.cogent.core.util.MyException;

public class MsgConfig implements IUploadConfig {

	@Override
	public int getTimeOut() {
		// TODO Auto-generated method stub
		return 6000;
	}

	@Override
	public String getUSN(Context context) throws MyException {
		// TODO Auto-generated method stub
		return LoginBus.getUSN(context);
	}

	@Override
	public String getUploadUrl(Context context) {
		// TODO Auto-generated method stub
		return SysConfigBus.getNetAddress(context);
	}

}
