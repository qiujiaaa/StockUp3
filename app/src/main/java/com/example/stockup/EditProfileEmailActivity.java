package com.example.stockup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileEmailActivity extends AppCompatActivity {

    TextView myTVEmail;
    EditText myETNewEmail;
    EditText myETPassword;
    Button myButton;

    DatabaseReference databaseRef;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_email);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1));

        myTVEmail = (TextView) findViewById(R.id.profile_email);
        myETNewEmail = (EditText) findViewById(R.id.profile_email_new);
        myETPassword = (EditText) findViewById(R.id.profile_email_confirm);
        myButton = (Button) findViewById(R.id.profile_email_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newEmail = myETNewEmail.getText().toString().trim();
                final String pwd = myETPassword.getText().toString().trim();
                if (newEmail.isEmpty()) {
                    Toast.makeText(EditProfileEmailActivity.this, "Field cannot be blank.", Toast.LENGTH_SHORT).show();
                } else {
                    AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), pwd);
                    user.reauthenticate(authCredential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user.updateEmail(newEmail);
                                        databaseRef.child("email").setValue(newEmail);
                                        Toast.makeText(EditProfileEmailActivity.this, "Email updated.", Toast.LENGTH_SHORT).show();
                                        Intent goToPage = new Intent(EditProfileEmailActivity.this, EditProfileActivity.class);
                                        startActivity(goToPage);
                                    } else {
                                        Toast.makeText(EditProfileEmailActivity.this, "Wrong Password.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
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
