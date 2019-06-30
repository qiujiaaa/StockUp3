package com.example.stockup;

public class Item {

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

    protected Item (Item item) {
        this.name = item.name;
        this.brand = item.brand;
        this.price = item.price;
        this.addInfo = item.addInfo;
    }

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
