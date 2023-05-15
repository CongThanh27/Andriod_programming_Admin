package com.example.appfood.model;

public class ProductOrderModel {
    private String order_number;
    private String order_date;
    private String status;
    private int quantity;
    private String product_name;
    private double price;
    private int id;
    private String image;

    public ProductOrderModel(String order_number, String order_date, String status, int quantity, String product_name, double price, int id, String image) {
        this.order_number = order_number;
        this.order_date = order_date;
        this.status = status;
        this.quantity = quantity;
        this.product_name = product_name;
        this.price = price;
        this.id = id;
        this.image = image;
    }

    public ProductOrderModel() {
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

    public int getQuantity() {
        return quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
