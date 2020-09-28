package com.example.InvestmentAndFinancialAnalysisSystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NearlyPlan extends AppCompatActivity implements Button.OnClickListener {
    private Button getNearlyPlan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearlyplan);
        getNearlyPlan = (Button) findViewById(R.id.return_user2);
        getNearlyPlan.setOnClickListener(this);
    }

    public void onClick(View view) {
        Intent intent_Nearly_to_User = new Intent(NearlyPlan.this, User.class);    //切换Login Activity至User Activity
        startActivity(intent_Nearly_to_User);
        finish();
    }

}




