package com.example.myapp.Activity.ThanhPhanTrongTK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapp.Activity.MainActivity;
import com.example.myapp.Activity.ManHinhChao.ManHinhChao2;
import com.example.myapp.R;

public class TrungTamHoTro extends AppCompatActivity {

    LinearLayout tinNhan,goi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trung_tam_ho_tro);

        tinNhan = findViewById(R.id.btnGuiTinNhan);
        goi = findViewById(R.id.btnGoi);
        Toolbar toolbar = findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> TrungTamHoTro.this.onBackPressed());


        tinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TrungTamHoTro.this, GuiTinNhan.class);
                startActivity(i);
            }
        });

        goi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callintent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:+84 12 2345 6789"));

                if (ActivityCompat.checkSelfPermission(TrungTamHoTro.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(TrungTamHoTro.this, new
                            String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(callintent);
            }
        });

    }
}