/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.hotel;

import model.AbstractModel;

/**
 *
 * @author Do Hung Cuong
 */
public abstract class HotelItem extends AbstractModel {

    protected double price;
    protected String img;
    protected String img2;
    protected String details;
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public void defaultImage() {
        //String imgname = name.toLowerCase().r
        if (img == null || img.equals("")) {
            img = name.toLowerCase() + ".jpg";
        }
        if (img2 == null || img2.equals("")) {
            img2 = name.toLowerCase() + "_2.jpg";
        }
    }

    protected void setInfor(String name, String type, double price, String img, String img2, String details) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.img2 = img2;
        this.type = type;
        this.details = details;
    }
    
    public abstract void initializeSomeInfor();
}
