package com.yizw.newhouselevy.UI;

import android.os.Bundle;
import android.widget.TextView;

import com.cogent.core.util.SystemInfo;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.yizw.newhouselevy.AsyncTask.LoadingAsyncTask;
import com.yizw.newhouselevy.DAO.DatabaseHelper;

/** 启动加载页面*/
public class LoadingA extends OrmLiteBaseActivity<DatabaseHelper>{
	public TextView txt_status;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.loading);
        
        initControl();
        
        LoadingAsyncTask task = new LoadingAsyncTask(this);
        task.execute("");
	}
	
	private void initControl(){				
		TextView txt_versionName =(TextView)findViewById(R.id.txt_versionName);
		txt_versionName.setText("版本号:"+SystemInfo.getVersionName(this));	
		txt_status = (TextView)findViewById(R.id.txt_status);
	}
}
