package com.bisys.core.entity.survey;

import java.util.Date;

public class VipListEntity {
	
	private String user_name;
	
	private Date register_date;
	
	private String register_ip;
	
	private int login_num;
	
	private int test_num;
	
	private String register_source;
	
	private String cellphone_zone;
	
	private String ip_zone;
	
	private String friends;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	public String getRegister_ip() {
		return register_ip;
	}

	public void setRegister_ip(String register_ip) {
		this.register_ip = register_ip;
	}

	public int getLogin_num() {
		return login_num;
	}

	public void setLogin_num(int login_num) {
		this.login_num = login_num;
	}

	public int getTest_num() {
		return test_num;
	}

	public void setTest_num(int test_num) {
		this.test_num = test_num;
	}

	public String getRegister_source() {
		return register_source;
	}

	public void setRegister_source(String register_source) {
		this.register_source = register_source;
	}

	public String getCellphone_zone() {
		return cellphone_zone;
	}

	public void setCellphone_zone(String cellphone_zone) {
		this.cellphone_zone = cellphone_zone;
	}

	public String getIp_zone() {
		return ip_zone;
	}

	public void setIp_zone(String ip_zone) {
		this.ip_zone = ip_zone;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}
}
