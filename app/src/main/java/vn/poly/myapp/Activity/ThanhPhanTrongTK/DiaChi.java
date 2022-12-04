package vn.poly.myapp.Activity.ThanhPhanTrongTK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.poly.myapp.DTO.GoogleDTO;
import vn.poly.myapp.DTO.ThongTin;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.ThongTinDAO;
import vn.poly.myapp.R;


public class DiaChi extends AppCompatActivity {
        EditText ten,sdt,tinh,huyen,xa,duong;
        CardView luu;
        GoogleDAO googleDAO;
        ThongTinDAO thongTinDAO;
        String user,user2;
        Toolbar toolbar;
        TextView thongbao1,thongbao2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi);

        anhXa();

        if(thongTinDAO.checkLogin(user)>0) {
            int a = Integer.parseInt(thongTinDAO.Stt(user));
            ThongTin tt = thongTinDAO.getALL().get(a - 1);

            if (tt.getTenNguoiNhanHang() == null) {
                ten.setText(thongTinDAO.TenChuTK(user));
            } else {
                ten.setText(tt.getTenNguoiNhanHang());
            }

            sdt.setText(tt.getSoDienThoai());
            tinh.setText(tt.getTenTinh());
            huyen.setText(tt.getTenHuyen());
            xa.setText(tt.getTenXa());
            duong.setText(tt.getTenDuong());
            luu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  checkSuaTK();
                }
            });
        }else if (googleDAO.checkLogin(user2)>0){
            int a = Integer.parseInt(googleDAO.Stt(user2));
            Log.d("123", "onCreate: "+a);
            GoogleDTO gg = googleDAO.getALL().get(a-1);
            sdt.setText(gg.getSoDienThoai());
            tinh.setText(gg.getTenTinh());
            huyen.setText(gg.getTenHuyen());
            xa.setText(gg.getTenXa());
            duong.setText(gg.getTenDuong());
            if (gg.getTenNguoiNhanHang() == null) {
                ten.setText(googleDAO.TenChuTK(user2));
            } else {
                ten.setText(gg.getTenNguoiNhanHang());
            }
            luu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ten.getText().toString().isEmpty()||tinh.getText().toString().isEmpty()||huyen.getText().toString().isEmpty()||xa.getText().toString().isEmpty()||sdt.getText().toString().isEmpty()){
                        thongbao1.setText("Vui lòng điền đủ thông tin. ");
                    }else {
                        thongbao1.setText("  ");
                        checkSuaGG();
                    }

                }
            });
        }

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> DiaChi.this.onBackPressed());
    }

    private void checkSuaTK() {
        if( !checkTen() | !checkTinh() |  !checkSDT() |!checkHuyen()  |!checkXa()){
            return;
        }else {
            ThongTin tt = new ThongTin();

            tt.setMaTK(thongTinDAO.makh(user));
            tt.setTenNguoiNhanHang(ten.getText().toString());
            tt.setSoDienThoai(sdt.getText().toString());
            tt.setTenTinh(tinh.getText().toString());
            tt.setTenHuyen(huyen.getText().toString());
            tt.setTenXa(xa.getText().toString());
            tt.setTenDuong(duong.getText().toString());

            int kq = thongTinDAO.updateDC(tt);

            if (kq == -1) {
                Toast.makeText(getApplicationContext(), "Luu Thất Bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Luu Thành Công", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void checkSuaGG() {
        if( !checkTen() | !checkTinh() |  !checkSDT() |!checkHuyen()  |!checkXa()){
            return;
        }else {

            GoogleDTO tt = new GoogleDTO();

            tt.setStt(googleDAO.makh(user2));
            tt.setTenNguoiNhanHang(ten.getText().toString());
            tt.setSoDienThoai(sdt.getText().toString());
            tt.setTenTinh(tinh.getText().toString());
            tt.setTenHuyen(huyen.getText().toString());
            tt.setTenXa(xa.getText().toString());
            tt.setTenDuong(duong.getText().toString());

            int kq = googleDAO.updateDCGG(tt);

            if (kq == -1) {
                Toast.makeText(getApplicationContext(), "Luu Thất Bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Luu Thành Công", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkXa() {
        if (xa.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    private boolean checkHuyen() {
        if (huyen.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    private boolean checkSDT() {
        String number = sdt.getText().toString();
        if (!number.matches("^((09|03|07|08|05)+([0-9]{8}$))")){
            thongbao2.setText("Sai định dạng sdt.");
            return false;
        }else {
            thongbao2.setText("");
            return true;
        }
    }

    private boolean checkTinh() {
        if (tinh.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    private boolean checkTen() {
        if (ten.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }



    private void anhXa() {
        toolbar = findViewById(R.id.toolBarnh);
        thongbao1=findViewById(R.id.thongbaonh);
        thongbao2=findViewById(R.id.thongbaonh2);
        ten = findViewById(R.id.tennh);
        sdt = findViewById(R.id.sonhanhang);
        tinh = findViewById(R.id.tinh);
        huyen = findViewById(R.id.huyen);
        xa = findViewById(R.id.xa);
        duong = findViewById(R.id.duong);
        luu = findViewById(R.id.luutt);

        googleDAO = new GoogleDAO(getApplicationContext());
        thongTinDAO =  new ThongTinDAO(getApplicationContext());

        SharedPreferences preferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = getSharedPreferences("USER_FILEgg",MODE_PRIVATE);
        user2 = preferences2.getString("email", "");
    }
}