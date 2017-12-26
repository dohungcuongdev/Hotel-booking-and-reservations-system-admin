/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author HUNGCUONG
 */

public class ChangePasswordBean {
	private String currentpassword;
	private String newpassword;
	private String confirm;

	public String getCurrentpassword() {
		return currentpassword;
	}

	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	public String getPWCheckingResult(String correctPassword) {
		return !currentpassword.equals(correctPassword) ? "Your current password is incorrect" : !newpassword.equals(confirm) ? "Password confirmation doesn't match password" : newpassword.equals(currentpassword) ? "New password is the same as currunt password": "Password changed successfully";
	}
	
	public boolean isMatchPassword(String correctPassword) {
		return (currentpassword.equals(correctPassword) && !newpassword.equals(currentpassword) && newpassword.equals(confirm));
	}
	
	public ChangePasswordBean() {
	}

	public ChangePasswordBean(String currentpassword, String newpassword, String confirm) {
		this.currentpassword = currentpassword;
		this.newpassword = newpassword;
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "ChangePasswordBean [currentpassword=" + currentpassword + ", newpassword=" + newpassword + ", confirm="
				+ confirm + "]";
	}
}
