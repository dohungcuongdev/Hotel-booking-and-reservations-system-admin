/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;

import model.sql.hotel.HotelService;

/**
 *
 * @author Do Hung Cuong
 */
public interface RestaurantDAO {

	public HotelService getHotelServiceByID(int id);
	public HotelService getHotelServiceByName(String name);
    public List<HotelService> getAllHotelServices();   
    public List<HotelService> getRelatedHotelServices(String type);   
    public String findAndAddNewService(HotelService newService);
    public void updateService(HotelService service);
	public void editImage(String name, String img, String img2);
	public void deleteItem(int id);
}
