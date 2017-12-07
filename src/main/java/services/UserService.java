/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import java.util.Map;
import model.user.tracking.ActionTracking;
import model.user.tracking.Activity;
import model.user.tracking.ChartData;
import model.user.tracking.CustomerBehavior;
import model.user.tracking.DataCollection;
import model.user.tracking.ExternalIP;
import model.user.tracking.FeedbackRoom;
import model.user.Administrator;
import model.user.Customer;
import model.user.tracking.FollowUsers;

/**
 *
 * @author HUNGCUONG
 */
public interface UserService {    
    
    public List<FollowUsers> getListFollowUsers();   
    public Map getFollowUsersMap(List<FollowUsers> list);   
    public Map getFollowUsersMapByIP(List<FollowUsers> list); 
    public Map getMapByExternalIP(List<FollowUsers> list); 
    public Map getFollowUsersMapByOneIP(List<FollowUsers> list, String ip);  
    public Map getMapFollowUsersCountry(List<FollowUsers> list);    
    public Map getPageAccessChartData(List<FollowUsers> list);    
    public Map getPageAccessChartDataByIP(String ipaddress, List<FollowUsers> list);    
    public String getJSONPageAccess(Map m);    
    public String getFollowUsersCountry(List<FollowUsers> list);     
    public List<ChartData> getListFollowUsersChartData(List<FollowUsers> list);    
    public ExternalIP getExternalIPDetails(String external_ip_address);    
    public Customer getCustomerByUsername(String username);
    public List<Customer> getAllCustomers();
    public boolean checkexsitCustomer(String username);     
    public List<String> getDateVisit(String username);    
    public ActionTracking getActionTrackingByUsername(String username);    
    public List<DataCollection> getListRoomBooked(String username);   
    public List<DataCollection> getListRoomCanceled(String username);   
    public double getAvgStarRoomFeedback(String username);   
    public double getAvgStarFeedback(String username);   
    public List<FeedbackRoom> getListFeedbackRoom(String username);   
    public List<CustomerBehavior> getDataCollection();  
    public CustomerBehavior getOneDataCollection(String username);  
    public Administrator getAdminByUserName(String username);
    public void updateAdmin(Administrator ad);
    public void updatePassword(String currentpassword, String correctpassword, String newpassword, String confirm);
    public void editProfileImg(String username, String img);
    public List<Activity> getAllActivity();
    public List<Activity> getAllActivityByUserName(String username);
    public List<Activity> getNewListNotification();
    public Activity getActivityBy(String id);
    public void seenNotification(String id);
}
