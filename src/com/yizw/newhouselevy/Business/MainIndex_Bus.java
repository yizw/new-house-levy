package com.yizw.newhouselevy.Business;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PDA_Login;

public class MainIndex_Bus {
	DatabaseHelper helper;
	private Dao<PDA_Login, String> dao;
	
	public MainIndex_Bus(DatabaseHelper databaseHelper) throws SQLException {
		this.helper = databaseHelper;
		dao = databaseHelper.getDao(PDA_Login.class);
	
	}
	
	public static String check(PDA_Login entity){
		StringBuilder strBuilder =new StringBuilder();
		return strBuilder.toString();
	}
	
    public  List<PDA_Login> queryListFull() throws SQLException{    	   	
    	return dao.queryForAll();
    }

    public PDA_Login getLoginByid(String in_id) throws SQLException {	
	   return dao.queryForId(in_id);
    }
        
}
