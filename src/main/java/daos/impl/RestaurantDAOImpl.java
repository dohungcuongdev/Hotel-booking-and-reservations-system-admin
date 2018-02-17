/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.RestaurantDAO;
import model.sql.hotel.HotelService;
import statics.constant.AppData;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
@Transactional
public class RestaurantDAOImpl extends HotelItemDAOImpl<HotelService> implements RestaurantDAO {

	public RestaurantDAOImpl() {
		classOfT = HotelService.class;
	}

	@Override
	public HotelService getHotelServiceByID(int id) {
		return getHotelServiceByIDNoDB(id);
		//return (HotelService) getHotelItemByID(id);
	}

	@Override
	public HotelService getHotelServiceByName(String name) {
		return getHotelServiceByNameNoDB(name);
		// return (HotelService) getHotelItemByName(name);
	}

	@Override
	public List<HotelService> getAllHotelServices() {
		return getAllHotelItems();
	}

	@Override
	public List<HotelService> getRelatedHotelServices(String type) {
		return getRelatedHotelServicesNoDB(type);
		//return getRelatedHotelItems(type);
	}

	@Override
	public String findAndAddNewService(HotelService newService) {
		return findAndAddNewItem(newService);
	}

	@Override
	public void updateService(HotelService service) {
		updateItem(service);
	}
	
    private HotelService getHotelServiceByIDNoDB(int id) {
    	for(HotelService item: AppData.listservices)
    		if(item.getId() == id)
    			return item;
    	return null;
    }

	private HotelService getHotelServiceByNameNoDB(String name) {
		for (HotelService item : AppData.listservices)
			if (item.getName().equals(name))
				return item;
		return null;
	}

	private List<HotelService> getRelatedHotelServicesNoDB(String type) {
		List<HotelService> listRelatedServices = new ArrayList<>();
		for (HotelService item : AppData.listservices)
			if (item.getType().equals(type))
				listRelatedServices.add(item);
		return listRelatedServices;
	}
}
