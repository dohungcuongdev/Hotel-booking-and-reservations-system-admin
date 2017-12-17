/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.RoomDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import database.MongoDBConnector;
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
public class RoomDAOImpl extends HotelItemDAOImp implements RoomDAO {
    
    @Autowired
    private JsonParserService jsonParser;

    public RoomDAOImpl() throws UnknownHostException {
        collection = MongoDBConnector.createConnection("rooms");
    }

    @Override
    public HotelRoom getRoomByID(String id) {
    	return (HotelRoom) getHotelItemByID(id, HotelRoom.class);
    }
    
    @Override
    public HotelRoom getRoomByName(String name) {
        return (HotelRoom) getHotelItemByName(name, HotelRoom.class);
    }

    @Override
    public List<HotelRoom> getAllRooms() {
        return getAllHotelItems(HotelRoom.class);
    }

    @Override
    public List<HotelRoom> getRelatedHotelRooms(String type) {
        return getRelatedHotelItems(type, HotelRoom.class);
    }
    
    @Override
    public String findAndAddNewRoom(HotelRoom newRoom) {
    	return findAndAddNewItem(newRoom);
    }
    
    @Override
    public List<HotelRoom> getRoomByPage(int skip, int limit) {
        ArrayList<HotelRoom> rooms = new ArrayList<>();
        DBCursor cursor = collection.find().skip(skip).limit(limit);
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	rooms.add(jsonParser.fromJson(obj, HotelRoom.class));
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
