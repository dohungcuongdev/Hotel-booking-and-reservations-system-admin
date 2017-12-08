package test;

import model.ChangePasswordBean;

public class Test2 {

	public static void main(String[] args) {
		ChangePasswordBean c = new ChangePasswordBean("12101995","2","3");
		System.out.println(c.getPWCheckingResult("12101995"));
	}

}
