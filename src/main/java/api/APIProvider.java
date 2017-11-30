package api;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

public interface APIProvider {
	public String getResource(String name);

	public JSONArray getListFollowUsers() throws IOException, JSONException;
}
