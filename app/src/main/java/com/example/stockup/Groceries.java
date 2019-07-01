package com.example.stockup;

import java.io.Serializable;

public class Groceries implements Serializable {

    Item item;
    int quantity;
    String usagePeriod;

    public Groceries() {}

    public Groceries(Item item, int quantity, String usagePeriod) {
        this.item = item;
        this.quantity = quantity;
        this.usagePeriod = usagePeriod;
    }

    protected Item getItem() {
        return item;
    }

    protected int getQuantity() {
        return quantity;
    }

    public String getUsagePeriod() { return usagePeriod; }

    public String getName() {
        return item.getName();
    }

    public String getBrand() {
        return item.getBrand();
    }

    public double getPrice() {
        return item.getPrice();
    }

    public String getAddInfo() {
        return item.getAddInfo();
    }

    protected void increaseQuantity() {
        quantity++;
    }

    protected void decreaseQuantity() {
        quantity--;
    }
}
