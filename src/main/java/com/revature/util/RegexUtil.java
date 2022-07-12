package com.revature.util;

public class RegexUtil {
		
	// 8 characters minimun, requires one character and one number
	public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

}
