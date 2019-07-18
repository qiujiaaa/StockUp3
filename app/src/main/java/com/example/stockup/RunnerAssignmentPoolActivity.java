package com.example.stockup;

import android.content.Intent;
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
import java.util.HashMap;
import java.util.List;

public class RunnerAssignmentPoolActivity extends AppCompatActivity {

    DatabaseReference dataBaseItems;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    ListView myListViewItems;
    FloatingActionButton myButtonHome;

    List<Order> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_assignment_pool);

        dataBaseItems = FirebaseDatabase.getInstance().getReference("orders");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        myListViewItems = (ListView) findViewById(R.id.runner_assignment_pool);
        myButtonHome = (FloatingActionButton) findViewById(R.id.runner_assignment_pool_button_home);
        list = new ArrayList<>();

        myButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(RunnerAssignmentPoolActivity.this, RunnerHome.class);
                startActivity(goToHome);
            }
        });

        myListViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order chosenOrder = (Order) parent.getItemAtPosition(position);
                Intent goToDetailsPage = new Intent(RunnerAssignmentPoolActivity.this, RunnerAssignmentPoolDetailsActivity.class);
                goToDetailsPage.putExtra("order", chosenOrder);
                startActivity(goToDetailsPage);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        dataBaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {

                    String orderPrice = itemSnapShot.child("price").getValue().toString();
                    String orderStatus = itemSnapShot.child("status").getValue().toString();
                    String orderAddress = itemSnapShot.child("address").getValue().toString();
                    String orderDate = itemSnapShot.child("date").getValue().toString();
                    int orderNumber = itemSnapShot.child("number").getValue(Long.class).intValue();

                    List<Groceries> orderList = new ArrayList<Groceries>();
                    for (DataSnapshot ds : itemSnapShot.child("list").getChildren()) {
                        orderList.add(ds.getValue(Groceries.class));
                    }

                    list.add(new Order(orderNumber, orderDate, orderStatus, orderPrice, orderAddress, orderList));
                }
                Collections.sort(list, new OrderComparator());
                RunnerAssignmentPool adapter = new RunnerAssignmentPool(RunnerAssignmentPoolActivity.this, list);
                myListViewItems.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
