package com.bisys.core.entity.survey;

import java.util.Date;

/*
 * 功能：问卷信息
 * */

public class SurveyInfoEntity {
	
	/*
	 * 问卷名字
	 * */
	private String survey_name;
	
	
	/*
	 * 问卷描述
	 * */
	private String survey_desc;
	
	/*
	 * 开始时间
	 * */
	private Date start_time;
	
	/*
	 * 结束时间
	 * */
	private Date end_time;
	
	/*
	 * 答卷人数
	 * */
	private  int num;

	public String getSurvey_name() {
		return survey_name;
	}

	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}

	public String getSurvey_desc() {
		return survey_desc;
	}

	public void setSurvey_desc(String survey_desc) {
		this.survey_desc = survey_desc;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
