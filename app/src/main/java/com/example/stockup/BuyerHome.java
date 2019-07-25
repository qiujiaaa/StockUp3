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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
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
    LinearLayout myWallet;
    LinearLayout myAddress;
    LinearLayout mySettings;
    LinearLayout myLogout;
    FloatingActionButton myButtonShopping;
    FloatingActionButton myButtonNotif;
    FloatingActionButton myButtonHaveNotif;

    DatabaseReference databaseRef;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getDisplayName().substring(1));

        myTextViewName = (TextView) findViewById(R.id.buyer_name);


        //Show username on creation.
        myTextViewName.setText("Welcome, " + user.getDisplayName().substring(1) + "!");

        myGroceries = (LinearLayout) findViewById(R.id.directory_groceries);
        myShoppingList = (LinearLayout) findViewById(R.id.directory_shopping_list);
        myOrders = (LinearLayout) findViewById(R.id.directory_orders);
        myWallet = (LinearLayout) findViewById(R.id.directory_Bwallet);
        myAddress = (LinearLayout) findViewById(R.id.directory_Baddress);
        mySettings = (LinearLayout) findViewById(R.id.directory_Bsettings);
        myLogout = (LinearLayout) findViewById(R.id.directory_Blogout);
        myButtonShopping = (FloatingActionButton) findViewById(R.id.button_shopping);
        myButtonNotif = (FloatingActionButton) findViewById(R.id.buyer_notif_button);
        myButtonHaveNotif = (FloatingActionButton) findViewById(R.id.buyer_notif_have_button);

        myGroceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGroceries  = new Intent(BuyerHome.this, BuyerGroceriesActivity.class);
                startActivity(goToGroceries);
            }
        });

        myShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToShoppingList  = new Intent(BuyerHome.this, BuyerShoppingListActivity.class);
                startActivity(goToShoppingList);

            }
        });

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToOrders = new Intent(BuyerHome.this, BuyerOrderActivity.class);
                startActivity(goToOrders);
            }
        });

        myWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToWallet = new Intent(BuyerHome.this, BuyerWalletActivity.class);
                startActivity(goToWallet);
            }
        });

        myAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddress = new Intent(BuyerHome.this, BuyerAddressActivity.class);
                startActivity(goToAddress);
            }
        });

        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(BuyerHome.this, EditProfileActivity.class);
                startActivity(goToSettings);
            }
        });

        myLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(BuyerHome.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
                Intent goToLogin = new Intent(BuyerHome.this, MainActivity.class);
                startActivity(goToLogin);
            }
        });

        myButtonShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToShopping = new Intent(BuyerHome.this, ItemListActivity.class);
                startActivity(goToShopping);
            }
        });

        myButtonNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNotif = new Intent(BuyerHome.this, NotificationActivity.class);
                startActivity(goToNotif);
            }
        });

        myButtonHaveNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNotif = new Intent(BuyerHome.this, NotificationActivity.class);
                startActivity(goToNotif);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseRef.child("myNoti").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean hasUnread = false;
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    if (ds.child("isRead").getValue(Boolean.class) == false) {
                        hasUnread = true;
                        break;
                    }
                }
                if (hasUnread) {
                    myButtonNotif.hide();
                    myButtonHaveNotif.show();
                } else {
                    myButtonHaveNotif.hide();
                    myButtonNotif.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
