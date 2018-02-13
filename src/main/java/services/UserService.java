/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import model.api.user.tracking.CustomerBehavior;
import model.api.user.tracking.ExternalIP;
import model.api.user.tracking.FollowUsers;
import model.api.user.tracking.CountryChartData;
import model.api.user.tracking.PageAccessChartData;
import model.mongodb.user.Customer;
import model.mongodb.user.tracking.Activity;
import model.sql.user.Administrator;

/**
 *
 * @author HUNGCUONG
 */
public interface UserService {    
    
    public List<FollowUsers> getListFollowUsers();   
    public List<PageAccessChartData> getPageAccessChartData();    
    public List<PageAccessChartData> getPageAccessChartDataByIP(String ipaddress);
    public List<PageAccessChartData> getPageAccessChartDataByUsername(String username);
    public ExternalIP getExternalIPDetails(String external_ip_address);
    public List<CountryChartData> getCountryChartData();
    public Customer getCustomerByUsername(String username);
    public List<Customer> getAllCustomers();   
    public List<CustomerBehavior> getDataCollection();  
    public CustomerBehavior getOneDataCollection(String username);  
    public Administrator getAdminByUserName(String username);
    public boolean isExists (String username);
    public void updateAdmin(Administrator ad);
    public void updatePassword(String username,  String newpassword);
    public void editProfileImg(String username, String img);
    public List<Activity> getAllActivity();
    public List<Activity> getAllActivityByUserName(String username);
    public List<Activity> getNewListNotification();
    public Activity getActivityBy(String id);
    public Activity seenNotification(String id);
    public Activity replyNotification(String id);
}
