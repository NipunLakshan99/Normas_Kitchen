package com.example.android_app_food_g6.ModelDelivery;

public class Delivery {
    String id;
    String fullname;
    String username;
    String nic;
    String imageurl;
    String mobil;
    String vehicleno;

    public Delivery() {
    }

    public Delivery(String id, String fullname, String username, String nic, String imageurl, String mobil, String vehicleno) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.nic = nic;
        this.imageurl = imageurl;
        this.mobil = mobil;
        this.vehicleno = vehicleno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
}
