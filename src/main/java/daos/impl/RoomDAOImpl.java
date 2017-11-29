/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.RoomDAO;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import database.MongoDBConnector;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import model.hotel.HotelRoom;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class RoomDAOImpl extends HotelItemDAOImp implements RoomDAO {

    private Gson gson = new Gson();

    public RoomDAOImpl() {
        try {
            collection = MongoDBConnector.createConnection("rooms");
        } catch (UnknownHostException ex) {
            Logger.getLogger(RoomDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HotelRoom getRoomByName(String name) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", name);
        DBCursor cursor = collection.find(whereQuery);
        while (cursor.hasNext()) {
            return gson.fromJson(cursor.next().toString(), HotelRoom.class);
        }
        return null;
    }

    @Override
    public List<HotelRoom> getAllRooms() {
        ArrayList<HotelRoom> rooms = new ArrayList<>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            rooms.add(gson.fromJson(cursor.next().toString(), HotelRoom.class));
        }
        return rooms;
    }

    @Override
    public List<HotelRoom> getRelatedHotelRooms(String type) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("type", type);
        ArrayList<HotelRoom> rooms = new ArrayList<>();
        DBCursor cursor = collection.find(whereQuery);
        while (cursor.hasNext()) {
            rooms.add(gson.fromJson(cursor.next().toString(), HotelRoom.class));
        }
        return rooms;
    }

    @Override
    public void updateRoom(HotelRoom room) {
        DBObject document = (DBObject) JSON.parse(gson.toJson(room));
        DBObject searchObject = new BasicDBObject();
        searchObject.put("name", room.getName());
        collection.update(searchObject, document);
    }
}
