package com.bisys.core.entity.survey;

/*
 * 功能：VIP 用户的好友信息
 * */

public class VipVipFriendInfoEntity {

	/*
	 * 用户名字
	 * */
	private String user_name;
	
	/*
	 * 好友信息【friend1,friend2】
	 * */
	private String friend_list;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFriend_list() {
		return friend_list;
	}

	public void setFriend_list(String friend_list) {
		this.friend_list = friend_list;
	}
}
