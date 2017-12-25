package com.innotek.demotestproject.activity_view.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;
import com.innotek.demotestproject.view.indicatorview.IndicatorViewLayout;
import com.innotek.demotestproject.view.piechart.DensityUtil;

public class StepHorezonActivity extends BaseActivity {

    private LinearLayout ll_stepviewhorizon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_horezon);
        ll_stepviewhorizon = (LinearLayout) findViewById(R.id.ll_stepviewhorizon);
        String[] tags = new String[]{"1", "2", "3"};//"4"
        String[] statuss = new String[]{"添加", "删除", "修改"};//,"查询一下下咯"
        setStepView();
        IndicatorViewLayout ivlayout = (IndicatorViewLayout) findViewById(R.id.ivlayout);
        IndicatorViewLayout ivlayout2 = (IndicatorViewLayout) findViewById(R.id.ivlayout2);
        ivlayout.initStepViewData(tags, statuss);
        ivlayout2.initStepViewData(tags, statuss);
        IndicatorViewLayout ivlayout1 = (IndicatorViewLayout) findViewById(R.id.ivlayout1);

        ivlayout1.initStepViewData(tags, statuss);


    }


    /**
     * 设置 审核流程的布局 stepview
     */
    public void setStepView() {

        ll_stepviewhorizon.setVisibility(View.VISIBLE);
        //父布局左间距50dp
        LinearLayout.LayoutParams ll_stepviewhorizonLayoutParams = (LinearLayout.LayoutParams) ll_stepviewhorizon.getLayoutParams();
        ll_stepviewhorizonLayoutParams.setMargins(DensityUtil.dip2px(this, 50), 0, 0, 0);
        ll_stepviewhorizon.setLayoutParams(ll_stepviewhorizonLayoutParams);

        //子布局均分
        DisplayMetrics dm = DensityUtil.getDisplayMetrics(this);
        int widthPixels = dm.widthPixels;
        int widthDIP = DensityUtil.px2dip(this, widthPixels);
        int itemWidth = (widthDIP - 100) / 3;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(StepHorezonActivity.this, itemWidth),
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // params.setMargins(DensityUtil.dip2px(this,50),0,0,0);
        addView(R.mipmap.ic_stephor, "1", "添加", params);
        addView(R.mipmap.ic_stephor, "2", "删除", params);
        addView(R.mipmap.ic_stephor, "3", "修改", params);

    }


    private void addView(int imagId, String strId, String status, LinearLayout.LayoutParams params) {
        View v = createItem(imagId, strId, status);
        //  v.setId(strId);
        ll_stepviewhorizon.addView(v, params);
    }

    private View createItem(int resid, String tag, String status) {
        View childView = View.inflate(this, R.layout.layout_indicatorview, null);
        TextView tagView = (TextView) childView.findViewById(R.id.tv_number);
        TextView tv_status = (TextView) childView.findViewById(R.id.tv_status);
        tagView.setText(tag);
        tv_status.setText(status);
        return childView;
    }

}
