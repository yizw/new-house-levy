package com.yizw.newhouselevy.Adapter;

import java.util.LinkedHashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cogent.core.component.DataTableList;
import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;


/*分户 问题反馈（问题反馈信息也是一个list，所以需要adapter类绑定数据）*/
public class HouProblemFeedbackAdapter extends BaseAdapter {

	private Context context;
	private DataTableList list;
	DatabaseHelper helper;
	
	public HouProblemFeedbackAdapter(Context context,DataTableList list,DatabaseHelper helper){
		this.context = context;
		this.list = list;
		this.helper = helper;
	}
	
	public void addItem(LinkedHashMap<String, Object> map){
		list.add(map);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		LinearLayout layout_id = null;
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.hou_question_item, null);
			layout_id = (LinearLayout) v.findViewById(R.id.linearLayout_id);
		} else{
			v = convertView;
			layout_id = (LinearLayout) v.findViewById(R.id.linearLayout_id);
		}
		if (list.size() == 1){
			layout_id.setBackgroundResource(R.drawable.preference_one_item);
		} else if (list.size() >1) {
			if (position == 0){
				layout_id.setBackgroundResource(R.drawable.preference_first_item);
			} 
			if (position == list.size()-1) {
				layout_id.setBackgroundResource(R.drawable.preference_last_item);
			} 
			if (position > 0 && position < list.size()-1){
				layout_id.setBackgroundResource(R.drawable.preference_item);
			}
		}
		if (list == null)
			return null;
		String id = list.get(position).get("id").toString();		
		
		String createddate = list.get(position).get("createddate").toString();//问题反馈时间
		String fdperson = list.get(position).get("feedbackperson").toString();//反馈人
		
		String content = list.get(position).get("content").toString();//问题反馈内容
		String parentid = list.get(position).get("parentid").toString();//回复对应的问题反馈ID，如果是问题反馈记录则为空
		
		createddate = createddate.replace(".0", "");

		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(id);
		txt_id.setTag(parentid);
		
		PublicBus.fillText(v, R.id.txt_1,createddate);
		PublicBus.fillText(v, R.id.txt_2,fdperson);
		PublicBus.fillText(v, R.id.txt_3,content);	
		
		return v;
	}

}
