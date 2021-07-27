package com.xianguoliang.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.xianguoliang.Adapter.ViewPagerAdapter;
import com.xianguoliang.Fragement.WeatherView;
import com.xianguoliang.R;
import com.xianguoliang.listener.MyCallBack;
import com.xianguoliang.model.City;
import com.xianguoliang.model.ForcastWeather;
import com.xianguoliang.model.NowWeather;
import com.xianguoliang.tools.CityUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyCallBack{
    ViewPager2 viewPager;
    TextView tv_title;
    List<City> myCitys;
    List<ForcastWeather> forcastWeatherList;
    NowWeather nowWeather;
    City localCity;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.mainLayout);

//        initData();
//        initViwePager();
//        setViewPagerHander();
//        TopTitleSeting(localCity.getName());  //设置头部Bar Fragement

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initViwePager();
        setViewPagerHander();
        TopTitleSeting(localCity.getName());  //设置头部Bar Fragement
        Log.v("cssss",myCitys.size()+"");
    }

    /**
     * 初始数据
     */
    private void initData()
    {
        Intent intent = getIntent();
        forcastWeatherList = (List<ForcastWeather>) intent.getSerializableExtra("forcastList");
        nowWeather = (NowWeather)intent.getSerializableExtra("now");

        SharedPreferences preferences = getSharedPreferences("location",MODE_PRIVATE);

        myCitys = new ArrayList<>();
        localCity = new City();
        localCity.setName(preferences.getString("localCity","定位未知"));
        localCity.setId(preferences.getString("cityLocation","定位未知"));
        localCity.setAdm1(preferences.getString("province","定位未知"));
        myCitys.add(localCity);

        for (City city:CityUtils.getUserCitys(this)) {
            myCitys.add(city);
        }

    }
    /**
     * 设置头部标题
     * @param title
     */
    private void TopTitleSeting(String title)
    {
        FragmentManager fm= getSupportFragmentManager();
        Fragment topBar = fm.findFragmentById(R.id.topBar);
        tv_title = (TextView) topBar.getView().findViewById(R.id.title);
        tv_title.setText(title);
    }

    /**
     * 初始化 viewPaper内容
     * 页面最多显示五页
     */
    private  void  initViwePager(){
        viewPager =findViewById(R.id.viewPaper);
        ArrayList<Fragment> weatherViews = new ArrayList<Fragment>();

        //限制页面最多显示五页
       int listSize = myCitys.size()>5? 5:myCitys.size();

       for (int i=0;i <listSize; i++) {
            weatherViews.add(WeatherView.newInstance(myCitys.get(i).getId()));
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle(),weatherViews);
        viewPager.setAdapter(viewPagerAdapter);
    }

    /**
     * 设置viewpager事件监听
     */
    private void setViewPagerHander(){

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position >= myCitys.size())
                    position = myCitys.size()-1;
                else if (position < 0)
                    position = 0;

//                Log.v("mttrtt",myCitys.get(position).getAdm1());
                String provice = myCitys.get(position).getAdm1();
//                Log.v("sss",provice);
                TopTitleSeting(myCitys.get(position).getName()+" "+provice );
            }
        });
    }

    @Override
    public void putStringValue(String content) {
        Log.v("cccc",content);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void putBitmapValue(Bitmap content) {
        if (content != null){
            mainLayout.setBackground(new BitmapDrawable(content));
        }

    }
}
