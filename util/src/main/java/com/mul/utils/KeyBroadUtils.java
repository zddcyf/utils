package com.mul.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils
 * @ClassName: FileAccessor
 * @Author: zdd
 * @CreateDate: 2019/12/2 15:09
 * @Description: 键盘工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/12/2 15:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class KeyBroadUtils {
    /**
     * 关闭键盘
     */
    public static void closeKeyBroad(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 打开键盘
     */
    public static void openKeyBroad(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        if (activity.getCurrentFocus() == null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } else {
            imm.showSoftInput(activity.getCurrentFocus(), 0);
        }
    }
}