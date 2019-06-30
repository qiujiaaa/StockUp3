package com.example.stockup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class WalletActivity extends AppCompatActivity {

    LinearLayout myLLAdd;
    LinearLayout myLLDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_wallet);

        myLLAdd = (LinearLayout) findViewById(R.id.Bpayment_add);
        myLLDelete = (LinearLayout) findViewById(R.id.Bpayment_delete);

        myLLAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myLLDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
