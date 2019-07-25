package com.example.stockup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class RunnerAssignmentDetailConfirmationActivity extends AppCompatActivity {
    Order order;
    Button myConfirmButton;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_assignment_detail_confirmation);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.3));

        user = FirebaseAuth.getInstance().getCurrentUser();

        Intent myIntent = getIntent();
        order = (Order) myIntent.getSerializableExtra("order");
        myConfirmButton = (Button) findViewById(R.id.runner_assignment_confirm_button);

        myConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                order.setStatus("Delivered on " + date + ". Awaiting confirmation from customer.");

                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");

                // Add to Orders in runner
                userRef.child(user.getDisplayName().substring(1)).child("myOrder").child("" + (order.getNumber())).setValue(order);

                // Change order in buyer
                userRef.child(order.getBuyer()).child("myOrder").child("" + (order.getNumber())).setValue(order);

                // Add notification in buyer
                String title = "Order " + order.getNumber() + " has been delivered";
                String detail = "Order delivered on " + date + ". Please proceed to confirm delivery in \"My Orders\".";
                Notification noti = new Notification(title, detail, false);
                userRef.child(order.getBuyer()).child("myNoti").child(title).setValue(noti);

                Toast.makeText(RunnerAssignmentDetailConfirmationActivity.this, "Assignment status updated.", Toast.LENGTH_SHORT).show();
                Intent goToMyAssignments = new Intent(RunnerAssignmentDetailConfirmationActivity.this, RunnerAssignmentActivity.class);

                startActivity(goToMyAssignments);
            }
        });

    }
}