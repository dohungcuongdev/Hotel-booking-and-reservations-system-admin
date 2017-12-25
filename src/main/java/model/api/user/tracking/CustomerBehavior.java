/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.user.tracking;

import java.util.List;

import model.mongodb.user.Customer;

/**
 *
 * @author HUNGCUONG
 */
public class CustomerBehavior {

	private Customer cus;
	private List dateVisited;
	private ActionTracking action;

	public Customer getCus() {
		return cus;
	}

	public void setCus(Customer cus) {
		this.cus = cus;
	}

	public List getDateVisited() {
		return dateVisited;
	}

	public void setDateVisited(List dateVisited) {
		this.dateVisited = dateVisited;
	}

	public ActionTracking getAction() {
		return action;
	}

	public void setAction(ActionTracking action) {
		this.action = action;
	}
	
	public CustomerBehavior(Customer cus, List dateVisited, ActionTracking action) {
		this.cus = cus;
		this.dateVisited = dateVisited;
		this.action = action;
	}

	public CustomerBehavior(Customer cus, ActionTracking action) {
		this.cus = cus;
		this.dateVisited = dateVisited;
		this.action = action;
	}

	@Override
	public String toString() {
		return "CustomerBehavior [cus=" + cus + ", dateVisited=" + dateVisited + ", action=" + action + "]";
	}
}
