package daos;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

public interface APIDAO {
	public String getResource(String name);

	public JSONArray getListFollowUsers() throws IOException, JSONException;
}
