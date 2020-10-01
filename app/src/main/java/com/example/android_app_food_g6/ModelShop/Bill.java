package com.example.android_app_food_g6.ModelShop;

public class Bill {
    private String userid;
    String time;
    String addres;
    String price;
    String payment;

    public Bill() {
    }

    public Bill(String userid,String time, String addres, String price, String payment) {
        this.setUserid(userid);
        this.time = time;
        this.addres = addres;
        this.price = price;
        this.payment = payment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
