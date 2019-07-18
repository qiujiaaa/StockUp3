package com.example.stockup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RunnerAssignmentOrderList extends ArrayAdapter<Groceries> {

    Activity context;
    List<Groceries> orders;

    public RunnerAssignmentOrderList(Activity context, List<Groceries> orders) {

        super(context, R.layout.runner_assignment_details_list_layout, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.runner_assignment_details_list_layout, null, true);

        TextView name = (TextView) listViewItem.findViewById(R.id.runner_assignment_order_name);
        TextView quantity = (TextView) listViewItem.findViewById(R.id.runner_assignment_order_quantity);
        TextView brand = (TextView) listViewItem.findViewById(R.id.runner_assignment_order_brand);

        final Groceries grocery = orders.get(position);

        name.setText(grocery.getName());
        quantity.setText("" + grocery.getQuantity());
        brand.setText(grocery.getBrand());

        return listViewItem;
    }

}
