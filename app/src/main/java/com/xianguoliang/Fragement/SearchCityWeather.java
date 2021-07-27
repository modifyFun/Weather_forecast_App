package com.xianguoliang.Fragement;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xianguoliang.DB.UserCityTable;
import com.xianguoliang.R;
import com.xianguoliang.listener.MyCallBack;
import com.xianguoliang.model.City;
import com.xianguoliang.tools.CityUtils;

/**
 * 搜索城市结果的天气页面
 */
public class SearchCityWeather extends Fragment implements MyCallBack {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    City city;

    View thisFragment;
    Button btn_back,btn_collect;
    TextView tv_title;
    LinearLayout search_weather;
    UserCityTable userCityTable;
    LinearLayout search_weather_layout;
    Bitmap bitmap;
    String bgCode;

    Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what==12) {
                Log.v("sssssssssss","11123");
//                search_weather_layout.setBackgroundColor(Color.RED);
//                search_weather_layout.setBackground(new BitmapDrawable(bitmap));

            }
        }
    };

    public SearchCityWeather(){}
    public SearchCityWeather(City city) {

        this.city = city;
    }

    //创建实例，并传参
    public static  SearchCityWeather newInstance(City city) {

        SearchCityWeather searchCityWeather = new SearchCityWeather();
        Bundle bundle = new Bundle();
        bundle.putSerializable("city",city);
        searchCityWeather.setArguments(bundle);
        return searchCityWeather;
    }


    //获取参数
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = (City) getArguments().getSerializable("city");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisFragment = inflater.inflate(R.layout.fragment_search_city_weather, container, false);
        initView(); //初始化组件
        setViews(); //设置组件

        return thisFragment;
    }

    private void initView()
    {
        btn_back = thisFragment.findViewById(R.id.back);
        btn_collect = thisFragment.findViewById(R.id.collect);
        tv_title = thisFragment.findViewById(R.id.title);
        search_weather = thisFragment.findViewById(R.id.search_weather);
        search_weather_layout = thisFragment.findViewById(R.id.search_weather_layout);
//        search_weather_layout.setBackground(new BitmapDrawable(bitmap));
//        if (bitmap == null){
//            Log.v("nnnnnnnnnnnn","nnnnnnnn");
//        }

//        handler.sendEmptyMessage(12);
    }

    private void setViews(){
        //加载字体并应用
        Typeface font = Typeface.createFromAsset(this.getActivity().getAssets(), "iconfont.ttf");
        btn_back.setTypeface(font);
        btn_collect.setTypeface(font);
        WeatherView weatherView = WeatherView.newInstance(city.getId());  //获取天气信息fragment
        //将天气 fragment添加进 SearchCityWeather 界面
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.search_weather,weatherView);
        transaction.commit();
        //设置城市标题
        tv_title.setText(city.getName());
        userCityTable = new UserCityTable(getActivity().getApplicationContext());
        if(userCityTable.isExistCity(city.getId())){
            btn_collect.setText(R.string.collectActive);
            btn_collect.setTextColor(Color.parseColor("#ff9900"));
        }else {
            btn_collect.setText(R.string.collect);
            btn_collect.setTextColor(Color.GRAY);
        }


        //设置按钮事件
        btnBack();
        btnCollect();
    }

    private void btnBack()
    {
        btn_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void btnCollect(){
        btn_collect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

//                boolean cityExist = userCityTable.isExistCity(city.getId());
                boolean cityExist = CityUtils.isExistCity(getActivity(),city);
                //已存在取消收藏
//                if (cityExist && userCityTable.deleteCity(city.getId()))
                if (cityExist && CityUtils.delCity(getActivity(),city))
                {
                    Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                    //btn_collect.setText(Html.fromHtml("&#xe609;") );
                    btn_collect.setText(R.string.collect );
                    btn_collect.setTextColor(Color.GRAY);
                }
                //未存在添加
//                if(!cityExist && userCityTable.add(city)){
                if(!cityExist && CityUtils.AddCityToDatabase(getActivity(),city)){
//                    btn_collect.setText(Html.fromHtml("&#xe63b;") );
                    btn_collect.setText(R.string.collectActive);
                    btn_collect.setTextColor(Color.parseColor("#ff9900"));
                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void putStringValue(String content) {
       bgCode = content;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void putBitmapValue(Bitmap content) {
            if (content != null){
                search_weather_layout.setBackground(new BitmapDrawable(content));
            }
    }
}
