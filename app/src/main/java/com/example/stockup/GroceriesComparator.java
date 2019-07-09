package com.example.stockup;

import java.util.Comparator;

public class GroceriesComparator implements Comparator<Groceries> {

    @Override
    public int compare(Groceries g1, Groceries g2) {
        return g1.getName().compareTo(g2.getName());
    }
}
