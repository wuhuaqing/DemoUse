package com.innotek.demotestproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.SearchEditView;

public class SearchEditTextActivity2 extends BaseActivity   {


    private EditText et;
    private SearchEditView sev;
    private LinearLayout activity_search_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_edit_text);
        initView();


    }

    private void initView() {
        et = (EditText) findViewById(R.id.et);
        sev = (SearchEditView) findViewById(R.id.sev);
        activity_search_edit_text = (LinearLayout) findViewById(R.id.activity_search_edit_text);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                 if(editable.toString().length()>0){
                     sev.setText(editable.toString());
                     sev.setIsCompleted(true);
                 }
            }
        });
    }


}