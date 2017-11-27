package com.innotek.demotestproject.activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.Gradient;
import java.util.ArrayList;
import java.util.List;

public class GaintViewActivity extends BaseActivity {

    private Gradient gradient;
    private List<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaint_view);
        initView();

    }

    private void initView() {
        imageViews = new ArrayList<ImageView>();
        getImageViewList();
        gradient = (Gradient) findViewById(R.id.gradient);
        //渐变时间
        gradient.setTime(3000);
        //渐变view
        gradient.setImageViews(imageViews);
        //点击view的事件响应
        gradient.setListner(new Gradient.onClickListner() {
            @Override
            public void setonClick(int position) {
                  Toast.makeText(GaintViewActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 初始化展示控件列表
     */
    private void getImageViewList() {
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.iv_change1);
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.iv_change2);
        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.drawable.iv_change3);
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         if (gradient!=null)
             gradient.stop();
    }
}
