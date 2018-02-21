/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.daoimpls;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.HotelItemDAO;
import model.sql.hotel.HotelItem;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
@Transactional
public abstract class HotelItemDAOImpl<T> implements HotelItemDAO<T> {
    
    protected Class<T> classOfT;

	@Autowired
	private SessionFactory sessionFactory;
    
    @Override
    public T getHotelItemByID(int id) {
    	return (T) sessionFactory.getCurrentSession().get(classOfT, id);
    }
    
    @Override
    public T getHotelItemByName(String name) {
		Query q = sessionFactory.getCurrentSession().createQuery("from " + classOfT.getName() + " where name = :name"); 
		q.setParameter("name", name);
		return (T) q.uniqueResult();
    }
    
    @Override
    public List<T> getAllHotelItems() {
    	return sessionFactory.getCurrentSession().createQuery("from " + classOfT.getName()).list(); 
    }
    
    @Override
    public List<T> getRelatedHotelItems(String type) {
		Query q = sessionFactory.getCurrentSession().createQuery("from "  + classOfT.getName() + " where type = :type"); 
		q.setParameter("type", type);
		return q.list();
    }
    
    @Override
    public String findAndAddNewItem(HotelItem newItem) {
		if(!isExists(newItem)) {
			sessionFactory.getCurrentSession().save(newItem);
			return newItem.getName();
		}
		return null;
    }

    @Override
    public void editImage(String name, String img, String img2) {
    	if(!img.equals("") || !img2.equals("")) {
    		if(!img.equals(""))
    			updateImage(name,"img", img);
    		if(!img2.equals(""))
    			updateImage(name,"img2", img2);
    	}
    }
    
    private void updateImage(String name, String imgdb, String imgFile) {
    	Query q = sessionFactory.getCurrentSession().createQuery("update " + classOfT.getName() + " set " + imgdb + " = :" + imgdb + " where name = :name");
		q.setParameter("name", name);
		q.setParameter(imgdb, imgFile);
		q.executeUpdate();
    }
    
    @Override
    public void deleteItem(int id) {
		Query q = sessionFactory.getCurrentSession().createQuery("delete " + classOfT.getName() +" where id = :id");
		q.setParameter("id", id);
		q.executeUpdate();
    }
    
    @Override
    public void updateItem(HotelItem item) {
		sessionFactory.getCurrentSession().saveOrUpdate(item);
    }	
    
    private boolean isExists (HotelItem item) {
	    Query query = sessionFactory.getCurrentSession().createQuery("from " + classOfT.getName() + " where name = :name");
	    query.setParameter("name", item.getName());
	    return query.uniqueResult() != null;
	}
}
