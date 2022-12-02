package vn.poly.myapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import vn.poly.myapp.Adapter.GioHangAdapter;
import vn.poly.myapp.DTO.GioHang;
import vn.poly.myapp.Dao.GioHangDAO;
import vn.poly.myapp.R;

public class Fragment_Cart extends Fragment {
    ArrayList<GioHang> list = new ArrayList<>();
    GioHangDAO dao;
    GioHangAdapter adapter;
    RecyclerView rcv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        dao = new GioHangDAO(getContext());
        rcv = view.findViewById(R.id.rcvGH);

       list =  dao.getALL();
       adapter = new GioHangAdapter(getActivity(),list);
        adapter.setList(list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(layoutManager);

       rcv.setAdapter(adapter);



        return view;
    }


}
