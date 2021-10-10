package com.teama.util;

public class Util {

	public static int parseInt(Object obj) {
		String strObj = String.valueOf(obj);
		return Integer.parseInt(strObj);
	}
	
	public static String parseDateTime(Object dateTimeObj) {
		if (dateTimeObj == null)
			return "";
		
		return dateTimeObj.toString();
	}
}
