package com.example.stockup;

import java.util.Comparator;

public class ItemsComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
