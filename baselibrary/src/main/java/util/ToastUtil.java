package util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.whq.baselibrary.R;

import base.BaseApplication;
/**
 * ToastUtil
 * @author whq
 *
 */
public class ToastUtil {
	private static Toast mToast;

	private ToastUtil() {
	}

	/**
	 * 取消Toast
	 */
	public static void cancel() {
		if (mToast != null) {
			mToast.cancel();
		}
	}

	/**
	 * toast msg
	 * @param mContext
	 * @param msg
	 * @param duration
     * @param gravity
     */
	public static void showToast(final Context mContext, final String msg, final int duration, final int gravity) {
		if (Looper.getMainLooper() == Looper.myLooper()) {
			toast(mContext, msg, duration, gravity);
		} else {
//			Looper.prepare();
//			Looper.loop();
			new Handler(mContext.getMainLooper()){
				@Override
				public void dispatchMessage(Message message) {
					toast(mContext, msg, duration, gravity);
				}
			}.sendEmptyMessageDelayed(0, 100);
		}
	}

	/**
	 * toast msg
	 * @param mContext
	 * @param msg
	 * @param duration
     * @param gravity
     */
	private static void toast(Context mContext, String msg, int duration, int gravity) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, msg, duration);
			mToast.setGravity(gravity, 0, 200);
			
		} else {
			mToast.setText(msg);
		}
		mToast.show();
	}

	/**
	 * toast msg
	 * @param mContext
	 * @param msgId  @string Id
     */
	public static void show(Context mContext, @StringRes int msgId) {
		showToast(mContext, msgId);
	}

	/**
	 * toast msg
	 * @param mContext
	 * @param strId
     */
	public static void showToast(Context mContext, int strId) {
		showToast(mContext, mContext.getString(strId));
	}

	/**
	 * toast msg
	 * @param mContext
	 * @param msg
     */
	public static void show(Context mContext, String msg){
		showToast(mContext,msg);
	}

	/**
	 * toast msg
	 * @param mContext
	 * @param msg
     */
	public static void showToast(Context mContext, String msg) {
		showToast(mContext, msg, Toast.LENGTH_LONG, Gravity.BOTTOM);
	}

	/**
	 * toast msg
	 * @param mContext
	 * @param msg
	 * @param duration
     */
	public static void showToast(Context mContext, String msg, int duration) {
		showToast(mContext, msg, duration, Gravity.BOTTOM);
	}

	/**
	 * toast msg
	 * @param mContext
	 * @param msg
	 * @param gravity
     */
	public static void showToast2(Context mContext, String msg, int gravity) {
		showToast(mContext, msg, Toast.LENGTH_LONG, gravity);
	}

	/**
	 * toast Msg
	 * @see
	 * @param msg
     */
	public static void showToast(String msg){
		showToast(BaseApplication.instance(), msg, Toast.LENGTH_LONG, Gravity.BOTTOM);
	}

	/**
	 * toast msg
	 * @param msg
     */
	public static void show(String msg){
		showToast(BaseApplication.instance(), msg, Toast.LENGTH_LONG, Gravity.BOTTOM);
	}

	/**
	 * toast msg
	 * @param msgId String id
     */
	public static void show(@StringRes int msgId){
		showToast(BaseApplication.instance(), msgId);
	}

	/**
	 * 显示自定义的Toast
	 * @param context
	 * @param toastLayoutId toast LayoutId
	 * @see R.id#id_toast_msg
	 * @see R.id#id_toast_img
	 * @param msg
	 * @param drawable
     */
	public static void showtoast(Context context, @LayoutRes int toastLayoutId, String msg, Drawable drawable) {
		mHandler.removeCallbacks(r);
		if (mToast == null) {
			mToast = new Toast(context);
			View toastView = LayoutInflater.from(context).inflate(toastLayoutId, null);
			if (toastView != null)
				mToast.setView(toastView);
			mToast.setGravity(Gravity.CENTER, 0, 0);
			mToast.setDuration(Toast.LENGTH_LONG);
		}
		View tView = mToast.getView();
		if (tView != null) {
			TextView textView = (TextView) tView.findViewById(R.id.id_toast_msg);
			if (!TextUtils.isEmpty(msg) && textView != null) {
				textView.setText(msg);
			}
			ImageView toastImg = (ImageView) tView.findViewById(R.id.id_toast_img);
			if (toastImg != null && drawable != null) {
				toastImg.setImageDrawable(drawable);
			}
		} else {
			mToast.setText(msg);
		}
		mHandler.postDelayed(r, 3000);
		mToast.show();
	}

	private static Handler mHandler = new Handler();
	private static Runnable r = new Runnable() {
		public void run() {
			if (mToast != null)
				mToast.cancel();
		}
	};

}