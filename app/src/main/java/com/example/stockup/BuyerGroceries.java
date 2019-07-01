package com.example.stockup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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

        return listViewItem;
    }

}
