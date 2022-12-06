package vn.poly.myapp.Activity;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.view.MenuItem;
import android.widget.Toast;

import vn.poly.myapp.Activity.checkInternet.NetworkChangeListener;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.Dao.YeuThichDAO;
import vn.poly.myapp.Fragment.Fragment_Accout;
import vn.poly.myapp.Fragment.GioHang.Fragment_Cart;
import vn.poly.myapp.Fragment.Fragment_DanhSach_Giay;
import vn.poly.myapp.Fragment.Fragment_Favourite;
import vn.poly.myapp.Fragment.Fragment_Home;
import vn.poly.myapp.Fragment.Fragment_Search;
import vn.poly.myapp.R;


import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity {

    private BottomNavigationView bottomNavigationView;
    private long backpressedTime;
    Fragment_Accout acc = new Fragment_Accout();
    Fragment_Home home = new Fragment_Home();
    Fragment_Cart buy = new Fragment_Cart();
    Fragment_Favourite fav = new Fragment_Favourite();
    Fragment_Search seac = new Fragment_Search();
    Fragment_DanhSach_Giay ds = new Fragment_DanhSach_Giay();

    TaiKhoanDAO dao;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();

    }


    @Override
    void initLayout() {
        bottomNavigationView = findViewById(R.id.bottom_nav_home);
        dao = new TaiKhoanDAO(getApplicationContext());
    }

    @Override
    void initAction() {
        SharedPreferences preferences = getSharedPreferences("USER_FILES", Context.MODE_PRIVATE);
        String check = preferences.getString("check", "");
        SharedPreferences preferences2 = getSharedPreferences("Google", Context.MODE_PRIVATE);
        String check2 = preferences2.getString("checkgg", "");


        if (check == "1" || check == "2" || check2 == "1" || check2 == "2") {
            bottomNavigationView.getMenu().findItem(R.id.nav_account).setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome, acc).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome, home).commit();
        }


//        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.nav_fa);
//        badgeDrawable.setVisible(true);
//        badgeDrawable.setNumber(8);
//
//        BadgeDrawable badgeDrawable2 = bottomNavigationView.getOrCreateBadge(R.id.nav_cart);
//        badgeDrawable2.setVisible(true);
//        badgeDrawable2.setNumber(8);
//
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome, home).commit();
                        return true;
                    case R.id.nav_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome, seac).commit();
                        return true;
                    case R.id.nav_fa:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome, fav).commit();
                        return true;
                    case R.id.nav_cart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome, buy).commit();
                        return true;
                    case R.id.nav_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome, acc).commit();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    int setLayout() {
        return R.layout.activity_main;
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
