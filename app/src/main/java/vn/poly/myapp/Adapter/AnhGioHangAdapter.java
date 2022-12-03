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

import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.R;

public class AnhGioHangAdapter extends RecyclerView.Adapter<AnhGioHangAdapter.AnhGioHangViewHolder>{
    ArrayList<GioHang> list;
    Context context;
    GioHangDAO dao;

    public AnhGioHangAdapter(Context context, ArrayList<GioHang> list){
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
    public AnhGioHangAdapter.AnhGioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_anh, parent, false);
        return new AnhGioHangAdapter.AnhGioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnhGioHangAdapter.AnhGioHangViewHolder holder, int position) {

        byte[] hinh = list.get(position).getHinh();

        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.hinhAnh.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AnhGioHangViewHolder extends RecyclerView.ViewHolder{

        private ImageView hinhAnh ;

        public AnhGioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhAnh = itemView.findViewById(R.id.srcanh);

        }
    }
}

