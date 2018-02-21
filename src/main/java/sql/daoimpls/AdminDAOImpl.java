/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.daoimpls;

import model.sql.user.Administrator;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.AdminDAO;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
@Transactional
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

    @Override
    public Administrator getAdminByUserName(String username) {
		Query q = sessionFactory.getCurrentSession().createQuery("from admin where username = :username"); 
		q.setParameter("username", username);
		return (Administrator) q.uniqueResult();
    }

    @Override
    public void updateAdmin(Administrator admin) {
//		if(!isExists(admin.getUsername()))
			sessionFactory.getCurrentSession().saveOrUpdate(admin);
    }

    @Override
    public void updatePassword(String username, String newpassword) {
		Query q = sessionFactory.getCurrentSession()
				.createQuery("update " + Administrator.class.getName() + " set password = :password where username = :username");
		q.setParameter("username", username);
		q.setParameter("password", newpassword);
		q.executeUpdate();
    }

    @Override
    public void editProfileImg(String username, String img) {
		Query q = sessionFactory.getCurrentSession().createQuery("update " + Administrator.class.getName() + " set img = :img where username = :username");
		q.setParameter("username", username);
		q.setParameter("img", img);
		q.executeUpdate();
    }
	
    @Override
    public boolean isExists (String username) {
	    Query query = sessionFactory.getCurrentSession().createQuery("from " + Administrator.class.getName() + " where username = :username");
	    query.setParameter("username", username);
	    return query.uniqueResult() != null;
	}

}
