/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.api.user.tracking.CustomerBehavior;
import model.api.user.tracking.ExternalIP;
import model.api.user.tracking.FollowUsers;
import model.api.user.tracking.PageAccessData;
import model.mongodb.user.Customer;
import model.mongodb.user.tracking.Activity;
import model.mysql.user.Administrator;
import services.UserService;
import daos.AdminDAO;
import daos.CustomerDAO;
import daos.TrackingDAO;
import daos.ActivityDAO;

/**
 *
 * @author HUNGCUONG
 */

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
    private TrackingDAO userDAO;
	
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
    public Administrator getAdminByUserName(String username) {
        return adminDAO.getAdminByUserName(username);
    }

    @Override
    public void updateAdmin(Administrator ad) {
        adminDAO.updateAdmin(ad);
    }

    @Override
    public void updatePassword(String username,  String newpassword) {
        adminDAO.updatePassword(username, newpassword);
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
    public Activity seenNotification(String id) {
        return activityDAO.seenNotification(id);
    }

    @Override
    public List<CustomerBehavior> getDataCollection() {
        return customerDAO.getDataCollection();
    }

    @Override
    public CustomerBehavior getOneDataCollection(String username) {
        return customerDAO.getOneDataCollection(username);
    }

	@Override
	public List<PageAccessData> getPageAccessChartData() {
		return userDAO.getPageAccessChartData();
	}

	@Override
	public List<PageAccessData> getPageAccessChartDataByIP(String ipaddress) {
		return userDAO.getPageAccessChartDataByIP(ipaddress);
	}

	@Override
	public Activity replyNotification(String id) {
		return activityDAO.replyNotification(id);
	}

	@Override
	public List<PageAccessData> getPageAccessChartDataByUsername(String username) {
		return userDAO.getPageAccessChartDataByUsername(username);
	}
}
