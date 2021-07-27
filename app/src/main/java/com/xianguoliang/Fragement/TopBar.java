package com.xianguoliang.Fragement;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.xianguoliang.R;
import com.xianguoliang.view.MainActivity;
import com.xianguoliang.view.SearchActivity;
import com.xianguoliang.view.UserCityActivity;

public class TopBar extends Fragment {

    Button btn_more,btn_search;
    TextView tv_title;
    View thisFragement;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (thisFragement == null) {
            thisFragement = inflater.inflate(R.layout.fragment_top_bar, container, false);
        }
        initView();
        ViewSeting();

        return thisFragement;
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        btn_more = (Button) thisFragement.findViewById(R.id.more);
        btn_search = (Button) thisFragement.findViewById(R.id.search);
        tv_title = (TextView) thisFragement.findViewById(R.id.title);
    }

    /**
     * 设置控件样式
     */
    private void ViewSeting()
    {

        Typeface font = Typeface.createFromAsset(this.getActivity().getAssets(), "iconfont.ttf");
        btn_more.setTypeface(font);
        btn_search.setTypeface(font);
        btn_more.setText(getResources().getString(R.string.more));
        btn_search.setText(getResources().getString(R.string.search));
        btn_more.getBackground().setAlpha(0);
        btn_search.getBackground().setAlpha(0);
        btn_more.setOnClickListener(btnList);
        btn_search.setOnClickListener(btnList);

    }

    public void setTitle(String title)
    {
        tv_title.setText(title);
    }


    //头部bar按钮监听器
    View.OnClickListener btnList = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId())
            {
                case R.id.more:
                    intent = new Intent(getActivity(), UserCityActivity.class);
                    break;
                case R.id.search:
                    intent = new Intent(getActivity(), SearchActivity.class);
                    break;
                default:
                    intent = new Intent(getActivity(), MainActivity.class);
                    break;
            }
            startActivity(intent);
        }
    };


}
