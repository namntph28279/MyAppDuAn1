package vn.poly.myapp.Fragment.YeuThich;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import vn.poly.myapp.Activity.MainActivity;
import vn.poly.myapp.R;

public class ChuaCoYTFragment extends Fragment {

    CardView xsp;
    public ChuaCoYTFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chua_co_y_t, container, false);
        xsp = view.findViewById(R.id.xemSp);
        xsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILES",getActivity().MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("check","5");
                edit.commit();
                SharedPreferences pref2 = getActivity().getSharedPreferences("Google",getActivity().MODE_PRIVATE);
                SharedPreferences.Editor edit2 = pref2.edit();
                edit2.putString("checkgg","6");
                edit2.commit();


                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}