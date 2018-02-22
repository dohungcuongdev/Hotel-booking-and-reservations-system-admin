/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb.daoimpls;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import daos.HotelItemDAO;
import model.sql.hotel.HotelItem;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public abstract class HotelItemDAOImpl<T> extends JsonParserDAO implements HotelItemDAO<T> {
    
    protected DBCollection collection;    
    
    protected Class<T> classOfT;
    
    @Override
    public T getHotelItemByID(int id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("id", id);
        DBObject obj = collection.findOne(whereQuery);
        return (T) fromJson(obj, classOfT);
    }
    
    public T getHotelItemByID(String _id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(_id));
        DBObject obj = collection.findOne(whereQuery);
        return (T) fromJson(obj, classOfT);
    }
    
    @Override
    public T getHotelItemByName(String name) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", name);
        DBObject obj = collection.findOne(whereQuery);
        return (T) fromJson(obj, classOfT);
    }
    
    @Override
    public List<T> getAllHotelItems() {
        List<T> items = new ArrayList<>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
//        	T t = (T) fromJson(obj, classOfT);
//        	System.out.println(t);
        	items.add((T) fromJson(obj, classOfT));
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
        	items.add((T) fromJson(obj, classOfT));
        }
        return items;
    }
    
    @Override
    public String findAndAddNewItem(HotelItem newItem) {
		if(!isExists(newItem)) {
			BasicDBObject sortQuery = new BasicDBObject();
			sortQuery.put("id", -1);
			DBCursor cursor = collection.find().sort(sortQuery).limit(1);
			int id = cursor.hasNext() ?  Integer.parseInt(cursor.next().get("id").toString()) + 1: 1;
			newItem.setId(id);
	    	collection.insert(newItem.toDBObject());
	    	return newItem.getName();
		}
		return null;
    }

    @Override
    public void editImage(String name, String img, String img2) {
    	if(!img.equals("") || !img2.equals("")) {
    		if(!img.equals(""))
    			updateImage(name,"img", img);
    		if(!img2.equals(""))
    			updateImage(name,"img2", img2);
    	}
    }
    
    private void updateImage(String name, String imgdb, String imgFile) {
    	BasicDBObject document = new BasicDBObject();
        document.append("$set", new BasicDBObject().append(imgdb, imgFile));
        BasicDBObject searchQuery = new BasicDBObject().append("name", name);
        collection.update(searchQuery, document);
    }
    
    @Override
    public void deleteItem(int id) {
    	collection.remove(new BasicDBObject().append("id", id));
    }
    
    public void deleteItem(String id) {
    	collection.remove(new BasicDBObject().append("_id", new ObjectId(id)));
    }
    
    @Override
    public void updateItem(HotelItem item) {
        DBObject document = parseJSON(toJson(item));
        DBObject searchObject = new BasicDBObject();
        searchObject.put("name", item.getName());
        collection.update(searchObject, document);
    }	
    
    private boolean isExists (HotelItem item) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", item.getName());
        DBObject obj = collection.findOne(whereQuery);
        return obj != null;
	}
}
