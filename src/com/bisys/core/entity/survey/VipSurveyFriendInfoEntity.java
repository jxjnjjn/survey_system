package com.bisys.core.entity.survey;

/*
 * 功能：会员中心-我的问卷-我的好友（字段和我的问卷一样）
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
	 * 答题时间
	 * */
	private int answer_time;
	
	/*
	 * 用户答案
	 * */
	private String answer;
	
	/*
	 * 正确答案
	 * */
	private String survey_anwser;
	
	/*
	 * 正确率
	 * */
	private double correct_rate;

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

	public int getAnswer_time() {
		return answer_time;
	}

	public void setAnswer_time(int answer_time) {
		this.answer_time = answer_time;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSurvey_anwser() {
		return survey_anwser;
	}

	public void setSurvey_anwser(String survey_anwser) {
		this.survey_anwser = survey_anwser;
	}

	public double getCorrect_rate() {
		return correct_rate;
	}

	public void setCorrect_rate(double correct_rate) {
		this.correct_rate = correct_rate;
	}
}
