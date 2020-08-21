package com.innotek.demotestproject.activity_view.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;
import com.innotek.demotestproject.view.indicatorview.IndicatorBean;
import com.innotek.demotestproject.view.indicatorview.IndicatorItemView;
import com.innotek.demotestproject.view.indicatorview.TimeStepView;

import java.util.ArrayList;

public class IndicatorItemViewActivity extends BaseActivity {

    private IndicatorItemView indicatorItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_indicatoritem);
        indicatorItemView = (IndicatorItemView) findViewById(R.id.iiview);
        initIndicatorItemView();
    }

    public void initIndicatorItemView(){
        ArrayList<IndicatorBean> indicatorBeanList = new ArrayList<>();

        /**
         *   <color name="color_ffaf03">#ffaf03</color>
         <color name="color_63b520">#63b520</color>
         <color name="color_ff5339">#ff5339</color>
         */
        IndicatorBean indicatorBean = new IndicatorBean();
        indicatorBean.isDashLine = false;
        indicatorBean.lineColor = getResources().getColor(R.color.color_4292AF);
        indicatorBean.indicatorText = "吴华清";
        indicatorBean.indicatorTexttColor = Color.WHITE;
        indicatorBean.circleIndicatorColor = getResources().getColor(R.color.color_ffaf03);
        indicatorBean.indicatorAboveText = "12";
        indicatorBean.indicatroAboveTextColor = getResources().getColor(R.color.color_ff5339);
        indicatorBean.isShowAboveText = true;
        indicatorBean.isShowButtomText = true;
        indicatorBean.indicatorButtomText = "11:20";
        indicatorBean.indicatroButtomTextColor = getResources().getColor(R.color.color_3A819B);

        IndicatorBean indicatorBean1 = new IndicatorBean();
        indicatorBean1.isDashLine = false;
        indicatorBean1.lineColor = Color.BLACK;
        indicatorBean1.indicatorText = "嗯嗯";
        indicatorBean1.indicatorTexttColor = Color.WHITE;
        indicatorBean1.circleIndicatorColor = getResources().getColor(R.color.color_63b520);
        indicatorBean1.indicatorAboveText = "13";
        indicatorBean1.indicatroAboveTextColor = getResources().getColor(R.color.color_ff5339);
        indicatorBean1.isShowAboveText = true;
        indicatorBean1.isShowButtomText = true;
        indicatorBean1.indicatorButtomText = "12:20";
        indicatorBean1.indicatroButtomTextColor = getResources().getColor(R.color.color_3A819B);

        IndicatorBean indicatorBean3 = new IndicatorBean();
        indicatorBean3.isDashLine = false;
        indicatorBean3.lineColor = Color.BLACK;
        indicatorBean3.indicatorText = "嗯嗯";
        indicatorBean3.indicatorTexttColor = Color.WHITE;
        indicatorBean3.circleIndicatorColor = getResources().getColor(R.color.color_63b520);
        indicatorBean3.indicatorAboveText = "13";
        indicatorBean3.indicatroAboveTextColor = getResources().getColor(R.color.color_ff5339);
        indicatorBean3.isShowAboveText = true;
        indicatorBean3.isShowButtomText = true;
        indicatorBean3.indicatorButtomText = "12:20";
        indicatorBean3.indicatroButtomTextColor = getResources().getColor(R.color.color_3A819B);

        IndicatorBean indicatorBean2 = new IndicatorBean();
        indicatorBean2.isDashLine = true;
        indicatorBean2.lineColor = getResources().getColor(R.color.color_4292AF);
        indicatorBean2.indicatorText = "ok";
        indicatorBean2.indicatorTexttColor = Color.WHITE;
        indicatorBean2.circleIndicatorColor = getResources().getColor(R.color.color_ff5339);
        //  indicatorBean2.indicatorAboveText = "14";
        indicatorBean2.indicatroAboveTextColor =getResources().getColor(R.color.color_ff5339);
        indicatorBean2.isShowAboveText = true;
        indicatorBean2.isShowButtomText = true;
        //   indicatorBean2.indicatorButtomText = "13:20";
        indicatorBean2.indicatroButtomTextColor = getResources().getColor(R.color.color_3A819B);
        indicatorBean2.carIndex = 3;

        indicatorBeanList.add(indicatorBean);
        indicatorBeanList.add(indicatorBean1);
        indicatorBeanList.add(indicatorBean3);
        indicatorBeanList.add(indicatorBean2);
        indicatorItemView.setIndicatorData(indicatorBeanList);
    }

}
