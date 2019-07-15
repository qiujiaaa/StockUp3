package com.example.stockup;

import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {

    @Override
    public int compare(Order g1, Order g2) {

        return g1.getNumber() - g2.getNumber();
    }
}
