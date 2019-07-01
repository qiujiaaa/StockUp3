package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyerAddToGroceriesActivity extends AppCompatActivity {

    Item item;

    TextView myTextViewName;
    EditText myEditTextUsagePeriod;
    Button myButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_add_to_groceries);

        Intent myIntent = getIntent();
        item = (Item) myIntent.getSerializableExtra("item");

        myTextViewName = (TextView) findViewById(R.id.add_to_groceries_name);
        myEditTextUsagePeriod = (EditText) findViewById(R.id.add_to_groceries_usage_period);
        myButtonAdd = (Button) findViewById(R.id.add_to_groceries_button);

        myTextViewName.setText(item.getName());

        myButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usagePeriod = myEditTextUsagePeriod.getText().toString().trim();
                // Add to database
                Groceries groceries = new Groceries(item, 0, usagePeriod);
                Toast.makeText(BuyerAddToGroceriesActivity.this, "Added to Groceries List", Toast.LENGTH_LONG).show();
                Intent goBack = new Intent(BuyerAddToGroceriesActivity.this, ItemListActivity.class);
                startActivity(goBack);
            }
        });

    }
}
