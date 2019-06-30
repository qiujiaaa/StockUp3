package com.example.stockup;

public class Groceries extends Item {

    Item item;
    int quantity;

    public Groceries(Item item, int quantity) {
        super(item);
        this.item = item;
        this.quantity = quantity;
    }

    protected Item getItem() {
        return item;
    }

    protected int getQuantity() {
        return quantity;
    }

    protected void increaseQuantity() {
        quantity++;
    }

    protected void decreaseQuantity() {
        quantity--;
    }
}
