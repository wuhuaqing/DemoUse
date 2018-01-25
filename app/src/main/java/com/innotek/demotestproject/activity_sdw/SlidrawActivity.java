package com.innotek.demotestproject.activity_sdw;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;
import com.innotek.demotestproject.view.WrapSlidingDrawer;

import java.util.ArrayList;
import java.util.List;

import util.ToastUtil;

public class SlidrawActivity extends BaseActivity {

    private ListView listview;
    private WrapSlidingDrawer wrapSlidingDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidraw);
        listview = (ListView) findViewById(R.id.listview);
        final TextView tv_dd = (TextView) findViewById(R.id.tv_dd);
        initData();
        wrapSlidingDrawer = (WrapSlidingDrawer) findViewById(R.id.sliddrawer);
        wrapSlidingDrawer.open();

        tv_dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(tv_dd.getText().toString());
            }
        });

    }

    public void initData() {
        final List<Catalog> catalogs = new ArrayList<Catalog>();
        catalogs.add(new Catalog("屏幕方向"));
        catalogs.add(new Catalog("打电话"));
        catalogs.add(new Catalog("版本信息"));

        // 这里ListView的适配器选用ArrayAdapter，ListView中每一项的布局选用系统的simple_list_item_1。
        ArrayAdapter<Catalog> adapter = new ArrayAdapter<Catalog>(this, android.R.layout.simple_list_item_1, catalogs);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show(catalogs.get(position).name);
            }
        });
    }

    public class Catalog {
        public Catalog(String name) {
            this.name = name;
        }

        // 条目名称
        public String name;
    }
}
