package com.example.stockup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuyerShoppingListActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    List<Groceries> list;
    ListView myListView;
    Button myButtonConfirmOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_my_shopping_list);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        myListView = (ListView) findViewById(R.id.buyer_shopping_list);
        myButtonConfirmOrder = (Button) findViewById(R.id.buyer_shopping_list_order);

        myButtonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToOrders = new Intent(BuyerShoppingListActivity.this, BuyerOrderConfirmationActivity.class);
                startActivity(goToOrders);
            }
        });

        list = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        final String theEmail = user.getEmail();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                /*
                list.add(new Groceries(new Item("100 Plus Isotonic Can Drink - Original", "100 PLUS", 14.7, "24 x 325ml"), 5, "30"));
                list.add(new Groceries(new Item("Coca-Cola Mini Bottle - Classic", "COCA COLA", 8.50, "12 x 250ml"), 1, "25"));
                list.add(new Groceries(new Item("Magiclean Wiper Dry Sheet", "MAGICLEAN", 14.15, "3 x 20 per pack "), 1, "40"));
                list.add(new Groceries(new Item("Nature's Wonders Baked Nuts - Macadamia", "NATURE'S WONDERS", 12.70, "220g"), 1, "14"));
                list.add(new Groceries(new Item("Top Detergent Powder - Super White", "FAB", 13.55, "5.5kg"), 1, "60"));
                */
                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {
                    String email = itemSnapShot.child("email").getValue().toString();
                    if(email.equals(theEmail)) {
                        DataSnapshot newDS = itemSnapShot.child("myShoppingList");
                        for (DataSnapshot ds: newDS.getChildren()) {
                            String name = ds.child("name").getValue().toString();
                            list.add(ds.getValue(Groceries.class));
                            /* Dummy grocery
                            if (!name.equals("a")) {
                                list.add(ds.getValue(Groceries.class));
                            } */

                        }

                        break;
                    }
                }
                BuyerShoppingList adapter = new BuyerShoppingList(BuyerShoppingListActivity.this, list);
                myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}