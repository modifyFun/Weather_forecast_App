package com.xianguoliang.tools;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    public static final String HEKEY = "填入你的key"; //和风天气接口key
    public static final String HEBASEURL = "https://devapi.qweather.com/";  //和风天气接口主域名
    public static final String CITYBASEURL = "https://geoapi.qweather.com/";  //城市接口主域名
    public static final String IMAGEURL = "https://cdn.qweather.com/img/plugin/190516/bg/h5/";

    public static boolean PEROK = false;  //权限是否已经获取

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context =getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }


}
