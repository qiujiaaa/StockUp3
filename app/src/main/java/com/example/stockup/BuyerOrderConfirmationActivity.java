package com.example.stockup;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class BuyerOrderConfirmationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_order_confirmation);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.6), (int) (height * 0.4));


        Toast.makeText(BuyerOrderConfirmationActivity.this, "Order successfully sent", Toast.LENGTH_SHORT).show();

    }


}
