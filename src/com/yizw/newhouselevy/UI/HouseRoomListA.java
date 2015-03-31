package com.yizw.newhouselevy.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cogent.core.component.DataTableList;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.Adapter.HouseRoomListAdapter;
import com.yizw.newhouselevy.AsyncTask.HouseRoomListAsyncTask;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HouseRoomListMainParm;
import com.yizw.newhouselevy.Entity.PageDomain;

/*房源--房间列表*/
public class HouseRoomListA extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public class KEY{	
		public static final String in_id = "in_id";
		public static final String listid = "listid";
		public static final String index = "index";
		
		public static final String pid = "pid";
		public static final String hid = "hid";
		
	}	
	

	public HouseRoomListA activity;
	
	public String in_id,listid,index,pid,hid;
	public static HouseRoomListMainParm entity;
	
	EditText edit_bno,edit_floor,edit_unit,edit_room;
	
	public ListView listView;
	public TextView txt_list_footer;
	
   // public String pid;
	
	public HouseRoomListAdapter adapter;
	
    public PageDomain pageDomain = new PageDomain();
    
    RelativeLayout tab3_right_Layout = null;
	ImageButton img_refresh = null;
	ImageButton img_search = null;
    
	public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.house_room_list);
		
   //   PublicBus.titleBarControl(this, "房间列表", true, null, true, "");     
  //    LoginBus.getLogin(this).setMainprojectnum(0);
        initControl();
        operateIntent();
        loadListPageData();
     
        entity = new HouseRoomListMainParm();
    }	
    
	private void initControl(){
		activity = this;
		
		tab3_right_Layout = (RelativeLayout) findViewById(R.id.tab3_right_layout);
		ImageButton exit = (ImageButton) findViewById(R.id.tab3_actionbar_left_exit);
		exit.setOnClickListener(new OnClick());
		
//		img_refresh = (ImageButton) findViewById(R.id.tab3_actionbar_refresh);
//		img_refresh.setOnClickListener(new OnClick());
//		img_refresh.setVisibility(View.GONE);
		
		
		img_search = (ImageButton) findViewById(R.id.tab3_actionbar_search);
		img_search.setOnClickListener(new OnClick());
		
		edit_bno = (EditText) findViewById(R.id.edit_bno);
		edit_unit = (EditText) findViewById(R.id.edit_unit);
		edit_floor = (EditText) findViewById(R.id.edit_floor);
		edit_room = (EditText) findViewById(R.id.edit_room);
		
		
		LayoutInflater inflater = LayoutInflater.from(this); 
		View footerView = inflater.inflate(R.layout.ty_list_footer, null);
		txt_list_footer = (TextView) footerView.findViewById(R.id.txt);
		
		initListPage(footerView);
	}
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		in_id = intent.getStringExtra(KEY.in_id);
		listid=intent.getStringExtra(KEY.listid);
		index=intent.getStringExtra(KEY.index);
		pid = intent.getStringExtra(KEY.pid);
		hid = intent.getStringExtra(KEY.hid);
		entity = new HouseRoomListMainParm();	
		
	}

	public void setEntityToUI(Object entity){		
	}
	
	public HouseRoomListMainParm getEntity(){		
		entity.setBno(edit_bno.getText().toString());
		entity.setUnit(edit_unit.getText().toString());
		entity.setFloor(edit_floor.getText().toString());
		entity.setRoom(edit_room.getText().toString());
		return entity;
	}
	
	
