package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable {
    private List<ProductModel> products;

    public Products(List<ProductModel> products) {
        this.products = products;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public Products() {
    }
}
