package com.example.stockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RunnerWalletWithdrawActivity extends AppCompatActivity {

    Button myButtonConfirm;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_wallet_withdraw);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.75), (int) (height * 0.25));

        user = FirebaseAuth.getInstance().getCurrentUser();
        myButtonConfirm = (Button) findViewById(R.id.runner_withdraw_confirm_button);

        myButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getDisplayName().substring(1));
                ref.child("myWallet").setValue(0.0);

                Toast.makeText(RunnerWalletWithdrawActivity.this, "Withdrawal completed.", Toast.LENGTH_SHORT).show();
                Intent goToWallet = new Intent(RunnerWalletWithdrawActivity.this, RunnerWalletActivity.class);
                startActivity(goToWallet);
            }
        });
    }
}
