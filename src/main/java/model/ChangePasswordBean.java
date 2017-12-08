package model;

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
	
	public boolean isMatchPassword(String correctPassword) {
		return (currentpassword.equals(correctPassword) && newpassword.equals(confirm));
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
