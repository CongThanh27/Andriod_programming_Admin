package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class listOrder implements Serializable {
    private List<OrderModel> orders;

    public listOrder(List<OrderModel> orders) {
        this.orders = orders;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}
