package com.yizw.newhouselevy.Entity;

import java.util.ArrayList;

import com.cogent.core.component.ValueName;

/**
 * 一个spinner对象或类似对象的数据源
 */
public class SpinnerDomain {
	
	private ArrayList<ValueName> Items;//数据项	
	private ValueName defaultItem;//默认选中项
	
	public ArrayList<ValueName> getItems() {
		return Items;
	}
	public void setItems(ArrayList<ValueName> items) {
		Items = items;
	}
	public ValueName getDefaultItem() {
		return defaultItem;
	}
	public void setDefaultItem(ValueName defaultItem) {
		this.defaultItem = defaultItem;
	}
}
