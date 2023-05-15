package com.example.appfood.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {

    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private int age;
    private String image;
    private int isAdmin;
    private String address;
    private String phoneNumber;
       // private boolean error;
      //  private String message;

        // Các phương thức getter và setter




    public UserModel() {
    }

    public UserModel(int id, String name, String email, String password, String gender, int age, String image, int isAdmin, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.image = image;
        this.isAdmin = isAdmin;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }

    public int isAdmin() {
        return isAdmin;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAdmin(int admin) {
        isAdmin = admin;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
