package vn.poly.myapp.Activity.ManHinhDangNhapDangKy;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import vn.poly.myapp.Activity.MainActivity;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.QuenMatKhau;
import vn.poly.myapp.DTO.GoogleDTO;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.Fragment.Fragment_Accout;
import vn.poly.myapp.R;

public class ManHinhDangNhap extends AppCompatActivity {


    TextInputEditText  User,Pass;
    CardView dangNhap;
    String tk,mk;
    TaiKhoanDAO taiKhoanDAO;
    GoogleDAO googleDAO;
    Fragment_Accout fragment_accout;
    TextView dky,quen;
    LinearLayout dangnhapGG;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextInputLayout checkpass,checkuser;
    TextView thongBao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);

        User = findViewById(R.id.inputNamedn);
        Pass = findViewById(R.id.inputPassdn);
        dky = findViewById(R.id.txtDangKy);
        dangNhap = findViewById(R.id.Dangnhapdn);
        checkpass = findViewById(R.id.checkpass);
        checkuser = findViewById(R.id.checkuser);
        thongBao = findViewById(R.id.tt);

        dangnhapGG  = findViewById(R.id.dangnhapgg);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }

        quen = findViewById(R.id.txtQuen);
        fragment_accout = new Fragment_Accout();
        googleDAO = new GoogleDAO(getApplicationContext());
        taiKhoanDAO = new TaiKhoanDAO(getApplicationContext());


        dky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManHinhDangKy.class);
                startActivity(intent);
                finish();
            }
        });

        quen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuenMatKhau.class);
                startActivity(intent);

            }
        });
        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = checkuser.getEditText().getText().toString().trim();
                String pass = checkpass.getEditText().getText().toString().trim();
                if (user.isEmpty()&&pass.isEmpty()){
                    thongBao.setText("Vui lòng nhập đầy đủ thông tin");
                }else if (user.isEmpty()){
                    thongBao.setText("Vui lòng điền User");
                }else if (pass.isEmpty()){
                    thongBao.setText("Vui lòng điền Password");}
                else {
                    thongBao.setText(" ");
                }
                checkLogin();
            }
        });

        dangnhapGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = gsc.getSignInIntent();
                startActivityForResult(signInIntent,1000);
            }
        });

        quayLai();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);

                GoogleDTO gg = new GoogleDTO();
                gg.setEmail(account.getEmail());
                gg.setHoTen(account.getDisplayName());

                rememberUsergg(account.getEmail());
                Log.d("zzzz", "onActivityResult: "+gg.getEmail());

                if (googleDAO.checkLogin(account.getEmail())>0){
                    navigateToSecondActivity();
                    Toast.makeText(this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();

                    SharedPreferences pref2 = getSharedPreferences("Google",MODE_PRIVATE);
                    SharedPreferences.Editor edit2 = pref2.edit();
                    edit2.putString("checkgg","1");
                    edit2.commit();

                }else {
                    googleDAO.themTK(gg);
                    navigateToSecondActivity();
                    Toast.makeText(this, "dang ky Tahnh Cong", Toast.LENGTH_SHORT).show();

                    SharedPreferences pref2 = getSharedPreferences("Google",MODE_PRIVATE);
                    SharedPreferences.Editor edit2 = pref2.edit();
                    edit2.putString("checkgg","1");
                    edit2.commit();

                }
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(ManHinhDangNhap.this, MainActivity.class);
        startActivity(intent);
    }

    private void checkLogin(){

     tk = User.getText().toString();
     mk = Pass.getText().toString();
        rememberUser(tk,mk);

     if (!checkUser() | !checkPass()){
         return ;
     }

     else{
         if(taiKhoanDAO.checkLogin(tk)>0){
             thongBao.setText("");
             Toast.makeText(this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
             Intent i = new Intent(ManHinhDangNhap.this, MainActivity.class);
             startActivity(i);

             SharedPreferences pref = getSharedPreferences("USER_FILES",MODE_PRIVATE);
             SharedPreferences.Editor edit = pref.edit();
             edit.putString("check","1");
             edit.commit();
             finish();

          }
         else {
             thongBao.setText("Tài khoản hoặc mật khẩu không đúng");
             return;
        }

     }
    }
    private boolean checkUser() {
        String user = checkuser.getEditText().getText().toString().trim();
        if (user.isEmpty()) {
            checkuser.setError(" ");
            return false;
        } else {
            checkuser.setError(null);
            return true;
        }
    }
    private boolean checkPass() {
        String pass = checkpass.getEditText().getText().toString().trim();
        if (pass.isEmpty() ) {
            checkpass.setError(" ");
            return false;
        } else {
            checkpass.setError(null);
            return true;
        }
    }

    private void quayLai() {
        setTitle("");
        Toolbar toolbar = findViewById(R.id.toolBardn);
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
    public void rememberUsergg(String u) {
        SharedPreferences pref3 = getSharedPreferences("USER_FILEgg",MODE_PRIVATE);
        SharedPreferences.Editor edit3 = pref3.edit();

        edit3.putString("email",u);


        edit3.commit();
    }
}