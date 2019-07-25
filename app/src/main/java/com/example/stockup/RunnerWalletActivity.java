package com.example.stockup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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

public class RunnerWalletActivity extends AppCompatActivity {

    TextView myTextViewBalance;
    LinearLayout myLLCollect;
    LinearLayout myLLWithdraw;
    LinearLayout myLLManage;
    FloatingActionButton myButtonHome;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_wallet);

        user = FirebaseAuth.getInstance().getCurrentUser();

        myTextViewBalance = (TextView) findViewById(R.id.Rwallet_balance);
        myLLCollect = (LinearLayout) findViewById(R.id.Rwallet_collect);
        myLLWithdraw = (LinearLayout) findViewById(R.id.Rwallet_withdraw);
        myLLManage = (LinearLayout) findViewById(R.id.Rwallet_manage);
        myButtonHome = (FloatingActionButton) findViewById(R.id.Rwallet_home_button);

        myButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(RunnerWalletActivity.this, RunnerHome.class);
                startActivity(goToHome);
            }
        });

        myLLCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent collect = new Intent(RunnerWalletActivity.this, RunnerWalletCollectActivity.class);
                double money = Double.valueOf(myTextViewBalance.getText().toString().substring(1));
                collect.putExtra("money", money);
                startActivity(collect);
            }
        });

        myLLWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double money = Double.valueOf(myTextViewBalance.getText().toString().substring(1));
                if (money == 0.0) {
                    Toast.makeText(RunnerWalletActivity.this, "Wallet is empty.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent changeNumber = new Intent(RunnerWalletActivity.this, RunnerWalletWithdrawActivity.class);
                    startActivity(changeNumber);
                }
            }
        });

        myLLManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeNumber = new Intent(RunnerWalletActivity.this, RunnerWalletChangeActivity.class);
                startActivity(changeNumber);
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
