package mongodb.daoimpls;

import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class JsonParserDAO {
	
	private Gson gson = new Gson();

	public <T> T fromJson(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}

	public <T> T fromJson(DBObject dbObject, Class<T> classOfT) {
		String _id = dbObject.removeField("_id").toString();
		String json = dbObject + "";
		json = json.substring(0, json.length() - 1) + ",\"_id\":\""  +  _id + "\"}";
		return gson.fromJson(json, classOfT);
	}
	
	public <T> T fromJson2(DBObject dbObject, Class<T> classOfT) {
		String _id = dbObject.removeField("_id").toString();
		String created_at = dbObject.removeField("created_at").toString();
		String json = dbObject + "";
		json = json.substring(0, json.length() - 1) + ",\"_id\":\""  +  _id + "\",\"created_at\":\""  +  created_at + "\"}";
		return gson.fromJson(json, classOfT);
	}

	public String toJson(Object src) {
		return gson.toJson(src);
	}
	
	public DBObject parseJSON(String json) {
		return (DBObject) JSON.parse(json);
	}
}
