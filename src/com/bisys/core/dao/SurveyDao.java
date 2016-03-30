package com.bisys.core.dao;

public interface SurveyDao {
	public boolean insertIntoVipBaseTable(String username,String password , String register_date , String register_ip , String register_source , String cellphone_zone , String ip_zone) throws Exception;
	public boolean replaceIntoVipDynamicTable(String username,int login_num , int test_num , String friend_1 , String friend_2 , String friend_3 , String friend_4 , String friend_5) throws Exception;
}
