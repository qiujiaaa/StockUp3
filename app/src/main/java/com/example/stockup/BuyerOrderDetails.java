package com.example.stockup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class BuyerOrderDetails extends AppCompatActivity {

    TextView myTVNum;
    TextView myTVDate;
    TextView myTVStatus;
    TextView myTVPrice;
    TextView myTVFinal;
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_order_details);

        myTVNum = (TextView) findViewById(R.id.buyer_order_detail_order_num);
        myTVDate = (TextView) findViewById(R.id.buyer_order_detail_date);
        myTVStatus = (TextView) findViewById(R.id.buyer_order_detail_status);
        myTVPrice = (TextView) findViewById(R.id.buyer_order_detail_price);
        myTVFinal = (TextView) findViewById(R.id.buyer_order_detail_final_price);
        myListView = (ListView) findViewById(R.id.buyer_order_detail_list_view);



    }
}
