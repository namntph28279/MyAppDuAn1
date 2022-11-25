package com.example.myapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapp.Dao.TaiKhoanDAO;
import com.example.myapp.Fragment.Fragment_Accout;
import com.example.myapp.Fragment.Fragment_Cart;
import com.example.myapp.Fragment.Fragment_Favourite;
import com.example.myapp.Fragment.Fragment_Home;
import com.example.myapp.Fragment.Fragment_Search;
import com.example.myapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private long backpressedTime;

    Fragment_Accout acc =  new Fragment_Accout();
    Fragment_Home home = new Fragment_Home();
    Fragment_Cart buy = new Fragment_Cart();
    Fragment_Favourite fav = new Fragment_Favourite();
    Fragment_Search seac = new Fragment_Search();


    TaiKhoanDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        SharedPreferences preferences = getSharedPreferences("USER_FILES", Context.MODE_PRIVATE);
        String check = preferences.getString("check", "");

        if(check =="1" || check =="2"){
            bottomNavigationView.getMenu().findItem(R.id.nav_account).setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,acc).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,home).commit();
        }



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,home).commit();
                        return true;
                    case R.id.nav_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,seac).commit();
                        return true;
                    case R.id.nav_fa:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,fav).commit();
                        return true;
                    case R.id.nav_cart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,buy).commit();
                        return true;
                    case R.id.nav_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,acc).commit();
                        return true;
                }
                return false;
            }
        });
    }


    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_nav_home);
        dao = new TaiKhoanDAO(getApplicationContext());

    }
    @Override
    public void onBackPressed() {
        if (backpressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        }
        Toast.makeText(this, "Bấm 2 lần để thoát", Toast.LENGTH_SHORT).show();
        backpressedTime = System.currentTimeMillis();
    }
}