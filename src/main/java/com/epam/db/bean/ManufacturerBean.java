package com.epam.db.bean;

public class ManufacturerBean {

    private int id;
    private String country;

    public ManufacturerBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String name) {
        this.country = name;
    }
}
