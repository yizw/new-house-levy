package com.yizw.newhouselevy.UI.COM;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class CHScrollView extends HorizontalScrollView {
	
	IHList hList;
	
	public CHScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		hList = (IHList) context;
	}

	
	public CHScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		hList = (IHList) context;
	}

	public CHScrollView(Context context) {
		super(context);
		hList = (IHList) context;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		//���д�����ֵ
		hList.setHorizontalScrollView(this);
		return super.onTouchEvent(ev);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		//����ǰ��CHSCrollView������ʱ����������
		if(hList.getHorizontalScrollView() == this) {
			hList.onScrollChanged(l, t, oldl, oldt);
		}else{
			super.onScrollChanged(l, t, oldl, oldt);
		}
	}
}