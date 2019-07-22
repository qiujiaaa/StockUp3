package com.example.stockup;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.Collections;
import java.util.List;

public class RunnerCompletedAssignmentDetailActivity extends AppCompatActivity {

    TextView myTVNum;
    TextView myTVDate;
    TextView myAddress;
    TextView myStatus;
    ListView myListView;
    FloatingActionButton myDeliveredButton;

    List<Groceries> list;
    Order order;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_assignment_details);

        Intent myIntent = getIntent();
        order = (Order) myIntent.getSerializableExtra("order");
        list = order.getList();

        myTVNum = (TextView) findViewById(R.id.runner_assignment_num);
        myTVDate = (TextView) findViewById(R.id.runner_assignment_date);
        myAddress = (TextView) findViewById(R.id.runner_assignment_address);
        myStatus = (TextView) findViewById(R.id.runner_assignment_status);
        myListView = (ListView) findViewById(R.id.runner_assignment_list_view);
        myDeliveredButton = (FloatingActionButton) findViewById(R.id.runner_assignment_button);

        myTVNum.setText("Assignment #" + order.getNumber());
        myTVDate.setText("Date Posted: " + order.getDate());
        myAddress.setText("Address: " + order.getAddress());
        myStatus.setText("Status: " + order.getStatus());

        myDeliveredButton.hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Collections.sort(list, new GroceriesComparator());
        RunnerAssignmentOrderList adapter = new RunnerAssignmentOrderList(RunnerCompletedAssignmentDetailActivity.this, list);
        myListView.setAdapter(adapter);
    }
}