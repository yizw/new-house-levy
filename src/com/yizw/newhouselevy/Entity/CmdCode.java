package com.yizw.newhouselevy.Entity;

/** 存放通信用的所有命令*/
public class CmdCode {
	
	/** 升级检测*/
	public static String CHECK = "CHECK";
	/** 登录*/
	public static String LOGIN = "LOGIN";
	/** 同步数据*/
	public static String SYN_DATA = "SYN_DATA";
	/** 查询PDA设备信息*/
	public static String SEARCH_PDA_INFO = "SEARCH_PDA_INFO";
	
	/** 通用查询*/
	public static String SEARCH_INFO = "SEARCH_INFO";
	
	/**项目总览表分页查询*/
	public static String SEARCH_PRODUCTPAGE = "SEARCH_PRODUCTPAGE";
	/**项目总推进表 总览表分页查询*/
	public static String SEARCH_PROGRESS_MAINLIST = "SEARCH_PROGRESS_MAINLIST";
	/**项目总推进表 具体信息查询*/
	public static String SEARCH_PROGRESS_MAIN_INFO = "SEARCH_PROGRESS_MAIN_INFO";
	/**项目分户列表分页查询*/
	public static String SEARCH_PRO_HOUSEHOLD_LIST = "SEARCH_PRO_HOUSEHOLD_LIST";	
	/**分户详情*/
	public static String SEARCH_PRO_HOUSEHOLD_INFO = "SEARCH_PRO_HOUSEHOLD_INFO";	
	/**分户推进列表*/
	public static String SEARCH_PROGRESS_BRANCHLIST = "SEARCH_PROGRESS_BRANCHLIST";
	/**分户推进表详情*/
	public static String SEARCH_PROGRESS_BRANCH_INFO = "SEARCH_PROGRESS_BRANCH_INFO";
		
	/**分户房屋确认表*/
	public static String SEARCH_PRO_HOUSE_CONFIRM_INFO = "SEARCH_PRO_HOUSE_CONFIRM_INFO";
	/**分户房屋确认表子表-户籍情况表*/
	public static String SEARCH_PRO_FAMILY_REGLIST = "SEARCH_PRO_FAMILY_REGLIST";
	/**分户房屋计算单列表*/
	public static String SEARCH_PRO_H_LIST = "SEARCH_PRO_H_LIST";
	/**分户计算单详情*/
	public static String SEARCH_PRO_H_LIST_INFO = "SEARCH_PRO_H_LIST_INFO";
	
	
	/**分户房屋确认表子表-房屋产权手续登记表  列表信息查询*/
	public static String SEARCH_HOUSE_CONFIRM_REG_LIST = "SEARCH_HOUSE_CONFIRM_REG_LIST";
	/**分户房屋确认表子表-房屋产权手续登记表  产权详细信息查询*/
	public static String SEARCH_HOUSE_CONFIRM_REG_INFO = "SEARCH_HOUSE_CONFIRM_REG_INFO";
	/**分户房屋确认表子表-房屋产权手续登记表   产权详细信息保存*/
	public static String SAVE_HOUSE_CONFIRM_REG = "SAVE_HOUSE_CONFIRM_REG";
	
	
	/**保存总推进*/
	public static String SAVE_PROGRESS_MAIN = "SAVE_PROGRESS_MAIN";
	/**保存分户*/
	public static String SAVE_PRO_HOUSEHOLD = "SAVE_PRO_HOUSEHOLD";
	/**分户推进保存*/
	public static String SAVE_PROGRESS_BRANCH = "SAVE_PROGRESS_BRANCH";
	/**确认表保存*/
	public static String SAVE_PRO_HOUSE_CONFIRM = "SAVE_PRO_HOUSE_CONFIRM";
	
