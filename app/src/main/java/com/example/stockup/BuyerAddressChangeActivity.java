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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuyerAddressChangeActivity extends AppCompatActivity {

    EditText myAddress;
    EditText myPassword;
    Button myButton;

    String address;
    String password;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_address_change);

        user = FirebaseAuth.getInstance().getCurrentUser();

        myAddress = (EditText) findViewById(R.id.buyer_new_address);
        myPassword = (EditText) findViewById(R.id.buyer_new_address_confirm);
        myButton = (Button) findViewById(R.id.buyer_new_address_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = myAddress.getText().toString().trim();
                password = myPassword.getText().toString().trim();
                if (address.isEmpty() || password.isEmpty()) {
                    Toast.makeText(BuyerAddressChangeActivity.this, "Fill in ALL the blanks.", Toast.LENGTH_SHORT).show();
                } else {
                    AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), password);
                    user.reauthenticate(authCredential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        DatabaseReference setCard = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myAddress");
                                        setCard.child("real").setValue(address);

                                        Toast.makeText(BuyerAddressChangeActivity.this, "Address Updated", Toast.LENGTH_SHORT).show();
                                        Intent nextIntent = new Intent(BuyerAddressChangeActivity.this, BuyerAddressActivity.class);
                                        startActivity(nextIntent);
                                    } else {
                                        Toast.makeText(BuyerAddressChangeActivity.this, "Wrong Password.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
