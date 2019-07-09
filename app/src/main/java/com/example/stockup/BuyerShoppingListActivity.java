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
import java.util.Collections;
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

                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {
                    String email = itemSnapShot.child("email").getValue().toString();
                    if(email.equals(theEmail)) {
                        DataSnapshot newDS = itemSnapShot.child("myShoppingList");
                        for (DataSnapshot ds: newDS.getChildren()) {
                            String name = ds.child("name").getValue().toString();
                            if (!name.equals("")) {
                                list.add(ds.getValue(Groceries.class));
                            }

                        }

                        break;
                    }
                }
                Collections.sort(list, new GroceriesComparator());
                BuyerShoppingList adapter = new BuyerShoppingList(BuyerShoppingListActivity.this, list);
                myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}