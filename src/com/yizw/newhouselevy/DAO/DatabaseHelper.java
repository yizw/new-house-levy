package com.yizw.newhouselevy.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yizw.newhouselevy.GlobalVar;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	Context context;
	/** 数据库文件名字*/
	private static final String DATABASE_NAME = DatabaseManager.DB_NAME;
	/** 数据库版本号*/
	public static final int DATABASE_VERSION = 1;
	/** 是否是首次初始化数据库*/
	public static boolean init = false;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		Log.i(GlobalVar.TAG, "数据库创建  版本:"+DATABASE_VERSION);
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			if(init)
				return;
			
			Log.i(GlobalVar.TAG, "数据库版本更新  oldVersion:"+oldVersion+"   newVersion:"+newVersion);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
