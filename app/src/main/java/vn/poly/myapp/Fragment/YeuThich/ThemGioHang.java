package vn.poly.myapp.Fragment.YeuThich;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import vn.poly.myapp.Activity.MainActivity;
import vn.poly.myapp.Activity.SanPham.SanPhamActivity;
import vn.poly.myapp.Adapter.ClickSizeColor;
import vn.poly.myapp.Adapter.OnSizeItemClick;
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

public class ThemGioHang extends AppCompatActivity implements ClickSizeColor {
    private RatingBar ratingBar;
    private TextView tv_rating, tv_name_sp;
    private Button btn_rating;
    private ImageView img_sp, img_fa_sp;
    private RecyclerView recyclerViewSize;
    private TaiKhoanDAO taiKhoanDAO;
    GoogleDAO googleDAO;
    YeuThichDAO yeuThichDAO;
    private EditText ed_soluong;
    private CardView cardView_add_gio_hang;
    List<Size> list_size;
    int f,g;
    int size = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_gio_hang);

        setUpToolBar();
        init();
        setUpRecyclerViewSize();
        initAction();
    }
    private void initAction(){
        //
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = getSharedPreferences("USER_FILEgg", MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");

        SharedPreferences preferences3 = getSharedPreferences("yeuThich",MODE_PRIVATE);
        String a = preferences3.getString("ten", "");
        String b = preferences3.getString("gia", "");
        String c = preferences3.getString("hinh", "");
        String e = preferences3.getString("id", "");
        String g2 = preferences3.getString("maSP", "");

        byte[] d = c.getBytes();

        int f = Integer.parseInt(e);
        int g= Integer.parseInt(g2);

        Log.d("ddddd", "initAction: "+d);

        Bitmap bmp = BitmapFactory.decodeByteArray(d , 0, d.length);
        img_sp.setImageBitmap(bmp);

        tv_name_sp.setText(a);

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
                    yt.setTenSp(a);
                    yt.setGia(b);
                    yt.setHinh(d);

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

                if (taiKhoanDAO.checkLogin(user)>0||googleDAO.checkLogin(user2)>0){
                    if (sl.equals("")){
                        Toast.makeText(ThemGioHang.this, "Chua nhap so luong", Toast.LENGTH_SHORT).show();
                    }else{

                        if (size < 0) {
                            Toast.makeText(ThemGioHang.this, "Vui long chon size truoc!!!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        yeuThichDAO = new YeuThichDAO(getApplicationContext());
                        GioHangDAO gioHangDAO = new GioHangDAO(getBaseContext());
                        GioHang gh = new GioHang();
                        gh.setEmail(user2);
                        gh.setTenDangNhap(user);
                        gh.setTenSp(a);
                        gh.setGia(b);
                        gh.setHinh(d);
                        gh.setKichCo(list_size.get(size).getSize());
                        gh.setSoLuong(sl);

                        int p=  gioHangDAO.themTK(gh);


                        if(p==-1){
                            Toast.makeText(getBaseContext(),"Add Thất Bại",Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(getBaseContext(),"Add Thành Công",Toast.LENGTH_SHORT).show();
                            SharedPreferences pref = getSharedPreferences("USER_FILES",MODE_PRIVATE);
                            SharedPreferences.Editor edit = pref.edit();
                            edit.putString("check","9");
                            edit.commit();
                            SharedPreferences pref2 = getSharedPreferences("Google",MODE_PRIVATE);
                            SharedPreferences.Editor edit2 = pref2.edit();
                            edit2.putString("checkgg","9");
                            edit2.commit();
                            yeuThichDAO.delete(g);
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);



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
        ed_soluong = findViewById(R.id.ed_soLuongyt);
        recyclerViewSize = findViewById(R.id.rv_size_sanphamyt);
        img_fa_sp = findViewById(R.id.img_fa_spytaaaaw);
        ratingBar = findViewById(R.id.ratingBaryt);
        tv_rating = findViewById(R.id.tv_ratingyt);
        btn_rating = findViewById(R.id.btn_ratingyt);
        img_sp = findViewById(R.id.img_spytaaaaa);
        tv_name_sp = findViewById(R.id.tv_name_spyt);
        cardView_add_gio_hang = findViewById(R.id.cardView_add_gio_hangyt);
    }

    private void setUpRecyclerViewSize() {
        list_size = new ArrayList<>();


        list_size.add(new Size("1", true));
        list_size.add(new Size("2", false));
        list_size.add(new Size("3", false));
        list_size.add(new Size("4", true));


        recyclerViewSize.setHasFixedSize(true);
        recyclerViewSize.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SizeAdapter adapter = new SizeAdapter(list_size, this, this, f);
        recyclerViewSize.setAdapter(adapter);

        adapter.setOnSizeItemClick(new OnSizeItemClick() {
            @Override
            public void onClick(int pos) {
                size = pos;
            }
        });
    }



    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> ThemGioHang.this.onBackPressed());
    }

    @Override
    public void callBackSize(String sizeSP) {

    }

    @Override
    public void callBackColor(String colorSP) {

    }
}