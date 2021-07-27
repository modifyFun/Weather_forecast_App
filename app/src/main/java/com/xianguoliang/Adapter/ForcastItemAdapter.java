package com.xianguoliang.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xianguoliang.R;
import com.xianguoliang.model.ForcastWeather;
import com.xianguoliang.tools.DateUtils;

import java.util.List;

public class ForcastItemAdapter extends BaseAdapter {

    List<ForcastWeather> forcastList ;
    Context context;
    TextView tv_weatherText,tv_temp;
    ImageView iv_weatherIcon;

    public ForcastItemAdapter(List<ForcastWeather> focastList, Context context) {
        this.forcastList = focastList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return forcastList.size();
    }

    @Override
    public Object getItem(int position) {
        return forcastList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView ==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.forcast_item,parent,false);
        }
        //初始化组件
        initView(convertView);
        ForcastWeather forcastWeather = forcastList.get(position); //获取预报
        //设置预报list
        setForcastList(convertView,forcastWeather);


        return convertView;
    }

    private void initView( View convertView) {
        iv_weatherIcon = convertView.findViewById(R.id.weatherIcon);
        tv_weatherText = convertView.findViewById(R.id.weatherText);
        tv_temp = convertView.findViewById(R.id.temp);

    }

    private void setForcastList(View convertView,ForcastWeather forcastWeather) {
        //获取天气图片
        String imageName = "w"+forcastWeather.getIconDay();
        int resID = convertView.getResources().getIdentifier(imageName, "drawable", "com.xianguoliang");
        Drawable image = convertView.getResources().getDrawable(resID);

        String nightText= forcastWeather.getTextDay().equals(forcastWeather.getTextNight())? "":"-"+forcastWeather.getTextNight();

        //设置UI
        iv_weatherIcon.setImageDrawable(image);
        tv_weatherText.setText(DateUtils.dateToWeek(forcastWeather.getFxDate())+"·"+forcastWeather.getTextDay()+nightText);
        tv_temp.setText(forcastWeather.getTempMin()+" ℃/"+forcastWeather.getTempMax()+" ℃");
    }


}
