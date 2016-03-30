package com.bisys.core.entity.vo;

import com.bisys.core.entity.bo.SysUserBo;

public class SysUserVo extends SysUserBo {

	private String authcode = "";
	
	private String randomString = "";

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getRandomString() {
		return randomString;
	}

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}
	
}
