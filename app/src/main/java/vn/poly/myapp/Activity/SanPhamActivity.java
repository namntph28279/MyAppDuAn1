package vn.poly.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.poly.myapp.Adapter.ClickSizeColor;
import vn.poly.myapp.Adapter.SizeAdapter;
import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.DTO.Size;
import vn.poly.myapp.DTO.YeuThich;
import vn.poly.myapp.Dao.GiayDao;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.Dao.YeuThichDAO;
import vn.poly.myapp.R;

public class SanPhamActivity extends AppCompatActivity implements ClickSizeColor {
    private RatingBar ratingBar;
    private TextView tv_rating, tv_name_sp;
    private Button btn_rating;
    private ImageView img_sp, img_fa_sp;
    private RecyclerView recyclerViewSize;
    private TaiKhoanDAO taiKhoanDAO;
    GoogleDAO googleDAO;
    private EditText ed_soluong;
    private CardView cardView_mua_ngay, cardView_add_gio_hang;

    GiayDao dao;
    Giay g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        setUpToolBar();
        init();
        setUpRecyclerViewSize();

        //
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = getSharedPreferences("USER_FILEgg", MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");



        //

        //Lay id sp
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("data");
        int id = b.getInt("id");

        //lay data 1 sp
        dao = new GiayDao(this);
        g = dao.selectOne(id);
        Bitmap bmp = BitmapFactory.decodeByteArray(g.getHinh(), 0, g.getHinh().length);
        img_sp.setImageBitmap(bmp);
        tv_name_sp.setText(g.getTen());
        Log.e("",g.getGia()+"");
        Log.e("",g.getTen()+"");
        Log.e("",g.getId()+"");
        taiKhoanDAO = new TaiKhoanDAO(this);
        googleDAO = new GoogleDAO(this);

        //Xử lý yêu thích
        img_fa_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taiKhoanDAO.checkLogin(user)>0  || googleDAO.checkLogin(user2)>0){
                    YeuThichDAO yeuThichDAO = new YeuThichDAO(getBaseContext());
                    YeuThich yt = new YeuThich();
                    yt.setEmail(user2);
                    yt.setTenDangNhap(user);
                    yt.setTenSp(g.getTen());
                    yt.setGia(g.getGia());
                    yt.setHinh(g.getHinh());

                    int b=  yeuThichDAO.themYT(yt);

                    if(b==-1){
                        Toast.makeText(getBaseContext(),"Add Thất Bại",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getBaseContext(),"Add Thành Công",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Chua dang nhap", Toast.LENGTH_SHORT).show();
                }



            }
        });

        //Xu ly them vao gio hang
        cardView_add_gio_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sl = ed_soluong.getText().toString();

                if (taiKhoanDAO.checkLogin(user)>0){
                    if (sl.equals("")){
                        Toast.makeText(SanPhamActivity.this, "Chua nhap so luong", Toast.LENGTH_SHORT).show();
                    }else{
                        GioHangDAO gioHangDAO = new GioHangDAO(getBaseContext());
                        GioHang gh = new GioHang();
                        Size s = new Size();
                        gh.setEmail(user2);
                        gh.setTenDangNhap(user);
                        gh.setTenSp(g.getTen());
                        gh.setGia(g.getGia());
                        gh.setHinh(g.getHinh());
                        gh.setKichCo(s.getSize());
                        gh.setSoLuong(sl);

                        int b=  gioHangDAO.themTK(gh);

                        if(b==-1){
                            Toast.makeText(getBaseContext(),"Add Thất Bại",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getBaseContext(),"Add Thành Công",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Chua dang nhap", Toast.LENGTH_SHORT).show();
                }




            }
        });








        btn_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_rating.setText(ratingBar.getRating()+"");
            }
        });

    }

    private void init(){
        ed_soluong = findViewById(R.id.ed_soLuong);
        recyclerViewSize = findViewById(R.id.rv_size_sanpham);
        img_fa_sp = findViewById(R.id.img_fa_sp);
        ratingBar = findViewById(R.id.ratingBar);
        tv_rating = findViewById(R.id.tv_rating);
        btn_rating = findViewById(R.id.btn_rating);
        img_sp = findViewById(R.id.img_sp);
        tv_name_sp = findViewById(R.id.tv_name_sp);
        cardView_mua_ngay = findViewById(R.id.cardView_mua_ngay);
        cardView_add_gio_hang = findViewById(R.id.cardView_add_gio_hang);
    }

    private void setUpRecyclerViewSize() {
        List<Size> list = new ArrayList<>();

        list.add(new Size("1", true));
        list.add(new Size("2", false));
        list.add(new Size("3", false));
        list.add(new Size("4", true));


        recyclerViewSize.setHasFixedSize(true);
        recyclerViewSize.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SizeAdapter adapter = new SizeAdapter(list, this, this);
        recyclerViewSize.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("zzz", "onResume: ads");
        //show len man hinh

    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> SanPhamActivity.this.onBackPressed());
    }

    @Override
    public void callBackSize(String sizeSP) {

    }

    @Override
    public void callBackColor(String colorSP) {

    }
}