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

public class BuyerMyShoppingListActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    List<Groceries> list;
    ListView myListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_my_shopping_list);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        myListView = (ListView) findViewById(R.id.buyer_shopping_list);

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

                /*for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {
                    String email = itemSnapShot.child("email").getValue().toString();
                    if(email.equals(theEmail)) {
                        DataSnapshot newDS = itemSnapShot.child("myShoppingList");

                        for (DataSnapshot ds: newDS.getChildren()) {
                            String name = ds.child("name").getValue().toString();
                            if (!name.equals("a")) {
                                list.add(ds.getValue(Groceries.class));
                            }

                        }

                        break;
                    }
                } */
                list.add(new Groceries(new Item("a", "b", 1.0, "c"), 10));
                list.add(new Groceries(new Item("ab", "b", 1.0, "c"), 10));
                list.add(new Groceries(new Item("az", "b", 1.0, "c"), 10));
                list.add(new Groceries(new Item("ae", "b", 1.0, "c"), 10));
                BuyerShoppingList adapter = new BuyerShoppingList(BuyerMyShoppingListActivity.this, list);
                myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}