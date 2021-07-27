package com.xianguoliang.tools;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xianguoliang.DB.UserCityTable;
import com.xianguoliang.model.City;
import com.xianguoliang.model.Result;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class CityUtils {
    final static String APISearchCity= "v2/city/lookup?range=cn&";  //城市查询 API


    public static  List<City> searchCity(String location)
    {
        List<City> citys = null;
        try {
            //中文转码成URLEncode 才能正确获取内容
            location= URLEncoder.encode(location);
            String json = HttpUtils.getJsonContent(MyApp.CITYBASEURL + APISearchCity + "location=" + location + "&key=" + MyApp.HEKEY);

            Gson typeGson = new Gson();
            Type type = new TypeToken<Result<List<City>>>(){}.getType();
            Result<List<City>> result = typeGson.fromJson(json,type);
            if (result.code != 200){
                City c = new City();
                c.setName("未查询到位置");
                citys = new ArrayList<>();
                citys.add(c);
            }
            else {
                citys = result.data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citys;
    }

    public static boolean isExistCity(Context context,City city)
    {
        UserCityTable userCityTable = new UserCityTable(context);
        boolean isExist = userCityTable.isExistCity(city.getId());

        return isExist;
    }

    public static boolean AddCityToDatabase(Context context,City city)
    {
        UserCityTable userCityTable = new UserCityTable(context);

        return userCityTable.add(city);
    }

    public static List<City> getUserCitys(Context context)
    {
        UserCityTable userCityTable = new UserCityTable(context);
        List<City> citys = userCityTable.getAllUserCity();
        return citys ;
    }


    //删除城市
    public  static boolean delCity(Context context,City city){
        UserCityTable userCityTable = new UserCityTable(context);

        return userCityTable.deleteCity(city.getId());
    }



}
