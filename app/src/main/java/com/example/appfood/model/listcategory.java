package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class listcategory implements Serializable {
    private List<category> listCategory;

    public listcategory(List<category> listCategory) {
        this.listCategory = listCategory;
    }

    public listcategory() {
    }

    public List<category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<category> listCategory) {
        this.listCategory = listCategory;
    }


}
