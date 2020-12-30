package com.innotek.demotestproject.view.hencoder.matrixcamera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.innotek.demotestproject.utils.ViewUtils;


/**
 * description ： TODO:类的作用
 * author : 姓名
 * date : 2020/10/14 15:45
 */
public class MatrixCameraView extends View {

    private static final int IMAGE_WIDTH = (int) ViewUtils.dp2px(200);
    private static final int IMAGE_PADDING = (int) ViewUtils.dp2px(100);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap image;
    Camera camera =  new Camera();


    public MatrixCameraView(Context context) {
        super(context);
    }

    public MatrixCameraView(Context context, @Nullable AttributeSet attrs ) {
        super(context, attrs);
        init();
    }

    private void init(){
            image = ViewUtils.getAvatar(getResources(), IMAGE_WIDTH);
            camera.rotateX(45);
            camera.setLocation(0 , 0, ViewUtils.getZForCamera()); // -8 * 72
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-30);
        canvas.clipRect(- IMAGE_WIDTH, - IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(30);
        canvas.translate(- (IMAGE_PADDING + IMAGE_WIDTH / 2), - (IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-30);
        camera.applyToCanvas(canvas);
        canvas.clipRect(- IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(30);
        canvas.translate(- (IMAGE_PADDING + IMAGE_WIDTH / 2), - (IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, paint);
        canvas.restore();
    }
}
