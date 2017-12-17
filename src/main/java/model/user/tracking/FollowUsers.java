/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user.tracking;

/**
 *
 * @author HUNGCUONG
 */
public class FollowUsers {

	private String user_ip_address;
	private String external_ip_address;
	private String user_id;
	private String username;
	private String created_at;
	private String page_access;
	private int duration;
	private String durationTime;
	private String country;
	private String city;
	
	public String getUser_ip_address() {
		return user_ip_address;
	}

	public void setUser_ip_address(String user_ip_address) {
		this.user_ip_address = user_ip_address;
	}

	public String getPage_access() {
		return page_access;
	}

	public void setPage_access(String page_access) {
		this.page_access = page_access;
	}

	public String getExternal_ip_address() {
		return external_ip_address;
	}

	public void setExternal_ip_address(String external_ip_address) {
		this.external_ip_address = external_ip_address;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "FollowUsers [user_ip_address=" + user_ip_address + ", external_ip_address="
				+ external_ip_address + ", user_id=" + user_id + ", username=" + username + ", page_access="
				+ page_access + ", created_at=" + created_at + ", duration=" + duration + ", durationTime="
				+ durationTime + ", country=" + country + ", city=" + city + "]";
	}
}
