package com.smt.utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static Date str2date(String dateStr) {
		Date retDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			retDate = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return retDate;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.str2date("2017-9-9"));
	}
}
