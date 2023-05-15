package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class ListProductOrrder implements Serializable {
    private List<ProductOrderModel> ListProductOrrder;

    public ListProductOrrder(List<ProductOrderModel> list) {
        this.ListProductOrrder = list;
    }

    public ListProductOrrder() {
    }

    public List<ProductOrderModel> getList() {
        return ListProductOrrder;
    }

    public void setList(List<ProductOrderModel> list) {
        this.ListProductOrrder = list;
    }
}
