package com.example.stockup;

import java.io.Serializable;

public class Item implements Serializable {

    String name;
    String brand;
    double price;
    String addInfo;

    protected Item (String name, String brand, double price, String addInfo) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.addInfo = addInfo;
    }

    protected Item () { }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public String getAddInfo() {
        return addInfo;
    }
}
