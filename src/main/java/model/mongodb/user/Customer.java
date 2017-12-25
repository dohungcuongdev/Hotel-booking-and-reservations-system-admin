/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mongodb.user;

import java.util.ArrayList;
import java.util.List;

import model.mongodb.MongoDbAbstractModel;
import model.mongodb.user.tracking.Activity;

/**
 *
 * @author Do Hung Cuong
 */
public class Customer extends MongoDbAbstractModel {
	
	protected String username;
	protected String phone;
	protected String password;
    private String address;
    private int balance;
    private List<Activity> activity = new ArrayList<>();
    private List<String> dateVisit;
    
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

    public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public List<Activity> getActivity() {
        return activity;
    }

    public void setActivity(List<Activity> activity) {
        this.activity = activity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(List<String> dateVisit) {
        this.dateVisit = dateVisit;
    }

	@Override
	public String toString() {
		return "Customer [address=" + address + ", balance=" + balance + ", activity=" + activity + ", dateVisit="
				+ dateVisit + "]";
	}
}
