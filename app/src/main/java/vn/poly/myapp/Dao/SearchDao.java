package vn.poly.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.DTO.Search;
import vn.poly.myapp.Database.DbHelper;

public class SearchDao {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public SearchDao(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Search> getAll(){
        ArrayList<Search> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM TABLE_SEARCH";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int id = cs.getInt(0);
            String search = cs.getString(1);


           list.add(new Search(id, search));
            cs.moveToNext();
        }

        return list;
    }

    public boolean insert(String s){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("search", s);


        long row = db.insert("TABLE_SEARCH", null, values);
        return (row>0);
    }

    public void deleteAll(){
        db.execSQL("delete from TABLE_SEARCH");
    }

}
