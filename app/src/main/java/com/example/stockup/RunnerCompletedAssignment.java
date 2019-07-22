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

public class RunnerCompletedAssignment extends ArrayAdapter<Order> {

    Activity context;
    List<Order> orders;

    public RunnerCompletedAssignment (Activity context, List<Order> orders) {

        super(context, R.layout.completed_assignment_layout, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.completed_assignment_layout, null, true);

        TextView number = (TextView) listViewItem.findViewById(R.id.completed_ass_layout_ass_num);
        TextView date = (TextView) listViewItem.findViewById(R.id.completed_ass_layout_ass_date);

        final Order order = orders.get(position);

        number.setText("Assignment #" + order.getNumber());
        date.setText("Date Completed: " + order.getStatus().substring(13, 23));

        return listViewItem;
    }

}