package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.sql.hotel.HotelRoom;

public class TestHibernate {

	public static void main(String[] args) {
	    Configuration cfg=new Configuration();  
	    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
	      
	    //creating seession factory object  
	    SessionFactory factory=cfg.buildSessionFactory();  
	      
	    //creating session object  
	    Session session=factory.openSession();  
	      
	    //creating transaction object  
	    Transaction t=session.beginTransaction();  
	          
	    HotelRoom room = (HotelRoom) session.get(HotelRoom.class, 1);
	    System.out.println(room);  
	      

	    //session.persist(room); // update room
	    //System.out.println("successfully saved");  
	    
	    

	      
	    t.commit();//transaction is commited
	    
	    session.close();  

	}

}
