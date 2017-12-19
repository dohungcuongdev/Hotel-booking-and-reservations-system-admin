package daos;

import java.util.List;

import model.hotel.HotelItem;

public abstract class HotelItemDAO {

	protected abstract <T> T getHotelItemByID(String id, Class<T> classOfT);
	protected abstract <T> T getHotelItemByName(String name, Class<T> classOfT);
	protected abstract <T> List<T> getAllHotelItems(Class<T> classOfT);
	protected abstract <T> List<T> getRelatedHotelItems(String type, Class<T> classOfT);
	protected abstract String findAndAddNewItem(HotelItem newItem);
	protected abstract void editImage(String name, String img, String img2);
	protected abstract void deleteItem(String name);
	protected abstract void updateItem(HotelItem item);
}
