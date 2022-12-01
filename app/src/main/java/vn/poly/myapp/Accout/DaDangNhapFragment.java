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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import vn.poly.myapp.Activity.MainActivity;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.ChinhSachActivity;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.ChinhSuaThongTinCaNhan;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.DiaChi;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.DieuKhoanVaDieuKien;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.DoiMatKhau;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.TieuChuanCongDong;
import vn.poly.myapp.Activity.ThanhPhanTrongTK.TrungTamHoTro;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.Dao.ThongTinDAO;
import vn.poly.myapp.R;


public class DaDangNhapFragment extends Fragment {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
LinearLayout thongtin,diachi,dieukhoan,tieuchuan,chinhsach,trungtamhotro,doimatkhau,dangxuat;
TextView ten,email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_da_dang_nhap, container, false);

       ThongTinDAO tt = new ThongTinDAO(getContext());
        TaiKhoanDAO tk = new TaiKhoanDAO(getContext());
        GoogleDAO gg = new GoogleDAO(getContext());
       thongtin =v.findViewById(R.id.chinhsuathongtin);
       diachi = v.findViewById(R.id.diachi);
       dieukhoan = v.findViewById(R.id.dieukhoan);
       tieuchuan = v.findViewById(R.id.tieuchuan);
       chinhsach = v.findViewById(R.id.chinhsach);
       trungtamhotro = v.findViewById(R.id.trungtamhotro);
       doimatkhau = v.findViewById(R.id.doimatkhau);
       dangxuat = v.findViewById(R.id.dangxuat);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getContext(),gso);
       ten = v.findViewById(R.id.texts);
       email = v.findViewById(R.id.txtEmail);
        GoogleDAO googleDAO = new GoogleDAO(getContext());
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");
        String pass = preferences.getString("PASSWORD", "");

        SharedPreferences preferences3 = getActivity().getSharedPreferences("USER_FILEgg", Context.MODE_PRIVATE);
        String users = preferences3.getString("email", "");

        if (tk.checkLogin(user,pass)>0){
            ten.setText(tt.TenChuTK(user));
            email.setText(tt.Eamil(user));
        }else {
            ten.setText(gg.TenChuTK(users));
            email.setText(users);
        }

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

              if (googleDAO.checkLogin(users)>0){


                  Dialog mdDialog = new Dialog(getContext());
                  mdDialog.setContentView(R.layout.layout_exit);
                  CardView btnCo, btnKo;
                  btnCo = mdDialog.findViewById(R.id.dialogExit_co);
                  btnKo = mdDialog.findViewById(R.id.dialogExit_khong);

                  btnCo.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {

                          gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(Task<Void> task) {

                                  SharedPreferences pref3 = getActivity().getSharedPreferences("USER_FILEgg", getContext().MODE_PRIVATE);
                                  SharedPreferences.Editor edit3 = pref3.edit();
                                  edit3.putString("email","");
                                  edit3.commit();


                                  SharedPreferences pref4 = getActivity().getSharedPreferences("Google",getContext().MODE_PRIVATE);
                                  SharedPreferences.Editor edit4 = pref4.edit();
                                  edit4.putString("checkgg","2");
                                  edit4.commit();

                                  startActivity(new Intent(getContext(), MainActivity.class));
                              }
                          });

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


              }else if(tk.checkLogin(user,pass)>0){
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
           }

       });
        return v;
    }
}