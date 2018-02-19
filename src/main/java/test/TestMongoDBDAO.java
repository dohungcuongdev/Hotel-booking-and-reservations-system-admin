package test;

import java.net.UnknownHostException;
import java.util.List;

import daos.impl.mongodb.ActivityDAOImpl;
import daos.impl.mongodb.RoomDAOImpl;
import daos.impl.mongodb.TrackingDAOImpl;
import daos.mongodb.ActivityDAO;
import daos.mongodb.RoomDAO;
import daos.mongodb.TrackingDAO;
import model.mongodb.hotel.HotelRoom;
import model.mongodb.user.tracking.Activity;

public class TestMongoDBDAO {
	
	public static void testRoom() throws UnknownHostException {
		RoomDAO roomDAO = new RoomDAOImpl();
		List<HotelRoom> rooms = roomDAO.getAllRooms();
		HotelRoom room = rooms.get(0);
		//System.out.println(room);
		room.setSize(300);
		roomDAO.updateRoom(room);
		//System.out.println(room);
		room.setName("103");
		roomDAO.findAndAddNewRoom(room);
		//System.out.println(room);
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

	public static void main(String[] args) throws UnknownHostException {
		testFollowUsers();

	}

}
