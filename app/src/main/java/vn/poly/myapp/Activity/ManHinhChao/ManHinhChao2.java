package vn.poly.myapp.Activity.ManHinhChao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import vn.poly.myapp.Activity.MainActivity;
import vn.poly.myapp.Adapter.AnhAdapte;
import vn.poly.myapp.DTO.Anh;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import vn.poly.myapp.R;

public class ManHinhChao2 extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private AnhAdapte anhAdapte;
    private List<Anh> listAnh;

    private Timer timer;
    private CardView khamphangay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao2);

        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle_indicator);
        khamphangay = findViewById(R.id.khamphangay);

        listAnh = getListPhoto();
        anhAdapte = new AnhAdapte(this, listAnh);
        viewPager.setAdapter(anhAdapte);

        circleIndicator.setViewPager(viewPager);
        anhAdapte.registerDataSetObserver(circleIndicator.getDataSetObserver());

        khamphangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManHinhChao2.this, MainActivity.class);
                startActivity(i);
            }
        });

        autoslide();
    }
    private List<Anh> getListPhoto(){
        List<Anh> list = new ArrayList<>();
        list.add(new Anh(R.drawable.logo));
        list.add(new Anh(R.drawable.logo1));
        list.add(new Anh(R.drawable.logo2));

        return list;
    }
    private  void autoslide(){

        if(listAnh ==null || listAnh.isEmpty()||viewPager==null){
            return;
        }
        if (timer==null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentitem = viewPager.getCurrentItem();

                        //totalItem : tong slide
                        int totalItem = listAnh.size() -1;

                        if (currentitem<totalItem){
                            currentitem++;
                            viewPager.setCurrentItem(currentitem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,4000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }
}