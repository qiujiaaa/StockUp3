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

public class ItemListActivity extends AppCompatActivity {

    DatabaseReference dataBaseItems;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    ListView myListViewItems;
    FloatingActionButton myButtonHome;
    SearchView mySearchView;

    List<Item> itemList;
    List<Item> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        dataBaseItems = FirebaseDatabase.getInstance().getReference("items");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        myListViewItems = (ListView) findViewById(R.id.buyer_shopping);
        myButtonHome = (FloatingActionButton) findViewById(R.id.buyer_shopping_button_home);
        mySearchView = (SearchView) findViewById(R.id.buyer_shopping_searchViewItem);
        itemList = new ArrayList<>();
        searchList = new ArrayList<>();

        myButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent goToHome = new Intent(ItemListActivity.this, BuyerHome.class);
            startActivity(goToHome);
            }
        });

        myListViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapter, View view, int position, final long id) {
                Item chosenItem = (Item) adapter.getItemAtPosition(position);
                Intent goToAddPage = new Intent(ItemListActivity.this, BuyerAddItemActivity.class);
                goToAddPage.putExtra("item", chosenItem);
                startActivity(goToAddPage);
            }
        });

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return searchName(newText);
            }
        });
    }

    private boolean searchName(String text) {

        String search = text.toLowerCase();
        searchList.clear();

        for (Item item: itemList) {
            String name = item.getName().toLowerCase();
            if (name.contains(search)) {
                searchList.add(item);
            }
        }
        if (searchList.isEmpty()) {
            Toast.makeText(ItemListActivity.this, "No search results.", Toast.LENGTH_SHORT).show();
        }

        Collections.sort(searchList, new ItemsComparator());
        ItemList adapter = new ItemList(ItemListActivity.this, searchList);
        myListViewItems.setAdapter(adapter);
        return false;

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

                Collections.sort(itemList, new ItemsComparator());
                ItemList adapter = new ItemList(ItemListActivity.this, itemList);
                myListViewItems.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
