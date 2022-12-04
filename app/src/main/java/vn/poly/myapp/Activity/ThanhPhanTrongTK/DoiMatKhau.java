package vn.poly.myapp.Activity.ThanhPhanTrongTK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import vn.poly.myapp.DTO.TaiKhoan;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.R;


public class DoiMatKhau extends AppCompatActivity {

    TextInputEditText mkMoi,mkCu,nhapLaiMk;
    TextInputLayout cu,moi,laimoi;
    CardView btnDoi;
    String users,passcu;
    TaiKhoanDAO taiKhoanDAO;
    Toolbar toolbar;
    TextView thongBao;
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
        setContentView(R.layout.activity_doi_mat_khau);

      anhXa();

      btnDoi.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {



              checkThongTin();

          }
      });
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> DoiMatKhau.this.onBackPressed());
    }

    private void checkThongTin() {

        if (!validatemkcu() | !validatemkmoi() | !validatelaiPassword()) {
            return;
            
        } else {

//        taiKhoanDAO.checkLogin(users);


            TaiKhoan tk = new TaiKhoan();
            tk.setTenDangNhap(users);

            tk.setMatKhau(mkMoi.getText().toString());

            Log.d("mkkk", "onClick: " + users);
            int kq = taiKhoanDAO.update(tk);
            if (kq == -1) {
                thongBao.setText("Đổi mật khẩu thất bại ");
            } else {
                thongBao.setText("Đổi mật khẩu thành công");
                String u = users;
                String p = mkMoi.getText().toString();
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.putString("USERMANE", u);
                edit.putString("PASSWORD", p);

                edit.commit();
                mkCu.setText("");
                mkMoi.setText("");
                nhapLaiMk.setText("");
            }
        }
    }
    private boolean validatemkcu() {
        String c = cu.getEditText().getText().toString().trim();
        String d = passcu ;
        if (c.isEmpty()) {
            cu.setError("Vui lòng điền mật khẩu cũ");
            return false;
        } else if (!d.equals(c)) {
            cu.setError("Sai mật khẩu");
            return false;
        } else {
            cu.setError(null);
            return true;
        }

    }


    private boolean validatemkmoi() {
        String b = moi.getEditText().getText().toString().trim();
        if (b.isEmpty()) {
            moi.setError("Vui lòng điền mật khẩu mới");
            return false;
      } else {
            moi.setError(null);
            return true;
        }
    }
    private boolean validatelaiPassword() {

        String a = laimoi.getEditText().getText().toString().trim();
        String b = moi.getEditText().getText().toString().trim();
        if (a.isEmpty()) {
            laimoi.setError("Vui lòng nhập lại mật khẩu mới");
            return false;
        } else if (!b.equals(a)) {
            laimoi.setError("Nhập lại mật khẩu mới không trùng");
            return false;
        } else {
            laimoi.setError(null);
            return true;
        }
    }

    private void anhXa() {
        mkMoi = findViewById(R.id.edtpassmoi);
        nhapLaiMk = findViewById(R.id.edtnhaplaipass);
        mkCu = findViewById(R.id.edtpasscu);
        cu = findViewById(R.id.cu);
        moi = findViewById(R.id.moi);
        laimoi = findViewById(R.id.laimoi);
        btnDoi = findViewById(R.id.DoiMatKhau);
        thongBao = findViewById(R.id.thongbaodk);
        toolbar = findViewById(R.id.toolBar);
        SharedPreferences preferences =  getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        users = preferences.getString("USERMANE", "");
        passcu = preferences.getString("PASSWORD", "");
        taiKhoanDAO = new TaiKhoanDAO(getApplicationContext());
    }
}