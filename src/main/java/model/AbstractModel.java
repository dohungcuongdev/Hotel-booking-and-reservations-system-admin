/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

import statics.provider.DateTimeCalculator;

/**
 *
 * @author Do Hung Cuong
 */
public abstract class AbstractModel {
	
	protected String _id;
    protected String name;
    protected String created_at;

    public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

    protected boolean checkNotNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null || obj.toString().equals("")) {
                return false;
            }
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

    protected boolean checkNaturalNumber(String... strings) {
        for (String str : strings) {
            if (!AbstractModel.this.checkNaturalNumber(str)) {
                return false;
            }
        }
        return true;
    }
    
    public Date getICTDateTime(String dateTime) {
    	return DateTimeCalculator.getICTDateTime(dateTime);
    }
    
    public String getICTStrDateTime(String dateTime) {
    	return DateTimeCalculator.getICTDateTime(dateTime).toString();
    }

    //lowercase first character of string
    protected String lowerFirstChar(String varname) {
        return Character.toLowerCase(varname.charAt(0)) + varname.substring(1);
    }

    //uppercase first character of string
    protected String upperFirstChar(String varname) {
        return Character.toUpperCase(varname.charAt(0)) + varname.substring(1);
    }

    @Override
    public abstract String toString();
}
