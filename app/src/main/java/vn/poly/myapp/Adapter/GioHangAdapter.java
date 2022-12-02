package vn.poly.myapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.R;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder>{
    ArrayList<GioHang> list;
    Context context;
    GioHangDAO dao;

    public GioHangAdapter(Context context, ArrayList<GioHang> list){
        this.context = context;
        this.list = list;
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
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
       holder.tv_ten.setText(list.get(position).getTenSp());
       holder.tv_gia.setText(list.get(position).getGia());
        Log.d("adatr", "onBindViewHolder: "+list.get(position).getTenSp());
        Log.d("adatr", "onBindViewHolder: "+list.get(position).getGia());
        byte[] hinh = list.get(position).getHinh();

        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.hinhAnh.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_ten, tv_gia;
        private ImageView tuyChon,hinhAnh ;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ten = itemView.findViewById(R.id.tenSPGH);
            tv_gia = itemView.findViewById(R.id.giaSPGH);
            hinhAnh = itemView.findViewById(R.id.hinhanhGH);
            tuyChon = itemView.findViewById(R.id.tuychonGH);
        }
    }
}
