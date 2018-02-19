/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mongodb.hotel;

import com.mongodb.DBObject;
import model.mongodb.MongoDbAbstractModel;
import statics.constant.AppData;
import statics.helper.DateTimeCalculator;

/**
 *
 * @author Do Hung Cuong
 */

public abstract class HotelItem extends MongoDbAbstractModel {

    protected int price;
    protected String img;
    protected String img2;
    protected String details;
    protected String type;
    protected String created_by;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	protected void setInfor(String name, String type, int price, String img, String img2, String details) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.img2 = img2;
        this.type = type;
        this.details = details;
    }
	
	public void setCreated() {
		this.created_by = AppData.admin.getUsername();
		this.created_at = DateTimeCalculator.getTimeToday();
	}
    
    public abstract void initializeSomeInfor();
    
    public abstract String getAbleToUpdate();
    
    public abstract DBObject toDBObject();
    
	public abstract void setNewInfor();
}
