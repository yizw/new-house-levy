package com.yizw.newhouselevy.UI.COM;

import android.widget.HorizontalScrollView;

/**�������ListViewʵ�ֽӿ�*/
public interface IHList {
	public HorizontalScrollView getHorizontalScrollView();
	
	public void setHorizontalScrollView(HorizontalScrollView view);
	
	public void onScrollChanged(int l, int t, int oldl, int oldt);
}
