package com.example.android_app_food_g6.Model;

public class Orders {
    String ordertime;
    String orderid;
    String paymenttype;
    String username;
    String address;
    String price;
    String status;

    public Orders() {
    }

    public Orders(String ordertime, String orderid, String paymenttype, String username, String address, String price, String status) {
        this.ordertime = ordertime;
        this.orderid = orderid;
        this.paymenttype = paymenttype;
        this.username = username;
        this.address = address;
        this.price = price;
        this.status = status;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderdate) {
        this.orderid = orderdate;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
