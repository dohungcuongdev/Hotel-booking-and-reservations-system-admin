/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import model.hotel.HotelRoom;
import model.hotel.HotelService;

/**
 *
 * @author HUNGCUONG
 */
public interface HotelItemService {
    
    public HotelRoom getRoomByID(String id);
    public HotelRoom getRoomByName(String name);
    public List<HotelRoom> getAllRooms();
    public long getNumRooms();
    public List<HotelRoom> getRoomByPage(int skip, int limit);
    public List<HotelRoom> getRelatedHotelRooms(String type);
    public void updateRoom(HotelRoom room);
    public String findAndAddNewRoom(HotelRoom newRoom);
    public void editImageRoom(String name, String img, String img2);    
    public void deleteRoom(String id);   
    public HotelService getHotelServiceByID(String id);
    public HotelService getHotelServiceByName(String name);
    public List<HotelService> getAllHotelServices();   
    public List<HotelService> getRelatedHotelServices(String type);
    public String findAndAddNewService(HotelService newService);
    public void updateService(HotelService service);    
    public void editImageService(String name, String img, String img2);    
    public void deleteService(String name);   
}
