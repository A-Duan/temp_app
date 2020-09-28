package com.example.InvestmentAndFinancialAnalysisSystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class CompletePersonalInformation extends AppCompatActivity implements Button.OnClickListener{
    private static int temp = 0;
    private SharedPreferences information_sp;                                //保存问卷信息
    private CheckBox Q1_S1;                                            //第一个问题
    private CheckBox Q1_S2;
    private CheckBox Q1_S3;


    private CheckBox Q2_S1;                                            //第二个问题
    private CheckBox Q2_S2;
    private CheckBox Q2_S3;


    private CheckBox Q3_S1;                                            //第三个问题
    private CheckBox Q3_S2;


    private CheckBox Q4_S1;                                            //第四个问题
    private CheckBox Q4_S2;
    private CheckBox Q4_S3;


    private CheckBox Q5_S1;                                            //第五个问题
    private CheckBox Q5_S2;


    private String Income;                                              //收入
    private String type;                                                //消费类型
    private String character;                                           //性格

    private Button get_report;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_personal_information);


        Q1_S1 = (CheckBox) findViewById(R.id.Q1_S1);
        Q1_S2 = (CheckBox) findViewById(R.id.Q1_S2);
        Q1_S3 = (CheckBox) findViewById(R.id.Q1_S3);
        Q2_S1 = (CheckBox) findViewById(R.id.Q2_S1);
        Q2_S2 = (CheckBox) findViewById(R.id.Q2_S2);
        Q2_S3 = (CheckBox) findViewById(R.id.Q2_S3);
        Q3_S1 = (CheckBox) findViewById(R.id.Q3_S1);
        Q3_S2 = (CheckBox) findViewById(R.id.Q3_S2);
        Q4_S1 = (CheckBox) findViewById(R.id.Q4_S1);
        Q4_S2 = (CheckBox) findViewById(R.id.Q4_S2);
        Q4_S3 = (CheckBox) findViewById(R.id.Q4_S3);
        Q5_S1 = (CheckBox) findViewById(R.id.Q5_S1);
        Q5_S2 = (CheckBox) findViewById(R.id.Q5_S2);

        information_sp = getSharedPreferences("user_choose", 0);
        get_report = (Button) findViewById(R.id.submit);



//        if (mUserDataManager == null) {
//            mUserDataManager = new UserDataManager(this);
//            mUserDataManager.openDataBase();                              //建立本地数据库
//        }
    }
    public void onClick(View view) {
        if(view.getId() == R.id.submit) {
            result();
           if(temp == 0) {
                Intent intent_questionire_to_report = new Intent(CompletePersonalInformation.this, Report.class);
                startActivity(intent_questionire_to_report);
                finish();
             }else if(temp == 2 ) {
               Intent intent_questionire_to_report = new Intent(CompletePersonalInformation.this, Report2.class);
               startActivity(intent_questionire_to_report);
               finish();
           }
        }
    }

    public void result(){
        //各个选项的选择情况
        boolean S1_1 = information_sp.getBoolean("Q1_S1", false);
        boolean S1_2 = information_sp.getBoolean("Q1_S2", false);
        boolean S1_3 = information_sp.getBoolean("Q1_S3", false);
        boolean S2_1 = information_sp.getBoolean("Q2_S1", false);
        boolean S2_2 = information_sp.getBoolean("Q2_S2", false);
        boolean S2_3 = information_sp.getBoolean("Q2_S3", false);
        boolean S3_1 = information_sp.getBoolean("Q3_S1", false);
        boolean S3_2 = information_sp.getBoolean("Q3_S2", false);
        boolean S4_1 = information_sp.getBoolean("Q4_S1", false);
        boolean S4_2 = information_sp.getBoolean("Q4_S2", false);
        boolean S4_3 = information_sp.getBoolean("Q4_S3", false);
        boolean S5_1 = information_sp.getBoolean("Q5_S1", false);
        boolean S5_2 = information_sp.getBoolean("Q5_S2", false);
        if(S1_1){
            Income = "高收入";
            temp++;
        }else if(S1_2){
            Income = "中等收入";
            temp += 2;
        }else if(S1_3){
            Income = "低收入";
        }else if(S3_1){
            character = "激进类型用户";
        }else if(S3_2){
            character = "保守类型用户";
        }else if(S4_1){
            type = "节俭型";
        }else if(S4_2){
            type = "适中型";
        }else if(S4_3){
            type = "高消费型";
        }

        String userincome = Income;
        String usercharacter = character;
        String usertype = type;

        SharedPreferences.Editor editor = information_sp.edit();
        editor.putString("USER_INCOME",userincome);
        editor.putString("USER_CHARACTER",usercharacter);
        editor.putString("USER_TYPE",usertype);

        temp = 2;
    }

}
