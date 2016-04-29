package com.bisys.core.entity.survey;

/*
 * 功能：问卷分析-应用分布
 * */

public class ShareAppAnalysisEntity {

	/*
	 * APP名称
	 * */
	private String app_name;
	
	/*
	 * APP数量
	 * */
	private int app_num;

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public int getApp_num() {
		return app_num;
	}

	public void setApp_num(int app_num) {
		this.app_num = app_num;
	}
}
