/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.HotelItemDAO;
import model.hotel.HotelItem;
import services.JsonParserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import static statics.provider.ImageEditor.editImagebyName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class HotelItemDAOImp implements HotelItemDAO {

    protected DBCollection collection;    
    
    @Autowired
    private JsonParserService jsonParser;
    
    @Override
    public <T> T getHotelItemByID(String id, Class<T> classOfT) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(id));
        DBObject obj = collection.findOne(whereQuery);
        return jsonParser.fromJson(obj, classOfT);
    }
    
    @Override
    public <T> T getHotelItemByName(String name, Class<T> classOfT) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", name);
        DBObject obj = collection.findOne(whereQuery);
        return jsonParser.fromJson(obj, classOfT);
    }
    
    @Override
    public <T> List<T> getAllHotelItems(Class<T> classOfT) {
        ArrayList<T> items = new ArrayList<>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	items.add(jsonParser.fromJson(obj, classOfT));
        }
        return items;
    }
    
    @Override
    public <T> List<T> getRelatedHotelItems(String type, Class<T> classOfT) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("type", type);
        ArrayList<T> items = new ArrayList<>();
        DBCursor cursor = collection.find(whereQuery);
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	items.add(jsonParser.fromJson(obj, classOfT));
        }
        return items;
    }
    
    @Override
    public String findAndAddNewItem(HotelItem newItem) {
    	collection.insert(newItem.toDBObject());
    	return newItem.getName();
    }

    @Override
    public void editImage(String name, String img, String img2) {
    	editImagebyName(collection, name, "img", img);
    	editImagebyName(collection, name, "img2", img2);
    }
    
    @Override
    public void deleteItem(String name) {
        collection.remove(new BasicDBObject().append("name", name));
    }
    
    @Override
    public void updateItem(HotelItem item) {
        DBObject document = (DBObject) JSON.parse(jsonParser.toJson(item));
        DBObject searchObject = new BasicDBObject();
        searchObject.put("name", item.getName());
        collection.update(searchObject, document);
    }

}
