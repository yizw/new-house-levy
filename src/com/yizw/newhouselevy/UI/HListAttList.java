package com.yizw.newhouselevy.UI;

import android.annotation.SuppressLint;
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


import com.yizw.newhouselevy.GlobalVar;
import com.example.newhouselevy.R;
import com.yizw.newhouselevy.Adapter.HListAttListAdapter;
import com.yizw.newhouselevy.AsyncTask.HListAttListAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PageDomain;
import com.cogent.core.component.DataTableList;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;

/*算单 附件一  列表*/
public class HListAttList extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public class KEY{
		/** id*/
		public static final String in_id = "in_id";
		/** 算单id*/
		public static final String listid = "listid";
		/*分户id*/
		public static final String hid = "hid";
		/*项目id*/
		public static final String pid = "pid";
		/*附件类别 0附件一，1附件二，2附件三*/
		public static final String temptype = "temptype";
		
		public static final String RE_ALLMONEY ="RE_ALLMONEY";
		/** 算单状态*/
		public static final String h_status = "h_status";
		
	}
		
	
	public HListAttList activity;
	
	public String listid,in_id,hid,pid,temptype,h_status;
	
	public ListView listView;
	public TextView txt_list_footer;
	
	public TextView allmoneyview;
	
	public HListAttListAdapter adapter;
	
	public PageDomain pageDomain = new PageDomain();

	public String allmoney;
	
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
		setContentView(R.layout.hou_hlist_att_list);		
		PublicBus.titleBarControl(this, "附件表", true, null, false, null);
		initControl();
        loadListPageData();
	}
	
	@SuppressLint("InflateParams")
	private void initControl() {
		Intent intent = this.getIntent();
		h_status = intent.getStringExtra(KEY.h_status);

		activity = this;
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);

		LayoutInflater inflater = LayoutInflater.from(this);
		View footerView = inflater.inflate(R.layout.ty_list_footer, null);
		txt_list_footer = (TextView) footerView.findViewById(R.id.txt);

		allmoneyview = (TextView) this.findViewById(R.id.allmoney);

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
  			adapter = new HListAttListAdapter(activity, new DataTableList());//实例化一个空数据项的适配器
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
  			listid = intent.getStringExtra(KEY.listid); 
  			hid = intent.getStringExtra(KEY.hid); 
  			pid = intent.getStringExtra(KEY.pid); 
  			temptype = intent.getStringExtra(KEY.temptype);
  			HListAttListAsyncTask task = new HListAttListAsyncTask(activity);
  			task.execute();
  		}
     
  		class OnClick implements View.OnClickListener {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.btn_title_left:
					Intent intent = new Intent();
					intent.putExtra(KEY.RE_ALLMONEY,allmoney); 
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
				adapter = new HListAttListAdapter(activity, new DataTableList());
				listView.setAdapter(adapter);	
				loadListPageData();
			}     	 
     	}
  		
  	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK){
			pageDomain = new PageDomain();
			adapter = new HListAttListAdapter(activity, new DataTableList());
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
			String att_id = txt_id.getText().toString();	
					
			Intent intent = new Intent(activity,HListAttInfo.class);						
			intent.putExtra(HListAttInfo.KEY.in_id, att_id);
			intent.putExtra(HListAttInfo.KEY.listid, listid);
			intent.putExtra(HListAttInfo.KEY.h_status, h_status);
			activity.startActivityForResult(intent,1);				
			}else{
			if(GlobalVar.footerInfo[2].equals(txt_list_footer.getText().toString()))
			loadListPageData();
			   }			
			}
		}
}