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
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private HotelItemService hotelItemService;

	@Autowired
	private ApplicationService appService;

	// index
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		return "index";
	}

	// login
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {
		if (isAuthenticated(request))
			return index(model, request);
		return "login";
	}

	// checklogin
	@RequestMapping(value = "check-login", method = RequestMethod.POST)
	public String checklogin(@ModelAttribute(value = "loginbean") LoginBean loginbean, ModelMap model,
			HttpServletRequest request) throws IOException {
		if (isAuthenticated(request))
			return index(model, request);
		String username = loginbean.getUserName();
		String password = loginbean.getPassword();
		AppData.admin = userService.getAdminByUserName(username);
		if (AppData.admin != null && username.equals(AppData.admin.getUsername())
				&& password.equals(AppData.admin.getPassword())) {
			request.getSession().setAttribute("username", username);
			request.getSession().setMaxInactiveInterval(24 * 60 * 60);
			return index(model, request);
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

	@RequestMapping(value = "search-result/{keyword}", method = RequestMethod.GET)
	public String searchResult(@PathVariable(value = "keyword") String keyword, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		model.put("keyword", keyword);
		model.put("cusDataCollection", userService.getDataCollection());
		initializeFollowUser(model);
		return "search-result";
	}

	// profile
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String profile(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		return initializeProfile(model);
	}

	// profile
	@RequestMapping(value = "change-password", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute(value = "changePassBean") ChangePasswordBean changePassBean,
			ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		String correctPassword = AppData.admin.getPassword();
		String newPassword = changePassBean.getNewpassword();
		model.put("pwCheckingResult", changePassBean.getPWCheckingResult(correctPassword));
		if (changePassBean.isMatchPassword(correctPassword)) {
			userService.updatePassword(AppData.admin.getUsername(), correctPassword,
					changePassBean.getCurrentpassword(), newPassword, changePassBean.getConfirm());
			AppData.admin.setPassword(newPassword);
		}
		return initializeProfile(model);
	}

	@RequestMapping(value = "profile-edited", method = RequestMethod.POST)
	public String editProfile(@ModelAttribute(value = "adminEdit") Administrator ad, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
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
	public String profileImgEdited(@RequestParam(value = "img") CommonsMultipartFile img, HttpServletRequest request,
			ModelMap model) {
		if (!isAuthenticated(request))
			return "login";
		userService.editProfileImg(AppData.admin.getUsername(), appService.uploadfile(img, request, model, "users"));
		AppData.admin = userService.getAdminByUserName(AppData.admin.getUsername());
		return profile(model, request);
	}

	// rooms
	@RequestMapping(value = "manage-rooms", method = RequestMethod.GET)
	public String manageRooms(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		return "manage-rooms";
	}

	@RequestMapping(value = "room", method = RequestMethod.GET)
	public String singleRoom(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		return manageRooms(model, request);
	}

	@RequestMapping(value = "room/{roomid}", method = RequestMethod.GET)
	public String singleRoom(@PathVariable(value = "roomid") String roomid, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		return initializeSingleRoom(model, roomid, "room");
	}

	@RequestMapping(value = "edit-room/{roomid}", method = RequestMethod.GET)
	public String editRoom(@PathVariable(value = "roomid") String roomid, ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		model.addAttribute("roomEdit", new HotelRoom());
		return initializeSingleRoom(model, roomid, "edit-room");
	}

	@RequestMapping(value = "room-edited", method = RequestMethod.POST)
	public String roomEdited(@ModelAttribute(value = "roomEdit") HotelRoom roomEdit, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		roomEdit.initializeSomeInfor();
		initialize(model);
		String strEdit = roomEdit.getAbleToEdit();
		model.put("editResult", strEdit);
		if (strEdit.equals(AppData.ABLE_TO_EDIT)) {
			hotelItemService.updateRoom(roomEdit);
			model.put("room", roomEdit);
			model.put("relatedRoom", hotelItemService.getRelatedHotelRooms(roomEdit.getType()));
		} else {
			return initializeSingleRoom(model, roomEdit.getId(), "edit-room");
		}
		return "edit-room";
	}

	@RequestMapping(value = "remove-room/{roomid}", method = RequestMethod.GET)
	public String removeRoom(@PathVariable(value = "roomid") String roomid, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		hotelItemService.deleteRoom(roomid);
		model.put("deleteResult", AppData.ABLE_TO_EDIT);
		return manageRooms(model, request);
	}

	@RequestMapping(value = "room-img-edited/{roomid}", method = RequestMethod.POST)
	public String roomImgEdited(@RequestParam(value = "img1") CommonsMultipartFile img1,
			@RequestParam(value = "img2") CommonsMultipartFile img2, HttpServletRequest request,
			@PathVariable(value = "roomid") String roomid, ModelMap model) {
		if (!isAuthenticated(request))
			return "login";
		model.addAttribute("roomEdit", new HotelRoom());
		hotelItemService.editImageRoom(roomid, appService.uploadfile(img1, request, model, "rooms"),
				appService.uploadfile(img2, request, model, "rooms"));
		return initializeSingleRoom(model, roomid, "edit-room");
	}

	// restaurant
	@RequestMapping(value = "manage-restaurant", method = RequestMethod.GET)
	public String manageRestaurant(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		return "manage-restaurant";
	}

	@RequestMapping(value = "service", method = RequestMethod.GET)
	public String singleService(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		return manageRestaurant(model, request);
	}

	@RequestMapping(value = "service/{serviceid}", method = RequestMethod.GET)
	public String singleService(@PathVariable(value = "serviceid") String serviceid, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		return initializeSingleService(model, serviceid, "service");
	}

	@RequestMapping(value = "edit-service/{serviceid}", method = RequestMethod.GET)
	public String editService(@PathVariable(value = "serviceid") String serviceid, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		model.addAttribute("serviceEdit", new HotelService());
		return initializeSingleService(model, serviceid, "edit-service");
	}

	@RequestMapping(value = "service-edited", method = RequestMethod.POST)
	public String serviceEdited(@ModelAttribute(value = "serviceEdit") HotelService serviceEdit, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		serviceEdit.initializeSomeInfor();
		initialize(model);
		String strEdit = serviceEdit.getAbleToEdit();
		model.put("editResult", strEdit);
		if (strEdit.equals(AppData.ABLE_TO_EDIT)) {
			hotelItemService.updateService(serviceEdit);
			model.put("service", serviceEdit);
			model.put("relatedServices", hotelItemService.getRelatedHotelServices(serviceEdit.getType()));
		} else {
			return initializeSingleService(model, serviceEdit.getId(), "edit-service");
		}
		return "edit-service";
	}

	@RequestMapping(value = "remove-service/{serviceid}", method = RequestMethod.GET)
	public String removeService(@PathVariable(value = "serviceid") String serviceid, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		hotelItemService.deleteService(serviceid);
		model.put("deleteResult", AppData.ABLE_TO_EDIT);
		return manageRestaurant(model, request);
	}

	@RequestMapping(value = "service-img-edited/{serviceid}", method = RequestMethod.POST)
	public String serviceImgEdited(@RequestParam(value = "img1") CommonsMultipartFile img1,
			@RequestParam(value = "img2") CommonsMultipartFile img2, HttpServletRequest request,
			@PathVariable(value = "serviceid") String serviceid, ModelMap model) {
		if (!isAuthenticated(request))
			return "login";
		model.addAttribute("serviceEdit", new HotelService());
		hotelItemService.editImageService(serviceid, appService.uploadfile(img1, request, model, "restaurant"),
				appService.uploadfile(img2, request, model, "restaurant"));
		return initializeSingleService(model, serviceid, "edit-service");
	}

	// users
	@RequestMapping(value = "manage-users", method = RequestMethod.GET)
	public String manageUsers(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		model.put("cusDataCollection", userService.getDataCollection());
		return "manage-users";
	}

	@RequestMapping(value = "follow-users", method = RequestMethod.GET)
	public String followUsers(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		initializeFollowUser(model);
		return "follow-users";
	}

	@RequestMapping(value = "view-statistics", method = RequestMethod.GET)
	public String viewStatistics(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		return "view-statistics";
	}

	@RequestMapping(value = "country-chart", method = RequestMethod.GET)
	public String followUserChart(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		return "country-chart";
	}

	@RequestMapping(value = "page-access-chart", method = RequestMethod.GET)
	public String pageAccessChart(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		Map m = userService.getPageAccessChartData(userService.getListFollowUsers());
		model.put("ipaddress", "All IP address");
		model.put("mapPageAccess", m);
		model.put("jsonchart", userService.getJSONPageAccess(m));
		return "page-access-chart";
	}

	@RequestMapping(value = "page-access-chart/{ipaddress}", method = RequestMethod.GET)
	public String pageAccessIPChart(@PathVariable(value = "ipaddress") String ipaddress, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		Map m = userService.getPageAccessChartDataByIP(ipaddress, userService.getListFollowUsers());
		model.put("ipaddress", ipaddress);
		model.put("mapPageAccess", m);
		model.put("jsonchart", userService.getJSONPageAccess(m));
		return "page-access-chart";
	}

	@RequestMapping(value = "follow-user-ip/{ip}", method = RequestMethod.GET)
	public String followUsersIP(@PathVariable(value = "ip") String ip, ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		List<FollowUsers> list = userService.getListFollowUsers();
		model.put("listFollowUsers", list);
		model.put("mapFollowUserIP", userService.getFollowUsersMapByOneIP(list, ip));
		return "follow-user-ip";
	}

	@RequestMapping(value = "ip-details/{externalip}", method = RequestMethod.GET)
	public String ipDetails(@PathVariable(value = "externalip") String externalip, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		model.put("ipDetails", userService.getExternalIPDetails(externalip));
		return "ip-details";
	}

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String singleUser(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		return manageUsers(model, request);
	}

	@RequestMapping(value = "customer", method = RequestMethod.GET)
	public String singleCustomer(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		return manageUsers(model, request);
	}

	@RequestMapping(value = "user/{username}", method = RequestMethod.GET)
	public String singleUser(@PathVariable(value = "username") String username, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		Customer cus = userService.getCustomerByUsername(username);
		cus.setActivity(userService.getAllActivityByUserName(username));
		model.put("customer", cus);
		return "user";
	}

	@RequestMapping(value = "customer/{username}", method = RequestMethod.GET)
	public String singleCustomer(@PathVariable(value = "username") String username, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		model.put("cusDataCollection", userService.getOneDataCollection(username));
		return "customer";
	}

	// message
	@RequestMapping(value = "message", method = RequestMethod.GET)
	public String message(ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		return "message";
	}

	@RequestMapping(value = "notification/{id}", method = RequestMethod.GET)
	public String notification(@PathVariable(value = "id") String id, ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		userService.seenNotification(id);
		model.put("activity", userService.getActivityBy(id));
		model.put("emailTemplates", AppData.EMAIL_TEMPLATE_1);
		initialize(model);
		return "notification";
	}

	@RequestMapping(value = "reply Book Room/{id}", method = RequestMethod.GET)
	public String replyBooking(@PathVariable(value = "id") String id, ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		userService.seenNotification(id);
		model.put("activity", userService.getActivityBy(id));
		model.put("emailTemplates", AppData.EMAIL_TEMPLATE_1);
		initialize(model);
		model.put("emailsent", "");
		return "reply Book Room";
	}

	@RequestMapping(value = "reply Cancel Room/{id}", method = RequestMethod.GET)
	public String replyCancel(@PathVariable(value = "id") String id, ModelMap model, HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		userService.seenNotification(id);
		model.put("activity", userService.getActivityBy(id));
		initialize(model);
		return "reply Cancel Room";
	}

	@RequestMapping(value = "send-mail", method = RequestMethod.POST)
	public String sendMail(@RequestParam("activity-id") String id, @RequestParam("message") String message,
			@RequestParam("user-email") String useremail, @RequestParam("subject") String subject, ModelMap model,
			HttpServletRequest request) {
		if (!isAuthenticated(request))
			return "login";
		model.put("emailsent", appService.sendEmail(appService.removeAccent(message), useremail, subject));
		model.put("emailTemplates", AppData.EMAIL_TEMPLATE_1);
		return replyEmail("reply " + subject, id, model);
	}

	// fqa
	@RequestMapping(value = "fqa", method = RequestMethod.GET)
	public String fqa(HttpServletRequest request, ModelMap model) {
		if (!isAuthenticated(request))
			return "login";
		initialize(model);
		return "fqa";
	}

	@RequestMapping(value = "downloadCSV")
	public void downloadCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!isAuthenticated(request))
			response.sendRedirect("index");
		else {
			response.setContentType("text/csv");
			// creates mock data
			String headerKey = AppData.HEADERKEY;
			String headerValue = String.format(AppData.CSV_FORMAT, AppData.CSV_FILENAME);
			response.setHeader(headerKey, headerValue);
			try ( // uses the Super CSV API to generate CSV data from the model data
					ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
							CsvPreference.STANDARD_PREFERENCE)) {
				String[] header = AppData.HEADERCSV;
				csvWriter.writeHeader(header);
				for (FollowUsers r : userService.getListFollowUsers()) {
					csvWriter.write(r, header);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
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

	private void initializeFollowUser(ModelMap model) {
		List<FollowUsers> list = userService.getListFollowUsers();
		model.put("mapFollowUsers", userService.getFollowUsersMap(list));
		model.put("mapFollowUsersIP", userService.getFollowUsersMapByIP(list));
		model.put("mapsExternalIP", userService.getMapByExternalIP(list));
	}

	private String initializeSingleRoom(ModelMap model, String roomid, String redirect) {
		initialize(model);
		HotelRoom room = hotelItemService.getRoomByID(roomid);
		model.put("room", room);
		model.put("relatedRoom", hotelItemService.getRelatedHotelRooms(room.getType()));
		return redirect;
	}

	private String initializeSingleService(ModelMap model, String serviceid, String redirect) {
		initialize(model);
		HotelService service = hotelItemService.getHotelServiceByID(serviceid);
		model.put("service", service);
		model.put("relatedServices", hotelItemService.getRelatedHotelServices(service.getType()));
		return redirect;
	}

	private String replyEmail(String redirect, String id, ModelMap model) {
		model.put("activity", userService.getActivityBy(id));
		initialize(model);
		return (redirect.equals("reply Book Room") || redirect.equals("reply Cancel Room")) ? redirect : "notification";
	}

}