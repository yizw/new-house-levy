package com.yizw.newhouselevy.Adapter;

import java.util.LinkedHashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;
import com.cogent.core.component.DataTableList;

public class TotalPushTableListAdapter extends BaseAdapter {

	private Context context;
	private DataTableList list;
	
	
	public TotalPushTableListAdapter(Context context,DataTableList list){
		this.context = context;
		this.list = list;
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
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.total_pushtable_list_item, null);
		}
		
		String ID = list.get(position).get("id").toString();
		
		//判断用户是否有该项目总推进权限
		//	String ROLE = list.get(position).get("typerole").toString();
			String ROLE =null;
			Object ROLEob = list.get(position).get("typerole");
			if(ROLEob !=null){
				ROLE = ROLEob.toString();
			//	fillText(v,R.id.h_role,ROLE);
			}
			
		
		//项目标题
		String projecttitle = list.get(position).get("pname").toString();

		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(ID);
		
		
		TextView h_role = (TextView) v.findViewById(R.id.h_role);
		h_role.setText(ROLE);
		
				
		//项目标题
		PublicBus.fillText(v, R.id.txt_projecttitle,projecttitle);		
		return v;
	}
	
	
	
}
