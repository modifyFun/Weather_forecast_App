package com.xianguoliang.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xianguoliang.R;
import com.xianguoliang.model.City;
import com.xianguoliang.tools.CityUtils;

import java.util.List;

public class SearchItemAdapter extends BaseAdapter {
    Context context;
    List<City> citys;
    TextView existFlag,cityName;
    Typeface font;

    public SearchItemAdapter(List<City> citys, Context context) {

        this.citys = citys;
        this.context = context;
    }

    @Override
    public int getCount() {
        return citys.size();
    }

    @Override
    public Object getItem(int position) {
        return citys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView ==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        }

        font = Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");
        initView(convertView);

        setView(citys.get(position));
        return convertView;
    }

    private void initView(View convertView) {
        existFlag = convertView.findViewById(R.id.existflag);
        cityName = convertView.findViewById(R.id.cityName);
    }

    private void setView(City city)
    {
        existFlag.setTypeface(font);
//        UserCityTable userCityTable = new UserCityTable(context.getApplicationContext());
        if (CityUtils.isExistCity(context,city))
        {
            existFlag.setText(R.string.collectActive);
            existFlag.setTextColor(Color.parseColor("#ff9900"));
        }
        cityName.setText(city.getName()+"-"+city.getAdm2()+","+city.getAdm1()+","+city.getCountry());
    }

}
