package com.yizw.newhouselevy.UI.COM;

import android.widget.HorizontalScrollView;

/**横向滚动ListView实现接口*/
public interface IHList {
	public HorizontalScrollView getHorizontalScrollView();
	
	public void setHorizontalScrollView(HorizontalScrollView view);
	
	public void onScrollChanged(int l, int t, int oldl, int oldt);
}
