package com.innotek.demotestproject.kotlinpackage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.innotek.demotestproject.R
import java.util.*

/**
 * Created by admin on 2017/5/25.
 */

class ListViewAdatpter(var contex: Context, var list: ArrayList<String>) : BaseAdapter() {

    var mLayouInflate: LayoutInflater = LayoutInflater.from(contex)

    override fun getItem(p0: Int): Any {
        return list.get(p0)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(p0: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = mLayouInflate.inflate(R.layout.kotlin_layout_listviewitem, null)
        var tv_item = view?.findViewById(R.id.tv_item) as TextView
        var str = getItem(p0)
        tv_item.text = str as CharSequence?
        tv_item.textSize = 20f
        view.setOnClickListener {
            onclicklister!!.onclicked(p0, p1)

        }
        return view
    }

    interface OnclickLis {
        fun onclicked(position: Int, view: View?)
    }

    //var 是可变变量
    var onclicklister: OnclickLis? = null

    fun setOnclickLis(onclicklis: OnclickLis) {
        this.onclicklister = onclicklis
    }
}
