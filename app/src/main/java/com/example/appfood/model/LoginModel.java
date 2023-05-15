package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class LoginModel implements Serializable {
    private int id;
    private String name;
    private String email;
    private boolean isAdmin;
    private String address;
    private String gender;
    private int age;
    private String phone_number;
    private boolean error;
    private String message;

    public LoginModel(int id, String name, String email, boolean isAdmin, String address, String gender, int age, String phone_number, boolean error, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.phone_number = phone_number;
        this.error = error;
        this.message = message;
    }

    public LoginModel() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
