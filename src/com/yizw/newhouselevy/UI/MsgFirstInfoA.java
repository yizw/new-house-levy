package com.yizw.newhouselevy.UI;

import java.sql.SQLException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cogent.core.msg.ByteHelper;
import com.cogent.core.msg.UploadCmd;
import com.cogent.core.msg.UploadMsg;
import com.cogent.core.msg.UploadParm;
import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.LogHelper;
import com.cogent.core.util.MyException;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.yizw.newhouselevy.Adapter.MsgFirstInfoAdapter;
import com.yizw.newhouselevy.Business.PDA_MessageBus;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PDA_Message;

public class MsgFirstInfoA extends OrmLiteBaseActivity<DatabaseHelper>{
	public class KEY{	
		/** 待上传消息id (int)*/
		public static final String in_id = "in_id";
		/** 表示返回用到的key*/
		public static final String re_name = "re_name";
	}
	
	public Activity activity;
	public PDA_Message entity;
	public UploadMsg msg;
	UploadParm[] parms;
	MsgFirstInfoAdapter adapter;
	
	final int requestCode_one = 1;
	final int requestCode_two = 2;
	
	public ListView listView;
	TextView txt;
	Button btn_;
	EditText edit_;
	Spinner sp_;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.msg_first_info);
		
        PublicBus.titleBarControl(this, "消息处理", true, null, true, null);
        initControl();
        operateIntent();
    }
	
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		txt = (TextView) findViewById(R.id.txt);
		listView =(ListView)findViewById(R.id.list);
		listView.setOnItemClickListener(new OnItemClick_list());
	}
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		int id = intent.getIntExtra(KEY.in_id, -1);
		
		try {
			PDA_MessageBus msgbus = new PDA_MessageBus(getHelper());
			entity = msgbus.queryForId(id);
			setEntityToUI(entity);
		} catch (SQLException e) {
			MyException myE = new MyException("加载消息信息出错了", e.getMessage(),e.getStackTrace());
			ExceptionHelper.Operate(myE, true, activity);
		}
	}
	
	Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	Spinner initSp(int id,String enmucode,boolean allowEmptyRow) throws MyException{
		Spinner sp = (Spinner)findViewById(id);
//		PublicBus.spinnerEnumBinding(sp,enmucode,allowEmptyRow,getHelper(),this);
		return sp;
	}
	
	public void setEntityToUI(PDA_Message entity){
		String content = entity.getContent();
		msg = UploadMsg.parse(content);
		UploadCmd cmd = msg.getCmds()[0];
		txt.setText(cmd.getCmdCode());
		parms = cmd.getParm();
		adapter = new MsgFirstInfoAdapter(activity, parms);
		listView.setAdapter(adapter);
	}
	
	public PDA_Message getEntity(){
		MsgFirstInfoAdapter adapter = (MsgFirstInfoAdapter)listView.getAdapter();
		UploadParm[] parms = adapter.getData();
		UploadCmd cmd = msg.getCmds()[0];
		cmd.clearParms();
		for(UploadParm parm:parms){
			cmd.addParameter(parm);
		}
		entity.setContent(msg.toString());
		return entity;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		case requestCode_one:
			requestCode_one(resultCode, data);
			break;
		case requestCode_two:
			requestCode_two(resultCode, data);
			break;

		default:
			break;
		}
	}
	
	private void requestCode_one(int resultCode, Intent data){
		if(resultCode!=RESULT_OK)
			return;
	}
	
	private void requestCode_two(int resultCode, Intent data){
		if(resultCode!=RESULT_OK)
			return;
	}
	
	class OnClick implements View.OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.btn_title_left:
				activity.finish();
				break;
			case R.id.btn_title_right:
				Onclick_btn_title_right();
				break;	
				
			default:
				break;
			}
		}
		
		void Onclick_btn_title_right() {
			try {
				getEntity();
				PDA_MessageBus bus = new PDA_MessageBus(getHelper());
				bus.update(entity);
				Toast.makeText(activity, "保存成功", Toast.LENGTH_SHORT).show();
				activity.finish();
			} catch (SQLException e) {
				MyException myE = new MyException("保存出错", e.getMessage(),e.getStackTrace());
				ExceptionHelper.Operate(myE, true, activity);
			}
		}
	}
	
	class OnItemClick_list implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			final UploadParm parm = parms[position];
			if(ByteHelper.exist(parm.getName()))//照片不处理
				return;
			
			LayoutInflater factory = LayoutInflater.from(activity);
            final View itemView = factory.inflate(R.layout.msg_first_info_item_edit, null);
            PublicBus.fillText(itemView, R.id.txt, parm.getName());
            PublicBus.fillEdit(itemView, R.id.edit, parm.getValue().toString());
                       
			new AlertDialog.Builder(activity)
			.setView(itemView)
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
	                	EditText edit = (EditText) itemView.findViewById(R.id.edit);
	                	parm.setValue(edit.getText().toString());
	                	adapter.notifyDataSetChanged();
                    }
                })
             .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).create().show();
		}
		
	}
	
	private static final int menu_cancel = 1;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, menu_cancel, 1, "不上传该消息").setIcon(R.drawable.ic_menu_close_clear_cancel);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		super.onOptionsItemSelected(item);
	
		switch (item.getItemId()) {
		case menu_cancel:
			menuItem_menu_cancel();
			break;
		}

		return false;
	}
		

	private void menuItem_menu_cancel() {
		try {
			getEntity();
			PDA_MessageBus bus = new PDA_MessageBus(getHelper());
			bus.uploadSuccess();
			LogHelper.writeToLogFlie("不上传消息:"+entity.getContent());
			Toast.makeText(activity, "操作成功", Toast.LENGTH_SHORT).show();
			activity.finish();
		} catch (SQLException e) {
			MyException myE = new MyException("操作出错", e.getMessage(),e.getStackTrace());
			ExceptionHelper.Operate(myE, true, activity);
		}
	}
}
