package com.example.stockup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemList extends ArrayAdapter<Item> {

    Activity context;
    List<Item> itemList;

    public ItemList(Activity context, List<Item> itemList) {

        super(context, R.layout.item_list_layout, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.item_list_layout, null, true);

        TextView itemNameTextView = (TextView) listViewItem.findViewById(R.id.item_name);
        TextView itemBrandTextView = (TextView) listViewItem.findViewById(R.id.item_brand);
        TextView itemPriceTextView = (TextView) listViewItem.findViewById(R.id.item_price);

        Item item = itemList.get(position);

        itemNameTextView.setText(item.getName());
        itemBrandTextView.setText(item.getBrand());
        itemPriceTextView.setText("$" + String.format("%.2f", item.getPrice()));

        return listViewItem;
    }
}
