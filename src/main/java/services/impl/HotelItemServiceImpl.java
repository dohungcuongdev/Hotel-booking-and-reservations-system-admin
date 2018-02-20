/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.RestaurantDAO;
import daos.RoomDAO;
import model.sql.hotel.HotelRoom;
import model.sql.hotel.HotelService;
import services.HotelItemService;
import test.TestConvertMongoToSQL;

/**
 *
 * @author HUNGCUONG
 */

@Service
public class HotelItemServiceImpl implements HotelItemService {
    
	@Autowired
    private RoomDAO roomDAO;
	
	@Autowired
    private RestaurantDAO restaurantDAO;

    @Override
    public HotelRoom getRoomByID(int id) {
        return roomDAO.getRoomByID(id);
    }

    @Override
    public List<HotelRoom> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    @Override
    public List<HotelRoom> getRelatedHotelRooms(String type) {
        return roomDAO.getRelatedHotelRooms(type);
    }

    @Override
    public void updateRoom(HotelRoom room) {
        roomDAO.updateRoom(room);
    }

    @Override
    public List<HotelService> getAllHotelServices() {
        return restaurantDAO.getAllHotelServices();
    }

    @Override
    public List<HotelService> getRelatedHotelServices(String type) {
        return restaurantDAO.getRelatedHotelServices(type);
    }

    @Override
    public void updateService(HotelService service) {
        restaurantDAO.updateService(service);
    }

    @Override
    public void editImageRoom(String name, String img, String img2) {
        roomDAO.editImage(name, img, img2);
    }

    @Override
    public void deleteRoom(int id) {
        roomDAO.deleteItem(id);
    }

    @Override
    public void editImageService(String name, String img, String img2) {
        restaurantDAO.editImage(name, img, img2);
    }

    @Override
    public void deleteService(int id) {
        restaurantDAO.deleteItem(id);
    }

	@Override
	public HotelService getHotelServiceByID(int id) {
		return restaurantDAO.getHotelServiceByID(id);
	}

	@Override
	public String findAndAddNewRoom(HotelRoom newRoom) {
		return roomDAO.findAndAddNewRoom(newRoom);
	}

	@Override
	public String findAndAddNewService(HotelService newService) {
		return restaurantDAO.findAndAddNewService(newService);
	}

	@Override
	public HotelRoom getRoomByName(String name) {
		return roomDAO.getRoomByName(name);
	}

	@Override
	public List<HotelRoom> getRoomByPage(int page) {
		return roomDAO.getRoomByPage(page);
	}

	@Override
	public long getNumRooms() {
		return roomDAO.getNumRooms();
	}

	@Override
	public HotelService getHotelServiceByName(String name) {
		return restaurantDAO.getHotelServiceByName(name);
	}
	
	@Override
	public void convertRoomFromMongoToMySQL() {
		List<HotelRoom> l = TestConvertMongoToSQL.convertRoomFromMongoToMySQL();
		System.out.println(l);
		for(HotelRoom r: l) {
			String roomName = roomDAO.findAndAddNewRoom(r);
			System.out.println(roomName);
		}
	}

	@Override
	public void convertRestaurantMongoToMySQL() {
		List<HotelService> l = TestConvertMongoToSQL.convertRestaurantMongoToMySQL();
		for(HotelService hs: l) {
			restaurantDAO.findAndAddNewService(hs);
		}
		
	}

	@Override
	public void bookRoom(HotelRoom room) {
		roomDAO.bookRoom(room);
	}
	
	@Override
	public void feedbackRoom(HotelRoom room) {
		roomDAO.feedbackRoom(room);
	}
}
