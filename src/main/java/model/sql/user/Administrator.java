/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.sql.user;

import javax.persistence.Column;
import javax.persistence.Entity;

import model.sql.SQLAbstractModel;

/**
 *
 * @author Do Hung Cuong
 */

@Entity(name = "admin")
public class Administrator extends SQLAbstractModel {

	//@Column(name = "username", unique = true, nullable = false, length = 255)
	@Column(name = "username", nullable = false)
	protected String username;
	
	@Column(name = "phone", nullable = false)
	protected String phone;
	
	@Column(name = "password", nullable = false)
	protected String password;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "birthday", nullable = false)
	private String birthday;

	@Column(name = "img", nullable = false)
	private String img;

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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isEnoughInfor() {
		return checkNotNull(name, phone, gender, birthday);
	}

	@Override
	public String toString() {
		return "Administrator [username=" + username + ", phone=" + phone + ", password=" + password + ", gender="
				+ gender + ", birthday=" + birthday + ", img=" + img + ", id=" + id + ", name=" + name + "]";
	}
}
