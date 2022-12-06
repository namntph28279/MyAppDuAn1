package vn.poly.myapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "myapp.db";
    static final int DB_VERSION = 1;

    public DbHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang san pham
        String crSP = "CREATE TABLE GIAY (id integer primary key autoincrement, " +
                "ten text, " +
                "gia integer," +
                " hinh blod," +
                " size integer)";

        db.execSQL(crSP);
        String crTK = "CREATE TABLE taiKhoan (" +
                "tenDangNhap text primary key  ," +
                " matKhau text not null )";

        db.execSQL(crTK);

        String crTTTK = "CREATE TABLE thongTin (maTK integer primary key autoincrement  ," +
                " hoTen text ," +
                " email text ," +
                " diaChi text ," +
                " soDienThoai text," +
                " gioiTinh integer," +
                " TenNguoiNhanHang text ," +
                " tenTinh text ," +
                " tenHuyen text," +
                " tenXa text," +
                " tenDuong text," +
                " tenDangNhap text," +

                "foreign key(tenDangNhap) references  taiKhoan(tenDangNhap))";

        db.execSQL(crTTTK);

        String crTTTKGG = "CREATE TABLE thongTinGG (" +
                " stt integer primary key autoincrement  ," +
                " email text ," +
                " hoTen text ," +
                " diaChi text ," +
                " soDienThoai text," +
                " gioiTinh integer," +
                " TenNguoiNhanHang text ," +
                " tenTinh text ," +
                " tenHuyen text," +
                " tenXa text," +
                " tenDuong text)";

        db.execSQL(crTTTKGG);

        String crGH = "CREATE TABLE gioHang (" +
                " maSP integer primary key autoincrement  ," +
                " tenSP text ," +
                " soLuong text ," +
                " gia text ," +
                "kichco text," +
                " hinh blod," +
                " tenDangNhap text ," +
                " email text )";

        db.execSQL(crGH);

        String crYT = "CREATE TABLE yeuThich (" +
                " maSP integer primary key autoincrement  ," +
                " tenSP text ," +
                " soLuong text ," +
                " gia integer ," +
                "kichco text," +
                " hinh blod," +
                " tenDangNhap text ," +
                " email text )";

        db.execSQL(crYT);

        //Tạo bảng search
        String crSearch = "CREATE TABLE TABLE_SEARCH (id integer primary key autoincrement, search text)";
        db.execSQL(crSearch);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
