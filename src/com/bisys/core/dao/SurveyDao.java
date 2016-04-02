package com.bisys.core.dao;

import java.util.List;

import com.bisys.core.entity.survey.VipListEntity;

public interface SurveyDao {
	public boolean insertIntoVipBaseTable(String user_name,String password , String register_date , String register_ip , String register_source , String cellphone_zone , String ip_zone , String role_name , String permission_name) throws Exception;
	public boolean replaceIntoVipDynamicTable(String user_name,int login_num , int test_num , String friends) throws Exception;
	public boolean insertIntoSurveyTable(String survey_name,String survey_desc , String survey_text , String survey_anwser , String start_time , String end_time , int status) throws Exception;
	public boolean insertIntoSurveyUserTable(String user_name,String survey_name , int answer_time , String answer , int transfer_flag) throws Exception; 
	public List<VipListEntity> getVipList() throws Exception; 
}
