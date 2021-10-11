package com.teama.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public static String getStrCurrentTime() {
		Date currentTime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(currentTime);	
	}
}
