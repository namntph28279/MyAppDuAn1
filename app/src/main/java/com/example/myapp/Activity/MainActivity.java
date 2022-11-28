package com.example.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapp.Fragment.Fragment_Accout;
import com.example.myapp.Fragment.Fragment_Cart;
import com.example.myapp.Fragment.Fragment_Favourite;
import com.example.myapp.Fragment.Fragment_Home;
import com.example.myapp.Fragment.Fragment_Search;
import com.example.myapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private long backpressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setUpBottomNavigationView();

    }

    private void setUpBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            if (item.getItemId() == R.id.nav_home) {
                selected = new Fragment_Home();
            } else if (item.getItemId() == R.id.nav_search) {
                selected = new Fragment_Search();
            } else if (item.getItemId() == R.id.nav_fa) {
                selected = new Fragment_Favourite();
            } else if (item.getItemId() == R.id.nav_cart) {
                selected = new Fragment_Cart();
            } else if (item.getItemId() == R.id.nav_account) {
                selected = new Fragment_Accout();
            }
            if (selected != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome, selected).commit();
            }
            return true;
        });
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_nav_home);
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