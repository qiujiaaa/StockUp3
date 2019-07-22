package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileEmailActivity extends AppCompatActivity {

    TextView myTVEmail;
    EditText myETNewEmail;
    Button myButton;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_email);

        user = FirebaseAuth.getInstance().getCurrentUser();

        myTVEmail = (TextView) findViewById(R.id.profile_email);
        myETNewEmail = (EditText) findViewById(R.id.profile_email_new);
        myButton = (Button) findViewById(R.id.profile_email_button);

        myTVEmail.setText(user.getEmail());

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = myETNewEmail.getText().toString().trim();
                if (newEmail.isEmpty()) {
                    Toast.makeText(EditProfileEmailActivity.this, "Field cannot be blank.", Toast.LENGTH_SHORT).show();
                } else {
                    user.updateEmail(newEmail);
                    FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("email").setValue(newEmail);
                    Toast.makeText(EditProfileEmailActivity.this, "Email updated.", Toast.LENGTH_SHORT).show();
                    Intent goToPage = new Intent(EditProfileEmailActivity.this, EditProfileActivity.class);
                    startActivity(goToPage);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        myTVEmail.setText(user.getEmail());
    }
}
