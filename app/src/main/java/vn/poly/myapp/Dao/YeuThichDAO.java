package vn.poly.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.DTO.YeuThich;
import vn.poly.myapp.Database.DbHelper;

public class YeuThichDAO {
    DbHelper mHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public YeuThichDAO(Context context) {
        this.context = context;
        mHelper = new DbHelper(context);
        sqLiteDatabase = mHelper.getWritableDatabase();
    }

    public int themYT(YeuThich ls) {

        ContentValues mvalues = new ContentValues();
        mvalues.put("tenSP", ls.getTenSp());
        mvalues.put("soLuong", ls.getSoLuong());
        mvalues.put("gia", ls.getGia());
        mvalues.put("kichco", ls.getKichCo());
        mvalues.put("hinh", ls.getHinh());
        mvalues.put("tenDangNhap", ls.getTenDangNhap());
        mvalues.put("email", ls.getEmail());

        Log.d("themGH", "themTK: " + ls.getTenSp());
        Log.d("themGH", "themTK: " + ls.getGia());
        Log.d("themGH", "themTK: " + ls.getHinh());


        long kq = sqLiteDatabase.insert("yeuThich", null, mvalues);

        if (kq < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public ArrayList<YeuThich> getALL(String id) {
        ArrayList<YeuThich> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich where tenDangNhap='" + id + "'", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            YeuThich ls = new YeuThich();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: " + c.getCount());
            mArr.add(ls);
        }
        c.close();
        return mArr;
    }

    public int checkHang(String id) {
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich where tenDangNhap='" + id + "' ", null);
        Log.d("Count", c.getCount() + "");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int a = c.getCount();
            c.moveToNext();
            c.close();
            return a;
        }

        return 0;
    }


    public ArrayList<YeuThich> getALLGG(String id) {
        ArrayList<YeuThich> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich where email='" + id + "'  ", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            YeuThich ls = new YeuThich();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: " + c.getCount());
            mArr.add(ls);
        }
        c.close();
        return mArr;
    }

    public int checkHangGG(String id) {
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich where email='" + id + "' ", null);
        Log.d("Count", c.getCount() + "");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int a = c.getCount();
            c.moveToNext();
            c.close();
            return a;
        }

        return 0;
    }




    public int delete(int id) {
        long kq = sqLiteDatabase.delete("yeuThich", "maSP=?", new String[]{String.valueOf(id)});
        if (kq < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public ArrayList<YeuThich> getALLTang(String id) {
        ArrayList<YeuThich> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich where tenDangNhap='" + id + "' ORDER BY gia ASC ", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            YeuThich ls = new YeuThich();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: " + c.getCount());
            mArr.add(ls);
        }
        c.close();
        return mArr;
    }

    public ArrayList<YeuThich> getALLGiam(String id) {
        ArrayList<YeuThich> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich where tenDangNhap='" + id + "' ORDER BY gia DESC ", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            YeuThich ls = new YeuThich();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: " + c.getCount());
            mArr.add(ls);
        }
        c.close();
        return mArr;
    }

    public ArrayList<YeuThich> getALLTangGG(String id) {
        ArrayList<YeuThich> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich where email='" + id + "' ORDER BY gia ASC ", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            YeuThich ls = new YeuThich();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: " + c.getCount());
            mArr.add(ls);
        }
        c.close();
        return mArr;
    }

    public ArrayList<YeuThich> getALLGiamGG(String id) {
        ArrayList<YeuThich> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich where email='" + id + "' ORDER BY gia DESC ", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            YeuThich ls = new YeuThich();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: " + c.getCount());
            mArr.add(ls);
        }
        c.close();
        return mArr;
    }
    public ArrayList<YeuThich> getALLGiamtb() {
        ArrayList<YeuThich> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich  ORDER BY gia DESC ", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            YeuThich ls = new YeuThich();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: " + c.getCount());
            mArr.add(ls);
        }
        c.close();
        return mArr;
    }
    public ArrayList<YeuThich> getALLTangtb() {
        ArrayList<YeuThich> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich  ORDER BY gia ASC ", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            YeuThich ls = new YeuThich();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: " + c.getCount());
            mArr.add(ls);
        }
        c.close();
        return mArr;
    }
    public ArrayList<YeuThich> getALLtb( ) {
        ArrayList<YeuThich> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich   ", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            YeuThich ls = new YeuThich();
            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

            c.moveToNext();
            Log.d("zz", "getALL: " + c.getCount());
            mArr.add(ls);
        }
        c.close();
        return mArr;
    }
    public int checkHangtb() {
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM yeuThich  ", null);
        Log.d("Count", c.getCount() + "");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int a = c.getCount();
            c.moveToNext();
            c.close();
            return a;
        }

        return 0;
    }
    public YeuThich selectOne(int id){
        sqLiteDatabase = mHelper.getReadableDatabase();
        YeuThich ls = new YeuThich();

        String sql = "SELECT * FROM yeuThich where maSP = "+id;

        Cursor c =  sqLiteDatabase.rawQuery(sql, null);
        if(c.moveToFirst()){

            ls.setMaSp(c.getInt(0));
            ls.setTenSp(c.getString(1));
            ls.setSoLuong(c.getString(2));
            ls.setGia(c.getString(3));
            ls.setKichCo(c.getString(4));
            ls.setHinh(c.getBlob(5));
            ls.setTenDangNhap(c.getString(6));
            ls.setEmail(c.getString(7));

        }

        return ls;
    }

}
