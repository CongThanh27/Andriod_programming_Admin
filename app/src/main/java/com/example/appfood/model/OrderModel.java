package com.example.appfood.model;

public class OrderModel {
    private String order_number;
    private String order_date;
    private String status;
    private String name;
    private String address;
    private String phone;
    private int price;
    private String product_names;

    public OrderModel(String order_number, String order_date, String status, String name, String address, String phone, int price, String product_names) {
        this.order_number = order_number;
        this.order_date = order_date;
        this.status = status;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.price = price;
        this.product_names = product_names;
    }

    public OrderModel() {
    }

    public String getOrder_number() {
        return order_number;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getPrice() {
        return price;
    }

    public String getProduct_names() {
        return product_names;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProduct_names(String product_names) {
        this.product_names = product_names;
    }
}
