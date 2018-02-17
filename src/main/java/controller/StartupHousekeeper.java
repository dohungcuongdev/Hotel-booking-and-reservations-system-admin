package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import services.HotelItemService;
import statics.constant.AppData;
import statics.helper.StringUtils;

@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {	
	
	@Autowired
	private HotelItemService hotelItemService;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		AppData.listrooms = hotelItemService.getAllRooms();
		AppData.listservices = hotelItemService.getAllHotelServices();

		for (int i = 33; i < 125; i++) {
			StringUtils.numCharSymbol[i - 33] = (char) i;
		}

	}
}