package com.bisys.core.entity.survey;

import java.util.Date;

/*
 * 功能：会员分析-注册人数趋势
 * */

public class VipTrendAnalysisEntity {

	/*
	 * 当日注册人数
	 * */
	private int today_num;
	
	/*
	 * 注册日期
	 * */
	private Date register_date;

	public int getToday_num() {
		return today_num;
	}

	public void setToday_num(int today_num) {
		this.today_num = today_num;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
}
