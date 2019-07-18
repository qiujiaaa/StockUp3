package com.example.stockup;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.Collections;
import java.util.List;

public class RunnerAssignmentDetailActivity extends AppCompatActivity {

    TextView myTVNum;
    TextView myTVDate;
    TextView myAddress;
    TextView myStatus;
    ListView myListView;
    FloatingActionButton myAddButton;

    List<Groceries> list;
    Order order;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_assignment_pool_details);

        Intent myIntent = getIntent();
        order = (Order) myIntent.getSerializableExtra("order");
        list = order.getList();

        myTVNum = (TextView) findViewById(R.id.runner_assignment_detail_assignment_num);
        myTVDate = (TextView) findViewById(R.id.runner_assignment_detail_date);
        myAddress = (TextView) findViewById(R.id.runner_assignment_detail_address);
        myStatus = (TextView) findViewById(R.id.runner_assignment_detail_status);
        myListView = (ListView) findViewById(R.id.runner_assignment_detail_list_view);
        myAddButton = (FloatingActionButton) findViewById(R.id.runner_assignment_order_detail_add_button);

        myTVNum.setText("Assignment #" + order.getNumber());
        myTVDate.setText("Date Posted: " + order.getDate());
        myAddress.setText("Address: " + order.getAddress());
        myStatus.setText("Status: " + order.getStatus());

        myAddButton.hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Collections.sort(list, new GroceriesComparator());
        RunnerAssignmentOrderList adapter = new RunnerAssignmentOrderList(RunnerAssignmentDetailActivity.this, list);
        myListView.setAdapter(adapter);
    }
}
