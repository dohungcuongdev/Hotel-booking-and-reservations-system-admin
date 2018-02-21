package test;

import sql.daoimpls.APIDAO;

public class TestAPI {
	
	static APIDAO a = new APIDAO();
	
	public static String getApi() {
		return a.getStringAPI("http://localhost:8080/Hotel-booking-and-reservations-system-admin/api/rooms");
	}
	
	public static String getApi2() {
		return a.getStringAPI("http://localhost:8080/Hotel-booking-and-reservations-system-admin/api/restaurant");
	}
	
	public static void main(String args[]) {
		System.out.println(getApi());
	}

}
