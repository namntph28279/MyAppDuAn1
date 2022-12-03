package vn.poly.myapp.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import vn.poly.myapp.Adapter.YeuThichAdapter;
import vn.poly.myapp.Adapter.onClickGioHang;
import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.DTO.YeuThich;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.Dao.YeuThichDAO;
import vn.poly.myapp.Fragment.GioHang.ThanhToan;
import vn.poly.myapp.R;

public class Fragment_Favourite extends Fragment implements onClickGioHang {
    ArrayList<YeuThich> list = new ArrayList<>();

    GoogleDAO googleDAO;
    TaiKhoanDAO taiKhoanDAO;
    YeuThichDAO yeuThichDAO;
    YeuThichAdapter adapter;
    RecyclerView rcv;
    TextView sl;
    ImageView sapXep;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        taiKhoanDAO = new TaiKhoanDAO(getContext());
        googleDAO = new GoogleDAO(getContext());
        yeuThichDAO = new YeuThichDAO(getContext());
        sapXep = view.findViewById(R.id.tuyChonSapXepYT);
        rcv = view.findViewById(R.id.rcvYT);
        sl = view.findViewById(R.id.soluongYT);
        getData();
        sapXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
        return view;
    }

    private void dialog() {


        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_filter_sx);
        ImageView back = dialog.findViewById(R.id.thoatSX);
        LinearLayout cao = dialog.findViewById(R.id.Cao);
        LinearLayout thap = dialog.findViewById(R.id.Thap);

        cao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaoThap();
                dialog.dismiss();
            }
        });
        thap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThapCao();
                dialog.dismiss();
            }
        });
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

    private void CaoThap() {
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");
        SharedPreferences preferences2 = getActivity().getSharedPreferences("USER_FILEgg", Context.MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");

        if (taiKhoanDAO.checkLogin(user)>0){
            int a = yeuThichDAO.checkHang(user);
            sl.setText(String.valueOf(a));
            list = yeuThichDAO.getALLGiam(user);

        }else if(googleDAO.checkLogin(user2)>0){
            int a = yeuThichDAO.checkHangGG(user2);
            sl.setText(String.valueOf(a));
            list = yeuThichDAO.getALLGiamGG(user2);

        }

        adapter = new YeuThichAdapter(getActivity(), this);
        adapter.setList(list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
    }
    private void ThapCao() {
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = getActivity().getSharedPreferences("USER_FILEgg", Context.MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");

        if (taiKhoanDAO.checkLogin(user)>0){
            int a = yeuThichDAO.checkHang(user);
            sl.setText(String.valueOf(a));
            list = yeuThichDAO.getALLTang(user);

        }else if(googleDAO.checkLogin(user2)>0){
            int a = yeuThichDAO.checkHangGG(user2);
            sl.setText(String.valueOf(a));
            list = yeuThichDAO.getALLTangGG(user2);

        }

        adapter = new YeuThichAdapter(getActivity(), this);
        adapter.setList(list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);

    }
    private void getData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = getActivity().getSharedPreferences("USER_FILEgg", Context.MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");
        if (taiKhoanDAO.checkLogin(user)>0){
            int a = yeuThichDAO.checkHang(user);
            sl.setText(String.valueOf(a));
            list = yeuThichDAO.getALL(user);


        }else if(googleDAO.checkLogin(user2)>0){
            int a = yeuThichDAO.checkHangGG(user2);
            sl.setText(String.valueOf(a));
            list =  yeuThichDAO.getALLGG(user2);

        }

        adapter = new YeuThichAdapter(getActivity(), this);
        adapter.setList(list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);


    }


    @Override
    public void dialogXoa(int id, String tenGiay) {
        AlertDialog.Builder mDialog = new AlertDialog.Builder(getContext());

        mDialog.setMessage("Bạn Có muốn Xóa Giay : " + tenGiay + " Không ?");
        mDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int kq = yeuThichDAO.delete(id);
                if (kq == -1) {
                    Toast.makeText(getContext(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
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
