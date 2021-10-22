package com.teama.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

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
	
	public static String getStrCurrentTime(int additiveDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, additiveDay);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(cal.getTime());	
	}

	public static String getStrAdditiveTime(String strDate, int additiveDay) {
		String result;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = format.parse(strDate);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, additiveDay);
			
			result = format.format(cal.getTime());	
		} catch (ParseException e) {
			e.printStackTrace();
			Date date = new Date(Long.MIN_VALUE);
			result = format.format(date); 
		}
		
		return result;	
	}
	
	public static PaginationInfo newPaginationInfo(int currentPageNo, int totalRecordCount) {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(currentPageNo);
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(10);
		paginationInfo.setTotalRecordCount(totalRecordCount);
		
		return paginationInfo;
	}
	
	public static String generateSalt() {
		Random random = new Random();
		
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < salt.length; i++) {
			// byte 값을 Hex 값으로 바꾸기.
			sb.append(String.format("%02x",salt[i]));
		}
		
		return sb.toString();
	}
	
}
