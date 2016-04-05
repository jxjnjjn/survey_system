package com.bisys.core.entity.survey;

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
}
