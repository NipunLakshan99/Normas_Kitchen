package com.example.android_app_food_g6.ModelShop;

public class Customer {
    private String id;
    String name;
    String password;
    String type;
    String address;

    public Customer() {
    }

    public Customer(String id,String name, String password, String type, String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
