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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class BuyerMyGroceries extends ArrayAdapter<Groceries> {

    Activity context;
    List<Groceries> groceries;

    public BuyerMyGroceries(Activity context, List<Groceries> groceries) {

        super(context, R.layout.my_groceries_layout, groceries);
        this.context = context;
        this.groceries = groceries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.my_groceries_layout, null, true);

        TextView name = (TextView) listViewItem.findViewById(R.id.my_groceries_layout_name);
        TextView brand = (TextView) listViewItem.findViewById(R.id.my_groceries_layout_brand);
        Button add = (Button) listViewItem.findViewById(R.id.my_groceries_layout_button_add);
        ProgressBar progressBar = (ProgressBar) listViewItem.findViewById(R.id.my_groceries_layout_progress_bar);

        final Groceries grocery = groceries.get(position);

        name.setText(grocery.getName());
        brand.setText(grocery.getBrand());

        return listViewItem;
    }

}
