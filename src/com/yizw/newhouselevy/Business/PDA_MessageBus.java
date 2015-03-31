package com.yizw.newhouselevy.Business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Handler;
import android.os.Message;

import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.MsgAutoUploadParm;
import com.yizw.newhouselevy.Entity.PDA_Message;
import com.cogent.core.msg.UploadMsg;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

/** PDA消息表操作类*/
public class PDA_MessageBus {
	/** 存放消息自动上传的所有参数信息*/
	public static MsgAutoUploadParm msgAutoUploadParm = new MsgAutoUploadParm();
	public static Handler handler = null;
	
	private Dao<PDA_Message, Integer> PDA_MessageDao;
	
	public PDA_MessageBus(DatabaseHelper databaseHelper) throws SQLException {
		PDA_MessageDao = databaseHelper.getDao(PDA_Message.class);
	}
	
	/** 获取一个未上传的消息，如果没有则返回null */
	public PDA_Message getUploadMsg() throws SQLException{
		
		//如果缓存中没有待上传消息的id，则从数据库中去获取
		if(msgAutoUploadParm.getIds().size()== 0)
			getAllNoUploadMsgIds();
		
		//从数据库中获取过后，如果缓存中还是没有待上传消息id则说明暂时没有可上传的消息，直接返回null
		if(msgAutoUploadParm.getIds().size()== 0)
			return null;
		
		//得到缓存中第一个未上传的消息id
		int id = msgAutoUploadParm.getIds().get(0);
		
		return queryForId(id);
	}
	
	/** 创建一个新的待上传消息*/
	public boolean create(UploadMsg uploadMsg) throws SQLException {	
		PDA_Message pda_msg = new PDA_Message();
		pda_msg.setUuid(uploadMsg.getUUID());
		pda_msg.setContent(uploadMsg.toString());
		pda_msg.setIsupload(0);
		pda_msg.setCredate(new Date());
		
		int returnInt = PDA_MessageDao.create(pda_msg);
		if (returnInt == 1){
			//如果创建成功，则待上传消息加1
			int waitCount = msgAutoUploadParm.getWaitSendMessageCount()+ 1;
			msgAutoUploadParm.setWaitSendMessageCount(waitCount);
			if(handler!=null){
				Message msg = new Message();
				handler.sendMessage(msg);
			}
			return true;
		}
		else
			return false;
	}
	
	public void update(PDA_Message data) throws SQLException{
		PDA_MessageDao.update(data);
	}
	
	/** 消息上传成功后进行的操作(修改上传成功的消息的状态标志，并从待上传缓存id中去除，更新待上传消息数)
	 * @throws SQLException */
	public void uploadSuccess() throws SQLException{
		//如果待上传消息缓存id为空则终止执行，正常情况该现象不会出现，该判断只是从操作安全性出发
		if(msgAutoUploadParm.getIds().size()==0)
			return;
		
		int id = msgAutoUploadParm.getIds().get(0);
		//将状态标志改为已上传
		if(UpdateMsgUploadState(id,1)){
			//如果修改成功，则待上传消息减1，并将该id从缓存中去除
			int waitCount = msgAutoUploadParm.getWaitSendMessageCount()- 1;
			msgAutoUploadParm.setWaitSendMessageCount(waitCount);
			msgAutoUploadParm.getIds().remove(0);
		}
	}
	
	/** 根据id获取该条消息*/
	public PDA_Message queryForId(Integer id) throws SQLException{
		return PDA_MessageDao.queryForId(id);
	}
	
	public boolean UpdateMsgUploadState(int id, int isUpload) throws SQLException{
		String sql = "update pda_message set isUpload = " + isUpload + " where id=" + id;
		int returnInt = PDA_MessageDao.updateRaw(sql);
		if (returnInt == 1)
			return true;
		else
			return false;
	}
	
	public String GetNoUploadMsgCount() throws SQLException{
		String sql = "select count(id) from pda_message where isUpload = 0";
		GenericRawResults<String[]> result = PDA_MessageDao.queryRaw(sql);
		List<String[]> resultList = result.getResults();
		return resultList.get(0)[0];
	}
	
	/** 查询出所有未上传消息的id，缓存起来，避免获取全部消息造成占用内存过大 */
	private void getAllNoUploadMsgIds() throws SQLException {
		QueryBuilder<PDA_Message, Integer> queryBuilder = PDA_MessageDao.queryBuilder();	
		ArrayList<String> columnsList = new ArrayList<String>();
		columnsList.add("id");//只查询出id
		queryBuilder.selectColumns(columnsList);
		queryBuilder.where().eq("isupload", 0);//未上传
		queryBuilder.orderBy("id", true);//按id排序
		PreparedQuery<PDA_Message> preparedQuery = queryBuilder.prepare();
		List<PDA_Message> list = PDA_MessageDao.query(preparedQuery);
		
		List<Integer> idsList = new ArrayList<Integer>();
		for(int i = 0; i< list.size();i++){
			idsList.add(list.get(i).getId());
		}
		
		//将获取到未上传消息缓存起来，更新待上传消息数量
		msgAutoUploadParm.setIds(idsList);
		msgAutoUploadParm.setWaitSendMessageCount(idsList.size());
	}
}
