package services.impl;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mongodb.DBObject;

import services.JsonParserService;

@Service
public class JsonParserServiceImpl implements JsonParserService {

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

	public String toJson(Object src) {
		return gson.toJson(src);
	}
}