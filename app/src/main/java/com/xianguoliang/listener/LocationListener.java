package com.xianguoliang.listener;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.xianguoliang.model.ForcastWeather;
import com.xianguoliang.model.NowWeather;
import com.xianguoliang.tools.GetWeather;
import com.xianguoliang.tools.MyApp;
import com.xianguoliang.view.MainActivity;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;

public class LocationListener implements TencentLocationListener {
    TencentLocationManager mLocationManager;
    Context mContext ;
    private boolean isOk = false; //是否获取成功

    private final int TIMEOUT= 5000;//超时时间
    List<ForcastWeather> forcastList;
    NowWeather nowWeather;

    public LocationListener() {}
    public LocationListener(TencentLocationManager mLocationManager,Context context) {
        this.mLocationManager = mLocationManager;
        this.mContext = context;
    }



    @Override
    public void onLocationChanged(TencentLocation location, int error, String reason) {
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what ==1){
                    Toast.makeText(mContext, "获取数据超时，请检查定位权限和网络", Toast.LENGTH_SHORT).show();
                }
                else if (msg.what ==2){
                    isOk = true;
                    Toast.makeText(mContext,"获取成功",Toast.LENGTH_SHORT).show();
                    //数据加载完后跳转到主页面并传输数据
                    if (MyApp.PEROK) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("now", nowWeather);
                        Intent intent = new Intent(mContext.getApplicationContext(), MainActivity.class);
                        intent.putExtras(bundle);
                        intent.putExtra("forcastList", (Serializable) forcastList);
                        mContext.startActivity(intent);
                    }
                    else{

                        Toast.makeText(mContext.getApplicationContext(), "需要允许权限", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        Timer timer = new Timer();

        TimerTask tt = new TimerTask(){
            @Override
            public void run() {
                Message mes = new Message();
                mes.what = 1;
                if (!isOk)
                    handler.sendMessage(mes);
            }
        };
        timer.schedule(tt,TIMEOUT);

        //定位成功
        if (TencentLocation.ERROR_OK == 0) {
            if (location.getCity() != null) {
                String city =  location.getCity(); //获取城市
                final String citylocation = location.getLongitude() + "," + location.getLatitude();
                //清除定位监听，停止定位
                if (mLocationManager != null && city !=null){
                    mLocationManager.removeUpdates(this);
                }

                //将定位信息保存到SharedPreferences
                final SharedPreferences preferences = mContext.getSharedPreferences("location",MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                if (city != null) {
                    edit.putString("localCity", city.equals("undefine") ? "中山" : city);//城市
                    edit.putString("province",location.getProvince()!=null? location.getProvince():"广东");
                    edit.putString("cityLocation", citylocation);//城市经纬度
                    edit.commit();
                }
                else {
                    edit.putString("localCity",  "中山");//城市
                    edit.putString("cityLocation", "113.38，22.52");//城市经纬度
                    edit.commit();
                }

                //依据定位获取天气
                new Thread(){
                    @Override
                    public void run() {
                        nowWeather = GetWeather.getNowWeather(citylocation);
                        forcastList= GetWeather.getForcastWeather7D(citylocation);
                        Message mes = new Message();
                        mes.what = 2;
                        handler.sendMessage(mes);
                    }
                }.start();

            }
        }
    }


    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

}
