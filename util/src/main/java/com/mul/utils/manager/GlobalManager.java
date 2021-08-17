package com.mul.utils.manager;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;

import com.mul.utils.date.DateUtils;

import java.lang.reflect.Method;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils.manager
 * @ClassName: GlobalConfig
 * @Author: zdd
 * @CreateDate: 2020/7/2 9:43
 * @Description: 公共变量的管理类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/2 9:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public enum GlobalManager {
    INSTANCE;

    private Stack<Context> activityStack; // 栈内的activity
    private Stack<Context> tempActivityStack; // 栈内的activity
    public Application app; // application
    public Context context; // 上下文

    // 全局变量
    public int versionCode; // 版本code码
    public String versionName; // 版本号
    public String packageName; // 包名
    public String flavorType;
    public Class mainClz;

    /**
     * log相关
     */
    public int isCaptureLog = 0; // 是否开启日志 0开启
    public String error = "error";
    public String debug = "debug";

    public AtomicBoolean isInit;
    public AtomicBoolean isNetwork;

    /**
     * 应用程序总体的SharedPreferences文件名称
     */
    public String PREFIX;

    public void init() {
        isInit = new AtomicBoolean();
        isNetwork = new AtomicBoolean();

        activityStack = new Stack<>(); // 栈内的activity
        tempActivityStack = new Stack<>(); // 栈内的activity

        getContext();
//        MAC = "402c7665febe";
//        getRequestId();
        getPackageInfo();
    }

    private Application getApp() {
        if (null == app) {
            try {
                Method currentApplication = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication");
                app = (Application) currentApplication.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return app;
    }

    private Context getContext() {
        if (null != context) {
            return context;
        }

        if (app != null) {
            context = app.getApplicationContext();
        } else {
            context = getApp().getApplicationContext();
        }
        return context;
    }

    /**
     * 通过PackageInfo得到的想要启动的应用的包名
     */
    private void getPackageInfo() {
        try {
            //通过PackageManager可以得到PackageInfo
            PackageManager pManager = getContext().getPackageManager();
            PackageInfo pInfo = pManager.getPackageInfo(getContext().getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            versionCode = pInfo.versionCode;
            packageName = getContext().getPackageName();
            versionName = pInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCreateTime() {
        return DateUtils.INSTANCE.getDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将activity压入栈
     *
     * @param activity 上下文
     */
    public void pushActivity(AppCompatActivity activity) {
        // 如果该activity是FLAG_ACTIVITY_NEW_TASK 栈内复用
        if ((activity.getIntent().getFlags() == Intent.FLAG_ACTIVITY_NEW_TASK) && activityStack.search(activity) != -1) {
            activityStack.remove(activity);
        }
        // 如果该activity是FLAG_ACTIVITY_NEW_TASK 栈顶复用
        if (activity.getIntent().getFlags() == Intent.FLAG_ACTIVITY_SINGLE_TOP && currentActivity() == activity) {
            activityStack.remove(activity);
        }
        activityStack.push(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public AppCompatActivity currentActivity() {
        return (AppCompatActivity) activityStack.peek();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     * pop取出后移出栈
     * peed取出后不会移出栈
     */
    public void finishCurrentActivity() {
        AppCompatActivity activity = (AppCompatActivity) activityStack.pop();
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(AppCompatActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        AppCompatActivity mActivity = null;
        for (Context activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                mActivity = (AppCompatActivity) activity;
                break;
            }
        }
        finishActivity(mActivity);
    }

    /**
     * 结束所有的activity
     */
    public void finishNonClassActivity() {
        finishNonClassActivity(currentActivity());
    }

    /**
     * 结束结束非class的activity
     */
    public void finishNonClassActivity(Context mContext) {
        for (Context mActivity : activityStack) {
            AppCompatActivity mActivity1 = (AppCompatActivity) mActivity;
            if (!mActivity1.getClass().equals(mContext.getClass())) {
                mActivity1.finish();
            }
        }
        activityStack.clear();
        activityStack.push(mContext);
    }

    /**
     * 结束所有的activity
     */
    public void finishAllActivity() {
        for (Context mActivity : activityStack) {
            ((AppCompatActivity) mActivity).finish();
        }
        activityStack.clear();
    }

    /**
     * 将activity压入栈
     *
     * @param activity 上下文
     */
    public void pushTempActivity(AppCompatActivity activity) {
        // 如果该activity是FLAG_ACTIVITY_NEW_TASK 栈内复用
        if ((activity.getIntent().getFlags() == Intent.FLAG_ACTIVITY_NEW_TASK) && activityStack.search(activity) != -1) {
            tempActivityStack.remove(activity);
        }
        // 如果该activity是FLAG_ACTIVITY_NEW_TASK 栈顶复用
        if (activity.getIntent().getFlags() == Intent.FLAG_ACTIVITY_SINGLE_TOP && currentActivity() == activity) {
            tempActivityStack.remove(activity);
        }
        tempActivityStack.push(activity);
    }

    /**
     * 结束所有的activity
     */
    public void finishTempAllActivity() {
        for (Context mActivity : tempActivityStack) {
            ((AppCompatActivity) mActivity).finish();
        }
        tempActivityStack.clear();
    }
}
