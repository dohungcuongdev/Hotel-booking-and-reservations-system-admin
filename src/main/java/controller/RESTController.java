package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.api.user.tracking.CountryChartData;
import model.api.user.tracking.PageAccessChartData;
import model.mongodb.user.tracking.Activity;
import model.sql.hotel.HotelRoom;
import model.sql.hotel.HotelService;
import services.HotelItemService;
import services.UserService;

@RestController
@RequestMapping("/api")
public class RESTController {
	
	@Autowired
	private HotelItemService hotelItemService;
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@RequestMapping(value = "/rooms", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<HotelRoom> getListRooms() {
		//return AppData.listrooms;
		List<HotelRoom> rooms = hotelItemService.getAllRooms();
		System.out.println(rooms);
		return rooms;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/restaurant", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<HotelService> getListServiceInRestaurant() {
		//return AppData.listservices;
		return hotelItemService.getAllHotelServices();
	}	
	
	@CrossOrigin
	@RequestMapping(value = "/rooms/id/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public HotelRoom getRoom(@PathVariable(value = "id") int id) {
		return hotelItemService.getRoomByID(id);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rooms/{name}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public HotelRoom getRoomByName(@PathVariable(value = "name") String name) {
		return hotelItemService.getRoomByName(name);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rooms/page/{page}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<HotelRoom> getRoomByPage(@PathVariable(value = "page") int page) {
		return hotelItemService.getRoomByPage(page);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rooms/all/quantity", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public long getNumRoom() {	
		return hotelItemService.getNumRooms();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public HotelService getItemInRestaurant(@PathVariable(value = "id") int id) {
		return hotelItemService.getHotelServiceByID(id);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/book-room/{name}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<HotelRoom> bookRoom(@PathVariable(value = "name") String name, @RequestBody HotelRoom room) {
		hotelItemService.bookRoom(room);
		//AppData.listrooms = hotelItemService.getAllRooms();
		return new ResponseEntity<HotelRoom>(room, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/feedback-room/{name}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<HotelRoom> ratingRoom(@PathVariable(value = "name") String name, @RequestBody HotelRoom room) {
		hotelItemService.feedbackRoom(room);
		//AppData.listrooms = hotelItemService.getAllRooms();
		return new ResponseEntity<HotelRoom>(room, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/page-access-chart", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<PageAccessChartData> getPageAccessChart() {
		return userService.getPageAccessChartData();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/page-access-chart/userIP/{userIP}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<PageAccessChartData> getPageAccessChartByIP(@PathVariable(value = "userIP") String userIP) {
		return userService.getPageAccessChartDataByIP(userIP);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/page-access-chart/username/{username}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<PageAccessChartData> getPageAccessChartByUsername(@PathVariable(value = "username") String username) {
		return userService.getPageAccessChartDataByUsername(username);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/country-chart", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<CountryChartData> getCountryChartData() {
		return userService.getCountryChartData();
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/correct-room/{name}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public HotelRoom correctRoom(@PathVariable(value = "name") String name) {
		List<Activity> listAct = userService.getAllActivity();
		List<Activity> listActFB = listAct.stream().filter((item) -> (item.getName().equals("Feedback Room") && item.getClick().equals(name))).collect(Collectors.toList());
		int numvote = 0;
		int star = 0;
		if(listActFB.size() != 0)
		for(int i = 0; i < listActFB.size(); i++) {
			++numvote;
			star += Integer.parseInt(listActFB.get(i).getNote().substring(21, 22));
		}
		HotelRoom room = hotelItemService.getRoomByName(name);
		room.setStar(star);
		room.setNumvote(numvote);
		hotelItemService.updateRoom(room);
		//AppData.listrooms = hotelItemService.getAllRooms();
		return room;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/correct-all-rooms", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<HotelRoom> correctAllRooms() {
		List<HotelRoom> Newrooms = hotelItemService.getAllRooms();
		List<HotelRoom> rooms = hotelItemService.getAllRooms();
		for(HotelRoom room: rooms) {
			Newrooms.add(correctRoom(room.getName()));
		}
		return Newrooms;
	}
	
	
//	@CrossOrigin
//	@RequestMapping(value = "/convert", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
//	public List<HotelRoom> convertRoomFromMongoToMySQL() {
//		hotelItemService.convertRoomFromMongoToMySQL();
//		return hotelItemService.getAllRooms();
//	}
//	
//	@CrossOrigin
//	@RequestMapping(value = "/convert2", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
//	public List<HotelService> convertRestaurantMongoToMySQL() {
//		hotelItemService.convertRestaurantMongoToMySQL();
//		return hotelItemService.getAllHotelServices();
//	}
}