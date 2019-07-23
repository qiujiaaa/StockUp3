package com.example.stockup;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification> {

    Activity context;
    List<Notification> notificationList;
    double pay;

    public NotificationAdapter(Activity context, List<Notification> notificationList) {

        super(context, R.layout.notification_layout, notificationList);
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.notification_layout, null, true);

        TextView notifNameTextView = (TextView) listViewItem.findViewById(R.id.notification_title);
        TextView notifDetailTextView = (TextView) listViewItem.findViewById(R.id.notification_details);
        Button notifButton = (Button) listViewItem.findViewById(R.id.notification_button);
        pay = 0;

        Notification notification = notificationList.get(position);

        notifNameTextView.setText(notification.getTitle().substring(0, 6) + "#" + notification.getTitle().substring(6));
        notifDetailTextView.setText(notification.getDetail());

        if (notification.isRead()) {
            notifButton.setVisibility(View.GONE);
        }

        return listViewItem;
    }


}
