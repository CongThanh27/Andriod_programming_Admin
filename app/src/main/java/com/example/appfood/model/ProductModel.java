package com.example.appfood.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductModel implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("product_name")
    private String product_name;
    @SerializedName("price")
    private double price;
    @SerializedName("priceold")
    private double priceold;
    @SerializedName("quantity")
    private int quantity;

    @SerializedName("sold")
    private int sold;
    @SerializedName("supplier")
    private String supplier;
    @SerializedName("category")
    private String category;
    @SerializedName("image")
    private String image;
    @SerializedName("description")
    private String description;
    @SerializedName("trademark")
    private String trademark;
    @SerializedName("origin")
    private String origin;
    @SerializedName("sex")
    private String sex;
    @SerializedName("skinproblems")
    private String skinproblems;

    @SerializedName("addtocart")
    private int addtocart;

    @SerializedName("addtofavorite")
    private int addtofavorite;

    @SerializedName("share")
    private int share;
    @SerializedName("rain")
    private int rain;
    @SerializedName("active")
    private int active;

    public ProductModel(int id, String product_name, double price, double priceold, int quantity, int sold, String supplier, String category, String image, String describe, String trademark, String origin, String sex, String skinproblems, int addtocart, int addtofavorite, int share, int rain, int active) {
        this.id = id;
        this.product_name = product_name;
        this.price = price;
        this.priceold = priceold;
        this.quantity = quantity;
        this.sold = sold;
        this.supplier = supplier;
        this.category = category;
        this.image = image;
        this.description = describe;
        this.trademark = trademark;
        this.origin = origin;
        this.sex = sex;
        this.skinproblems = skinproblems;
        this.addtocart = addtocart;
        this.addtofavorite = addtofavorite;
        this.share = share;
        this.rain = rain;
        this.active = active;
    }

    public ProductModel() {
    }

    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceold() {
        return priceold;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSold() {
        return sold;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getTrademark() {
        return trademark;
    }

    public String getOrigin() {
        return origin;
    }

    public String getSex() {
        return sex;
    }

    public String getSkinproblems() {
        return skinproblems;
    }

    public int getAddtocart() {
        return addtocart;
    }

    public int getAddtofavorite() {
        return addtofavorite;
    }

    public int getShare() {
        return share;
    }

    public int getRain() {
        return rain;
    }

    public int getActive() {
        return active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceold(double priceold) {
        this.priceold = priceold;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescribe(String describe) {
        this.description = describe;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSkinproblems(String skinproblems) {
        this.skinproblems = skinproblems;
    }

    public void setAddtocart(int addtocart) {
        this.addtocart = addtocart;
    }

    public void setAddtofavorite(int addtofavorite) {
        this.addtofavorite = addtofavorite;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
