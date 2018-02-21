/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.daoimpls;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.gson.reflect.TypeToken;

import daos.ActivityDAO;
import model.mongodb.user.tracking.Activity;
import statics.constant.APIData;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class ActivityDAOImpl extends APIDAO implements ActivityDAO {

	@Override
	public List<Activity> getAllActivity() {
		return getJsonData(getStringAPI(APIData.ACTIVITY_API), new TypeToken<List<Activity>>(){}.getType());
	}

	@Override
	public List<Activity> getAllActivityByUserName(String username) {
		return getJsonData(getStringAPI(APIData.ACTIVITY_USERNAME_API + username), new TypeToken<List<Activity>>(){}.getType());
	}

	@Override
	public Activity getActivityBy(String id) {
		return getJsonData(getStringAPI(APIData.ACTIVITY_API + id), Activity.class);
	}

	@Override
	public List<Activity> getNewListNotification() {
		return getJsonData(getStringAPI(APIData.ACTIVITY_NO_RESPONSE_API), new TypeToken<List<Activity>>(){}.getType());
	}

	@Override
	public Activity seenNotification(String id) {
		return getJsonData(getStringAPI(APIData.SEEN_NOTIFICATION_API + id), Activity.class);
	}
	
	@Override
	public Activity replyNotification(String id) {
		return getJsonData(getStringAPI(APIData.REPLY_NOTIFICATION_API + id), Activity.class);
	}
}
