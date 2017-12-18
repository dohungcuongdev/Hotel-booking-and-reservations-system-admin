/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import static statics.provider.MathCalculator.round;
import static statics.provider.StringUtils.upperFirstChar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import daos.UserDAO;
import model.user.tracking.ChartData;
import model.user.tracking.ExternalIP;
import model.user.tracking.FollowUsers;

/**
 *
 * @author HUNGCUONG
 */

@Repository
public class UserDAOImpl extends APIDAOImpl implements UserDAO {

	private final Gson gson = new Gson();
	
	@Override
	public ExternalIP getExternalIPDetails(String external_ip_address) {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/follow-users/externalIP/" + external_ip_address), ExternalIP.class);
	}

	@Override
	public List<FollowUsers> getListFollowUsers() {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/follow-users/"), new TypeToken<List<FollowUsers>>(){}.getType());
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
	public Map getPageAccessChartData(List<FollowUsers> list) {
		Map m = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			String key = mergeKey(list.get(i).getPage_access());
			if (m.containsKey(key)) {
				m.replace(key, Integer.parseInt(m.get(key) + "") + 1);
			} else {
				m.put(key, 1);
			}
		}
		return m;
	}

	@Override
	public Map getPageAccessChartDataByIP(String ipaddress, List<FollowUsers> list) {
		Map m = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUser_ip_address().equals(ipaddress)) {
				String key = mergeKey(list.get(i).getPage_access());
				if (m.containsKey(key)) {
					m.replace(key, Integer.parseInt(m.get(key) + "") + 1);
				} else {
					m.put(key, 1);
				}
			}
		}
		return m;
	}
	
	private String mergeKey(String key) {
		if (key.contains("/rooms-tariff"))
			key = "View Room";
		else if (key.contains("book room"))
			key = "Book Room";
		else if (key.contains("cancel room"))
			key = "Cancel Room";
		else if (key.contains("filter in rooms") || key.contains("/room-details") || key.contains("search in rooms")
				|| key.contains("click image in rooms"))
			key = "Find Rooms";
		else if (key.contains("filter in restaurant") || key.contains("/hotel-services")
				|| key.contains("search in restaurant") || key.contains("click image in restaurant"))
			key = "View Restaurant";
		else if (key.contains("feedback"))
			key = "Send Feedback";
		else if (key.contains("click link /"))
			key = "View " + upperFirstChar(key.substring(12)) + " Page";
		else if (key.contains("login"))
			key = "Login";
		else if (key.contains("sign up"))
			key = "Sign Up";
		else if (key.contains("register"))
			key = "Register";
		else if (key.contains("/logout"))
			key = "Logout";
		else if (key.contains("/change-password") || key.contains("Change password"))
			key = "Change password";
		return key;
	}

	@Override
	public String getJSONPageAccess(Map m) {
		StringBuilder jsonArray = new StringBuilder("[");
		m.keySet().stream().forEach((key) -> {
			jsonArray.append("{\"page_access\" : \"").append(key).append("\", \"visit_time\" : ").append(m.get(key)).append(", \"color\" : \"#CD0D74\"},");
		});
		return jsonArray.append("]").toString();
	}
	
	@Override
	public Map getMapByExternalIP(List<FollowUsers> list) {
		Map m = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i).getExternal_ip_address();
			if (m.containsKey(key)) {
				m.replace(key, Integer.parseInt(m.get(key) + "") + 1);
			} else {
				m.put(key, 1);
			}
		}
		return m;
	}

	@Override
	public Map getFollowUsersMapByIP(List<FollowUsers> list) {
		Map m = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i).getUser_ip_address();
			if (m.containsKey(key)) {
				m.replace(key, Integer.parseInt(m.get(key) + "") + 1);
			} else {
				m.put(key, 1);
			}
		}
		return m;
	}

	@Override
	public Map getMapFollowUsersCountry(List<FollowUsers> list) {
		Map m = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i).getCountry();
			if (m.containsKey(key)) {
				m.replace(key, Integer.parseInt(m.get(key) + "") + 1);
			} else {
				m.put(key, 1);
			}
		}
		return m;
	}

	private int getTotalChartData(List<FollowUsers> list) {
		int result = 0;
		Map<String, Object> m = getMapFollowUsersCountry(list);
		for (Map.Entry<String, Object> entry : m.entrySet()) {
			result += Integer.parseInt(entry.getValue().toString());
		}
		return result;
	}

	@Override
	public List<ChartData> getListFollowUsersChartData(List<FollowUsers> list) {
		List<ChartData> l = new ArrayList();
		int totalChartData = getTotalChartData(list);
		if (totalChartData == 0)
			return l;
		Map<String, Object> m = getMapFollowUsersCountry(list);
		for (Map.Entry<String, Object> entry : m.entrySet()) {
			int quantity = Integer.parseInt(entry.getValue().toString());
			l.add(new ChartData(entry.getKey(), quantity, round(quantity * 100.0 / totalChartData)));
		}
		return l;
	}

	@Override
	public String getFollowUsersCountry(List<FollowUsers> list) {
		StringBuilder jsonArray = new StringBuilder("[");
		Map m = getMapFollowUsersCountry(list);
		m.keySet().stream().forEach((key) -> {
			jsonArray.append("{\"country\" : \"").append(key).append("\", \"visits\" : ").append(m.get(key)).append("},");
		});
		return jsonArray.append("]").toString();
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
}
