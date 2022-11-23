package com.example.myapp.Activity.ManHinhDangNhapDangKy;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapp.DTO.TaiKhoan;
import com.example.myapp.Dao.TaiKhoanDAO;
import com.example.myapp.R;

public class ManHinhDangKy extends AppCompatActivity {

    EditText ten,tenDangNhap,Email,Pass,PassLai;
    CardView btnDangKy;
    CheckBox checkDieuKhoan;
    TextView dangNhap;

    TaiKhoanDAO taiKhoanDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_ky);

        ten = findViewById(R.id.inputFullName);
        Email = findViewById(R.id.inputFullName);
        tenDangNhap = findViewById(R.id.inputTenDangNhap);
        Pass = findViewById(R.id.inputPassword);
        PassLai = findViewById(R.id.inputPasswordAgain);
        checkDieuKhoan = findViewById(R.id.check);
        dangNhap = findViewById(R.id.txtDangNhap);

        btnDangKy = findViewById(R.id.DangKy);
        taiKhoanDAO = new TaiKhoanDAO(getApplicationContext());

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiKhoan tk = new TaiKhoan();

                tk.setHoTen(ten.getText().toString());
                tk.setEmail(Email.getText().toString());
                tk.setTenDangNhap(tenDangNhap.getText().toString());
                tk.setMatKhau(Pass.getText().toString());

                int kq = taiKhoanDAO.themTK(tk);

                if(kq==-1){
                    Toast.makeText(getApplicationContext(),"Đăng ký Thất Bại",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Đăng ký Thành Công",Toast.LENGTH_SHORT).show();
                }

            }
        });

        quayLai();
    }

    private void quayLai() {
        setTitle("");
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> ManHinhDangKy.this.onBackPressed());
    }
}