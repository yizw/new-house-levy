package com.yizw.newhouselevy.Business;

import android.database.sqlite.SQLiteDatabase;

import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.yizw.newhouselevy.DAO.DatabaseHelper;

/** 对数据库中的数据进行清除整理*/
public class SyncDataClear {

	/** 是否需要执行楼栋管理员变更导致的数据清理操作*/
	public static boolean needClear = false;
	
	/** 从同步过程中的sql语句中检测，是否需要执行楼栋管理员变更导致的数据清理操作*/
	public static void needClearCheck(String sql){
//		if(!needClear){
//			if(sql.length()<150){//该判断为了避免操作过长的sql语句，因为这样的语句肯定不是操作管辖楼栋的
//				//如果sql语句中有管辖楼栋的操作，则需要执行楼栋管理员变更导致的数据清理操作
//				if(sql.contains("SQ_BUILDING")||sql.contains("sq_building"))
//					SyncDataClear.needClear = true;
//			}
//		}
//		sql = null;
	}
	/** 清理不需要的数据*/
	public static void clearData(DatabaseHelper databaseHelper){
		try {
			SQLiteDatabase db = databaseHelper.getWritableDatabase();
			
			Sys_EnumBus.putAllToCache(databaseHelper);//加载枚举缓存
			
			//清除已上传的消息
			db.execSQL("DELETE FROM PDA_Message WHERE PDA_Message.isupload = 1;");
			
			if(needClear){//楼栋管理员变更需要执行的清理操作
				needClear = false;
				String admin = LoginBus.getLogin(databaseHelper).getSYSID();
				//清除非管辖内的楼栋
				db.execSQL("DELETE FROM SQ_BUILDING WHERE ADMIN <> '"+admin+"';");
				//清除非管辖内的房间
				db.execSQL("DELETE FROM SQ_HOUSE WHERE SQ_HOUSE.BUILDINGID NOT IN(SELECT SQ_BUILDING.ID FROM SQ_BUILDING);");				
				//清除非管辖范围内的人口
				db.execSQL("DELETE FROM POP_POPULATE WHERE POP_POPULATE.BUILDINGID NOT IN(SELECT SQ_BUILDING.ID FROM SQ_BUILDING);");
			}
		} catch (Exception e) {
			MyException myE = new MyException("", "清理不需要的数据时出错",e.getStackTrace());
			ExceptionHelper.Operate(myE, false, null);
		}
	}
}
