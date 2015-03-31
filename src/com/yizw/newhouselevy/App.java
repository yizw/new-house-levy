package com.yizw.newhouselevy;
import com.umeng.analytics.MobclickAgent;

import android.app.Application;

public class App extends Application{

	
	@Override
	public void onCreate() {
		super.onCreate();
		
		MobclickAgent.setDebugMode(false);
        MobclickAgent.setSessionContinueMillis(300000);
        MobclickAgent.onError(this);
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();	
	}
}
