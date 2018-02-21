/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb.daoimpls;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import daos.ActivityDAO;
import model.mongodb.user.tracking.Activity;
import statics.helper.DateTimeCalculator;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class ActivityDAOImpl extends JsonParserDAO implements ActivityDAO {
	
	private DBCollection collection; 
	
	public ActivityDAOImpl() throws UnknownHostException {
		collection = MongoDbConnector.createConnection("activity");
	}

	@Override
	public List<Activity> getAllActivity() {
        List<Activity> activities = new ArrayList<>();
        BasicDBObject orderBy = new BasicDBObject();
        orderBy.put("created_at", -1);
        DBCursor cursor = collection.find().sort(orderBy);
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	activities.add(getActivityDB(obj));
        }
        return activities;
	}

	@Override
	public List<Activity> getAllActivityByUserName(String username) {
        List<Activity> activities = new ArrayList<>();
        BasicDBObject whereQuery = new BasicDBObject();
        BasicDBObject orderBy = new BasicDBObject();
        whereQuery.put("username", username);
        orderBy.put("created_at", -1);
        DBCursor cursor = collection.find(whereQuery).sort(orderBy);
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	activities.add(getActivityDB(obj));
        }
        return activities;
	}

	@Override
	public Activity getActivityBy(String id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(id));
        DBObject obj = collection.findOne(whereQuery);
        return getActivityDB(obj);
	}

	@Override
	public List<Activity> getNewListNotification() {
		List<Activity> activities = new ArrayList<>();
		BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("response", "Not Yet");
		BasicDBObject orderBy = new BasicDBObject();
		orderBy.put("created_at", -1);        
		DBCursor cursor = collection.find(whereQuery).sort(orderBy);
        while (cursor.hasNext()) {
        	DBObject obj = cursor.next();
        	activities.add(getActivityDB(obj));
        }
        return activities;
	}

	@Override
	public Activity seenNotification(String id) {
        return updateResponseSttAndGet(id, "Seen");
	}
	
	@Override
	public Activity replyNotification(String id) {
		return updateResponseSttAndGet(id, "Email sent");
	}
	
	private Activity updateResponseSttAndGet(String id, String response) {
        BasicDBObject document = new BasicDBObject();
        document.append("$set", new BasicDBObject().append("response", response));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(id));
        collection.update(searchQuery, document);
        return getActivityBy(id);
	}
	
    private Activity getActivityDB(DBObject obj) {
    	String id = obj.get("_id") + "";
    	String created_at = DateTimeCalculator.getStringICTDateTime(obj.get("created_at"));
    	Activity act = fromJson2(obj, Activity.class);
        act.set_id(id);
        act.setCreated_at(created_at);
        act.setContent(act.getContent().replaceAll("\n", "<br>"));
        return act;
    }
}
