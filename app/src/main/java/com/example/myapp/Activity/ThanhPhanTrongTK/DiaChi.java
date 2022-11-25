package com.example.myapp.Activity.ThanhPhanTrongTK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.DTO.ThongTin;
import com.example.myapp.Dao.ThongTinDAO;
import com.example.myapp.R;

public class DiaChi extends AppCompatActivity {
        EditText ten,sdt,tinh,huyen,xa,duong;
        CardView luu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi);

        Toolbar toolbar = findViewById(R.id.toolBar);

        ten = findViewById(R.id.tennh);
        sdt = findViewById(R.id.sonhanhang);
        tinh = findViewById(R.id.tinh);
        huyen = findViewById(R.id.huyen);
        xa = findViewById(R.id.xa);
        duong = findViewById(R.id.duong);
        luu = findViewById(R.id.luutt);

        ThongTinDAO thongTinDAO =  new ThongTinDAO(getApplicationContext());

        SharedPreferences preferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        if (thongTinDAO.tenNguoiNhan(user)==null){
            ten.setText(thongTinDAO.TenChuTK(user));
        }else {
            ten.setText(thongTinDAO.tenNguoiNhan(user));
        }

        sdt.setText(thongTinDAO.SoDienThoai(user));
        tinh.setText(thongTinDAO.tenTinh(user));
        huyen.setText(thongTinDAO.tenHuyen(user));
        xa.setText(thongTinDAO.tenXa(user));
        duong.setText(thongTinDAO.tenDuong(user));

luu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ThongTin tt = new ThongTin();

        tt.setMaTK(thongTinDAO.makh(user));
        tt.setTenNguoiNhanHang(ten.getText().toString());
        tt.setSoDienThoai(sdt.getText().toString());
        tt.setTenTinh(tinh.getText().toString());
        tt.setTenHuyen(huyen.getText().toString());
        tt.setTenXa(xa.getText().toString());
        tt.setTenDuong(duong.getText().toString());

        int kq =thongTinDAO.updateDC(tt);

        if(kq==-1 ){
            Toast.makeText(getApplicationContext(),"Luu Thất Bại",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Luu Thành Công",Toast.LENGTH_SHORT).show();
        }
    }
});

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> DiaChi.this.onBackPressed());
    }
}