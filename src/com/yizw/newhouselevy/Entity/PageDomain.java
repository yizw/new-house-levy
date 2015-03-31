package com.yizw.newhouselevy.Entity;

import android.content.Context;
import android.widget.Toast;

/**
 * 存放分页相关的参数信息
 */
public class PageDomain {
	
	
	/** 数据是否正在加载*/
	public boolean isloading = false;
	
	/** 当前页加载失败*/
	public boolean loadFailed = false;
	
	/** 数据项总数*/
	public int itemTotal = -1;
	
	/** 每一页拥有的数据项数量*/
	public int pageItems;
	
	/** 当前显示页的最后一项索引*/
	public int lastItem;//注：增加了一个加载项,列脚
	
	/** 总页数*/
	public int getPageNum(){
		if(pageItems!=0){
			int pageNum = itemTotal/pageItems + (itemTotal%pageItems>0?1:0);
			return pageNum;
		}else{
			return 0;
		}
	}
	
	/** 当前页*/
	public int getNowPage(){
		if(pageItems!=0){
			int nowPage = lastItem/pageItems + (lastItem%pageItems>0?1:0);
			return nowPage;
		}else{
			return 0;
		}
	}
	
	private String tempInfo = "";//用来临时存放提示信息，以便过滤掉重复提示的信息
	
	/** 显示当前，页的提示信息*/
	public void showInfo(Context context){
		int nowPage = getNowPage();
		if(itemTotal==0||nowPage==0)
			return;
		
		String info = nowPage + "/" + getPageNum() + "页";
		if(!info.equals(tempInfo)){
			tempInfo = info;
			Toast msg = Toast.makeText(context,info, Toast.LENGTH_SHORT);      
	        msg.show();
		}							
	}
}
