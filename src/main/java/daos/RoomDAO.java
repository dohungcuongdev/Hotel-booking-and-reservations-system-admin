/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;
import model.hotel.HotelRoom;

/**
 *
 * @author Do Hung Cuong
 */
public interface RoomDAO {

    public HotelRoom getRoomByID(String id);
    public List<HotelRoom> getAllRooms();
    public List<HotelRoom> getRelatedHotelRooms(String type);
    public void updateRoom(HotelRoom room);
	public void editImage(String name, String img, String img2);
	public void deleteItem(String id);
}
