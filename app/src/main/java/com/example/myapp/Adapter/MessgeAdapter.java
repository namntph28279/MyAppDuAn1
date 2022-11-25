package com.example.myapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DTO.Message;
import com.example.myapp.R;

import java.util.List;

public class MessgeAdapter extends RecyclerView.Adapter<MessgeAdapter.MessHode> {

    List<Message> lists;
    public void setData(List<Message>list){
        this.lists = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MessHode onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tinnhan,parent,false);

        return new MessHode(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessHode holder, int position) {
Message message = lists.get(position);
if(message==null){
    return;
}
holder.tvMess.setText(message.getMess());
    }

    @Override
    public int getItemCount() {
        if(lists!=null){
            return lists.size();
        }
        return 0;
    }

    public class MessHode extends RecyclerView.ViewHolder{
        private TextView tvMess;

        public MessHode(@NonNull View itemView) {
            super(itemView);

            tvMess = itemView.findViewById(R.id.tv_mess);

        }
    }
}
