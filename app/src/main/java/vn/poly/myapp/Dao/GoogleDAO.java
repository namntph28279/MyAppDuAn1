package vn.poly.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vn.poly.myapp.DTO.GoogleDTO;
import vn.poly.myapp.DTO.GoogleDTO;
import vn.poly.myapp.DTO.ThongTin;
import vn.poly.myapp.Database.DbHelper;

public class GoogleDAO {
    DbHelper mSqlite;
    SQLiteDatabase mSqLiteDatabase;
    Context context;

    public GoogleDAO(Context context) {
        this.context = context;
        mSqlite = new DbHelper(context);
        mSqLiteDatabase = mSqlite.getWritableDatabase();
    }

    public int themTK(GoogleDTO ls) {

        ContentValues mvalues = new ContentValues();

        mvalues.put("email", ls.getEmail());
        mvalues.put("hoTen", ls.getHoTen());
        mvalues.put("diaChi", ls.getDiaChi());
        mvalues.put("soDienThoai", ls.getSoDienThoai());
        mvalues.put("gioiTinh", ls.getGioiTinh());
        mvalues.put("TenNguoiNhanHang", ls.getTenNguoiNhanHang());
        mvalues.put("tenTinh", ls.getTenTinh());
        mvalues.put("tenHuyen", ls.getTenHuyen());
        mvalues.put("tenXa", ls.getTenXa());
        mvalues.put("tenDuong", ls.getTenDuong());


        long kq = mSqLiteDatabase.insert("thongTinGG", null, mvalues);

        Log.d("zzzz", "themTK: "+ls.getEmail());
        if (kq < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public ArrayList<GoogleDTO> getALL() {
        ArrayList<GoogleDTO> mArr = new ArrayList<>();

        Cursor c = mSqLiteDatabase.query("thongTinGG", null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            GoogleDTO ls = new GoogleDTO();

            ls.setEmail(c.getString(0));
            ls.setHoTen(c.getString(1));
            ls.setDiaChi(c.getString(2));
            ls.setSoDienThoai(c.getString(3));
            ls.setGioiTinh(c.getInt(4));
            ls.setTenNguoiNhanHang(c.getString(5));
            ls.setTenTinh(c.getString(6));
            ls.setTenHuyen(c.getString(7));
            ls.setTenXa(c.getString(8));
            ls.setTenDuong(c.getString(9));
            c.moveToNext();
            mArr.add(ls);

        }

        c.close();
        return mArr;
    }


    public int updateGG(GoogleDTO ls) {
        ContentValues mvalues = new ContentValues();


        mvalues.put("hoTen", ls.getHoTen());
        mvalues.put("diaChi", ls.getDiaChi());
        mvalues.put("soDienThoai", ls.getSoDienThoai());
        mvalues.put("gioiTinh", ls.getGioiTinh());

        long kq = mSqLiteDatabase.update("thongTinGG", mvalues, "email=?", new String[]{String.valueOf(ls.getEmail())});
        if (kq < 0) {
            return -1;
        } else {
            return 1;
        }
    }
    public int updateDCGG(GoogleDTO ls){
        ContentValues mvalues = new ContentValues();


        mvalues.put("soDienThoai", ls.getSoDienThoai());
        mvalues.put("TenNguoiNhanHang", ls.getTenNguoiNhanHang());
        mvalues.put("tenTinh", ls.getTenTinh());
        mvalues.put("tenHuyen", ls.getTenHuyen());
        mvalues.put("tenXa", ls.getTenXa());
        mvalues.put("tenDuong", ls.getTenDuong());

        long kq =  mSqLiteDatabase.update("thongTinGG",mvalues,"email=?",new String[]{String.valueOf(ls.getEmail())});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }
    public String TenChuTK(String id  ){
        String x = "";
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM thongTinGG  WHERE email='"+id+"'",null);
        Log.d("Count2",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x ="";
            x = c.getString(1);
            c.moveToNext();
        }

        return x;
    }
    public String emailGG(String id  ){
        String x = "";
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM thongTinGG  WHERE email='"+id+"'  ",null);
        Log.d("Count2",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x ="";
            x = c.getString(0);
            c.moveToNext();
        }

        return x;
    }
    public int checkLogin(String id) {

        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM thongTinGG  WHERE email='"+id+"'", null);
        Log.d("Countgg", c.getCount() + "");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int a = c.getCount();
            c.moveToNext();
            c.close();
            return a;
        }

        return -1;
    }



}