/*	private Button initBtn(int id) {
		Button btn = (Button) findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}*/

	void initListPage(View footerView) {

		listView = (ListView) this.findViewById(R.id.list);
		listView.addFooterView(footerView);
		adapter = new HouseRoomListAdapter(activity, new DataTableList());// 实例化一个空数据项的适配器
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClick_list());
		listView.setOnScrollListener(new ListView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// 注：因为增加了一个加载项，所以最后一项的索引刚好等于已加载项的数量

				if (adapter == null || pageDomain.itemTotal == -1)// 首次加载不自动引发
					return;

				int loadedNum = adapter.getCount();// 得到已经加载项的数量

				// 得到当前显示页的最后一项的索引
				pageDomain.lastItem = firstVisibleItem + visibleItemCount - 1;
				pageDomain.showInfo(activity);

				if (pageDomain.lastItem == loadedNum && !pageDomain.isloading) {

					// 如果数据总项不是初始值-1，并且加载项大于等于数据项总项，则表示加载已经完成
					if ((pageDomain.itemTotal != -1)
							&& (loadedNum >= pageDomain.itemTotal)) {
						txt_list_footer.setText(GlobalVar.footerInfo[1]);
					} else {
						loadListPageData();
					}
				}
			}
		});
	}

	/** 加载分页数据 */
	void loadListPageData() {
		String bno = edit_bno.getText().toString();
		String unit = edit_unit.getText().toString();
		String floor = edit_floor.getText().toString();
		String room = edit_room.getText().toString();
		
	 	Intent intent = this.getIntent();
	    pid = intent.getStringExtra(KEY.pid);
	    hid = intent.getStringExtra(KEY.hid);
		if(StringHelper.isNullOrEmpty(listid) || StringHelper.isNullOrEmpty(index)) {
			  listid="";
		} 
		HouseRoomListAsyncTask task = new HouseRoomListAsyncTask(activity,bno,unit,floor,room,listid);
		task.execute();
	}

	class OnClick implements View.OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			/*case R.id.btn_title_left:
				activity.finish();
				break;
			case R.id.btn_title_right:
				Onclick_btn_title_right();
				break;*/
				
			case R.id.tab3_actionbar_left_exit:
				Intent intent = new Intent();
				activity.setResult(Activity.RESULT_OK, intent);
				activity.finish();
				break;
				
//			case R.id.tab3_actionbar_refresh:
//				Onclick_tab2_actionbar_refresh();
//				break;
			case R.id.tab3_actionbar_search:
				Onclick_tab3_actionbar_search();
				break;	

			default:
				break;
			}
		}

//		void Onclick_tab3_actionbar_refresh() {
//			pageDomain = new PageDomain();
//			adapter = new HouseRoomListAdapter(activity, new DataTableList());
//			listView.setAdapter(adapter);
//			loadListPageData();
//		}
		
		void Onclick_tab3_actionbar_search() {				
			getEntity();
			
			pageDomain = new PageDomain();
			adapter = new HouseRoomListAdapter(activity, new DataTableList());
			listView.setAdapter(adapter);	
			loadListPageData();	
		}		
		
	}
		
	class OnItemClick_list implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// 注：因为增加了一个加载项，所以最后一项的索引刚好等于已加载项的数量
			int loadedNum = adapter.getCount();// 得到已经加载项的数量
			if (loadedNum > position) {
				// 点击的是数据项
				TextView txt_id = (TextView) view.findViewById(R.id.txt_id);
				String houseroomid = txt_id.getText().toString();

				Intent intent = new Intent(activity, HouseRoomInfoA.class);
				intent.putExtra(HouseRoomInfoA.KEY.in_id, houseroomid);
				intent.putExtra(HouseRoomInfoA.KEY.listid, listid);
				intent.putExtra(HouseRoomInfoA.KEY.index, index);
				
				if(StringHelper.isNullOrEmpty(listid) || StringHelper.isNullOrEmpty(index)){
					activity.startActivity(intent);
				}else{
					activity.startActivityForResult(intent,1);	
				}
				
				//activity.startActivityForResult(intent,1);		

			} else {
				if (GlobalVar.footerInfo[2].equals(txt_list_footer.getText()
						.toString()))
					loadListPageData();
			}

		}
	}

	
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK){
//			pageDomain = new PageDomain();
//			adapter = new HouseRoomListAdapter(activity, new DataTableList());
//			listView.setAdapter(adapter);	
//			loadListPageData();	
			//3月20日修改，选房后，房间列表页面finish掉，返回OK值给房开商列表，房开商列表也要finish掉
			Intent intent = new Intent();
			activity.setResult(Activity.RESULT_OK, intent);
			activity.finish();
		}
	}
	
	
}

