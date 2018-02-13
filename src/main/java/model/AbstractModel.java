/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.util.Date;

import statics.helper.DateTimeCalculator;

/**
 *
 * @author Do Hung Cuong
 */

public abstract class AbstractModel {
	
	protected boolean checkNotNull(Object... objs) {
		for (Object obj : objs) {
			if (obj == null || obj.toString().equals("")) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean checkNaturalNumber(int num) {
		try {
			if (num <= 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	protected boolean checkNaturalNumber(String str) {
		try {
			int num = Integer.parseInt(str);
			if (num <= 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	protected boolean checkNaturalNumber(int... numbers) {
		for (int num : numbers) {
			if (!checkNaturalNumber(num)) {
				return false;
			}
		}
		return true;
	}

	protected boolean checkNaturalNumber(String... strings) {
		for (String str : strings) {
			if (!checkNaturalNumber(str)) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean checkIsDateTimeFormat(String strDate) {
		try {
			getDateFormated(strDate);
			return true;
		} catch(ParseException e) {
			return false;
		}
	}
	
	protected boolean checkIsDateFormat(String... strDates) {
		for (String strDate : strDates) {
			if (!checkIsDateTimeFormat(strDate)) {
				return false;
			}
		}
		return true;
	}
	
	
	public Date getToday() {
		return DateTimeCalculator.getToday();
	}
	
	public static Date getDateFormated(String strDate) throws ParseException {
		return DateTimeCalculator.getDateFormated(strDate);
	}
	
	public Date getDateTimeFormated(String date) {
		return DateTimeCalculator.formatDateTime(date);
	}

	public Date getICTDateTime(String dateTime) {
		return DateTimeCalculator.getICTDateTime(dateTime);
	}

	public String getICTStrDateTime(String dateTime) {
		return DateTimeCalculator.getICTDateTime(dateTime).toString();
	}

	// lowercase first character of string
	protected String lowerFirstChar(String varname) {
		return Character.toLowerCase(varname.charAt(0)) + varname.substring(1);
	}

	// uppercase first character of string
	protected String upperFirstChar(String varname) {
		return Character.toUpperCase(varname.charAt(0)) + varname.substring(1);
	}
    
    @Override
    public abstract String toString();
}
