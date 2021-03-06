package com.example.InvestmentAndFinancialAnalysisSystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//登录功能
public class Login extends AppCompatActivity implements Button.OnClickListener{
    //public static final String EXTRA_MESSAGE = "com.example.InvestmentAndFinancialAnalysisSystem.MESSAGE";
    public int pwdresetFlag = 0;
    private EditText mAccount;              //用户名编辑
    private EditText mPwd;                            //密码编辑
    private Button mRegisterButton;                   //注册按钮
    private Button mLoginButton;                      //登录按钮
    private CheckBox mRememberCheck;                  //查看该选项是否选中
    private SharedPreferences login_sp;                //用来保存应用程序的各种配置信息，
                                                       //其本质是一个以“键-值”对的方式保存数据的xml文件，
                                                       //其文件保存在/data/data//shared_prefs目录下。

    private String userNameValue,passwordValue;

    private View loginView;                           //登录界面
    private View loginSuccessView;                    //登录成功的提示
    private TextView loginSuccessShow;                //文本框
    private TextView mChangepwdText;
    private UserDataManager mUserDataManager;         //用户数据管理类


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //通过id找到相应的控件
        mAccount = (EditText) findViewById(R.id.login_Input_account);
        mPwd = (EditText)findViewById(R.id.login_Input_pwd);
        mRegisterButton = (Button) findViewById(R.id.login_btn_register);
        mLoginButton = (Button) findViewById(R.id.login_btn_login);
        loginView = findViewById(R.id.login_view);
        loginSuccessView = findViewById(R.id.login_success_view);
        loginSuccessShow = (TextView) findViewById(R.id.login_success_show);


        mRememberCheck = (CheckBox) findViewById(R.id.Login_Remember);          //记住密码功能

        login_sp = getSharedPreferences("userInfo",0);
        String name = login_sp.getString("USER_NAME","");
        String pwd =login_sp.getString("PASSWORD","");
        boolean choseRemeber = login_sp.getBoolean("mRememberCheck",false);

        if(choseRemeber){
            mAccount.setText(name);
            mPwd.setText(pwd);
            mRememberCheck.setChecked(true);
        }

        mRegisterButton.setOnClickListener(this);           //采用OnClickListener方法设置不同按钮按下之后的监听事件
        mLoginButton.setOnClickListener(this);


        ImageView image = (ImageView) findViewById(R.id.logo);             //使用ImageView显示logo
        image.setImageResource(R.drawable.logo);


        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_register:
                Intent intent_Login_to_Register = new Intent(Login.this, Register.class);    //切换Login Activity至User Activity
                startActivity(intent_Login_to_Register);
                finish();
                break;
            case R.id.login_btn_login:                              //登录界面的登录按钮
                login();
                break;
        }
    }

    public void login() {                                              //登录按钮监听事件
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = mPwd.getText().toString().trim();
            SharedPreferences.Editor editor =login_sp.edit();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result==1){                                             //返回1说明用户名和密码均正确
                //保存用户名和密码
                editor.putString("USER_NAME", userName);
                editor.putString("PASSWORD", userPwd);

                //是否记住密码
                if(mRememberCheck.isChecked()){
                    editor.putBoolean("mRememberCheck", true);
                }else{
                    editor.putBoolean("mRememberCheck", false);
                }
                editor.commit();

                Intent intent = new Intent(Login.this,User.class) ;    //切换Login Activity至User Activity
                startActivity(intent);
                finish();
                Toast.makeText(this, "登陆成功！",Toast.LENGTH_SHORT).show();//登录成功提示
            }else if(result==0){
                Toast.makeText(this, "登陆失败！请输入正确的用户名和密码",Toast.LENGTH_SHORT).show();  //登录失败提示
            }
        }
    }
    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, "用户名为空，请重新输入！",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码为空，请重新输入！",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {                                 //创建用户数据库
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }           //销毁用户数据库

    @Override
    protected void onPause() {                                  //关闭用户数据库
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }

}
