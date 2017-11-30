package api.impl;

import org.springframework.stereotype.Repository;

import api.APIProvider;

import java.io.IOException;

import org.apache.http.HttpEntity;
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

@Repository
@Configuration
@PropertySource("classpath:api.properties")
public class APIProviderImpl implements APIProvider {

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
	public JSONArray getListFollowUsers() throws IOException, JSONException {
		HttpGet httpGetKeyAndId = new HttpGet(getResource("api.followuser"));
		JSONArray jsonArray = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpGetKeyAndId);) {
			HttpEntity entity = response.getEntity();
			jsonArray = new JSONArray(EntityUtils.toString(entity));
		}
		return jsonArray;
	}

}
