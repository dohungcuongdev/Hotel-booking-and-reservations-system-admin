/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.daoimpls;

import statics.constant.AppData;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.RestaurantDAO;
import model.sql.hotel.HotelService;

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
		//return getHotelServiceByIDNoDB(id);
		return (HotelService) getHotelItemByID(id);
	}

	@Override
	public HotelService getHotelServiceByName(String name) {
		//return getHotelServiceByNameNoDB(name);
		return (HotelService) getHotelItemByName(name);
	}

	@Override
	public List<HotelService> getAllHotelServices() {
		return getAllHotelItems();
	}

	@Override
	public List<HotelService> getRelatedHotelServices(String type) {
		//return getRelatedHotelServicesNoDB(type);
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
	
    private HotelService getHotelServiceByIDNoDB(int id) {
    	return AppData.listservices.stream().filter((item) -> (item.getId() == id)).findFirst().get();
    }
	
    private HotelService getHotelServiceByNameNoDB(String name) {
    	return AppData.listservices.stream().filter((item) -> (item.getName().equals(name))).findFirst().get();
    }
    
    private List<HotelService> getRelatedHotelServicesNoDB(String type) {
    	return AppData.listservices.stream().filter((item) -> (item.getType().equals(type))).collect(Collectors.toList());
    }
}
