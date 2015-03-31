package com.yizw.newhouselevy.Business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import com.yizw.newhouselevy.GlobalVar;
import com.example.newhouselevy.R;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.SpinnerDomain;
import com.cogent.core.component.SpinnerAdapterEx;
import com.cogent.core.component.ValueName;
import com.cogent.core.util.DateHelper;
import com.cogent.core.util.DialogHelper;
import com.cogent.core.util.MyException;
import com.cogent.core.util.StringHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * 用于存放一些公用方法
 */
public class PublicBus {
	
	/**
	 * 控制标题栏的名称和左右两个按钮
	 * @param activity 
	 * @param titleName 标题名称
	 * @param leftShow 左边按钮是否显示
	 * @param leftTxt 左边按钮显示的文字，为空则显示默认值
	 * @param rightShow 右边按钮是否显示
	 * @param rightTxt 右边按钮显示的文字，为空则显示默认值
	 */
	public static void titleBarControl(Activity activity,String titleName,boolean leftShow,String leftTxt,boolean rightShow,String rightTxt){
		TextView txt_title_name = (TextView)activity.findViewById(R.id.txt_title_name);
		Button btn_title_left = (Button)activity.findViewById(R.id.btn_title_left);
		Button btn_title_right = (Button)activity.findViewById(R.id.btn_title_right);
		
		txt_title_name.setText(titleName);
//		txtBold(txt_title_name);
		
		if(leftShow){
			btn_title_left.setVisibility(View.VISIBLE);
			if(!StringHelper.isNullOrEmpty(leftTxt))
				btn_title_left.setText(leftTxt);
		}else{
			btn_title_left.setVisibility(View.INVISIBLE);
		}
		
		if(rightShow){
			btn_title_right.setVisibility(View.VISIBLE);
			if(!StringHelper.isNullOrEmpty(rightTxt))
				btn_title_right.setText(rightTxt);
		}else{
			btn_title_right.setVisibility(View.INVISIBLE);
		}
	}

	public static void fillText(View v, int id, String text) {
		TextView textView = (TextView) v.findViewById(id);
		textView.setText(text == null ? "" : text);
	}
	
	public static void fillEdit(View v, int id, String text) {
		EditText edit = (EditText) v.findViewById(id);
		edit.setText(text == null ? "" : text);
	}
	
	public static void fillBtn(View v, int id, String text) {
		Button btn = (Button) v.findViewById(id);
		btn.setText(text == null ? "" : text);
	}
	
	/**对TextView控件的文本加粗*/
	public static void txtBold(TextView tv){
		TextPaint tp = tv.getPaint(); 
		tp.setFakeBoldText(true); 
	}
	
    /**
     * 获取Spinner选中项的值（非显示名称），必须是使用SpinnerAdapterEx
     * @return 未取到时返回空
     */
    public static String spinnerGetValue(Spinner spinner){
    	int position = spinner.getSelectedItemPosition();
    	if(position>=0){
    		SpinnerAdapterEx adapter = (SpinnerAdapterEx) spinner.getAdapter();
    		return adapter.getValue(position);
    	}else
    		return "";
    }
    
    /**
     * 获取Spinner选中项的名称
     * @return 未取到时返回null
     */
    public static String spinnerGetName(Spinner spinner){
    	if(spinner.getSelectedView()==null)
    		return null;
    	else{
    		TextView txtv = (TextView)spinner.getSelectedView();
    		return txtv.getText().toString();
    	}
    }
    
    /**
     * 设置Spinner选中项的值（非显示名称），必须是使用SpinnerAdapterEx
     */
    public static void spinnerSetValue(Spinner spinner,Object value){
    	SpinnerAdapterEx adapter = (SpinnerAdapterEx)spinner.getAdapter();
    	
    	if(value == null||adapter==null)
    		return;   	
    	
    	int position = adapter.GetPositionByValue(value);
    	spinner.setSelection(position);
    }
    
    
    /**
     * 得到一个状态提示框(默认已经显示)
     * @param context
     * @param msg 如果不为null或空，将作为状态框的提示文字
     */
    public static AlertDialog getStatusDialog(Context context,String msg){
    	return DialogHelper.getStatusDialog(context, msg);
    }

