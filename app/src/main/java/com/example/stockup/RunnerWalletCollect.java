package com.example.stockup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RunnerWalletCollect extends ArrayAdapter<Order> {

    Activity context;
    List<Order> list;

    public RunnerWalletCollect(Activity context, List<Order> list) {

        super(context, R.layout.payout_layout, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.payout_layout, null, true);

        TextView number = (TextView) listViewItem.findViewById(R.id.payout_ass_num);
        TextView date = (TextView) listViewItem.findViewById(R.id.payout_ass_date);

        Order order = list.get(position);

        number.setText("Order #" + order.getNumber());
        date.setText("Date Completed: " + order.getStatus().substring(13, 23));

        return listViewItem;
    }
}
