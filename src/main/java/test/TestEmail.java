package test;

import java.util.Date;
import java.util.Random;

import statics.helper.EmailSender;
import statics.helper.StringUtils;

public class TestEmail {

	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			System.out.println("email " + i);
			EmailSender.sendHTMLEmail("Dear User,<br><br>Your password have been changed to <b style='color:blue'>" + StringUtils.getRandomStringLen16() + "</b> at " + new Date() + "<br><br>Please click here to change your password <a href=\"http://localhost:8080/Hotel-booking-and-reservations-system-admin/profile.html\">Holiday Crown Hotel Admin</a><br><br>With best regards,<br> Hung Cuong.<br><br><b>Holiday Crown.</b><br>Address: 24 Street 7, Bình An Ward, District 2.<br>Phone Number: 0908998923.<br>Hotline: (08).3740480", "cuongvip1295@gmail.com", "Forget Password");
		}
		

	}

}
