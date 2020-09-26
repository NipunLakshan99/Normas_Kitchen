package com.example.android_app_food_g6.ModelDelivery;

public class Delivery {
    String id;
    String fullname;
    String username;
    String nic;
    String mobile;
    String vehicleno;

    public Delivery() {
    }

    public Delivery(String id, String fullname, String username, String nic, String mobile, String vehicleno) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.nic = nic;
        this.mobile = mobile;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
}
