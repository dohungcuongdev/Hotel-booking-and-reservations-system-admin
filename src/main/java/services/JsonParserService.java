package services;

import com.mongodb.DBObject;

public interface JsonParserService {
	public <T> T fromJson(String json, Class<T> classOfT);

	public <T> T fromJson(DBObject dbObject, Class<T> classOfT);

	public String toJson(Object src);
}
