package com.example.stockup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BuyerMyGroceries extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_my_groceries);

        TextView myTextViewGroceries= (TextView) findViewById(R.id.textview_my_groceries);



    }
}
