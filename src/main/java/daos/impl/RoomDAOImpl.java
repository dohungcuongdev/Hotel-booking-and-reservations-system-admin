/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.CustomerDAO;
import daos.RoomDAO;
import model.mongodb.user.Customer;
import model.mysql.hotel.HotelRoom;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
@Transactional
public class RoomDAOImpl extends HotelItemDAOImpl<HotelRoom> implements RoomDAO {
	
    public RoomDAOImpl() {
    	classOfT = HotelRoom.class;
    }

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public HotelRoom getRoomByID(int id) {
		return (HotelRoom) getHotelItemByID(id);
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
		Query q = sessionFactory.getCurrentSession().createQuery("from " + HotelRoom.class.getName());
		q.setFirstResult((page - 1) * 6);
		q.setMaxResults(6);
		return q.list();
	}

	@Override
	public long getNumRooms() {
		return 0;
	}

	@Override
	public void updateRoom(HotelRoom room) {
		updateItem(room);
	}
	
	@Override
	public void bookRoom(HotelRoom room) {
		if(room.isReadyToBook()) {
			Customer whoBook = customerDAO.getCustomerByUsername(room.getBooked_by());
			if(whoBook.getBalance() >= room.getPrice()) {
				updateRoom(room);
			}
		}
	}
}
