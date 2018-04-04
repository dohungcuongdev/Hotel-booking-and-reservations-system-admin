/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statics.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.mongodb.user.Customer;
import model.sql.hotel.HotelRoom;
import model.sql.hotel.HotelService;
import model.sql.user.Administrator;

/**
 *
 * @author Do Hung Cuong
 */

public class AppData {
	
	
	/* change server config here */
	// public static final String SERVER_CONFIGURATION = "custom";
	// public static final String SERVER_CONFIGURATION = "localhost:3000";
	public static final String SERVER_CONFIGURATION = "localhost:3000 & localhost:8080";
	// public static final String SERVER_CONFIGURATION = "https://hotel-booking-and-reservations.herokuapp.com";
	// public static final String SERVER_CONFIGURATION = "https://hotel-booking-system-v1.herokuapp.com";
	// public static final String SERVER_CONFIGURATION = "https://hotel-booking-system-v2.herokuapp.com";
	
	
	/* change database config here */
	public static final String DB_CONFIGURATION = "local";
	// public static final String DB_CONFIGURATION = "mlab";
	
	public static String SPRING_APP_URL;
	public static String MEAN_URL;
	public static String MONGGO_URL;
	public static String DATABASE;
	
	static {
		switch (SERVER_CONFIGURATION) {
		
		case "custom":
			/* change your custom server config here */
			MEAN_URL = "http://localhost:3000/";
			SPRING_APP_URL = "https://admin-hotel-booking-v1.herokuapp.com/";
			break;
			
		case "localhost:3000":
			MEAN_URL = "http://localhost:3000/";
			SPRING_APP_URL = "http://localhost:3000/";
			break;
			
		case "localhost:3000 & localhost:8080":
			MEAN_URL = "http://localhost:3000/";
			SPRING_APP_URL = "http://localhost:8080/Hotel-booking-and-reservations-system-admin/";
			break;
			
		case "https://hotel-booking-and-reservations.herokuapp.com":
			MEAN_URL = "https://hotel-booking-and-reservations.herokuapp.com/";
			SPRING_APP_URL = "https://admin-hotel-booking.herokuapp.com/";
			break;
			
		case "https://hotel-booking-system-v1.herokuapp.com":
			MEAN_URL = "https://hotel-booking-system-v1.herokuapp.com/";
			SPRING_APP_URL = "https://admin-hotel-booking-v1.herokuapp.com/";
			break;
		case "https://hotel-booking-system-v2.herokuapp.com":
			MEAN_URL = "https://hotel-booking-system-v2.herokuapp.com/";
			SPRING_APP_URL = "https://admin-hotel-booking-v1.herokuapp.com/";
			break;
			
		default:
			break;
		}
		
		if(DB_CONFIGURATION.equals("local")) {
				DATABASE = "HotelBookingReservationsSystem";
				String DATABASE_HOST = "localhost";
				int DATABASE_PORT = 27017;
				MONGGO_URL = "mongodb://" + DATABASE_HOST + ":" + DATABASE_PORT;
		}
		
		if(DB_CONFIGURATION.equals("mlab")) {
				DATABASE = "hotel_booking_system";
				MONGGO_URL = "mongodb://dohungcuongdev:ititiu13170@ds157057.mlab.com:57057/" + DATABASE;
		}
	}


			
	public static final String EMAIL = "cuongvip1295@gmail.com";
	public static final String AUTHENTICATION = "ititiu13170";
	public static final String MAIL_HOST = "smtp.gmail.com";
	public static final String MAIL_SMTP_PORT = "587";
	public static final String MAIL_SMTP_AUTH = "true";
	public static final String MAIL_SMTP_STARTTLS_ENABLE = "true";
	public static final String MAIL_SMTP_SSS_TRUST = MAIL_HOST;
	public static final String AUTH_FAIL = "Authentication failed";
	public static final String WRONG_EMAIL_ADDRESS = "Wrong email address";
	public static final String ERROR_MESSAGE = "ErrorMessage";
	public static final String EMAIL_SENT = "Sent successfully";
	public static final String FORGET_PW_HEADER_EMAIL = "Dear User,<br><br>Your password have been changed to <b style='color:blue'>";
	public static final String FORGET_PW_FOOTER_EMAIL = "<br><br>Please click here to change your password <a href='"
			+ SPRING_APP_URL
			+ "'>Marina Meepo Hotel Admin</a><br><br>With best regards,<br> Hung Cuong.<br><br><b>Marina Meepo.</b><br>Address: 24 Street 7, Bình An Ward, District 2.<br>Phone Number: 0908998923.<br>Hotline: (08).3740480";
	public static final String EMAIL_NO_SUBJECT_MES = "Please input subject and message!";
	public static final String INFOR_NOT_ENOUGH = "Please input all the information!";
	public static final String WRONG_ROOM_NAME = "Room name must be 3 digits natural number! (Ex: 604)";
	public static final String WRONG_NUMBER_FORMAT_ROOM = "Price, Size, Star and Number of Adults must be a positive natural number!";
	public static final String WRONG_TYPE_ROOM = "Type must be deluxe, family, couple or single!";
	public static final String WRONG_STATUS_ROOM = "Status must be available or booked!";
	public static final String WRONG_CHECKIN_CHECKOUT = "The checkin date and checkout date is not accepted!";
	public static final String ABLE_TO_EDIT = "success";
	public static final String ABLE_TO_ADD = ABLE_TO_EDIT;
	public static final String EDITSUCCESS = ABLE_TO_EDIT;
	public static final String ERROR = "error";
	public static final String HEADERKEY = "Content-Disposition";
	public static final String CSV_FORMAT = "attachment; filename=\"%s\"";
	public static final String CSV_FILENAME = "follow-users.csv";
	public static final String[] HEADERCSV = { "User_ip_address", "Page_access", "Created_at", "External_ip_address",
			"Username", "Duration" };
	public static final String NO_FILE_CHOSEN = "No file chosen";
	public static final String NOT_PDF = "Just accept PDF format";
	public static final String PDF_RESOURCES = "resources/pdf/";
	public static final String UPLOAD_SUCCESS = "Upload successfully";
	public static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss 'ICT' yyyy";
	public static final String IMAGE_RESOURCES = "resources/img/";
	public static final List<String> ROOM_TYPES = Arrays.asList("deluxe", "family", "couple", "single");
	public static final List<String> ROOM_STATUS = Arrays.asList("booked", "available");
	public static final List<String> SERVICE_TYPES = Arrays.asList("food", "drink", "fruit", "ice-cream");
	public static final List<String> MEALS_TYPES = Arrays.asList("breakfast", "lunch", "dinner", "snack");
	public static final List<String> MEALS_TIME = Arrays.asList("7:00 am to 10:00 am", "11:00 am to 4:00 pm",
			"5:00 pm to 9:00 pm", "Anytime");
	public static final String WRONG_NUMBER_FORMAT_SERVICE = "Price and Quantity must be a positive natural number!";
	public static final String WRONG_TYPE_SERVICE = "Type must be food, drink, fruit or ice-cream!";
	public static final String INVALID_SERVICE_TYPE = "Serve type must be breafast, lunch, dinner or snack!";
	public static final String ACTIVITY[] = { "Book Room", "Cancel Room", "Feedback Room", "Feedback" };
	public static final String REUSE_STRING[] = { "activity", "room", "service", "edit-room", "edit-service",
			"editResult", "reply Book Room", "reply Cancel Room" };
	public static final String ROOM_DEFAULT_IMG[] = { "room-pictures-excellent-luxury-hotel-rooms.jpg",
			"room-pictures-delightful-livingfamily-room-aaron-design-and-build-inc.jpg", };
	public static final String RESTAURANT_DEFAULT_IMG[] = { "pizza.jpg", "pizza_2.jpg", };
	public static final String EMAIL_TEMPLATE_1[] = { "",
			"Dear Mr. Đỗ Hùng Cường, &#13;&#13;Thank you for your interest in our hotel. This email is to acknowledge the receipt of your email and thank you for sending us your request. We will carefully review your request. Should your request match our ability, we will contact you soon. In other cases, we will keep the request for future opportunities.&#13;&#13;With best regards, &#13Hùng Cường.&#13&#13...&#13Marina Meepo.&#13Address: 24 Street 7, Bình An Ward, District 2.&#13Phone Number: 0908998923.&#13Hotline: (08).37404802.",
			"Dear Mr. Đỗ Hùng Cường, &#13;&#13;Thank you for your interest in our hotel. This email is to acknowledge the receipt of your email and thank you for sending us your request. We will carefully review your request. Should your request match our ability, we will contact you soon. In other cases, we will keep the request for future opportunities.&#13;&#13;With best regards, &#13Hùng Cường.&#13&#13...&#13Marina Meepo.&#13Address: 24 Street 7, Bình An Ward, District 2.&#13Phone Number: 0908998923.&#13Hotline: (08).37404802." };

	public static Administrator admin = new Administrator();
	public static List<HotelRoom> listrooms = new ArrayList<>();
	public static List<HotelService> listservices = new ArrayList<>();
	public static List<Customer> listcustomers = new ArrayList<>();
}
