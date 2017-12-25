/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.user.tracking;

/**
 *
 * @author HUNGCUONG
 */
public class DataCollection {
    
    private String date;
    private String data;

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DataCollection(String Date, String data) {
        this.date = Date;
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataCollection{" + "date=" + date + ", data=" + data + '}';
    }
}
