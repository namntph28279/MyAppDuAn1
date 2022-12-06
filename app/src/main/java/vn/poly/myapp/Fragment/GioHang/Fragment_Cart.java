package vn.poly.myapp.Fragment.GioHang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import vn.poly.myapp.Adapter.GioHangAdapter;
import vn.poly.myapp.Adapter.onClickGioHang;
import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.DTO.Order;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.R;

public class Fragment_Cart extends Fragment implements onClickGioHang {
    ArrayList<GioHang> list = new ArrayList<>();
    GioHangDAO dao;
    GoogleDAO googleDAO;
    TaiKhoanDAO taiKhoanDAO;
    GioHangAdapter adapter;
    RecyclerView rcv;
    TextView sl,tongTien;
    CardView thanhToan;
    LinearLayout gh,nh,tt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);


        dao = new GioHangDAO(getContext());
        googleDAO = new GoogleDAO(getContext());
        taiKhoanDAO = new TaiKhoanDAO(getContext());
        rcv = view.findViewById(R.id.rcvGH);
        sl = view.findViewById(R.id.soluongGH);
        tongTien = view.findViewById(R.id.giaGH);
        thanhToan = view.findViewById(R.id.thanhtoan);
        gh = view.findViewById(R.id.ghcc);
        nh = view.findViewById(R.id.mhgh);
        tt = view.findViewById(R.id.thanhtoa);

         getData();

        thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhToan myBottonshet = ThanhToan.newInstance();
                myBottonshet.show(getChildFragmentManager(),myBottonshet.getTag());
                //khi an ngoai se khong tat
                myBottonshet.setCancelable(false);
            }
        });

        return view;
    }

    private void getData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");
        SharedPreferences preferences2 = getActivity().getSharedPreferences("USER_FILEgg", Context.MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");

        if (taiKhoanDAO.checkLogin(user)>0){
            int a = dao.checkHang(user);
            sl.setText(String.valueOf(a));
            int b= dao.TongTien(user);
            tongTien.setText(String.valueOf(b));
            list =  dao.getALL(user);

            FragmentManager fragmentManager= getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(a==0){
                nh.setVisibility(View.INVISIBLE);
                tt.setVisibility(View.INVISIBLE);
                ChuaCoGHFragment chuaCoGHFragment= new ChuaCoGHFragment();
                fragmentTransaction.replace(R.id.ghcc,chuaCoGHFragment);
                fragmentTransaction.commit();
            }


        }else if(googleDAO.checkLogin(user2)>0){
            int a = dao.checkHangGG(user2);
            sl.setText(String.valueOf(a));
            int b= dao.TongTienGG(user2);
            tongTien.setText(String.valueOf(b));
            list =  dao.getALLGG(user2);

            FragmentManager fragmentManager= getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if(a==0){
                nh.setVisibility(View.INVISIBLE);
                tt.setVisibility(View.INVISIBLE);
                ChuaCoGHFragment chuaCoGHFragment= new ChuaCoGHFragment();
                fragmentTransaction.replace(R.id.ghcc,chuaCoGHFragment);
                fragmentTransaction.commit();
            }

        }else {

            FragmentManager fragmentManager= getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                nh.setVisibility(View.INVISIBLE);
                tt.setVisibility(View.INVISIBLE);
                ChuaCoGHFragment chuaCoGHFragment= new ChuaCoGHFragment();
                fragmentTransaction.replace(R.id.ghcc,chuaCoGHFragment);
                fragmentTransaction.commit();

        }



        adapter = new GioHangAdapter(getActivity(),this);
        adapter.setList(list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);

    }



    @Override
    public void dialogXoa(int id , String tenGiay) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_delete);

        CardView btnCo, btnKo;
        TextView ten;
        btnCo = dialog.findViewById(R.id.dialogExit_coa);
        btnKo = dialog.findViewById(R.id.dialogExit_khonga);
        ten = dialog.findViewById(R.id.tenGiayaa);

        ten.setText(tenGiay);

        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kq = dao.delete(id);
                if (kq == -1) {
                    Toast.makeText(getContext(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                }
                getData();
                dialog.dismiss();
            }
        });
        btnKo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
