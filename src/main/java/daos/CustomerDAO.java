/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;

import model.user.tracking.ActionTracking;
import model.user.tracking.CustomerBehavior;
import model.user.tracking.DataCollection;
import model.user.tracking.Feedback;
import model.user.Customer;

/**
 *
 * @author Do Hung Cuong
 */
public interface CustomerDAO {

    public Customer getCustomerByUsername(String username);
    public List<Customer> getAllCustomers();
    public boolean checkexsitCustomer(String username);
    public List<String> getDateVisit(String username);    
    public ActionTracking getActionTrackingByUsername(String username);    
    public List<DataCollection> getListRoomBooked(String username);    
    public List<DataCollection> getListRoomCanceled(String username);    
    public double getAvgStarRoomFeedback(String username);    
    public double getAvgStarFeedback(String username);    
    public List<Feedback> getListFeedbackRoom(String username);   
    public List<CustomerBehavior> getDataCollection();   
    public CustomerBehavior getOneDataCollection(String username);
}
