package vn.poly.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import vn.poly.myapp.DTO.TaiKhoan;
import vn.poly.myapp.Database.DbHelper;

import java.util.ArrayList;

public class TaiKhoanDAO {
    DbHelper mSqlite;
    SQLiteDatabase mSqLiteDatabase;
    Context context;

    public TaiKhoanDAO(Context context) {
        this.context = context;
        mSqlite = new DbHelper(context);
        mSqLiteDatabase = mSqlite.getWritableDatabase();
    }

    public int themTK(TaiKhoan ls){

        ContentValues mvalues = new ContentValues();

        mvalues.put("tenDangNhap", ls.getTenDangNhap());
        mvalues.put("matKhau", ls.getMatKhau());

        long kq = mSqLiteDatabase.insert("taiKhoan",null,mvalues);

        if(kq<0){
            return -1;
        }else {
            return 1;
        }
    }

    public ArrayList<TaiKhoan> getALL(){
        ArrayList<TaiKhoan> mArr = new ArrayList<>();

        Cursor c = mSqLiteDatabase.query("taiKhoan",null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){

            TaiKhoan ls = new TaiKhoan();

            ls.setTenDangNhap(c.getString(0));
            ls.setMatKhau(c.getString(3));
            
            c.moveToNext();
            mArr.add(ls);

        }

        c.close();
        return mArr;
    }




    public int update(TaiKhoan ls){
        ContentValues mValues = new ContentValues();

        mValues.put("matKhau",ls.getMatKhau());

        long kq =  mSqLiteDatabase.update("taiKhoan",mValues,"tenDangNhap=?",new String[]{String.valueOf(ls.getTenDangNhap())});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

    public int checkLogin(String id ){

        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM taiKhoan WHERE tenDangNhap='"+id+"'  ",null);
        Log.d("Count",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            int  a = c.getCount();
            c.moveToNext();
            c.close();
            return a;
        }

        return -1;
    }


}
