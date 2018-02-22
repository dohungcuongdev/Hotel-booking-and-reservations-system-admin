/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.sql.hotel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

import com.mongodb.DBObject;

import model.sql.SQLAbstractModel;
import statics.constant.AppData;
import statics.helper.DateTimeCalculator;

/**
 *
 * @author Do Hung Cuong
 */

@MappedSuperclass
public abstract class HotelItem extends SQLAbstractModel {

	@Column(name = "price", nullable = false)
    protected int price;
	
	@Column(name = "img", nullable = false)
    protected String img;
	
	@Column(name = "img2", nullable = false)
    protected String img2;
	
	@Column(name = "details", nullable = false)
	@Type(type="text")
    protected String details;
	
	@Column(name = "type", nullable = false)
    protected String type;
	
	@Column(name = "created_by", nullable = false)
    protected String created_by;

	@Column(name = "created_at", nullable = false)
	protected String created_at;

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

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
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
	
	public HotelItem() {}
	
    public HotelItem(String name, int price, String img, String img2, String details, String type, String created_by, String created_at) {
		this.name = name;
		this.price = price;
		this.img = img;
		this.img2 = img2;
		this.details = details;
		this.type = type;
		this.created_by = created_by;
		this.created_at = created_at;
	}
	
    public HotelItem(int id, String name, int price, String img, String img2, String details, String type, String created_by, String created_at) {
		this(name, price, img, img2, details, type, created_by, created_at);
		this.id = id;
	}

	public abstract void initializeSomeInfor();

	public abstract String isAbleToUpdate();
    
	public abstract void setNewInfor();

	@Override
	public String toString() {
		return "HotelItem [price=" + price + ", img=" + img + ", img2=" + img2 + ", details=" + details + ", type=" + type + ", created_by=" + created_by + ", created_at=" + created_at + ", id=" + id + ", name=" + name + "]";
	}
	 
	//use for old DAOs: mongodb DAOs
    public abstract DBObject toDBObject();
}