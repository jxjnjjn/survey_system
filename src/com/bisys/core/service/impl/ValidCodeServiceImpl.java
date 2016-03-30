package com.bisys.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.ValidCodeDao;
import com.bisys.core.entity.ValidCode;
import com.bisys.core.service.ValidCodeService;

@Service
public class ValidCodeServiceImpl implements ValidCodeService {
	
	@Autowired
	private ValidCodeDao validCodeDao;

	@Override
	public boolean checkValidCode(String random, String input) {
		String codeKey = genCodeKey(random);
		ValidCode code = validCodeDao.getValidCode(codeKey);
		if (code == null) {
			return false;
		}
		if (!code.getCode().equalsIgnoreCase(input)) {
			return false;
		}
		this.remove(code);
		return true;
	}

	@Override
	public void recordValidCode(String random, String code) {
		String codeKey = this.genCodeKey(random);
		//create new code
		ValidCode validCode = new ValidCode(codeKey, code);
		validCodeDao.saveValidCode(validCode);
		//发送到手机端
		this.send2phone(codeKey);
	}
	
	@Override
	public void remove(ValidCode code) {
		validCodeDao.remove(code);
	}

	private String genCodeKey(String random) {
		return random;
	}
	
	public void send2phone(String code) {
		//发送到手机端
		
	}
}
