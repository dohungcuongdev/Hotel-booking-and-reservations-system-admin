package daos.impl;

import org.springframework.stereotype.Repository;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import daos.TestDAO;

@Repository
@Configuration
@PropertySource("classpath:database.properties")
public class TestDAOImpl implements TestDAO {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// injecting properties
	@Autowired
	private Environment env;

	@Override
	public String getResource(String name) {
		return env.getProperty(name);

	}
	
	@Override
	public JSONArray getListFollowUsers() throws IOException, ParseException, JSONException {
		HttpGet httpGetKeyAndId = new HttpGet("http://localhost:3000/api/follow-users");
		JSONArray jsonArray = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpGetKeyAndId);) {
			HttpEntity entity = response.getEntity();
			jsonArray = new JSONArray(EntityUtils.toString(entity));
		}
		return jsonArray;
	}

}
