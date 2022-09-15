package com.dricks.jumpee.helpers;

public class UserLoginInfo {
	public static String userGlobalEmail;
	public static int userGlobalId;

	public static String getUserGlobalEmail() {
		return userGlobalEmail;
	}

	public static void setUserGlobalEmail(String userGlobalEmail) {
		UserLoginInfo.userGlobalEmail = userGlobalEmail;
	}

	public static int getUserGlobalId() {
		return userGlobalId;
	}

	public static void setUserGlobalId(int userGlobalId) {
		UserLoginInfo.userGlobalId = userGlobalId;
	}
	
}

