package com.yizw.newhouselevy.Business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.SpinnerDomain;
import com.yizw.newhouselevy.Entity.DBTable.SYS_ENUM;
import com.cogent.core.component.ValueName;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class Sys_EnumBus {
	//枚举缓存
	private static HashMap<String,SpinnerDomain> EnumCache =new HashMap<String,SpinnerDomain>();
	private static HashMap<String,ValueName[]> EnumArrayCache = new HashMap<String, ValueName[]>();
			
	public static void ClearCache(){
		EnumCache.clear();
		EnumArrayCache.clear();
	}
	
	/**
	 * 根据枚举编码获取对应的枚举项数组
	 * @param typecode 枚举编码
	 * @throws SQLException
	 */
	public static ValueName[] getEnumArray(String typecode,DatabaseHelper helper) throws SQLException{
		if (EnumArrayCache.containsKey(typecode)) {//先找缓存
			return EnumArrayCache.get(typecode);
		} else {
			ArrayList<ValueName> list= getEnum(typecode, helper).getItems();
			ValueName[] array = new ValueName[list.size()];
			list.toArray(array);
			EnumArrayCache.put(typecode, array);//缓存处理
			return array;
		}
	}
	
	/**
	 * 根据枚举编码获取对应的枚举集合
	 * @param typecode 枚举编码
	 */
	public static SpinnerDomain getEnum(String typecode,DatabaseHelper helper) throws SQLException {
		//进行一次全部缓存处理
		putAllToCache(helper);
		
		//从缓存中取出对应的枚举集合
		if (EnumCache.containsKey(typecode)) {
			return EnumCache.get(typecode);
		} else {
			SpinnerDomain spinnerDomain = new SpinnerDomain();
			ArrayList<ValueName> valueNameList = new ArrayList<ValueName>();
			spinnerDomain.setItems(valueNameList);
			return spinnerDomain;
		}
	}
	
	/**
	 * 根据枚举编码和枚举值获取该枚举值对应的描述文字,未找到时返回传入的枚举值
	 * @param typecode 枚举编码
	 * @param enumvalue 枚举值
	 * @return 未找到时返回传入的枚举值
	 */
	public static String getEnumName(String typecode, String enumvalue,DatabaseHelper helper) throws SQLException {
		if (typecode==null || typecode.equals("")||enumvalue==null)
			return enumvalue;

		ArrayList<ValueName> list = getEnum(typecode, helper).getItems();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getValue().equals(enumvalue))
				return list.get(i).getName();// 匹配上则返回
		}
		
		if(enumvalue==null||enumvalue.equals(GlobalVar.enum_defalut))
			return "";

		// 未匹配到则返回传入的枚举值
		return enumvalue;
	}
    
	/**
	 * 将枚举值从数据库中全部查出缓存起来,会自动识别处理是否已经缓存过
	 * @throws SQLException 
	 */
	public static void putAllToCache(DatabaseHelper helper) throws SQLException {
		//如果已经缓存处理过则返回
		if(EnumCache.size()!=0){
			return;
		}
		
		//从数据库中取出所有的枚举值
		Dao<SYS_ENUM, String> enumDao = helper.getDao(SYS_ENUM.class);
		QueryBuilder<SYS_ENUM, String> queryBuilder = enumDao.queryBuilder();
		queryBuilder.orderBy("SEQ", true);
		PreparedQuery<SYS_ENUM> preparedQuery = queryBuilder.prepare();
		List<SYS_ENUM> list = enumDao.query(preparedQuery);
		
		//遍历所有枚举，放入缓存
		for(int i=0;i<list.size();i++){
			SYS_ENUM enumItem = list.get(i);
			
			ValueName valueName = new ValueName();
			valueName.setName(enumItem.getENUMITEMNAME());
			valueName.setValue(enumItem.getVALUE());
				
			if(EnumCache.containsKey(enumItem.getENUMTYPECODE())){
				//如果已经存在，则将新的数据项加入
				EnumCache.get(enumItem.getENUMTYPECODE()).getItems().add(valueName);
			}else{
				//如果不存在，则创建对象加入到缓存中
				SpinnerDomain spinnerDomain = new SpinnerDomain();
				ArrayList<ValueName> valueNameList = new ArrayList<ValueName>();
				valueNameList.add(valueName);
				spinnerDomain.setItems(valueNameList);
				EnumCache.put(enumItem.getENUMTYPECODE(), spinnerDomain);
			}
		}
	}
}
