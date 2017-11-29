/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.ActivityDAO;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import database.MongoDBConnector;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.user.tracking.Activity;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import static statics.provider.DateTimeCalculator.getDateTime;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class ActivityDAOImpl implements ActivityDAO {

	private final Gson gson = new Gson();
	
    private DBCollection collection;

    {
        try {
            collection = MongoDBConnector.createConnection("activity");
        } catch (UnknownHostException ex) {
            Logger.getLogger(RoomDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Activity> getAllActivity(DBCursor cursor) {
        ArrayList<Activity> activitylist = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            Activity act = gson.fromJson(obj + "", Activity.class);
            act.setId(obj.get("_id") + "");
            act.setTime(getDateTime(obj.get("created_at") + ""));
            activitylist.add(act);
        }
        activitylist.sort(new Activity.CompareDateTime());
        return activitylist;
    }

    @Override
    public List<Activity> getAllActivity() {
        DBCursor cursor = collection.find();
        return getAllActivity(cursor);
    }

    @Override
    public List<Activity> getAllActivityByUserName(String username) {
        DBObject searchObject = new BasicDBObject();
        searchObject.put("username", username);
        DBCursor cursor = collection.find(searchObject);
        return getAllActivity(cursor);
    }

    @Override
    public Activity getActivityBy(String id) {
        DBObject searchObject = new BasicDBObject();
        searchObject.put("_id", new ObjectId(id));
        DBCursor cursor = collection.find(searchObject);
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            Activity act = gson.fromJson(obj + "", Activity.class);
            act.setId(obj.get("_id") + "");
            act.setTime(getDateTime(obj.get("created_at") + ""));
            return act;
        }
        return null;
    }

    @Override
    public List<Activity> getNewListNotification() {
        DBObject searchObject = new BasicDBObject();
        searchObject.put("response", "Not Yet");
        DBCursor cursor = collection.find(searchObject);
        return getAllActivity(cursor);
    }

    @Override
    public void seenNotification(String id) {
        BasicDBObject document = new BasicDBObject();
        document.append("$set", new BasicDBObject().append("response", "Seen"));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(id));
        collection.update(searchQuery, document);
    }
}
