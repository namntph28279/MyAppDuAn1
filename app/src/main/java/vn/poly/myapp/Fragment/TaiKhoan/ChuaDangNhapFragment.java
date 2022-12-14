package vn.poly.myapp.Fragment.TaiKhoan;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.poly.myapp.Activity.ManHinhDangNhapDangKy.ManHinhDangKy;
import vn.poly.myapp.Activity.ManHinhDangNhapDangKy.ManHinhDangNhap;
import vn.poly.myapp.R;


public class ChuaDangNhapFragment extends Fragment {
    private CardView btnLogin,btnSingup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_chua_dang_nhap, container, false);
       btnLogin = v.findViewById(R.id.btnLogin);
       btnSingup = v.findViewById(R.id.btnSignUp);

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), ManHinhDangNhap.class);
               startActivity(intent);
           }
       });
       btnSingup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), ManHinhDangKy.class);
               startActivity(intent);
           }
       });
        return v;
    }
}