package com.innotek.demotestproject.activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.innotek.demotestproject.Bean.ZhihuBean;
import com.innotek.demotestproject.R;
import com.innotek.demotestproject.utils.http.ApiUrl;
import com.innotek.demotestproject.utils.http.NetWorkClient;
import com.whq.baselibrary.normalhttp.okhttpLib.HttpResult;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import util.ToastUtil;

/***
 * 知乎日报的api
 * 地址来源分析：https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90
 */
public class ZhihuDataActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.btn_cm)
    Button btn_cm;

    private NetWorkClient absHttpClient;
    private int what_getzhihu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_data);
        ButterKnife.bind(this);
        //注册EventBus
        EventBus.getDefault().register(this);
        absHttpClient = new NetWorkClient(this);
    }

    @OnClick(R.id.btn_cm)
    public void click() {
        what_getzhihu = absHttpClient.getAsyncHttp(ApiUrl.ZhihuDta, ZhihuBean.class);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HttpResult<ZhihuBean> result) {
        if (what_getzhihu == result.requestCode) {
            if (result.data != null) {
                ZhihuBean zhihuBean = result.data;
                ToastUtil.show(zhihuBean.date);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
