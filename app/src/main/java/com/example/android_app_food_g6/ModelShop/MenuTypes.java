package com.example.android_app_food_g6.ModelShop;

public class MenuTypes {
    String name,Image;

    public MenuTypes(String name, String image) {
        this.name = name;
        Image = image;
    }

    public MenuTypes() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
