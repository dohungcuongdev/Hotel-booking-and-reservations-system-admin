package test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import daos.RestaurantDAO;
import daos.RoomDAO;
import mongodb.daoimpls.JsonParserDAO;
import mongodb.daoimpls.RestaurantDAOImpl;
import mongodb.daoimpls.RoomDAOImpl;
import mongodb.daoimpls.TrackingDAOImpl;
import model.api.user.tracking.FollowUsers;
import model.mongodb.user.tracking.Activity;
import model.sql.hotel.HotelRoom;
import model.sql.hotel.HotelService;
import statics.constant.AppData;
import statics.helper.DateTimeCalculator;

public class TestUpdateDBOnline {
	
	public static void addRoomDBOnline() throws UnknownHostException {
		JsonParserDAO jsonParser = new JsonParserDAO();
		RoomDAO roomDAO = new RoomDAOImpl();
		
    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    	DB db = mongoClient.getDB("HotelBookingReservationsSystem");
        DBCollection roomCollection =  db.getCollection("rooms");
        List<HotelRoom> rooms = new ArrayList<>();
        DBCursor cursor = roomCollection.find();
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	rooms.add(jsonParser.fromJson(obj, HotelRoom.class));
        }
        
        
        for(int i = 0; i < rooms.size(); i++) {
        	HotelRoom room = rooms.get(i);
        	room.setBooked_by("");
        	room.setCheckin("");
        	room.setCheckout("");
        	room.setStar(0);
        	room.setNumvote(0);
        	roomDAO.findAndAddNewRoom(room);
        	System.out.println(room);
        }
	}
	
	public static void addRestaurantDBOnline() throws UnknownHostException {
		JsonParserDAO jsonParser = new JsonParserDAO();
		RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
		
    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    	DB db = mongoClient.getDB("HotelBookingReservationsSystem");
        DBCollection resCollection =  db.getCollection("restaurant");
        List<HotelService> items = new ArrayList<>();
        DBCursor cursor = resCollection.find();
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	items.add(jsonParser.fromJson(obj, HotelService.class));
        }
        
        
        for(int i = 0; i < items.size(); i++) {
        	HotelService item = items.get(i);
        	restaurantDAO.findAndAddNewService(item);
        	System.out.println(item);
        }
	}
	
	public static void addFollowUsersDBOnline() throws UnknownHostException {
		JsonParserDAO jsonParser = new JsonParserDAO();
		TrackingDAOImpl trackingDAO = new TrackingDAOImpl();
		
    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    	DB db = mongoClient.getDB("HotelBookingReservationsSystem");
        DBCollection trackingCollection =  db.getCollection("follow-users");
        List<FollowUsers> items = new ArrayList<>();
        DBCursor cursor = trackingCollection.find();
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	String created_at = DateTimeCalculator.getStringICTDateTime(obj.get("created_at"));
        	FollowUsers fu = jsonParser.fromJson2(obj, FollowUsers.class);
        	fu.setCreated_at(created_at);
        	items.add(fu);
        }
        
        
        for(int i = 0; i < items.size(); i++) {
        	FollowUsers item = items.get(i);
        	trackingDAO.addNewItem(item);
        	System.out.println(item);
        }
	}

	public static void main(String[] args) throws UnknownHostException {
		addRoomDBOnline();
        

	}

}