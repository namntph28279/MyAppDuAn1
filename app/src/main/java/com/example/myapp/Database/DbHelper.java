package com.example.myapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "myapp.db";
    static final int DB_VERSION = 1;

    public DbHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang san pham
        String crSP = "CREATE TABLE GIAY (id integer primary key autoincrement, ten text, gia integer, hinh blod, size integer)";
        db.execSQL(crSP);
        String crTK = "CREATE TABLE taiKhoan (tenDangNhap text primary key  , hoTen text, email text, matKhau text not null )";
        db.execSQL(crTK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
