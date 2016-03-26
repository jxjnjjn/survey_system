package com.bisys.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bisys.core.dao.GeneralDao;
import com.bisys.core.dao.ValidCodeDao;
import com.bisys.core.entity.ValidCode;

@Repository
public class ValidCodeDaoImpl implements ValidCodeDao {
	
	@Autowired
	private GeneralDao generalDao;

	@Override
	public boolean remove(ValidCode code) {
		return false;
	}
	
	@Override
	public ValidCode getValidCode(String codeKey) {
		try {
			return generalDao.getEntity(
					ValidCode.class, 
					" select * from verify_code_table where code = ? ", new Object[]{codeKey});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveValidCode(ValidCode code) {
		try {
			generalDao.saveEntity(
					" insert into verify_code_table (code) values(?)", 
					new Object[]{code.getCodeKey()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
