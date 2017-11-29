/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

    //get all variable name of class
    public List<String> getVarsOfObject(Object obj) {
        List<String> vars = new ArrayList();
        Class testObject = obj.getClass();
        Method[] methods = testObject.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (!name.equalsIgnoreCase("getClass") && name.startsWith("get")) {
                vars.add(lowerFirstChar(name.substring(3)));
            } else if (name.startsWith("is")) {
                vars.add(lowerFirstChar(name.substring(2)));
            }
        }
        return vars;
    }

    //call get Method by variable name 
    public String getMethodValueOf(Class anyClass, String var) throws Exception {
        Method method = anyClass.getMethod("get" + upperFirstChar(var));
        return (String) method.invoke(anyClass);
    }

    //lowercase first character of string
    private String lowerFirstChar(String varname) {
        return Character.toLowerCase(varname.charAt(0)) + varname.substring(1);
    }

    //uppercase first character of string
    private String upperFirstChar(String varname) {
        return Character.toUpperCase(varname.charAt(0)) + varname.substring(1);
    }

    @Override
    public abstract String toString();
}
