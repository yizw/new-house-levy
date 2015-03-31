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
import android.widget.TextView;

/*资料库*/
public class DataBaseListAdapter extends BaseAdapter{
	
	
	private Context context;
	private DataTableList list;
	
	public DataBaseListAdapter(Context context,DataTableList list){
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
			v = vi.inflate(R.layout.database_list_item, null);
		}
		
		String ID = list.get(position).get("id").toString();
		
		
		//资料库文件标题
		String title = list.get(position).get("title").toString();
		//资料库发布时间
		String createddate = list.get(position).get("createddate").toString();
		//资料库发布人
		String creator = list.get(position).get("creator").toString();
		
		
		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(ID);
				
		//资料库文件标题
		PublicBus.fillText(v, R.id.title,title);
		//资料库发布时间
		PublicBus.fillText(v, R.id.createddate,createddate);
		//资料库发布人
		PublicBus.fillText(v, R.id.creator,creator);
		
		return v;
	}
	

}
