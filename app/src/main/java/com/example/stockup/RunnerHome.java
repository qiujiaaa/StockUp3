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
    LinearLayout myWallet;
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

        //Show username on creation.
        myTextViewName.setText("Welcome, " + user.getDisplayName().substring(1) + "!");

        myAssignments = (LinearLayout) findViewById(R.id.directory_assignments);
        myCompleted = (LinearLayout) findViewById(R.id.directory_completed);
        myWallet = (LinearLayout) findViewById(R.id.directory_Rwallet);
        mySettings = (LinearLayout) findViewById(R.id.directory_Rsettings);
        myButtonJobPool = (FloatingActionButton) findViewById(R.id.button_jobs);

        myAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAssignments = new Intent(RunnerHome.this, RunnerAssignmentActivity.class);
                startActivity(goToAssignments);
            }
        });

        myCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPage = new Intent(RunnerHome.this, RunnerCompletedAssignmentActivity.class);
                startActivity(goToPage);
            }
        });

        myWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToWallet = new Intent(RunnerHome.this, RunnerWalletActivity.class);
                startActivity(goToWallet);
            }
        });

        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myButtonJobPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPool = new Intent(RunnerHome.this, RunnerAssignmentPoolActivity.class);
                startActivity(goToPool);
            }
        });
    }
}