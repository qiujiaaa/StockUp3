package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditProfileActivity extends AppCompatActivity {

    LinearLayout myLLEmail;
    LinearLayout myLLPassword;
    TextView myTVEmail;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();

        myLLEmail = (LinearLayout) findViewById(R.id.profile_email);
        myLLPassword = (LinearLayout) findViewById(R.id.profile_password);
        myTVEmail = (TextView) findViewById(R.id.profile_email_set);

        myTVEmail.setText(user.getEmail());

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

            }
        });

    }
}
