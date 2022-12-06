package vn.poly.myapp.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

import vn.poly.myapp.Adapter.GiayAdapter;
import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.Dao.GiayDao;
import vn.poly.myapp.R;

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
    Bitmap bitmap1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_danhsach_giay, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("dfghj","zxcghj");
//        list = dao.getAll();
//        adapter = new GiayAdapter(getActivity(),list);
//        rcv_sp.setAdapter(adapter);
//        rcv_sp.setLayoutManager(new GridLayoutManager(getActivity(),2));
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
                showDialog();
            }
        });




        //hien thi len danh sach
        dao = new GiayDao(getActivity());
        list = dao.getAll();
        adapter = new GiayAdapter(getActivity(), list);
        Log.e("SSS111", " + " + list.size());
        rcv_sp.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == getActivity().RESULT_OK && data != null){
             bitmap1 = (Bitmap) data.getExtras().get("data");
             showDialog();
        }

        if(requestCode == REQUEST_CODE_FOLDER && resultCode == getActivity().RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                 bitmap1 = BitmapFactory.decodeStream(inputStream);
                 showDialog();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
   // Dialog dialog;
    private void showDialog(){
        EditText ed_ten, ed_gia;
        ImageView img_camera, img_up;
        Button btn_add, btn_cancel;

        //
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog_them_giay,null);
        dialog.setView(view);
        dialog.show();


        //anh xa
        ed_gia =view.findViewById(R.id.ed_gia);
        ed_ten = view.findViewById(R.id.ed_ten);
        btn_add = view.findViewById(R.id.btn_add);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        img_img = view.findViewById(R.id.img_img);
        img_camera = view.findViewById(R.id.img_camera);
        img_up = view.findViewById(R.id.img_up);

        if (bitmap1 != null){
            img_img.setImageBitmap(bitmap1);
        }


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = ed_ten.getText().toString();

                //chuyen data imageView -> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img_img.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] hinhAnh = byteArrayOutputStream.toByteArray();

                if(ten.equals("") || ed_gia.getText().toString().equals("") || hinhAnh == null){
                    Toast.makeText(getActivity(), "Chua nhap du lieu", Toast.LENGTH_SHORT).show();
                }else{
                    String gia =  ed_gia.getText().toString();

                    Log.d("hinhanh", "onClick: "+ hinhAnh);
                    if(dao.insert(ten, gia, hinhAnh, 1)){
                        Toast.makeText(getActivity(), "Them thanh cong", Toast.LENGTH_SHORT).show();

                        list = dao.getAll();
                        Log.e("SSS", " + " + list.size());
                        adapter = new GiayAdapter(getActivity(),list);
                        rcv_sp.setAdapter(adapter);

                        //dialog.hide();
                    }
                }
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_gia.setText("");
                ed_ten.setText("");
                dialog.dismiss();
            }
        });

        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, REQUEST_CODE_CAMERA);

            }
        });

        img_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST_CODE_FOLDER );
            }
        });

    }
}
