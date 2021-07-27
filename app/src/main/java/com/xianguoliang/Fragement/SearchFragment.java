package com.xianguoliang.Fragement;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.xianguoliang.Adapter.SearchItemAdapter;
import com.xianguoliang.R;
import com.xianguoliang.model.City;
import com.xianguoliang.tools.CityUtils;

import java.util.List;

public class SearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText ed_search;
    Button btn_search;
    ListView lv_city;
    Typeface font;

    View thisFragment;
    List<City> citys;
    SearchItemAdapter searchItemAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thisFragment = inflater.inflate(R.layout.fragment_search, container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");

        intView();
        setView();
        return thisFragment;
    }
    //初始化组件
    private void intView() {
        ed_search = thisFragment.findViewById(R.id.ed_search);
        btn_search = thisFragment.findViewById(R.id.btn_search);
        lv_city = thisFragment.findViewById(R.id.lv_city);
    }

    //设置组件
    private void setView() {
        btn_search.setTypeface(font);
        //监听查找按钮事件
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setListView();
            }

        });
        //监听城市listView 点击事件
        lv_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoWeather(position);
            }
        });

        //监听enter 事件
        ed_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    setListView();
                }
                return false;
            }
        });
    }

    private void gotoWeather(int position){
        City city =citys.get(position);
        if(city.getId()!=null)
        {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.search_fragment, SearchCityWeather.newInstance(city), null)
                    .addToBackStack(null)
                    .commit();
            lv_city.setAdapter(null);
            ed_search.setText("");
        }
    }

    private void setListView()
    {
        String location = ed_search.getText().toString();
        if (!location.equals("")){

            citys = CityUtils.searchCity(location);
            if (citys.get(0).getName() == null)
            {
                Toast.makeText(getActivity(), "搜索失败了", Toast.LENGTH_SHORT).show();
            }else
            {
                searchItemAdapter = new SearchItemAdapter(citys,getActivity());
                lv_city.setAdapter(searchItemAdapter);
            }
        }
    }

}
