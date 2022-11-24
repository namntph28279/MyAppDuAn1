package com.example.myapp.Activity.ManHinhDangNhapDangKy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.Accout.DaDangNhapFragment;
import com.example.myapp.Activity.MainActivity;
import com.example.myapp.Dao.TaiKhoanDAO;
import com.example.myapp.Fragment.Fragment_Accout;
import com.example.myapp.R;

public class ManHinhDangNhap extends AppCompatActivity {

    EditText User,Pass;
    CardView dangNhap;
    String tk,mk;
    TaiKhoanDAO taiKhoanDAO;
    Fragment_Accout fragment_accout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);

        User = findViewById(R.id.inputNamedn);
        Pass = findViewById(R.id.inputPassdn);

        dangNhap = findViewById(R.id.Dangnhapdn);
        fragment_accout = new Fragment_Accout();

        taiKhoanDAO = new TaiKhoanDAO(getApplicationContext());


        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();

                SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                User.setText(pref.getString("USERMANE",""));
                Pass.setText(pref.getString("PASSWORD",""));



                    Intent i = new Intent(ManHinhDangNhap.this, MainActivity.class);
                    startActivity(i);
            }
        });


        quayLai();

    }

    private void vaoTaiKhoan(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.acc,fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();

    }

    private void checkLogin(){
     tk = User.getText().toString();
     mk = Pass.getText().toString();
        rememberUser(tk,mk);
     if (tk.isEmpty()||mk.isEmpty()){
         Toast.makeText(this, "Không được để chống tài khoản hoặc Mật khẩu", Toast.LENGTH_SHORT).show();
     }
     else{





         if(taiKhoanDAO.checkLogin(tk,mk)>0){
             Toast.makeText(this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();


          }
         else {
             Toast.makeText(this, "Tên đăng nhập hoặc Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
             return;
        }

     }
    }

    private void quayLai() {
        setTitle("");
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> ManHinhDangNhap.this.onBackPressed());
    }
    public void rememberUser(String u , String p  ) {
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

            edit.putString("USERMANE",u);
            edit.putString("PASSWORD",p);

            edit.commit();
    }
}