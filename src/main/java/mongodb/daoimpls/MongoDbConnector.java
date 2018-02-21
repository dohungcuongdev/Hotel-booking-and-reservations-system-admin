/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb.daoimpls;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;
import statics.constant.AppData;
import org.bson.types.ObjectId;

/**
 *
 * @author Do Hung Cuong
 */
public class MongoDbConnector {

    public static DBCollection createConnection(String collectionName) throws UnknownHostException {
    	
    	MongoClient mongoClient = new MongoClient(new MongoClientURI(AppData.MONGGO_URL));
    	DB db = mongoClient.getDB(AppData.DATABASE);
        return db.getCollection(collectionName);
    }

    public static void updateOne(String collectionName, String originalfield, String value, String updatefield, String updatevalue) throws UnknownHostException {
    	BasicDBObject dbObj = originalfield.equals("id") ? new BasicDBObject("_id", new ObjectId(value)): new BasicDBObject(originalfield, value);
    	createConnection(collectionName).update(dbObj, new BasicDBObject("$set", new BasicDBObject(updatefield, updatevalue)));
    }
}
