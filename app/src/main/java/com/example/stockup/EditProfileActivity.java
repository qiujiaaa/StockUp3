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

public class EditProfileActivity extends AppCompatActivity {

    LinearLayout myLLEmail;
    LinearLayout myLLPassword;
    TextView myTVEmail;
    FloatingActionButton myButtonHome;

    FirebaseUser user;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1));

        myLLEmail = (LinearLayout) findViewById(R.id.profile_email);
        myLLPassword = (LinearLayout) findViewById(R.id.profile_password);
        myTVEmail = (TextView) findViewById(R.id.profile_email_set);
        myButtonHome = (FloatingActionButton) findViewById(R.id.edit_profile_button_home);

        myLLEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEmail = new Intent(EditProfileActivity.this, EditProfileEmailActivity.class);
                startActivity(goToEmail);
            }
        });

        myLLPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEmail = new Intent(EditProfileActivity.this, EditProfilePasswordActivity.class);
                startActivity(goToEmail);
            }
        });

        myButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome;
                if (user.getDisplayName().substring(0, 1).equals("B")) {
                    goToHome = new Intent(EditProfileActivity.this, BuyerHome.class);
                } else {
                    goToHome = new Intent(EditProfileActivity.this, RunnerHome.class);
                }
                startActivity(goToHome);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myTVEmail.setText(dataSnapshot.child("email").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
