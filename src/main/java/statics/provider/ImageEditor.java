/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statics.provider;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 *
 * @author Do Hung Cuong
 */
public class ImageEditor {

    public static void editImagebyName(DBCollection collection, String name, String imgdb, String img) {
        DBObject searchObject = new BasicDBObject();
        searchObject.put("name", name);
        editImage(collection, searchObject, imgdb, img);
    }

    public static void editImagebyUserName(DBCollection collection, String username, String img) {
    	DBObject searchObject = new BasicDBObject();
        searchObject.put("username", username);
        editImage(collection, searchObject, "img", img);
    }
    
    public static void editImageByID(DBCollection collection, String id, String imgdb, String img) {
    	DBObject searchObject = new BasicDBObject();
        searchObject.put("_id", new ObjectId(id));
        editImage(collection, searchObject, imgdb, img);
    }

    public static void editImage(DBCollection collection, DBObject searchObject, String imgdb, String img) {
        BasicDBObject updateFields = new BasicDBObject();
        BasicDBObject setQuery = new BasicDBObject();
        if (img != null && !img.equals("")) {
            updateFields.append(imgdb, img);
            setQuery.append("$set", updateFields);
            collection.update(searchObject, setQuery);
        }
    }
}
