package daos.mongodb;

import java.util.List;

import model.mongodb.hotel.HotelItem;

public interface HotelItemDAO<T> {

	public T getHotelItemByID(String id);
	public T getHotelItemByName(String name);
	public List<T> getAllHotelItems();
	public List<T> getRelatedHotelItems(String type);
	public String findAndAddNewItem(HotelItem newItem);
	public void editImage(String name, String img, String img2);
	public void deleteItem(String id);
	public void updateItem(HotelItem item);
}
