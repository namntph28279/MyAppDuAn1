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

    public ArrayList<GioHang> getALL(String id) {
        ArrayList<GioHang> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM gioHang where tenDangNhap='"+id+"'  ",null);
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

    public int checkHang(String id){
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM gioHang where tenDangNhap='"+id+"' ",null);
        Log.d("Count",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            int  a = c.getCount();
            c.moveToNext();
            c.close();
            return a;
        }

        return 0;
    }

    public int TongTien(String id){

        Cursor c = sqLiteDatabase.rawQuery("SELECT sum(soLuong*gia) FROM gioHang where tenDangNhap='"+id+"'",null);

        c.moveToFirst();
        while ( !c.isAfterLast()){
            int  a = c.getInt(0);
            c.moveToNext();
            c.close();
            Log.d("aaaa", "TongTien: "+a);
            return a;

        }

        return 0;
    }

    public ArrayList<GioHang> getALLGG(String id) {
        ArrayList<GioHang> mArr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM gioHang where email='"+id+"'  ",null);
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

    public int checkHangGG(String id){
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM gioHang where email='"+id+"' ",null);
        Log.d("Count",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            int  a = c.getCount();
            c.moveToNext();
            c.close();
            return a;
        }

        return 0;
    }

    public int TongTienGG(String id){

        Cursor c = sqLiteDatabase.rawQuery("SELECT sum(soLuong*gia) FROM gioHang where email='"+id+"'",null);

        c.moveToFirst();
        while ( !c.isAfterLast()){
            int  a = c.getInt(0);
            c.moveToNext();
            c.close();
            Log.d("aaaa", "TongTien: "+a);
            return a;

        }

        return 0;
    }

    public int delete(int id){
        long kq =  sqLiteDatabase.delete("gioHang","maSP=?",new String[]{String.valueOf(id)});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }


}
