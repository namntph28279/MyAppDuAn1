package com.example.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp.DTO.Giay;
import com.example.myapp.Database.DbHelper;

import java.util.ArrayList;

public class GiayDao {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public GiayDao(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Giay> getAll(){
        ArrayList<Giay> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM GIAY";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int id = cs.getInt(0);
            String ten = cs.getString(1);
            float gia = cs.getFloat(2);
            byte[] hinh = cs.getBlob(3);
            float size = cs.getFloat(4);

            list.add(new Giay(id, ten, gia, hinh, size));
            cs.moveToNext();
        }

        return list;
    }

    public boolean insert(String ten, float gia, byte[] hinh, float size){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", ten);
        values.put("gia", gia);
        values.put("hinh", hinh);
        values.put("size", size);


        long row = db.insert("giay", null, values);
        return (row>0);
    }
}
