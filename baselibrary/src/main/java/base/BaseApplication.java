package base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by admin on 2017/7/21.
 */

public abstract class BaseApplication extends Application {

    public static BaseApplication baseApplication;
    //获取到主线程的handler
    private static Handler mMainThreadHandler ;
    //获取到主线程轮询器
    private static Looper mMainThreadLooper;
    //获取到主线程的Thread
    private static Thread mMainThread;
    //获取到主线程的id
    private static int mMainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        init();
    }

    public static BaseApplication instance(){
        return baseApplication;
    }

    /***
     * 关联基础库的application 相关全局操作在此处理
     */
    public abstract void init();


    //对外暴露一个上下文
    public static BaseApplication getApplication(){
        return baseApplication;
    }
    //对外暴露一个主线程的handelr
    public static Handler getMainThreadHandler(){
        return mMainThreadHandler;
    }
    //对外暴露一个主线程的Looper
    public static Looper getMainThreadLooper(){
        return mMainThreadLooper;
    }
    //对外暴露一个主线程
    public static Thread getMainThread(){
        return mMainThread;
    }
    //对外暴露一个主线程ID
    public static int getMainThreadId(){
        return mMainThreadId;
    }
}
