package com.example.InvestmentAndFinancialAnalysisSystem;

public class UserData {
    private String userName;                  //用户名
    private String userPwd;                   //用户密码
    private int userId;                       //用户ID号
    private String userIncome;                //用户收入类型
    private String userCharacter;             //用户消费性格
    private String userType;                  //用户消费种类
    public int pwdresetFlag=0;
    //获取用户名
    public String getUserName() {             //获取用户名
        return userName;
    }
    //设置用户名
    public void setUserName(String userName) {  //输入用户名
        this.userName = userName;
    }
    //获取用户密码
    public String getUserPwd() {                //获取用户密码
        return userPwd;
    }
    //设置用户密码
    public void setUserPwd(String userPwd) {     //输入用户密码
        this.userPwd = userPwd;
    }
    //获取用户id
    public int getUserId() {                   //获取用户ID号
        return userId;
    }
    //设置用户id
    public void setUserId(int userId) {       //设置用户ID号
        this.userId = userId;
    }

    public String getUserIncome(){
        return userIncome;
    }

    public void setUserIncome(String userIncome){
        this.userIncome = userIncome;
    }

    public String getUserCharacter(){
        return userCharacter;
    }

    public void setUserCharacter(String userCharacter){
        this.userCharacter = userCharacter;
    }

    public String getUserType(){
        return userType;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }


   /* public UserData(String userName, String userPwd, int userId) {    //用户信息
        super();
        this.userName = userName;
        this.userPwd = userPwd;
        this.userId = userId;
    }*/

    public UserData(String userName, String userPwd) {  //这里只采用用户名和密码
        super();
        this.userName = userName;
        this.userPwd = userPwd;
    }

}
