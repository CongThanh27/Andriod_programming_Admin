package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class ListMuaHuy implements Serializable {
    private List<MuaHuyModel> thongke;

    public ListMuaHuy(List<MuaHuyModel> thongke) {
        this.thongke = thongke;
    }

    public ListMuaHuy() {
    }

    public List<MuaHuyModel> getThongke() {
        return thongke;
    }

    public void setThongke(List<MuaHuyModel> thongke) {
        this.thongke = thongke;
    }
}
