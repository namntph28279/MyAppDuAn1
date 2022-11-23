package com.example.myapp.Accout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myapp.Fragment.Fragment_Accout;
import com.example.myapp.R;


public class DaDangNhapFragment extends Fragment {

LinearLayout thongtin,diachi,dieukhoan,tieuchuan,chinhsach,trungtamhotro,doimatkhau,dangxuat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_da_dang_nhap, container, false);

       thongtin =v.findViewById(R.id.chinhsuathongtin);
       diachi = v.findViewById(R.id.diachi);
       dieukhoan = v.findViewById(R.id.dieukhoan);
       tieuchuan = v.findViewById(R.id.tieuchuan);
       chinhsach = v.findViewById(R.id.chinhsach);
       trungtamhotro = v.findViewById(R.id.trungtamhotro);
       doimatkhau = v.findViewById(R.id.doimatkhau);
       dangxuat = v.findViewById(R.id.dangxuat);

       thongtin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       diachi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       dieukhoan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       tieuchuan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       chinhsach.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       trungtamhotro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       doimatkhau.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       dangxuat.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Dialog mdDialog = new Dialog(getContext());
               mdDialog.setContentView(R.layout.layout_exit);
               CardView btnCo, btnKo;
               btnCo = mdDialog.findViewById(R.id.dialogExit_co);
               btnKo = mdDialog.findViewById(R.id.dialogExit_khong);

               btnCo.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       FragmentManager fragmentManager= getParentFragmentManager();
                       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                       ChuaDangNhapFragment chuaDangNhapFragment= new ChuaDangNhapFragment();

                       fragmentTransaction.replace(R.id.acc, chuaDangNhapFragment);
                       fragmentTransaction.addToBackStack(null);
                       fragmentTransaction.commit();
                       mdDialog.dismiss();



                   }
               });
               btnKo.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       mdDialog.dismiss();
                   }
               });
               mdDialog.show();
           }

       });
        return v;
    }
}