/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.hotel;

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

    public void initializeServeTime() {
        serveTime = AppData.MEALS_TIME.get(AppData.MEALS_TYPES.indexOf(serveType));
    }

    private boolean isEnoughInfor() {
        return checkNotNull(name, type, details, quantity, note, serveType) && price > 0 && checkNaturalNumber(quantity);
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

    public String getAbleToEdit() {
        if (!isEnoughInfor()) {
            return AppData.INFOR_NOT_ENOUGH;
        } else if (isInvalidType()) {
            return AppData.WRONG_TYPE_SERVICE;
        } else if (isInvalidServeType()) {
            return AppData.INVALID_SERVICE_TYPE;
        }
        return AppData.ABLE_TO_EDIT;
    }

    public HotelService() {
    }

    public HotelService(String name, String type, double price, String img, String img2, String details, String quantity, String note, String serveType, String serveTime) {
        super.setInfor(name, type, price, img, img2, details);
        this.quantity = quantity;
        this.note = note;
        this.serveType = serveType;
        this.serveTime = serveTime;
    }

    @Override
    public String toString() {
        return "HotelService{" + "quantity=" + quantity + ", note=" + note + ", serveType=" + serveType + ", serveTime=" + serveTime + '}';
    }
}
