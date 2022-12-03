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
import android.widget.Toast;

import vn.poly.myapp.DTO.TaiKhoan;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.R;


public class DoiMatKhau extends AppCompatActivity {

    EditText mkMoi,mkCu,nhapLaiMk;
    CardView btnDoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        mkMoi = findViewById(R.id.edtpassmoi);
        nhapLaiMk = findViewById(R.id.edtnhaplaipass);
        mkCu = findViewById(R.id.edtpasscu);

        btnDoi = findViewById(R.id.DoiMatKhau);

        Toolbar toolbar = findViewById(R.id.toolBar);

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(getApplicationContext());

      btnDoi.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              SharedPreferences preferences =  getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
              String users = preferences.getString("USERMANE", "");
              String pass = preferences.getString("PASSWORD", "");

              taiKhoanDAO.checkLogin(users);
              if(validate()>0){

                  TaiKhoan tk =  new TaiKhoan();
                  tk.setTenDangNhap(users);

                  tk.setMatKhau(mkMoi.getText().toString());

                  Log.d("mkkk", "onClick: " +users );
                  int kq = taiKhoanDAO.update(tk);
                  if(kq==-1){
                      Toast.makeText(getApplicationContext(),"Doi Mat Khau Thất Bại",Toast.LENGTH_SHORT).show();
                  }else{
                      Toast.makeText(getApplicationContext(),"Doi Mat Khau Thành Công",Toast.LENGTH_SHORT).show();
                      String u = users;
                      String p = mkMoi.getText().toString();
                      SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                      SharedPreferences.Editor edit = pref.edit();

                      edit.putString("USERMANE",u);
                      edit.putString("PASSWORD",p);

                      edit.commit();
                      mkCu.setText("");
                      mkMoi.setText("");
                      nhapLaiMk.setText("");
                  }
              }
          }
      });
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> DoiMatKhau.this.onBackPressed());
    }

    private int validate() {
        int check = 1;
        if (mkCu.getText().length() == 0 && mkMoi.getText().length() == 0 && nhapLaiMk.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Ban phai nhap du thong tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            //doc user,pass trong sharePreferences
            SharedPreferences preferences =  getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passcu = preferences.getString("PASSWORD", "");
            String pass = mkMoi.getText().toString();
            String laipass = nhapLaiMk.getText().toString();

            if (mkCu.getText().length()==0){
                Toast.makeText(getApplicationContext(), "Vui long nhap mat khau cu", Toast.LENGTH_SHORT).show();
                check = -1;

            }else if (!passcu.equals(mkCu.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Mat khau cu sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }

            if (mkMoi.getText().length()==0){
                Toast.makeText(getApplicationContext(), "Vui long nhap mat khau moi", Toast.LENGTH_SHORT).show();
                check = -1;

            }
            if (!pass.equals(laipass)) {
                Toast.makeText(getApplicationContext(), "Mat khau moi khong trung", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}