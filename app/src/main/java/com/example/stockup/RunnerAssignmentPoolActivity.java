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

        dataBaseItems = FirebaseDatabase.getInstance().getReference("items");
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

        /*myListViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapter, View view, int position, final long id) {
                Order chosenOrder = (Order) adapter.getItemAtPosition(position);
                Intent goToAddPage = new Intent(ItemListActivity.this, BuyerAddItemActivity.class);
                goToAddPage.putExtra("item", chosenItem);
                startActivity(goToAddPage);
            }
        });*/
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        dataBaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {

                    String itemName = itemSnapShot.child("name").getValue().toString();
                    String itemBrand = itemSnapShot.child("brand").getValue().toString();
                    String itemInfo = itemSnapShot.child("info").getValue().toString();
                    String price = itemSnapShot.child("price").getValue().toString();
                    double itemPrice = Double.valueOf(price);

                    list.add(new Item(itemName, itemBrand, itemPrice, itemInfo));
                }

                Collections.sort(list, new ItemsComparator());
                RunnerAssignmentPool adapter = new RunnerAssignmentPool(RunnerAssignmentPoolActivity.this, list);
                myListViewItems.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/
}