/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import static statics.provider.ImageEditor.editImagebyName;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import daos.HotelItemDAO;
import model.hotel.HotelItem;
import services.JsonParserService;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public abstract class HotelItemDAOImpl<T> implements HotelItemDAO<T> {

    protected DBCollection collection;    
    
    protected Class<T> classOfT;
    
    @Autowired
    private JsonParserService jsonParser;
    
    @Override
    public T getHotelItemByID(String id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(id));
        DBObject obj = collection.findOne(whereQuery);
        return (T) jsonParser.fromJson(obj, classOfT);
    }
    
    @Override
    public T getHotelItemByName(String name) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", name);
        DBObject obj = collection.findOne(whereQuery);
        return (T) jsonParser.fromJson(obj, classOfT);
    }
    
    @Override
    public List<T> getAllHotelItems() {
        ArrayList<T> items = new ArrayList<>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	items.add((T) jsonParser.fromJson(obj, classOfT));
        }
        return items;
    }
    
    @Override
    public List<T> getRelatedHotelItems(String type) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("type", type);
        ArrayList<T> items = new ArrayList<>();
        DBCursor cursor = collection.find(whereQuery);
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	items.add((T) jsonParser.fromJson(obj, classOfT));
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
    	item.setId(null);
        DBObject document = (DBObject) JSON.parse(jsonParser.toJson(item));
        DBObject searchObject = new BasicDBObject();
        searchObject.put("name", item.getName());
        collection.update(searchObject, document);
    }

}
