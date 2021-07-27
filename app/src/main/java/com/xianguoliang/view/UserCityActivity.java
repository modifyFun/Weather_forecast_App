package com.xianguoliang.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xianguoliang.Fragement.UserCityFragment;
import com.xianguoliang.R;

public class UserCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_city);
        //设置显示的fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        UserCityFragment ucf = new UserCityFragment();
        transaction.add(R.id.view, ucf);
        transaction.commit();
    }

}
