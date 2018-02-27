package sql.daoimpls;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class APIDAO {

	private final Gson gson = new Gson();

	public String getStringAPI(String api) {
		// env.getProperty(api)
		HttpGet httpGetKeyAndId = new HttpGet(api);
		String jsonData = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpGetKeyAndId)) {
			HttpEntity entity = response.getEntity();
			jsonData = EntityUtils.toString(entity);
		} catch (IOException e) {
			System.err.print("Error: ");
			System.out.println("API " + api + " not found");
		}
		return jsonData;
	}

	public <T> T getJsonData(String json, Type typeOfT) throws JsonParseException {
		if(json == null)
			return null;
		return gson.fromJson(json, typeOfT);
	}

}
