package com.example.stockup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class BuyerOrderActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseUser user;

    List<Order> list;
    ListView myListView;
    FloatingActionButton myButtonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_order);

        user = FirebaseAuth.getInstance().getCurrentUser();
        String username = user.getDisplayName().substring(1);
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(username).child("myOrder");
        myListView = (ListView) findViewById(R.id.buyer_my_orders);
        myButtonHome = (FloatingActionButton) findViewById(R.id.buyer_order_button_home);

        myButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(BuyerOrderActivity.this, BuyerHome.class);
                startActivity(goToHome);
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order chosenOrder = (Order) parent.getItemAtPosition(position);
                Intent goToDetailsPage = new Intent(BuyerOrderActivity.this, BuyerOrderDetailsActivity.class);
                goToDetailsPage.putExtra("order", chosenOrder);
                startActivity(goToDetailsPage);
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

                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    String num = ds.child("number").getValue().toString();
                    if (!num.equals("")) {
                        list.add(ds.getValue(Order.class));
                    }

                }

                Collections.sort(list, new OrderComparator());
                Collections.reverse(list);
                BuyerOrder adapter = new BuyerOrder(BuyerOrderActivity.this, list);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
