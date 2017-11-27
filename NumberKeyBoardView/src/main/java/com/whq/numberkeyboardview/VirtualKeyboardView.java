package com.whq.numberkeyboardview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 虚拟键盘
 */
public class VirtualKeyboardView extends RelativeLayout {
    private Context context;
    private GridView gridView;    //用GrideView布局键盘，其实并不是真正的键盘，只是模拟键盘的功能
    private ArrayList<Map<String, String>> valueList;  //用ArrayList适配Adapter ，用数组不能往adapter中填充

    public VirtualKeyboardView(Context context) {
        this(context, null);
    }

    public VirtualKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = View.inflate(context, R.layout.layout_virtual_keyboard, null);
        valueList = new ArrayList<>();
        gridView = (GridView) view.findViewById(R.id.gv_keybord);
        initValueList();
        setupView();
        addView(view);      //必须要，不然不显示控件
    }


    public ArrayList<Map<String, String>> getValueList() {
        return valueList;
    }

    private void initValueList() {

        // 初始化按钮上应该显示的数字
        for (int i = 1; i < 13; i++) {
            Map<String, String> map = new HashMap<>();
            if (i < 10) {
                map.put("name", String.valueOf(i));
            } else if (i == 10) {
                map.put("name", ".");//"."
            } else if (i == 11) {
                map.put("name", String.valueOf(0));
            } else if (i == 12) {
                map.put("name", "");
            }
            valueList.add(map);
        }
    }

    private void setupView() {

        KeyBoardAdapter keyBoardAdapter = new KeyBoardAdapter(context, valueList);
        gridView.setAdapter(keyBoardAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onPasswordInputFinish != null) {
                    if (position < 11) {    //点击0~9按钮
                        String value= "";
                        if(position==9){
                              value =".";
                        }else{
                              value = valueList.get(position).get("name");
                        }
                        onPasswordInputFinish.inputFinish(value);

                    } else {
                        if (position == 11) {      //点击退格键
                            onPasswordInputFinish.inputDelete();
                        }
                    }
                }
            }
        });
    }

    private OnPasswordInputFinish onPasswordInputFinish;

    public void setOnFinishInput(final OnPasswordInputFinish pass) {
        this.onPasswordInputFinish = pass;
    }

}
