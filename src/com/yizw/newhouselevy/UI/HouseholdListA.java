package com.yizw.newhouselevy.UI;

import android.annotation.SuppressLint;
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
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.Adapter.HouseholdListAdapter;
import com.yizw.newhouselevy.AsyncTask.HouseholdListAsyncTask;
import com.yizw.newhouselevy.Business.QueryHouseholdBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HouseholdListMainParm;
import com.yizw.newhouselevy.Entity.PageDomain;

/** 项目分户列表**/
@SuppressLint("InflateParams")
public class HouseholdListA extends OrmLiteBaseActivity<DatabaseHelper> {
	public class KEY{	
		public static final String in_id = "in_id";
		/**项目id */
		public static final String pid = "pid";		
	}	
			
	public HouseholdListA activity;
	public String in_id,pid;
	public static HouseholdListMainParm entity;
		
	EditText edit_name,edit_cardno;
	
	public ListView listView;
	public TextView txt_list_footer;	
	public HouseholdListAdapter adapter;
	public PageDomain pageDomain = new PageDomain();
	
	RelativeLayout tab1_right_Layout = null;
	ImageButton img_add = null;
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.householdlist);		
	//	PublicBus.titleBarControl(this,"分户列表",true,null,true,"");		
		initControl();
		loadListPageData();
		operateIntent();
		entity = new HouseholdListMainParm();
	}
	
	
	private void initControl(){
		activity = this;
		
		tab1_right_Layout = (RelativeLayout) findViewById(R.id.tab1_right_layout);
		ImageButton exit = (ImageButton) findViewById(R.id.tab1_actionbar_left_exit);
		exit.setOnClickListener(new OnClick());
		
		img_search = (ImageButton) findViewById(R.id.tab1_actionbar_search);
		img_search.setOnClickListener(new OnClick());
		
		img_add = (ImageButton) findViewById(R.id.tab1_actionbar_add);
		img_add.setOnClickListener(new OnClick());
		
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_cardno = (EditText) findViewById(R.id.edit_cardno);
		
		LayoutInflater inflater = LayoutInflater.from(this); 
		View footerView = inflater.inflate(R.layout.ty_list_footer, null);
		txt_list_footer = (TextView) footerView.findViewById(R.id.txt);
		initListPage(footerView);
	}
	
	
	private void operateIntent(){
		Intent intent = this.getIntent();
		in_id = intent.getStringExtra(KEY.in_id);
		entity = new HouseholdListMainParm();	
	}

	
	public void setEntityToUI(Object entity){		
	}
	
	public HouseholdListMainParm getEntity(){		
		entity.setHouseholdwner(edit_name.getText().toString());
	//	entity.setCardno(edit_cardno.getText().toString());
		return entity;
	}
	
	 void initListPage(View footerView){
			
			listView = (ListView) this.findViewById(R.id.list);
			listView.addFooterView(footerView);
			adapter = new HouseholdListAdapter(activity, new DataTableList());//实例化一个空数据项的适配器
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
			String householdwner = edit_name.getText().toString();
		//	String cardno = edit_cardno.getText().toString();
		    Intent intent = this.getIntent();
  			pid = intent.getStringExtra(KEY.in_id); 
	  //	HouseholdListAsyncTask task = new HouseholdListAsyncTask(activity,householdwner,cardno);
			HouseholdListAsyncTask task = new HouseholdListAsyncTask(activity,householdwner);
			task.execute();
		}
				
		class OnClick implements View.OnClickListener {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.tab1_actionbar_left_exit:
				//	DialogHelper.showCloseConfirm(activity, "返回上一级", "您确认要返回上一级？");
					activity.finish();
					break;
				case R.id.tab1_actionbar_search:
					Onclick_tab1_actionbar_search();
					break;					
				case R.id.tab1_actionbar_add:
					Intent intent = new Intent();
					intent.setClass(HouseholdListA.this, HouseholdInfoA.class);
					intent.putExtra(HouseholdInfoA.KEY.pid, pid);
					activity.startActivityForResult(intent,1);			
					break;					
				default:
					break;
				}
			}
			
			void Onclick_tab1_actionbar_search() {				
				getEntity();
				new QueryHouseholdBus(activity);
				
				//判断查询时分户姓名是否为空，若为空，则弹出提示框
		 //	    QueryHouseholdBus bus = new QueryHouseholdBus(activity);
		 //		String result = bus.check(entity);
		 //		if(!result.equals("")){
		 //			DialogHelper.showConfirm(activity,result);
		 //			return;
		 //		}
				pageDomain = new PageDomain();
				adapter = new HouseholdListAdapter(activity, new DataTableList());
				listView.setAdapter(adapter);	
				loadListPageData();	
			}			
		}
		
		
		  @Override
		  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			
			if(resultCode==RESULT_OK){
				pageDomain = new PageDomain();
				adapter = new HouseholdListAdapter(activity, new DataTableList());
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
					String householdid = ((TextView) view.findViewById(R.id.txt_id)).getText().toString();
					//打开分户信息页面
					Intent intent = new Intent();
					intent.setClass(HouseholdListA.this, HouseholdInfoA.class);
					intent.putExtra(HouseholdInfoA.KEY.in_id, householdid);
					activity.startActivityForResult(intent,1);			
				}else{
					if(GlobalVar.footerInfo[2].equals(txt_list_footer.getText().toString()))
						loadListPageData();
				}
			}
		}
}
