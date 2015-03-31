package com.yizw.newhouselevy.Adapter;

import java.util.LinkedHashMap;

import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;
import com.cogent.core.component.DataTableList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TotalPushTableDetailAdapter extends BaseAdapter {

	private Context context;
	private DataTableList list;
	
	
	public TotalPushTableDetailAdapter(Context context,DataTableList list){
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
		v = vi.inflate(R.layout.total_pushtable_detail_item, null);
	    }
		        //总推进表ID
		        String ID = list.get(position).get("id").toString();
		        //总推进表各内容项状态
		        String status = list.get(position).get("status").toString();
		
		        //总推进表内容项
				String pushtablecontent = list.get(position).get("title").toString();
				//内容项完成时间
			    String finishtime_ = list.get(position).get("finishdate").toString();	
							
			    String finishtime = finishtime_.length()>9?finishtime_.substring(0,10):"";
				
				
				TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
				txt_id.setText(ID);
				
				String PID = list.get(position).get("pid")==null?"":list.get(position).get("pid").toString();
				TextView p_id = (TextView) v.findViewById(R.id.p_id);
				p_id.setText(PID);
				
				ImageView imgphoto = (ImageView) v.findViewById(R.id.img_photo);
			
				int i = Integer.parseInt(status);				
				switch (i) {
				case 0:
					//未完成
				  imgphoto.setBackgroundResource(R.drawable.pushtabledetail_unfinish);
				  break;					
				case 1:
					//进行中
				  imgphoto.setBackgroundResource(R.drawable.pushtabledetail_continue);
				  break;										
				case 2:
					//已完成
				  imgphoto.setBackgroundResource(R.drawable.pushtabledetail_finish);
				  break;					
				default :
				  imgphoto.setBackgroundResource(R.drawable.pushtabledetail_unfinish);
				  break;
				}
				
						
				//总推进表内容项
				PublicBus.fillText(v, R.id.txt_content,pushtablecontent);
				//内容项完成时间
				PublicBus.fillText(v, R.id.txt_finishtime,finishtime);
				
				return v;
	}
}
