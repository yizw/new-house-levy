package com.yizw.newhouselevy.Adapter;

import java.util.List;
import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Entity.CONFIRM_PERSON;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/*国有个人确认表选择户籍人口列表*/
public class GYGRConfirmPersonAdapter extends BaseAdapter{

	private Context context;
	private List<CONFIRM_PERSON> list;
	public GYGRConfirmPersonAdapter(Context context,List<CONFIRM_PERSON> list){
		this.context=context;
		this.list=list;
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
	
	public void addItem(CONFIRM_PERSON person){
		list.add(person);
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
			v = vi.inflate(R.layout.gyconfirm_person_list_item, null);
		}
		
		CONFIRM_PERSON person= list.get(position);
		String name=person.getName();
		fillText(v,R.id.txt_confirm_1,name);
		String cardno=person.getCardno();
		fillText(v,R.id.txt_confirm_2,cardno);
		v.setTag(person);
		return v;
	}

	private void fillText(View v, int id, String text) {
		TextView textView = (TextView) v.findViewById(id);
		textView.setText(text == null ? "" : text);
	}
}
