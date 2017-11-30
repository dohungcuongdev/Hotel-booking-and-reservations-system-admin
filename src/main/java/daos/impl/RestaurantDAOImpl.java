/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.RestaurantDAO;
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

import model.hotel.HotelService;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class RestaurantDAOImpl extends HotelItemDAOImp implements RestaurantDAO {

    private final Gson gson = new Gson();

    public RestaurantDAOImpl() {
        try {
        	collection = MongoDBConnector.createConnection("hotel-services");
        } catch (UnknownHostException ex) {
            Logger.getLogger(RestaurantDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HotelService getHotelServiceByName(String name) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", name);
        DBCursor cursor = collection.find(whereQuery);
        HotelService service = new HotelService();
        while (cursor.hasNext()) {
            service = gson.fromJson(cursor.next() + "", HotelService.class);
            service.initializeServeTime();
        }
        return service;
    }

    @Override
    public List<HotelService> getAllHotelServices() {
        ArrayList<HotelService> services = new ArrayList<>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            HotelService service = gson.fromJson(cursor.next() + "", HotelService.class);
            service.initializeServeTime();
            services.add(service);
        }
        return services;
    }

    @Override
    public List<HotelService> getRelatedHotelServices(String type) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("type", type);
        ArrayList<HotelService> services = new ArrayList<>();
        DBCursor cursor = collection.find(whereQuery);
        while (cursor.hasNext()) {
            HotelService service = gson.fromJson(cursor.next() + "", HotelService.class);
            service.initializeServeTime();
            services.add(service);
        }
        return services;
    }

    @Override
    public void updateService(HotelService service) {
        DBObject document = (DBObject) JSON.parse(gson.toJson(service));
        DBObject searchObject = new BasicDBObject();
        searchObject.put("name", service.getName());
        collection.update(searchObject, document);
    }
}
