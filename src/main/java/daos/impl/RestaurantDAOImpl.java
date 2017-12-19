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
public class RestaurantDAOImpl extends HotelItemDAOImpl<HotelService> implements RestaurantDAO {

    public RestaurantDAOImpl() throws UnknownHostException {
    	classOfT = HotelService.class;
        collection = MongoDBConnector.createConnection("restaurant");
    }

    @Override
    public HotelService getHotelServiceByID(String id) {
    	return (HotelService) getHotelItemByID(id);
    }
    
    @Override
    public HotelService getHotelServiceByName(String name) {
    	return (HotelService) getHotelItemByName(name);
    }

	@Override
	public List<HotelService> getAllHotelServices() {
		 return getAllHotelItems();
	}

    @Override
    public List<HotelService> getRelatedHotelServices(String type) {
    	return getRelatedHotelItems(type);
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
