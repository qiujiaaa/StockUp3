package com.example.stockup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RunnerWalletActivity extends AppCompatActivity {

    TextView myTextViewBalance;
    LinearLayout myLLWithdraw;
    LinearLayout myLLManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_wallet);

        myTextViewBalance = (TextView) findViewById(R.id.Rwallet_balance);
        myLLWithdraw = (LinearLayout) findViewById(R.id.Rwallet_withdraw);
        myLLManage = (LinearLayout) findViewById(R.id.Rwallet_manage);

        //Set as balance
        //myTextViewBalance.setText();

        myLLWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myLLManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
