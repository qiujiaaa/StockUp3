package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

public class RunnerAssignmentPoolDetailsConfirmationActivity extends AppCompatActivity {

    Order order;
    Button myConfirmButton;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_assignment_pool_details_confirmation);

        user = FirebaseAuth.getInstance().getCurrentUser();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.75), (int) (height * 0.25));

        Intent myIntent = getIntent();
        order = (Order) myIntent.getSerializableExtra("order");
        myConfirmButton = (Button) findViewById(R.id.runner_assignment_detail_assignment_confirm);

        myConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                order.setStatus("Picked up on " + date + ". To be delivered within 48 hours.");

                // Remove from Orders Pool in Firebase
                DatabaseReference ordersDataRef = FirebaseDatabase.getInstance().getReference("orders");
                ordersDataRef.child("" + order.getNumber()).removeValue();

                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");

                // Add to Orders in runner
                userRef.child(user.getDisplayName().substring(1)).child("myOrder").child("" + (order.getNumber())).setValue(order);

                // Change in Orders in buyer
                userRef.child(order.getBuyer()).child("myOrder").child("" + (order.getNumber())).setValue(order);

                Toast.makeText(RunnerAssignmentPoolDetailsConfirmationActivity.this, "Added to My Assignments.", Toast.LENGTH_SHORT).show();
                Intent goToMyAssignments = new Intent(RunnerAssignmentPoolDetailsConfirmationActivity.this, RunnerAssignmentActivity.class);
                startActivity(goToMyAssignments);
            }
        });

    }
}
