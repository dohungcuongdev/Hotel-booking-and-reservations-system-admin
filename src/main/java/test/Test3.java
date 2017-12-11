package test;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import database.MongoDBConnector;
import model.hotel.HotelRoom;

public class Test3 {

/**
 *
 * @author Do Hung Cuong
 */
	
	public static void main(String[] args) throws UnknownHostException {
		Gson gson = new Gson();
		DBCollection testcollection = MongoDBConnector.createConnection("rooms");
		BasicDBObject whereQuery = new BasicDBObject();
		HotelRoom newRoom = new HotelRoom("108", "100", 100, "2", "available", "img1.jpg", "img2.jpg", "deluxe", "details", "wifi", "400");
		testcollection.insert(newRoom.toDBObject());
		System.out.println(testcollection.findOne());
	}
}
