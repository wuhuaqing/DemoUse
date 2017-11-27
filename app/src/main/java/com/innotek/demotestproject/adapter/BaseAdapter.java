package com.innotek.demotestproject.adapter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author THINK
 * @Email guoyil199@163.com
 * @version V1.0
 * @Date 2016年5月12日 下午2:46:20
 * @Title <B>Adapter </B>
 * <pre>todo</pre>
 * @param <T>
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private List<T> list;
    protected Context context;

    protected OnItemPartClickListener onItemPartClick;
    public void setOnItemParkClick(OnItemPartClickListener onItemPartClick){
        this.onItemPartClick=onItemPartClick;
    }

    /**
     * @version V1.0
     * @Date 2016年5月12日 下午2:51:55
     * <pre>ListView Item 中的点击事件回调</pre>
     */
    public interface OnItemPartClickListener{
        void onItemPartClick(View v,Object obj,int pos);
    }


    public BaseAdapter(Context context) {
        init(context, new ArrayList<T>());
    }

    public BaseAdapter(Context context, List<T> list) {
        init(context, list);
    }

    private void init(Context context, List<T> list) {
        this.list = list;
        this.context = context;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void clear() {
        if (list != null) {
            this.list.clear();
            notifyDataSetChanged();
        }
    }

    public void addAll(List<T> list) {
        if (list != null) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public T getItem(int position) {
        return getCount()>0 ? list.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflate(getContentView());
        }
        onInitView(convertView, position);
        return convertView;
    }

    private View inflate(int layoutResID) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResID, null);
        return view;
    }

    public abstract int getContentView();

    public abstract void onInitView(View view, int position);

    @SuppressWarnings("unchecked")
    protected <E extends View> E get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (null == viewHolder) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (null == childView) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);

        }
        return (E) childView;
    }
}

