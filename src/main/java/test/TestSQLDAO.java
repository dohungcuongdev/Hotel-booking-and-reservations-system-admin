package test;

import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import daos.CustomerDAO;
import daos.RoomDAO;
import services.ApplicationService;
import statics.constant.AppData;

@Repository
public class TestSQLDAO {
	
	@Autowired
	ApplicationService appService;
	
	@Autowired
	RoomDAO roomDAO;
	
	@Autowired
	CustomerDAO customerDAO;
	
	public Object runTestAndGetData() {
		
		Object obj;
		
		try {
			obj = dataDisplayOnWeb();
		} catch (Exception e) {
			obj = e.getMessage();
		}
		
		
		System.out.println("\n\n--------------------- Test SQL DAO go here ----------------------\n");
		
		
		// log here
		System.out.println(obj);
		
		
		
		System.out.println("\n------------------------- End Test SQL DAO ----------------------\n\n");
		
		return obj;
	}
	
	public Object dataDisplayOnWeb() {
		
		// return data to show on web here
		return roomDAO.getRoomByID(1);
	}
	
	public static void main (String args[]) {
		try {
			Desktop desktop = Desktop.getDesktop();
			URI uri = new URI(String.format(AppData.SPRING_APP_URL + "testSQLDAO", URLEncoder.encode("test", "UTF8")));
			desktop.browse(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
