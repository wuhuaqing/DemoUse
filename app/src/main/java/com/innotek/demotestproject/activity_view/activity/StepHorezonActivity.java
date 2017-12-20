package com.innotek.demotestproject.activity_view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;

public class StepHorezonActivity extends BaseActivity {

    private LinearLayout ll_stepviewhorizon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_horezon);
        ll_stepviewhorizon = (LinearLayout) findViewById(R.id.ll_stepviewhorizon);
        setStepView( );
    }


    /**
     * 设置 审核流程的布局 stepview
     *
     */
    public void setStepView( ) {

            ll_stepviewhorizon.setVisibility(View.VISIBLE);
            addView(R.mipmap.ic_stephor,"1");
            addView(R.mipmap.ic_stephor,"2");
            addView(R.mipmap.ic_stephor,"3");


    }

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    private void addView(int imagId, String  strId ) {
        View v = createItem(imagId, strId);
      //  v.setId(strId);
        ll_stepviewhorizon.addView(v, params);
    }

    private View createItem(int resid, String tag ) {
        View childView = View.inflate(this, R.layout.layout_indicatorview, null);
        TextView tagView = (TextView) childView.findViewById(R.id.tv_number);
        tagView.setText(tag);
        return childView;
    }

}
