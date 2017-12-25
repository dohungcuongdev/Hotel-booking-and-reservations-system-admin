package test;

import daos.impl.APIDAOImpl;

public class APIRoom {
	
	APIDAOImpl a = new APIDAOImpl();
	
	public String getApi() {
		return a.getStringAPI("http://localhost:8080/Hotel-booking-and-reservations-system-admin/api/rooms");
	}
	
	public String getApi2() {
		return a.getStringAPI("http://localhost:8080/Hotel-booking-and-reservations-system-admin/api/restaurant");
	}

}
