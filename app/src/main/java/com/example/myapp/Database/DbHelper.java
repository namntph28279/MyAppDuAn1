package com.example.myapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    static final  String dbName = "SHOPSNEAKER";
    static final int dbVersion = 1;

    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String crTK = " create table taiKhoan(" +
                "tenDangNhap primary key autoincrement," +
                "hoTen text not null,"+
                "soDienThoai text not null,"+
                "email text not null," +
                "matKhau text not null)";

        db.execSQL(crTK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
