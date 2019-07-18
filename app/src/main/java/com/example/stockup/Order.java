package com.example.stockup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Order implements Serializable {

    static int count = 1;

    int number;
    String date;
    String status;
    String price;
    String address;
    List<Groceries> list;

    public Order() {}

    public Order(int number, String date, String status, String price, String address, List<Groceries> list) {
        this.number = number;
        this.date = date;
        this.status = status;
        this.price = price;
        this.address = address;
        this.list = list;
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

    public static void increaseCount() { count++; }

    public void setStatus(String newStatus) {
        status = newStatus;
    }
}
