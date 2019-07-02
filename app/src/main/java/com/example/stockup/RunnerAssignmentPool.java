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

public class RunnerAssignmentPool extends ArrayAdapter<Order> {

    Activity context;
    List<Order> orders;

    public RunnerAssignmentPool(Activity context, List<Order> orders) {

        super(context, R.layout.assignment_pool_layout, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.assignment_pool_layout, null, true);

        TextView number = (TextView) listViewItem.findViewById(R.id.ass_pool_layout_ass_num);
        TextView date = (TextView) listViewItem.findViewById(R.id.ass_pool_layout_ass_date);
        TextView address = (TextView) listViewItem.findViewById(R.id.ass_pool_layout_ass_addres);

        final Order order = orders.get(position);

        number.setText("Address #" + order.getNumber());
        date.setText("Date Posted: " + order.getDate());
        address.setText("Address: " + order.getAddress());

        return listViewItem;
    }

}
