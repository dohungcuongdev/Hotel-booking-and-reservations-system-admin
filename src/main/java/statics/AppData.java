/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statics;

import daos.impl.ActivityDAOImpl;
import model.user.Administrator;
import daos.impl.AdminDAOImpl;
import java.util.Arrays;
import java.util.List;
import model.user.tracking.Activity;

/**
 *
 * @author Do Hung Cuong
 */

public class AppData {
    
    public static final String DATABASE = "HotelBookingReservationsSystem";
    public static final String DATABASE_HOST = "localhost";
    public static final int DATABASE_PORT = 27017;
    
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
    public static final String EMAIL_NO_SUBJECT_MES = "Please input subject and message!";
    
    public static final String INFOR_NOT_ENOUGH = "Please input all the information!";
    public static final String WRONG_NUMBER_FORMAT_ROOM = "Price, Size, Star and Number of Adults must be a positive natural number!";
    public static final String WRONG_TYPE_ROOM = "Type must be deluxe, family, couple or single!";
    public static final String WRONG_STATUS_ROOM = "Status must be available or booked!";
    public static final String WRONG_CHECKIN_CHECKOUT = "The checkin date and checkout date is not accepted!";
    public static final String ABLE_TO_EDIT = "success";
      
    public static final String EDITSUCCESS = ABLE_TO_EDIT;
    public static final String ERROR = "error";
    
    public static final String HEADERKEY = "Content-Disposition";
    public static final String CSV_FORMAT = "attachment; filename=\"%s\"";
    public static final String CSV_FILENAME = "follow-users.csv";
    public static final String[] HEADERCSV = {"User_ip_address", "Page_access", "Date_access", "External_ip_address", "Username", "Duration", "Country"};
    
    public static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss 'ICT' yyyy";
    public static final String IMAGE_RESOURCES = "resources/img/";
    
    public static final List<String> ROOM_TYPES = Arrays.asList("deluxe", "family", "couple", "single");
    public static final List<String> ROOM_STATUS = Arrays.asList("booked", "available");
    
    public static final List<String> SERVICE_TYPES = Arrays.asList("food", "drink", "fruit", "ice-cream");
    public static final List<String> MEALS_TYPES = Arrays.asList("breakfast", "lunch", "dinner", "snack");
    public static final List<String> MEALS_TIME = Arrays.asList( "7:00 am to 10:00 am", "11:00 am to 4:00 pm", "5:00 pm to 9:00 pm", "Anytime");
    public static final String WRONG_TYPE_SERVICE = "Type must be food, drink, fruit or ice-cream!";
    public static final String INVALID_SERVICE_TYPE = "Serve type must be breafast, lunch, dinner or snack!";
 
    public static final String ACTIVITY[] = {"Book Room","Cancel Room","Feedback Room","Feedback"};
    
    public static final String REUSE_STRING[] = {"activity", "room", "service", "edit-room", "edit-service", "editResult", "reply Book Room", "reply Cancel Room"}; 
    
    public static Administrator admin;
    public static List<Activity> newNotifications;
    
    private AppData() {}
    
    static {
        refreshAll();
    }
    
    public static void setNewAdmin(Administrator ad) {
    	admin = ad;
    }

    public static void refreshAll() {
        refreshAdmin();
        refreshNotification();
    }

    public static void refreshAdmin() {
        admin = new AdminDAOImpl().getAdminByUserName("cuongvip1295@yahoo.com.vn");
    }

    public static void refreshNotification() {
        newNotifications = new ActivityDAOImpl().getNewListNotification();
    }
}
