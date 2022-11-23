package com.example.myapp.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Adapter.GiayAdapter;
import com.example.myapp.DTO.Giay;
import com.example.myapp.Dao.GiayDao;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fragment_DanhSach_Giay extends Fragment {
    ArrayList<Giay> list;
    GiayDao dao;
    GiayAdapter adapter;
    RecyclerView rcv_sp;
    FloatingActionButton fab;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;
    ImageView img_img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_danhsach_giay, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        list = dao.getAll();
        adapter.setList(list);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == getActivity().RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_img.setImageBitmap(bitmap);
        }

        if(requestCode == REQUEST_CODE_FOLDER && resultCode == getActivity().RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //anh xa
        rcv_sp = view.findViewById(R.id.rcv_sp);
        fab = view.findViewById(R.id.fab);

        //
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed_ten, ed_gia;
                ImageView img_camera, img_up;
                Button btn_add, btn_cancel;

                //
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
                View view = inflater.inflate(R.layout.custom_dialog_them_giay,null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                //anh xa
                ed_gia =view.findViewById(R.id.ed_gia);
                ed_ten = view.findViewById(R.id.ed_ten);
                btn_add = view.findViewById(R.id.btn_add);
                btn_cancel = view.findViewById(R.id.btn_cancel);
                img_img = view.findViewById(R.id.img_res);

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = ed_ten.getText().toString();
                        float gia = Float.parseFloat(ed_gia.getText().toString());

                        //chuyen data imageView -> byte[]
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) img_img.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] hinhAnh = byteArrayOutputStream.toByteArray();

                        if(ten.equals("") || ed_gia.getText().toString().equals("")){
                            Toast.makeText(getActivity(), "Chua nhap du lieu", Toast.LENGTH_SHORT).show();
                        }else{
                            if(dao.insert(ten, gia, hinhAnh, 1)){
                                Toast.makeText(getActivity(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ed_gia.setText("");
                        ed_ten.setText("");
                    }
                });

            }
        });


        //hien thi len danh sach
        dao = new GiayDao(getActivity());
        list = dao.getAll();
        adapter = new GiayAdapter(getActivity(), list);

        rcv_sp.setAdapter(adapter);




    }
}
