package com.example.myapp.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp.Database.DbHelper;

public class TaiKhoanDAO {
    DbHelper mSqlite;
    SQLiteDatabase mSqLiteDatabase;
    Context context;

    public TaiKhoanDAO(Context context) {
        this.context = context;
        mSqlite = new DbHelper(context);
        mSqLiteDatabase = mSqlite.getWritableDatabase();
    }

}
