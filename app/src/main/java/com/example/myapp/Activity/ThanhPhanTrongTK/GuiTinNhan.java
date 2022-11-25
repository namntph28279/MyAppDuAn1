package com.example.myapp.Activity.ThanhPhanTrongTK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp.DTO.Message;
import com.example.myapp.Adapter.MessgeAdapter;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class GuiTinNhan extends AppCompatActivity {
    RecyclerView recyclerView;
    MessgeAdapter messgeAdapter;
    List<Message> list;
    EditText nhap;
    CardView gui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_tin_nhan);

        nhap= findViewById(R.id.edt_mess);
        gui = findViewById(R.id.btnSent);

        recyclerView = findViewById(R.id.rcv_mess);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        messgeAdapter = new MessgeAdapter();
        messgeAdapter.setData(list);

        recyclerView.setAdapter(messgeAdapter);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> GuiTinNhan.this.onBackPressed());
        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senMese();
            }
        });

      nhap.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              checkkeyboda();
          }
      });
    }

    private void senMese() {

        String messs = nhap.getText().toString().trim();
        if( TextUtils.isEmpty(messs)){
            return;
        }

        list.add(new Message(messs));
        messgeAdapter.notifyDataSetChanged();

        //vi tri cuoi cung
        recyclerView.scrollToPosition(list.size()-1);

        nhap.setText("");
    }

    //dung de lay va hien thi vi tri cuoi cung
    private void checkkeyboda(){
      final   View view = findViewById(R.id.activtRoot);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                view.getWindowVisibleDisplayFrame(r);

                int heid = view.getRootView().getHeight()-r.height();
                if (heid > 0.25*view.getRootView().getHeight()){

                    //check du lieu cuoi cung
                    if (list.size()>0){
                        recyclerView.scrollToPosition(list.size()-1);
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }
}