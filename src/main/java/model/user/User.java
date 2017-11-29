/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import model.AbstractModel;

/**
 *
 * @author Do Hung Cuong
 */
public abstract class User extends AbstractModel {

    protected String username;
    protected String password;
    protected String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User() {
    }

    protected void setUserInfor(String username, String password, String name, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
    
    protected void setEachUserInfor(String var, String infor) {
        username = (var.equals("username")) ? infor : username;
        password = (var.equals("password")) ? infor : password;
        name = (var.equals("name")) ? infor : name;
        phone = (var.equals("phone")) ? infor : phone;
    }

    @Override
    public abstract String toString();

}
