package com.example.appfood.model;

public class MuaHuyModel {
    private String id;
    private String name;
    private String email;
    private String sl;

    public MuaHuyModel(String id, String name, String email, String sl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sl = sl;
    }

    public MuaHuyModel() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSl() {
        return sl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }


}
