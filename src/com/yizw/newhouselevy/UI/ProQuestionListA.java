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
import com.yizw.newhouselevy.Adapter.ProQuestionAdapter;
import com.yizw.newhouselevy.AsyncTask.ProQuestionSearchAsyncTask;
import com.yizw.newhouselevy.Business.PublicBus;
import com.yizw.newhouselevy.DAO.DatabaseHelper;
import com.yizw.newhouselevy.Entity.PageDomain;

/** 问题反馈(项目)  问题信息*/
public class ProQuestionListA extends OrmLiteBaseActivity<DatabaseHelper>{
	public class KEY{	
		/** id*/
		public static final String in_id = "in_id";
		/** 项目id*/
		public static final String pid = "pid";
		/** 分户id*/
		public static final String hid = "hid";
		/** 问题id*/
		public static final String question_id = "question_id";
		
		//从项目总览入口进入，赋值为1，只做查看，将保存按钮隐藏
		public static final String hide = "hide";
		
	}
	public String in_id,pid,hid,question_id,hide;
	
	public ProQuestionListA activity;
	
	public ListView listView;
	public TextView txt_list_footer,txt_info;
	
	public View footerView;
	
	public ProQuestionAdapter adapter;
	
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
        setContentView(R.layout.problem_feedback_question_list);
        PublicBus.titleBarControl(this, "项目问题反馈", true, null, true, null);
        initControl();
    //  operateIntent();
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
			adapter = new ProQuestionAdapter(activity, new DataTableList());//实例化一个空数据项的适配器
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
			question_id = intent.getStringExtra(KEY.question_id);
			ProQuestionSearchAsyncTask task = new ProQuestionSearchAsyncTask(activity);
			task.execute();
			
			if(!StringHelper.isNullOrEmpty(hide) && "1".equals(hide)){
			Button btn = (Button)findViewById(R.id.btn_title_right);
			btn.setVisibility(View.GONE);
			}
		}
	
		
		 @Override
		  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			
			if(resultCode==RESULT_OK){
				pageDomain = new PageDomain();
				adapter = new ProQuestionAdapter(activity, new DataTableList());
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
	    				
						TextView question_id = (TextView) view.findViewById(R.id.pro_question_id);
						String questionid = question_id.getText().toString();
						
						//hide为1,表示是从项目总览入口进入，只做查看，不容许做新增问题或回复
						if(!"1".equals(hide) && StringHelper.isNullOrEmpty(questionid) ){ 
						Intent intent = new Intent(activity,ProReplyAdd.class);						
						intent.putExtra(ProReplyAdd.KEY.in_id, pid);
						intent.putExtra(ProReplyAdd.KEY.question_id, projectid);
						activity.startActivityForResult(intent,1);
						}
					}else{
						if(GlobalVar.footerInfo[2].equals(txt_list_footer.getText().toString()))
							loadListPageData();
					}			
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
				Intent intent = new Intent(activity, ProQuestionAdd.class);
				intent.putExtra(ProQuestionAdd.KEY.p_id, pid);
				startActivityForResult(intent, 0);
			} 	 
     	}
	
}	
