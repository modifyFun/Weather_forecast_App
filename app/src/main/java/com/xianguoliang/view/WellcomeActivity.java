package com.xianguoliang.view;

import android.Manifest;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.xianguoliang.R;
import com.xianguoliang.listener.LocationListener;
import com.xianguoliang.tools.MyApp;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class WellcomeActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {


    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wellcome);
        initView();
        //检测用户权限及请求权限
        requirePermission();

        AnimationDrawable animationDrawable1 = (AnimationDrawable) im.getDrawable();
        animationDrawable1.start();


    }

    private void initView()
    {
        im = (ImageView) findViewById(R.id.loading);
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private  void  initLocation()
    {
        //腾讯定位SDK
        TencentLocationManager mLocationManager =TencentLocationManager.getInstance(this);
        TencentLocationRequest locationRequest =  TencentLocationRequest.create();
        locationRequest.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_POI);
        //请求获取位置
        mLocationManager.requestLocationUpdates(locationRequest, new LocationListener(mLocationManager,this) , Looper.getMainLooper());
        //mLocationManager.requestSingleFreshLocation(locationRequest, new LocationListener(), Looper.getMainLooper());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //easypermissions 获取用户权限
    @AfterPermissionGranted(1)
    private void requirePermission() {
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        String[] permissionsForQ = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION, //target为Q时，动态请求后台定位权限
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        if (Build.VERSION.SDK_INT >= 29 ? EasyPermissions.hasPermissions(this, permissionsForQ) :
                EasyPermissions.hasPermissions(this, permissions)) {
                //Toast.makeText(this, "权限OK", Toast.LENGTH_LONG).show();
            MyApp.PEROK = true;
            initLocation();
        } else {
            EasyPermissions.requestPermissions(this, "需要权限",1, Build.VERSION.SDK_INT >= 29 ? permissionsForQ : permissions);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        MyApp.PEROK = true;
        initLocation();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {


    }
}
