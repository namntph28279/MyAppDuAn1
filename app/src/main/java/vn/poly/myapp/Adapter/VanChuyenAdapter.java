package vn.poly.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.poly.myapp.DTO.VanChuyen;
import vn.poly.myapp.R;

public class VanChuyenAdapter extends ArrayAdapter<VanChuyen> {
    public VanChuyenAdapter(@NonNull Context context, int resource, @NonNull List<VanChuyen> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spiner_vanchuyen,parent,false);
        TextView itemvanChuyen = convertView.findViewById(R.id.tv_selected);
        VanChuyen vanChuyena = this.getItem(position);

        if (vanChuyena!=null){
            itemvanChuyen.setText(vanChuyena.getName());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vanchuyen,parent,false);
        TextView vanChuyen = convertView.findViewById(R.id.tv_vanchuyen);
        VanChuyen vanChuyena = this.getItem(position);

        if (vanChuyena!=null){
            vanChuyen.setText(vanChuyena.getName());
        }

        return convertView;
    }
}
