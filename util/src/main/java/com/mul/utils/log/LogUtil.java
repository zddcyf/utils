package com.mul.utils.log;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.mul.utils.FileAccessor;
import com.mul.utils.StringUtils;
import com.mul.utils.date.DateUtils;
import com.mul.utils.manager.GlobalManager;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils.log
 * @ClassName: LogExceptionResult
 * @Author: zdd
 * @CreateDate: 2021/4/7 15:51:20
 * @Description: 日志输出工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/7 15:51:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LogUtil {
    public static void v(String message) {
        v("", message);
    }

    public static void v(String tag, String message) {
        Log.v(getTag(tag), message);
    }

    public static void saveV(String message) {
        saveV("", message);
    }

    public static void saveV(String tag, String message) {
        Log.v(getTag(tag), message);
        save(GlobalManager.INSTANCE.debug, getTag(tag), message);
    }

    public static void d(String message) {
        d("", message);
    }

    public static void d(String tag, String message) {
        Log.d(getTag(tag), message);
    }

    public static void saveD(String message) {
        saveD("", message);
    }

    public static void saveD(String tag, String message) {
        Log.d(getTag(tag), message);
        save(GlobalManager.INSTANCE.debug, getTag(tag), message);
    }

    public static void i(String message) {
        i("", message);
    }

    public static void i(String tag, String message) {
        Log.i(getTag(tag), message);
    }

    public static void saveI(String message) {
        saveI("", message);
    }

    public static void saveI(String tag, String message) {
        Log.i(getTag(tag), message);
        save(GlobalManager.INSTANCE.debug, getTag(tag), message);
    }

    public static void w(String message) {
        w("", message);
    }

    public static void w(String tag, String message) {
        Log.w(getTag(tag), message);
    }

    public static void saveW(String message) {
        saveW("", message);
    }

    public static void saveW(String tag, String message) {
        Log.w(getTag(tag), message);
        save(GlobalManager.INSTANCE.debug, getTag(tag), message);
    }

    public static void e(String message) {
        e("", message);
    }

    public static void e(String tag, String message) {
        Log.e(getTag(tag), message);
    }

    public static void saveE(String message) {
        saveE("", message);
    }

    public static void saveE(String tag, String message) {
        Log.e(getTag(tag), message);
        save(GlobalManager.INSTANCE.error, getTag(tag), message);
    }

    private static String getTag(String tag) {
        String[] logInfo = getFunctionInfo();
//        return String.format("%s-%s"
//                , tag
//                , logInfo[2]);
        return String.format("%s-%s-%s"
//                logInfo[1].substring(0, logInfo[1].indexOf("."))
                , !TextUtils.isEmpty(tag) ? tag :
                        !TextUtils.isEmpty(logInfo[1]) ? logInfo[1].substring(0, logInfo[1].indexOf(".")) : LogTagConfig.UTILS_TAG
                , logInfo[2]
                , logInfo[3]);
    }

    private static String[] getFunctionInfo() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(LogUtil.class.getName())) {
                continue;
            }
//            Log.i("getFunctionInfo","/"+Thread.currentThread().getName()+"/"+st.getFileName()+"/"+st.getLineNumber()+"/"+st.getMethodName());
            String[] result = new String[5];
            result[0] = Thread.currentThread().getName();
            result[1] = st.getFileName();
            result[2] = StringUtils.parseString(st.getLineNumber());
            result[3] = st.getMethodName();
            result[4] = result[1].substring(0, result[1].length() - 5);
            return result;
        }
        return null;
    }

    public static void save(String name, String tag, String msg) {
        if (GlobalManager.INSTANCE.isCaptureLog != 0) {
            return;
        }

        if (msg == null) {
            return;
        }
        try {
//            String fileName = tag.substring(0, tag.indexOf("-")) + "-" + DateUtils.INSTANCE.getDate() + ".log";
            String fileName = tag.substring(0, tag.indexOf("-")) + ".log";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                String path = FileAccessor.INSTANCE.FILE_FILE_CRASH_LOG
                        + File.separator + name + File.separator
                        + DateUtils.INSTANCE.getDate();
                File dir = new File(path);
                if (!dir.exists()) {
                    boolean mMkdirs = dir.mkdirs();
                    Log.e(tag, mMkdirs + "");
                }

                if (!dir.exists()) {
                    return;
                }
                fileName = path + File.separator + fileName;
                FileOutputStream fos = new FileOutputStream(fileName, true);
                fos.write(StringUtils.parseString(tag, "::", GlobalManager.INSTANCE.getCreateTime(), "::", msg).getBytes());
                fos.write("\r\n".getBytes());
                fos.close();
            }
        } catch (Exception e) {
            Log.e(tag, "an error occured while writing file...", e);
        }
    }
}
