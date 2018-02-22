/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb.daoimpls;

import static statics.helper.StringUtils.upperFirstChar;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import daos.TrackingDAO;
import model.api.user.tracking.ExternalIP;
import model.api.user.tracking.FollowUsers;
import model.api.user.tracking.GeoLocation;
import model.api.user.tracking.CountryChartData;
import model.api.user.tracking.PageAccessChartData;
import statics.helper.GeoLookup;

/**
 *
 * @author HUNGCUONG
 */

@Repository
public class TrackingDAOImpl extends JsonParserDAO implements TrackingDAO {

	private DBCollection collection;

	public TrackingDAOImpl() throws UnknownHostException {
		collection = MongoDbConnector.createConnection("follow-users");
	}

	@Override
	public ExternalIP getExternalIPDetails(String external_ip_address) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("external_ip_address", external_ip_address);
		DBObject obj = collection.findOne(whereQuery);
		return fromJson(obj.toString(), ExternalIP.class);
	}

	@Override
	public List<FollowUsers> getListFollowUsers() {
		List<FollowUsers> listFU = new ArrayList<>();
		BasicDBObject orderBy = new BasicDBObject();
		orderBy.put("created_at", -1);
		DBCursor cursor = collection.find().sort(orderBy);
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			listFU.add(getFollowUsersDB(obj));
		}
		return listFU;
	}

	@Override
	public List<PageAccessChartData> getPageAccessChartData() {
		String query = "[{ \"$group\": { _id: \"$page_access\", count: { $sum: 1 } } }]";
		return getPageAccessChartData(query);
	}

	@Override
	public List<PageAccessChartData> getPageAccessChartDataByIP(String ipaddress) {
		String query = "[{ $match: { user_ip_address: '" + ipaddress
				+ "' } }, { \"$group\": { _id: \"$page_access\", count: { $sum: 1 } } }]";
		return getPageAccessChartData(query);
	}

	@Override
	public List<PageAccessChartData> getPageAccessChartDataByUsername(String username) {
		String query = "[{ $match: { username: '" + username
				+ "' } }, { \"$group\": { _id: \"$page_access\", count: { $sum: 1 } } }]";
		return getPageAccessChartData(query);
	}

	@Override
	public List<CountryChartData> getCountryChartData() {
		String query = "[{ \"$group\": { _id: \"$external_ip_address\", count: { $sum: 1 } } }]";
		List<DBObject> q = (List<DBObject>) JSON.parse(query);
		Iterable<DBObject> result = collection.aggregate(q).results();
		Iterator iterator = result.iterator();
		List<CountryChartData> listGeo = new ArrayList<>();
		List<String> temp = new ArrayList<>();
		while (iterator.hasNext()) {
			CountryChartData geo = new CountryChartData();
			DBObject element = (DBObject) iterator.next();
			String exPI = element.get("_id").toString();
			int visitTime = Integer.parseInt(element.get("count").toString());
			GeoLocation geoLocation = GeoLookup.getLocation(exPI);
			String countryCode = geoLocation.getCountryCode();
			geo.setCountryCode(countryCode);
			geo.setCountryName(geoLocation.getCountryName());
			if (temp.contains(countryCode)) {
				int index = temp.indexOf(countryCode);
				geo.setVisitTime(visitTime + listGeo.get(index).getVisitTime());
				listGeo.set(index, geo);
			} else {
				temp.add(countryCode);
				geo.setVisitTime(visitTime);
				listGeo.add(geo);
			}
		}
		return listGeo;
	}

	private List<PageAccessChartData> getPageAccessChartData(String query) {
		List<DBObject> q = (List<DBObject>) JSON.parse(query);
		Iterable<DBObject> result = collection.aggregate(q).results();
		Iterator iterator = result.iterator();
		List<PageAccessChartData> listPAData = new ArrayList<>();
		List<String> keys = new ArrayList<>();
		while (iterator.hasNext()) {
			DBObject element = (DBObject) iterator.next();
			String page_access = mergeKey(element.get("_id").toString());
			int visit_time = Integer.parseInt(element.get("count").toString());
			if (keys.contains(page_access)) {
				int index = keys.indexOf(page_access);
				listPAData.set(index,
						new PageAccessChartData(page_access, listPAData.get(index).getVisit_time() + visit_time));
			} else {
				keys.add(page_access);
				listPAData.add(new PageAccessChartData(page_access, visit_time));
			}
		}
		return listPAData;
	}

	private String mergeKey(String key) {
		if (key.contains("/rooms-tariff"))
			return "View Room";
		if (key.contains("book room"))
			return "Book Room";
		if (key.contains("cancel room"))
			return "Cancel Room";
		if (key.contains("filter in rooms") || key.contains("/room-details") || key.contains("search in rooms")
				|| key.contains("click image in rooms"))
			return "Find Rooms";
		if (key.contains("filter in restaurant") || key.contains("/hotel-services")
				|| key.contains("search in restaurant") || key.contains("click image in restaurant"))
			return "View Restaurant";
		if (key.contains("feedback"))
			return "Send Feedback";
		if (key.contains("click link /"))
			return "View " + upperFirstChar(key.substring(12)) + " Page";
		if (key.contains("login"))
			return "Login";
		if (key.contains("sign up"))
			return "Sign Up";
		if (key.contains("register"))
			return "Register";
		if (key.contains("/logout"))
			return "Logout";
		if (key.contains("/change-password") || key.contains("Change password"))
			return "Change password";
		return key;
	}

	private FollowUsers getFollowUsersDB(DBObject obj) {
		String created_at = obj.get("created_at") + "";
		FollowUsers fu = fromJson2(obj, FollowUsers.class);
		fu.setCreated_at(created_at);
		return fu;
	}

	public void addNewItem(FollowUsers newFU) {
		BasicDBObject sortQuery = new BasicDBObject();
		collection.insert(newFU.toDBObject());
	}
}
