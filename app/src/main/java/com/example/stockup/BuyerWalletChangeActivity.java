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

public class BuyerWalletChangeActivity extends AppCompatActivity {

    EditText myCardNum;
    EditText myCardMonth;
    EditText myCardYear;
    EditText myCardCVC;
    EditText myPassword;
    Button myButton;

    String num;
    String month;
    String year;
    String cvc;
    String password;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_wallet_change);

        user = FirebaseAuth.getInstance().getCurrentUser();

        myCardNum = (EditText) findViewById(R.id.buyer_new_card_number);
        myCardMonth = (EditText) findViewById(R.id.buyer_new_card_month);
        myCardYear = (EditText) findViewById(R.id.buyer_new_card_year);
        myCardCVC = (EditText) findViewById(R.id.buyer_new_card_cvc);
        myPassword = (EditText) findViewById(R.id.buyer_new_card_confirm);
        myButton = (Button) findViewById(R.id.buyer_new_card_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = myCardNum.getText().toString().trim();
                month = myCardMonth.getText().toString().trim();
                if (month.length() == 1) {
                    month = "0" + month;
                }
                year = myCardYear.getText().toString().trim();
                cvc = myCardCVC.getText().toString().trim();
                password = myPassword.getText().toString().trim();

                if (num.isEmpty() || month.isEmpty() || year.isEmpty() || cvc.isEmpty()) {
                    Toast.makeText(BuyerWalletChangeActivity.this, "Fill in ALL the blanks.", Toast.LENGTH_SHORT).show();
                } else if (num.length() != 16) {
                    Toast.makeText(BuyerWalletChangeActivity.this, "Invalid card number.", Toast.LENGTH_SHORT).show();
                } else if (month.length() != 2) {
                    Toast.makeText(BuyerWalletChangeActivity.this, "Invalid expiry month.", Toast.LENGTH_SHORT).show();
                } else if (year.length() != 4) {
                    Toast.makeText(BuyerWalletChangeActivity.this, "Invalid expiry year.", Toast.LENGTH_SHORT).show();
                } else if (cvc.length() != 3) {
                    Toast.makeText(BuyerWalletChangeActivity.this, "Invalid card cvc.", Toast.LENGTH_SHORT).show();
                } else {
                    AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), password);
                    user.reauthenticate(authCredential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        String card = num + month + year + cvc;

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        DatabaseReference setCard = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myCard");
                                        setCard.setValue(card);

                                        Toast.makeText(BuyerWalletChangeActivity.this, "Wallet Updated", Toast.LENGTH_SHORT).show();
                                        Intent nextIntent = new Intent(BuyerWalletChangeActivity.this, BuyerWalletActivity.class);
                                        startActivity(nextIntent);
                                    } else {
                                        Toast.makeText(BuyerWalletChangeActivity.this, "Wrong Password.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}
