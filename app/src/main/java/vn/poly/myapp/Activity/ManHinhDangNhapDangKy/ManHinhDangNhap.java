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

import vn.poly.myapp.Activity.MainActivity;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.QuenMatKhau;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.Fragment.Fragment_Accout;
import vn.poly.myapp.R;
import vn.poly.myapp.SecondActivity;

public class ManHinhDangNhap extends AppCompatActivity {

    EditText User,Pass;
    CardView dangNhap;
    String tk,mk;
    TaiKhoanDAO taiKhoanDAO;
    Fragment_Accout fragment_accout;
    TextView dky,quen;
    LinearLayout dangnhapGG;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);

        User = findViewById(R.id.inputNamedn);
        Pass = findViewById(R.id.inputPassdn);
        dky = findViewById(R.id.txtDangKy);
        dangNhap = findViewById(R.id.Dangnhapdn);
        dangnhapGG  = findViewById(R.id.dangnhapgg);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }

        quen = findViewById(R.id.txtQuen);
        fragment_accout = new Fragment_Accout();

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
                navigateToSecondActivity();

                SharedPreferences.Editor editor = getApplicationContext()
                        .getSharedPreferences("my",MODE_PRIVATE)
                        .edit();
                editor.putString("user",account.getDisplayName());
                editor.putString("email",account.getEmail());
                editor.apply();
                Log.d("zzz", "onActivityResult: "+account.getDisplayName() +account.getEmail() );

            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(ManHinhDangNhap.this, SecondActivity.class);
        startActivity(intent);
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

             Intent i = new Intent(ManHinhDangNhap.this, MainActivity.class);
             startActivity(i);

             SharedPreferences pref = getSharedPreferences("USER_FILES",MODE_PRIVATE);
             SharedPreferences.Editor edit = pref.edit();
             edit.putString("check","1");


             edit.commit();


             finish();

          }
         else {
             Toast.makeText(this, "Tên đăng nhập hoặc Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
             return;
        }

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
}