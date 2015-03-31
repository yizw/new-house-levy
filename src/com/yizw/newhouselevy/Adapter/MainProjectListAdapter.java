package com.yizw.newhouselevy.Adapter;

import java.util.LinkedHashMap;

import com.yizw.newhouselevy.Business.PublicBus;
import com.example.newhouselevy.R;
import com.cogent.core.component.DataTableList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainProjectListAdapter extends BaseAdapter {
	
	private Context context;
	private DataTableList list;
	
	public MainProjectListAdapter(Context context,DataTableList list){
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
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.main_projectlist_item, null);
		}
		
	//	HouseList houseList = HouseListHelper.getHouseListData(list);
		
		
		String ID = list.get(position).get("id").toString();		
		//项目标题
		String projecttitle = list.get(position).get("pname").toString();		
		//开发区分管领导
		String specleader = list.get(position).get("specleader").toString();		
		//中心分管领导
		String centerleader = list.get(position).get("centerleader").toString();		
		//项目分管领导
		String projectmanage = list.get(position).get("projectmanage").toString();
		//项目部部长
	//	String projectdirector = list.get(position).get("projectdirector").toString();
		String minister = list.get(position).get("minister").toString();
		
		//项目起始时间
		String projectdate_ = list.get(position).get("starttime").toString();	
		
		String projectdate = projectdate_.length()>9?projectdate_.substring(0,10):"";
		
		//摸底户数
		String housenumber = list.get(position).get("housenumber").toString();	
		//已签户数
		String signnumber = list.get(position).get("signnumber").toString();
		
	//	String admin = list.get(position).get("admin").toString();

		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(ID);
   //  	txt_id.setTag(admin);
		
		
		
		//项目标题
		PublicBus.fillText(v, R.id.txt_projecttitle,projecttitle);		
		//开发区领导
	//	PublicBus.fillText(v, R.id.txt_specleader,txtspecleader);
		PublicBus.fillText(v, R.id.special_leader,specleader);
		//中心分管领导
	//	PublicBus.fillText(v, R.id.txt_centerleader,txtcenterleader);
		PublicBus.fillText(v, R.id.center_leader,centerleader);
		//项目分管领导
	//	PublicBus.fillText(v, R.id.txt_projectmanage,txtprojectmanage);
		PublicBus.fillText(v, R.id.project_manage,projectmanage);
		//项目部部长
	//	PublicBus.fillText(v, R.id.txt_projectdirector,txtprojectdirector);
	//	PublicBus.fillText(v, R.id.project_director,projectdirector);
		PublicBus.fillText(v, R.id.project_director,minister);
		//项目起始时间
	//	PublicBus.fillText(v, R.id.txt_projectdate,txtprojectdate);
		PublicBus.fillText(v, R.id.project_date,projectdate);
		
	//	String temp = DateHelper.dateToString(projectdate,DateHelper.formatTwo);
		
		//摸底户数
	//	PublicBus.fillText(v, R.id.txt_guessnumber,txtguessnumber);
		PublicBus.fillText(v, R.id.guess_number,housenumber);
		//已签户数
	//	PublicBus.fillText(v, R.id.txt_signnumber,txtsignnumber);
		PublicBus.fillText(v, R.id.sign_number,signnumber);
		return v;
	}
	
}
