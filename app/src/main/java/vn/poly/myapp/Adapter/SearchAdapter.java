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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {
    ArrayList<Search> listOld;
    ArrayList<Search> list;
    Context context;
    SearchDao dao;

    public SearchAdapter(Context context, ArrayList<Search> list){
        this.context = context;
        this.list = list;
        dao = new SearchDao(context);
        this.listOld = list;
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();

                if(search.isEmpty()){
                    list = listOld;
                }else{
                    ArrayList<Search> list01 = new ArrayList<>();
                    for (Search s: listOld){
                        if(s.getSearch().contains(search.toLowerCase())){
                            list01.add(s);
                        }
                    }

                    list = list01;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Search>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
