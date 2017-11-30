/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 *
 * @author Do Hung Cuong
 */
public abstract class AbstractModel {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
