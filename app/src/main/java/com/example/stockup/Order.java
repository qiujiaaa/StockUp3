package com.example.stockup;

class Order {

    String number;
    String date;
    String status;
    double price;
    String address;

    public Order() {}

    public Order(String number, String date, String status, double price, String address) {
        this.number = number;
        this.date = date;
        this.status = status;
        this.price = price;
        this.address = address;
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

    public String getAddress() { return address; }

    public void updateStatus() {
        status = "Completed";
    }
}
