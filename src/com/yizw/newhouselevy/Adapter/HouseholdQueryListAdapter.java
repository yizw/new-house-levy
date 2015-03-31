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
import com.yizw.newhouselevy.DAO.DatabaseHelper;

public class HouseholdQueryListAdapter extends BaseAdapter{
	
	private Context context;
	private DataTableList list;
	
	public HouseholdQueryListAdapter(Context context,DataTableList list,DatabaseHelper helper){
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
			v = vi.inflate(R.layout.householdlist_item, null);
		}
		
		String ID = list.get(position).get("id").toString();
		
		String houseowner = list.get(position).get("houseowner").toString();
		fillText(v,R.id.txt_name,houseowner);
			
			
		String cartno = list.get(position).get("cartno").toString();
		fillText(v,R.id.txt_cardno,cartno);
		
//		String photo =list.get(position).get("photo").toString();
//		ImageView(v,R.id.img_photo);
		
		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(ID);
			
		return v;
	}
	

	private void fillText(View v, int id, String text) {
		TextView textView = (TextView) v.findViewById(id);
		textView.setText(text == null ? "" : text);
	}
	

}
