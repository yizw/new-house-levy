package com.yizw.newhouselevy.Adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Entity.CONFIRM_PERSON;

/*集体个人确认表选择户籍人口列表*/
public class JTGRConfirmPersonAdapter extends BaseAdapter{

	private Context context;
	private List<CONFIRM_PERSON> list;
	public JTGRConfirmPersonAdapter(Context context,List<CONFIRM_PERSON> list){
		this.context=context;
		this.list=list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public void addItem(CONFIRM_PERSON person){
		list.add(person);
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
			v = vi.inflate(R.layout.jtconfirm_person_list_item, null);
		}
		CONFIRM_PERSON person= list.get(position);
		String name=list.get(position).getName();
		fillText(v,R.id.txt_confirm_1,name);
		String cardno=list.get(position).getCardno();
		fillText(v,R.id.txt_confirm_2,cardno);
		v.setTag(person);
		return v;
	}

	private void fillText(View v, int id, String text) {
		TextView textView = (TextView) v.findViewById(id);
		textView.setText(text == null ? "" : text);
	}
}
