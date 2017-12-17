/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statics.provider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import statics.AppData;

/**
 *
 * @author Do Hung Cuong
 */
public class DateTimeCalculator {
	
	public static Date getToday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return formatDateTime(format.format(new Date()));
	}
	
	public static String getTimeToday() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return format.format(new Date());
	}

    public static Date getDateTime(String dateTime) {
        SimpleDateFormat myFormat = new SimpleDateFormat(AppData.DATE_FORMAT);
        try {
            return myFormat.parse(dateTime);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Date formatDateTime(String dateTime, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateTime);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Date formatDateTime(String dateTime) {
        return formatDateTime(dateTime, "yyyy-MM-dd");
    }
    
    public static Date getICTDateTime(String dateTime) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(formatDateTime(dateTime.replaceFirst("T", " "), "yyyy-MM-dd HH:mm:ss"));
        cal.add(Calendar.HOUR_OF_DAY, 7);
    	return cal.getTime();
    }

    public static String formatMillisecond(int millis) {
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;
        long sss = millis - hour * 3600 * 1000 - minute * 60 * 1000 - second * 1000;
        return String.format("%02d:%02d:%02d:%03d", hour, minute, second, sss);
    }
}
