package com.example.stockup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class BuyerGroceries extends ArrayAdapter<Groceries> {

    Activity context;
    List<Groceries> groceries;

    public BuyerGroceries(Activity context, List<Groceries> groceries) {

        super(context, R.layout.groceries_layout, groceries);
        this.context = context;
        this.groceries = groceries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.groceries_layout, null, true);

        TextView name = (TextView) listViewItem.findViewById(R.id.my_groceries_layout_name);
        TextView brand = (TextView) listViewItem.findViewById(R.id.my_groceries_layout_brand);
        Button add = (Button) listViewItem.findViewById(R.id.my_groceries_layout_button_add);
        TextView usage = (TextView) listViewItem.findViewById(R.id.my_groceries_layout_usage_period);

        final Groceries grocery = groceries.get(position);

        name.setText(grocery.getName());
        brand.setText(grocery.getBrand());
        usage.setText(grocery.getUsagePeriod());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myShoppingList");
                HashMap<String, Object> map = new HashMap<>();
                grocery.increaseQuantity();
                map.put(grocery.getName(), grocery);
                databaseRef.updateChildren(map);
                Toast.makeText(getContext(), "Added to Shopping Cart", Toast.LENGTH_SHORT).show();
            }
        });

        return listViewItem;
    }

}
