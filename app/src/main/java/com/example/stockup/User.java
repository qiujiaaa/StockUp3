package com.example.stockup;

import java.util.ArrayList;
import java.util.List;

public class User {

    String username;
    String email;
    String role;
    List<Groceries> myShoppingList;
    List<Groceries> myGroceries;
    List<String> myCard;
    List<String> myAddress;
    List<Notification> myNoti;

    public User() {}

    public User(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;

        myShoppingList = new ArrayList<>();
        myGroceries = new ArrayList<>();
        myCard = new ArrayList<>();
        myAddress = new ArrayList<>();
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

    public List<String> getCard() { return myCard; }

    public List<String> getMyAddress() { return myAddress; }

    public List<Notification> getMyNoti() { return myNoti; }
}
