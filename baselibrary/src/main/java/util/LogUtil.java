package util;

import android.util.Log;

/**
 * LogUtil Util
 * @author whq
 *
 */
public class LogUtil {

	private LogUtil() {}
	
	public static boolean isDebug= true;

	private static final String TAG = "TAG";

	public static void i(String msg) {
		if (isDebug)
			Log.i(TAG, generate(msg));
	}

	public static void d(String msg) {
		if (isDebug)
			Log.d(TAG, generate(msg));
	}

	public static void e(String msg) {
		if (isDebug)
			Log.e(TAG, generate(msg));
	}

	public static void v(String msg) {
		if (isDebug)
			Log.v(TAG, generate(msg));
	}

	public static void i(String tag, String msg) {
		if (isDebug)
			Log.i(tag, generate(msg));
	}

	public static void i(String tag, String msg, Throwable e) {
		if (isDebug)
			Log.i(tag, generate(msg),e);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			Log.d(tag, generate(msg));
	}

	public static void d(String tag, String msg, Throwable e) {
		if (isDebug)
			Log.d(tag, generate(msg),e);
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			Log.e(tag, generate(msg));
	}
	
	public static void e(String msg, Throwable e) {
		if (isDebug)
			Log.e(TAG, generate(msg),e);
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			Log.v(tag,generate(msg));
	}
	
	private static String generate(String prefix) {
		StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
		String tag = "[%s.%s(L:%d)]: %s";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber(),prefix==null?"":prefix);
		return tag;
	}

	public static void e(String tag, String s, Throwable throwable) {
		if (isDebug)
			Log.e(tag,generate(s),throwable);
	}

	public static void w(String tag, String s) {
		if(isDebug)
			Log.w(tag,generate(s));
	}
}