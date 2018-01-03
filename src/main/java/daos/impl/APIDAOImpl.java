package daos.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.api.user.tracking.CountryChartData;
import statics.provider.GeoLookup;

public class APIDAOImpl {

	public String getStringAPI(String api) {
		// env.getProperty(api)
		HttpGet httpGetKeyAndId = new HttpGet(api);
		String jsonData = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpGetKeyAndId)) {
			HttpEntity entity = response.getEntity();
			jsonData = EntityUtils.toString(entity);
		} catch (IOException e) {
			System.out.println("API not found");
		}
		return jsonData;
	}
}
