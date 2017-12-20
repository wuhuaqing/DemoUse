package com.innotek.demotestproject.activity_sdw;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SPWDemoActivity extends BaseActivity  {
    private Unbinder bind;
    @BindView(R.id.btn_coordidemo1)
    public Button btn_coordidemo1;
    @BindView(R.id.btn_coordidemo2)
    public Button btn_coordidemo2;
    @BindView(R.id.btn_drawerlayout)
    public Button btn_drawerlayout;
    @BindView(R.id.btn_serachview)
    public Button btn_serachview;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spwdemo);
        bind = ButterKnife.bind(this);
        toolbar.setTitle(getString(R.string.title_spwdemo));
        btn_coordidemo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SPWDemoActivity.this,CoordinatorLayoutDemo1Actvity.class));
            }
        });
        btn_coordidemo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SPWDemoActivity.this,CoordinatorLayoutDemo2Actvity.class));
            }
        });
        btn_drawerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SPWDemoActivity.this,DrawerLayoutActivity.class));
            }
        });
        btn_serachview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SPWDemoActivity.this,SearchViewActivity.class));
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
