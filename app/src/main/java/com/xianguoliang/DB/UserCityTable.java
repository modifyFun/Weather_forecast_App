package com.xianguoliang.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xianguoliang.model.City;

import java.util.ArrayList;
import java.util.List;

public class UserCityTable {
    DatabaseHelper helper;
    SQLiteDatabase db;
    public static final String TB_NAME = "user_citys";  //表名
    public static final String COL_CITYNAME = "cityname"; //城市名称
    public static final String COL_CITYCODE = "citycode";   //城市代码
    public static final String COL_PROCINCE= "province"; //省份
    public static final String COL_ADDTIME = "addtime"; //添加时间



    public static final String SQL = "CREATE TABLE IF NOT EXISTS "+ TB_NAME +"("+
            COL_CITYCODE + " integer primary key,"+
            COL_CITYNAME + " text,"+
            COL_PROCINCE + " text,"+
            COL_ADDTIME +" integer"+
            ")";

    public UserCityTable(Context context) {
        helper = DatabaseHelper.getInstance(context,SQL);
        db = helper.getReadableDatabase();
    }

    /**
     * 添加城市
     * @param city
     * @return
     */
    public boolean add (City city){

        long flag = 0;
        ContentValues values = new ContentValues();

        values.put(COL_CITYCODE,Integer.parseInt(city.getId()));
        values.put(COL_CITYNAME,city.getName());
        values.put(COL_PROCINCE,city.getAdm1());
        values.put(COL_ADDTIME,System.currentTimeMillis()/1000);

        try{
            flag = db.insert(TB_NAME,null,values);
        }catch (Exception e){
            Log.v("error",e.getMessage());
            return false;
        }
        if (flag > 0){
            return true;
        }
        return false;
    }

    //查询用户已选择所有城市的数据
    public List<City> getAllUserCity(){

        List<City> list = new ArrayList<>();
        try {
            Cursor cursor;
            cursor = db.rawQuery("select * from "+ TB_NAME +" order by "+COL_ADDTIME+" desc",null);

            if (cursor !=null && cursor.moveToFirst()){
                do {
                    City user_city = new City();
                    user_city.setName(cursor.getString(cursor.getColumnIndex(COL_CITYNAME)));
                    user_city.setId(cursor.getString(cursor.getColumnIndex(COL_CITYCODE)));
                    user_city.setAdm1(cursor.getString(cursor.getColumnIndex(COL_PROCINCE)));
                    user_city.setExist(true);
                    list.add(user_city);
                }while (cursor.moveToNext());
            }
            if (cursor!=null){
                cursor.close();
            }

        }catch (Exception e){
            Log.v("mydb:",e.getMessage());
        }
        return list;
    }

    //查询是否存在城市
    public boolean isExistCity(String cityCode){

        Cursor cursor = null;
        boolean flage = false;
        String sql = "select "+COL_CITYCODE+" from "+ TB_NAME + " where "+ COL_CITYCODE  + "=" +cityCode;
        try{

            cursor = db.rawQuery(sql,null);
            if (cursor !=null && cursor.getCount() > 0){
                flage = true;
            }
        }catch (Exception e){
            Log.v("mydb:",e.getMessage());
        }
        if (cursor!=null){
            cursor.close();
        }

        return flage;
    }

    //删除城市
    public boolean deleteCity(String cityCode){

        int flag = 0;
        try {
            flag = db.delete(TB_NAME,COL_CITYCODE +"=?",new String[]{cityCode});
        }catch (Exception e){
            Log.v("mydb:",e.getMessage());
        }
        if (flag>0) return true;

        return false;
    }
}
