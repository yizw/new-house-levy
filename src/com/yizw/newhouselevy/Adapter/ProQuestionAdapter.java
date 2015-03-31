package com.yizw.newhouselevy.Adapter;

import java.util.LinkedHashMap;
import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;
import com.cogent.core.component.DataTableList;
import com.cogent.core.util.StringHelper;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/*问题反馈 （项目）--问题列表适配*/
public class ProQuestionAdapter extends BaseAdapter{
	
	
	private Context context;
	private DataTableList list;
	
	public ProQuestionAdapter(Context context,DataTableList list){
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
			v = vi.inflate(R.layout.problem_feedback_question_item, null);
		}
		
		String ID = list.get(position).get("id").toString();
		
		
		String createddate = list.get(position).get("createddate").toString();//问题反馈提问时间
		String person_name = list.get(position).get("person_name").toString();//问题反馈提问人
		
		String content = list.get(position).get("content").toString();//问题反馈提问内容
		
		String parentid=  list.get(position).get("question_id").toString();
		
		TextView txt_id = (TextView) v.findViewById(R.id.txt_id);
		txt_id.setText(ID);
		
	//	TextView pro_question_id = (TextView) v.findViewById(R.id.pro_question_id);
	//	pro_question_id.setText(parentid);
		
		PublicBus.fillText(v, R.id.txt_1, createddate);
		PublicBus.fillText(v, R.id.txt_2, person_name);
		PublicBus.fillText(v, R.id.txt_3, content);
		
		PublicBus.fillText(v, R.id.pro_question_id, parentid);
		
		LinearLayout linear = (LinearLayout) v.findViewById(R.id.linear);
		if(StringHelper.isNullOrEmpty(parentid)){
			linear.setBackgroundResource(R.drawable.chat_left_bg);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.LEFT;
			linear.setLayoutParams(params);
		}else{
			linear.setBackgroundResource(R.drawable.chat_right_bg);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.RIGHT;
			linear.setLayoutParams(params);
		}
		return v;
	}
	

}
