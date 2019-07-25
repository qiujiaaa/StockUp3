package com.example.stockup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RunnerWalletChangeActivity extends AppCompatActivity {

    EditText myNum;
    EditText myPassword;
    Button myButton;

    String num;
    String password;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_wallet_change);

        user = FirebaseAuth.getInstance().getCurrentUser();

        myNum = (EditText) findViewById(R.id.runner_new_number);
        myPassword = (EditText) findViewById(R.id.runner_new_number_confirm);
        myButton = (Button) findViewById(R.id.runner_new_number_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = myNum.getText().toString().trim();
                password = myPassword.getText().toString().trim();

                if (num.isEmpty()) {
                    Toast.makeText(RunnerWalletChangeActivity.this, "Fill in your new number.", Toast.LENGTH_SHORT).show();
                } else if (num.length() != 8) {
                    Toast.makeText(RunnerWalletChangeActivity.this, "Invalid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), password);
                    user.reauthenticate(authCredential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        DatabaseReference setCard = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myNumber");
                                        setCard.setValue(num);

                                        Toast.makeText(RunnerWalletChangeActivity.this, "Wallet Updated", Toast.LENGTH_SHORT).show();
                                        Intent nextIntent = new Intent(RunnerWalletChangeActivity.this, RunnerWalletActivity.class);
                                        startActivity(nextIntent);
                                    } else {
                                        Toast.makeText(RunnerWalletChangeActivity.this, "Wrong Password.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}
