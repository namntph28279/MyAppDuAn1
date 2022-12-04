package vn.poly.myapp.Fragment.GioHang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
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


        }else if(googleDAO.checkLogin(user2)>0){
            int a = dao.checkHangGG(user2);
            sl.setText(String.valueOf(a));
            int b= dao.TongTienGG(user2);
            tongTien.setText(String.valueOf(b));
            list =  dao.getALLGG(user2);

        }

        adapter = new GioHangAdapter(getActivity(),this);
        adapter.setList(list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);

    }


    @Override
    public void dialogXoa(int id , String tenGiay) {
        AlertDialog.Builder mDialog = new AlertDialog.Builder(getContext());

        mDialog.setMessage("Bạn Có muốn Xóa Giay : "+tenGiay+" Không ?");
        mDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int kq = dao.delete(id);
                if(kq==-1){
                    Toast.makeText(getContext(),"Xóa Thất Bại",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Xóa Thành Công",Toast.LENGTH_SHORT).show();
                }
                getData();
            }
        });
        mDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        mDialog.show();
    }
}
