package com.yizw.newhouselevy.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cogent.core.component.DataTableList;
import com.cogent.core.util.StringHelper;
import com.example.newhouselevy.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yizw.newhouselevy.GlobalVar;
import com.yizw.newhouselevy.Adapter.HouseholdCoptSheetAdapter;
import com.yizw.newhouselevy.AsyncTask.HouHlistCreatAsyncTask;
import com.yizw.newhouselevy.AsyncTask.HouseholdCoptSheetAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.HOU_HOUSEHOLD;
import com.yizw.newhouselevy.Entity.PageDomain;

/**分户--补偿计算单列表 **/
@SuppressLint("NewApi")
 public class HouseholdCoptSheetA extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public class KEY{	
		public static final String in_id = "in_id";
		public static final String coptid = "coptid";
		/*分户id*/
		public static final String hid = "hid";	
		/*分户所属的项目id*/
		public static final String pid = "pid";
		
		/*分户房屋调查确认表id*/
		public static final String c_id = "c_id";
		/*分户房屋调查确认表状态值*/
		public static final String c_status = "c_status";
		
		/*分户   算单id*/
		public static final String hlist_id = "hlist_id";
		
		public static final String hliststatus = "hliststatus";
		
		/*分户类别  个人或企业*/
		public static final String h_type = "h_type";
		/*项目类别 国有或集体 */
		public static final String p_type = "p_type";	
	}
	
	public String coptid,hid,pid,c_id,c_status,household_type,project_type,hlist_id,hliststatus;
	
	public HouseholdCoptSheetA activity;
	
	public HOU_HOUSEHOLD entity;

	
	RelativeLayout tab1_right_Layout = null;
	
	public ListView listView;
	public TextView txt_list_footer;
	
	public HouseholdCoptSheetAdapter adapter;
	
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
        setContentView(R.layout.household_coptsheet_list);
        PublicBus.titleBarControl(this, "补偿计算单", true, null, true, null);     
  //    LoginBus.getLogin(this).setMainprojectnum(0);
        initControl();
        loadListPageData();
    }	
    
	@SuppressLint("InflateParams")
	private void initControl(){
		activity = this;
		tab1_right_Layout = (RelativeLayout) findViewById(R.id.tab1_right_layout);		
		
		initBtn(R.id.btn_title_left);
		initBtn(R.id.btn_title_right);
		
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
			adapter = new HouseholdCoptSheetAdapter(activity, new DataTableList());//实例化一个空数据项的适配器
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
  			
		    hid = intent.getStringExtra(KEY.in_id); 
		    pid = intent.getStringExtra(KEY.pid);
		    
  			household_type = intent.getStringExtra(KEY.h_type); 
  			project_type = intent.getStringExtra(KEY.p_type); 
  			
  			c_id = intent.getStringExtra(KEY.c_id); 
  			c_status = intent.getStringExtra(KEY.c_status); 
  			
  			hliststatus = intent.getStringExtra(KEY.hliststatus);
  			
  			hlist_id=intent.getStringExtra(KEY.hlist_id); 
  			
			HouseholdCoptSheetAsyncTask task = new HouseholdCoptSheetAsyncTask(activity);
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
					Onclick_btn_title_right(v);
					break;		
				default:
					break;
				}
			}
			void Onclick_btn_title_right(View v){
				onPopupButtonClick(v);
			}		
		}
	 		
		public void onPopupButtonClick(View button)
		{
			PopupMenu popup_add = new PopupMenu(activity, tab1_right_Layout);					 
			getMenuInflater().inflate(R.menu.coptsheet_popup_menu_add, popup_add.getMenu());			
			popup_add.setOnMenuItemClickListener(
					new PopupMenu.OnMenuItemClickListener()					
			{
				@Override
				public boolean onMenuItemClick(MenuItem item)
				{
					switch (item.getItemId())
					{
						case R.id.add_proexchange:
							Onclick_btn_1();
							break;
						case R.id.add_montarycopt:
							Onclick_btn_2();
							break;
						default: 
							break;
					}
					return true;
				}
			});
			popup_add.show();			
		   }
		
		//产权调换
		void Onclick_btn_1() {
			if(newactivity()){			
			//Household_type分户类型（0个人，1企业）;Project_type项目类型（0国有，1集体）
			
/*			if ("0".equals(household_type) && "0".equals(project_type)) {
				Intent intent = new Intent(activity, GYGRProExchangeA.class);
				intent.putExtra(GYGRProExchangeA.KEY.in_id, coptid);
				activity.startActivityForResult(intent, 1);
			} else if ("1".equals(household_type) && "0".equals(project_type)) {
				Intent intent = new Intent(activity, GYQYProExchangeA.class);
				intent.putExtra(GYQYProExchangeA.KEY.in_id, coptid);
				activity.startActivityForResult(intent, 1);
			} else if ("0".equals(household_type) && "1".equals(project_type)) {
				Intent intent = new Intent(activity, JTGRProExchangeA.class);
				intent.putExtra(JTGRProExchangeA.KEY.in_id, coptid);
				activity.startActivityForResult(intent, 1);
			} else if ("1".equals(household_type) && "1".equals(project_type)) {
				Intent intent = new Intent(activity, JTQYProExchangeA.class);
				intent.putExtra(JTQYProExchangeA.KEY.in_id, coptid);
				activity.startActivityForResult(intent, 1);
			}*/
				
			HouHlistCreatAsyncTask task = new HouHlistCreatAsyncTask(activity, "0");
			task.execute();
			loadListPageData();
		  }
			
		  }
		
		//货币补偿
		 void Onclick_btn_2(){	
			 if(newactivity()){
			//Household_type分户类型（0个人，1企业）;Project_type项目类型（0国有，1集体）
				 
//			if("0".equals(household_type)
//			&& "0".equals(project_type)){
//			Intent intent = new Intent(activity, GYGRMonetaryCoptA.class);
//			intent.putExtra(GYGRMonetaryCoptA.KEY.in_id, coptid);
//			activity.startActivityForResult(intent,1);
//			}				
//	    	else if("1".equals(household_type)
//	    	&& "0".equals(project_type)){
//			Intent intent = new Intent(activity, GYQYMonetaryCoptA.class);
//			intent.putExtra(GYQYMonetaryCoptA.KEY.in_id, coptid);
//			activity.startActivityForResult(intent,1);
//		    }
//		    else if("0".equals(household_type)
//		    && "1".equals(project_type)){
//			Intent intent = new Intent(activity, JTGRMonetaryCoptA.class);
//			intent.putExtra(JTGRMonetaryCoptA.KEY.in_id, coptid);
//			activity.startActivityForResult(intent,1);
//		    }
//		    else if("1".equals(household_type)
//		    && "1".equals(project_type)){
//			Intent intent = new Intent(activity, JTQYMonetaryCoptA.class);
//			intent.putExtra(JTQYMonetaryCoptA.KEY.in_id, coptid);
//			activity.startActivityForResult(intent,1);
//		    }
		
			HouHlistCreatAsyncTask task = new HouHlistCreatAsyncTask(activity,"1");
			task.execute();
			loadListPageData();
			
		   }
		  }
		 
		 
		 @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			
			if(resultCode==RESULT_OK){
				pageDomain = new PageDomain();
				adapter = new HouseholdCoptSheetAdapter(activity, new DataTableList());
				listView.setAdapter(adapter);	
				loadListPageData();
			}
		}
		 
		
		 
	    boolean newactivity(){
				if(StringHelper.isNullOrEmpty(c_id)){
					Toast.makeText(activity, "请先新增确认表！", Toast.LENGTH_LONG).show();
					return false;
				}
//				else if(!StringHelper.isNullOrEmpty(c_id) && "1".equals(hliststatus)){
//					Toast.makeText(activity, "你有未提交的算单，不能再新增算单了！", Toast.LENGTH_LONG).show();
//					return false;
//				}
				else if(!StringHelper.isNullOrEmpty(c_id) && "1".equals(c_status) && "1".equals(hliststatus)){
					Toast.makeText(activity, "提交确认表后才可新增多张算单！", Toast.LENGTH_LONG).show();
					return false;
				}
				return true;
			}
		 
			
		class OnItemClick_list implements OnItemClickListener{		
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
					//注：因为增加了一个加载项，所以最后一项的索引刚好等于已加载项的数量
					int loadedNum = adapter.getCount();//得到已经加载项的数量
					if(loadedNum > position){
						//点击的是数据项
						TextView txt_id = (TextView) view.findViewById(R.id.txt_id);
						String coptid = txt_id.getText().toString();
//						TextView h_id = (TextView) view.findViewById(R.id.h_id);
//						String hid = h_id.getText().toString();	
					   //Household_type分户类型（0个人，1企业）;Project_type项目类型（0国有，1集体）;Contracttype算单类型（0产权调换，1货币补偿）
						String contracttype = ((TextView) view.findViewById(R.id.contracttype)).getText().toString();
						String household_type = ((TextView) view.findViewById(R.id.household_type)).getText().toString();
						String project_type = ((TextView) view.findViewById(R.id.project_type)).getText().toString();
						
						if("0".equals(household_type)					 
						&& "0".equals(project_type)
						&& "0".equals(contracttype)){
						Intent intent = new Intent(activity, GYGRProExchangeA.class);
						intent.putExtra(GYGRProExchangeA.KEY.in_id, coptid);
						intent.putExtra(GYGRProExchangeA.KEY.in_hid, hid);
						intent.putExtra(GYGRProExchangeA.KEY.in_pid, pid);
						intent.putExtra(GYGRProExchangeA.KEY.c_status, c_status);
						activity.startActivityForResult(intent,1);	
								}			
						else if("1".equals(household_type)
						&& "0".equals(project_type)
						&& "0".equals(contracttype)){
						Intent intent = new Intent(activity, GYQYProExchangeA.class);
						intent.putExtra(GYQYProExchangeA.KEY.in_id, coptid);
						intent.putExtra(GYQYProExchangeA.KEY.in_hid, hid);
						intent.putExtra(GYQYProExchangeA.KEY.in_pid, pid);
						intent.putExtra(GYQYProExchangeA.KEY.c_status, c_status);
						activity.startActivityForResult(intent,1);	
								}
						else if("0".equals(household_type)
						&& "1".equals(project_type)
						&& "0".equals(contracttype)){
						Intent intent = new Intent(activity, JTGRProExchangeA.class);
						intent.putExtra(JTGRProExchangeA.KEY.in_id, coptid);
						intent.putExtra(JTGRProExchangeA.KEY.in_hid, hid);
						intent.putExtra(JTGRProExchangeA.KEY.in_pid, pid);
						intent.putExtra(JTGRProExchangeA.KEY.c_status, c_status);
						activity.startActivityForResult(intent,1);	
								}
						else if("1".equals(household_type)
						&& "1".equals(project_type)
						&& "0".equals(contracttype)){
						Intent intent = new Intent(activity, JTQYProExchangeA.class);
						intent.putExtra(JTQYProExchangeA.KEY.in_id, coptid);
						intent.putExtra(JTQYProExchangeA.KEY.in_hid, hid);
						intent.putExtra(JTQYProExchangeA.KEY.in_pid, pid);
						intent.putExtra(JTQYProExchangeA.KEY.c_status, c_status);
						activity.startActivityForResult(intent,1);	
								}				
						else if("0".equals(household_type)
						&& "0".equals(project_type)
						&& "1".equals(contracttype)){
						Intent intent = new Intent(activity, GYGRMonetaryCoptA.class);
						intent.putExtra(GYGRMonetaryCoptA.KEY.in_id, coptid);
						intent.putExtra(GYGRMonetaryCoptA.KEY.in_hid, hid);
						intent.putExtra(GYGRMonetaryCoptA.KEY.in_pid, pid);
						intent.putExtra(GYGRMonetaryCoptA.KEY.c_status, c_status);
						activity.startActivityForResult(intent,1);	
								}				
						else if("1".equals(household_type)
						&& "0".equals(project_type)
					    && "1".equals(contracttype)){
						Intent intent = new Intent(activity, GYQYMonetaryCoptA.class);
						intent.putExtra(GYQYMonetaryCoptA.KEY.in_id, coptid);
						intent.putExtra(GYQYMonetaryCoptA.KEY.in_hid, hid);
						intent.putExtra(GYQYMonetaryCoptA.KEY.in_pid, pid);
						intent.putExtra(GYQYMonetaryCoptA.KEY.c_status, c_status);
						activity.startActivityForResult(intent,1);	
							    }
					    else if("0".equals(household_type)
						&& "1".equals(project_type)
						&& "1".equals(contracttype)){
						Intent intent = new Intent(activity, JTGRMonetaryCoptA.class);
						intent.putExtra(JTGRMonetaryCoptA.KEY.in_id, coptid);
						intent.putExtra(JTGRMonetaryCoptA.KEY.in_hid, hid);
						intent.putExtra(JTGRMonetaryCoptA.KEY.in_pid, pid);
						intent.putExtra(JTGRMonetaryCoptA.KEY.c_status, c_status);
						activity.startActivityForResult(intent,1);	
							    }
					    else if("1".equals(household_type)
						&& "1".equals(project_type)
						&& "1".equals(contracttype)){
						Intent intent = new Intent(activity, JTQYMonetaryCoptA.class);
						intent.putExtra(JTQYMonetaryCoptA.KEY.in_id,coptid);
						intent.putExtra(JTQYMonetaryCoptA.KEY.in_hid, hid);
						intent.putExtra(JTQYMonetaryCoptA.KEY.in_pid, pid);
						intent.putExtra(JTQYMonetaryCoptA.KEY.c_status, c_status);
						activity.startActivityForResult(intent,1);	
						}	
					}else{
						if(GlobalVar.footerInfo[2].equals(txt_list_footer.getText().toString()))
							loadListPageData();
					}	
				
				}
			}

}
