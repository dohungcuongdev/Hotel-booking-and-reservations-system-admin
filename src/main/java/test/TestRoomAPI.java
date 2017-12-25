package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import model.mysql.hotel.HotelRoom;
import statics.provider.DateTimeCalculator;

public class TestRoomAPI {

	public static List<HotelRoom> convertRoomFromMongoToMySQL() {
		List<HotelRoom> l = new ArrayList<>();
		try {
			APIRoom a = new APIRoom();
			String json = a.getApi();
			JSONArray jarray = new JSONArray(json);
			for (int i = 0; i < jarray.length(); i++) {
				JSONObject jobj = jarray.getJSONObject(i);
				String name = jobj.getString("name");
				int price = jobj.getInt("price");
				String img = jobj.getString("img");
				String img2 = jobj.getString("img2");
				String details = jobj.getString("details");
				String type = jobj.getString("type");
				String created_by = jobj.getString("created_by");
				int size = jobj.getInt("size");
				int numpeople = jobj.getInt("numpeople");
				String status = jobj.getString("status");
				String amenities = jobj.getString("amenities");
				String booked_by = jobj.getString("booked_by");
				int avgAminities = jobj.getInt("avgAminities");
				Date checkin = null;
				Date checkout = null;
				Date created_at = DateTimeCalculator.getICTDateTime(jobj.getString("created_at"));
				int star = jobj.getInt("star");
				int numvote = jobj.getInt("numvote");
				HotelRoom room = new HotelRoom(name, price, img, img2, details, type, created_by, created_at, size,
						numpeople, status, amenities, booked_by, avgAminities, checkin, checkout, star, numvote);
				l.add(room);
			}
		} catch (Exception e) {

		}
		return l;
	}

	public static void main(String[] args) throws JSONException {

	}

}
