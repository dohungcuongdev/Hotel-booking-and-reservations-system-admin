/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import database.MongoDBConnector;

/**
 *
 * @author Do Hung Cuong
 */

public class Test {
	public static void main(String[] args) throws UnknownHostException {
		Gson gson = new Gson();
		DBCollection testcollection = MongoDBConnector.createConnection("test");
		BasicDBObject whereQuery = new BasicDBObject();
		String id = "5a24e2ad55caf5a7455cf654";
		whereQuery.put("_id", new ObjectId(id));
		DBCursor cursor = testcollection.find(whereQuery);
		TestModel testModel = new TestModel();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			testModel = gson.fromJson(obj + "", TestModel.class);
		}
		testModel.setId(id);
		System.out.println(testModel);
	}

}