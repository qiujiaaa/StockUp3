package com.example.stockup;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class BuyerOrderConfirmationActivity extends Activity {

    Button myButtonSend;
    TextView myTextViewCard;
    TextView myTextViewAddress;
    TextView myTextViewPrice;
    FirebaseUser user;
    DatabaseReference databaseRef;
    List<Groceries> list;
    int count; //for order number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_order_confirmation);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1));
        list = (List<Groceries>) getIntent().getSerializableExtra("list");

        myButtonSend = (Button) findViewById(R.id.buyer_order_confirmation);
        myTextViewCard = (TextView) findViewById(R.id.buyer_order_card);
        myTextViewAddress = (TextView) findViewById(R.id.buyer_order_address);
        myTextViewPrice = (TextView) findViewById(R.id.buyer_order_price);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myTextViewAddress.setText(dataSnapshot.child("myAddress").child("real").getValue().toString());
                myTextViewCard.setText(dataSnapshot.child("myCard").child("real").getValue().toString().substring(0, 16));

                double price =  3.0;
                for (DataSnapshot ds: dataSnapshot.child("myShoppingList").getChildren()) {
                    Groceries g = ds.getValue(Groceries.class);
                    price += g.getPrice() * g.getQuantity();
                }
                myTextViewPrice.setText("$" + String.format("%.2f", price));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String status = "Pending";
                String price = myTextViewPrice.getText().toString();
                String address = myTextViewAddress.getText().toString();

                // Empty Shopping List
                DatabaseReference shoppingListDataRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myShoppingList");
                HashMap<String, Object> map = new HashMap<>();
                map.put("0", new Groceries(new Item("", "", 0, ""), 0, ""));
                shoppingListDataRef.setValue(map);

                // Create Order object
                Order order = new Order(count+1, date, status, price, address, list);
                Order.increaseCount();

                // Add to Orders Pool in Firebase
                DatabaseReference ordersDataRef = FirebaseDatabase.getInstance().getReference("orders");
                ordersDataRef.child("count").setValue(++count);
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("" + count, order);
                ordersDataRef.updateChildren(map2);

                // Add to Orders in user
                DatabaseReference orderListDataRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myOrder");
                orderListDataRef.updateChildren(map2);

                Toast.makeText(BuyerOrderConfirmationActivity.this, "Order Submitted", Toast.LENGTH_SHORT).show();
                Intent goToOrder = new Intent(BuyerOrderConfirmationActivity.this, BuyerOrderActivity.class);
                startActivity(goToOrder);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("orders");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = dataSnapshot.child("count").getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
