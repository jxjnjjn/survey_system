package com.bisys.core.entity.survey;

import java.util.Date;

/*
 * 功能：问卷分析-手机区域分布
 * */

public class PhoneZoneAnalysisEntity {

	/*
	 * 地区名称
	 * */
	private String zone_name;
	
	/*
	 * 地区会员数
	 * */
	private Date zone_num;

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

	public Date getZone_num() {
		return zone_num;
	}

	public void setZone_num(Date zone_num) {
		this.zone_num = zone_num;
	}
}
