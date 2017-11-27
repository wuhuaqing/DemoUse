package com.innotek.demotestproject.kotlinpackage

import android.os.Bundle
import com.innotek.demotestproject.R
import com.innotek.demotestproject.activity.BaseActivity
import com.innotek.demotestproject.kotlinpackage.adapter.ListViewAdatpter
import kotlinx.android.synthetic.main.activity_kotlin_main.*
import java.util.*

class KotlinMainActivity : BaseActivity() {

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)
        initView()
    }

    fun initView(){
        tv_kotlin.text = actionAdd("aa","bb")
        initData()
    }

    fun initData(){

        var list = ArrayList<String>()
        list.add("aa")
        list.add("bb")
        list.add("cc")
        list.add("dd")
        var listviewAdapter = ListViewAdatpter(this,list)

        //listviewAdapter.setOnclickLis()

        listview.adapter = listviewAdapter
    }

    fun actionAdd(x :String, y:String) : String{
        return x+y
    }
}
