/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.user.Administrator;
import statics.AppData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.user.tracking.Activity;
import model.LoginBean;
import model.hotel.HotelRoom;
import model.hotel.HotelService;
import model.user.Customer;
import model.user.tracking.FollowUsers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import services.HotelItemService;
import services.UserService;
import services.ApplicationService;

/**
 *
 * @author Do Hung Cuong
 */

@Controller
@RequestMapping(value = "/")
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private HotelItemService hotelItemService;

	@Autowired
	private ApplicationService appService;

	// index
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		initialize(model);
		return "index";
	}

	// login
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	// checklogin
	@RequestMapping(value = "check-login", method = RequestMethod.POST)
	public String checklogin(@ModelAttribute(value = "loginbean") LoginBean loginbean, ModelMap model) throws IOException {
		if (loginbean.getUserName().equals("cuongvip1295@yahoo.com.vn") && loginbean.getPassword().equals("12101995")) {
			return index(model);
		}
		return "login";
	}
	
	@RequestMapping(value = "search-result/{keyword}", method = RequestMethod.GET)
	public String searchResult(@PathVariable(value = "keyword") String keyword, ModelMap model) {
		if(keyword!=null && !keyword.equals("")) {
			initialize(model);
			model.put("keyword", keyword);
			model.put("cusDataCollection", userService.getDataCollection());
			initializeFollowUser(model);
		}
		return "search-result";
	}

	// profile
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String profile(ModelMap model) {
		initialize(model);
		model.addAttribute("adminEdit", new Administrator());
		return "profile";
	}

	@RequestMapping(value = "profile-edited", method = RequestMethod.POST)
	public String editProfile(@ModelAttribute(value = "adminEdit") Administrator ad, ModelMap model) {
		if (ad.isEnoughInfor()) {
			userService.updateAdmin(ad);
			AppData.setNewAdmin(ad);;
			model.put(AppData.REUSE_STRING[5], AppData.EDITSUCCESS);
		} else {
			model.put(AppData.REUSE_STRING[5], AppData.INFOR_NOT_ENOUGH);
		}
		initialize(model);
		return "profile";
	}

	@RequestMapping(value = "profile-img-edited", method = RequestMethod.POST)
	public String profileImgEdited(@RequestParam(value = "img") CommonsMultipartFile img, HttpServletRequest request,
			ModelMap model) {
		userService.editProfileImg(AppData.admin.getUsername(), appService.uploadfile(img, request, model, "users"));
		AppData.refreshAdmin();
		return profile(model);
	}

	// rooms
	@RequestMapping(value = "manage-rooms", method = RequestMethod.GET)
	public String manageRooms(ModelMap model) {
		initialize(model);
		return "manage-rooms";
	}

	@RequestMapping(value = "room", method = RequestMethod.GET)
	public String singleRoom(ModelMap model) {
		return manageRooms(model);
	}

	@RequestMapping(value = "room/{roomid}", method = RequestMethod.GET)
	public String singleRoom(@PathVariable(value = "roomid") String roomid, ModelMap model) {
		return initializeSingleRoom(model, roomid, AppData.REUSE_STRING[1]);
	}

	@RequestMapping(value = "edit-room/{roomid}", method = RequestMethod.GET)
	public String editRoom(@PathVariable(value = "roomid") String roomid, ModelMap model) {
		model.addAttribute("roomEdit", new HotelRoom());
		return initializeSingleRoom(model, roomid, AppData.REUSE_STRING[3]);
	}

	@RequestMapping(value = "room-edited", method = RequestMethod.POST)
	public String roomEdited(@ModelAttribute(value = "roomEdit") HotelRoom roomEdit, ModelMap model) {
		roomEdit.initializeSomeInfor();
		initialize(model);
		String strEdit = roomEdit.getAbleToEdit();
		model.put(AppData.REUSE_STRING[5], strEdit);
		if (strEdit.equals(AppData.ABLE_TO_EDIT)) {
			hotelItemService.updateRoom(roomEdit);
			model.put(AppData.REUSE_STRING[1], roomEdit);
			model.put("relatedRoom", hotelItemService.getRelatedHotelRooms(roomEdit.getType()));
		} else {
			return initializeSingleRoom(model, roomEdit.getId(), AppData.REUSE_STRING[3]);
		}
		return AppData.REUSE_STRING[3];
	}

	@RequestMapping(value = "remove-room/{roomid}", method = RequestMethod.GET)
	public String removeRoom(@PathVariable(value = "roomid") String roomid, ModelMap model) {
		hotelItemService.deleteRoom(roomid);
		model.put("deleteResult", AppData.ABLE_TO_EDIT);
		return manageRooms(model);
	}

	@RequestMapping(value = "room-img-edited/{roomid}", method = RequestMethod.POST)
	public String roomImgEdited(@RequestParam(value = "img1") CommonsMultipartFile img1,
			@RequestParam(value = "img2") CommonsMultipartFile img2, HttpServletRequest request,
			@PathVariable(value = "roomid") String roomid, ModelMap model) {
		model.addAttribute("roomEdit", new HotelRoom());
		hotelItemService.editImageRoom(roomid, appService.uploadfile(img1, request, model, "rooms"),
				appService.uploadfile(img2, request, model, "rooms"));
		return initializeSingleRoom(model, roomid, AppData.REUSE_STRING[3]);
	}

	// restaurant
	@RequestMapping(value = "manage-restaurant", method = RequestMethod.GET)
	public String manageRestaurant(ModelMap model) {
		initialize(model);
		return "manage-restaurant";
	}

	@RequestMapping(value = "service", method = RequestMethod.GET)
	public String singleService(ModelMap model) {
		return manageRestaurant(model);
	}

	@RequestMapping(value = "service/{serviceid}", method = RequestMethod.GET)
	public String singleService(@PathVariable(value = "serviceid") String serviceid, ModelMap model) {
		return initializeSingleService(model, serviceid, AppData.REUSE_STRING[2]);
	}

	@RequestMapping(value = "edit-service/{serviceid}", method = RequestMethod.GET)
	public String editService(@PathVariable(value = "serviceid") String serviceid, ModelMap model) {
		model.addAttribute("serviceEdit", new HotelService());
		return initializeSingleService(model, serviceid, AppData.REUSE_STRING[4]);
	}

	@RequestMapping(value = "service-edited", method = RequestMethod.POST)
	public String serviceEdited(@ModelAttribute(value = "serviceEdit") HotelService serviceEdit, ModelMap model) {
		serviceEdit.initializeSomeInfor();
		initialize(model);
		String strEdit = serviceEdit.getAbleToEdit();
		model.put(AppData.REUSE_STRING[5], strEdit);
		if (strEdit.equals(AppData.ABLE_TO_EDIT)) {
			hotelItemService.updateService(serviceEdit);
			model.put(AppData.REUSE_STRING[2], serviceEdit);
			model.put("relatedServices", hotelItemService.getRelatedHotelServices(serviceEdit.getType()));
		} else {
			return initializeSingleService(model, serviceEdit.getId(), AppData.REUSE_STRING[4]);
		}
		return AppData.REUSE_STRING[4];
	}

	@RequestMapping(value = "remove-service/{serviceid}", method = RequestMethod.GET)
	public String removeService(@PathVariable(value = "serviceid") String serviceid, ModelMap model) {
		hotelItemService.deleteService(serviceid);
		model.put("deleteResult", AppData.ABLE_TO_EDIT);
		return manageRestaurant(model);
	}

	@RequestMapping(value = "service-img-edited/{serviceid}", method = RequestMethod.POST)
	public String serviceImgEdited(@RequestParam(value = "img1") CommonsMultipartFile img1,
			@RequestParam(value = "img2") CommonsMultipartFile img2, HttpServletRequest request,
			@PathVariable(value = "serviceid") String serviceid, ModelMap model) {
		model.addAttribute("serviceEdit", new HotelService());
		hotelItemService.editImageService(serviceid, appService.uploadfile(img1, request, model, "restaurant"),
				appService.uploadfile(img2, request, model, "restaurant"));
		return initializeSingleService(model, serviceid, AppData.REUSE_STRING[4]);
	}

	// users
	@RequestMapping(value = "manage-users", method = RequestMethod.GET)
	public String manageUsers(ModelMap model) {
		initialize(model);
		model.put("cusDataCollection", userService.getDataCollection());
		return "manage-users";
	}

	@RequestMapping(value = "follow-users", method = RequestMethod.GET)
	public String followUsers(ModelMap model) {
		initialize(model);
		initializeFollowUser(model);
		return "follow-users";
	}

	@RequestMapping(value = "view-statistics", method = RequestMethod.GET)
	public String viewStatistics(ModelMap model) {
		initialize(model);
		return "view-statistics";
	}

	@RequestMapping(value = "country-chart", method = RequestMethod.GET)
	public String followUserChart(ModelMap model) {
		initialize(model);
		return "country-chart";
	}

	@RequestMapping(value = "page-access-chart", method = RequestMethod.GET)
	public String pageAccessChart(ModelMap model) {
		initialize(model);
		Map m = userService.getPageAccessChartData(userService.getListFollowUsers());
		model.put("ipaddress", "All IP address");
		model.put("mapPageAccess", m);
		model.put("jsonchart", userService.getJSONPageAccess(m));
		return "page-access-chart";
	}
	
	@RequestMapping(value = "page-access-chart/{ipaddress}", method = RequestMethod.GET)
	public String pageAccessIPChart(@PathVariable(value = "ipaddress") String ipaddress, ModelMap model) {
		initialize(model);
		Map m = userService.getPageAccessChartDataByIP(ipaddress, userService.getListFollowUsers());
		model.put("ipaddress", ipaddress);
		model.put("mapPageAccess", m);
		model.put("jsonchart", userService.getJSONPageAccess(m));
		return "page-access-chart";
	}

	@RequestMapping(value = "follow-user-ip/{ip}", method = RequestMethod.GET)
	public String followUsersIP(@PathVariable(value = "ip") String ip, ModelMap model) {
		initialize(model);
		List<FollowUsers> list = userService.getListFollowUsers();
		model.put("listFollowUsers", list);
		model.put("mapFollowUserIP", userService.getFollowUsersMapByOneIP(list, ip));
		return "follow-user-ip";
	}
	
	@RequestMapping(value = "ip-details/{externalip}", method = RequestMethod.GET)
	public String ipDetails(@PathVariable(value = "externalip") String externalip, ModelMap model) {
		initialize(model);
		model.put("ipDetails", userService.getExternalIPDetails(externalip));
		return "ip-details";
	}

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String singleUser(ModelMap model) {
		return manageUsers(model);
	}

	@RequestMapping(value = "customer", method = RequestMethod.GET)
	public String singleCustomer(ModelMap model) {
		return manageUsers(model);
	}

	@RequestMapping(value = "user/{username}", method = RequestMethod.GET)
	public String singleUser(@PathVariable(value = "username") String username, ModelMap model) {
		initialize(model);
		Customer cus = userService.getCustomerByUsername(username);
		cus.setActivity(userService.getAllActivityByUserName(username));
		model.put("customer", cus);
		return "user";
	}

	@RequestMapping(value = "customer/{username}", method = RequestMethod.GET)
	public String singleCustomer(@PathVariable(value = "username") String username, ModelMap model) {
		initialize(model);
		model.put("cusDataCollection", userService.getOneDataCollection(username));
		return "customer";
	}

	// message
	@RequestMapping(value = "message", method = RequestMethod.GET)
	public String message(ModelMap model) {
		initialize(model);
		return "message";
	}

	@RequestMapping(value = "notification/{id}", method = RequestMethod.GET)
	public String notification(@PathVariable(value = "id") String id, ModelMap model) {
		userService.seenNotification(id);
		model.put(AppData.REUSE_STRING[0], userService.getActivityBy(id));
		model.put("emailTemplates", AppData.EMAIL_TEMPLATE_1);
		initialize(model);
		return "notification";
	}

	@RequestMapping(value = "reply Book Room/{id}", method = RequestMethod.GET)
	public String replyBooking(@PathVariable(value = "id") String id, ModelMap model) {
		userService.seenNotification(id);
		model.put(AppData.REUSE_STRING[0], userService.getActivityBy(id));
		model.put("emailTemplates", AppData.EMAIL_TEMPLATE_1);
		initialize(model);
		model.put("emailsent", "");
		return AppData.REUSE_STRING[6];
	}

	@RequestMapping(value = "reply Cancel Room/{id}", method = RequestMethod.GET)
	public String replyCancel(@PathVariable(value = "id") String id, ModelMap model) {
		userService.seenNotification(id);
		model.put(AppData.REUSE_STRING[0], userService.getActivityBy(id));
		initialize(model);
		return AppData.REUSE_STRING[7];
	}

	@RequestMapping(value = "send-mail", method = RequestMethod.POST)
	public String sendMail(@RequestParam("activity-id") String id, @RequestParam("message") String message,
			@RequestParam("user-email") String useremail, @RequestParam("subject") String subject, ModelMap model) {
		model.put("emailsent", appService.sendEmail(appService.removeAccent(message), useremail, subject));
		model.put("emailTemplates", AppData.EMAIL_TEMPLATE_1);
		return replyEmail("reply " + subject, id, model);
	}

	// fqa
	@RequestMapping(value = "fqa", method = RequestMethod.GET)
	public String fqa(ModelMap model) {
		initialize(model);
		return "fqa";
	}

	@RequestMapping(value = "downloadCSV")
	public void downloadCSV(HttpServletResponse response) {
		response.setContentType("text/csv");
		// creates mock data
		String headerKey = AppData.HEADERKEY;
		String headerValue = String.format(AppData.CSV_FORMAT, AppData.CSV_FILENAME);
		response.setHeader(headerKey, headerValue);
		try ( // uses the Super CSV API to generate CSV data from the model data
				ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE)) {
			String[] header = AppData.HEADERCSV;
			csvWriter.writeHeader(header);
			for (FollowUsers r : userService.getListFollowUsers()) {
				csvWriter.write(r, header);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// initialize function
	private void initialize(ModelMap model) {
		List<Activity> listactivily = userService.getAllActivity();
		List<HotelRoom> listrooms = hotelItemService.getAllRooms();
		List<HotelService> listservices = hotelItemService.getAllHotelServices();
		List<Customer> listusers = userService.getAllCustomers();
		model.put("ad", AppData.admin);
		model.put("listusers", listusers);
		model.put("newNotifications", userService.getNewListNotification());
		model.put("listactivily", listactivily);
		model.put("listrooms", listrooms);
		model.put("listservices", listservices);
		model.put("totalUsers", listusers.size() * 100);
		model.put("totalMessage", listactivily.size() * 100);
		model.put("totalRooms", listrooms.size() * 100);
		model.put("totalServices", listservices.size() * 100);
	}
	
	private void initializeFollowUser(ModelMap model) {
		List<FollowUsers> list = userService.getListFollowUsers();
		model.put("mapFollowUsers", userService.getFollowUsersMap(list));
		model.put("mapFollowUsersIP", userService.getFollowUsersMapByIP(list));
		model.put("mapsExternalIP", userService.getMapByExternalIP(list));
	}

	private String initializeSingleRoom(ModelMap model, String roomid, String redirect) {
		initialize(model);
		HotelRoom room = hotelItemService.getRoomByID(roomid);
		model.put(AppData.REUSE_STRING[1], room);
		model.put("relatedRoom", hotelItemService.getRelatedHotelRooms(room.getType()));
		return redirect;
	}

	private String initializeSingleService(ModelMap model, String serviceid, String redirect) {
		initialize(model);
		HotelService service = hotelItemService.getHotelServiceByID(serviceid);
		model.put(AppData.REUSE_STRING[2], service);
		model.put("relatedServices", hotelItemService.getRelatedHotelServices(service.getType()));
		return redirect;
	}

	private String replyEmail(String redirect, String id, ModelMap model) {
		model.put(AppData.REUSE_STRING[0], userService.getActivityBy(id));
		initialize(model);
		return (redirect.equals(AppData.REUSE_STRING[6]) || redirect.equals(AppData.REUSE_STRING[7])) ? redirect : "notification";
	}

}