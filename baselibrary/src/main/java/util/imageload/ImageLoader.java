package util.imageload;

import android.widget.ImageView;

/**
 * Created by admin on 2017/7/7.
 * 加载实体类，用于存放图片加载相关的属性
 */

public class ImageLoader   {

    public String url ;//图片网络地址
    public String filepath;//图片本地路径
    public ImageView imageView;
    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }



}
