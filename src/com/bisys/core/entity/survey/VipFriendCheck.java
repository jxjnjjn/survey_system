package com.bisys.core.entity.survey;

/*
 * 功能：VIP 用户的好友数信息
 * */

public class VipFriendCheck {

	/*
	 * 用户名字
	 * */
	private String user_name;
	
	/*
	 * 好友数
	 * */
	private int friend_num;
	
	/*
	 * 好友是否存在：不存在记录:0 ，已经存在于注册列表中：1 ， 已经存在于好友列表中：2
	 * */
	private int friend_exist;

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

	public int getFriend_exist() {
		return friend_exist;
	}

	public void setFriend_exist(int friend_exist) {
		this.friend_exist = friend_exist;
	}

}
