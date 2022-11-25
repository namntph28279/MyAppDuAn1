package com.example.myapp.Activity.ThanhPhanTrongTK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.myapp.R;

public class DieuKhoanVaDieuKien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieu_khoan_va_dieu_kien);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> DieuKhoanVaDieuKien.this.onBackPressed());
    }
}