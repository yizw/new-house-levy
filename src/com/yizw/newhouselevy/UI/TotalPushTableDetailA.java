package com.yizw.newhouselevy.UI;

import android.annotation.SuppressLint;
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
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.Adapter.TotalPushTableDetailAdapter;
import com.yizw.newhouselevy.AsyncTask.TotalPushTableDetailAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PageDomain;

/**征收项目总推进表详细信息**/
public class TotalPushTableDetailA extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public class KEY{	
		public static final String in_id = "in_id";
		public static final String pid = "pid";
		//从项目总览入口进入，赋值为1，只做查看，将保存按钮隐藏
		public static final String hide = "hide";
	}	
	
    public TotalPushTableDetailA activity;
    
    public String pid,hide;
    
 //   public Object entity;
	
    public ListView listView;
	public TextView txt_list_footer;
	
	public TotalPushTableDetailAdapter adapter;
	
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.total_pushtable_detail);		
		PublicBus.titleBarControl(this, "总推进表详细", true, null, true, null);
		initControl();
        loadListPageData();
	}
	
	@SuppressLint("InflateParams")
	private void initControl() {
        Intent intent = this.getIntent();
		hide = intent.getStringExtra(KEY.hide);
		activity = this;

		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);

		LayoutInflater inflater = LayoutInflater.from(this);
		View footerView = inflater.inflate(R.layout.ty_list_footer, null);
		txt_list_footer = (TextView) footerView.findViewById(R.id.txt);

		initListPage(footerView);
	}
	   
     Button initBtn(int id){
	     Button btn = (Button)findViewById(id);
	     btn.setOnClickListener(new OnClick());
	     return btn;
        }

    void initListPage(View footerView){
  			
  			listView = (ListView) this.findViewById(R.id.list);
  			listView.addFooterView(footerView);
  			adapter = new TotalPushTableDetailAdapter(activity, new DataTableList());//实例化一个空数据项的适配器
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
  			pid = intent.getStringExtra(KEY.in_id); 
  			TotalPushTableDetailAsyncTask task = new TotalPushTableDetailAsyncTask(activity);
  			task.execute();
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
				pageDomain = new PageDomain();
				adapter = new TotalPushTableDetailAdapter(activity, new DataTableList());
				listView.setAdapter(adapter);	
				loadListPageData();
			}     	 
     	}
  		
  	   @Override
		  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			
			if(resultCode==RESULT_OK){
				pageDomain = new PageDomain();
				adapter = new TotalPushTableDetailAdapter(activity, new DataTableList());
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
					String projectid = txt_id.getText().toString();	
    				
					TextView p_id = (TextView) view.findViewById(R.id.p_id);
					String pid = p_id.getText().toString();	
					
					Intent intent = new Intent(activity,TotalPushTableInfoA.class);						
					intent.putExtra(TotalPushTableInfoA.KEY.in_id, projectid);
					intent.putExtra(TotalPushTableInfoA.KEY.pid, pid);
					intent.putExtra(TotalPushTableInfoA.KEY.hide, hide);
					activity.startActivityForResult(intent,1);
				}else{
					if(GlobalVar.footerInfo[2].equals(txt_list_footer.getText().toString()))
						loadListPageData();
				}			
			}
		}
}
