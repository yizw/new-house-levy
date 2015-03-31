package com.yizw.newhouselevy.Adapter;

import java.util.LinkedHashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cogent.core.component.DataTableList;
import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;

public class HouseUnitListAdapter extends BaseAdapter {

	private Context context;
	private DataTableList list;
	
	
	public HouseUnitListAdapter(Context context,DataTableList list){
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
			v = vi.inflate(R.layout.house_unit_list_item, null);
		}
		
		String ID = list.get(position).get("id").toString();
		
		
		//单元标题
		String title = list.get(position).get("title").toString();

		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(ID);
				
		//单元标题
		PublicBus.fillText(v, R.id.txt_title,title);		
		return v;
	}
	
	
	
}
