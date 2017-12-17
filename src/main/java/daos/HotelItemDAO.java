/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;

import model.hotel.HotelItem;

/**
 *
 * @author HUNGCUONG
 */
public interface HotelItemDAO {

	public <T> T getHotelItemByID(String id, Class<T> classOfT);
	public <T> T getHotelItemByName(String name, Class<T> classOfT);
	public <T> List<T> getAllHotelItems(Class<T> classOfT);
	public <T> List<T> getRelatedHotelItems(String type, Class<T> classOfT);
	public String findAndAddNewItem(HotelItem newItem);
	public void editImage(String id, String img, String img2);
	public void deleteItem(String id);
	public void updateItem(HotelItem item);
}
