package vn.poly.myapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import vn.poly.myapp.Activity.SanPham.DanhSachGiayActivity;
import vn.poly.myapp.Adapter.AnhAdapte;
import vn.poly.myapp.Adapter.GiayAdapter;
import vn.poly.myapp.DTO.Anh;
import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.Dao.GiayDao;
import vn.poly.myapp.R;

public class Fragment_Home extends Fragment {

    private ViewPager viewpager_home;
    private CircleIndicator circleIndicator_home;
    private AnhAdapte anhAdapte;
    private List<Anh> listAnh;
    private Timer timer;
    private TextView tv_showAll_home;
    private Context context;

    //
    ArrayList<Giay> list;
    GiayDao dao;
    GiayAdapter adapter;
    RecyclerView rcv_home;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewpager_home = view.findViewById(R.id.viewpager_home);
        rcv_home = view.findViewById(R.id.rcv_home);
        circleIndicator_home = view.findViewById(R.id.circle_indicator_home);
        tv_showAll_home = view.findViewById(R.id.tv_showAll_home);


        //
        listAnh = getListPhoto();
        anhAdapte = new AnhAdapte(getActivity(), listAnh);
        viewpager_home.setAdapter(anhAdapte);

        circleIndicator_home.setViewPager(viewpager_home);
        anhAdapte.registerDataSetObserver(circleIndicator_home.getDataSetObserver());


        //hien thi len danh sach
        dao = new GiayDao(getActivity());
        list = dao.getAll();
        adapter = new GiayAdapter(getActivity(), list);
        rcv_home.setAdapter(adapter);

        autoslide();

        //
        tv_showAll_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DanhSachGiayActivity.class);
                getActivity().startActivity(i);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private List<Anh> getListPhoto(){
        List<Anh> list = new ArrayList<>();
        list.add(new Anh(R.drawable.slide1));
        list.add(new Anh(R.drawable.slide2));
        list.add(new Anh(R.drawable.slide3));

        return list;
    }

    private  void autoslide(){

        if(listAnh ==null || listAnh.isEmpty()||viewpager_home==null){
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
                        int currentitem = viewpager_home.getCurrentItem();

                        //totalItem : tong slide
                        int totalItem = listAnh.size() -1;

                        if (currentitem<totalItem){
                            currentitem++;
                            viewpager_home.setCurrentItem(currentitem);
                        }else {
                            viewpager_home.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);
    }

}
