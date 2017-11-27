package com.innotek.demotestproject.activity_sdw;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;
import com.innotek.demotestproject.activity.MainActivity;
import com.innotek.demotestproject.activity.PickViewActivity;
import com.innotek.demotestproject.view.PointMoveView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SPWDemoActivity extends BaseActivity  {

    private Unbinder bind;

    @BindView(R.id.btn_coordidemo1)
    public Button btn_coordidemo1;
    @BindView(R.id.btn_coordidemo2)
    public Button btn_coordidemo2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spwdemo);
        bind = ButterKnife.bind(this);
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
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
