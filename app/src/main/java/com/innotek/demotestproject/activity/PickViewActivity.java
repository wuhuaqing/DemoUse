package com.innotek.demotestproject.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.SubBuyPickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PickViewActivity extends BaseActivity {
    Unbinder unbinder;
    @BindView(R.id.pt_hour)
    SubBuyPickerView ptHour;
     @BindView(R.id.pt_minu)
    SubBuyPickerView ptMinu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_view);
        unbinder =  ButterKnife.bind(this);
//        ptHour = (SubBuyPickerView) findViewById(R.id.pt_hour);
//        ptMinu = (SubBuyPickerView) findViewById(R.id.pt_minu);
        initData();
        test();
    }



    private void initData() {
        List<String> mDataList = new ArrayList<String>();
        mDataList.add("01");
        mDataList.add("02");
        mDataList.add("03");
        mDataList.add("04");
        mDataList.add("05");
        mDataList.add("06");
        // pickview_time.setData(mDataList);
        ptHour.setData(mDataList);
        ptHour.setHintString("小时");

        List<String> mList = new ArrayList<String>();
        mList.add("00");
        mList.add("30");
        ptMinu.setData(mList);
        ptMinu.setHintString("分钟");

        ptMinu.setOnSelectListener(new SubBuyPickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                Toast.makeText(PickViewActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //这儿发生泄漏
    public    void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
