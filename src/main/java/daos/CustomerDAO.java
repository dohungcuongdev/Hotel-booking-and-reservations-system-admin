/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;

import model.api.user.tracking.ActionTracking;
import model.api.user.tracking.CustomerBehavior;
import model.mongodb.user.Customer;

/**
 *
 * @author Do Hung Cuong
 */
public interface CustomerDAO {

    public Customer getCustomerByUsername(String username);
    public List<Customer> getAllCustomers();
    public ActionTracking getActionTrackingByUsername(String username);  
    public List<CustomerBehavior> getDataCollection();
    public CustomerBehavior getOneDataCollection(String username);
}
