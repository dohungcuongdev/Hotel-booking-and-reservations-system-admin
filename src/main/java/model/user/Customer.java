/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import java.util.ArrayList;
import java.util.List;
import model.user.tracking.Activity;

/**
 *
 * @author Do Hung Cuong
 */
public class Customer extends User {

    private String address;
    private List<Activity> activity = new ArrayList<>();
    private List<String> dateVisit;

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
		return "Customer [activity=" + activity + ", address=" + address + ", created_at=" + created_at + ", dateVisit=" + dateVisit + "]";
	}
}
