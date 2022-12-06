package vn.poly.myapp.Fragment.GioHang;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import vn.poly.myapp.Activity.MainActivity;
import vn.poly.myapp.Adapter.AnhGioHangAdapter;
import vn.poly.myapp.Adapter.GioHangAdapter;
import vn.poly.myapp.Adapter.TongTienChiTiet;
import vn.poly.myapp.Adapter.VanChuyenAdapter;
import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.DTO.Order;
import vn.poly.myapp.DTO.VanChuyen;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.R;

public class ThanhToan extends BottomSheetDialogFragment {

    ArrayList<GioHang> list = new ArrayList<>();
    GioHangDAO dao;
    AnhGioHangAdapter adapter;
    TongTienChiTiet tt;
    VanChuyenAdapter vanChuyenAdapter;
    TaiKhoanDAO taiKhoanDAO;
    GoogleDAO googleDAO;
    private TextView tvPrice ;
    private ImageView thoat;
    private CardView datHang;
    private Spinner vanChuyen;
    private LinearLayout tongT;
    private RecyclerView rcvanh,ttTong;
    LinearLayout btn_dat;
    GioHangDAO gioHangDAO;


    public static ThanhToan newInstance( ){
        ThanhToan myBottonshet = new ThanhToan();
        return myBottonshet;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog= (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View viewDialog = LayoutInflater.from(getContext()).inflate(R.layout.btn_thanh_toan,null);
        bottomSheetDialog.setContentView(viewDialog);

        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", getContext().MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = getActivity().getSharedPreferences("USER_FILEgg", getContext().MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");
        initView(viewDialog);


        if (taiKhoanDAO.checkLogin(user)>0){
            list =  dao.getALL(user);
            adapter.setList(list);
            rcvanh.setAdapter(adapter);
            tvPrice.setText(String.valueOf(dao.TongTien(user)));
        }else if(googleDAO.checkLogin(user2)>0){

            list =  dao.getALLGG(user2);
            adapter.setList(list);
            rcvanh.setAdapter(adapter);
            tvPrice.setText(String.valueOf(dao.TongTienGG(user2)));
        }


        vanChuyen.setAdapter(vanChuyenAdapter);
        vanChuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tongT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.btn_tong);
                ImageView back = dialog.findViewById(R.id.backt);
                ttTong = dialog.findViewById(R.id.ttong);
                TextView giatc = dialog.findViewById(R.id.giatc);
                tt = new TongTienChiTiet(getActivity(),list);

                if (taiKhoanDAO.checkLogin(user)>0){
                    list =  dao.getALL(user);
                    giatc.setText(String.valueOf(dao.TongTien(user)));
                }else if(googleDAO.checkLogin(user2)>0){
                    list =  dao.getALLGG(user2);
                    giatc.setText(String.valueOf(dao.TongTienGG(user2)));
                }

                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
                ttTong.setLayoutManager(layoutManager);
                tt.setList(list);
                ttTong.setAdapter(tt);
                dialog.show();

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });

        btn_dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleDAO.checkLogin(user2)>0){
                    SharedPreferences pref2 = getActivity().getSharedPreferences("Google",getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor edit2 = pref2.edit();
                    edit2.putString("checkgg","8");
                    edit2.commit();
                    SharedPreferences pref = getActivity().getSharedPreferences("USER_FILES",getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("check","7");
                    edit.commit();
                    gioHangDAO.deleteAll(user2);
                    gioHangDAO.getALLGG(user2);
                    Toast.makeText(getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(), MainActivity.class);
                    startActivity(i);

                }else if(taiKhoanDAO.checkLogin(user)>0){
                    SharedPreferences pref2 = getActivity().getSharedPreferences("Google",getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor edit2 = pref2.edit();
                    edit2.putString("checkgg","8");
                    edit2.commit();
                    SharedPreferences pref = getActivity().getSharedPreferences("USER_FILES",getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("check","7");
                    edit.commit();
                    gioHangDAO.deleteAllTK(user);
                    gioHangDAO.getALL(user);
                    Toast.makeText(getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(), MainActivity.class);
                    startActivity(i);
                }

                bottomSheetDialog.dismiss();
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        return bottomSheetDialog;
    }

    private void initView(View view){
        gioHangDAO = new GioHangDAO(getContext());
        btn_dat = view.findViewById(R.id.btnDat);
        googleDAO = new GoogleDAO(getContext());
        taiKhoanDAO = new TaiKhoanDAO(getContext());
        vanChuyenAdapter = new VanChuyenAdapter(getActivity(),R.layout.spiner_vanchuyen,getListvc());
        adapter = new AnhGioHangAdapter(getActivity(),list);
        dao = new GioHangDAO(getContext());
        vanChuyen = view.findViewById(R.id.spvc);
        tongT = view.findViewById(R.id.Tongt);
        tvPrice = view.findViewById(R.id.tongdh);
        datHang = view.findViewById(R.id.thanhtoandh);
        thoat = view.findViewById(R.id.exte);
        rcvanh = view.findViewById(R.id.rcvanh);
    }

    private List<VanChuyen> getListvc() {
        List<VanChuyen> list = new ArrayList<>();
        list.add(new VanChuyen("Giao hàng tiết kiệm (GHTK)"));
        list.add(new VanChuyen("Giao hàng nhanh (GHN)"));
        list.add(new VanChuyen("Vietnam Post (VNPost)"));
        list.add(new VanChuyen("247 Express"));
        list.add(new VanChuyen("Viettel Post"));
        return list;

    }


}
