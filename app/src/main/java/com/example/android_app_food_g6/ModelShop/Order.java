package com.example.android_app_food_g6.ModelShop;

public class Order {
    int id;
    String cusName,address,paymentType,time;

    public Order() {
    }

    public Order(int id, String cusName, String address, String paymentType, String time) {
        this.id = id;
        this.cusName = cusName;
        this.address = address;
        this.paymentType = paymentType;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
