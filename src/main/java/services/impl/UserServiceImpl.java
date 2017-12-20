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
import model.user.tracking.Activity;
import model.user.tracking.CustomerBehavior;
import model.user.tracking.ExternalIP;
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
    public Map getFollowUsersMapByOneIP(List<FollowUsers> list, String ip) {
        return userDAO.getFollowUsersMapByOneIP(list, ip);
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
    public void updatePassword(String username, String currentpassword, String correctpassword, String newpassword, String confirm) {
        adminDAO.updatePassword(username, currentpassword, correctpassword, newpassword, confirm);
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
    public List<CustomerBehavior> getDataCollection() {
        return customerDAO.getDataCollection();
    }

    @Override
    public CustomerBehavior getOneDataCollection(String username) {
        return customerDAO.getOneDataCollection(username);
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
	public void replyNotification(String id) {
		activityDAO.replyNotification(id);
	}
}
