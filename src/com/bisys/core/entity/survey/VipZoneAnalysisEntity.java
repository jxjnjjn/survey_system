package com.bisys.core.entity.survey;


/*
 * 功能：会员分析-地区分布
 * */

public class VipZoneAnalysisEntity {

	/*
	 * 地区名称
	 * */
	private String zone_name;
	
	/*
	 * 地区会员数
	 * */
	private int zone_num;

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

	public int getZone_num() {
		return zone_num;
	}

	public void setZone_num(int zone_num) {
		this.zone_num = zone_num;
	}

}
