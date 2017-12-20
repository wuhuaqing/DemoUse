package com.innotek.demotestproject.activity_sdw;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchViewBarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_title)
    RelativeLayout rl_title;
    @BindView(R.id.ll_search)
    LinearLayout ll_search;
    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;
    boolean isExpand = false;
    public Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_bar);
        unbinder = ButterKnife.bind(this);
        //设置toolbar初始透明度为0
        toolbar.getBackground().mutate().setAlpha(0);
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
