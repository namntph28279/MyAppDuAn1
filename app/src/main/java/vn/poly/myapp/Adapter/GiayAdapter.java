package vn.poly.myapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.myapp.Activity.ManHinhDangNhapDangKy.ManHinhDangNhap;
import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.Dao.GiayDao;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.R;


import java.util.ArrayList;

public class GiayAdapter extends RecyclerView.Adapter<GiayAdapter.GiayViewHolder> {
    ArrayList<Giay> list;
    Context context;
    GiayDao dao;



    public GiayAdapter(Context context, ArrayList<Giay> list){
        this.context = context;
        this.list = list;
        dao = new GiayDao(context);
    }

    public void setList(ArrayList<Giay> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GiayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_giay, parent, false);
        return new GiayViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull GiayViewHolder holder, int position) {
        holder.tv_ten.setText(list.get(position).getTen());
        holder.tv_gia.setText(list.get(position).getGia()+"");

        SharedPreferences preferences = context.getSharedPreferences("USER_FILE", context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = context.getSharedPreferences("USER_FILEgg", context.MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");

        byte[] hinh = list.get(position).getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.img_res.setImageBitmap(bitmap);

        holder.img_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHangDAO gioHangDAO = new GioHangDAO(context);
                Toast.makeText(v.getContext(), ""+list.get(position).getTen()+list.get(position).getGia()+list.get(position).getHinh(), Toast.LENGTH_SHORT).show();

                GioHang gh = new GioHang();
                gh.setTenDangNhap(user);
                gh.setEmail(user2);
                gh.setTenSp(list.get(position).getTen());
                gh.setGia(list.get(position).getGia());
                gh.setHinh(list.get(position).getHinh());
                gh.setSoLuong("1");

                Log.d("abc", "onBindViewHolder: "+list.get(position).getTen());
                Log.d("abc", "onBindViewHolder: "+list.get(position).getGia());
                Log.d("abc", "onBindViewHolder: "+user);


                int a=  gioHangDAO.themTK(gh);

                if(a==-1){
                    Toast.makeText(context,"Add Thất Bại",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"Add Thành Công",Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GiayViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_ten, tv_gia;
        private ImageView img_res,img_giohang;

        public GiayViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_ten = itemView.findViewById(R.id.tv_ten);
            tv_gia = itemView.findViewById(R.id.tv_moTa);
            img_res = itemView.findViewById(R.id.img_res);
            img_giohang = itemView.findViewById(R.id.imggiohang);
        }
    }
}
