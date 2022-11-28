package vn.poly.myapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    ProgressDialog dialog;

    public void showLoading(){
        if(dialog != null){
            dialog.show();
        }
    }

    public void hideLoading(){
        if(dialog.isShowing()) dialog.hide();
    }

    public void navigatescreen(Class target){
        startActivity(new Intent(this, target));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        initLayout();
        initAction();
    }

     abstract void initLayout();

    abstract void initAction();

    abstract int setLayout();



}
