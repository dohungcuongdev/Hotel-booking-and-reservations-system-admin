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
import org.bson.types.ObjectId;
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
        	collection = MongoDBConnector.createConnection("restaurant");
        } catch (UnknownHostException ex) {
            Logger.getLogger(RestaurantDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HotelService getHotelServiceByID(String id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(id));
        DBObject obj = collection.findOne(whereQuery);
        return getHotelServiceWithID(obj);
    }

    @Override
    public List<HotelService> getAllHotelServices() {
        ArrayList<HotelService> services = new ArrayList<>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
            services.add(getHotelServiceWithID(obj));
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
        	DBObject obj = cursor.next();
            services.add(getHotelServiceWithID(obj));
        }
        return services;
    }

    @Override
    public void updateService(HotelService service) {
        DBObject document = (DBObject) JSON.parse(gson.toJson(service));
        DBObject searchObject = new BasicDBObject();
        searchObject.put("_id", new ObjectId(service.getId()));
        collection.update(searchObject, document);
    }
    
    private HotelService getHotelServiceWithID(DBObject obj) {
    	HotelService service = new HotelService();
    	service = gson.fromJson(obj.toString(), HotelService.class);
    	service.setId(obj.get("_id").toString());
        service.initializeServeTime();
    	return service;
    }
}
