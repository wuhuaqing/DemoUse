package util.imageload;
import android.content.Context;
import android.widget.ImageView;

/**
 * Created by admin on 2017/7/7.
 * 提供给APP使用的工具类
 * 上层应用通过此类进行数据加载
 */

public class ImageUtil {

    IimageloadeInterface imageLoader  ;

    private ImageUtil(){}
    private static ImageUtil imageUtil = new ImageUtil();
    public static   ImageUtil getInstance()
    {
        return imageUtil;
    }

    /**
     * 设置context
     * @param context
     * @return
     */
     public ImageUtil setImageLoade(Context context){
        // imageLoader = new GlideImageFactoryImpl(context);
         imageLoader =  ImageLoadFactory.getImageLoad(context);
        return   imageUtil;
     }

    /**
     * 设置加载url
     * @param Url
     * @return
     */
     public ImageUtil setImageLoadUrl(String Url){
         if(imageLoader!=null){
             imageLoader.setImageLoaderUrl(Url);
         }
         return imageUtil;
     }


    /***
     * 将图片资源放入指定的view
     * @param imageView
     */
    public void loadTo(ImageView imageView){
        if(imageLoader!=null){
            imageLoader.setTargetView(imageView);
            imageLoader.setImageToView(imageView);
        }
    }

}
