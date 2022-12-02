package vn.poly.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.Database.DbHelper;

public class GioHangDAO {
    DbHelper mHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public GioHangDAO(Context context) {
        this.context = context;
        mHelper = new DbHelper(context);
        sqLiteDatabase = mHelper.getWritableDatabase();
    }
    public int themTK(GioHang ls){

        ContentValues mvalues = new ContentValues();
        mvalues.put("tenSP", ls.getTenSp());
        mvalues.put("soLuong", ls.getSoLuong());
        mvalues.put("gia", ls.getGia());
        mvalues.put("kichco", ls.getKichCo());
        mvalues.put("hinh", ls.getHinh());
        mvalues.put("tenDangNhap", ls.getTenDangNhap());
        mvalues.put("email", ls.getEmail());

        Log.d("themGH", "themTK: "+ls.getTenSp());
        Log.d("themGH", "themTK: "+ls.getGia());
        Log.d("themGH", "themTK: "+ls.getHinh());


        long kq = sqLiteDatabase.insert("gioHang",null,mvalues);

        if(kq<0){
            return -1;
        }else {
            return 1;
        }
    }

    public ArrayList<GioHang> getALL() {
        ArrayList<GioHang> mArr = new ArrayList<>();

        Cursor c = sqLiteDatabase.query("gioHang", null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            GioHang ls = new GioHang();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: "+c.getCount());
            mArr.add(ls);
        }

        c.close();
        return mArr;
    }
}
