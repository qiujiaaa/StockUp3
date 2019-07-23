package com.example.stockup;

import android.support.annotation.NonNull;
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

public class RunnerWalletActivity extends AppCompatActivity {

    TextView myTextViewBalance;
    LinearLayout myLLWithdraw;
    LinearLayout myLLManage;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_wallet);

        user = FirebaseAuth.getInstance().getCurrentUser();

        myTextViewBalance = (TextView) findViewById(R.id.Rwallet_balance);
        myLLWithdraw = (LinearLayout) findViewById(R.id.Rwallet_withdraw);
        myLLManage = (LinearLayout) findViewById(R.id.Rwallet_manage);

        myLLWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myLLManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myWallet");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myTextViewBalance.setText("$" + String.format("%.2f", dataSnapshot.getValue(Double.class)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
