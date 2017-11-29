/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import static statics.provider.DateTimeCalculator.getDateTime;
import static statics.provider.MathCalculator.round;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import daos.ActivityDAO;
import daos.CustomerDAO;
import daos.UserDAO;
import database.MongoDBConnector;
import model.user.Customer;
import model.user.tracking.ActionTracking;
import model.user.tracking.Activity;
import model.user.tracking.CustomerBehavior;
import model.user.tracking.DataCollection;
import model.user.tracking.FeedbackRoom;
import statics.AppData;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private final Gson gson = new Gson();
	
	@Autowired
	private ActivityDAO activityDAO;
	
	@Autowired
	private UserDAO userDAO ;
	
	private DBCollection collection;

	public CustomerDAOImpl() {
		try {
			collection = MongoDBConnector.createConnection("customers");
		} catch (UnknownHostException ex) {
			Logger.getLogger(RoomDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", username);
		DBCursor cursor = collection.find(whereQuery);
		Customer cus = new Customer();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			cus = gson.fromJson(obj + "", Customer.class);
			cus.setRegistered_date(getDateTime(obj.get("created_at") + ""));
		}
		return cus;
	}

	@Override
	public List<Customer> getAllCustomers() {
		ArrayList<Customer> customers = new ArrayList<>();
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Customer cus = gson.fromJson(obj + "", Customer.class);
			cus.setRegistered_date(getDateTime(obj.get("created_at") + ""));
			cus.setDateVisit(getDateVisit(cus.getUsername()));
			customers.add(cus);
		}
		return customers;
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
				.map((fu) -> fu.getDate_access().toString().substring(0, 10)
						+ fu.getDate_access().toString().substring(19, 28))
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
		List<FeedbackRoom> feedbackroom = new ArrayList<>();
		int starFBR = 0, countFBR = 0;
		int starFB = 0, countFB = 0;
		List<Activity> activities = activityDAO.getAllActivityByUserName(username);
		for (Activity act : activities) {
			if (act.getName().equals(AppData.ACTIVITY[0])) {
				roombooked.add(new DataCollection(act.getTime() + "", act.getDetails().substring(12)));
			}
			if (act.getName().equals(AppData.ACTIVITY[1])) {
				roomcanceled.add(new DataCollection(act.getTime() + "", act.getDetails().substring(20)));
			}
			if (act.getName().equals(AppData.ACTIVITY[2])) {
				String date = act.getTime() + "";
				String room = act.getNote().substring(12, 15);
				int star = act.getNote().charAt(21) - 48;
				String feedback = act.getContent();
				feedbackroom.add(new FeedbackRoom(date, room, star, feedback));

				starFBR += act.getNote().charAt(21) - 48;
				++countFBR;
			}
			if (act.getName().equals(AppData.ACTIVITY[3])) {
				starFB += act.getNote().charAt(12) - 48;
				++countFB;
			}

		}
		double avgfeedbackRoom = round(starFBR * 1.0 / countFBR, 2);
		double avgFeedbackSV = round(starFB * 1.0 / countFB, 2);
		return new ActionTracking(roombooked, roomcanceled, feedbackroom, avgfeedbackRoom, avgFeedbackSV);

	}

	@Override
	public List<DataCollection> getListRoomBooked(String username) {
		List<DataCollection> roombooked = new ArrayList<>();
		activityDAO.getAllActivityByUserName(username).stream().filter((act) -> (act.getName().equals(AppData.ACTIVITY[0])))
				.forEach((act) -> {
					roombooked.add(new DataCollection(act.getTime() + "", act.getDetails().substring(12)));
				});
		return roombooked;
	}

	@Override
	public List<DataCollection> getListRoomCanceled(String username) {
		List<DataCollection> roomcanceled = new ArrayList<>();
		activityDAO.getAllActivityByUserName(username).stream().filter((act) -> (act.getName().equals(AppData.ACTIVITY[1])))
				.forEach((act) -> {
					roomcanceled.add(new DataCollection(act.getTime() + "", act.getDetails().substring(20)));
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
		if(count == 0) return 0;
		return round(star * 1.0 / count, 2);
	}

	public int getTotalStarRoomFeedback(String username) {
		int star = 0;
		star = activityDAO.getAllActivityByUserName(username).stream()
				.filter((act) -> (act.getName().equals(AppData.ACTIVITY[2]))).map((act) -> act.getNote().charAt(21) - 48)
				.reduce(star, Integer::sum);
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
		if(count == 0) return 0;
		return round(star * 1.0 / count, 2);
	}

	public double getTotalStarFeedback(String username) {
		int star = 0;
		star = activityDAO.getAllActivityByUserName(username).stream()
				.filter((act) -> (act.getName().equals(AppData.ACTIVITY[3]))).map((act) -> act.getNote().charAt(12) - 48)
				.reduce(star, Integer::sum);
		return star;
	}

	@Override
	public List<CustomerBehavior> getDataCollection() {
		List<CustomerBehavior> cdc = new ArrayList<>();
		List<Customer> customers = getAllCustomers();
		customers.stream().forEach((cus) -> {
			String un = cus.getUsername();
			cdc.add(new CustomerBehavior(cus, getDateVisit(un), getActionTrackingByUsername(un)));
		});
		return cdc;
	}

	@Override
	public CustomerBehavior getOneDataCollection(String username) {
		return new CustomerBehavior(getCustomerByUsername(username), getDateVisit(username), getActionTrackingByUsername(username));
	}

	@Override
	public List<FeedbackRoom> getListFeedbackRoom(String username) {
		List<FeedbackRoom> fbr = new ArrayList<>();
		activityDAO.getAllActivityByUserName(username).stream().filter((act) -> (act.getName().equals(AppData.ACTIVITY[2])))
				.forEach((act) -> {
					String date = act.getTime() + "";
					String room = act.getNote().substring(12, 15);
					int star = act.getNote().charAt(21) - 48;
					String feedback = act.getContent();
					fbr.add(new FeedbackRoom(date, room, star, feedback));
				});
		return fbr;
	}
}
