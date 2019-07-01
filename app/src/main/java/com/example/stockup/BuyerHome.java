package com.example.stockup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BuyerHome extends AppCompatActivity {

    TextView myTextViewName;
    LinearLayout myGroceries;
    LinearLayout myShoppingList;
    LinearLayout myOrders;
    LinearLayout myBookmarks;
    LinearLayout myWallet;
    LinearLayout myAddress;
    LinearLayout mySettings;
    FloatingActionButton myButtonShopping;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        myTextViewName = (TextView) findViewById(R.id.buyer_name);

        /*
        Show username on creation.
         */
        final String theEmail = user.getEmail();
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String email = "";
                String un = "";

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    email = ds.child("email").getValue().toString();
                    if (email.equals(theEmail)) {
                        un = ds.child("username").getValue().toString();
                        break;
                    }
                }
                myTextViewName.setText("Welcome, " + un + "!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myGroceries = (LinearLayout) findViewById(R.id.directory_groceries);
        myShoppingList = (LinearLayout) findViewById(R.id.directory_shopping_list);
        myOrders = (LinearLayout) findViewById(R.id.directory_orders);
        myBookmarks = (LinearLayout) findViewById(R.id.directory_Bbookmarks);
        myWallet = (LinearLayout) findViewById(R.id.directory_Bwallet);
        myAddress = (LinearLayout) findViewById(R.id.directory_Baddress);
        mySettings = (LinearLayout) findViewById(R.id.directory_Bsettings);
        myButtonShopping = (FloatingActionButton) findViewById(R.id.button_shopping);

        myGroceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToShoppingList  = new Intent(BuyerHome.this, BuyerMyShoppingListActivity.class);
                startActivity(goToShoppingList);

            }
        });

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToWallet = new Intent(BuyerHome.this, WalletActivity.class);
                startActivity(goToWallet);
            }
        });

        myAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myButtonShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToShopping = new Intent(BuyerHome.this, ItemListActivity.class);
                startActivity(goToShopping);
            }
        });
    }
}
