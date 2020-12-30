package com.innotek.demotestproject.view.hencoder.viewgroup;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * description ： 流式布局
 * author : 姓名
 * date : 12/10/20 10:46
 */
public class FlowLayout extends ViewGroup {


    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //子view的布局坐标集合
    List<Rect> childrenBounds = new ArrayList<>();

    /**
     * 重写 onMeasure 依次测量每个子view的宽高，以及计算并设置当前布局的总宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //布局内子view已经占用的宽度
        int widthUsed = 0;
        //布局内子view已经占用的高度
        int heightUsed = 0;

        //当前行所使用的宽度
        int lineWidth = 0;
        //当前行所使用的高度
        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {

            View subView = getChildAt(i);
            int subMeasuredWidth = subView.getMeasuredWidth();
            int subMeasuredHeight = subView.getMeasuredHeight();
            MarginLayoutParams layoutParams = (MarginLayoutParams) subView.getLayoutParams();
            //将子view的margin值算入子view所需的宽高中
            subMeasuredWidth = subMeasuredWidth + layoutParams.leftMargin + layoutParams.rightMargin;
            subMeasuredHeight = subMeasuredHeight + layoutParams.topMargin + layoutParams.bottomMargin;

            measureChildWithMargins(subView, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);

            //换行操作
            if (widthMode != MeasureSpec.UNSPECIFIED && lineWidth + subMeasuredWidth > widthSize) {
                //新行的起点
                lineWidth = 0;
                //新行的高度
                heightUsed += lineHeight;
                measureChildWithMargins(subView, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

            Rect rect;
            if (childrenBounds.size() <= i) {
                rect = new Rect();
                childrenBounds.add(rect);
            } else {
                rect = childrenBounds.get(i);
            }

            //保存当前子view的左上右下坐标
            rect.set(lineWidth, heightUsed, lineWidth + subMeasuredWidth, heightUsed + subMeasuredHeight);
            //当前行的行宽
            lineWidth += subMeasuredWidth;
            //获取子view的最高高度
            lineHeight = Math.max(lineHeight, subMeasuredHeight);
            //子view已经占用的宽
            widthUsed = Math.max(lineWidth, widthUsed);

        }

        //本布局的宽高
        int measureWidth = widthUsed;
        heightUsed += lineHeight;
        int measureHeight = heightUsed;

        //设置当前布局的总宽高
        setMeasuredDimension(measureWidth, measureHeight);

    }

    /**
     * 将子view的布局工作在此处理（子view如何摆放的逻辑）
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
       /* for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if(i == 0){
                childAt.layout(l,t,(l+r)/2,(t+b)/2);
            }else {
                childAt.layout((l+r)/2,(t+b)/2,r,b);
            }
        }*/

        for (int i = 0; i < getChildCount(); i++) {
            View subView = getChildAt(i);
            Rect rect = childrenBounds.get(i);
            //对子view的摆放添加margin间隔设置
            MarginLayoutParams lp = (MarginLayoutParams) subView.getLayoutParams();
            int left = rect.left + lp.leftMargin;
            int top = rect.top + lp.topMargin;
            int right = rect.right - lp.rightMargin;
            int bottom = rect.bottom - lp.bottomMargin;
            subView.layout(left, top, right, bottom);

        }

    }

    /**
     * 进行 measureChild操作时，需要重写此方法以考虑间隔参数
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
