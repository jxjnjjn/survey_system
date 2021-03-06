package com.bisys.core.entity.survey;


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
	 * 问卷内容
	 * */
	private String survey_text;
	
	/*
	 * 问卷答案
	 * */
	private String survey_anwser;
	
	/*
	 * 问卷状态
	 * */
	private String status;
	
	/*
	 * 开始时间
	 * */
	private String start_time;
	
	/*
	 * 结束时间
	 * */
	private String end_time;
	
	/*
	 * 答卷人数
	 * */
	private  int num;
	
	/*
	 * 结束时间
	 * */
	private String oldname;

	
	
	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

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
	
	

	public String getSurvey_text() {
		return survey_text;
	}

	public void setSurvey_text(String survey_text) {
		this.survey_text = survey_text;
	}

	public String getSurvey_anwser() {
		return survey_anwser;
	}

	public void setSurvey_anwser(String survey_anwser) {
		this.survey_anwser = survey_anwser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
