package util.imageload;

import android.content.Context;

/**
 * Created by admin on 2017/7/7.
 *
 *  这里是做图片加载类更换的类
 */

public class ImageLoadFactory {

    public static IimageloadeInterface getImageLoad(Context context){
        IimageloadeInterface iImageloadeFactory
                = new PicassoImageFatoayImpl(context);
        return iImageloadeFactory;
    }
}
