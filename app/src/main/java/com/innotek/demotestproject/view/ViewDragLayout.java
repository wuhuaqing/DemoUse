package com.innotek.demotestproject.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.innotek.demotestproject.R;

/**
 * Created by hongliang on 2016/1/15.
 */
public class ViewDragLayout extends RelativeLayout {
    private ViewDragHelper mDragger;
    private View ivScroll;
    private View iv_bg;

    public ViewDragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == ivScroll;
            }
            /**
             * 限制在viewGroup内的横向滑动
             * @param child
             * @param left
             * @param dx
             * @return
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return 0;
            }

            /**
             * 限制在ViewGroup竖直方向中滑动
             * @param child
             * @param top
             * @param dy
             * @return
             */
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - ivScroll.getHeight() - topBound;
                final int newTop = Math.min(Math.max(top, topBound), bottomBound);
                return newTop;
            }

            //手指释放回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (releasedChild == ivScroll) {

                    int height = getHeight();
                    //当前位置，考虑车子本身大小
                    int currentPositionY = ivScroll.getTop() + ivScroll.getHeight() / 2;
                    if (currentPositionY <= height / 4) {
                        mDragger.settleCapturedViewAt(ivScroll.getLeft(), 0);
                        Toast.makeText(getContext(),"1", Toast.LENGTH_LONG).show();
                    } else if (currentPositionY >= (height / 4) && currentPositionY <= (3 * height / 4)) {
                        mDragger.settleCapturedViewAt(ivScroll.getLeft(), height / 2 - ivScroll.getHeight() / 2);
                        Toast.makeText(getContext(),"2", Toast.LENGTH_LONG).show();
                    } else {
                        mDragger.settleCapturedViewAt(ivScroll.getLeft(), height - ivScroll.getHeight());
                        Toast.makeText(getContext(),"3", Toast.LENGTH_LONG).show();
                    }
                    invalidate();
                    Log.e("hongliang", ivScroll.getLeft() + "  top=" + ivScroll.getTop());
                }

            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }
    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivScroll = findViewById(R.id.iv_scroll);
        iv_bg = findViewById(R.id.iv_bg);
    }
}
