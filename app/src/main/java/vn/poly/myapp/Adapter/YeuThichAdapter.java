package vn.poly.myapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.DTO.Size;
import vn.poly.myapp.DTO.YeuThich;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.Dao.YeuThichDAO;
import vn.poly.myapp.R;

public class YeuThichAdapter extends RecyclerView.Adapter<YeuThichAdapter.YeuThichAdapterViewHolder>{
    ArrayList<YeuThich> list;
    Context context;
    YeuThichDAO dao;
    onClickGioHang monClickGioHang;

    public YeuThichAdapter(Context context, onClickGioHang monClickGioHang){
        this.context = context;
        this.monClickGioHang = monClickGioHang;
        dao = new YeuThichDAO(context);
    }



    public void setList(ArrayList<YeuThich> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public YeuThichAdapter.YeuThichAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_fa, parent, false);
        return new YeuThichAdapter.YeuThichAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YeuThichAdapter.YeuThichAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_ten.setText(list.get(position).getTenSp());
        holder.tv_gia.setText(list.get(position).getGia());

        Log.d("adatr", "onBindViewHolder: "+list.get(position).getTenSp());
        Log.d("adatr", "onBindViewHolder: "+list.get(position).getGia());
        byte[] hinh = list.get(position).getHinh();

        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.hinhAnh.setImageBitmap(bitmap);



        holder.tuyChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_filter_tc);

                ImageView back = dialog.findViewById(R.id.ic_cleargh);
                LinearLayout xoa = dialog.findViewById(R.id.XoaYT);
                LinearLayout line_add = dialog.findViewById(R.id.line_add);

                line_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        SharedPreferences preferences = context.getSharedPreferences("USER_FILE",context.MODE_PRIVATE);
//                        String user = preferences.getString("USERMANE", "");
//
//                        SharedPreferences preferences2 = context.getSharedPreferences("USER_FILEgg", context.MODE_PRIVATE);
//                        String user2 = preferences2.getString("email", "");
//
//                        GioHangDAO gioHangDAO = new GioHangDAO(context);
//                        GioHang gh = new GioHang();
//                        Giay g = new Giay();
//                        Size s = new Size();
//                        gh.setEmail(user2);
//                        gh.setTenDangNhap(user);
//                        gh.setTenSp(g.getTen());
//                        gh.setGia(g.getGia());
//                        gh.setHinh(g.getHinh());
//                        gh.setKichCo(s.getSize());
//                        gh.setSoLuong("");
//
//                        int b=  gioHangDAO.themTK(gh);
//
//                        if(b==-1){
//                            Toast.makeText(context,"Add Thất Bại",Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(context,"Add Thành Công",Toast.LENGTH_SHORT).show();
//                        }
                    }
                });

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
        holder.themGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class YeuThichAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_ten, tv_gia;
        private ImageView tuyChon,hinhAnh,sapXep ;
        MaterialCardView themGio;


        public YeuThichAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ten = itemView.findViewById(R.id.tenYT);
            tv_gia = itemView.findViewById(R.id.giaYT);
            hinhAnh = itemView.findViewById(R.id.hinhanhYT);
             themGio = itemView.findViewById(R.id.themGio);
            sapXep = itemView.findViewById(R.id.tuyChonSapXepYT);
            tuyChon = itemView.findViewById(R.id.tuychonYT);
        }
    }
}