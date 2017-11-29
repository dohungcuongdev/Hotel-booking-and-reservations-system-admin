/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.HotelItemDAO;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import static statics.provider.ImageEditor.editImagebyName;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class HotelItemDAOImp implements HotelItemDAO {

    protected DBCollection collection;

    @Override
    public void editImage(String name, String img, String img2) {
        editImagebyName(collection, name, "img", img);
        editImagebyName(collection, name, "img2", img2);
    }
    
    @Override
    public void deleteItem(String name) {
        collection.remove(new BasicDBObject().append("name", name));
    }

}
