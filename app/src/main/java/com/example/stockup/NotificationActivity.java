package com.example.stockup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    DatabaseReference dataBaseItems;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    ListView myListViewItems;

    List<Notification> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        user = FirebaseAuth.getInstance().getCurrentUser();
        dataBaseItems = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myNoti");

        myListViewItems = (ListView) findViewById(R.id.notification_list);
        list = new ArrayList<>();

        myListViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapter, View view, int position, final long id) {
                Notification notification = (Notification) adapter.getItemAtPosition(position);
                notification.update();
                FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myNoti").child(notification.getTitle()).setValue(notification);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        dataBaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {

                    String title = itemSnapShot.child("title").getValue().toString();
                    String detail = itemSnapShot.child("detail").getValue().toString();
                    boolean read = itemSnapShot.child("isRead").getValue(Boolean.class);

                    list.add(new Notification(title, detail, read));
                }

                Collections.reverse(list);
                NotificationAdapter adapter = new NotificationAdapter(NotificationActivity.this, list);
                myListViewItems.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
