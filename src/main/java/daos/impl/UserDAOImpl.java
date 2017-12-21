/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import static statics.provider.StringUtils.upperFirstChar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import daos.UserDAO;
import model.user.tracking.ExternalIP;
import model.user.tracking.FollowUsers;
import model.user.tracking.PageAccessData;

/**
 *
 * @author HUNGCUONG
 */

@Repository
public class UserDAOImpl extends APIDAOImpl implements UserDAO {

	private final Gson gson = new Gson();

	@Override
	public ExternalIP getExternalIPDetails(String external_ip_address) {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/follow-users/externalIP/" + external_ip_address),
				ExternalIP.class);
	}

	@Override
	public List<FollowUsers> getListFollowUsers() {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/follow-users/"),
				new TypeToken<List<FollowUsers>>() {
				}.getType());
	}

	@Override
	public Map getFollowUsersMap(List<FollowUsers> list) {
		Map m = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i).getPage_access();
			if (m.containsKey(key)) {
				m.replace(key, Integer.parseInt(m.get(key) + "") + 1);
			} else {
				m.put(key, 1);
			}
		}
		return m;
	}
	@Override
	public List<PageAccessData> getPageAccessChartData() {
		return getPageAccessChartData("http://localhost:3000/api/follow-users/statistics/PageAccess/");

	}

	@Override
	public List<PageAccessData> getPageAccessChartDataByIP(String ipaddress) {
		return getPageAccessChartData("http://localhost:3000/api/follow-users/statistics/PageAccess/" + ipaddress);
	}

	@Override
	public Map getFollowUsersMapByOneIP(List<FollowUsers> list, String ip) {
		Map m = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUser_ip_address().equals(ip)) {
				String key = list.get(i).getPage_access();
				if (m.containsKey(key)) {
					m.replace(key, Integer.parseInt(m.get(key) + "") + 1);
				} else {
					m.put(key, 1);
				}
			}
		}
		return m;
	}
	
	private int getIndexByKey(List<String> key, String keyword) {
		for(int i = 0; i < key.size(); i++)
			if(key.get(i).equals(keyword))
				return i;
		return -1;
	}
	
	private List<PageAccessData> getPageAccessChartData(String api) {
		List<PageAccessData> listPAData = new ArrayList<>();
		List<String> key = new ArrayList<>();
		try {
			JSONArray jsonArr = new JSONArray(getStringAPI(api));
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject o = jsonArr.getJSONObject(i);
				String page_access = mergeKey(o.getString("_id"));
				int visit_time = o.getInt("count");
				if(key.contains(page_access)) {
					int index = getIndexByKey(key, page_access);
					listPAData.set(index, new PageAccessData(page_access, listPAData.get(index).getVisit_time() + visit_time));
				} else {
					key.add(page_access);
					listPAData.add(new PageAccessData(page_access, visit_time));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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

}
