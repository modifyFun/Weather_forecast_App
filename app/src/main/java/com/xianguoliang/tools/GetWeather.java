package com.xianguoliang.tools;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xianguoliang.model.ForcastWeather;
import com.xianguoliang.model.NowWeather;
import com.xianguoliang.model.Result;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 获取天气工具类
 */
public class GetWeather {
    final static String APINow = "v7/weather/now?";  //实时天气 API
    final static String API7D = "v7/weather/7d?";  //7天预报 API
    final static String API3D = "v7/weather/3d?";  //7天预报 API
    final static String API10D = "v7/weather/10d?";  //10天预报 API
    final static String API15D = "v7/weather/15d?";  //15天预报 API

    /**
     * 获取当前天气情况
     * @param location
     * @return
     */
    public static NowWeather getNowWeather(String location) {
        NowWeather nowWeather =null;
        try {
            String json = HttpUtils.getJsonContent(MyApp.HEBASEURL + APINow + "location=" + location + "&key=" + MyApp.HEKEY);

           // Log.v("getJson",MyApp.HEBASEURL + APINow + "location=" + location + "&key=" + MyApp.HEKEY);
            //处理json
            Gson typeGson = new Gson();
            Type type = new TypeToken<Result<NowWeather>>(){}.getType();
            Result<NowWeather> result = typeGson.fromJson(json,type);
            nowWeather = result.data;

        }catch (Exception e){
           e.printStackTrace();
        }

        return  nowWeather;
    }


    /**
     *获取三天天气预报
     * @param location 城市编号
     * @return ArrayList<ForcastWeather>
     */
    public static List<ForcastWeather> getForcastWeather3D(String location)
    {
        List<ForcastWeather> forcastWeathers = null;
        try {
            //获取七天预报json
            String json = HttpUtils.getJsonContent(MyApp.HEBASEURL + API3D + "location=" + location + "&key=" + MyApp.HEKEY);
            //Log.v("myjson",json);
            //处理json
            Gson typeGson = new Gson();
            Type type = new TypeToken<Result<List<ForcastWeather>>>(){}.getType();
            Result<List<ForcastWeather>> result = typeGson.fromJson(json,type);
            forcastWeathers = result.data;
        }
        catch (Exception e){
            Log.e("fceee",e.getMessage());
        }
        return forcastWeathers;
    }


    /**
     *获取七天天气预报
     * @param location 城市编号
     * @return ArrayList<ForcastWeather>
     */
    public static List<ForcastWeather> getForcastWeather7D(String location)
    {
        List<ForcastWeather> forcastWeathers = null; //七天预报列表
        try {
            //获取七天预报json
            String json = HttpUtils.getJsonContent(MyApp.HEBASEURL + API7D + "location=" + location + "&key=" + MyApp.HEKEY);
            //Log.v("myjson",json);
            //处理json
            Gson typeGson = new Gson();
            Type type = new TypeToken<Result<List<ForcastWeather>>>(){}.getType();
            Result<List<ForcastWeather>> result = typeGson.fromJson(json,type);
            forcastWeathers = result.data;
        }
        catch (Exception e){
            Log.e("fceee",e.getMessage());
        }
        return forcastWeathers;
    }

    /**
     *获取三天天气预报
     * @param location 城市编号
     * @return ArrayList<ForcastWeather>
     */
    public static List<ForcastWeather> getForcastWeather10D(String location)
    {
        List<ForcastWeather> forcastWeathers = null;
        try {
            //获取七天预报json
            String json = HttpUtils.getJsonContent(MyApp.HEBASEURL + API10D + "location=" + location + "&key=" + MyApp.HEKEY);
            //Log.v("myjson",json);
            //处理json
            Gson typeGson = new Gson();
            Type type = new TypeToken<Result<List<ForcastWeather>>>(){}.getType();
            Result<List<ForcastWeather>> result = typeGson.fromJson(json,type);
            forcastWeathers = result.data;
        }
        catch (Exception e){
            Log.e("fceee",e.getMessage());
        }
        return forcastWeathers;
    }

    /**
     *获取三天天气预报
     * @param location 城市编号
     * @return ArrayList<ForcastWeather>
     */
    public static List<ForcastWeather> getForcastWeather15D(String location)
    {
        List<ForcastWeather> forcastWeathers = null;
        try {
            //获取七天预报json
            String json = HttpUtils.getJsonContent(MyApp.HEBASEURL + API15D + "location=" + location + "&key=" + MyApp.HEKEY);
            //Log.v("myjson",json);
            //处理json
            Gson typeGson = new Gson();
            Type type = new TypeToken<Result<List<ForcastWeather>>>(){}.getType();
            Result<List<ForcastWeather>> result = typeGson.fromJson(json,type);
            forcastWeathers = result.data;
        }
        catch (Exception e){
            Log.e("fceee",e.getMessage());
        }
        return forcastWeathers;
    }



}


