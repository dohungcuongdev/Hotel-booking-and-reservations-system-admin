/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.sql.hotel;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import statics.constant.AppData;

/**
 *
 * @author Do Hung Cuong
 */

@Entity(name = "restaurant")
public class HotelService extends HotelItem {

	@Column(name = "quantity", nullable = false)
    private int quantity;
	
	@Column(name = "note", nullable = false)
    private String note;
	
	@Column(name = "serveType", nullable = false)
    private String serveType;
	
	@Column(name = "serveTime", nullable = false)
    private String serveTime;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getServeType() {
        return serveType;
    }

    public void setServeType(String serveType) {
        this.serveType = serveType;
    }

    public String getServeTime() {
        return serveTime;
    }
    
    @Override
	public void setNewInfor() {
    	initializeServeTime();
		this.img = AppData.RESTAURANT_DEFAULT_IMG[0];
		this.img2 = AppData.RESTAURANT_DEFAULT_IMG[1];
    	super.setCreated();
	}

    public void initializeServeTime() {
        serveTime = AppData.MEALS_TIME.get(AppData.MEALS_TYPES.indexOf(serveType));
    }

    private boolean isEnoughInfor() {
        return checkNotNull(name, type, details, quantity, note, serveType);
    }

	private boolean isNumberFormat() {
		return checkNaturalNumber(quantity, price);
	}

    @Override
    public void initializeSomeInfor() {
        initializeServeTime();
    }

    private boolean isInvalidType() {
        return !AppData.SERVICE_TYPES.contains(type);
    }

    private boolean isInvalidServeType() {
        return !AppData.MEALS_TYPES.contains(serveType);
    }
    
    @Override
    public String isAbleToUpdate() {
        return !isEnoughInfor() ?  AppData.INFOR_NOT_ENOUGH : !isNumberFormat() ? AppData.WRONG_NUMBER_FORMAT_SERVICE : isInvalidType() ? AppData.WRONG_TYPE_SERVICE : isInvalidServeType() ? AppData.INVALID_SERVICE_TYPE: AppData.ABLE_TO_EDIT;
    }
    
    

	public HotelService() {
	}

	public HotelService(int id, String name, int price, String img, String img2, String details, String type, String created_by, String created_at, int quantity, String note, String serveType, String serveTime) {
		this(name, price, img, img2, details, type, created_by, created_at, quantity, note, serveType, serveTime);
		this.id = id;
	}

	public HotelService(String name, int price, String img, String img2, String details, String type, String created_by, String created_at, int quantity, String note, String serveType, String serveTime) {
		super(name, price, img, img2, details, type, created_by, created_at);
		this.quantity = quantity;
		this.note = note;
		this.serveType = serveType;
		this.serveTime = serveTime;
	}

	public HotelService(int quantity, String note, String serveType, String serveTime) {
		super();
		this.quantity = quantity;
		this.note = note;
		this.serveType = serveType;
		this.serveTime = serveTime;
	}

	@Override
	public String toString() {
		return "HotelService [ id=" + id
				+ ", name=" + name + ", quantity=" +  quantity + ", note=" + note + ", serveType=" + serveType + ", serveTime="
				+ serveTime + ", price=" + price + ", img=" + img + ", img2=" + img2 + ", details=" + details
				+ ", type=" + type + ", created_by=" + created_by + ", created_at=" + created_at + "]";
	}

	//use for old DAOs: mongodb DAOs
	@Override
    public DBObject toDBObject() {
    	return BasicDBObjectBuilder.start("id", id).append("name", name).append("type", type).append("price", price).append("quantity", quantity).append("note", note).append("details", details).append("img", img).append("img2", img2).append("serveType", serveType).append("created_by", created_by).append("created_at", created_at).get();
    }
}