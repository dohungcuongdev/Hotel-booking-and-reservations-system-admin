/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.daoimpls;

import static statics.helper.MathCalculator.round;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.gson.reflect.TypeToken;

import daos.ActivityDAO;
import daos.CustomerDAO;
import daos.TrackingDAO;
import model.api.user.tracking.ActionTracking;
import model.api.user.tracking.CustomerBehavior;
import model.api.user.tracking.DataCollection;
import model.api.user.tracking.Feedback;
import model.mongodb.user.Customer;
import model.mongodb.user.tracking.Activity;
import statics.constant.APIData;
import statics.constant.AppData;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class CustomerDAOImpl extends APIDAO implements CustomerDAO {

	@Autowired
	private ActivityDAO activityDAO;

	@Autowired
	private TrackingDAO userDAO;

	@Override
	public Customer getCustomerByUsername(String username) {
		return getJsonData(getStringAPI(APIData.USER_USERNAME_API + username), Customer.class);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return getJsonData(getStringAPI(APIData.USER_API), new TypeToken<List<Customer>>(){}.getType());
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

	private List<String> getDateVisit(String username) {
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
}
