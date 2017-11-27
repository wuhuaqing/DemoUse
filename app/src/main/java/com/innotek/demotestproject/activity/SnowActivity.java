package com.innotek.demotestproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.SnowView;

import java.util.Timer;
import java.util.TimerTask;

public class SnowActivity extends BaseActivity {

    private SnowView flowerview;
    private RelativeLayout activity_snow;

    // 屏幕宽度
    public static int screenWidth;
    // 屏幕高度
    public static int screenHeight;
    Timer myTimer = null;
    TimerTask mTask = null;
    private static final int SNOW_BLOCK = 1;
    private Handler mHandler = new Handler() {
        public void dispatchMessage(Message msg) {
            flowerview.inva();
            flowerview.setSnowViewClickListener(new SnowView.onSnowViewClickListener() {
                @Override
                public void onSnowViewClickLis() {
                    Toast.makeText(SnowActivity.this,"haha",Toast.LENGTH_SHORT).show();
                }
            });
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow);
        initView();
    }


    private void initView() {
        flowerview = (SnowView) findViewById(R.id.flowerview);
        activity_snow = (RelativeLayout) findViewById(R.id.activity_snow);
        screenWidth = getWindow().getWindowManager().getDefaultDisplay()
                .getWidth();//获取屏幕宽度
        screenHeight = getWindow().getWindowManager().getDefaultDisplay()
                .getHeight();//获取屏幕高度

        DisplayMetrics dis = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dis);
        float de = dis.density;
        flowerview.setWH(screenWidth, screenHeight, de);
        flowerview.loadFlower();
        flowerview.addRect();

        myTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = SNOW_BLOCK;
                mHandler.sendMessage(msg);
            }
        };
        myTimer.schedule(mTask, 3000, 10);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        flowerview.recly();
    }
}
