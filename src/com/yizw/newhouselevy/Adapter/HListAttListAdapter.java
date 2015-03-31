package com.yizw.newhouselevy.Adapter;

import java.util.LinkedHashMap;

import com.yizw.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;
import com.cogent.core.component.DataTableList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/*算单  附件一  列表   适配*/
public class HListAttListAdapter extends BaseAdapter {

	private Context context;
	private DataTableList list;
	
	
	public HListAttListAdapter(Context context,DataTableList list){
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
			v = vi.inflate(R.layout.hou_hlist_att_item, null);
		//	v.setClickable(true);
		}
		
		String ID = list.get(position).get("id").toString();
		
		//序号
	//	String name = list.get(position).get("name").toString();
		
		//名称
		String name = list.get(position).get("name").toString();
		//类别
		String type = list.get(position).get("type").toString();
		//单位
		String unit = list.get(position).get("unit").toString();
		//数量
		String num = list.get(position).get("num").toString();
		
		
		

		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(ID);
		

		//序号
	//	PublicBus.fillText(v, R.id.txt_name,name);		
		//名称
		PublicBus.fillText(v, R.id.txt_name,name);
		//类别
		PublicBus.fillText(v, R.id.txt_type,"类别："+type);
		//单位
		PublicBus.fillText(v, R.id.txt_unit,"单位："+unit);
		//数量
		PublicBus.fillText(v, R.id.txt_num,"数量："+num);
		
		return v;
	}
			
}
