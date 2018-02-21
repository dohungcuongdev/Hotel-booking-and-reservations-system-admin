/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb.daoimpls;

import model.sql.user.Administrator;

import java.net.UnknownHostException;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import daos.AdminDAO;

/**
 *
 * @author Do Hung Cuong
 */

@Repository
public class AdminDAOImpl extends JsonParserDAO implements AdminDAO {
	
	private DBCollection collection;    
	
	public AdminDAOImpl() throws UnknownHostException {
		collection = MongoDbConnector.createConnection("admin");
	}

    @Override
    public Administrator getAdminByUserName(String username) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("username", username);
        DBObject obj = collection.findOne(whereQuery);
        return fromJson(obj, Administrator.class);
    }

    @Override
    public void updateAdmin(Administrator admin) {
        DBObject document = parseJSON(toJson(admin));
        DBObject searchObject = new BasicDBObject();
        searchObject.put("username", admin.getUsername());
        collection.update(searchObject, document);
    }

    @Override
    public void updatePassword(String username, String newpassword) {
    	BasicDBObject document = new BasicDBObject();
        document.append("$set", new BasicDBObject().append("password", newpassword));
        BasicDBObject searchQuery = new BasicDBObject().append("username", username);
        collection.update(searchQuery, document);
    }

    @Override
    public void editProfileImg(String username, String img) {
    	BasicDBObject document = new BasicDBObject();
        document.append("$set", new BasicDBObject().append("img", img));
        BasicDBObject searchQuery = new BasicDBObject().append("username", username);
        collection.update(searchQuery, document);
    }
	
    @Override
    public boolean isExists (String username) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("username", username);
        DBObject obj = collection.findOne(whereQuery);
        return obj != null;
	}

}
