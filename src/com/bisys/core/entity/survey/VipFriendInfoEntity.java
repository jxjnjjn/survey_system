package com.bisys.core.entity.survey;

/*
 * 功能：VIP 用户的好友信息
 * */

public class VipFriendInfoEntity {

	/*
	 * 用户名字
	 * */
	private String user_name;
	
	/*
	 * 好友信息【friend1,friend2】
	 * */
	private String friends;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}
}
