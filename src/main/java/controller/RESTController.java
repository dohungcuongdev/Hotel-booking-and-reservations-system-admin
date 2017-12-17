package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.hotel.HotelRoom;
import model.hotel.HotelService;
import model.user.tracking.ChartData;
import services.HotelItemService;
import services.UserService;

@RestController
@RequestMapping("/api")
public class RESTController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HotelItemService hotelItemService;
	
	@CrossOrigin
	@RequestMapping(value = "/rooms", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<HotelRoom> getListRooms() {
		return hotelItemService.getAllRooms();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/restaurant", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<HotelService> getListServiceInRestaurant() {
		return hotelItemService.getAllHotelServices();
	}	
	
	@CrossOrigin
	@RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public HotelRoom getRoom(@PathVariable(value = "id") String id) {
		return hotelItemService.getRoomByID(id);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rooms/roomname/{name}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public HotelRoom getRoomByName(@PathVariable(value = "name") String name) {
		return hotelItemService.getRoomByName(name);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rooms/page/{page}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<HotelRoom> getRoomByPage(@PathVariable(value = "page") int page) {
		return hotelItemService.getRoomByPage((page-1)*6, 6);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rooms/all/quantity", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public long getNumRoom() {
		return hotelItemService.getNumRooms();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public HotelService getItemInRestaurant(@PathVariable(value = "id") String id) {
		return hotelItemService.getHotelServiceByID(id);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/chart-data", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<ChartData> getChartData() {
		return userService.getListFollowUsersChartData(userService.getListFollowUsers());
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rooms/{name}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public void upDateRoom(@PathVariable(value = "name") String name) {
		HotelRoom room = hotelItemService.getRoomByName("808");
		System.out.println(room);
		room.setId(null);
		room.setDetails("hello from me");
		hotelItemService.updateRoom(room);
	}
	

	
/*	$scope.updateSonarQube = function() {
		// insert or update sonarqube .../api/updateSonarQube
		swal ( "Notify" ,  "Getting api from SonarQube" );
		$http.put("http://localhost:8080/Hotel-booking-and-reservations-system-admin/api/rooms/5a362f6d7ba1a1c0e10aaf23").then(
		function (response) {
			swal ( "Congrats" ,  "Update successfully!" ,  "success" );
		}, 
		function (error) {
			console.log(error);
			swal ( "Oops" ,  "Update error! " + error.statusText ,  "error" );
		});	
	};*/
}
