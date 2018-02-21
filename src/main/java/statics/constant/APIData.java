package statics.constant;

public class APIData {

	//local
	public static final String MEAN_URL = "http://localhost:3000/";
	
	
	//online
	//public static final String MEAN_URL = "https://hotel-booking-and-reservations.herokuapp.com/";
	
	//online v1
	//public static final String MEAN_URL = "https://hotel-booking-system-v1.herokuapp.com/";
	
	
	public static final String MEAN_API_URL = MEAN_URL + "api/";
	public static final String USER_API = MEAN_URL + "api/users/";
	public static final String USER_USERNAME_API = MEAN_URL + "api/users/username/";
	public static final String ACTIVITY_API = MEAN_URL + "api/activity/";
	public static final String ACTIVITY_USERNAME_API = MEAN_URL + "api/activity/username/";
	public static final String ACTIVITY_NO_RESPONSE_API = MEAN_URL + "api/activity/response/not-yet";
	public static final String SEEN_NOTIFICATION_API = MEAN_URL + "api/activity/seen-notification/";
	public static final String REPLY_NOTIFICATION_API = MEAN_URL + "api/activity/reply-notification/";
	public static final String EXTERNAL_IP_DETAILS_API = MEAN_URL + "api/follow-users/externalIP/";
	public static final String FOLLOW_USERS_API = MEAN_URL + "api/follow-users/";
	public static final String PAGE_ACCESS_API = MEAN_URL + "api/follow-users/statistics/PageAccess/";
	public static final String PAGE_ACCESS_USERIP_API = MEAN_URL + "api/follow-users/statistics/PageAccess/userIP/";
	public static final String PAGE_ACCESS_USERNAME_API = MEAN_URL + "api/follow-users/statistics/PageAccess/username/";
	public static final String EXTERNAL_IP_API = MEAN_URL + "api/follow-users/statistics/ExternalIP/";
}
