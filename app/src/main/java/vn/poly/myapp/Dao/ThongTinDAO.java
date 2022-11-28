package vn.poly.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public String makh(String id  ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan tk on tk.tenDangNhap = tt.tenDangNhap " +
                " WHERE tk.tenDangNhap='"+id+"'  ",null);
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
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan tk on tk.tenDangNhap = tt.tenDangNhap " +
                " WHERE tk.tenDangNhap='"+id+"'  ",null);
        Log.d("Count2",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x ="";
            x = c.getString(1);
            c.moveToNext();
        }

        return x;
    }
    public String Eamil(String id ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan  tk on tk.tenDangNhap = tt.tenDangNhap  " +
                "WHERE tk.tenDangNhap='"+id+"'",null);
        Log.d("Count",c.getColumnName(2));
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x = c.getString(2);
            c.moveToNext();
        }

        return x;
    }
    public String DiaChi(String id ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan  tk on tk.tenDangNhap = tt.tenDangNhap  " +
                "WHERE tk.tenDangNhap='"+id+"'",null);

        c.moveToFirst();
        while ( !c.isAfterLast()){
            x = c.getString(3);
            c.moveToNext();
        }

        return x;
    }
    public String SoDienThoai(String id ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan  tk on tk.tenDangNhap = tt.tenDangNhap  " +
                "WHERE tk.tenDangNhap='"+id+"'",null);
        Log.d("Count",c.getColumnName(4));
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x = c.getString(4);
            c.moveToNext();
        }

        return x;
    }
    public String gioiTinh(String id ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan  tk on tk.tenDangNhap = tt.tenDangNhap  " +
                "WHERE tk.tenDangNhap='"+id+"'",null);
        Log.d("Count",c.getColumnName(5));
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x = c.getString(5);
            c.moveToNext();
        }

        return x;
    }
    public String tenNguoiNhan(String id ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan  tk on tk.tenDangNhap = tt.tenDangNhap  " +
                "WHERE tk.tenDangNhap='"+id+"'",null);
        Log.d("Count",c.getColumnName(6));
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x = c.getString(6);
            c.moveToNext();
        }

        return x;
    }
    public String tenTinh(String id ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan  tk on tk.tenDangNhap = tt.tenDangNhap  " +
                "WHERE tk.tenDangNhap='"+id+"'",null);
        Log.d("Count",c.getColumnName(7));
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x = c.getString(7);
            c.moveToNext();
        }

        return x;
    }
    public String tenHuyen(String id ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan  tk on tk.tenDangNhap = tt.tenDangNhap  " +
                "WHERE tk.tenDangNhap='"+id+"'",null);
        Log.d("Count",c.getColumnName(8));
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x = c.getString(8);
            c.moveToNext();
        }

        return x;
    }
    public String tenXa(String id ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan  tk on tk.tenDangNhap = tt.tenDangNhap  " +
                "WHERE tk.tenDangNhap='"+id+"'",null);
        Log.d("Count",c.getColumnName(9));
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x = c.getString(9);
            c.moveToNext();
        }

        return x;
    } public String tenDuong(String id ){
        String x = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM thongTin tt " +
                "inner join taiKhoan  tk on tk.tenDangNhap = tt.tenDangNhap  " +
                "WHERE tk.tenDangNhap='"+id+"'",null);
        Log.d("Count",c.getColumnName(10));
        c.moveToFirst();
        while ( !c.isAfterLast()){
            x = c.getString(10);
            c.moveToNext();
        }

        return x;
    }

}
