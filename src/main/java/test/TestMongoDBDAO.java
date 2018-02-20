package test;

import java.net.UnknownHostException;
import java.util.List;

import daos.impl.mongodb.ActivityDAOImpl;
import daos.impl.mongodb.CustomerDAOImpl;
import daos.impl.mongodb.RoomDAOImpl;
import daos.impl.mongodb.TrackingDAOImpl;
import daos.ActivityDAO;
import daos.CustomerDAO;
import daos.RoomDAO;
import daos.TrackingDAO;
import model.api.user.tracking.CustomerBehavior;
import model.hotel.HotelRoom;
import model.mongodb.user.Customer;
import model.mongodb.user.tracking.Activity;
import statics.helper.DateTimeCalculator;

public class TestMongoDBDAO {
	
	public static void testRoom() throws UnknownHostException {
		RoomDAO roomDAO = new RoomDAOImpl();
		List<HotelRoom> rooms = roomDAO.getRelatedHotelRooms("deluxe");
		System.out.println(rooms);
	}
	
	public static void testCustomer() throws UnknownHostException {
		CustomerDAO customerDAO = new CustomerDAOImpl();
		List<Customer> customers = customerDAO.getAllCustomers();
		System.out.println(customers);
	}
	
	public static void testActivity() throws UnknownHostException {
		ActivityDAO activityDAO = new ActivityDAOImpl();
		Activity activity = activityDAO.seenNotification("5a85ecc9fbb15400149b934b");
		System.out.println(activity);
		//System.out.println(activityDAO.getAllActivityByUserName("cuongvip1295@gmail.com"));
		
		
//		Activity activity = activityDAO.replyNotification("5a85ecc9fbb15400149b934b");
//		System.out.println(activity);
	}
	
	public static void testFollowUsers() throws UnknownHostException {
		TrackingDAO trackingDAO = new TrackingDAOImpl();
		//System.out.println(trackingDAO.getListFollowUsers());
		//System.out.println(trackingDAO.getExternalIPDetails("115.177.51.122"));
		System.out.println(trackingDAO.getCountryChartData());
		
	}
	
	public static void testAdmin() {
		
	}

	public static void main(String[] args) throws UnknownHostException {
		
	}

}
