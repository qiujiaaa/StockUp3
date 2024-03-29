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

import org.w3c.dom.Text;

public class BuyerWalletActivity extends AppCompatActivity {

    LinearLayout myLLAdd;
    TextView myTVCard;
    FloatingActionButton myButtonHome;

    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_wallet);

        String username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName().substring(1);
        databaseRef = FirebaseDatabase.getInstance().getReference("users").child(username).child("myCard");

        myLLAdd = (LinearLayout) findViewById(R.id.Bpayment_add);
        myTVCard = (TextView) findViewById(R.id.Bpayment_card);
        myButtonHome = (FloatingActionButton) findViewById(R.id.Bpayment_home_button);

        myLLAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPage = new Intent(BuyerWalletActivity.this, BuyerWalletChangeActivity.class);
                startActivity(goToPage);
            }
        });

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String card = dataSnapshot.getValue().toString();
                card = card.substring(0, 4) + "-" + card.substring(4, 8) + "-" + card.substring(8, 12) + "-" + card.substring(12, 16);
                myTVCard.setText(card);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(BuyerWalletActivity.this, BuyerHome.class);
                startActivity(goToHome);
            }
        });
    }
}
