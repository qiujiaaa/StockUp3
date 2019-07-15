package com.example.stockup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class BuyerOrder extends ArrayAdapter<Order> {

    Activity context;
    List<Order> orders;

    public BuyerOrder(Activity context, List<Order> orders) {

        super(context, R.layout.order_layout, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.order_layout, null, true);

        TextView orderNum = (TextView) listViewItem.findViewById(R.id.order_layout_order_number);
        TextView orderDate = (TextView) listViewItem.findViewById(R.id.order_layout_order_date);
        TextView orderStatus = (TextView) listViewItem.findViewById(R.id.order_layout_order_status);
        TextView orderPrice = (TextView) listViewItem.findViewById(R.id.order_layout_order_price);

        final Order order = orders.get(position);

        orderNum.setText("Order #" + order.getNumber());
        orderDate.setText("Date Ordered: " + order.getDate());
        orderStatus.setText("Order Status: " + order.getStatus());
        orderPrice.setText(order.getPrice());

        return listViewItem;
    }

}
