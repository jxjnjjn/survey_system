package com.bisys.core.entity.survey;

import java.util.Date;

/*
 * 功能：问卷排行榜
 * */

public class SurveyRankListEntity {

	/*
	 * 用户名字
	 * */
	private String user_name;
	
	/*
	 * 注册日期
	 * */
	private Date register_date;
	
	/*
	 * 答题日期
	 * */
	private Date answer_date;
	
	/*
	 * 用户答案
	 * */
	private String answer;
	
	/*
	 * 正确答案
	 * */
	private String correct_anwser;
	
	/*
	 * 正确率
	 * */
	private String correct_rate;

	
	public String getCorrect_anwser() {
		return correct_anwser;
	}

	public void setCorrect_anwser(String correct_anwser) {
		this.correct_anwser = correct_anwser;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	public Date getAnswer_date() {
		return answer_date;
	}

	public void setAnswer_date(Date answer_date) {
		this.answer_date = answer_date;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCorrect_rate() {
		return correct_rate;
	}

	public void setCorrect_rate(String correct_rate) {
		this.correct_rate = correct_rate;
	}
}
