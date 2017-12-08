/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import model.user.tracking.ActionTracking;
import model.user.tracking.Activity;
import model.user.tracking.ChartData;
import model.user.tracking.CustomerBehavior;
import model.user.tracking.DataCollection;
import model.user.tracking.ExternalIP;
import model.user.tracking.Feedback;
import model.user.Administrator;
import model.user.Customer;
import model.user.tracking.FollowUsers;
import services.UserService;
import daos.AdminDAO;
import daos.CustomerDAO;
import daos.UserDAO;
import daos.ActivityDAO;

/**
 *
 * @author HUNGCUONG
 */

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
    private UserDAO userDAO;
	
	@Autowired
    private AdminDAO adminDAO;
	
	@Autowired
    private CustomerDAO customerDAO;
	
	@Autowired
    private ActivityDAO activityDAO;

    @Override
    public List<FollowUsers> getListFollowUsers() {
        return userDAO.getListFollowUsers();
    }

    @Override
    public Map getFollowUsersMap(List<FollowUsers> list) {
        return userDAO.getFollowUsersMap(list);
    }

    @Override
    public Map getFollowUsersMapByIP(List<FollowUsers> list) {
        return userDAO.getFollowUsersMapByIP(list);
    }

    @Override
    public Map getFollowUsersMapByOneIP(List<FollowUsers> list, String ip) {
        return userDAO.getFollowUsersMapByOneIP(list, ip);
    }

    @Override
    public Map getMapFollowUsersCountry(List<FollowUsers> list) {
        return userDAO.getMapFollowUsersCountry(list);
    }

    @Override
    public String getFollowUsersCountry(List<FollowUsers> list) {
        return userDAO.getFollowUsersCountry(list);
    }
    
    @Override
    public ExternalIP getExternalIPDetails(String external_ip_address) {
    	return userDAO.getExternalIPDetails(external_ip_address);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerDAO.getCustomerByUsername(username);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public boolean checkexsitCustomer(String username) {
        return customerDAO.checkexsitCustomer(username);
    }

    @Override
    public List<String> getDateVisit(String username) {
        return customerDAO.getDateVisit(username);
    }

    @Override
    public Administrator getAdminByUserName(String username) {
        return adminDAO.getAdminByUserName(username);
    }

    @Override
    public void updateAdmin(Administrator ad) {
        adminDAO.updateAdmin(ad);
    }

    @Override
    public void updatePassword(String currentpassword, String correctpassword, String newpassword, String confirm) {
        adminDAO.updatePassword(currentpassword, correctpassword, newpassword, confirm);
    }

    @Override
    public void editProfileImg(String username, String img) {
        adminDAO.editProfileImg(username, img);
    }

    @Override
    public List<Activity> getAllActivity() {
        return activityDAO.getAllActivity();
    }

    @Override
    public List<Activity> getAllActivityByUserName(String username) {
        return activityDAO.getAllActivityByUserName(username);
    }

    @Override
    public List<Activity> getNewListNotification() {
        return activityDAO.getNewListNotification();
    }

    @Override
    public Activity getActivityBy(String id) {
        return activityDAO.getActivityBy(id);
    }

    @Override
    public void seenNotification(String id) {
        activityDAO.seenNotification(id);
    }

    @Override
    public List<DataCollection> getListRoomBooked(String username) {
        return customerDAO.getListRoomBooked(username);
    }

    @Override
    public List<DataCollection> getListRoomCanceled(String username) {
        return customerDAO.getListRoomCanceled(username);
    }

    @Override
    public double getAvgStarRoomFeedback(String username) {
        return customerDAO.getAvgStarRoomFeedback(username);
    }

    @Override
    public List<CustomerBehavior> getDataCollection() {
        return customerDAO.getDataCollection();
    }

    @Override
    public double getAvgStarFeedback(String username) {
        return customerDAO.getAvgStarFeedback(username);
    }

    @Override
    public CustomerBehavior getOneDataCollection(String username) {
        return customerDAO.getOneDataCollection(username);
    }

    @Override
    public List<Feedback> getListFeedbackRoom(String username) {
        return customerDAO.getListFeedbackRoom(username);
    }

    @Override
    public List<ChartData> getListFollowUsersChartData(List<FollowUsers> list) {
        return userDAO.getListFollowUsersChartData(list);
    }

	@Override
	public ActionTracking getActionTrackingByUsername(String username) {
		return customerDAO.getActionTrackingByUsername(username);
	}

	@Override
	public Map getPageAccessChartData(List<FollowUsers> list) {
		return userDAO.getPageAccessChartData(list);
	}

	@Override
	public String getJSONPageAccess(Map m) {
		return userDAO.getJSONPageAccess(m);
	}

	@Override
	public Map getPageAccessChartDataByIP(String ipaddress, List<FollowUsers> list) {
		return userDAO.getPageAccessChartDataByIP(ipaddress, list);
	}

	@Override
	public Map getMapByExternalIP(List<FollowUsers> list) {
		return userDAO.getMapByExternalIP(list);
	}
}
