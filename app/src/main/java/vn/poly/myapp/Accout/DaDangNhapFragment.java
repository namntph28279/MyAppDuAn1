package vn.poly.myapp.Accout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.poly.myapp.Activity.MainActivity;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.ChinhSachActivity;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.ChinhSuaThongTinCaNhan;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.DiaChi;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.DieuKhoanVaDieuKien;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.DoiMatKhau;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.TieuChuanCongDong;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.TrungTamHoTro;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.Dao.ThongTinDAO;
import vn.poly.myapp.R;


public class DaDangNhapFragment extends Fragment {

LinearLayout thongtin,diachi,dieukhoan,tieuchuan,chinhsach,trungtamhotro,doimatkhau,dangxuat;
TextView ten,email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_da_dang_nhap, container, false);

       ThongTinDAO tt = new ThongTinDAO(getContext());
        TaiKhoanDAO tk = new TaiKhoanDAO(getContext());

       thongtin =v.findViewById(R.id.chinhsuathongtin);
       diachi = v.findViewById(R.id.diachi);
       dieukhoan = v.findViewById(R.id.dieukhoan);
       tieuchuan = v.findViewById(R.id.tieuchuan);
       chinhsach = v.findViewById(R.id.chinhsach);
       trungtamhotro = v.findViewById(R.id.trungtamhotro);
       doimatkhau = v.findViewById(R.id.doimatkhau);
       dangxuat = v.findViewById(R.id.dangxuat);

       ten = v.findViewById(R.id.texts);
       email = v.findViewById(R.id.txtEmail);

        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        ten.setText(tt.TenChuTK(user));
        email.setText(tt.Eamil(user));

       thongtin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), ChinhSuaThongTinCaNhan.class);
               startActivity(intent);
           }
       });

       diachi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), DiaChi.class);
               startActivity(intent);
           }
       });

       dieukhoan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), DieuKhoanVaDieuKien.class);
               startActivity(intent);
           }
       });
       tieuchuan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), TieuChuanCongDong.class);
               startActivity(intent);
           }
       });
       chinhsach.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), ChinhSachActivity.class);
               startActivity(intent);
           }
       });
       trungtamhotro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), TrungTamHoTro.class);
               startActivity(intent);
           }
       });
       doimatkhau.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), DoiMatKhau.class);
               startActivity(intent);
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

                       SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", getContext().MODE_PRIVATE);
                       SharedPreferences.Editor edit = pref.edit();
                       edit.putString("USERMANE","");
                       edit.putString("PASSWORD","");
                       edit.commit();

                       SharedPreferences pref2 = getActivity().getSharedPreferences("USER_FILES",getContext().MODE_PRIVATE);
                       SharedPreferences.Editor edit2 = pref2.edit();
                       edit2.putString("check","2");
                       edit2.commit();

                       Intent i = new Intent(getActivity(), MainActivity.class);
                       startActivity(i);

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