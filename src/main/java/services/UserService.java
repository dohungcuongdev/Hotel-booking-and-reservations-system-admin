/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import java.util.Map;
import model.user.tracking.Activity;
import model.user.tracking.CustomerBehavior;
import model.user.tracking.ExternalIP;
import model.user.Administrator;
import model.user.Customer;
import model.user.tracking.FollowUsers;
import model.user.tracking.PageAccessData;

/**
 *
 * @author HUNGCUONG
 */
public interface UserService {    
    
    public List<FollowUsers> getListFollowUsers();   
    public Map getFollowUsersMap(List<FollowUsers> list); 
    public Map getFollowUsersMapByOneIP(List<FollowUsers> list, String ip);    
    public List<PageAccessData> getPageAccessChartData();    
    public Map getPageAccessChartDataByIP(String ipaddress, List<FollowUsers> list);    
    public String getJSONPageAccess(Map m);     
    public ExternalIP getExternalIPDetails(String external_ip_address);    
    public Customer getCustomerByUsername(String username);
    public List<Customer> getAllCustomers();   
    public List<CustomerBehavior> getDataCollection();  
    public CustomerBehavior getOneDataCollection(String username);  
    public Administrator getAdminByUserName(String username);
    public void updateAdmin(Administrator ad);
    public void updatePassword(String username, String currentpassword, String correctpassword, String newpassword, String confirm);
    public void editProfileImg(String username, String img);
    public List<Activity> getAllActivity();
    public List<Activity> getAllActivityByUserName(String username);
    public List<Activity> getNewListNotification();
    public Activity getActivityBy(String id);
    public void seenNotification(String id);
    public void replyNotification(String id);
}
