package vn.poly.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.poly.myapp.DTO.Size;
import vn.poly.myapp.R;


public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.SizeViewHolder> {
    private final List<Size> list;
    private final Context context;
    private final ClickSizeColor clickSizeColor;
    private final int id;
    public int itemSelected = -1;

    public OnSizeItemClick getOnSizeItemClick() {
        return onSizeItemClick;
    }

    public void setOnSizeItemClick(OnSizeItemClick onSizeItemClick) {
        this.onSizeItemClick = onSizeItemClick;
    }

    public OnSizeItemClick onSizeItemClick;
    public SizeAdapter(List<Size> list, Context context, ClickSizeColor clickSizeColor,int id) {
        this.list = list;
        this.context = context;
        this.clickSizeColor = clickSizeColor;
        this.id = id;
    }



    @NonNull
    @Override
    public SizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SizeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_size, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SizeViewHolder holder, int position) {
        Size size = list.get(position);
        if (size == null)
            return;

        holder.size.setText(size.getSize());

        if (itemSelected == position) {
            holder.cardView.setCardBackgroundColor(holder.cardView.getResources().getColor(R.color.teal_200));
        } else {
            holder.cardView.setCardBackgroundColor(holder.cardView.getResources().getColor(R.color.white));
        }

        holder.cardView.setOnClickListener(view -> {
            holder.setSingleSelected(holder.getAdapterPosition());
            onSizeItemClick.onClick(position);
            clickSizeColor.callBackSize(size.getSize());

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SizeViewHolder extends RecyclerView.ViewHolder {
        private final TextView size;
        private final CardView cardView;

        public SizeViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.layout_list_size_and_color);
            size = itemView.findViewById(R.id.tv_list_size_and_color);
        }

        private void setSingleSelected(int positionAdapter) {
            if (positionAdapter == RecyclerView.NO_POSITION)
                return;
            notifyItemChanged(itemSelected);
            itemSelected = positionAdapter;
            notifyItemChanged(itemSelected);

        }

    }
}
