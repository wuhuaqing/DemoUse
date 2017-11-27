package com.innotek.demotestproject.activity_sdw;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CoordinatorLayoutDemo2Actvity extends BaseActivity {
    private Unbinder bind;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.collaptoolbarlayout)
//    CollapsingToolbarLayout collaptoolbarlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_demo2_actvity);
        bind = ButterKnife.bind(this);
        initToolBar();
    }

    public void initToolBar(){
          toolbar.setTitle("标题");
       //  collaptoolbarlayout.setTitle("标题");
//        toolbar.setCollapsible(true);
        setSupportActionBar(toolbar);
        //图标点击事件在support方法后
        toolbar.setNavigationIcon(R.mipmap.ic_sdw_return);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
