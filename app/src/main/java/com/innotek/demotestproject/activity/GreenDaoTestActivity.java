package com.innotek.demotestproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.database.greendaouse.DataBaseMannager;
import com.innotek.demotestproject.database.greendaouse.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import util.ToastUtil;

public class GreenDaoTestActivity extends Activity {

    @BindView(R.id.btn_adddata)
    Button btn_adddata;
    @BindView(R.id.btn_querydata)
    Button btn_querydata;
    @BindView(R.id.btn_queryone)
    Button btn_queryone;
    @BindView(R.id.btn_update)
    Button btn_update;
    @BindView(R.id.tv_content)
    TextView tv_content;

    private List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_adddata)
    public void addData() {
        list = new ArrayList<User>();
        list.clear();
        DataBaseMannager.getInstance(this).deleteUserData();
        for (int i = 0; i < 5; i++) {
            //  UserNew user = new UserNew();
            User user = new User();
            user.setUserId(i);
            user.setNickName("whqNick" + i);
            user.setUserName("whq" + i);
            user.setAge(10 + i);
            user.setHeight(170 + i);
            user.setHasGF(false);
            list.add(user);
        }
        DataBaseMannager.getInstance(this).insertUserListData(list);
    }

    /* 查询所有数据，当数据库版本更新时，先点击此按钮看修改的字段以及数据是否已经迁移到新的数据库中栈（如果更新成功，新加的字段会展示出来，只是数据为null），
     然后点击btn_update 插入新的数据，再次点击查询操作，来查看新的数据是否保存到数据库
     如果 数据中新的字段有值，则说明数据库的迁移与升级工作成功执行*/
    @OnClick(R.id.btn_querydata)
    public void querydata() {

        List<User> userList = DataBaseMannager.getInstance(this).checkUserData();
        if (userList != null && userList.size() > 0) {
            tv_content.setText(userList.toString());
        }
    }

    @OnClick(R.id.btn_update)
    public void updateData() {
        list = new ArrayList<User>();
        list.clear();
        for (int i = 5; i < 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setNickName("whqNick" + i);
            user.setUserName("whq" + i);
            user.setAge(10 + i);
            user.setHeight(170 + i);
            user.setHasGF(false);
            user.setMoneyCount(i * (0.2d));
            user.setWoshi("whq" + i);
            list.add(user);
        }
        DataBaseMannager.getInstance(this).insertUserListData(list);
    }

    @OnClick(R.id.btn_queryone)
    public void querydataone() {
        User user = new User();
        user.setUserId(0);
        user.setNickName("whqNick0");
        user.setUserName("whq0");
        user.setAge(10);
        user.setHeight(170);

        boolean bool = DataBaseMannager.getInstance(this).isHasSameData(user);
        ToastUtil.show(String.valueOf(bool));
    }
}
