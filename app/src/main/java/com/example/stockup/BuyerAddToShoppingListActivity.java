package com.example.stockup;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyerAddToShoppingListActivity extends Activity {

    Item item;

    TextView myTextViewName;
    EditText myEditTextQuantity;
    Button myButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_add_to_shopping_list);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.65));

        Intent myIntent = getIntent();
        item = (Item) myIntent.getSerializableExtra("item");

        myTextViewName = (TextView) findViewById(R.id.add_to_shopping_list_name);
        myEditTextQuantity = (EditText) findViewById(R.id.add_to_shopping_list_quantity);
        myButtonAdd = (Button) findViewById(R.id.add_to_shopping_list_button);

        myTextViewName.setText(item.getName());

        myButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = myEditTextQuantity.getText().toString().trim();
                // Add to database

                if (quantity.isEmpty()) {
                    Toast.makeText(BuyerAddToShoppingListActivity.this, "Enter quantity", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BuyerAddToShoppingListActivity.this, "Added to Shopping List", Toast.LENGTH_LONG).show();
                    Intent goBack = new Intent(BuyerAddToShoppingListActivity.this, ItemListActivity.class);
                    startActivity(goBack);
                }
            }
        });

    }
}
