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

public class HouseRoomListAdapter extends BaseAdapter {

	private Context context;
	private DataTableList list;
	
	public static String[] statusnames=new String[]{"空","已签约","暂时占用","限制使用","已签订","已交付"};
	
	
	public HouseRoomListAdapter(Context context,DataTableList list){
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
			v = vi.inflate(R.layout.house_room_list_item, null);
		}
		
		String ID = list.get(position).get("id").toString();
		
		
		//房间号
		String title = list.get(position).get("room").toString();
		//房间状态
		String status = list.get(position).get("status").toString();

		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(ID);
				
		
		//房间号
		PublicBus.fillText(v, R.id.txt_title,"房间号："+title);
		//房间状态
		PublicBus.fillText(v, R.id.txt_status,"房间状态:"+statusnames[Integer.valueOf(status)]);
		
		
		
		return v;
	}
	
	
	
}
