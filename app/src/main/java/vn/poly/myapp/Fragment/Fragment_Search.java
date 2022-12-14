package vn.poly.myapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import vn.poly.myapp.Adapter.GiayAdapter;
import vn.poly.myapp.Adapter.SearchAdapter;
import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.DTO.Search;
import vn.poly.myapp.Dao.GiayDao;
import vn.poly.myapp.Dao.SearchDao;
import vn.poly.myapp.R;

public class Fragment_Search extends Fragment {
    private EditText ed_search;
    private ImageView img_search;
    private TextView tv_deleteAll;
    private GiayAdapter adapter;
    private RecyclerView rcv_search, rcv_sp_search;
    GiayDao dao;
    private ArrayList<Giay> list;

    private ArrayList<Search> list_s;
    private SearchAdapter adapter_s;
    SearchDao dao_s;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ánh xạ
        rcv_search = view.findViewById(R.id.rcv_search);
        ed_search = view.findViewById(R.id.ed_search);
        rcv_sp_search = view.findViewById(R.id.rcv_sp_search);
        img_search = view.findViewById(R.id.img_search);
        tv_deleteAll = view.findViewById(R.id.tv_deleteAll);


        //Xử lý ds giày
        dao = new GiayDao(getActivity());
        list = dao.getAll();
        adapter = new GiayAdapter(getActivity(), list);
        rcv_sp_search.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //Xử lý ds search
        dao_s = new SearchDao(getActivity());
        list_s = dao_s.getAll();
        adapter_s = new SearchAdapter(getActivity(), list_s);
        rcv_search.setAdapter(adapter_s);
        adapter_s.notifyDataSetChanged();

        //Xoa lich su tim kiem
        tv_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao_s.deleteAll();
                list_s = dao_s.getAll();
                adapter_s.setList(list_s);

            }
        });

        //Xử lý searchview
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ed_search.getText().toString();
                if(text.equals("")){
                    Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
                }else{

                    if (dao_s.insert(text)){
                        adapter.getFilter().filter(text);
                        adapter.notifyDataSetChanged();
                        list_s = dao_s.getAll();
                        adapter_s.setList(list_s);
                        ed_search.setText("");
                        Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

}
