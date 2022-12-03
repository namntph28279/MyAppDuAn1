package vn.poly.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.Dao.GiayDao;
import vn.poly.myapp.R;

public class SanPhamActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private TextView tv_rating, tv_name_sp;
    private Button btn_rating;
    private ImageView img_sp;

    GiayDao dao;
    Giay g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        setUpToolBar();

        //Anh xa
        ratingBar = findViewById(R.id.ratingBar);
        tv_rating = findViewById(R.id.tv_rating);
        btn_rating = findViewById(R.id.btn_rating);
        img_sp = findViewById(R.id.img_sp);
        tv_name_sp = findViewById(R.id.tv_name_sp);

        //Lay id sp
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("data");
        int id = b.getInt("id");

        //lay data 1 sp
        dao = new GiayDao(this);
        g = dao.selectOne(id);







        ratingBar.setNumStars(5);

        btn_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_rating.setText(ratingBar.getRating()+"");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //show len man hinh
        Bitmap bmp = BitmapFactory.decodeByteArray(g.getHinh(), 0, g.getHinh().length);
        img_sp.setImageBitmap(bmp);
        tv_name_sp.setText(g.getTen());
    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> SanPhamActivity.this.onBackPressed());
    }
}