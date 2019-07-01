package com.example.stockup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    CheckBox myCheckBoxBuyer;
    CheckBox myCheckBoxRunner;
    EditText myTextViewEmail;
    EditText myTextViewUsername;
    EditText myTextViewPassword;
    EditText myTextViewConfirmPassword;
    Button myButtonRegister;
    TextView myTextViewLogin;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference("users");

        myCheckBoxBuyer= (CheckBox) findViewById(R.id.buyer);
        myCheckBoxRunner= (CheckBox) findViewById(R.id.runner);
        myTextViewEmail = (EditText) findViewById(R.id.edittext_new_email);
        myTextViewUsername = (EditText) findViewById(R.id.edittext_new_username);
        myTextViewPassword = (EditText) findViewById(R.id.edittext_new_password);
        myTextViewConfirmPassword = (EditText) findViewById(R.id.edittext_confirm_password);
        myButtonRegister = (Button) findViewById(R.id.button_register);
        myTextViewLogin = (TextView) findViewById(R.id.textview_login_here);

        myTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });

        myButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean buyer = myCheckBoxBuyer.isChecked();
                boolean runner = myCheckBoxRunner.isChecked();
                if ((buyer && runner) || (!buyer && !runner)) {
                    Toast.makeText(RegisterActivity.this, "Choose only ONE role.", Toast.LENGTH_SHORT).show();
                } else {
                    if (validate()) {
                        String email = myTextViewEmail.getText().toString().trim();
                        String user = myTextViewUsername.getText().toString().trim();
                        String pwd = myTextViewPassword.getText().toString().trim();
                        String cfmpwd = myTextViewConfirmPassword.getText().toString().trim();
                        if (!pwd.equals(cfmpwd)) {
                            Toast.makeText(RegisterActivity.this, "Password does not match. Try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            final boolean isBuyer = buyer;
                            final String theUser = user;
                            final String theEmail = email;
                            firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        User newUser;
                                        if (isBuyer) {
                                            newUser = new User(theUser, theEmail, "buyer");
                                        } else {
                                            newUser = new User(theUser, theEmail, "runner");
                                        }
                                        databaseRef.child(theUser).setValue(newUser);
                                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(loginIntent);
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });
                        };
                    }

                }


            }
        });
    }

    protected boolean validate() {

        boolean result = false;
        String email = myTextViewEmail.getText().toString().trim();
        String user = myTextViewUsername.getText().toString().trim();
        String pwd = myTextViewPassword.getText().toString().trim();
        String cfmpwd = myTextViewConfirmPassword.getText().toString().trim();

        if (email.isEmpty() || user.isEmpty() || pwd.isEmpty() || cfmpwd.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }
}
