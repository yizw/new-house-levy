package com.yizw.newhouselevy.Adapter;

import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Business.PublicBus;
import com.cogent.core.msg.ByteHelper;
import com.cogent.core.msg.UploadParm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MsgFirstInfoAdapter extends BaseAdapter {
	private Context context;
	private UploadParm[] parms;
	
	public MsgFirstInfoAdapter(Context context,UploadParm[] parms){
		this.context = context;
		this.parms = parms;
	}
	
	public UploadParm[] getData(){
		return parms;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return parms.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return parms[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null){
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.msg_first_info_item, null);
		}
		
		final UploadParm parm = parms[position];	
		PublicBus.fillText(v, R.id.txt_left, parm.getName());
		if(!ByteHelper.exist(parm.getName()))//照片不处理
			PublicBus.fillText(v, R.id.txt_right, parm.getValue().toString());

		return v;
	}

}
