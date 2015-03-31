package com.yizw.newhouselevy.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cogent.core.component.DataTableList;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.Adapter.HouseNameListAdapter;
import com.yizw.newhouselevy.AsyncTask.HouseNameListAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PageDomain;

/*房源--房开商列表*/
public class HouseNameListA extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public class KEY{	
		public static final String in_id = "in_id";
		public static final String in_index = "in_index"; 
		public static final String pid = "pid"; 
		
	}	
    public String index,pid,in_id,in_index;
    public String listid;
	public HouseNameListA activity;
	public ListView listView;
	public TextView txt_list_footer;
	
   // public String pid;
	
	public HouseNameListAdapter adapter;
	
    public PageDomain pageDomain = new PageDomain();
    
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
        setContentView(R.layout.house_name_list);
		
        PublicBus.titleBarControl(this, "房源列表", true, null, true, "");     
  //    LoginBus.getLogin(this).setMainprojectnum(0);
        initControl();
        loadListPageData();
    }	
    
	private void initControl(){
		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
		Intent intent = this.getIntent();
		listid = intent.getStringExtra(KEY.in_id);
		index = intent.getStringExtra(KEY.in_index);
		pid = intent.getStringExtra(KEY.pid);
		
		
		LayoutInflater inflater = LayoutInflater.from(this); 
		View footerView = inflater.inflate(R.layout.ty_list_footer, null);
		txt_list_footer = (TextView) footerView.findViewById(R.id.txt);
		
		initListPage(footerView);
	}
	
	private Button initBtn(int id){
		Button btn = (Button)findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
		

	 void initListPage(View footerView){
			
			listView = (ListView) this.findViewById(R.id.list);
			listView.addFooterView(footerView);
			adapter = new HouseNameListAdapter(activity, new DataTableList());//实例化一个空数据项的适配器
			listView.setAdapter(adapter);		
			listView.setOnItemClickListener(new OnItemClick_list());
			listView.setOnScrollListener(new ListView.OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {			
				}
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {				
					//注：因为增加了一个加载项，所以最后一项的索引刚好等于已加载项的数量
					
					if(adapter == null||pageDomain.itemTotal==-1)//首次加载不自动引发
						return;
					
					int loadedNum = adapter.getCount();//得到已经加载项的数量					
					
					//得到当前显示页的最后一项的索引
					pageDomain.lastItem = firstVisibleItem + visibleItemCount - 1;
					pageDomain.showInfo(activity);			
									
					if(pageDomain.lastItem==loadedNum && !pageDomain.isloading){
						
						//如果数据总项不是初始值-1，并且加载项大于等于数据项总项，则表示加载已经完成
						if((pageDomain.itemTotal!=-1)&&(loadedNum >= pageDomain.itemTotal)){					
							txt_list_footer.setText(GlobalVar.footerInfo[1]);
						}else{
							loadListPageData();
						}
					}												
				}
			});
		}
    
	 /** 加载分页数据*/
		void loadListPageData(){
			Intent intent = this.getIntent();
			
  			pid = intent.getStringExtra(KEY.pid); 
			HouseNameListAsyncTask task = new HouseNameListAsyncTask(activity);
			task.execute();
		}
	 
		class OnClick implements View.OnClickListener {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.btn_title_left:
					Intent intent = new Intent();
					activity.setResult(Activity.RESULT_OK, intent);
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
				pageDomain = new PageDomain();
				adapter = new HouseNameListAdapter(activity, new DataTableList());
				listView.setAdapter(adapter);	
				loadListPageData();
			}
		}
	 
		
		
		class OnItemClick_list implements OnItemClickListener{
		
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
					//注：因为增加了一个加载项，所以最后一项的索引刚好等于已加载项的数量
					int loadedNum = adapter.getCount();//得到已经加载项的数量
					if(loadedNum > position){
						//点击的是数据项
						TextView txt_id = (TextView) view.findViewById(R.id.txt_id);
						String housenameid = txt_id.getText().toString();	
												
//						Intent intent = new Intent(activity,HouseBnoListA.class);						
//						intent.putExtra(HouseBnoListA.KEY.in_id, housenameid);
						
						Intent intent = new Intent(activity,HouseRoomListA.class);						
						intent.putExtra(HouseRoomListA.KEY.hid, housenameid);
						intent.putExtra(HouseRoomListA.KEY.listid, listid);
						intent.putExtra(HouseRoomListA.KEY.index, index);
						intent.putExtra(HouseRoomListA.KEY.pid, pid);
					//	intent.putExtra(HouseBnoListA.KEY.pid, pid);
						if(StringHelper.isNullOrEmpty(listid) || StringHelper.isNullOrEmpty(index)){
							activity.startActivity(intent);
						}else{
							activity.startActivityForResult(intent,1);	
						}
								
					}else{
						if(GlobalVar.footerInfo[2].equals(txt_list_footer.getText().toString()))
							loadListPageData();
					}			
					
				}
			}
		
		/*protected void OnActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);

			switch (requestCode) {
			case 1:
				activity.setResult(Activity.RESULT_OK, null);
				activity.finish();
				break;
			 
			default:
				// RequestCode_photo(requestCode,resultCode, data);
				break;
			}
		}*/
		
		
		 @Override
		  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			
			if(resultCode==RESULT_OK){
		    //3月20日修改，选房后，房间列表页面finish掉，返回OK值给房开商列表，房开商列表也要finish掉，返回OK值给算单
				Intent intent = new Intent();
				activity.setResult(Activity.RESULT_OK, intent);
				activity.finish();
			}
		}
		
		
}

