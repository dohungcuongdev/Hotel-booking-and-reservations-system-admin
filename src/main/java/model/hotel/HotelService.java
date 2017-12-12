/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.hotel;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import statics.AppData;

/**
 *
 * @author Do Hung Cuong
 */
public class HotelService extends HotelItem {

    private String quantity;
    private String note;
    private String serveType;
    private String serveTime;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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
    	this.created_by = AppData.admin.getUsername();
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
    public String getAbleToUpdate() {
        return !isEnoughInfor() ?  AppData.INFOR_NOT_ENOUGH : !isNumberFormat() ? AppData.WRONG_NUMBER_FORMAT_SERVICE : isInvalidType() ? AppData.WRONG_TYPE_SERVICE : isInvalidServeType() ? AppData.INVALID_SERVICE_TYPE: AppData.ABLE_TO_EDIT;
    }

    @Override
    public String toString() {
        return "HotelService{" + "quantity=" + quantity + ", note=" + note + ", serveType=" + serveType + ", serveTime=" + serveTime + '}';
    }
	
    @Override
    public DBObject toDBObject() {
    	return BasicDBObjectBuilder.start("name", name).append("type", type).append("price", price).append("quantity", quantity).append("note", note).append("details", details).append("img", img).append("img2", img2).append("serveType", serveType).get();
    }
}
