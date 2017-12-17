/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;


import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;

import daos.ActivityDAO;
import daos.CustomerDAO;
import model.user.tracking.Activity;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class ActivityDAOImpl implements ActivityDAO {

	@Autowired
	private CustomerDAO customerDAO;
	private final Gson gson = new Gson();
	
	public String getStringAPI(String api) {
		//env.getProperty(api)
		HttpGet httpGetKeyAndId = new HttpGet(api);
		String jsonData = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpGetKeyAndId);) {
			HttpEntity entity = response.getEntity();
			jsonData = EntityUtils.toString(entity);
		} catch (IOException e) {
			System.out.println("API not found");
		}
		return jsonData;
	}

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
	public void seenNotification(String id) {
		updateResponseNotification(id, "Seen");
	}
	
	@Override
	public void replyNotification(String id) {
		updateResponseNotification(id, "Email sent");
	}
	
	private void updateResponseNotification(String id, String response) {
//		HttpClient httpClient = new DefaultHttpClient();
//		JSONObject keyArg = new JSONObject();
//		HttpPut httpPut = new HttpPut();
//		httpPut.addHeader("Content-Type", "application/json");
//		httpPut.addHeader("Content-Length", "LENGTH");
//		httpPut.addHeader("Key", "ad412f36a2eecbcd5c0e323e");
//		httpPut.setEntity((HttpEntity) keyArg);
//        HttpResponse response = httpClient.execute(httpPut);
		
//		BasicDBObject document = new BasicDBObject();
//		document.append("$set", new BasicDBObject().append("response", response));
//		BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(id));
//		collection.update(searchQuery, document);
	}
}
