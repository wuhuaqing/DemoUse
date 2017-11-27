package util.imageload;

import android.widget.ImageView;

/**
 * Created by admin on 2017/7/7.
 * 图片加载框架方法封装的接口
 */

public interface IimageloadeInterface {

      void setImageLoaderUrl(String url);
      void setTargetView(ImageView targetView);
      void setImageToView(ImageView iv);
}
