package com.example.stockup;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuyerGroceriesActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    List<Groceries> list;
    ListView myListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_my_groceries);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        myListView = (ListView) findViewById(R.id.buyer_my_groceries);

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
                list.add(new Groceries(new Item("Ayam Brand Spread in Mayonnaise - Salmon", "AYAM BRAND", 2.65, "160g"), 3, "15"));
                list.add(new Groceries(new Item("Coca-Cola Mini Bottle - Classic", "COCA COLA", 8.50, "12 x 250ml"), 1, "25"));
                list.add(new Groceries(new Item("FairPrice Vegetable Oil", "FAIRPRICE", 7.95, "3L"), 1, "30"));
                list.add(new Groceries(new Item("FairPrice Flour - Self Raising", "FAIRPRICE", 2.60, "1.5kg"), 1, "50"));
                list.add(new Groceries(new Item("Magiclean Wiper Dry Sheet", "MAGICLEAN", 14.15, "3 x 20 per pack "), 1, "40"));
                list.add(new Groceries(new Item("Mcm Pet Shampoo with Conditioner - Floral", "MCM", 8.00, "400ml"), 1, "60"));
                list.add(new Groceries(new Item("Meiji Low Fat Yoghurt - Strawberry", "MEIJI", 1.0, "135g"), 10, "7"));
                list.add(new Groceries(new Item("Milo Chocolate Malt UHT Packet Drink", "MILO", 14.20, "4 x (5+1 free) x 200ml (CTN)"), 1, "30"));
                list.add(new Groceries(new Item("Nature's Wonders Baked Nuts - Macadamia", "NATURE'S WONDERS", 12.70, "220g"), 1, "14"));
                list.add(new Groceries(new Item("Top Detergent Powder - Super White", "FAB", 13.55, "5.5kg"), 1, "60"));
                /*for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {
                    String email = itemSnapShot.child("email").getValue().toString();
                    if(email.equals(theEmail)) {
                        DataSnapshot newDS = itemSnapShot.child("myGroceries");
                        for (DataSnapshot ds: newDS.getChildren()) {
                            String name = ds.child("name").getValue().toString();
                            list.add(ds.getValue(Groceries.class));
                            if (!name.equals("a")) {
                                list.add(ds.getValue(Groceries.class));
                            }

                        }
                        break;
                    }
                }*/
                BuyerGroceries adapter = new BuyerGroceries(BuyerGroceriesActivity.this, list);
                myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}