package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class category implements Serializable {
   private String category;

    public category(String category) {

        this.category = category;
    }

    public category() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
