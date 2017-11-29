package services.impl;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.APIDAO;
import services.APIService;

@Service
public class APIServiceImpl implements APIService {
	
	@Autowired
	private APIDAO testDAO;
	
	@Override
	public String getResource(String name) {
		return testDAO.getResource(name);
	}

	@Override
	public JSONArray getListFollowUsers() throws IOException, JSONException {
		return testDAO.getListFollowUsers();
	}

}
