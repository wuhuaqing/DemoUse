package com.innotek.demotestproject.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

import com.innotek.demotestproject.R;

/**
 * description ： TODO:类的作用
 * author : 姓名
 * date : 2020/10/14 15:48
 */
public class ViewUtils {
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static Bitmap getAvatar(Resources res, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, R.mipmap.filpboard, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(res, R.mipmap.filpboard, options);
    }

    public static int getZForCamera() {
        return (int) (- 6 * Resources.getSystem().getDisplayMetrics().density);
    }
}
