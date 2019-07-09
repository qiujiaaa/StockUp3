package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register2Activity extends AppCompatActivity {

    EditText myCardNum;
    EditText myCardMonth;
    EditText myCardYear;
    EditText myCardCVC;
    EditText myAddress;
    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        myCardNum = (EditText) findViewById(R.id.buyer_card_number);
        myCardMonth = (EditText) findViewById(R.id.buyer_card_month);
        myCardYear = (EditText) findViewById(R.id.buyer_card_year);
        myCardCVC = (EditText) findViewById(R.id.buyer_card_cvc);
        myAddress = (EditText) findViewById(R.id.buyer_address);
        myButton = (Button) findViewById(R.id.buyer_create_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = myCardNum.getText().toString().trim();
                String month = myCardMonth.getText().toString().trim();
                if (month.length() == 1) {
                    month = "0" + month;
                }
                String year = myCardYear.getText().toString().trim();
                String cvc = myCardCVC.getText().toString().trim();
                String address = myAddress.getText().toString().trim();

                if (num.isEmpty() | month.isEmpty() || year.isEmpty() || cvc.isEmpty() || address.isEmpty()) {
                    Toast.makeText(Register2Activity.this, "Fill in ALL the blanks.", Toast.LENGTH_SHORT).show();
                } else if (num.length() != 16) {
                    Toast.makeText(Register2Activity.this, "Invalid card number.", Toast.LENGTH_SHORT).show();
                } else if (month.length() != 2) {
                    Toast.makeText(Register2Activity.this, "Invalid expiry month.", Toast.LENGTH_SHORT).show();
                } else if (year.length() != 4) {
                    Toast.makeText(Register2Activity.this, "Invalid expiry year.", Toast.LENGTH_SHORT).show();
                } else if (cvc.length() != 3) {
                    Toast.makeText(Register2Activity.this, "Invalid card cvc.", Toast.LENGTH_SHORT).show();
                } else {
                    String card = num + month + year + cvc;

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    DatabaseReference setCard = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myCard");
                    setCard.child("real").setValue(card);
                    DatabaseReference setAddress = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myAddress");
                    setAddress.child("real").setValue(address);

                    Toast.makeText(Register2Activity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent nextIntent = new Intent(Register2Activity.this, MainActivity.class);
                    startActivity(nextIntent);
                }
            }
        });
    }
}
