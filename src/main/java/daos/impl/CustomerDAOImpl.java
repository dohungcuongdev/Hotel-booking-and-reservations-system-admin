/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import static statics.provider.MathCalculator.round;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import daos.ActivityDAO;
import daos.CustomerDAO;
import daos.UserDAO;
import model.user.Customer;
import model.user.tracking.ActionTracking;
import model.user.tracking.Activity;
import model.user.tracking.CustomerBehavior;
import model.user.tracking.DataCollection;
import model.user.tracking.Feedback;
import statics.AppData;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
@Configuration
@PropertySource("classpath:database.properties")
public class CustomerDAOImpl implements CustomerDAO {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	private Environment env;

	@Autowired
	private ActivityDAO activityDAO;

	@Autowired
	private UserDAO userDAO;
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
	public Customer getCustomerByUsername(String username) {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/users/username/" + username), Customer.class);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return gson.fromJson(getStringAPI("http://localhost:3000/api/users/"), new TypeToken<List<Customer>>(){}.getType());
	}

	@Override
	public boolean checkexsitCustomer(String username) {
		return getAllCustomers().stream().anyMatch((customer) -> (customer.getUsername().equals(username)));
	}

	@Override
	public List<String> getDateVisit(String username) {
		List<String> dateVisits = new ArrayList<>();
		userDAO.getListFollowUsers().stream()
				.filter((fu) -> (fu.getUsername() != null && fu.getUsername().equals(username)))
				.map((fu) -> fu.getCreated_at().toString().substring(0, 10))
				.forEach((dateVisit) -> {
					if (dateVisits.isEmpty() || !dateVisits.contains(dateVisit)) {
						dateVisits.add(dateVisit);
					}
				});
		return dateVisits;
	}

	@Override
	public ActionTracking getActionTrackingByUsername(String username) {
		List<DataCollection> roombooked = new ArrayList<>();
		List<DataCollection> roomcanceled = new ArrayList<>();
		List<Feedback> feedbackroom = new ArrayList<>();
		List<Feedback> feedbackservice = new ArrayList<>();
		int starFBR = 0, countFBR = 0;
		int starFB = 0, countFB = 0;
		List<Activity> activities = activityDAO.getAllActivityByUserName(username);
		for (Activity act : activities) {
			String date = act.getICTStrDateTime(act.getCreated_at());;
			if (act.getName().equals(AppData.ACTIVITY[0])) {
				roombooked.add(new DataCollection(date, act.getDetails().substring(12)));
			}
			if (act.getName().equals(AppData.ACTIVITY[1])) {
				roomcanceled.add(new DataCollection(date, act.getDetails().substring(20)));
			}
			if (act.getName().equals(AppData.ACTIVITY[2])) {
				String room = act.getNote().substring(12, 15);
				int star = act.getNote().charAt(21) - 48;
				String feedback = act.getContent();
				feedbackroom.add(new Feedback(date, room, star, feedback));
				starFBR += star;
				++countFBR;
			}
			if (act.getName().equals(AppData.ACTIVITY[3])) {
				int star = act.getNote().charAt(12) - 48;
				String feedback = act.getContent();
				feedbackservice.add(new Feedback(date, star, feedback));
				starFB += star;
				++countFB;
			}
		}
		double avgfeedbackRoom = round(starFBR * 1.0 / countFBR);
		double avgFeedbackSV = round(starFB * 1.0 / countFB);
		return new ActionTracking(roombooked, roomcanceled, feedbackroom, feedbackservice, avgfeedbackRoom,
				avgFeedbackSV);
	}

	@Override
	public List<DataCollection> getListRoomBooked(String username) {
		List<DataCollection> roombooked = new ArrayList<>();
		activityDAO.getAllActivityByUserName(username).stream()
				.filter((act) -> (act.getName().equals(AppData.ACTIVITY[0]))).forEach((act) -> {
					roombooked.add(new DataCollection(act.getICTStrDateTime(act.getCreated_at()), act.getDetails().substring(12)));
				});
		return roombooked;
	}

	@Override
	public List<DataCollection> getListRoomCanceled(String username) {
		List<DataCollection> roomcanceled = new ArrayList<>();
		activityDAO.getAllActivityByUserName(username).stream()
				.filter((act) -> (act.getName().equals(AppData.ACTIVITY[1]))).forEach((act) -> {
					roomcanceled.add(new DataCollection(act.getICTStrDateTime(act.getCreated_at()), act.getDetails().substring(20)));
				});
		return roomcanceled;
	}

	@Override
	public double getAvgStarRoomFeedback(String username) {
		int star = 0, count = 0;
		for (Activity act : activityDAO.getAllActivityByUserName(username)) {
			if (act.getName().equals(AppData.ACTIVITY[2])) {
				star += act.getNote().charAt(21) - 48;
				++count;
			}
		}
		if (count == 0)
			return 0;
		return round(star * 1.0 / count);
	}

	public int getTotalStarRoomFeedback(String username) {
		int star = 0;
		star = activityDAO.getAllActivityByUserName(username).stream()
				.filter((act) -> (act.getName().equals(AppData.ACTIVITY[2])))
				.map((act) -> act.getNote().charAt(21) - 48).reduce(star, Integer::sum);
		return star;
	}

	@Override
	public double getAvgStarFeedback(String username) {
		int star = 0, count = 0;
		for (Activity act : activityDAO.getAllActivityByUserName(username)) {
			if (act.getName().equals(AppData.ACTIVITY[3])) {
				star += act.getNote().charAt(12) - 48;
				++count;
			}
		}
		if (count == 0)
			return 0;
		return round(star * 1.0 / count);
	}

	public double getTotalStarFeedback(String username) {
		int star = 0;
		star = activityDAO.getAllActivityByUserName(username).stream()
				.filter((act) -> (act.getName().equals(AppData.ACTIVITY[3])))
				.map((act) -> act.getNote().charAt(12) - 48).reduce(star, Integer::sum);
		return star;
	}

	@Override
	public List<CustomerBehavior> getDataCollection() {
		List<CustomerBehavior> cdc = new ArrayList<>();
		List<Customer> customers = getAllCustomers();
		customers.stream().forEach((cus) -> {
			String un = cus.getUsername();
			cdc.add(new CustomerBehavior(cus, getActionTrackingByUsername(un)));
		});
		return cdc;
	}

	@Override
	public CustomerBehavior getOneDataCollection(String username) {
		return new CustomerBehavior(getCustomerByUsername(username), getDateVisit(username),
				getActionTrackingByUsername(username));
	}

	@Override
	public List<Feedback> getListFeedbackRoom(String username) {
		List<Feedback> fbr = new ArrayList<>();
		activityDAO.getAllActivityByUserName(username).stream()
				.filter((act) -> (act.getName().equals(AppData.ACTIVITY[2]))).forEach((act) -> {
					String date = act.getICTStrDateTime(act.getCreated_at());
					String room = act.getNote().substring(12, 15);
					int star = act.getNote().charAt(21) - 48;
					String feedback = act.getContent();
					fbr.add(new Feedback(date, room, star, feedback));
				});
		return fbr;
	}
}
