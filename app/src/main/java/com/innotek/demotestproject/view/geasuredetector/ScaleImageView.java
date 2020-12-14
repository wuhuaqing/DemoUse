package com.innotek.demotestproject.view.geasuredetector;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import com.innotek.demotestproject.utils.ViewUtils;

import util.LogUtil;

/**
 * description ： TODO:支持缩放的ImageView
 * author : 姓名
 * date : 12/14/20 14:24
 * 第一步：加载图片资源到View中
 * 第二步：确认缩放比例逻辑和缩放的轴心坐标
 * 第三步：使用手势监听器接管事件分发器，识别用户的操作手势
 * 第四步：判断手势操作，进行缩放逻辑处理：双击缩放
 * 第五步：对缩放体验进行优化，加入动画
 * 第六步：图片拖动
 * 第七步：加入惯性滑动处理
 */
public class ScaleImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    public static final float OVER_SCALE_FACTOR = 1.5f;
    Paint paint = new Paint();
    private Bitmap bitmap;
    private float originalOffsetX;
    private float originalOffsetY;
    private float offsetX;
    private float offsetY;

    //缩小值
    private float scaleSmall;
    //放大值
    private float scaleBig;
    private float currentScale;

    private boolean isBig;//是否放大
    private GestureDetectorCompat gestureDetector;
    private OverScroller scroller;


    float scaleFraction;//0~1f
    private ObjectAnimator scaleAnimator;

    public ScaleImageView(Context context) {
        super(context);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = ViewUtils.getAvatar(getResources(), (int) ViewUtils.dp2px(300));
        gestureDetector = new GestureDetectorCompat(context, this);
        scroller = new OverScroller(context);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = (getWidth() - bitmap.getWidth()) / 2f;
        originalOffsetY = (getHeight() - bitmap.getHeight()) / 2f;

        float bitmapRate = (float) bitmap.getWidth() / bitmap.getHeight();
        float viewRate = (float) getWidth() / getHeight();
        if (bitmapRate > viewRate) {
            scaleSmall = (float) getWidth() / bitmap.getWidth();
            scaleBig = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            scaleSmall = (float) getHeight() / bitmap.getHeight();
            scaleBig = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }
        currentScale = scaleSmall;
        //currentScale =  scaleBig;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //偏移操作
        canvas.translate(offsetX, offsetY);
        //缩放操作
        //加入动画渐变控制
        currentScale = scaleSmall + (scaleBig - scaleSmall) * scaleFraction;
        // sx sy 是横向和纵向的放缩倍数； px py 是放缩的轴心。
        canvas.scale(currentScale, currentScale, (float) getWidth() / 2f, (float) getHeight() / 2f);
        //图片绘制
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint);
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    public ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return scaleAnimator;
    }


    /***
     * 使用手势监测器，需要接管onTouchEvent 来识别用户操作手势
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    // 使用手势器，onDown方法返回true，代表当前view消费触摸事件
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override   // 等同于onMove
    public boolean onScroll(MotionEvent downEvent, MotionEvent event, float distanceX, float distanceY) {
        //放大状态做滑动偏移处理
        if (isBig) {
            offsetX -= distanceX;
            offsetY -= distanceY;
            //偏移修正，让图片的滑动在一定的范围内进行滑动
            //x轴的往左移动到最左侧 的左限
            offsetX = Math.min(offsetX, (bitmap.getWidth() * scaleBig - getWidth()) / 2f);
            //x轴的往右移动到最右侧 的右限
            offsetX = Math.max(offsetX, -(bitmap.getWidth() * scaleBig - getWidth()) / 2f);
            //Y轴的往上移动到 最上侧 的上限
            offsetY = Math.min(offsetY, (bitmap.getHeight() * scaleBig - getHeight()) / 2f);
            //Y轴的往下移动到 最下侧 的下限
            offsetY = Math.max(offsetY, -(bitmap.getHeight() * scaleBig - getHeight()) / 2f);

//            if(offsetX > (bitmap.getWidth()*scaleBig - getWidth())/2f){
//                offsetX = (bitmap.getWidth()*scaleBig - getWidth())/2f;
//            }else if(offsetX < (bitmap.getWidth()*scaleBig - getWidth())/2f){
//                offsetX = -(bitmap.getWidth()*scaleBig - getWidth())/2f;
//            }
//
//            if(offsetY > (bitmap.getHeight()*scaleBig - getHeight())/2f){
//                offsetY = (bitmap.getHeight()*scaleBig - getHeight())/2f;
//            }else if(offsetY <(bitmap.getHeight()*scaleBig - getHeight())/2f){
//                offsetY = -(bitmap.getHeight()*scaleBig - getHeight())/2f;
//            }

            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override  //惯性滑动方法
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (isBig) {
            //惯性滑动X轴边界
            int minX = -(int) (bitmap.getWidth() * scaleBig - getWidth()) / 2;
            int maxX = (int) (bitmap.getWidth() * scaleBig - getWidth()) / 2;
            //惯性滑动y轴边界
            int minY = -(int) (bitmap.getHeight() * scaleBig - getHeight()) / 2;
            int maxY = (int) (bitmap.getHeight() * scaleBig - getHeight()) / 2;

            // scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY, minX, maxX, minY, maxY);
            // x，y 超出值 （超出回弹效果）
            scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY, minX, maxX, minY, maxY, (int) ViewUtils.dp2px(100), (int) ViewUtils.dp2px(100));
            //post 与  postOnAnimation 的区别： postOnAnimation是一帧接一帧在主线程刷新，post可能同一帧会执行好几次post(在没有使用invalidate()时)
            postOnAnimation(new Runnable() {
                @Override
                public void run() {
                    //惯性滑动未完成继续一帧一帧刷新动画
                    if (scroller.computeScrollOffset()) {
                        offsetX = scroller.getCurrX();
                        offsetY = scroller.getCurrY();
                        invalidate();
                        postOnAnimation(this);
                    }

                }
            });
        }
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override  //双击响应
    public boolean onDoubleTap(MotionEvent e) {
        LogUtil.e("onDoubleTap " + e.getX());
        isBig = !isBig;
        if (isBig) {
            getScaleAnimator().start();
        } else {
            getScaleAnimator().reverse();
        }
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
