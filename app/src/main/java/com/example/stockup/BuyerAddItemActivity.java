package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class BuyerAddItemActivity extends AppCompatActivity {

    TextView myTextViewName;
    TextView myTextViewBrand;
    TextView myTextViewPrice;
    Button myButtonShopping;
    Button myButtonGroceries;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_add_item);

        Intent myIntent = getIntent();
        item = (Item) myIntent.getSerializableExtra("item");

        myTextViewName = (TextView) findViewById(R.id.add_item_name);
        myTextViewBrand = (TextView) findViewById(R.id.add_item_brand);
        myTextViewPrice = (TextView) findViewById(R.id.add_item_price);
        myButtonShopping = (Button) findViewById(R.id.add_item_shopping);
        myButtonGroceries = (Button) findViewById(R.id.add_item_groceries);

        myTextViewName.setText(item.getName());
        myTextViewBrand.setText(item.getBrand());
        myTextViewPrice.setText("$" + item.getPrice());


    }
}
