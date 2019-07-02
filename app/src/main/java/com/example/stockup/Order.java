package com.example.stockup;

class Order {

    String number;
    String date;
    String status;
    double price;

    public Order() {}

    public Order(String number, String date, String status, double price) {
        this.number = number;
        this.date = date;
        this.status = status;
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public void updateStatus() {
        status = "Completed";
    }
}
