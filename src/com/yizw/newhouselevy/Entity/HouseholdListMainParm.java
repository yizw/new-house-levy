package com.yizw.newhouselevy.Entity;

/**分户查询 用到的查询条件*/
public class HouseholdListMainParm {
	
	private String householdwner;//分户姓名
	private String cardno;//分户身份证号	

	public String getHouseholdwner() {
		return householdwner;
	}
	public void setHouseholdwner(String householdwner) {
		this.householdwner = householdwner;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
}
