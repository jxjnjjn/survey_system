package com.bisys.core.entity.survey;

import com.bisys.core.util.MathUtil;

/*
 * 功能：会员中心-我的问卷-我的好友
 * */

public class VipSurveyFriendInfoEntity {

	/*
	 * 用户名字
	 * */
	private String user_name;
	
	/*
	 * 问卷名字
	 * */
	private String survey_name;
	
	/*
	 * 问卷答案
	 * */
	private String survey_answer;
	
	/*
	 * 用户答案
	 * */
	private String user_answer;
	
	/*
	 * 正确率
	 * */
	private String correct_rate;
	

	public String getSurvey_answer() {
		return survey_answer;
	}

	public void setSurvey_answer(String survey_answer) {
		this.survey_answer = survey_answer;
	}

	public String getUser_answer() {
		return user_answer;
	}

	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}

	public String getCorrect_rate() {
		return correct_rate;
	}

	public void setCorrect_rate(String correct_rate) {
		this.correct_rate = correct_rate;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSurvey_name() {
		return survey_name;
	}

	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}
	
	public void calculate_correct_rate()
	{
		this.correct_rate = MathUtil.correctRateString(this.user_answer, this.survey_answer);
	}
	
	public void addSurveyToList(String survey_name) {
		if(this.survey_name == null)
		{
			this.survey_name = survey_name;
		}
		else
		{
			this.survey_name = this.survey_name+","+survey_name;
		}	
	}
}
