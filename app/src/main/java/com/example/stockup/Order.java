package com.example.stockup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Order implements Serializable {

    int number;
    String date;
    String status;
    String price;
    String address;
    List<Groceries> list;
    String buyer;
    String runner;
    boolean collected;

    public Order() {}

    public Order(int number, String date, String status, String price, String address, List<Groceries> list, String buyer) {
        this.number = number;
        this.date = date;
        this.status = status;
        this.price = price;
        this.address = address;
        this.list = list;
        this.buyer = buyer;
        runner = "";
        collected = false;
    }

    public int getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getPrice() {
        return price;
    }

    public String getAddress() { return address; }

    public List<Groceries> getList() { return list; }

    public String getBuyer() { return buyer; }

    public String getRunner() { return runner; }

    public boolean isCollected() { return collected; }

    public void setCollected() { collected = true; }

    public void setRunner(String runner) { this.runner = runner; }

    public void setStatus(String newStatus) {
        status = newStatus;
    }
}
