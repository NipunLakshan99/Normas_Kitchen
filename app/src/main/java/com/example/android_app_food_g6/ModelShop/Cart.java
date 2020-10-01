package com.example.android_app_food_g6.ModelShop;

public class Cart {
    String foodId,cusId,foodname,price;
    int qty;

    public Cart() {
    }

    public Cart(String foodId, String cusId, String foodname, String price, int qty) {
        this.foodId = foodId;
        this.cusId = cusId;
        this.foodname = foodname;
        this.price = price;
        this.qty = qty;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
