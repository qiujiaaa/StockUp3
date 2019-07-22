package com.example.stockup;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.List;

public class BuyerOrderDetailsActivity extends AppCompatActivity {

    TextView myTVNum;
    TextView myTVDate;
    TextView myTVStatus;
    TextView myTVPrice;
    TextView myTVFinal;
    ListView myListView;
    FloatingActionButton myConfirmButton;

    List<Groceries> list;
    Order order;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_order_details);

        Intent myIntent = getIntent();
        order = (Order) myIntent.getSerializableExtra("order");
        list = order.getList();

        myTVNum = (TextView) findViewById(R.id.buyer_order_detail_order_num);
        myTVDate = (TextView) findViewById(R.id.buyer_order_detail_date);
        myTVStatus = (TextView) findViewById(R.id.buyer_order_detail_status);
        myTVPrice = (TextView) findViewById(R.id.buyer_order_detail_price);
        myTVFinal = (TextView) findViewById(R.id.buyer_order_detail_final_price);
        myListView = (ListView) findViewById(R.id.buyer_order_detail_list_view);
        myConfirmButton = (FloatingActionButton) findViewById(R.id.buyer_order_button);

        myConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToConfirm = new Intent(BuyerOrderDetailsActivity.this, BuyerCompletedOrderConfirmationActivity.class);
                goToConfirm.putExtra("order", order);
                startActivity(goToConfirm);
            }
        });

        if (!(order.getStatus().contains("confirmation"))) {
            myConfirmButton.hide();
        }

        myTVNum.setText("Order #" + order.getNumber());
        myTVDate.setText("Date Ordered: " + order.getDate());
        myTVStatus.setText("Order Status: " + order.getStatus());
        double bill = Double.valueOf(order.price.substring(1)) - 3;
        myTVPrice.setText("Total Bill: $" + String.format("%.2f", bill));
        myTVFinal.setText("Amount Paid (+$3 delivery): " + order.getPrice());


    }

    @Override
    protected void onStart() {
        super.onStart();
        Collections.sort(list, new GroceriesComparator());
        BuyerOrderDetails adapter = new BuyerOrderDetails(BuyerOrderDetailsActivity.this, list);
        myListView.setAdapter(adapter);

    }
}
