/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mongodb.user.tracking;

import java.util.Comparator;

import model.mongodb.MongoDbAbstractModel;

/**
 *
 * @author Do Hung Cuong
 */

public class Activity extends MongoDbAbstractModel {

    private String username;
    private String click;
    private String details;
    private String note;
    private String content;
    private String response;
    private String fullname;
    private String email;
    private String phone;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    public void setCustomerInfor(String email, String fullname, String phone) {
    	this.email = email;
    	this.fullname = fullname;
    	this.phone = phone;
    }

    public static class CompareDateTime implements Comparator<Activity> {
        @Override
        public int compare(Activity a1, Activity a2) {
            return a2.created_at.compareTo(a1.created_at);
        }
    }

	@Override
	public String toString() {
		return "Activity [username=" + username + ", click=" + click + ", details=" + details + ", note=" + note
				+ ", content=" + content + ", response=" + response + ", fullname=" + fullname + ", email=" + email
				+ ", phone=" + phone + ", _id=" + _id + ", name=" + name + ", created_at=" + created_at + "]";
	}
}
