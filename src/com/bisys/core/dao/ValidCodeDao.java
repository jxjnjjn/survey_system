package com.bisys.core.dao;

import com.bisys.core.entity.ValidCode;

public interface ValidCodeDao {
	
	public void saveValidCode(ValidCode validCode) throws Exception;
	
	/**
	 * 检查验证输入是否正确
	 * <p>
	 * 请求验证码，
	 * 
	 * @param random 随机值，与请求验证码的时候一致
	 * @param input 当前用户的输入
	 * @return
	 */
	public ValidCode getValidCode(String codeKey);
	
	public boolean remove(ValidCode code);

}