    /**
     * 构建一个按下返回键时需要的确认消息框
     * @param activity 
     * @param title 消息框标题
     * @param msg 消息框内容
     * @return
     */
    public static AlertDialog builderConfirmDialog(final Activity activity,String title,String msg){
		AlertDialog dialog = 
			new AlertDialog.Builder(activity)
			.setTitle(title)
			.setMessage(msg)
			.setPositiveButton("确定", new OnClickListener() {				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					activity.finish();
				}
			})
			.setNegativeButton("取消", new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			})
			.create();
    	return dialog;
    }
        
    
	/** 对Spinner控件进行数据绑定,使用适配器为SpinnerAdapterEx*/
	public static void spinnerBinding(Context context,Spinner spinner,List<ValueName> list){		
		spinnerBinding(context,spinner,list,0,16);
	}	
	public static void spinnerBinding(Context context,Spinner spinner,List<ValueName> list,int txtColor,int txtSize){		
		spinnerBinging(context,spinner,list,"Name","Value",txtColor,txtSize);
	}	
	public static void spinnerBinging(Context context,Spinner spinner,List<?> list,String name,String value){
		spinnerBinging(context,spinner,list,name,value,0,16);
	}	
	public static void spinnerBinging(Context context,Spinner spinner,List<?> list,String name,String value,int txtColor,int txtSize){
		SpinnerAdapterEx adapter = new SpinnerAdapterEx(context,list);
		adapter.setDisplayMember(name);
		adapter.setValueMember(value);
		adapter.txtColor = txtColor;
		adapter.txtSize = txtSize;
		spinner.setAdapter(adapter);
	}
	
	public static void spinnerEnumBinding(Spinner spinner,String enumcode,boolean allowEmptyRow,DatabaseHelper helper,Context context) throws MyException{
		try {
			SpinnerDomain spinnerDomain = Sys_EnumBus.getEnum(enumcode, helper);
			ArrayList<ValueName> list = spinnerDomain.getItems();
			if(allowEmptyRow){
				//如果允许为空，则复制一个新的list，在新的list中增加空项，以免修改了缓存中的数据
				ArrayList<ValueName> tempList = new ArrayList<ValueName>();
				ValueName emptyRow =new ValueName();
				emptyRow.setName("");
				emptyRow.setValue(GlobalVar.enum_defalut);
				tempList.add(emptyRow);
				for(int i=0;i<list.size();i++){
					tempList.add(list.get(i));
				}
				spinnerBinding(context,spinner,tempList);
			}else{	
				spinnerBinding(context,spinner,list);
			}
		} catch (SQLException e) {
			throw new MyException("枚举值绑定出错", "绑定枚举"+enumcode+"出现异常\n"+ e.getMessage(),e.getStackTrace());
		}

	}
      
	public static void setDate(final Context context,final Button btn){
		Date initDate = PublicBus.btnGetDate(context,btn);		
		Date date = null;	
		btn.setTag(null);
		//如果给定了初始时间，则使用初始时间
		if(initDate!=null)
			date = initDate;
		else
			date = new Date();
		
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		
		DatePickerDialog dialog = new DatePickerDialog(
				context,
				new OnDateSetListener() {				
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
						Calendar calendar = Calendar.getInstance();
						calendar.set(year,monthOfYear,dayOfMonth);
						
						Object tag = btn.getTag();
						if(tag == null){
							String dateStr = DateHelper.dateToString(calendar.getTime(), DateHelper.formatTwo);
							btn.setText(dateStr);
						}	
					}
				},				
				calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH)
		);
		
		dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "取消", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				btn.setTag(false);
			}
			
		});
		
		dialog.setButton(DatePickerDialog.BUTTON_NEUTRAL, "清除", new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				btn.setTag(false);
				btn.setText("");
			}
		});
		
		dialog.show();
	}

    /** 获取用来存放日期的按钮的值*/
	public static Date btnGetDate(Context context,Button btn){
    	String btnStr = btn.getText().toString();
    	if(btnStr.equals(""))
    		return null;
    	else
    		return DateHelper.stringToDate(btnStr);
    }
    
    /** 设置用来存放日期的按钮的值*/
	public static void btnSetDate(Context context,Button btn,Date date){
    	if(date==null){
    		btn.setText("");
    	}else{
			String btnStr = DateHelper.dateToString(date, DateHelper.formatTwo);
			btn.setText(btnStr);
    	}
    }	
	
	/** 将子控件设为不可用 目前只适应LinearLayout布局*/
	public static void disabled(LinearLayout linear){
		for (int i = 0; i < linear.getChildCount(); i++) { 
			View v = linear.getChildAt(i);
			if(v instanceof LinearLayout){
				LinearLayout myLinear = (LinearLayout) v;
				disabled(myLinear);
			}else if ( v instanceof EditText){
				v.setEnabled(false);
				v.setFocusable(false);
			}else if (v instanceof Button){
				v.setEnabled(false);
			}else if (v instanceof Spinner){
				v.setEnabled(false);
			}
		}
	}
	
	public static void addStr(StringBuilder strBuilder,String str){
		if(strBuilder.length()>0)
			strBuilder.append("\n"+str);
		else
			strBuilder.append(str);
	}
	
	/**获取Map中指定key对应的值，未获取到返回""*/
	public static String getMapValue(LinkedHashMap<String, Object> map,String key){
			return getMapValue(map,key,"");
	} 
	
	/**获取Map中指定key对应的值，未获取到返回指定的默认值*/
	public static String getMapValue(LinkedHashMap<String, Object> map,String key,String defaultRe){
		if(map == null)
			return defaultRe;
		
		if(map.containsKey(key))
			return map.get(key).toString();
		else
			return defaultRe;
	} 
	
	public static Spinner init_sp_year(Activity activity,int spinner,boolean allowEmptyRow){
		Spinner sp = (Spinner)activity.findViewById(spinner);
		
		Calendar c = Calendar.getInstance();
		int nowYear = c.get(Calendar.YEAR);
		
		ArrayList<ValueName> list = new ArrayList<ValueName>();
		if(allowEmptyRow){
			ValueName emptyRow =new ValueName("",GlobalVar.enum_defalut);
			list.add(emptyRow);
		}
		
		for(int i=2011;i<nowYear+2;i++){
			ValueName vn = new ValueName(i+"", i+"");
			list.add(vn);
		}

		PublicBus.spinnerBinding(activity,sp,list);
		return sp;
	}
	
	public static Spinner init_sp_month(Activity activity,int spinner,boolean allowEmptyRow){
		Spinner sp = (Spinner)activity.findViewById(spinner);
		
		ArrayList<ValueName> list = new ArrayList<ValueName>();
		if(allowEmptyRow){
			ValueName emptyRow =new ValueName("",GlobalVar.enum_defalut);
			list.add(emptyRow);
		}
		
		for(int i=1;i<13;i++){
			ValueName vn = new ValueName(i+"", i+"");
			list.add(vn);
		}

		PublicBus.spinnerBinding(activity,sp,list);
		return sp;
	}
	
}
