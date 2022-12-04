package vn.poly.myapp.Activity.ManHinhDangNhapDangKy;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import vn.poly.myapp.DTO.TaiKhoan;
import vn.poly.myapp.DTO.ThongTin;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.Dao.ThongTinDAO;
import vn.poly.myapp.R;


public class ManHinhDangKy extends AppCompatActivity {
    CardView btnDangKy;
    CheckBox checkDieuKhoan;
    TextView dangNhap,thongBao,thongBao2,thongBao3;
    TextInputEditText ten,tenDangNhap,Email,Pass,PassLai;
    TextInputLayout checkten,checktenDangNhap,checkEmail,checkPass,checkPassLai;
    TaiKhoanDAO taiKhoanDAO;
    ThongTinDAO thongTinDAO;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //ít nhất 1 số
                    "(?=.*[a-z])" +         //ít nhất 1 chữ thường
                    "(?=.*[A-Z])" +         //ít nhất 1 chữ hoa
                    "(?=\\S+$)" +           //không khoảng trắng
                    ".{4,}" +               //tối thiểu 4 kí tự
                    "$");
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

        checkten = findViewById(R.id.checkTen);
        checktenDangNhap = findViewById(R.id.tenDN);
        checkEmail = findViewById(R.id.CheckEmail);
        checkPass= findViewById(R.id.CheckPassaa);
        checkPassLai= findViewById(R.id.CheckPassaaaaaaaa);
        thongBao = findViewById(R.id.ttdk);
        thongBao2 = findViewById(R.id.ttdk2);
        thongBao3 = findViewById(R.id.ttdk3);

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


                String name = checkten.getEditText().getText().toString().trim();
                String youeemal = checkEmail.getEditText().getText().toString().trim();
                String tdn = checktenDangNhap.getEditText().getText().toString().trim();
                String passa = checkPass.getEditText().getText().toString().trim();
                String laipassa = checkPassLai.getEditText().getText().toString().trim();

                if(name.isEmpty() || youeemal.isEmpty() || tdn.isEmpty()||passa.isEmpty()||laipassa.isEmpty()){
                   thongBao.setText("Vui lòng điền đủ thông tin. ");
                }else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(youeemal).matches()){
                        thongBao2.setText("Sai định dạng email. ");
                    }else{
                        thongBao2.setText("");
                    }

                    if (!PASSWORD_PATTERN.matcher(passa).matches()){
                        thongBao.setText("Mật khẩu phải có 4 kí tự (số chữ thường và chữ hoa) ");
                    }else{
                        thongBao.setText("");
                    }

                    if(!passa.equals(laipassa)){
                        thongBao3.setText("Password không trùng");
                    }else {
                        thongBao3.setText("");
                    }

                    if(!checkDieuKhoan.isChecked()){
                        thongBao.setText("Vui lòng đồng ý điều khoản. ");
                    }
                }

                checkDangKy();

            }
        });

        quayLai();
    }

    private void checkDangKy() {
        if (!User() | !YourEmail() | !nameDN() | !pass() | !cheklaipass() | !dieukhoan()){
                return;
        }else {
            TaiKhoan tk = new TaiKhoan();
            ThongTin tt = new ThongTin();

            tk.setTenDangNhap(tenDangNhap.getText().toString());
            tk.setMatKhau(Pass.getText().toString());
            tt.setHoTen(ten.getText().toString());
            tt.setEmail(Email.getText().toString());
            tt.setTenDangNhap(tenDangNhap.getText().toString());

            int kq = taiKhoanDAO.themTK(tk);

            if(kq==-1){
               thongBao.setText("Tên đăng nhập đã có người sử dụng vui lòng chọn tên khác.");
            }else{
                thongTinDAO.themTK(tt);
                Toast.makeText(getApplicationContext(),"Đăng ký Thành Công.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ManHinhDangNhap.class);
                startActivity(intent);
                finish();
            }
        }
    }

    private boolean dieukhoan() {
       if ( !checkDieuKhoan.isChecked()){

           return false;
       }
       else {

           return true;
       }
    }

    private boolean cheklaipass() {
        String a = checkPassLai.getEditText().getText().toString().trim();
        String b = checkPass.getEditText().getText().toString().trim();
        if (a.isEmpty()) {
            checkPassLai.setError(" ");
            return false;
        } else if (!b.equals(a) ) {
            checkPassLai.setError(" ");
            return false;
        } else {
            checkPassLai.setError(null);
            return true;
        }
    }

    private boolean pass() {
        String b = checkPass.getEditText().getText().toString().trim();
        if (b.isEmpty()) {
            checkPass.setError(" ");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(b).matches()) {
            checkPass.setError(" ");
            return false;
        } else {
            checkPass.setError(null);
            return true;
        }
    }

    private boolean nameDN() {
        String c = checktenDangNhap.getEditText().getText().toString().trim();
        if (c.isEmpty()) {
            checktenDangNhap.setError(" ");
            return false;
        } else {
            checktenDangNhap.setError(null);
            return true;
        }
    }

    private boolean YourEmail() {
        String d = checkEmail.getEditText().getText().toString().trim();
        if (d.isEmpty()) {
            checkEmail.setError(" ");
            return false;
        } if (!Patterns.EMAIL_ADDRESS.matcher(d).matches()) {
            checkEmail.setError(" ");
            return false;
        } else  {
            checkEmail.setError(null);
            return true;
        }
    }

    private boolean User() {
        String e = checkten.getEditText().getText().toString().trim();
        if (e.isEmpty()) {
            checkten.setError(" ");
            return false;
        } else {
            checkten.setError(null);
            return true;
        }
    }

    private void quayLai() {
        setTitle("");
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> ManHinhDangKy.this.onBackPressed());
    }
}