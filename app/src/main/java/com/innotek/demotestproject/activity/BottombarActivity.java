package com.innotek.demotestproject.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.fragment.BaseFragment;
import com.innotek.demotestproject.fragment.bottombarfragment.FragmentOne;
import com.innotek.demotestproject.fragment.bottombarfragment.FragmentThr;
import com.innotek.demotestproject.fragment.bottombarfragment.FragmentTwo;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import util.ToastUtil;


/**
 * BottomBar使用案例
 * https://github.com/roughike/BottomBar
 */
public class BottombarActivity extends BaseActivity {//

    private BottomBar bottomBar;
    private ViewPager vp_main;
    private List<BaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottombar);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        vp_main = (ViewPager) findViewById(R.id.vp_main);


        initFragment();
        bottomBar.selectTabAtPosition(1);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                ToastUtil.show("tabId: "+tabId);
             //   bottomBar.getCurrentTabPosition();
                if (tabId ==R.id.tab_one){
                    vp_main.setCurrentItem(0,true);
                }
                if (tabId ==R.id.tab_two){
                    vp_main.setCurrentItem(1,true);
                }
                if (tabId ==R.id.tab_thr){
                    vp_main.setCurrentItem(2,true);
                }


            }
        });


    }


    private  void initFragment(){

        fragmentList = new ArrayList<>();
        fragmentList.add(FragmentOne.newInstance("fragmentone_whq"));
        fragmentList.add(new FragmentTwo());
        fragmentList.add(new FragmentThr());

        vp_main.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
