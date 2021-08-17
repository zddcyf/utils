package com.mul.utils.glide;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils.glide
 * @ClassName: MySimpleTarget
 * @Author: zdd
 * @CreateDate: 2021/8/16 10:27:41
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/16 10:27:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface MySimpleTarget {
    void onResourceReady(@NonNull Drawable resource);
}
