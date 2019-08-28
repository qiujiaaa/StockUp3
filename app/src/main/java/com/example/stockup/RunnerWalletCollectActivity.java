package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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

public class RunnerWalletCollectActivity extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference databaseReference;

    Button myButton;
    ListView myListView;

    double money;
    List<Order> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_wallet_collect);

        money = (double) getIntent().getSerializableExtra("money");
        list = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myOrder");

        myButton = (Button) findViewById(R.id.runner_collect_payout);
        myListView = (ListView) findViewById(R.id.runner_collect_payout_list);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;

                for (Order o: list) {
                    count++;
                    o.setCollected();
                    databaseReference.child("" + o.getNumber()).setValue(o);
                }

                money += 3.00 * count;
                FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1)).child("myWallet").setValue(money);

                if (count == 0) {
                    Toast.makeText(RunnerWalletCollectActivity.this, "No payout available.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RunnerWalletCollectActivity.this, "Payout collected", Toast.LENGTH_SHORT).show();
                }
                Intent goToWallet = new Intent(RunnerWalletCollectActivity.this, RunnerWalletActivity.class);
                startActivity(goToWallet);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    String status = ds.child("status").getValue().toString();
                    boolean collected = ds.child("collected").getValue(Boolean.class);
                    if (status.contains("Delivered") && collected == false) {
                        list.add(ds.getValue(Order.class));
                    }

                }

                Collections.sort(list, new OrderComparator());
                RunnerWalletCollect adapter = new RunnerWalletCollect(RunnerWalletCollectActivity.this, list);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
