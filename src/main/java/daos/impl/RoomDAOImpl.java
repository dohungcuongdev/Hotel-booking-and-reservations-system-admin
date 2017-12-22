/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.RoomDAO;
import database.MongoDbConnector;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import model.hotel.HotelRoom;
import services.JsonParserService;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class RoomDAOImpl extends HotelItemDAOImpl<HotelRoom> implements RoomDAO {

	@Autowired
	private JsonParserService jsonParser;

	public RoomDAOImpl() throws UnknownHostException {
		classOfT = HotelRoom.class;
		collection = MongoDbConnector.createConnection("rooms");
	}

	@Override
	public HotelRoom getRoomByID(String id) {
		return (HotelRoom) getHotelItemByID(id);
	}

	@Override
	public HotelRoom getRoomByName(String name) {
		return (HotelRoom) getHotelItemByName(name);
	}

	@Override
	public List<HotelRoom> getAllRooms() {
		return getAllHotelItems();
	}

	@Override
	public List<HotelRoom> getRelatedHotelRooms(String type) {
		return getRelatedHotelItems(type);
	}

	@Override
	public String findAndAddNewRoom(HotelRoom newRoom) {
		return findAndAddNewItem(newRoom);
	}

	@Override
	public List<HotelRoom> getRoomByPage(int page) {
		ArrayList<HotelRoom> rooms = new ArrayList<>();
		DBCursor cursor = collection.find().skip((page - 1) * 6).limit(6);
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			rooms.add(jsonParser.fromJson(obj, classOfT));
		}
		return rooms;
	}

	@Override
	public long getNumRooms() {
		return collection.count();
	}

	@Override
	public void updateRoom(HotelRoom room) {
		updateItem(room);
	}
}
