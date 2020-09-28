package com.example.InvestmentAndFinancialAnalysisSystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDataManager {             //用户数据管理类
    //一些宏定义和声明
    private static final String TAG = "UserDataManager";
    private static final String DB_NAME = "user_data";
    private static final String TABLE_NAME = "users";
    public static final String ID = "_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PWD = "user_pwd";
    public static final String USER_INCOME = "user_income";
    public static final String USER_CHARACTER = "user_character";
    public static final String USER_TYPE = "user_type";


    //    public static final String SILENT = "silent";
//    public static final String VIBRATE = "vibrate";
    private static final int DB_VERSION = 2;
    private Context mContext = null;

    //创建用户book表
    private static  final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " integer primary key," + USER_NAME + " varchar,"
            + USER_PWD + " varchar," + USER_INCOME + " String," + USER_CHARACTER + " String,"
            + USER_TYPE + "String" + " );";

    private SQLiteDatabase mSQLiteDatabase = null;
    private DataBaseManagementHelper mDatabaseHelper = null;

    //DataBaseManagementHelper继承自SQLiteOpenHelper
    private static class DataBaseManagementHelper extends SQLiteOpenHelper {

        DataBaseManagementHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG,"db.getVersion()="+db.getVersion());
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
            db.execSQL(DB_CREATE);
            Log.i(TAG, "db.execSQL(DB_CREATE)");
            Log.e(TAG, DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "DataBaseManagementHelper onUpgrade");
            onCreate(db);
        }
    }

    public UserDataManager(Context context) {
        mContext = context;
        Log.i(TAG, "UserDataManager construction!");
    }
    //打开数据库
    public void openDataBase() throws SQLException {
        mDatabaseHelper = new DataBaseManagementHelper(mContext);
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }
    //关闭数据库
    public void closeDataBase() throws SQLException {
        mDatabaseHelper.close();
    }
    //添加新用户，即注册
    public long insertUserData(UserData userData) {
        String userName = userData.getUserName();
        String userPwd = userData.getUserPwd();
//        String userIncome = userData.getUserIncome();
//        String userCharacter = userData.getUserCharacter();
//        String userType = userData.getUserType();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PWD, userPwd);
//        values.put(USER_INCOME,userIncome);
//        values.put(USER_CHARACTER,userCharacter);
//        values.put(USER_TYPE,userType);
        return mSQLiteDatabase.insert(TABLE_NAME, ID, values);
    }
    //更新用户信息
    public boolean updateUserData(UserData userData) {
        //int id = userData.getUserId();
        String userName = userData.getUserName();
        String userPwd = userData.getUserPwd();
        String userIncome = userData.getUserIncome();
        String userCharacter = userData.getUserCharacter();
        String userType = userData.getUserType();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PWD, userPwd);
        values.put(USER_INCOME,userIncome);
        values.put(USER_CHARACTER,userCharacter);
        values.put(USER_TYPE,userType);
        return mSQLiteDatabase.update(TABLE_NAME, values,null, null) > 0;
        //return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }
    //
    public Cursor fetchUserData(int id) throws SQLException {
        Cursor mCursor = mSQLiteDatabase.query(false, TABLE_NAME, null, ID
                + "=" + id, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //
    public Cursor fetchAllUserDatas() {
        return mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null,
                null);
    }

    //
    public String getStringByColumnName(String columnName, int id) {
        Cursor mCursor = fetchUserData(id);
        int columnIndex = mCursor.getColumnIndex(columnName);
        String columnValue = mCursor.getString(columnIndex);
        mCursor.close();
        return columnValue;
    }
    //
    public boolean updateUserDataById(String columnName, int id,
                                      String columnValue) {
        ContentValues values = new ContentValues();
        values.put(columnName, columnValue);
        return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }
    //根据用户名找用户，可以判断注册时用户名是否已经存在
    public int findUserByName(String userName){
        String sql = "Select * from users where user_name =?";
        Log.i(TAG,"findUserByName , userName="+userName);
        int result=0;
        Cursor mCursor=mSQLiteDatabase.rawQuery(sql,new String[]{userName});
        if(mCursor!=null){
            result=mCursor.getCount();
            mCursor.close();
            Log.i(TAG,"findUserByName , result="+result);
        }
        return result;
    }
    //根据用户名和密码找用户，用于登录
    public int findUserByNameAndPwd(String userName,String pwd){
        String sql = "Select * from users where user_name=? and user_pwd=?";
        Log.i(TAG,"findUserByNameAndPwd");
        int result=0;
        Cursor mCursor=mSQLiteDatabase.rawQuery(sql, new String[]{userName, pwd});
        if(mCursor!=null){
            result=mCursor.getCount();
            mCursor.close();
            Log.i(TAG,"findUserByNameAndPwd , result="+result);
        }
        return result;
    }

}
