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
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.Adapter.HouConfirmAttListAdapter;
import com.yizw.newhouselevy.AsyncTask.HouConfirmAttListAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PageDomain;

/*分户房屋调查确认表-附件1  信息列表页面*/
public class HouConfirmAttListA extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public class KEY{	
		public static final String in_id = "in_id";
		/*分户id*/
		public static final String hid = "hid";
		/*项目id*/
		public static final String pid = "pid";
		
		/*附件类别 0附件一，1附件二，2附件三*/
		public static final String temptype = "temptype";
		/*确认表状态 */
		public static final String c_status = "c_status";
		
	}	
	
	public HouConfirmAttListA activity;
	
	public String hid,pid,in_id,temptype,c_status;
	
	public ListView listView;
	public TextView txt_list_footer;
	
	public HouConfirmAttListAdapter adapter;
	
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
		setContentView(R.layout.hou_confirm_att_list);		
		PublicBus.titleBarControl(this, "附件表", true, null, true, null);
		initControl();
        loadListPageData();
	}
	
	@SuppressLint("InflateParams")
	private void initControl() {
		Intent intent = this.getIntent();
		c_status = intent.getStringExtra(KEY.c_status);
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
  			adapter = new HouConfirmAttListAdapter(activity, new DataTableList());//实例化一个空数据项的适配器
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
  			hid = intent.getStringExtra(KEY.hid); 
  			pid = intent.getStringExtra(KEY.pid); 
  			temptype = intent.getStringExtra(KEY.temptype);
  	//		status = intent.getStringExtra(KEY.status);
  			HouConfirmAttListAsyncTask task = new HouConfirmAttListAsyncTask(activity);
  			task.execute();
  			
  			if(!StringHelper.isNullOrEmpty(c_status)&&"2".equals(c_status)){
  				Button btn = (Button)findViewById(R.id.btn_title_right);
  				btn.setVisibility(View.GONE);
  			}
  			
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
				Intent intent = new Intent(activity, HouConfirmAttInfoA.class);
				intent.putExtra(HouConfirmAttInfoA.KEY.attconfirmid, in_id);
				
				intent.putExtra(HouConfirmAttInfoA.KEY.hid, hid);
				intent.putExtra(HouConfirmAttInfoA.KEY.pid, pid);
				intent.putExtra(HouConfirmAttInfoA.KEY.temptype, temptype);
				intent.putExtra(HouConfirmAttInfoA.KEY.c_status, c_status);
				startActivityForResult(intent, 0);
			} 	 
     		
     		
     	}
  		
  	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK){
			pageDomain = new PageDomain();
			adapter = new HouConfirmAttListAdapter(activity, new DataTableList());
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
					
			Intent intent = new Intent(activity,HouConfirmAttInfoA.class);						
			intent.putExtra(HouConfirmAttInfoA.KEY.in_id, att_id);
			intent.putExtra(HouConfirmAttInfoA.KEY.c_status,c_status);
			activity.startActivityForResult(intent,1);				
			}else{
			if(GlobalVar.footerInfo[2].equals(txt_list_footer.getText().toString()))
			loadListPageData();
			   }			
			}
		}
}