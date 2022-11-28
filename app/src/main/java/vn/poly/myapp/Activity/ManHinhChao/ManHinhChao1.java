package vn.poly.myapp.Activity.ManHinhChao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



import vn.poly.myapp.R;

public class ManHinhChao1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),ManHinhChao2.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}