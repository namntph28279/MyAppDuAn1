package vn.poly.myapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.myapp.Activity.SanPham.SanPhamActivity;
import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.DTO.YeuThich;
import vn.poly.myapp.Dao.GiayDao;
import vn.poly.myapp.Dao.YeuThichDAO;
import vn.poly.myapp.R;


import java.util.ArrayList;

public class GiayAdapter extends RecyclerView.Adapter<GiayAdapter.GiayViewHolder> implements Filterable {
    ArrayList<Giay> list;
    ArrayList<Giay> listOld;
    Context context;
    GiayDao dao;
    YeuThichDAO yeuThichDAO;



    public GiayAdapter(Context context, ArrayList<Giay> list){
        this.context = context;
        this.list = list;
        dao = new GiayDao(context);
        this.listOld = list;
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
        holder.item_card_giay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SanPhamActivity.class);
                //
                Bundle b = new Bundle();
                int id = list.get(position).getId();
                b.putInt("id", id);
                i.putExtra("data", b);
                //
                context.startActivity(i);
            }
        });


        SharedPreferences preferences = context.getSharedPreferences("USER_FILE", context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = context.getSharedPreferences("USER_FILEgg", context.MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");

        byte[] hinh = list.get(position).getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.img_res.setImageBitmap(bitmap);

        holder.img_yeu_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               YeuThichDAO yeuThichDAO = new YeuThichDAO(context);
                YeuThich yt = new YeuThich();
                yt.setEmail(user2);
                yt.setTenDangNhap(user);
                yt.setTenSp(list.get(position).getTen());
                yt.setGia(list.get(position).getGia());
                yt.setHinh(list.get(position).getHinh());

                int b=  yeuThichDAO.themYT(yt);

                if(b==-1){
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
        private ImageView img_res,img_yeu_thich;
        private CardView item_card_giay;

        public GiayViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_ten = itemView.findViewById(R.id.tv_ten);
            tv_gia = itemView.findViewById(R.id.tv_moTa);
            img_res = itemView.findViewById(R.id.img_res);
            img_yeu_thich = itemView.findViewById(R.id.img_yeu_thich);
            item_card_giay = itemView.findViewById(R.id.card_item_giay);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();

                if(search.isEmpty()){
                    list = listOld;
                }else{
                    ArrayList<Giay> list_s = new ArrayList<>();
                    for (Giay g: listOld){
                        if(g.getTen().contains(search.toLowerCase())){
                            list_s.add(g);
                        }
                    }

                    list = list_s;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Giay>) results.values;
                notifyDataSetChanged();
            }
        };
    }


}
