package com.example.stockup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BuyerAddressActivity extends AppCompatActivity {

    LinearLayout myLLAddAddress;
    FloatingActionButton myButtonHome;
    TextView myAddress;

    FirebaseUser user;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_address);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myAddress");

        myAddress = (TextView) findViewById(R.id.Baddress);
        myLLAddAddress = (LinearLayout) findViewById(R.id.Baddress_add);
        myButtonHome = (FloatingActionButton) findViewById(R.id.Baddress_home_button);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String address = dataSnapshot.getValue().toString();
                myAddress.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(BuyerAddressActivity.this, BuyerHome.class);
                startActivity(goToHome);
            }
        });

        myLLAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPage = new Intent(BuyerAddressActivity.this, BuyerAddressChangeActivity.class);
                startActivity(goToPage);
            }
        });
    }
}
