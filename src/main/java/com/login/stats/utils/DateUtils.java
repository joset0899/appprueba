package com.login.stats.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	public static String getDateFormated(Date date) {
		return getDateFormat().format(date);
	}
	
	public static SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
	}

}
