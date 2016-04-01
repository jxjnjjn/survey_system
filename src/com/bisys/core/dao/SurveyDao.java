package com.bisys.core.dao;

public interface SurveyDao {
	public boolean insertIntoVipBaseTable(String username,String password , String register_date , String register_ip , String register_source , String cellphone_zone , String ip_zone) throws Exception;
	public boolean replaceIntoVipDynamicTable(String username,int login_num , int test_num , String friend_1 , String friend_2 , String friend_3 , String friend_4 , String friend_5) throws Exception;
	public boolean insertIntoSurveyTable(String survey_name,String survey_desc , String survey_text , String survey_anwser , String start_time , String end_time , int status) throws Exception;
	public boolean insertIntoSurveyUserTable(String user_name,String survey_name , int answer_time , String answer , int transfer_flag) throws Exception; 
}