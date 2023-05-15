package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class MessagerProductAll implements Serializable {
    private int page;
    private boolean error;
    private List<ProductModel> products;

    public MessagerProductAll(int page, boolean error, List<ProductModel> products) {
        this.page = page;
        this.error = error;
        this.products = products;
    }

    public MessagerProductAll() {
    }

    public int getPage() {
        return page;
    }

    public boolean isError() {
        return error;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
