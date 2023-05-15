package com.example.appfood.model;

import java.io.Serializable;

public class DoanhThu implements Serializable {
    private String kq;
    private String price;

    public DoanhThu(String kq, String price) {
        this.kq = kq;
        this.price = price;
    }

    public DoanhThu() {
    }

    public String getKq() {
        return kq;
    }

    public String getPrice() {
        return price;
    }

    public void setKq(String kq) {
        this.kq = kq;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
