package com.innotek.demotestproject.activity;

import android.os.Bundle;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.CarLoadingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/12/5.
 */

public class CarAnimatorActivity extends BaseActivity {

    @BindView(R.id.carloading)
    public CarLoadingView carloading;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_animator);
        unbinder = ButterKnife.bind(this);

        carloading.startLoad();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
