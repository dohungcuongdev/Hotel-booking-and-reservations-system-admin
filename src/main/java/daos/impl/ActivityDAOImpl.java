/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;


import java.util.List;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import daos.ActivityDAO;
import model.user.tracking.Activity;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class ActivityDAOImpl extends APIDAOImpl implements ActivityDAO {

	private final Gson gson = new Gson();

	@Override
	public List<Activity> getAllActivity() {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/activity/"), new TypeToken<List<Activity>>(){}.getType());
	}

	@Override
	public List<Activity> getAllActivityByUserName(String username) {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/activity/username/" + username), new TypeToken<List<Activity>>(){}.getType());
	}

	@Override
	public Activity getActivityBy(String id) {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/activity/" + id), Activity.class);
	}

	@Override
	public List<Activity> getNewListNotification() {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/activity/response/not-yet"), new TypeToken<List<Activity>>(){}.getType());
	}

	@Override
	public Activity seenNotification(String id) {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/activity/seen-notification/" + id), Activity.class);
	}
	
	@Override
	public Activity replyNotification(String id) {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/activity/reply-notification" + id), Activity.class);
	}
}
