package com.yizw.newhouselevy.Entity;

public class EnumCode {
	
	public class SQ_HOUSE{
		public static final String USECIRCS = "USAG";
		public static final String HOUSESHAP = "FWHX";
	}
	
//	/** 房屋确认调查表*/
//	public class HOU_CONFIRM_TABLE{
//		public static final String ispaytax = "SF";
//		public static final String ismortgage = "SF";
//		public static final String isclose = "SF";		
//		public static final String isoperat = "SF";
//		public static final String islegaluse = "SF";		
//	}
	
	/**分户信息页面 */
	public class HOU_HOUSEHOLD{
		//分户类型 0表示个人，1表示企业
		public static final String householdtype = "household_type";
	}	
	
	/**分户推进表信息页面 */
	public class HOU_PUSH_INFO{
		//推进表单项完成状态0表示未开始；1表示进行中；2表示已完成
		public static final String status = "finish_status";
	}	
	
	/**总推进表信息页面 */
	public class TOTAL_PUSH_INFO{
		//推进表单项完成状态0表示未开始；1表示进行中；2表示已完成
		public static final String status = "finish_status";
	}	 
	
	/**国有个人产权调换算单页面 */
	public class GYGR_PRO_INFO{
		//推进表单项完成状态0表示被征收房屋小于安置房；1表示被征收房屋大于安置房
		public static final String bqflagvalue = "bqflagvalue";
	}	 
	
	/**国有个人货币补偿算单页面 */
	public class GYGR_MON_INFO{
		//推进表单项完成状态0表示被征收房屋小于安置房；1表示被征收房屋大于安置房
		public static final String zxbclagvalue = "zxbclagvalue";
	}	
		
}
