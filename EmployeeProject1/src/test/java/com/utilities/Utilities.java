package com.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class Utilities {

	public static String getName() {
		return ("John" + RandomStringUtils.random(3));
	}
	
	public static String getSalary() {
		return(RandomStringUtils.randomNumeric(5));
	}
	
	public static String getAge() {
		return(RandomStringUtils.randomNumeric(2));
	}
}
