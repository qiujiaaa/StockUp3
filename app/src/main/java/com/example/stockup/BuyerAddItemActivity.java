package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;

public class BuyerAddItemActivity extends AppCompatActivity {

    TextView myTextViewName;
    TextView myTextViewBrand;
    TextView myTextViewPrice;
    TextView myTextViewInfo;
    Button myButtonShopping;
    Button myButtonGroceries;
    Item item;

    FirebaseUser user;
    DatabaseReference myDataBaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_add_item);

        Intent myIntent = getIntent();
        item = (Item) myIntent.getSerializableExtra("item");

        myDataBaseRef = FirebaseDatabase.getInstance().getReference().child("users");
        user = FirebaseAuth.getInstance().getCurrentUser();

        myTextViewName = (TextView) findViewById(R.id.add_item_name);
        myTextViewBrand = (TextView) findViewById(R.id.add_item_brand);
        myTextViewPrice = (TextView) findViewById(R.id.add_item_price);
        myTextViewInfo = (TextView) findViewById(R.id.add_item_info);
        myButtonShopping = (Button) findViewById(R.id.add_item_shopping);
        myButtonGroceries = (Button) findViewById(R.id.add_item_groceries);

        myTextViewName.setText(item.getName());
        myTextViewBrand.setText(item.getBrand());
        myTextViewPrice.setText("$" + String.format("%.2f", item.getPrice()));
        myTextViewInfo.setText(item.getAddInfo());

        myButtonGroceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Connecting to database
                String username = user.getDisplayName().substring(1);
                HashMap<String, Object> map = new HashMap<>();
                map.put(item.getName(), new Groceries(item, 0));
                myDataBaseRef.child(username).child("myGroceries").updateChildren(map);
                Toast.makeText(BuyerAddItemActivity.this, "Added to Bookmarks", Toast.LENGTH_SHORT).show();
                Intent goBack = new Intent(BuyerAddItemActivity.this, ItemListActivity.class);
                startActivity(goBack);
            }
        });

        myButtonShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddShopping = new Intent(BuyerAddItemActivity.this, BuyerAddToShoppingListActivity.class);
                goToAddShopping.putExtra("item", item);
                startActivity(goToAddShopping);
            }
        });

    }
}
