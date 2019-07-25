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
    LinearLayout myLogout;
    FloatingActionButton myButtonJobPool;
    FloatingActionButton myButtonNotif;
    FloatingActionButton myButtonHaveNotif;

    FirebaseUser user;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_home);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getDisplayName().substring(1));

        myTextViewName = (TextView) findViewById(R.id.runner_name);
        final String theEmail = user.getEmail();

        //Show username on creation.
        myTextViewName.setText("Welcome, " + user.getDisplayName().substring(1) + "!");

        myAssignments = (LinearLayout) findViewById(R.id.directory_assignments);
        myCompleted = (LinearLayout) findViewById(R.id.directory_completed);
        myWallet = (LinearLayout) findViewById(R.id.directory_Rwallet);
        mySettings = (LinearLayout) findViewById(R.id.directory_Rsettings);
        myLogout = (LinearLayout) findViewById(R.id.directory_Rlogout);
        myButtonJobPool = (FloatingActionButton) findViewById(R.id.button_jobs);
        myButtonNotif = (FloatingActionButton) findViewById(R.id.runner_notif_button);
        myButtonHaveNotif = (FloatingActionButton) findViewById(R.id.runner_notif_have_button);

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
                Intent goToSettings = new Intent(RunnerHome.this, EditProfileActivity.class);
                startActivity(goToSettings);
            }
        });

        myLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(RunnerHome.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
                Intent goToLogin = new Intent(RunnerHome.this, MainActivity.class);
                startActivity(goToLogin);
            }
        });

        myButtonJobPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPool = new Intent(RunnerHome.this, RunnerAssignmentPoolActivity.class);
                startActivity(goToPool);
            }
        });

        myButtonNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNotif = new Intent (RunnerHome.this, NotificationActivity.class);
                startActivity(goToNotif);
            }
        });

        myButtonHaveNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNotif = new Intent (RunnerHome.this, NotificationActivity.class);
                startActivity(goToNotif);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseRef.child("myNoti").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean hasUnread = false;
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    if (ds.child("isRead").getValue(Boolean.class) == false) {
                        hasUnread = true;
                        break;
                    }
                }
                if (hasUnread) {
                    myButtonNotif.hide();
                    myButtonHaveNotif.show();
                } else {
                    myButtonNotif.show();
                    myButtonHaveNotif.hide();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}