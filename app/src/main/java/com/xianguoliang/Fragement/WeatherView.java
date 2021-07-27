package com.xianguoliang.Fragement;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.xianguoliang.Adapter.ForcastItemAdapter;
import com.xianguoliang.R;
import com.xianguoliang.listener.MyCallBack;
import com.xianguoliang.model.ForcastWeather;
import com.xianguoliang.model.NowWeather;
import com.xianguoliang.tools.DowloadImage;
import com.xianguoliang.tools.GetWeather;
import com.xianguoliang.tools.MyApp;

import java.util.Calendar;
import java.util.List;


public class WeatherView extends Fragment {
    View thisFragement;
    ListView lv_forcast;
    List<ForcastWeather> forcastWeatherList;
    NowWeather nowWeather;
    String location;
    ImageView iv_weatherIcon;
    TextView tv_temp,tv_weatherText,tv_wind,tv_uv,tv_hum;

    Bitmap myBg;
    MyCallBack myCallBack ;
    String bgCode;
    ScrollView scrollView;

    public  WeatherView(List<ForcastWeather> forcastWeatherList)
    {
        this.forcastWeatherList = forcastWeatherList;
    }

    /**
     * 获取fragement实例
     * @param forcastWeatherList
     * @param nowWeather
     * @return
     */
    public static WeatherView newInstance( List<ForcastWeather> forcastWeatherList,NowWeather nowWeather) {

        WeatherView weatherView = new WeatherView(forcastWeatherList);
        Bundle bundle = new Bundle();
        bundle.putSerializable("nowWeather",nowWeather);

        //bundle.putSerializable(" forcastWeatherList", (Serializable) forcastWeatherList);
        weatherView.setArguments(bundle);
        return weatherView;
    }

    public static WeatherView newInstance(String location){
        WeatherView weatherView = new WeatherView(null);
        Bundle bundle = new Bundle();
        bundle.putString("location",location);
        weatherView.setArguments(bundle);
        return weatherView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            myCallBack = (MyCallBack) getActivity();
        }catch (Exception e){
            e.printStackTrace();
            FragmentManager fm = getFragmentManager();
            for (Fragment f :fm.getFragments()){
                if (f instanceof SearchCityWeather){
                    myCallBack = (MyCallBack) f;
                }
            }

        }

        if (getArguments() != null){
            nowWeather = (NowWeather) getArguments().getSerializable("nowWeather");
            location = getArguments().getString("location");

            if(nowWeather == null || forcastWeatherList == null) {
                nowWeather = GetWeather.getNowWeather(location != null? location:"101281701");
                forcastWeatherList = GetWeather.getForcastWeather7D(location != null? location:"101281701");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (myCallBack == null){
            return;
        }
        //发送天气代码到Activity
        if (myBg !=null) {
            myCallBack.putStringValue(bgCode);
            myCallBack.putBitmapValue(myBg);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (thisFragement == null) {
            thisFragement = inflater.inflate(R.layout.fragment_weather_view, container, false);
        }

        initView();
        setForcast();
        setView();


        final Handler handler = new Handler(){
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 10){
                    if (myCallBack == null){
                        scrollView.setBackground(new BitmapDrawable(myBg));
                    }
                    else {
                        myCallBack.putBitmapValue(myBg);
                    }
                }
            }
        };
        new Thread(){
            @Override
            public void run() {
                super.run();

                switch (Integer.parseInt(nowWeather.getIcon())){
                    case 150:
                        bgCode = 100+"n";
                        break;
                    case 153:
                        bgCode = 103+"n";
                        break;
                    case 154:
                        bgCode = 104+"n";
                        break;
                    case 350:
                        bgCode = 300+"n";
                        break;
                    case 351:
                        bgCode = 301+"n";
                        break;
                    case 456:
                        bgCode = 406+"n";
                        break;
                    case 457:
                        bgCode = 407+"n";
                        break;
                    case 100:
                    case 103:
                    case 104:
                    case 300:
                    case 301:
                    case 406:
                    case 407:
                        bgCode = nowWeather.getIcon()+"d";
                        break;
                    default:
                        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>19)
                            bgCode = nowWeather.getIcon()+"n";
                        else
                            bgCode = nowWeather.getIcon()+"d";
                        break;
                }
                Log.v("bgurl",MyApp.IMAGEURL +bgCode+".png");
                myBg = DowloadImage.dowload(MyApp.IMAGEURL +bgCode+".png");
                handler.sendEmptyMessage(10);
            }
        }.start();
        return thisFragement;
    }

    private void initView() {
        lv_forcast = thisFragement.findViewById(R.id.forcast);
        iv_weatherIcon =thisFragement.findViewById(R.id.weatherIcon);
        tv_temp = thisFragement.findViewById(R.id.temp);
        tv_weatherText=thisFragement.findViewById(R.id.weatherText);
        tv_wind = thisFragement.findViewById(R.id.wind);
        tv_uv = thisFragement.findViewById(R.id.uv);
        tv_hum = thisFragement.findViewById(R.id.hum);
        scrollView = thisFragement.findViewById(R.id.weather_layout);
    }

    /**
     * 设置组件
     */
    private void setView(){
       String uv = "";
       switch (forcastWeatherList.get(0).getUvIndex())
       {
           case "0":
           case "1":
           case "2":
               uv = "很弱";
               break;
           case "3":
           case "4":
               uv = "弱";
               break;
           case "5":
           case "6":
               uv = "较强";
               break;
           case "7":
           case "8":
           case "9":
               uv = "强";
               break;
           default :
               uv = "很强";
       }
        iv_weatherIcon.setImageDrawable(GetImageByStringId("w"+nowWeather.getIcon()));
        tv_temp.setText(nowWeather.getTemp()+"℃");

        tv_weatherText.setText(nowWeather.getText());

        tv_wind.setText(nowWeather.getWindScale() +"级\n"+ nowWeather.getWindDir());

        tv_uv.setText(uv+"\n"+"紫外线");
        tv_hum.setText(nowWeather.getHumidity()+"%\n相对湿度");
    }

    public Drawable GetImageByStringId(String name)
    {
        int resID = getResources().getIdentifier(name, "drawable", "com.xianguoliang");
        Drawable image =  getResources().getDrawable(resID);
        return image;
    }
    /**
     * 配置 frocastlist
     */
    private void setForcast(){
        //设置预报list

        ForcastItemAdapter forcastAdapter = new ForcastItemAdapter(forcastWeatherList,getActivity());
        lv_forcast.setAdapter(forcastAdapter);
        setListViewHeightBasedOnChildren(lv_forcast);
    }

    /**
     * 解决listView 在 socrllView 无法计算高度问题
     * @param listView
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
