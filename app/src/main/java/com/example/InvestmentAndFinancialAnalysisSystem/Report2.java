package com.example.InvestmentAndFinancialAnalysisSystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Report2 extends AppCompatActivity implements Button.OnClickListener {

    private Button mReturn;

    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.report2);
        mReturn = (Button) findViewById(R.id.return_user);

        mReturn.setOnClickListener(this);
    }

    public void onClick(View view){
        if(view.getId() == R.id.return_user) {
            Intent intent5 = new Intent(Report2.this, User.class);    //切换User Activity至Login Activity
            startActivity(intent5);
            finish();
        }
    }
}