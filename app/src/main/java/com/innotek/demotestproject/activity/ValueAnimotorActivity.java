package com.innotek.demotestproject.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.piechart.DensityUtil;
import com.innotek.demotestproject.view.piechart.PieChartView2;
import com.innotek.demotestproject.view.piechart.PieChartView3;

import java.util.ArrayList;
import java.util.List;

public class ValueAnimotorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animotor);
        initView();
    }

    public void initView() {
        PieChartView2 pieChartView = (PieChartView2) findViewById(R.id.pie_chart);
        List<PieChartView2.PieceDataHolder> pieceDataHolders = new ArrayList<>();
        //数据集合                                 //视图数据    比重值    扇形颜色                 扇形名字  扇形数据
        pieceDataHolders.add(new PieChartView2.PieceDataHolder(50, Color.parseColor("#F05A4C"), "网购", "50"));
        pieceDataHolders.add(new PieChartView2.PieceDataHolder(8, Color.parseColor("#3875C2"), "手机充值", "42.56"));
        pieceDataHolders.add(new PieChartView2.PieceDataHolder(1, Color.parseColor("#28AB93"), "健康", "30"));
        pieceDataHolders.add(new PieChartView2.PieceDataHolder(10, Color.parseColor("#28A000"), "住宿", "30"));

        //设置指向线长度
        pieChartView.setMarkerLineLength(DensityUtil.dip2px(this, 20));
        //设置圆半径
        pieChartView.setPieChartCircleRadius(DensityUtil.dip2px(this, 60));
        //文字大小
        pieChartView.setTextSize(DensityUtil.dip2px(this, 4));
        pieChartView.setData(pieceDataHolders);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView2();
    }

    public void initView2() {

        PieChartView3 pieChartView = (PieChartView3) findViewById(R.id.pie_chart3);
        List<PieChartView3.PieceDataHolder> pieceDataHolders = new ArrayList<>();
        //数据集合                                 //视图数据    比重值    扇形颜色                 扇形名字  扇形数据
        pieceDataHolders.add(new PieChartView3.PieceDataHolder(50, Color.parseColor("#F05A4C"), "网购", "50"));
        pieceDataHolders.add(new PieChartView3.PieceDataHolder(8, Color.parseColor("#3875C2"), "手机充值", "42.56"));
        pieceDataHolders.add(new PieChartView3.PieceDataHolder(1, Color.parseColor("#28AB93"), "健康", "30"));
        pieceDataHolders.add(new PieChartView3.PieceDataHolder(10, Color.parseColor("#28A000"), "住宿", "30"));

        //设置指向线长度
        pieChartView.setMarkerLineLength(DensityUtil.dip2px(this, 20));
        //设置圆半径
        pieChartView.setPieChartCircleRadius(DensityUtil.dip2px(this, 60));
        //文字大小
        pieChartView.setTextSize(DensityUtil.dip2px(this, 4));
        pieChartView.setData(pieceDataHolders);
    }
}
