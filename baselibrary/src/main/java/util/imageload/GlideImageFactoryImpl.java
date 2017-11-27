package util.imageload;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by admin on 2017/7/7.
 *
 * Glide 图片加载框架的工厂类，对Glide加载图片的的方法封装
 */

public class GlideImageFactoryImpl implements IimageloadeInterface {

    private  Context context;
    private  ImageLoader imageLoader;

     public GlideImageFactoryImpl(Context context){
         this.context  =  context;
         imageLoader = new ImageLoader();
     }

    @Override
    public void setImageLoaderUrl(String url) {
        if(imageLoader!=null) {
            imageLoader.setUrl(url);
        }
    }

    @Override
    public void setTargetView(ImageView targetView){
        if(imageLoader!=null) {
            imageLoader.setImageView(targetView);
        }
    }
    @Override
    public void setImageToView(ImageView iv) {
        if(imageLoader!=null){
            Glide.with(context).load(imageLoader.getUrl()).into(imageLoader.getImageView());
        }
    }
}
