package com.bisys.core.entity.bo;

public class SysUserBo {
	
	private String user_name = "";
	
	private String password = "";
	
	private String repassword = "";
	
	private String register_date = "";
	
	private String register_ip = "";
	
	private String register_source = "";
	
	private String cellphone_zone = "";
	
	private String ip_zone = "";
	
	private String oldPass = "";
	
	private String newPass = "";
	
	private String confirmPass = "";
	
	public String getOldPass() {
		return oldPass;
	}



	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}



	public String getNewPass() {
		return newPass;
	}



	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}



	public String getConfirmPass() {
		return confirmPass;
	}



	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}


	public String getUser_name() {
		return user_name;
	}



	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRepassword() {
		return repassword;
	}



	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}



	public String getRegister_date() {
		return register_date;
	}



	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}



	public String getRegister_ip() {
		return register_ip;
	}



	public void setRegister_ip(String register_ip) {
		this.register_ip = register_ip;
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



	@Override
	public String toString() {
		return "SysUserBo [ userName=" + user_name 
				+ " password=" + password
				+ " repassword=" + repassword
				+ " register_date=" + register_date
				+ " register_ip=" + register_ip
				+ " register_source=" + register_source
				+ " cellphone_zone=" + cellphone_zone
				+ " ip_zone=" + ip_zone
				+"]";
	}
}
