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

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    DatabaseReference dataBaseItems;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    ListView myListViewItems;
    FloatingActionButton myButtonHome;
    SearchView mySearchView;

    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        dataBaseItems = FirebaseDatabase.getInstance().getReference("items");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        myListViewItems = (ListView) findViewById(R.id.listViewItem);
        myButtonHome = (FloatingActionButton) findViewById(R.id.button_home);
        mySearchView = (SearchView) findViewById(R.id.searchViewItem);
        itemList = new ArrayList<>();

        myButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent goToHome = new Intent(ItemListActivity.this, BuyerHome.class);
            startActivity(goToHome);
            }
        });

        myListViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                final Item chosenItem = (Item) adapter.getItemAtPosition(position);

                final String theEmail = user.getEmail();
                dataBaseItems.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        itemList.clear();

                        for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {
                            String email = itemSnapShot.child("email").getValue().toString();
                            if(email.equals(theEmail)) {
                                User user = itemSnapShot.getValue(User.class);
                                user.getMyShoppingList().add(new Groceries(chosenItem, 1));
                                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        dataBaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                itemList.clear();

                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {

                    String itemName = itemSnapShot.child("name").getValue().toString();
                    String itemBrand = itemSnapShot.child("brand").getValue().toString();
                    String itemInfo = itemSnapShot.child("info").getValue().toString();
                    String price = itemSnapShot.child("price").getValue().toString();
                    double itemPrice = Double.valueOf(price);

                    itemList.add(new Item(itemName, itemBrand, itemPrice, itemInfo));
                }

                ItemList adapter = new ItemList(ItemListActivity.this, itemList);
                myListViewItems.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
