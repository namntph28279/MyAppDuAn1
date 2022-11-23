package com.example.myapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DTO.Giay;
import com.example.myapp.Dao.GiayDao;
import com.example.myapp.R;

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

    @Override
    public void onBindViewHolder(@NonNull GiayViewHolder holder, int position) {
        holder.tv_ten.setText(list.get(position).getTen());
        holder.tv_gia.setText(list.get(position).getGia()+"");


        byte[] hinh = list.get(position).getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.img_res.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GiayViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_ten, tv_gia;
        private ImageView img_res;

        public GiayViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ten = itemView.findViewById(R.id.tv_ten);
            tv_gia = itemView.findViewById(R.id.tv_moTa);
            img_res = itemView.findViewById(R.id.img_res);
        }
    }
}
