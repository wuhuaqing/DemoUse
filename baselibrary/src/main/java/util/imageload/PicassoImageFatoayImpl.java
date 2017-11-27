package util.imageload;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by admin on 2017/7/7.
 * Picasso 图片加载框架
 */

public class PicassoImageFatoayImpl implements IimageloadeInterface {

    private Context context;
    private  ImageLoader imageLoader;

    public   PicassoImageFatoayImpl(Context context){
          this.context = context;
          imageLoader = new ImageLoader();
    }

    @Override
    public void setImageLoaderUrl(String url) {
        if(imageLoader!=null) {
            imageLoader.setUrl(url);
        }
    }

    @Override
    public void setTargetView(ImageView targetView) {
        if(imageLoader!=null) {
            imageLoader.setImageView(targetView);
        }
    }

    @Override
    public void setImageToView(ImageView iv) {
        if(imageLoader!=null){
            Picasso.with(context).load(imageLoader.getUrl()).into(imageLoader.getImageView());
        }
    }
}
