package com.innotek.demotestproject.fragment.bottombarfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.fragment.BaseFragment;

public class FragmentOne  extends BaseFragment {

    private  final  static  String ARG ="args";
    private TextView tv_one;

    public static BaseFragment newInstance(String arg){
        FragmentOne fragment = new FragmentOne();
        Bundle bundle = new Bundle();
        bundle.putString( ARG, arg);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmentone, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    public void initView(View view){
        tv_one = (TextView) view.findViewById(R.id.tv_one);
        String bundle = (String) getArguments().get(ARG);
        tv_one.setText(bundle);
    }
}
