package com.example.stockup;

import java.util.ArrayList;
import java.util.List;

public class User {

    String username;
    String email;
    String role;
    List<Groceries> myShoppingList;
    List<Groceries> myGroceries;
    String myCard;
    String myNumber;
    String myAddress;
    double myWallet;
    List<Notification> myNoti;

    public User() {}

    public User(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;

        myWallet = 0.0;
        myShoppingList = new ArrayList<>();
        myGroceries = new ArrayList<>();
        myNoti = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public List<Groceries> getMyShoppingList() { return myShoppingList; }

    public List<Groceries> getMyGroceries() { return myGroceries; }

    public double getMyWallet() { return myWallet; }

    public String getMyCard() { return myCard; }

    public String getMyNumber() { return myNumber; }

    public String getMyAddress() { return myAddress; }

    public List<Notification> getMyNoti() { return myNoti; }
}
