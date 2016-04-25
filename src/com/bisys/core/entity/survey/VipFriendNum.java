package com.bisys.core.entity.survey;

/*
 * 功能：VIP 用户的好友数信息
 * */

public class VipFriendNum {

	/*
	 * 用户名字
	 * */
	private String user_name;
	
	/*
	 * 好友数
	 * */
	private int friend_num;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getFriend_num() {
		return friend_num;
	}

	public void setFriend_num(int friend_num) {
		this.friend_num = friend_num;
	}

}
