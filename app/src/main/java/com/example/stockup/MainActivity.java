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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText myTextViewEmail;
    EditText myTextViewPassword;
    Button myButtonLogin;
    TextView myTextViewRegister;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextViewEmail = (EditText) findViewById(R.id.edittext_email);
        myTextViewPassword = (EditText) findViewById(R.id.edittext_password);
        myButtonLogin = (Button) findViewById(R.id.button_login);
        myTextViewRegister = (TextView) findViewById(R.id.textview_register);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users");

        myTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        myButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = myTextViewEmail.getText().toString().trim();
                String password = myTextViewPassword.getText().toString().trim();
                validate(email, password);
            }
        });
    }

    protected void validate(String email, String pwd) {

        final String theEmail = email;
        firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String role = user.getDisplayName().substring(0, 1);
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent goToHome;
                    if (role.equals("B")) {
                        goToHome = new Intent(MainActivity.this, BuyerHome.class);
                    } else {
                        goToHome = new Intent(MainActivity.this, RunnerHome.class);
                    }
                    startActivity(goToHome);

                } else {
                    Toast.makeText(MainActivity.this, "Wrong username or password. Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
