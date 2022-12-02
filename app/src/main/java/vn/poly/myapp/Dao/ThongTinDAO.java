package vn.poly.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


import vn.poly.myapp.DTO.ThongTin;
import vn.poly.myapp.Database.DbHelper;

public class ThongTinDAO {
    DbHelper mHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public ThongTinDAO(Context context) {
        this.context = context;
        mHelper = new DbHelper(context);
        sqLiteDatabase = mHelper.getWritableDatabase();
    }
    public int themTK(ThongTin ls){

        ContentValues mvalues = new ContentValues();

        mvalues.put("hoTen", ls.getHoTen());
        mvalues.put("email", ls.getEmail());
        mvalues.put("diaChi", ls.getDiaChi());
        mvalues.put("soDienThoai", ls.getSoDienThoai());
        mvalues.put("gioiTinh", ls.getGioiTinh());
        mvalues.put("TenNguoiNhanHang", ls.getTenNguoiNhanHang());
        mvalues.put("tenTinh", ls.getTenTinh());
        mvalues.put("tenHuyen", ls.getTenHuyen());
        mvalues.put("tenXa", ls.getTenXa());
        mvalues.put("tenDuong", ls.getTenDuong());
        mvalues.put("tenDangNhap",ls.getTenDangNhap());


        long kq = sqLiteDatabase.insert("thongTin",null,mvalues);
        Log.d("zzzzzz", "themTK: "+ls.getHoTen());
        Log.d("zzzzzz", "tenDN: "+ls.getTenDangNhap());
        Log.d("zzzzzz", "email: "+ls.getEmail());
        if(kq<0){
            return -1;
        }else {
            return 1;
        }
    }
    public int checkLogin(String id) {

        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin  WHERE tenDangNhap='"+id+"'", null);
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
    public int updateTT(ThongTin ls){
        ContentValues mvalues = new ContentValues();

        mvalues.put("maTK",ls.getMaTK());
        mvalues.put("hoTen", ls.getHoTen());
        mvalues.put("email", ls.getEmail());
        mvalues.put("diaChi", ls.getDiaChi());
        mvalues.put("soDienThoai", ls.getSoDienThoai());
        mvalues.put("gioiTinh", ls.getGioiTinh());


        long kq =  sqLiteDatabase.update("thongTin",mvalues,"maTK=?",new String[]{String.valueOf(ls.getMaTK())});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

    public ArrayList<ThongTin> getALL() {
        ArrayList<ThongTin> mArr = new ArrayList<>();

        Cursor c = sqLiteDatabase.query("thongTin", null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            ThongTin ls = new ThongTin();
            ls.setMaTK(c.getString(0));
            ls.setHoTen(c.getString(1));
            ls.setEmail(c.getString(2));
            ls.setDiaChi(c.getString(3));
            ls.setSoDienThoai(c.getString(4));
            ls.setGioiTinh(c.getInt(5));
            ls.setTenNguoiNhanHang(c.getString(6));
            ls.setTenTinh(c.getString(7));
            ls.setTenHuyen(c.getString(8));
            ls.setTenXa(c.getString(9));
            ls.setTenDuong(c.getString(10));
            ls.setTenDangNhap(c.getString(11));

            c.moveToNext();
            Log.d("zz", "getALL: "+c.getCount());
            mArr.add(ls);

        }

        c.close();
        return mArr;
    }
    public int updateDC(ThongTin ls){
        ContentValues mvalues = new ContentValues();

        mvalues.put("maTK",ls.getMaTK());
        mvalues.put("soDienThoai", ls.getSoDienThoai());
        mvalues.put("TenNguoiNhanHang", ls.getTenNguoiNhanHang());
        mvalues.put("tenTinh", ls.getTenTinh());
        mvalues.put("tenHuyen", ls.getTenHuyen());
        mvalues.put("tenXa", ls.getTenXa());
        mvalues.put("tenDuong", ls.getTenDuong());

        long kq =  sqLiteDatabase.update("thongTin",mvalues,"maTK=?",new String[]{String.valueOf(ls.getMaTK())});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }
    public String Stt(String id){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin  WHERE tenDangNhap='"+id+"'",null);

        c.moveToFirst();
        while ( !c.isAfterLast()){
            x ="";
            x = c.getString(0);
            c.moveToNext();
        }
        Log.d("Count2hai",x);
        return x;
    }
    public String makh(String id  ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin  WHERE  tenDangNhap='"+id+"'  ",null);
        Log.d("Count2",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x ="";
            x = c.getString(0);
            c.moveToNext();
        }

        return x;
    }
    public String TenChuTK(String id  ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin  WHERE tenDangNhap='"+id+"'",null);
        Log.d("Count2",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x ="";
            x = c.getString(1);
            c.moveToNext();
        }

        return x;
    }
    public String Eamil(String id  ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin  WHERE tenDangNhap='"+id+"'",null);
        Log.d("Count2",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x ="";
            x = c.getString(2);
            c.moveToNext();
        }

        return x;
    }

}
