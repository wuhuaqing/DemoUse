package com.innotek.demotestproject.activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import com.innotek.demotestproject.R;

import util.imageload.ImageUtil;

public class ImageLoadeActivity extends BaseActivity {

      private ImageView iv;
      private Context context;

    private String url = "http://innotek-bucket-test.oss-cn-hangzhou.aliyuncs.com/170628175643240.png";
              @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loade);
        context = this;
        iv = (ImageView) findViewById(R.id.iv);
        initData();
    }
    public void initData(){
      //  Glide.with(this).load(url).into(iv);
       ImageUtil.getInstance().setImageLoade(this).setImageLoadUrl(url).loadTo(iv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
