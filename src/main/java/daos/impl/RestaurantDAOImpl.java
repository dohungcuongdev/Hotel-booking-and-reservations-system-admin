/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.RestaurantDAO;
import model.mysql.hotel.HotelService;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
@Transactional
public class RestaurantDAOImpl extends HotelItemDAOImpl<HotelService> implements RestaurantDAO {	
	
	@Autowired
	private SessionFactory sessionFactory;
	
    public RestaurantDAOImpl() {
    	classOfT = HotelService.class;
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
