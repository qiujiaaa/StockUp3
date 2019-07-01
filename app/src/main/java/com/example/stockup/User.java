package com.example.stockup;

import java.util.ArrayList;
import java.util.List;

public class User {

    String username;
    String email;
    String role;
    List<Groceries> myShoppingList;
    List<Groceries> myGroceries;

    public User() {}

    public User(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;

        // DUMMY ITEMS FIRST :<
        myShoppingList = new ArrayList<>();
        myShoppingList.add(new Groceries(new Item("Ayam Brand Spread in Mayonnaise - Salmon", "AYAM BRAND", 2.65, "160g"), 3, "15"));
        myShoppingList.add(new Groceries(new Item("Coca-Cola Mini Bottle - Classic", "COCA COLA", 8.50, "12 x 250ml"), 1, "25"));
        myShoppingList.add(new Groceries(new Item("FairPrice Vegetable Oil", "FAIRPRICE", 7.95, "3L"), 1, "30"));
        myShoppingList.add(new Groceries(new Item("FairPrice Flour - Self Raising", "FAIRPRICE", 2.60, "1.5kg"), 1, "50"));
        myShoppingList.add(new Groceries(new Item("Magiclean Wiper Dry Sheet", "MAGICLEAN", 14.15, "3 x 20 per pack "), 1, "40"));
        myShoppingList.add(new Groceries(new Item("Mcm Pet Shampoo with Conditioner - Floral", "MCM", 8.00, "400ml"), 1, "60"));
        myShoppingList.add(new Groceries(new Item("Meiji Low Fat Yoghurt - Strawberry", "MEIJI", 1.0, "135g"), 10, "7"));
        myShoppingList.add(new Groceries(new Item("Milo Chocolate Malt UHT Packet Drink", "MILO", 14.20, "4 x (5+1 free) x 200ml (CTN)"), 1, "30"));
        myShoppingList.add(new Groceries(new Item("Nature's Wonders Baked Nuts - Macadamia", "NATURE'S WONDERS", 12.70, "220g"), 1, "14"));
        myShoppingList.add(new Groceries(new Item("Top Detergent Powder - Super White", "FAB", 13.55, "5.5kg"), 1, "60"));

        myGroceries = new ArrayList<>();
        myGroceries.add(new Groceries(new Item("Coca-Cola Mini Bottle - Classic", "COCA COLA", 8.50, "12 x 250ml"), 1, "25"));
        myGroceries.add(new Groceries(new Item("Magiclean Wiper Dry Sheet", "MAGICLEAN", 14.15, "3 x 20 per pack "), 1, "40"));
        myGroceries.add(new Groceries(new Item("Nature's Wonders Baked Nuts - Macadamia", "NATURE'S WONDERS", 12.70, "220g"), 1, "14"));
        myGroceries.add(new Groceries(new Item("Top Detergent Powder - Super White", "FAB", 13.55, "5.5kg"), 1, "60"));
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

    public void addNewShopping(Groceries g) {
        myShoppingList.add(g);
    }
}
