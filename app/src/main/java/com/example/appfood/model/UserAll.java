package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class UserAll implements Serializable {
    private boolean error;
    private List<UserModel> users;

    public UserAll(boolean error, List<UserModel> users) {
        this.error = error;
        this.users = users;
    }

    public UserAll() {
    }

    public boolean isError() {
        return error;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
