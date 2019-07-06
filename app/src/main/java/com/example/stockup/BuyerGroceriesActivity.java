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

                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {
                    String email = itemSnapShot.child("email").getValue().toString();
                    if(email.equals(theEmail)) {
                        DataSnapshot newDS = itemSnapShot.child("myGroceries");
                        for (DataSnapshot ds: newDS.getChildren()) {
                            String name = ds.child("name").getValue().toString();
                            //list.add(ds.getValue(Groceries.class));
                            if (!name.equals("")) {
                                list.add(ds.getValue(Groceries.class));
                            }

                        }
                        break;
                    }
                }
                BuyerGroceries adapter = new BuyerGroceries(BuyerGroceriesActivity.this, list);
                myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}