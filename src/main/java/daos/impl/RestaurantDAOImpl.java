/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.RestaurantDAO;
import database.MongoDBConnector;
import java.net.UnknownHostException;
import java.util.List;
import org.springframework.stereotype.Repository;
import model.hotel.HotelService;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class RestaurantDAOImpl extends HotelItemDAOImp implements RestaurantDAO {

    public RestaurantDAOImpl() throws UnknownHostException {
        collection = MongoDBConnector.createConnection("restaurant");
    }

    @Override
    public HotelService getHotelServiceByID(String id) {
    	return (HotelService) getHotelItemByID(id, HotelService.class);
    }
    
    @Override
    public HotelService getHotelServiceByName(String name) {
    	return (HotelService) getHotelItemByName(name, HotelService.class);
    }

	@Override
	public List<HotelService> getAllHotelServices() {
		 return getAllHotelItems(HotelService.class);
	}

    @Override
    public List<HotelService> getRelatedHotelServices(String type) {
    	return getRelatedHotelItems(type, HotelService.class);
    }
    
    @Override
    public String findAndAddNewService(HotelService newService) {
    	return findAndAddNewItem(newService);
    }

	@Override
	public void updateService(HotelService service) {
		updateItem(service);
	}
}
