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

    public User() {}

    public User(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;

        // DUMMY ITEMS FIRST :<
        myShoppingList = new ArrayList<>();
        myShoppingList.add(new Groceries(new Item("", "", 0, ""), 0, "0"));

        myGroceries = new ArrayList<>();
        myGroceries.add(new Groceries(new Item("", "", 0, ""), 0, "0"));

        myCard = new ArrayList<>();
        myCard.add(" ");

        myAddress = new ArrayList<>();
        myAddress.add(" ");
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
}
