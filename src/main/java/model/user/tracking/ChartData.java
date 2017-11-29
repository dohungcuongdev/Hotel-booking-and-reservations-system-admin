/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user.tracking;

/**
 *
 * @author HUNGCUONG
 */
public class ChartData {
    
    private String data;
    private int quantity;
    private double percent;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public ChartData(String data, int quantity, double percent) {
        this.data = data;
        this.quantity = quantity;
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "ChartData{" + "data=" + data + ", quantity=" + quantity + ", percent=" + percent + '}';
    }
}
