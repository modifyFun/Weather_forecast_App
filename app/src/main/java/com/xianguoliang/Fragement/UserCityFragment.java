package com.xianguoliang.Fragement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.xianguoliang.Adapter.CityItemAdapter;
import com.xianguoliang.R;
import com.xianguoliang.model.City;
import com.xianguoliang.model.ForcastWeather;
import com.xianguoliang.model.NowWeather;
import com.xianguoliang.tools.CityUtils;
import com.xianguoliang.tools.GetWeather;
import com.xianguoliang.view.SearchActivity;

import java.util.ArrayList;
import java.util.List;


public class UserCityFragment extends Fragment {

    List<City> cities;
    private View thisFragment;
    ListView lv_city;
    Button btn_add;
    CityItemAdapter cia;
    private List<NowWeather> nowWeatherList = new ArrayList<>();
    private List<ForcastWeather> forcastWeatherList = new ArrayList<>();

    public UserCityFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initdata();
        renderView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thisFragment = inflater.inflate(R.layout.fragment_user_city, container, false);

        initView();
//        initdata();
//        renderView();
        listViewHandle();
        btnAddHandle();

        return thisFragment;
    }

    private void initView(){
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        btn_add = thisFragment.findViewById(R.id.btn_add);
        lv_city = thisFragment.findViewById(R.id.lv_city);
        btn_add.setTypeface(font);
    }

    private void initdata(){
        cities = CityUtils.getUserCitys(getActivity());
        nowWeatherList.add(new NowWeather());
        forcastWeatherList.add(new ForcastWeather());
        cia = new CityItemAdapter(getActivity(),cities,nowWeatherList,forcastWeatherList);
        lv_city.setAdapter(cia);
    }

    private void renderView(){

        //通知listview 更新数据
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 8)
                {
                    cia.notifyDataSetChanged();
                }
            }
        };
        //获取收藏城市的天气
        new Thread(){
            @Override
            public void run() {
                super.run();
                nowWeatherList.clear();
                forcastWeatherList.clear();
                for (City c : cities) {
                    NowWeather nowWeather = GetWeather.getNowWeather(c.getId());
                    ForcastWeather forcastWeather = GetWeather.getForcastWeather3D(c.getId()).get(0);
                    if (nowWeather != null && forcastWeather != null){
                        nowWeatherList.add(nowWeather);
                        forcastWeatherList.add(forcastWeather);
                        Message msg = new Message();
                        msg.what = 8;
                        handler.sendMessage(msg);
                    }
                }
            }
        }.start();
    }

    private void listViewHandle(){
        lv_city.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.view, SearchCityWeather.newInstance(cities.get(position)), null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        //长按删除
        lv_city.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage("确定删除?");
                builder.setTitle("提示");

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(CityUtils.delCity(getActivity(),cities.get(position))&&nowWeatherList.remove(position)!=null){
                            cities.remove(position);
                            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT).show();
                        }
                        cia.notifyDataSetChanged();
                    }
                });

                //添加AlertDialog.Builder对象的setNegativeButton()方法
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();


                return true;
            }
        });
    }

    private void btnAddHandle(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
//                getActivity().finish();
            }
        });
    }
}
