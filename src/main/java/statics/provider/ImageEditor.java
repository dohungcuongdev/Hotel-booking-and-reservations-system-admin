/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statics.provider;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 *
 * @author Do Hung Cuong
 */
public class ImageEditor {

    public static void editImagebyName(DBCollection collection, String name, String imgdb, String img) {
        editImage(collection, "name", name, imgdb, img);
    }

    public static void editImagebyUserName(DBCollection collection, String username, String img) {
        editImage(collection, "username", username, "img", img);
    }

    public static void editImage(DBCollection collection, String namedb, String name, String imgdb, String img) {
        BasicDBObject updateFields = new BasicDBObject();
        DBObject searchObject = new BasicDBObject();
        BasicDBObject setQuery = new BasicDBObject();
        searchObject.put(namedb, name);
        if (img != null && !img.equals("")) {
            updateFields.append(imgdb, img);
            setQuery.append("$set", updateFields);
            collection.update(searchObject, setQuery);
        }
    }
}
