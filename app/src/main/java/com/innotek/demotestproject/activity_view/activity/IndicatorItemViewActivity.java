package com.innotek.demotestproject.activity_view.activity;

import android.os.Bundle;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;
import com.innotek.demotestproject.view.indicatorview.IndicatorItemView;

import java.util.ArrayList;
import java.util.List;

public class IndicatorItemViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_indicatoritem);
       IndicatorItemView indicatorItemView = (IndicatorItemView) findViewById(R.id.iiview);
        ArrayList<Integer> lisd = new ArrayList<>();
        ArrayList<Boolean> booleens = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        lisd.add(1);
        lisd.add(2);
        lisd.add(3);
        booleens.add(false);
        booleens.add(true);
        booleens.add(true);
        stringArrayList.add("吴华清");
        stringArrayList.add("蛮帅的");
        stringArrayList.add("看好你");
        indicatorItemView.setIndicatorData(lisd,booleens,stringArrayList);
    }
}
