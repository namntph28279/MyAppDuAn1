package vn.poly.myapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.R;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder>{
    ArrayList<GioHang> list;
    Context context;
    GioHangDAO dao;
    onClickGioHang monClickGioHang;

    public GioHangAdapter(Context context, onClickGioHang monClickGioHang){
        this.context = context;
        this.monClickGioHang = monClickGioHang;
        dao = new GioHangDAO(context);
    }
    public void setList(ArrayList<GioHang> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphamgiohang, parent, false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.tv_ten.setText(list.get(position).getTenSp());
       holder.tv_gia.setText(list.get(position).getGia());
        holder.sl.setText(list.get(position).getSoLuong());

        Log.d("adatr", "onBindViewHolder: "+list.get(position).getTenSp());
        Log.d("adatr", "onBindViewHolder: "+list.get(position).getGia());
        byte[] hinh = list.get(position).getHinh();

        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.hinhAnh.setImageBitmap(bitmap);

        holder.tuyChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_filter_tc_gio_hang);
                ImageView back = dialog.findViewById(R.id.thoatTC);
                LinearLayout xoa = dialog.findViewById(R.id.xoaGH);

                xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                       monClickGioHang.dialogXoa(list.get(position).getMaSp(),list.get(position).getTenSp());
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
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_ten, tv_gia,sl;
        private ImageView tuyChon,hinhAnh ;


        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ten = itemView.findViewById(R.id.tenSPGH);
            tv_gia = itemView.findViewById(R.id.giaSPGH);
            hinhAnh = itemView.findViewById(R.id.hinhanhGH);
            sl = itemView.findViewById(R.id.solgsp);

            tuyChon = itemView.findViewById(R.id.tuychonGH);
        }
    }
}
