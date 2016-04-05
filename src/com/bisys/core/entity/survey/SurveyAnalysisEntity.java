package com.bisys.core.entity.survey;

/*
 * 功能：问卷分析
 * */

public class SurveyAnalysisEntity {

	/*
	 * 问卷名字
	 * */
	private String survey_name;
	
	/*
	 * 参与人数
	 * */
	private int vip_num;
	
	/*
	 * 新会员比率
	 * */
	private float new_vip_rate;
	
	/*
	 * 转发比率
	 * */
	private float transfer_rate;

	public String getSurvey_name() {
		return survey_name;
	}

	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}

	public int getVip_num() {
		return vip_num;
	}

	public void setVip_num(int vip_num) {
		this.vip_num = vip_num;
	}

	public float getNew_vip_rate() {
		return new_vip_rate;
	}

	public void setNew_vip_rate(float new_vip_rate) {
		this.new_vip_rate = new_vip_rate;
	}

	public float getTransfer_rate() {
		return transfer_rate;
	}

	public void setTransfer_rate(float transfer_rate) {
		this.transfer_rate = transfer_rate;
	}
}
