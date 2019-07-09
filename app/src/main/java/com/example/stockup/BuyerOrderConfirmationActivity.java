package com.example.stockup;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

public class BuyerOrderConfirmationActivity extends Activity {

    Button myButtonSend;
    TextView myTextViewCard;
    TextView myTextViewAddress;
    TextView myTextViewPrice;
    FirebaseUser user;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_order_confirmation);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1));

        myButtonSend = (Button) findViewById(R.id.buyer_order_confirmation);
        myTextViewCard = (TextView) findViewById(R.id.buyer_order_card);
        myTextViewAddress = (TextView) findViewById(R.id.buyer_order_address);
        myTextViewPrice = (TextView) findViewById(R.id.buyer_order_price);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myTextViewAddress.setText(dataSnapshot.child("myAddress").child("real").getValue().toString());
                myTextViewCard.setText(dataSnapshot.child("myCard").child("real").getValue().toString().substring(0, 16));

                double price =  0.0;
                for (DataSnapshot ds: dataSnapshot.child("myShoppingList").getChildren()) {
                    Groceries g = ds.getValue(Groceries.class);
                    price += g.getPrice();
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
                // Create Order object
                // Empty Shopping List
                Toast.makeText(BuyerOrderConfirmationActivity.this, "Order successfully sent", Toast.LENGTH_SHORT).show();
                Intent goToOrder = new Intent(BuyerOrderConfirmationActivity.this, BuyerOrderActivity.class);
                startActivity(goToOrder);
            }
        });

    }

}
