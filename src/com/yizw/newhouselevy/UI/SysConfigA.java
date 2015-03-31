package com.yizw.newhouselevy.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;

import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.SysConfigBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.DAO.DatabaseManager;

public class SysConfigA extends PreferenceActivity {
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.sysconfig);
	}
	
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,Preference preference) {
		
		String key = preference.getKey();
		
		if("InitDatabse".equals(key)){
			
			new AlertDialog.Builder(this)
			.setTitle("操作确认")
			.setMessage("确定要强行初始化？")
			.setPositiveButton("确定", new OnClickListener() {				
				
				public void onClick(DialogInterface dialog, int which) {
					
					DatabaseManager dbm = new DatabaseManager(SysConfigA.this);
					String restr = dbm.initDatabse(new DatabaseHelper(SysConfigA.this), true);
					SysConfigBus.setSyncData(SysConfigA.this, SysConfigA.this.getString(R.string.SyncData));
					if(restr.equals(""))
						Toast.makeText(SysConfigA.this, "强行初始化成功", Toast.LENGTH_LONG).show();
					else
						Toast.makeText(SysConfigA.this, "强行初始化失败", Toast.LENGTH_LONG).show();
				}
			})
			.setNegativeButton("取消", new OnClickListener() {				
				public void onClick(DialogInterface dialog, int which) {
				}
			})
			.create().show();
			
		}
		return false;
	}
}
