/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

/**
 *
 * @author HUNGCUONG
 */
public interface HotelItemDAO {
    
    public void editImage(String name, String img, String img2);
    
    public void deleteItem(String id);
}
