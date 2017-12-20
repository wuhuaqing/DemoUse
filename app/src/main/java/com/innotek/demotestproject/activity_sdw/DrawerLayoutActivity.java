package com.innotek.demotestproject.activity_sdw;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;
import com.innotek.demotestproject.activity_sdw.listener.MenuItemClickedListener;

import util.ToastUtil;

/**
 * Created by admin on 2017/12/12.
 */

public class DrawerLayoutActivity extends BaseActivity implements View.OnClickListener  {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView menu;
    private View headerView;
    private MenuItem menuItem;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        initView();
        toolbar.setVisibility(View.GONE);
    }

    public void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        menu = (ImageView) findViewById(R.id.main_menu);
        tv_content = (TextView) findViewById(R.id.tv_content);
        //获取头布局
        headerView = navigationView.getHeaderView(0);
        ImageView iv_person = (ImageView) headerView.findViewById(R.id.person);
        TextView tv_sign = (TextView) headerView.findViewById(R.id.tv_sign);
        TextView tv_name = (TextView) headerView.findViewById(R.id.tv_name);
        iv_person.setOnClickListener(this);
        tv_sign.setOnClickListener(this);
        tv_name.setOnClickListener(this);
        menu.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // item.setChecked(true);
                // Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                ToastUtil.show(item.getTitle().toString());
                menuItem = item;
                tv_content.setText(item.getTitle().toString());
                // drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_menu://点击菜单，跳出侧滑菜单
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView);
                } else {
                    drawerLayout.openDrawer(navigationView);
                }
                break;
            case R.id.person:
                ToastUtil.show("person");
                break;
            case R.id.tv_sign:
                ToastUtil.show("tv_sign");
                break;
            case R.id.tv_name:
                ToastUtil.show("tv_name");
                break;
            default:
        }
    }


}
