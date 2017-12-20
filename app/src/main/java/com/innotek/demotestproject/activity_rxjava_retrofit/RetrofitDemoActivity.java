package com.innotek.demotestproject.activity_rxjava_retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RetrofitDemoActivity extends BaseActivity {

    private Unbinder butterKnife;

    @BindView(R.id.btn_getinfo)
    Button btn_getinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);
        butterKnife = ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_getinfo)
    public void clickMethod(){
     //   RetrofitUtil.getInstance().getUser("Guolei1130");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnife.unbind();
    }
}
