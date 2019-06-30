package com.example.stockup;

import java.util.ArrayList;
import java.util.List;

public class User {

    String username;
    String email;
    String role;
    List<Groceries> myShoppingList;
    List<Groceries> myGroceries;

    public User(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
        myShoppingList = new ArrayList<>();
        myShoppingList.add(new Groceries(new Item("a", "b", 1.0, "c"), 10));
        myGroceries = new ArrayList<>();
        myGroceries.add(new Groceries(new Item("a", "b", 1.0, "c"), 10));
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
}
