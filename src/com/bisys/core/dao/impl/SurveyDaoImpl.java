package com.bisys.core.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bisys.core.dao.GeneralDao;
import com.bisys.core.dao.SurveyDao;

@Repository
public class SurveyDaoImpl implements SurveyDao {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GeneralDao generalDao;

	@Override
	public boolean insertIntoVipBaseTable(String username,String password , String register_date , String register_ip , String register_source , String cellphone_zone , String ip_zone)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into vip_base_info(name,password,register_date,register_ip,register_source,cellphone_zone,ip_zone) VALUES('"
				+username+"','"+password+"','"+register_date+"','"+register_ip+"','"
				+register_source+"','"+cellphone_zone+"','"+ip_zone+");";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
}
