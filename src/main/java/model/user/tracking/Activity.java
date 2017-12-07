/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user.tracking;

import java.util.Comparator;
import java.util.Date;
import model.AbstractModel;

/**
 *
 * @author Do Hung Cuong
 */
public class Activity extends AbstractModel {

    private String username;
    private String click;
    private String details;
    private String note;
    private String content;
    private Date time;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Activity() {
    }

    public Activity(String id, String username, String click, String details, String note, String content, Date time, String response) {
        this.id = id;
        this.username = username;
        this.click = click;
        this.details = details;
        this.note = note;
        this.content = content;
        this.time = time;
        this.response = response;
    }

    public Activity(String id, String name, String username, String click, String details, String note, String content, Date time, String response, String fullname, String email, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.click = click;
        this.details = details;
        this.note = note;
        this.content = content;
        this.time = time;
        this.response = response;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Activity{" + "id=" + id + ", username=" + username + ", click=" + click + ", details=" + details + ", note=" + note + ", content=" + content + ", time=" + time + ", response=" + response + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone + '}';
    }

    public static class CompareDateTime implements Comparator<Activity> {
        @Override
        public int compare(Activity a1, Activity a2) {
            return a2.time.compareTo(a1.time);
        }
    }
}
