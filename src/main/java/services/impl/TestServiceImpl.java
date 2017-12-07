package services.impl;

import java.io.IOException;
import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import daos.TestDAO;
import services.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestDAO testDAO;
	
	@Override
	public String getResource(String name) {
		return testDAO.getResource(name);
	}

	@Override
	public JSONArray getListFollowUsers() throws IOException, ParseException, JSONException {
		return testDAO.getListFollowUsers();
	}
}
