package vn.poly.myapp.Activity.ThanhPhanTrongTK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;


import vn.poly.myapp.R;

public class TieuChuanCongDong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tieu_chuan_cong_dong);

            Toolbar toolbar = findViewById(R.id.toolBar);
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(view -> TieuChuanCongDong.this.onBackPressed());

    }
}