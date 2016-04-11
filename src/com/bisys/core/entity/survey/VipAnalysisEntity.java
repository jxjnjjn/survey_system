package com.bisys.core.entity.survey;

/*
 * 功能：会员分析-注册人数统计
 * */

public class VipAnalysisEntity {

	/*
	 * 累计注册人数
	 * */
	private int total_num;
	
	/*
	 * 当日注册人数
	 * */
	private int today_num;
	
	/*
	 * 当日浏览未注册人数
	 * */
	private int unregister_num;

	public int getTotal_num() {
		return total_num;
	}

	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}

	public int getToday_num() {
		return today_num;
	}

	public void setToday_num(int today_num) {
		this.today_num = today_num;
	}

	public int getUnregister_num() {
		return unregister_num;
	}

	public void setUnregister_num(int unregister_num) {
		this.unregister_num = unregister_num;
	}
}
