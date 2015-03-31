package com.yizw.newhouselevy.UI;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.newhouselevy.R;

/**
 * 点击通知显示的页面
 */
public class MsgNotificationA extends Activity{
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg_notification);
        
        this.finish();
        
        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				// 取消显示在通知列表中的指定通知（参数为通知标识符）
				nm.cancel(0);
			}
		});
    }

}
