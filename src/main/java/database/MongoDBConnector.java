/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import statics.AppData;
import org.bson.types.ObjectId;

/**
 *
 * @author Do Hung Cuong
 */
public class MongoDBConnector {

    public static DBCollection createConnection(String collectionName) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(AppData.DATABASE_HOST, AppData.DATABASE_PORT);
        DB db = mongoClient.getDB(AppData.DATABASE);
        return db.getCollection(collectionName);
    }

    public static void updateOne(String collectionName, String originalfield, String value, String updatefield, String updatevalue) throws UnknownHostException {
        if (originalfield.equals("id")) {
            createConnection(collectionName).update(
                    new BasicDBObject("_id", new ObjectId(value)),
                    new BasicDBObject("$set", new BasicDBObject(updatefield, updatevalue))
            );
        } else {
            createConnection(collectionName).update(
                    new BasicDBObject(originalfield, value),
                    new BasicDBObject("$set", new BasicDBObject(updatefield, updatevalue))
            );
        }
    }
}
