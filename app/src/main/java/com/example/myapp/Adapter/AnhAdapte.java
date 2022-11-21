package com.example.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.myapp.DTO.Anh;
import com.example.myapp.R;

import java.util.List;

public class AnhAdapte extends PagerAdapter {

    private Context mcontext;
    private List<Anh> listAnh;

    public AnhAdapte(Context mcontext, List<Anh> listAnh) {
        this.mcontext = mcontext;
        this.listAnh = listAnh;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);

        ImageView imgPhoto = view.findViewById(R.id.img_photo);

        Anh photo = listAnh.get(position);

        //neu anh khac null thi se tien hanh set anh cho ImageView
        if(photo!= null){
            Glide.with(mcontext).load(photo.getAnh()).into(imgPhoto);
        }
         //add view to viewgroup
         container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        //neu list anh khac null thi se tra ve so luong
        if (listAnh != null){
            return listAnh.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Remove view
        container.removeView((View) object);
    }
}
