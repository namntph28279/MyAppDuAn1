package com.example.myapp.Activity.ThanhPhanTrongTK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.myapp.R;

public class QuenMatKhau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        setTitle("");
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> QuenMatKhau.this.onBackPressed());
    }
}