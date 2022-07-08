package com.revature.util;

public class RegexUtil {
		
	// 8 characters minimun, requires one character and one number
	public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$";

}
