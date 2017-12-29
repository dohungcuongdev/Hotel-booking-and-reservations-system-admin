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
import model.api.user.tracking.GeoSameCountry;
import model.api.user.tracking.PageAccessData;
import model.mongodb.user.Customer;
import model.mongodb.user.tracking.Activity;
import model.mysql.user.Administrator;

/**
 *
 * @author HUNGCUONG
 */
public interface UserService {    
    
    public List<FollowUsers> getListFollowUsers();   
    public List<PageAccessData> getPageAccessChartData();    
    public List<PageAccessData> getPageAccessChartDataByIP(String ipaddress);
    public List<PageAccessData> getPageAccessChartDataByUsername(String username);
    public ExternalIP getExternalIPDetails(String external_ip_address);
    public List<GeoSameCountry> getGeoSameCountry();
    public Customer getCustomerByUsername(String username);
    public List<Customer> getAllCustomers();   
    public List<CustomerBehavior> getDataCollection();  
    public CustomerBehavior getOneDataCollection(String username);  
    public Administrator getAdminByUserName(String username);
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
