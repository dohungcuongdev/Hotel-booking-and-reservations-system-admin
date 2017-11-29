package controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.user.tracking.ChartData;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import services.APIService;
import services.UserService;

@RestController
@RequestMapping("/api")
public class RESTController {
	
	@Autowired
	private APIService testService;
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@RequestMapping(value = "/follow-users", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String getListFollowUsers() throws ParseException, IOException, JSONException {
		return testService.getListFollowUsers().toString();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/chart-data", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<ChartData> getChartData() {
		return userService.getListFollowUsersChartData(userService.getListFollowUsers());
	}

}
