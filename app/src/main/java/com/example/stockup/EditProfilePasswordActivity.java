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

public class EditProfilePasswordActivity extends AppCompatActivity {

    EditText myOldPwd;
    EditText myNewPwd;
    EditText myNewPwd2;
    Button myButton;

    DatabaseReference databaseRef;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_password);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1));

        myOldPwd = (EditText) findViewById(R.id.profile_password_old);
        myNewPwd = (EditText) findViewById(R.id.profile_password_new);
        myNewPwd2 = (EditText) findViewById(R.id.profile_password_new_2);
        myButton = (Button) findViewById(R.id.profile_password_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pwd = myOldPwd.getText().toString().trim();
                final String pwdN = myNewPwd.getText().toString().trim();
                final String pwdN2 = myNewPwd2.getText().toString().trim();
                if (pwd.isEmpty() || pwdN.isEmpty() || pwdN2.isEmpty()) {
                    Toast.makeText(EditProfilePasswordActivity.this, "Field cannot be blank.", Toast.LENGTH_SHORT).show();
                } else if (!pwdN.equals(pwdN2)) {
                    Toast.makeText(EditProfilePasswordActivity.this, "New passwords does not match.", Toast.LENGTH_SHORT).show();
                } else {
                    AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), pwd);
                    user.reauthenticate(authCredential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user.updatePassword(pwdN);
                                        Toast.makeText(EditProfilePasswordActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                        Intent nextIntent = new Intent(EditProfilePasswordActivity.this, EditProfileActivity.class);
                                        startActivity(nextIntent);
                                    } else {
                                        Toast.makeText(EditProfilePasswordActivity.this, "Wrong Password.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}
