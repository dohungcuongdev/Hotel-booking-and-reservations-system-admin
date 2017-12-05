/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.HotelItemDAO;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import static statics.provider.ImageEditor.editImageByID;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class HotelItemDAOImp implements HotelItemDAO {

    protected DBCollection collection;

    @Override
    public void editImage(String id, String img, String img2) {
    	editImageByID(collection, id, "img", img);
    	editImageByID(collection, id, "img2", img2);
    }
    
    @Override
    public void deleteItem(String id) {
        collection.remove(new BasicDBObject().append("_id", new ObjectId(id)));
    }

}
