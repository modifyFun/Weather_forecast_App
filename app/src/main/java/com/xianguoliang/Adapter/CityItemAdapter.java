package com.xianguoliang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.xianguoliang.R;
import com.xianguoliang.model.City;
import com.xianguoliang.model.ForcastWeather;
import com.xianguoliang.model.NowWeather;

import java.util.List;

public class CityItemAdapter extends BaseAdapter {
    List<City> cities;
    Context context;
    ConstraintLayout container;
    TextView cityName,weatherText,temp;
    private List<NowWeather> nowWeatherList;
    private List<ForcastWeather> forcastWeatherList;

    public CityItemAdapter( Context context, List<City> cities,List<NowWeather> nowWeatherList, List<ForcastWeather> forcastWeatherList) {
        this.cities = cities;
        this.context = context;
        this.nowWeatherList = nowWeatherList;
        this.forcastWeatherList = forcastWeatherList;
    }

    public CityItemAdapter(Context context, List<City> cities) {

        this.context = context;
        this.cities = cities;
    }

    @Override
    public int getCount() {
        return nowWeatherList.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView ==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.city_item,parent,false);
        }
        initView(convertView);
        setView(position);

        return convertView;
    }

    private void initView(View converView){
        cityName = converView.findViewById(R.id.cityName);
        weatherText = converView.findViewById(R.id.weatherText);
        temp = converView.findViewById(R.id.temp);
        container = converView.findViewById(R.id.container);
    }

    private void setView(final int position){

        try {
            if (cities.get(position) != null){
                cityName.setText(cities.get(position).getName());
            }

            if (nowWeatherList.get(position) == null || forcastWeatherList.get(position) == null)
                return;
            switch (Integer.parseInt( nowWeatherList.get(position).getIcon()))
            {
                case 100:
                case 150:
                case 104:
                    container.setBackgroundResource((R.drawable.sunny));
                    break;
                case 101:
                case 102:
                case 103:
                case 153:
                case 154:
                case 900:
                    container.setBackgroundResource(R.drawable.defult_bg_shape);
                    break;
                default:
                    container.setBackgroundResource(R.drawable.bad_bg_shape);
                    break;
            }
            temp.setText(nowWeatherList.get(position).getTemp()+"℃");
            weatherText.setText(nowWeatherList.get(position).getText()+
                    " "+forcastWeatherList.get(position).getTempMin() +
                    "℃/" + forcastWeatherList.get(position).getTempMax()+"℃");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
