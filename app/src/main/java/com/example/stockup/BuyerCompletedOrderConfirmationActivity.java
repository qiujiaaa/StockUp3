package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BuyerCompletedOrderConfirmationActivity extends AppCompatActivity {

    Order order;
    Button myConfirmButton;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_completed_order_confirmation);

        user = FirebaseAuth.getInstance().getCurrentUser();

        Intent myIntent = getIntent();
        order = (Order) myIntent.getSerializableExtra("order");
        myConfirmButton = (Button) findViewById(R.id.buyer_completed_order_confirm_button);

        myConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setStatus("Order Completed");

                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");

                // Change Orders in buyer
                userRef.child(user.getDisplayName().substring(1)).child("myOrder").child("" + (order.getNumber())).setValue(order);

                // Change order in runner
                userRef.child(order.getRunner()).child("myOrder").child("" + (order.getNumber())).setValue(order);

                // Add notification in runner
                String title = "Order " + order.getNumber() + " has been completed";
                String detail = "Money added in wallet.";
                Notification noti = new Notification(title, detail, false);
                userRef.child(order.getRunner()).child("myNoti").child(title).setValue(noti);

                Toast.makeText(BuyerCompletedOrderConfirmationActivity.this, "Assignment status updated.", Toast.LENGTH_SHORT).show();
                Intent goToMyAssignments = new Intent(BuyerCompletedOrderConfirmationActivity.this, BuyerOrderActivity.class);

                startActivity(goToMyAssignments);
            }
        });

    }



}
