package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class listDoanhThu implements Serializable {
    private List<DoanhThu> thongke;

    public listDoanhThu(List<DoanhThu> doanhthu) {
        this.thongke = doanhthu;
    }

    public listDoanhThu() {
    }

    public List<DoanhThu> getDoanhthu() {
        return thongke;
    }

    public void setDoanhthu(List<DoanhThu> doanhthu) {
        this.thongke = doanhthu;
    }
}
