/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb.daoimpls;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import daos.CustomerDAO;
import daos.RoomDAO;
import model.mongodb.user.Customer;
import model.sql.hotel.HotelRoom;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class RoomDAOImpl extends HotelItemDAOImpl<HotelRoom> implements RoomDAO {

	public RoomDAOImpl() throws UnknownHostException {
		classOfT = HotelRoom.class;
		collection = MongoDbConnector.createConnection("rooms");
	}

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public HotelRoom getRoomByID(int id) {
		return (HotelRoom) getHotelItemByID(id);
	}
	
	public HotelRoom getRoomByID(String _id) {
		return (HotelRoom) getHotelItemByID(_id);
	}

	@Override
	public HotelRoom getRoomByName(String name) {
		return (HotelRoom) getHotelItemByName(name);
	}

	@Override
	public List<HotelRoom> getAllRooms() {
		return getAllHotelItems();
	}

	@Override
	public List<HotelRoom> getRelatedHotelRooms(String type) {
		return getRelatedHotelItems(type);
	}

	@Override
	public String findAndAddNewRoom(HotelRoom newRoom) {
		return findAndAddNewItem(newRoom);
	}

	@Override
	public List<HotelRoom> getRoomByPage(int page) {
		ArrayList<HotelRoom> rooms = new ArrayList<>();
		DBCursor cursor = collection.find().skip((page - 1) * 6).limit(6);
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			rooms.add(fromJson(obj, classOfT));
		}
		return rooms;
	}

	@Override
	public long getNumRooms() {
		return collection.count();
	}

	@Override
	public void updateRoom(HotelRoom room) {
		updateItem(room);
	}

	@Override
	public void bookRoom(HotelRoom room) {
		if (room.isReadyToBook()) {
			Customer whoBook = customerDAO.getCustomerByUsername(room.getBooked_by());
			if (whoBook.getBalance() >= room.getPrice()) {
				updateRoom(room);
			}
		}
	}

	@Override
	public void feedbackRoom(HotelRoom room) {
		if (room.isReadyToFeedback()) {
			updateRoom(room);
		}
	}
}
