package com.example.android_app_food_g6.ModelShop;

public class Foods {
    String type,name,image;
    String price;
    String id;

    public Foods() {
    }

    public Foods(String type, String name, String image, String price, String id) {
        this.type = type;
        this.name = name;
        this.image = image;
        this.price = price;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