	/**资料库分页查询*/
	public static String SEARCH_DATABASEPAGE = "SEARCH_DATABASEPAGE";
	/**资料详细信息查询*/
	public static String SEARCH_DATABASE_INFO = "SEARCH_DATABASE_INFO";
	
	
	/**房源-房开商 分页查询*/
	public static String SEARCH_H_HOUSE_LIST = "SEARCH_H_HOUSE_LIST";
	/**房源-楼栋 分页查询*/
	public static String SEARCH_HOUSEBNO = "SEARCH_H_HOUSE_ITEM_LIST";
	/**房源-单元 分页查询*/
	public static String SEARCH_HOUSEUNIT = "SEARCH_H_HOUSE_ITEM_LIST";
	/**房源-楼层  分页查询*/
	public static String SEARCH_HOUSEFLOOR = "SEARCH_H_HOUSE_ITEM_LIST";
	/**房源-房间号 分页查询*/
	public static String SEARCH_H_HOUSE_ITEM_LIST = "SEARCH_H_HOUSE_ITEM_LIST";
	
	/**房源-房间号 查询到房间信息*/
	public static String SEARCH_HOUSE_ROOM_INFO = "SEARCH_HOUSE_ROOM_INFO";	
	/**房源-房间号 保存选房信息*/
	public static String SAVE_HOUSE_ROOM_INFO = "SAVE_HOUSE_ROOM_INFO";
	
	/**国有个人-集体个人-产权  删除选房信息*/
	public static String DELETE_HOUSE_ROOM_INFO = "DELETE_HOUSE_ROOM_INFO";
	
	
	
	/**资金余额项目列表分页查询*/
	public static String SEARCH_FUNDBALANCE = "SEARCH_FUNDBALANCE";
	/**资金余额详细信息查询*/
	public static String SEARCH_FUNDBALANCE_INFO = "SEARCH_FUNDBALANCE_INFO";
	
	/**问题反馈--项目及分户   问题反馈--问题信息列表详情查询*/
	public static String SEARCH_PROFEEDBACK_QUESTION_INFO = "SEARCH_PROFEEDBACK_QUESTION_INFO";
	/**问题反馈--项目问题反馈--问题保存*/
	public static String SAVE_PRO_PROFEEDBACK_QUESTION_INFO = "SAVE_PROFEEDBACK_QUESTION_INFO";
	
	/**问题反馈--项目及 分户 问题反馈--反馈信息列表详情查询*/
	public static String SEARCH_PROFEEDBACK_REPLY_INFO = "SEARCH_PROFEEDBACK_REPLY_INFO";
	/**问题反馈--项目问题反馈--回复保存*/
	public static String SAVE_PRO_PROFEEDBACK_REPLY_INFO = "SAVE_PRO_PROFEEDBACK_REPLY_INFO";
	
	
	/**问题反馈--分户问题反馈--问题保存*/
	public static String SAVE_HOU_PROFEEDBACK_QUESTION_INFO = "SAVE_HOU_PROFEEDBACK_QUESTION_INFO";
	/**问题反馈--分户问题反馈--回复保存*/
	public static String SAVE_HOU_PROFEEDBACK_REPLY_INFO = "SAVE_HOU_PROFEEDBACK_REPLY_INFO";
	
	
	
	/**确认表-附件   列表查询*/
	public static String SEARCH_CONFIRM_ATT_LIST = "SEARCH_CONFIRM_ATT_LIST";
	/**确认表-附件  列表 Item信息查询*/
	public static String SEARCH_CONFIRM_ATT_INFO = "SEARCH_CONFIRM_ATT_INFO";
	/**确认表-附件    列表 Item信息   保存*/
	public static String SAVE_CONFIRM_ATT_INFO = "SAVE_CONFIRM_ATT_INFO";
	
	
	/**算单-附件   列表查询*/
	public static String SEARCH_HLIST_ATT_LIST = "SEARCH_HLIST_ATT_LIST";
	/**算单-附件   列表Item信息查询*/
	public static String SEARCH_HLIST_ATT_INFO = "SEARCH_HLIST_ATT_INFO";
	/**算单-附件   列表Item信息  保存*/
	public static String SAVE_HLIST_ATT_INFO = "SAVE_HLIST_ATT_INFO";
	
	
	/**用户信息  查询*/
	public static String SEARCH_USER_INFO = "SEARCH_USER_INFO";
	/**用户信息修改 保存*/
	public static String SAVE_USER_INFO = "SAVE_USER_INFO";
	
	
	/**时限控制    信息查询*/
	public static String SEARCH_TIMECONTROL_INFO = "SEARCH_TIMECONTROL_INFO";
	
	
}
