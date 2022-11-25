package com.example.myapp.Activity.ThanhPhanTrongTK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapp.Activity.ManHinhDangNhapDangKy.ManHinhDangNhap;
import com.example.myapp.DTO.ThongTin;
import com.example.myapp.Dao.ThongTinDAO;
import com.example.myapp.R;

public class ChinhSuaThongTinCaNhan extends AppCompatActivity {

    EditText ten,email,diachi,sdt;
    RadioGroup check;
    CardView Luu;
    RadioButton rd,nam,nu,khac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin_ca_nhan);

        Toolbar toolbar = findViewById(R.id.toolBar);
        ten = findViewById(R.id.edtten);
        email = findViewById(R.id.edte);
        diachi = findViewById(R.id.edtd);
        sdt = findViewById(R.id.edts);
        check = findViewById(R.id.chk);
        Luu = findViewById(R.id.luu);

        nam = findViewById(R.id.rdNam);
        nu = findViewById(R.id.rdNu);
        khac = findViewById(R.id.rdKhac);

        ThongTinDAO thongTinDAO = new ThongTinDAO(getApplicationContext());

        SharedPreferences preferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        ten.setText(thongTinDAO.TenChuTK(user));
        email.setText(thongTinDAO.Eamil(user));
        diachi.setText(thongTinDAO.DiaChi(user));
        sdt.setText(thongTinDAO.SoDienThoai(user));

        if (Integer.parseInt(thongTinDAO.gioiTinh(user))==0){
            khac.setChecked(true);
        }else if(Integer.parseInt(thongTinDAO.gioiTinh(user))==1){
            nu.setChecked(true);
        }else {
            nam.setChecked(true);
        }

        Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ThongTin tt = new ThongTin();

               tt.setMaTK(thongTinDAO.makh(user));
                tt.setHoTen(ten.getText().toString());
                tt.setEmail(email.getText().toString());
                tt.setDiaChi(diachi.getText().toString());
                tt.setSoDienThoai(sdt.getText().toString());

                if (nam.isChecked()){
                    tt.setGioiTinh(2);
                }else if (nu.isChecked()){
                    tt.setGioiTinh(1);
                }else{
                    tt.setGioiTinh(0);
                }

                int kq =thongTinDAO.updateTT(tt);

                if(kq==-1 ){
                    Toast.makeText(getApplicationContext(),"Sua Thất Bại",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Sua Thành Công",Toast.LENGTH_SHORT).show();
                }
            }
        });


        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> ChinhSuaThongTinCaNhan.this.onBackPressed());
    }

}