package com.yizw.newhouselevy.Adapter;

import java.util.LinkedHashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cogent.core.component.DataTableList;
import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;

public class HouseholdCoptSheetAdapter extends BaseAdapter {

	private Context context;
	private DataTableList list;
	
	
	public HouseholdCoptSheetAdapter(Context context,DataTableList list){
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
	
	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.household_coptsheet_list_item, null);
		}
		
		String ID = list.get(position).get("id").toString();
				
		//补偿计算单合同编号前部分
		String coptsheettitle1 = list.get(position).get("mainnum").toString();
		//补偿计算单合同编号后部分
		String coptsheettitle2 = list.get(position).get("subnum").toString();
		//算单类型
	//	String contracttype = list.get(position).get("contracttype").toString();
		

		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(ID);
		
		String HOUSEHOLD_TYPE = list.get(position).get("household_type").toString();
		TextView household_type = (TextView) v.findViewById(R.id.household_type);
		household_type.setText(HOUSEHOLD_TYPE);
		
		String PROJECT_TYPE = list.get(position).get("project_type").toString();
		TextView project_type = (TextView) v.findViewById(R.id.project_type);
		project_type.setText(PROJECT_TYPE);
		
		String CONTRACTTYPE = list.get(position).get("contracttype").toString();
		TextView contracttype = (TextView) v.findViewById(R.id.contracttype);
		contracttype.setText(CONTRACTTYPE);
				
		
		//补偿计算单标题
		PublicBus.fillText(v, R.id.txt_coptsheettitle1,coptsheettitle1+"-");
		PublicBus.fillText(v, R.id.txt_coptsheettitle2,coptsheettitle2);
		PublicBus.fillText(v, R.id.txt_contracttype,CONTRACTTYPE.equals("0")?"产权调换":"货币补偿");
		
		
		return v;
	}
	
	
}
