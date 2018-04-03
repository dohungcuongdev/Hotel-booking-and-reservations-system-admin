package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import services.HotelItemService;
import services.UserService;
import statics.constant.AppData;

@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {	
	
	@Autowired
	private HotelItemService hotelItemService;
	
	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		
		//testing data
/*		AppData.listrooms = hotelItemService.getAllRooms();
		AppData.listservices = hotelItemService.getAllHotelServices();
		AppData.listcustomers = userService.getAllCustomers();*/
	}
}