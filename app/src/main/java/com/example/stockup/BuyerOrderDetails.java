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

public class BuyerOrderDetails extends ArrayAdapter<Groceries> {

    Activity context;
    List<Groceries> orders;

    public BuyerOrderDetails(Activity context, List<Groceries> orders) {

        super(context, R.layout.order_details_layout, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.order_details_layout, null, true);

        TextView name = (TextView) listViewItem.findViewById(R.id.order_detail_item_name);
        TextView quantity = (TextView) listViewItem.findViewById(R.id.order_detail_item_quantity);
        TextView price = (TextView) listViewItem.findViewById(R.id.order_detail_item_price);

        final Groceries grocery = orders.get(position);

        name.setText(grocery.getName());
        quantity.setText("Qty: " + grocery.getQuantity());
        double totalPrice = grocery.getPrice() * grocery.getQuantity();
        price.setText("$" + String.format("%.2f", totalPrice));

        return listViewItem;
    }

}
