package test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.sql.hotel.HotelRoom;
import model.sql.hotel.HotelService;
import sql.daoimpls.APIDAO;

public class TestConvertMongoToSQL {

	public static List<HotelRoom> convertRoomFromMongoToMySQL() {
		List<HotelRoom> l = new ArrayList<>();
		try {
			TestAPI a = new TestAPI();
			String json = a.getApi();
			JSONArray jarray = new JSONArray(json);
			for (int i = 0; i < jarray.length(); i++) {
				int id = i + 1;
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
				String created_at = jobj.getString("created_at");
				int star = jobj.getInt("star");
				int numvote = jobj.getInt("numvote");
				HotelRoom room = new HotelRoom(id, name, price, img, img2, details, type, created_by, created_at, size,
						numpeople, status, amenities, booked_by, avgAminities, "", "", star, numvote);
				l.add(room);
			}
		} catch (Exception e) {

		}
		return l;
	}
	
	public static List<HotelService> convertRestaurantMongoToMySQL() {
		List<HotelService> l = new ArrayList<>();
		try {
			TestAPI a = new TestAPI();
			String json = a.getApi2();
			JSONArray jarray = new JSONArray(json);
			for (int i = 0; i < jarray.length(); i++) {
				int id = i + 1;
				JSONObject jobj = jarray.getJSONObject(i);
				String name = jobj.getString("name");
				int price = jobj.getInt("price");
				String img = jobj.getString("img");
				String img2 = jobj.getString("img2");
				String details = jobj.getString("details");
				String type = jobj.getString("type");
				String created_by = jobj.getString("created_by");
				String created_at = jobj.getString("created_at");
				int quantity = jobj.getInt("quantity");
				String note = jobj.getString("note");
				String serveType = jobj.getString("serveType");
				String serveTime = jobj.getString("serveTime");
				HotelService service = new HotelService(id, name, price, img, img2, details, type, created_by, created_at, quantity, note, serveType, serveTime);
				l.add(service);
			}
		} catch (Exception e) {

		}
		return l;
	}

	public static void main(String[] args) throws JSONException {
		System.out.println(convertRoomFromMongoToMySQL());
	}

}
