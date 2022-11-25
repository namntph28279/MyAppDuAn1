package com.example.myapp.Activity.ManHinhDangNhapDangKy;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapp.DTO.TaiKhoan;
import com.example.myapp.DTO.ThongTin;
import com.example.myapp.Dao.TaiKhoanDAO;
import com.example.myapp.Dao.ThongTinDAO;
import com.example.myapp.R;

public class ManHinhDangKy extends AppCompatActivity {
    EditText ten,tenDangNhap,Email,Pass,PassLai;
    CardView btnDangKy;
    CheckBox checkDieuKhoan;
    TextView dangNhap;

    TaiKhoanDAO taiKhoanDAO;
    ThongTinDAO thongTinDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_ky);

        ten = findViewById(R.id.inputFullName);
        Email = findViewById(R.id.inputEmail);
        tenDangNhap = findViewById(R.id.inputTenDangNhap);
        Pass = findViewById(R.id.inputPassword);
        PassLai = findViewById(R.id.inputPasswordAgain);
        checkDieuKhoan = findViewById(R.id.check);
        dangNhap = findViewById(R.id.txtDangNhap);

        btnDangKy = findViewById(R.id.DangKy);

        taiKhoanDAO = new TaiKhoanDAO(getApplicationContext());
        thongTinDAO= new ThongTinDAO(getApplicationContext());


        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManHinhDangNhap.class);
                startActivity(intent);
                finish();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiKhoan tk = new TaiKhoan();
                ThongTin tt = new ThongTin();


                if(TextUtils.isEmpty(ten.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Vui Lòng Không Để Trống Tên",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(Email.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Vui Lòng Không Để Trống Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(tenDangNhap.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Vui Lòng Không Để Trống Ten Dang Nhap",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(Pass.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Vui Lòng Không Để Trống Mat Khau ",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(Pass.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Vui Lòng Nhap Lai Mat Khau ",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!checkDieuKhoan.isChecked()){
                    Toast.makeText(getApplicationContext(),"Vui Lòng Chap Nhan Dieu Khoan ",Toast.LENGTH_SHORT).show();
                    return;
                }



                tk.setTenDangNhap(tenDangNhap.getText().toString());
                tk.setMatKhau(Pass.getText().toString());

                tt.setHoTen(ten.getText().toString());
                tt.setEmail(Email.getText().toString());
                tt.setTenDangNhap(tenDangNhap.getText().toString());

                int kq = taiKhoanDAO.themTK(tk);
                int kq2 = thongTinDAO.themTK(tt);

                if(kq==-1 && kq2==-1){
                    Toast.makeText(getApplicationContext(),"Đăng ký Thất Bại",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Đăng ký Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ManHinhDangNhap.class);
                    startActivity(intent);
                    finish();
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