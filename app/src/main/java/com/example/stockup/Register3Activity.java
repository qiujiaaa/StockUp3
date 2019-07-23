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

public class Register3Activity extends AppCompatActivity {

    EditText myNum;
    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        myNum = (EditText) findViewById(R.id.runner_number);
        myButton = (Button) findViewById(R.id.runner_create_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = myNum.getText().toString().trim();

                if (num.isEmpty()) {
                    Toast.makeText(Register3Activity.this, "Fill in your number.", Toast.LENGTH_SHORT).show();
                } else if (num.length() != 8) {
                    Toast.makeText(Register3Activity.this, "Invalid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    DatabaseReference setNum= FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myNumber");
                    setNum.setValue(num);

                    Toast.makeText(Register3Activity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent nextIntent = new Intent(Register3Activity.this, MainActivity.class);
                    startActivity(nextIntent);
                }
            }
        });
    }
}
