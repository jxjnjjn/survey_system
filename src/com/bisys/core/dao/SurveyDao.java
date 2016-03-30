package com.bisys.core.dao;

public interface SurveyDao {	
	public boolean insertIntoVipBaseTable(String username,String password , String register_date , String register_ip , String register_source , String cellphone_zone , String ip_zone) throws Exception;
}
