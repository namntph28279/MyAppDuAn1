package vn.poly.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.Database.DbHelper;

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
            String gia = cs.getString(2);
            byte[] hinh = cs.getBlob(3);
            float size = cs.getFloat(4);

            list.add(new Giay(id, ten, gia, hinh, size));
            cs.moveToNext();
        }

        return list;
    }

    public boolean insert(String ten, String gia, byte[] hinh, float size){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", ten);
        values.put("gia", gia);
        values.put("hinh", hinh);
        values.put("size", size);


        long row = db.insert("giay", null, values);
        return (row>0);
    }

    public Giay selectOne(int id){
        db = dbHelper.getReadableDatabase();
        Giay giay = new Giay();
        String[] args = new String[] { id + "" };
        String[] columns = new String[]{"*"};

        Cursor c = db.query("giay",columns, null, null,null,null,null);
        if(c.moveToFirst()){

            giay.setId( c.getInt(  0  ) );
            giay.setTen( c.getString(1) );
            giay.setGia(c.getString(2));
            giay.setHinh(c.getBlob(3));
            giay.setSize(c.getFloat(4));

        }

        return giay;
    }


}
