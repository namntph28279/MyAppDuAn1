package vn.poly.myapp.Adapter;

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

import java.util.ArrayList;

import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.R;

public class TongTienChiTiet extends RecyclerView.Adapter<TongTienChiTiet.TongTienChiTietViewHolder>{
    ArrayList<GioHang> list;
    Context context;
    GioHangDAO dao;

    public TongTienChiTiet(Context context, ArrayList<GioHang> list){
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
    public TongTienChiTiet.TongTienChiTietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tong, parent, false);
        return new TongTienChiTiet.TongTienChiTietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TongTienChiTiet.TongTienChiTietViewHolder holder, int position) {

        holder.soLuong.setText(list.get(position).getSoLuong());
        holder.TenSp.setText(list.get(position).getTenSp());
        holder.gia.setText(list.get(position).getGia());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TongTienChiTietViewHolder extends RecyclerView.ViewHolder{

        private TextView soLuong,TenSp,gia ;

        public TongTienChiTietViewHolder(@NonNull View itemView) {
            super(itemView);
            soLuong = itemView.findViewById(R.id.soluongt);
            TenSp = itemView.findViewById(R.id.tenSpt);
            gia = itemView.findViewById(R.id.giaT);

        }
    }
}
