package com.example.stockup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RunnerAssignmentActivity extends AppCompatActivity {

    DatabaseReference dataBaseItems;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    ListView myListViewItems;
    FloatingActionButton myHomeButton;

    List<Order> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_assignment);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        dataBaseItems = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myOrder");

        myListViewItems = (ListView) findViewById(R.id.runner_assignment_list);
        myHomeButton = (FloatingActionButton) findViewById(R.id.runner_assignment_button_home);
        list = new ArrayList<>();

        myHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(RunnerAssignmentActivity.this, RunnerHome.class);
                startActivity(goToHome);
            }
        });

        myListViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapter, View view, int position, final long id) {
                Order chosenOrder = (Order) adapter.getItemAtPosition(position);
                Intent goToAddPage = new Intent(RunnerAssignmentActivity.this, RunnerAssignmentDetailActivity.class);
                goToAddPage.putExtra("order", chosenOrder);
                startActivity(goToAddPage);
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

                    Order order = itemSnapShot.getValue(Order.class);
                    list.add(order);

                    /*int number = itemSnapShot.child("number").getValue(Integer.class);
                    String date = itemSnapShot.child("date").getValue().toString();
                    String status = itemSnapShot.child("status").getValue().toString();
                    String price = itemSnapShot.child("price").getValue().toString();
                    String address = itemSnapShot.child("address").getValue().toString();

                    list.add(new Item(itemName, itemBrand, itemPrice, itemInfo));*/
                }

                Collections.sort(list, new OrderComparator());
                RunnerAssignment adapter = new RunnerAssignment(RunnerAssignmentActivity.this, list);
                myListViewItems.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
