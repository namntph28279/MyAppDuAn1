package vn.poly.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.poly.myapp.DTO.Giay;
import vn.poly.myapp.DTO.Search;
import vn.poly.myapp.Dao.GiayDao;
import vn.poly.myapp.Dao.SearchDao;
import vn.poly.myapp.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>  {

    ArrayList<Search> list;
    Context context;
    SearchDao dao;

    public SearchAdapter(Context context, ArrayList<Search> list){
        this.context = context;
        this.list = list;
        dao = new SearchDao(context);

    }

    public void setList(ArrayList<Search> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_serach, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.tv_item_search.setText(list.get(position).getSearch());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_item_search;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_search = itemView.findViewById(R.id.tv_row_search);
        }
    }

}
