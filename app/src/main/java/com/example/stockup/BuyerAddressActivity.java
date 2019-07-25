package com.example.stockup;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class BuyerAddressActivity extends AppCompatActivity {

    LinearLayout myLLAddAddress;
    FloatingActionButton myButtonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_address);

        myLLAddAddress = (LinearLayout) findViewById(R.id.Baddress_add);
        myButtonHome = (FloatingActionButton) findViewById(R.id.Baddress_home_button);

        myButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(BuyerAddressActivity.this, BuyerHome.class);
                startActivity(goToHome);
            }
        });

        myLLAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPage = new Intent(BuyerAddressActivity.this, BuyerAddressChangeActivity.class);
                startActivity(goToPage);
            }
        });
    }
}
