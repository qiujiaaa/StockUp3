package com.example.stockup;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class BuyerShoppingList extends ArrayAdapter<Groceries> {

    Activity context;
    List<Groceries> shoppingList;

    public BuyerShoppingList(Activity context, List<Groceries> shoppingList) {

        super(context, R.layout.shopping_list_layout, shoppingList);
        this.context = context;
        this.shoppingList = shoppingList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.shopping_list_layout, null, true);

        TextView name = (TextView) listViewItem.findViewById(R.id.shopping_list_layout_name);
        TextView brand = (TextView) listViewItem.findViewById(R.id.shopping_list_layout_brand);
        Button subtract = (Button) listViewItem.findViewById(R.id.shopping_list_layout_button_s);
        TextView quantity = (TextView) listViewItem.findViewById(R.id.shopping_list_layout_quantity);
        Button add = (Button) listViewItem.findViewById(R.id.shopping_list_layout_button_a);
        TextView price = (TextView) listViewItem.findViewById(R.id.shopping_list_layout_price);

        final Groceries grocery = shoppingList.get(position);

        name.setText(grocery.getName());
        brand.setText(grocery.getBrand());
        quantity.setText("" + grocery.getQuantity());
        double finalPrice =  grocery.getPrice() * grocery.getQuantity();
        price.setText("$" + String.format("%.2f", finalPrice));

        return listViewItem;
    }

}
