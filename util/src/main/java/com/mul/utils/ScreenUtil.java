package com.mul.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.mul.utils.manager.GlobalManager;

/**
 * @ProjectName: youlinanzhuoyuanshengduan
 * @Package: com.wisdom.tdweilaiapp.utils.glide
 * @ClassName: CornerTransform
 * @Author: zdd
 * @CreateDate: 2019/12/11 20:30
 * @Description: 封装px和dp的转换工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/12/11 20:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class ScreenUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dpToPx(float dpValue) {
        DisplayMetrics displayMetrics = GlobalManager.INSTANCE.context.getResources().getDisplayMetrics();
        float scale = displayMetrics.density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int pxToDp(float pxValue) {
        DisplayMetrics displayMetrics = GlobalManager.INSTANCE.context.getResources().getDisplayMetrics();
        float scale = displayMetrics.density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getWidth() {
        DisplayMetrics displayMetrics = GlobalManager.INSTANCE.context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getHeight() {
        DisplayMetrics displayMetrics = GlobalManager.INSTANCE.context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = GlobalManager.INSTANCE.context.getResources().getDisplayMetrics();
        return displayMetrics;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        Resources resources = GlobalManager.INSTANCE.context.getResources();
        int resourceId = GlobalManager.INSTANCE.context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
}
