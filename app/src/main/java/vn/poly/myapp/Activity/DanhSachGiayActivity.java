package vn.poly.myapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import vn.poly.myapp.Accout.DaDangNhapFragment;
import vn.poly.myapp.Adapter.GiayAdapter;
import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.Dao.GiayDao;
import vn.poly.myapp.Dao.GoogleDAO;
import vn.poly.myapp.Dao.TaiKhoanDAO;
import vn.poly.myapp.R;

public class DanhSachGiayActivity extends AppCompatActivity {
    ArrayList<Giay> list;
    GiayDao dao;
    GiayAdapter adapter;
    RecyclerView rcv_ds_giay;
    FloatingActionButton fab_giay;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;
    ImageView img_img;
    Bitmap bitmap1;
    TaiKhoanDAO taiKhoanDAO;
    GoogleDAO googleDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_giay);
        init();
        setUpToolBar();

        taiKhoanDAO = new TaiKhoanDAO(this);
        googleDAO = new GoogleDAO(this);

        fab_giay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        //Xử lý ẩn hiện fab
        SharedPreferences preferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERMANE", "");

        SharedPreferences preferences2 = getSharedPreferences("USER_FILEgg", MODE_PRIVATE);
        String user2 = preferences2.getString("email", "");


        if (taiKhoanDAO.checkLogin(user)>0  || googleDAO.checkLogin(user2)>0){
           fab_giay.setVisibility(View.VISIBLE);

        }else{
            fab_giay.setVisibility(View.INVISIBLE);
        }



        //hien thi len danh sach
        dao = new GiayDao(this);
        list = dao.getAll();
        adapter = new GiayAdapter(this, list);
        rcv_ds_giay.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
                list = dao.getAll();
                adapter = new GiayAdapter(this,list);
    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolBar_ds_giay);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> DanhSachGiayActivity.this.onBackPressed());
    }

    private void init(){
        taiKhoanDAO = new TaiKhoanDAO(this);
        rcv_ds_giay = findViewById(R.id.rcv_ds_giay);
        fab_giay = findViewById(R.id.fab_ds_giay);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            bitmap1 = (Bitmap) data.getExtras().get("data");
            showDialog();
        }

        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmap1 = BitmapFactory.decodeStream(inputStream);
                showDialog();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showDialog(){
        EditText ed_ten, ed_gia;
        ImageView img_camera, img_up;
        Button btn_add, btn_cancel;

        //
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
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
                    Toast.makeText(getBaseContext(), "Chua nhap du lieu", Toast.LENGTH_SHORT).show();
                }else{
                    String gia =  ed_gia.getText().toString();

                    Log.d("hinhanh", "onClick: "+ hinhAnh);
                    if(dao.insert(ten, gia, hinhAnh, 1)){
                        Toast.makeText(getBaseContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();

                        list = dao.getAll();
                        Log.e("SSS", " + " + list.size());
                        adapter = new GiayAdapter(getBaseContext(),list);
                        rcv_ds_giay.setAdapter(adapter);

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