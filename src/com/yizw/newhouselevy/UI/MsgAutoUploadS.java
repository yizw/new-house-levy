package com.yizw.newhouselevy.UI;

import java.sql.SQLException;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;

import com.cogent.core.msg.MessageUploader;
import com.cogent.core.msg.RebackInfo;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.yizw.newhouselevy.MsgConfig;
import com.yizw.newhouselevy.Business.PDA_MessageBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PDA_Message;

/**用来控制消息上传的服务*/
@SuppressLint("HandlerLeak")

public class MsgAutoUploadS extends OrmLiteBaseService<DatabaseHelper>{
	/** 消息上传状态通知栏的标识ID*/
	public static int id_msg = 131;
	/** 通知服务管理对象*/
	private NotificationManager manager = null;
	/** 通知栏的UI*/
	private RemoteViews remoteView = null;
	private Notification notification = new Notification();
	private PendingIntent pIntent = null;
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//更新待上传消息通知状态
			int UploadCycle = PDA_MessageBus.msgAutoUploadParm.getUploadCycle()/1000;
			int WaitCount = PDA_MessageBus.msgAutoUploadParm.getWaitSendMessageCount();
			String noticeStr;
			if(PDA_MessageBus.msgAutoUploadParm.isUploadStatus())
				noticeStr = "消息上传周期(秒): " + UploadCycle + "     待上传消息条数: " + WaitCount;			
			else
				noticeStr = "消息上传已停止" + "     待上传消息条数：" + WaitCount;
			remoteView.setTextViewText(R.id.txt_1, noticeStr);
			
//			//更新GPS上传通知状态
//			if(GpsBus.open)
//				noticeStr = "坐标采集周期(秒): " + GpsBus.cycle/1000 + "     已成功采集数量: " + GpsBus.num;			
//			else
//				noticeStr = "坐标采集已停止";
//			remoteView.setTextViewText(R.id.txt_2, noticeStr);
			
			
			// 关键部分，重新更新通知
			notification.contentView = remoteView;
			notification.contentIntent = pIntent;
			manager.notify(id_msg, notification);
			super.handleMessage(msg);
		}

	};
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		initNotification();
		startMsgAutoUpload();
	//	startGPS();
	}
	
	private void initNotification(){
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		remoteView = new RemoteViews(getPackageName(), R.layout.msg_notice_view);
		Intent intent = new Intent(this, MsgNotificationA.class);
		pIntent = PendingIntent.getActivity(this, 0, intent, 0);		
				
		// 通知的图标必须设置(其他属性为可选设置),否则通知无法显示
		notification.icon = R.drawable.logo;
	}
	
	/** 启动消息自动上传*/
	private void startMsgAutoUpload(){
		
		try {//从数据库中读取一次，初始化待上传的消息数量
			PDA_MessageBus pda_MessageBus = new PDA_MessageBus(getHelper());
			PDA_MessageBus.handler = handler;
			pda_MessageBus.getUploadMsg();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Message msg = new Message();
		handler.sendMessage(msg);
				
		// 起一个线程用来控制上传
		new Thread(new Runnable() {
			@Override
			public void run() {
					
				PDA_MessageBus pda_MessageBus =null;
				while (PDA_MessageBus.msgAutoUploadParm.isUploadStatus()) {
					try {
						//根据设置的上传周期，线程先等待，时间到后再执行上传操作
						Thread.sleep(PDA_MessageBus.msgAutoUploadParm.getUploadCycle());
						if(!PDA_MessageBus.msgAutoUploadParm.isUploadStatus())
							continue;
							
						if(pda_MessageBus==null)
							pda_MessageBus = new PDA_MessageBus(getHelper());
						PDA_Message pdaMsg = pda_MessageBus.getUploadMsg();
						if(pdaMsg!=null){
							MessageUploader messageUploader = new MessageUploader(getApplicationContext(),new MsgConfig());
							RebackInfo rebackInfo = messageUploader.uploadMessage(true, pdaMsg.getContent());
							if(rebackInfo!=null){
								rebackInfo.throwException();
								if(rebackInfo.getFlag()==1){
									pda_MessageBus.uploadSuccess();																		
								}
							}
						}
						//更新一次通知
						Message msg = new Message();
						handler.sendMessage(msg);
					}
					catch (MyException e) {
						ExceptionHelper.Operate(e, false, getApplicationContext());
					} 
					catch (Exception e) {
						MyException myE =new MyException("自动上传执行出错",e.getMessage(),e.getStackTrace());
						ExceptionHelper.Operate(myE, false, getApplicationContext());
					} 
				}
				
			}
		}).start(); 
	}
	


//	/** 纬度*/
//	public static String latitude="";
//	/** 经度*/
//	public static String longitude="";
//	public void startGPS(){
//		LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5,new mLocationListener());
//	
//        // 起一个线程用来控制GPS上传
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while (GpsBus.open) {
//					try {
//						Thread.sleep(GpsBus.cycle);
//						
//						//用于解决GPS上传关掉后，不能马上停止
//						if(!GpsBus.open)
//							continue;
//												
//						GpsBus.upload(getApplicationContext(),latitude, longitude);
//						latitude = "";
//						longitude = "";
//						
//						//更新一次通知
//						Message msg = new Message();
//						handler.sendMessage(msg);
//						
//					}catch (MyException e) {
//						ExceptionHelper.Operate(e, false, getApplicationContext());
//					}catch (Exception e) {
//						MyException myE =new MyException("GPS上传执行出错",e.getMessage(),e.getStackTrace());
//						ExceptionHelper.Operate(myE, false, getApplicationContext());
//					}					
//				}
//				
//			}}).start();
//	}
//	
	
	
//	class mLocationListener implements LocationListener{
//
//		@Override
//		public void onLocationChanged(Location location) {
//			latitude = String.valueOf(location.getLatitude());//纬度
//			longitude = String.valueOf(location.getLongitude());//经度	
//		}
//
//		@Override
//		public void onProviderDisabled(String provider) {
//		}
//
//		@Override
//		public void onProviderEnabled(String provider) {
//		}
//
//		@Override
//		public void onStatusChanged(String provider, int status, Bundle extras) {
//		}		
//	}
}
