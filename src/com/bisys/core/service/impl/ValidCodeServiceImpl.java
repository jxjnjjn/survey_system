package com.bisys.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.ValidCodeDao;
import com.bisys.core.entity.ValidCode;
import com.bisys.core.exception.ServiceException;
import com.bisys.core.service.ValidCodeService;
import com.bisys.core.util.HttpUtil;

@Service
public class ValidCodeServiceImpl implements ValidCodeService {
	
	private static Logger logger = Logger.getLogger(ValidCodeServiceImpl.class);
	
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
	public void recordValidCode(String random, String code,String user_name) throws Exception{
		String codeKey = this.genCodeKey(random);
		//create new code
		ValidCode validCode = new ValidCode(codeKey, code);
		try {
			validCodeDao.saveValidCode(validCode);
			//发送到手机端
			this.send2phone(code,user_name);
		} catch (Exception e) {
			throw new ServiceException("获取验证码异常");
		}
	}
	
	@Override
	public void remove(ValidCode code) {
		validCodeDao.remove(code);
	}

	private String genCodeKey(String random) {
		return random;
	}
	
	public void send2phone(String code,String user_name) {
		//发送到手机端
		logger.info("发送到手机端" + user_name + ":" + code);
		String result = HttpUtil.sendPost("https://sms.yunpian.com/v2/sms/single_send.json", "apikey=300164b07190d22bb7727b81918055fb&mobile=+639052298886&text=" + code);
		//String result = HttpUtil.sendGet("https://sms.yunpian.com/v2/sms/single_send.json", "");
		logger.info("发送到手机端返回值"+result);
	}
}
