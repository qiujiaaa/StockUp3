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

public class RunnerHome extends AppCompatActivity {

    TextView myTextViewName;
    LinearLayout myAssignments;
    LinearLayout myCompleted;
    LinearLayout myBookmarks;
    LinearLayout myWallet;
    LinearLayout myUNDECIDED;
    LinearLayout mySettings;
    FloatingActionButton myButtonJobPool;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_home);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        myTextViewName = (TextView) findViewById(R.id.runner_name);
        final String theEmail = user.getEmail();

        /*
        Show username on creation.
         */
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String email = "";
                String un = "";

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    email = ds.child("email").getValue().toString();
                    if (email.equals(theEmail)) {
                        un = ds.child("username").getValue().toString();
                        break;
                    }
                }
                myTextViewName.setText("Welcome, " + un + "!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myAssignments = (LinearLayout) findViewById(R.id.directory_assignments);
        myCompleted = (LinearLayout) findViewById(R.id.directory_completed);
        myBookmarks = (LinearLayout) findViewById(R.id.directory_Rbookmarks);
        myWallet = (LinearLayout) findViewById(R.id.directory_Rwallet);
        myUNDECIDED = (LinearLayout) findViewById(R.id.directory_Rundecided);
        mySettings = (LinearLayout) findViewById(R.id.directory_Rsettings);
        myButtonJobPool = (FloatingActionButton) findViewById(R.id.button_jobs);

        myAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToWallet = new Intent(RunnerHome.this, WalletActivity.class);
                startActivity(goToWallet);
            }
        });

        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myUNDECIDED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myButtonJobPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}