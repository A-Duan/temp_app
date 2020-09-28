package com.example.InvestmentAndFinancialAnalysisSystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.InvestmentAndFinancialAnalysisSystem.Login;
import com.example.InvestmentAndFinancialAnalysisSystem.R;

public class User extends AppCompatActivity implements Button.OnClickListener{
    private Button mReturnButton;
    private Button mComplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        mReturnButton = (Button)findViewById(R.id.returnback);
        mReturnButton.setOnClickListener(this);

        mComplete = (Button)findViewById(R.id.CompletePersonalInformation);
        mComplete.setOnClickListener(this);
    }
    /*public void back_to_login(View view) {
        //setContentView(R.layout.login);
        Intent intent3 = new Intent(User.this, Login.class) ;
        startActivity(intent3);
        finish();

    }*/
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.returnback:
                Intent intent3 = new Intent(User.this, Login.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.CompletePersonalInformation:
                Intent intent4 = new Intent(User.this, CompletePersonalInformation.class);
                startActivity(intent4);
                //finish();
                break;
            case R.id.getNearlyPlan:
                Intent intent7 = new Intent(User.this, NearlyPlan.class);
                startActivity(intent7);
                //finish();
                break;
        }
    }

}
