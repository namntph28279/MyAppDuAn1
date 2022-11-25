package com.example.myapp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.Accout.ChuaDangNhapFragment;
import com.example.myapp.Accout.DaDangNhapFragment;
import com.example.myapp.Dao.TaiKhoanDAO;
import com.example.myapp.R;

public class Fragment_Accout extends Fragment {
     TaiKhoanDAO taiKhoanDAO;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_accout, container, false);

            taiKhoanDAO = new TaiKhoanDAO(getActivity());
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");
        String pass = preferences.getString("PASSWORD", "");


        Log.d("zzzz", "onCreateView: "+user + pass);

        FragmentManager fragmentManager= getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (taiKhoanDAO.checkLogin(user,pass)>0){
            DaDangNhapFragment daDangNhapFragment= new DaDangNhapFragment();
            fragmentTransaction.replace(R.id.acc,daDangNhapFragment);
            fragmentTransaction.commit();

        }else {

            ChuaDangNhapFragment chuaDangNhapFragment= new ChuaDangNhapFragment();
            fragmentTransaction.replace(R.id.acc,chuaDangNhapFragment);
            fragmentTransaction.commit();

        }
           return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}