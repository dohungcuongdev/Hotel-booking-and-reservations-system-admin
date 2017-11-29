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
import model.hotel.HotelRoom;
import model.hotel.HotelService;
import services.HotelItemService;

/**
 *
 * @author HUNGCUONG
 */

@Service
public class HotelItemServiceImpl implements HotelItemService {
    
	@Autowired
    private RoomDAO roomDAO;
	
	@Autowired
    private RestaurantDAO serviceDAO;

    @Override
    public HotelRoom getRoomByName(String name) {
        return roomDAO.getRoomByName(name);
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
    public HotelService getHotelServiceByName(String name) {
        return serviceDAO.getHotelServiceByName(name);
    }

    @Override
    public List<HotelService> getAllHotelServices() {
        return serviceDAO.getAllHotelServices();
    }

    @Override
    public List<HotelService> getRelatedHotelServices(String type) {
        return serviceDAO.getRelatedHotelServices(type);
    }

    @Override
    public void updateService(HotelService service) {
        serviceDAO.updateService(service);
    }

    @Override
    public void editImageRoom(String name, String img, String img2) {
        roomDAO.editImage(name, img, img2);
    }

    @Override
    public void deleteRoom(String name) {
        roomDAO.deleteItem(name);
    }

    @Override
    public void editImageService(String name, String img, String img2) {
        serviceDAO.editImage(name, img, img2);
    }

    @Override
    public void deleteService(String name) {
        serviceDAO.deleteItem(name);
    }
}
