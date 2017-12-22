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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.user.tracking.Activity;
import model.ChangePasswordBean;
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
public class AppController {

	@Autowired
	private UserService userService;

	@Autowired
	private HotelItemService hotelItemService;

	@Autowired
	private ApplicationService appService;

	// login
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if (isAuthenticated(request))
			return index(request, response, model);
		return "login";
	}

	// checklogin
	@RequestMapping(value = "check-login", method = RequestMethod.POST)
	public String checklogin(@ModelAttribute(value = "loginbean") LoginBean loginbean, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		String username = loginbean.getUserName();
		String password = loginbean.getPassword();
		AppData.admin = userService.getAdminByUserName(username);
		if (AppData.admin != null && username.equals(AppData.admin.getUsername()) && password.equals(AppData.admin.getPassword())) {
			request.getSession().setAttribute("username", username);
			request.getSession().setMaxInactiveInterval(24 * 60 * 60);
			return index(request, response, model);
		}
		model.put("checkLogin", "Invalid username or password!");
		return "login";
	}

	// logout
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute("username", "");
		return "login";
	}

	// index
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return authInitializeRedirect(request, response, model, "index");
	}

	@RequestMapping(value = "search-result/{keyword}", method = RequestMethod.GET)
	public String searchResult(@PathVariable(value = "keyword") String keyword, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		initialize(model);
		model.put("cusDataCollection", userService.getDataCollection());
		return "search-result";
	}

	// profile
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String profile(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		return initializeProfile(model);
	}

	// profile
	@RequestMapping(value = "change-password", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute(value = "changePassBean") ChangePasswordBean changePassBean, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		String correctPassword = AppData.admin.getPassword();
		String newPassword = changePassBean.getNewpassword();
		model.put("pwCheckingResult", changePassBean.getPWCheckingResult(correctPassword));
		if (changePassBean.isMatchPassword(correctPassword)) {
			userService.updatePassword(AppData.admin.getUsername(), correctPassword, changePassBean.getCurrentpassword(), newPassword, changePassBean.getConfirm());
			AppData.admin.setPassword(newPassword);
		}
		return initializeProfile(model);
	}

	@RequestMapping(value = "profile-edited", method = RequestMethod.POST)
	public String editProfile(@ModelAttribute(value = "adminEdit") Administrator ad, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		if (ad.isEnoughInfor()) {
			userService.updateAdmin(ad);
			AppData.admin = ad;
			model.put("editResult", AppData.EDITSUCCESS);
		} else {
			model.put("editResult", AppData.INFOR_NOT_ENOUGH);
		}
		return initializeProfile(model);
	}

	@RequestMapping(value = "profile-img-edited", method = RequestMethod.POST)
	public String profileImgEdited(@RequestParam(value = "img") CommonsMultipartFile img, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		userService.editProfileImg(AppData.admin.getUsername(), appService.uploadImage(img, request, model, "users"));
		AppData.admin = userService.getAdminByUserName(AppData.admin.getUsername());
		return initializeProfile(model);
	}

	// rooms
	@RequestMapping(value = {"manage-rooms", "room", "rooms"}, method = RequestMethod.GET)
	public String manageRooms(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return authInitializeRedirect(request, response, model, "manage-rooms");
	}

	@RequestMapping(value = "room/{roomName}", method = RequestMethod.GET)
	public String singleRoom(@PathVariable(value = "roomName") String roomName, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		return initializeSingleRoom(model, roomName, "room");
	}

	@RequestMapping(value = "edit-room/{roomName}", method = RequestMethod.GET)
	public String editRoom(@PathVariable(value = "roomName") String roomName, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		model.addAttribute("roomEdit", new HotelRoom());
		return initializeSingleRoom(model, roomName, "edit-room");
	}

	@RequestMapping(value = "add-room", method = RequestMethod.GET)
	public String addRoom(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("newRoom", new HotelRoom());
		return authInitializeRedirect(request, response, model, "add-room");
	}

	@RequestMapping(value = "room-added", method = RequestMethod.POST)
	public String roomAdded(@ModelAttribute(value = "newRoom") HotelRoom newRoom, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		initialize(model);
		newRoom.setNewInfor();
		String ableToAddNewRoom = newRoom.getAbleToUpdate();
		model.put("addResult", ableToAddNewRoom);
		if (ableToAddNewRoom.equals(AppData.ABLE_TO_ADD)) {
			model.addAttribute("roomEdit", new HotelRoom());
			model.put("room", newRoom);
			model.put("relatedRoom", hotelItemService.getRelatedHotelRooms(newRoom.getType()));
		} else {
			model.addAttribute("newRoom", new HotelRoom());
			return "add-room";
		}
		return initializeSingleRoom(model, hotelItemService.findAndAddNewRoom(newRoom), "edit-room");
	}

	@RequestMapping(value = "room-edited", method = RequestMethod.POST)
	public String roomEdited(@ModelAttribute(value = "roomEdit") HotelRoom roomEdit, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		roomEdit.initializeSomeInfor();
		initialize(model);
		String ableToEditRoom = roomEdit.getAbleToUpdate();
		model.put("editResult", ableToEditRoom);
		if (ableToEditRoom.equals(AppData.ABLE_TO_EDIT)) {
			hotelItemService.updateRoom(roomEdit);
			model.put("room", roomEdit);
			model.put("relatedRoom", hotelItemService.getRelatedHotelRooms(roomEdit.getType()));
		} else {
			return initializeSingleRoom(model, roomEdit.getName(), "edit-room");
		}
		return "edit-room";
	}

	@RequestMapping(value = "remove-room/{roomName}", method = RequestMethod.GET)
	public String removeRoom(@PathVariable(value = "roomName") String roomName, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		hotelItemService.deleteRoom(roomName);
		model.put("deleteResult", AppData.ABLE_TO_EDIT);
		return manageRooms(request, response, model);
	}

	@RequestMapping(value = "room-img-edited/{roomName}", method = RequestMethod.POST)
	public String roomImgEdited(@RequestParam(value = "img1") CommonsMultipartFile img1, @RequestParam(value = "img2") CommonsMultipartFile img2, @PathVariable(value = "roomName") String roomName, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		model.addAttribute("roomEdit", new HotelRoom());
		hotelItemService.editImageRoom(roomName, appService.uploadImage(img1, request, model, "rooms"), appService.uploadImage(img2, request, model, "rooms"));
		return initializeSingleRoom(model, roomName, "edit-room");
	}

	// restaurant
	@RequestMapping(value = {"manage-restaurant, service, services"}, method = RequestMethod.GET)
	public String manageRestaurant(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return authInitializeRedirect(request, response, model, "manage-restaurant");
	}

	@RequestMapping(value = "service/{servicename}", method = RequestMethod.GET)
	public String singleService(@PathVariable(value = "servicename") String servicename, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		return initializeSingleService(model, servicename, "service");
	}

	@RequestMapping(value = "add-service", method = RequestMethod.GET)
	public String addService(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("newService", new HotelService());
		return authInitializeRedirect(request, response, model, "add-service");
	}

	@RequestMapping(value = "service-added", method = RequestMethod.POST)
	public String serviceAdded(@ModelAttribute(value = "newService") HotelService newService, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		initialize(model);
		newService.setNewInfor();
		String ableToAddNewService = newService.getAbleToUpdate();
		model.put("addResult", ableToAddNewService);
		if (ableToAddNewService.equals(AppData.ABLE_TO_ADD)) {
			model.addAttribute("serviceEdit", new HotelService());
			model.put("service", newService);
			model.put("relatedRoom", hotelItemService.getRelatedHotelServices(newService.getType()));
		} else {
			model.addAttribute("newService", new HotelService());
			return "add-service";
		}
		return initializeSingleService(model, hotelItemService.findAndAddNewService(newService), "edit-service");
	}

	@RequestMapping(value = "edit-service/{servicename}", method = RequestMethod.GET)
	public String editService(@PathVariable(value = "servicename") String servicename, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("serviceEdit", new HotelService());
		return initializeSingleService(model, servicename, "edit-service");
	}

	@RequestMapping(value = "service-edited", method = RequestMethod.POST)
	public String serviceEdited(@ModelAttribute(value = "serviceEdit") HotelService serviceEdit, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		serviceEdit.initializeSomeInfor();
		initialize(model);
		String strEdit = serviceEdit.getAbleToUpdate();
		model.put("editResult", strEdit);
		if (strEdit.equals(AppData.ABLE_TO_EDIT)) {
			hotelItemService.updateService(serviceEdit);
			model.put("service", serviceEdit);
			model.put("relatedServices", hotelItemService.getRelatedHotelServices(serviceEdit.getType()));
		} else {
			return initializeSingleService(model, serviceEdit.getName(), "edit-service");
		}
		return "edit-service";
	}

	@RequestMapping(value = "remove-service/{servicename}", method = RequestMethod.GET)
	public String removeService(@PathVariable(value = "servicename") String servicename, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		hotelItemService.deleteService(servicename);
		model.put("deleteResult", AppData.ABLE_TO_EDIT);
		return manageRestaurant(request, response, model);
	}

	@RequestMapping(value = "service-img-edited/{servicename}", method = RequestMethod.POST)
	public String serviceImgEdited(@RequestParam(value = "img1") CommonsMultipartFile img1, @RequestParam(value = "img2") CommonsMultipartFile img2, @PathVariable(value = "servicename") String servicename, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("serviceEdit", new HotelService());
		hotelItemService.editImageService(servicename, appService.uploadImage(img1, request, model, "restaurant"), appService.uploadImage(img2, request, model, "restaurant"));
		return initializeSingleService(model, servicename, "edit-service");
	}

	// users
	@RequestMapping(value = "manage-users", method = RequestMethod.GET)
	public String manageUsers(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		initialize(model);
		model.put("cusDataCollection", userService.getDataCollection());
		return "manage-users";
	}

	@RequestMapping(value = "follow-users", method = RequestMethod.GET)
	public String viewStatistics(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return authInitializeRedirect(request, response, model, "follow-users");
	}

	@RequestMapping(value = "tracking-ip", method = RequestMethod.GET)
	public String followUsers(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return initializeTracking("UserIP", request, response, model);
	}

	@RequestMapping(value = "tracking-exip", method = RequestMethod.GET)
	public String followUsersExternalIP(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return initializeTracking("ExternalIP", request, response, model);
	}

	@RequestMapping(value = "tracking-members", method = RequestMethod.GET)
	public String followMemebers(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return initializeTracking("Username", request, response, model);
	}
	
	@RequestMapping(value = "tracking-page-access", method = RequestMethod.GET)
	public String pageAccessStatistics(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return initializeTracking("PageAccess", request, response, model);
	}

	@RequestMapping(value = "country-chart", method = RequestMethod.GET)
	public String followUserChart(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return authInitializeRedirect(request, response, model, "country-chart");
	}

	@RequestMapping(value = {"page-access-chart", "PageAccess/**"}, method = RequestMethod.GET)
	public String pageAccessChart(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.put("ipaddress", "All IP address");
		return authInitializeRedirect(request, response, model, "page-access-chart");
	}

	@RequestMapping(value = {"member-chart/{username}","Username/{username}"}, method = RequestMethod.GET)
	public String pageAccessMemberChart(@PathVariable(value = "username") String username, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return authInitializeRedirect(request, response, model, "member-chart");
	}
	
	@RequestMapping(value = {"page-access-chart/{ipaddress}", "UserIP/{ipaddress}"}, method = RequestMethod.GET)
	public String pageAccessIPChart(@PathVariable(value = "ipaddress") String ipaddress, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return authInitializeRedirect(request, response, model, "page-access-chart");
	}

	@RequestMapping(value = "click-tracking-ip/{trackingParam}", method = RequestMethod.GET)
	public String followUsersIP(@PathVariable(value = "trackingParam") String ip, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.put("tracking","ip");
		return authInitializeRedirect(request, response, model, "click-tracking");
	}
	
	@RequestMapping(value = "click-tracking-member/{trackingParam}", method = RequestMethod.GET)
	public String followMember(@PathVariable(value = "trackingParam") String username, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.put("tracking","member");
		return authInitializeRedirect(request, response, model, "click-tracking");
	}
	
	@RequestMapping(value = {"ip-details/{externalip}", "ExternalIP/{externalip}"}, method = RequestMethod.GET)
	public String ipDetails(@PathVariable(value = "externalip") String externalip, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		initialize(model);
		model.put("ipDetails", userService.getExternalIPDetails(externalip));
		return "ip-details";
	}

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String singleUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return manageUsers(request, response, model);
	}

	@RequestMapping(value = "customer", method = RequestMethod.GET)
	public String singleCustomer(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return manageUsers(request, response, model);
	}

	@RequestMapping(value = "user/{username}", method = RequestMethod.GET)
	public String singleUser(@PathVariable(value = "username") String username, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		initialize(model);
		Customer cus = userService.getCustomerByUsername(username);
		cus.setActivity(userService.getAllActivityByUserName(username));
		model.put("customer", cus);
		return "user";
	}

	@RequestMapping(value = "customer/{username}", method = RequestMethod.GET)
	public String singleCustomer(@PathVariable(value = "username") String username, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		initialize(model);
		model.put("cusDataCollection", userService.getOneDataCollection(username));
		return "customer";
	}

	// message
	@RequestMapping(value = "message", method = RequestMethod.GET)
	public String message(HttpServletRequest request, HttpServletResponse response, ModelMap model)  {
		return authInitializeRedirect(request, response, model, "message");
	}

	@RequestMapping(value = "notification/{id}", method = RequestMethod.GET)
	public String notification(@PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		userService.seenNotification(id);
		model.put("activity", userService.getActivityBy(id));
		model.put("emailTemplates", AppData.EMAIL_TEMPLATE_1);
		initialize(model);
		return "notification";
	}

	@RequestMapping(value = "reply Book Room/{id}", method = RequestMethod.GET)
	public String replyBooking(@PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		userService.seenNotification(id);
		model.put("activity", userService.getActivityBy(id));
		model.put("emailTemplates", AppData.EMAIL_TEMPLATE_1);
		initialize(model);
		model.put("emailsent", "");
		return "reply Book Room";
	}

	@RequestMapping(value = "reply Cancel Room/{id}", method = RequestMethod.GET)
	public String replyCancel(@PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		model.put("activity", userService.seenNotification(id));
		initialize(model);
		return "reply Cancel Room";
	}

	@RequestMapping(value = "send-mail", method = RequestMethod.POST)
	public String sendMail(@RequestParam("activity-id") String id, @RequestParam("message") String message, @RequestParam("user-email") String useremail, @RequestParam("subject") String subject, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		model.put("emailsent", appService.sendEmail(appService.removeAccent(message), useremail, subject));
		model.put("emailTemplates", AppData.EMAIL_TEMPLATE_1);
		model.put("activity", userService.replyNotification(id));
		initialize(model);
		return (subject.equals("Book Room") || subject.equals("Cancel Room")) ? "reply " + subject : "notification";
	}

	// fqa
	@RequestMapping(value = "fqa", method = RequestMethod.GET)
	public String fqa(HttpServletRequest request, HttpServletResponse response, ModelMap model, String redirect) {
		return authInitializeRedirect(request, response, model, "fqa");
	}
	
	@RequestMapping(value = "upload-fqa", method = RequestMethod.POST)
	public String UploadFQA(@RequestParam(value = "fqaPDF") CommonsMultipartFile fqaPDF, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		checkAuth(request, response);
		initialize(model);
		appService.uploadPDF(fqaPDF, request, model);
		return "fqa";
	}

	@RequestMapping(value = "downloadCSV")
	public void downloadCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

	private boolean isAuthenticated(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		if (username == null || username.equals(""))
			return false;
		if (AppData.admin.getUsername() == null)
			AppData.admin = userService.getAdminByUserName(username);
		return (AppData.admin != null && AppData.admin.getUsername().equals(username)) ? true : false;
	}
	
	private void checkAuth(HttpServletRequest request, HttpServletResponse response) {
		if(!isAuthenticated(request)) {
			try {
				response.sendRedirect("login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String authInitializeRedirect(HttpServletRequest request, HttpServletResponse response, ModelMap model, String redirect) {
		checkAuth(request, response);
		initialize(model);
		return redirect;
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

	private String initializeProfile(ModelMap model) {
		initialize(model);
		model.addAttribute("adminEdit", new Administrator());
		model.addAttribute("changePassBean", new ChangePasswordBean());
		return "profile";
	}

	private String initializeSingleRoom(ModelMap model, String roomName, String redirect) {
		initialize(model);
		HotelRoom room = hotelItemService.getRoomByName(roomName);
		model.put("room", room);
		model.put("relatedRoom", hotelItemService.getRelatedHotelRooms(room.getType()));
		return redirect;
	}

	private String initializeSingleService(ModelMap model, String servicename, String redirect) {
		initialize(model);
		HotelService service = hotelItemService.getHotelServiceByName(servicename);
		model.put("service", service);
		model.put("relatedServices", hotelItemService.getRelatedHotelServices(service.getType()));
		return redirect;
	}
	
	private String initializeTracking(String tracking, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.put("tracking", tracking);
		return authInitializeRedirect(request, response, model, "tracking-users");
	}
}