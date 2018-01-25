package com.innotek.demotestproject.activity_view.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;
import com.innotek.demotestproject.view.indicatorview.IndicatorBean;
import com.innotek.demotestproject.view.indicatorview.TimeStepView;

import java.util.ArrayList;

public class TimeStepViewActivity extends BaseActivity {
    private TimeStepView tiemstepview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_step_view);
        tiemstepview = (TimeStepView) findViewById(R.id.tiemstepview);
        initTimeStepView();
    }

    public void initTimeStepView(){
        ArrayList<IndicatorBean> indicatorBeanList = new ArrayList<>();
        IndicatorBean indicatorBean = new IndicatorBean();
        indicatorBean.lineColor = getResources().getColor(R.color.color_4292AF);

        indicatorBean.indicatorTexttColor = Color.WHITE;
        indicatorBean.circleIndicatorColor = getResources().getColor(R.color.color_ffaf03);
        indicatorBean.indicatorAboveText = "￥142";
        indicatorBean.indicatroAboveTextColor = getResources().getColor(R.color.color_ff5339);
        indicatorBean.isShowAboveText = true;
        indicatorBean.isShowButtomText = true;
        indicatorBean.indicatorButtomText = "11-18 11:20";
        indicatorBean.indicatroButtomTextColor = getResources().getColor(R.color.color_3A819B);

        IndicatorBean indicatorBean1 = new IndicatorBean();

        indicatorBean1.lineColor = Color.BLACK;
        indicatorBean1.indicatorTexttColor = Color.WHITE;
        indicatorBean1.circleIndicatorColor = getResources().getColor(R.color.color_63b520);
        indicatorBean1.indicatorAboveText = "￥9";
        indicatorBean1.indicatroAboveTextColor = getResources().getColor(R.color.color_ff5339);
        indicatorBean1.isShowAboveText = true;
        indicatorBean1.isShowButtomText = true;
        indicatorBean1.indicatorButtomText = "11-18 12:20";
        indicatorBean1.indicatroButtomTextColor = getResources().getColor(R.color.color_3A819B);

        IndicatorBean indicatorBean2 = new IndicatorBean();

        indicatorBean2.lineColor = Color.BLACK;
        indicatorBean2.indicatorTexttColor = Color.WHITE;
        indicatorBean2.circleIndicatorColor = getResources().getColor(R.color.color_63b520);
        indicatorBean2.indicatorAboveText = "-￥44";
        indicatorBean2.indicatroAboveTextColor = getResources().getColor(R.color.color_ff5339);
        indicatorBean2.isShowAboveText = true;
        indicatorBean2.isShowButtomText = true;
        indicatorBean2.indicatorButtomText = "11-18 12:20";
        indicatorBean2.indicatroButtomTextColor = getResources().getColor(R.color.color_3A819B);

        indicatorBeanList.add(indicatorBean);
        indicatorBeanList.add(indicatorBean1);
        indicatorBeanList.add(indicatorBean2);
        tiemstepview.setIndicatorData(indicatorBeanList);
    }
}
