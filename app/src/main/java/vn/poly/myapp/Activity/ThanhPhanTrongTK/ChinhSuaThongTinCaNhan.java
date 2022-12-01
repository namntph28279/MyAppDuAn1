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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import vn.poly.myapp.DTO.GoogleDTO;
import vn.poly.myapp.DTO.ThongTin;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.Dao.ThongTinDAO;
import vn.poly.myapp.R;


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
        GoogleDAO googleDAO = new GoogleDAO(getApplicationContext());

        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = getSharedPreferences("USER_FILEgg",MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");

        if(thongTinDAO.checkLogin(user)>0){

            int b = Integer.parseInt(thongTinDAO.Stt(user));
            ThongTin tt = thongTinDAO.getALL().get(b-1);
            ten.setText(tt.getHoTen());
            email.setText(tt.getEmail());
            diachi.setText(tt.getDiaChi());
            sdt.setText(tt.getSoDienThoai());

           if (tt.getGioiTinh()==2){
               nam.setChecked(true);
            }else if (tt.getGioiTinh()==1){
               nu.setChecked(true);
            }else {
               khac.setChecked(true);
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

        }else if (googleDAO.checkLogin(user2)>0){
            int a = Integer.parseInt(googleDAO.Stt(user2));
            Log.d("123", "onCreate: "+a);
            GoogleDTO gg = googleDAO.getALL().get(a-1);

            ten.setText(gg.getHoTen());
            email.setText(gg.getEmail());
            diachi.setText(gg.getDiaChi());
            sdt.setText(gg.getSoDienThoai());
            if (gg.getGioiTinh()==2){
                nam.setChecked(true);
            }else if (gg.getGioiTinh()==1){
                nu.setChecked(true);
            }else {
                khac.setChecked(true);
            }
            Luu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GoogleDTO tt = new GoogleDTO();

                    tt.setStt(googleDAO.makh(user2));
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

                    int kq =googleDAO.updateGG(tt);

                    if(kq==-1 ){
                        Toast.makeText(getApplicationContext(),"Sua Thất Bại",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Sua Thành Công",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> ChinhSuaThongTinCaNhan.this.onBackPressed());
    }

}