package com.yizw.newhouselevy.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.Business.SyncDataForStream;
import com.yizw.newhouselevy.DAO.DatabaseHelper;

/** 数据同步*/
public class SyncDataA extends OrmLiteBaseActivity<DatabaseHelper> implements 
    SyncDataForStream.SyncStatusChangeListener,
    SyncDataForStream.SyncEndListener{

	/** 同步结束*/
	private boolean sysEnd = false;
	private TextView txt;
	private ProgressBar prg;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sync_data);	
		PublicBus.titleBarControl(this, "同步数据", false, null, false, null);
		
		txt = (TextView) findViewById(R.id.txt);
		prg = (ProgressBar) findViewById(R.id.prg);
		
		SyncDataForStream syncData = new SyncDataForStream(this,getHelper());
		syncData.setSyncStatusChangeListener(this);
		syncData.setSyncEndListener(this);
		syncData.SyncData();
	}

	@Override
	public void syncStatusChange(String status) {
		txt.setText(status);
	}

	@Override
	public void syncEnd(Boolean success, String info) {	
		String msg;
		if(success){
			SyncDataA.this.setResult(Activity.RESULT_OK, null);
			if(info.equals(""))
				msg = "同步成功";
			else
				msg = "同步成功："+info;
		}else{
			if(info.equals(""))
				msg = "同步失败";
			else
				msg = "同步失败："+info;
		}
		
		txt.setText(msg);
		prg.setVisibility(View.GONE);
		sysEnd = true;
		
		new AlertDialog.Builder(SyncDataA.this).setMessage(msg)
				.setPositiveButton("确定", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						SyncDataA.this.finish();
					}
				}).create().show();
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(!sysEnd){
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				return false;
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}

}
